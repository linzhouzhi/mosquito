package com.lzz.logic;

import com.lzz.dao.Logs;
import com.lzz.dao.Roles;
import com.lzz.model.QueryParam;
import com.lzz.util.ClientSign;
import com.lzz.util.Common;
import com.lzz.util.CommonUtil;
import com.lzz.util.Wechat;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 17/4/9.
 */
public class RoleLogic implements Common{
    /**
     * 通过 wechat 获取成员列表
     * @return
     */
    public JSONObject getUserList(){
        JSONObject res = Wechat.allUserList();
        return res;
    }

    /**
     * 添加一条 报警规则
     * @param reqData
     * @return
     */
    public boolean addRole(com.lzz.model.Roles reqData){
        Roles roles = new Roles();
        String clientid = ClientSign.clientIp();
        reqData.setClientId( clientid );
        int res = roles.insertRole(reqData);
        if( res != 0 ){
            return true;
        }
        return false;
    }

    /**
     * 获取更新时间
     * @param roleId
     * @return
     */
    public int getRoleUpdateTime(int roleId){
        Roles roles = new Roles();
        Map map = roles.selectRoleById( roleId );
        if( map.isEmpty() || map == null ){
            return 0;
        }
        return (int) map.get("send_time");
    }

    public int addRestRole(com.lzz.model.Roles rolesData){
        Roles roles = new Roles();
        int roleId = roles.getRoleId( rolesData.getRoleName() );
        if( roleId != -1 ){
            return roleId;
        }
        roleId = roles.insertRole(rolesData);
        return roleId;
    }

    /**
     * 根据 clientid 获取roles
     * @return
     */
    public List getRoles() {
        Roles roles = new Roles();
        String clientid = ClientSign.clientIp();
        List list = roles.selectRoles(clientid);
        return list;
    }

    public List getServices(){
        Roles roles = new Roles();
        List list = roles.selectServices();
        return list;
    }

    public int getLasteRoleId( String clientIp ){
        Roles roles = new Roles();
        int roleId = roles.getLastIdRoles( clientIp );
        return roleId;
    }

    public boolean updateSendTime(int roleid){
        Roles roles = new Roles();
        int updateRow = roles.updateRolesSendTime( roleid );
        if( updateRow != 0 ){
            return true;
        }
        return false;
    }

    public Map getRoleDetail(int roleid){
        Roles roles = new Roles();
        Map map = roles.selectRoleById( roleid );
        return map;
    }

    public boolean deleteRoleDetail(int roleid){
        Roles roles = new Roles();
        Logs logs = new Logs();
        boolean flag= false;
        flag = roles.deleteRoleById( roleid );
        flag = logs.deleteLogsByRoleid( roleid );
        return flag;
    }

    public List getLogs(String target, String timeType, String type) {
        Logs logs = new Logs();
        List list = null;
        int dateTime = getTypeTime( timeType );
        if( type.equals("role") ){
            list = logs.selectLogsByRoleid(target, dateTime);
        }else {
            list = logs.selectLogsByService(target, dateTime);
        }
        return list;
    }

    public List getMetricGroup(String target, String timeType, String type){
        Logs logs = new Logs();
        List list = null;
        int dateTime = getTypeTime( timeType );
        if( type.equals("role") ){
            list = logs.selectByRoleidGroupLogs(Integer.parseInt(target), timeType, dateTime);
        }else {
            list = logs.selectByServiceGroupLogs(target, timeType, dateTime);
        }
        return list;
    }

    /**
     * 发送微信报警
     * @param roleId
     */
    public void sendMessage(int roleId, float metric, float metricValue, String message, String members){
        if( metric < metricValue ){
            int sendTime = this.getRoleUpdateTime( roleId );
            int currentTime = CommonUtil.getTime();
            // 如果发送时间过了 SEND_RANGE_TIME ，那么就发送报警
            if( (currentTime - sendTime) > SEND_RANGE_TIME ){
                // 微信发送报警
                boolean sendStatus = Wechat.sendTextMessage(members, message);
                if( sendStatus == true ){
                    // 修改 roles 的发送时间
                    this.updateSendTime( roleId );
                }
            }
        }
    }

    public boolean addLog(int roleId, QueryParam queryParam){
        com.lzz.model.Logs logs = new com.lzz.model.Logs();
        logs.setRoleid( roleId );
        logs.setMembers( queryParam.getMembers() );
        logs.setClientId( ClientSign.clientIp() );
        logs.setService( queryParam.getService() );
        logs.setErrorMessage( queryParam.getErrorMessage() );
        logs.setMetricValue( queryParam.getMetricValue() );
        logs.setAddTime( CommonUtil.getTime() );
        logs.setDay( CommonUtil.getDay() );
        logs.setHour( CommonUtil.getHour() );
        logs.setMinute( CommonUtil.getMinute() );
        boolean res = Logs.insertLogs( logs );
        return  res;
    }

    /**
     * 根据restful post 接口获取参数转化成 roles model 对象
     * @param queryParam
     * @return
     */
    public com.lzz.model.Roles getRolesParam(QueryParam queryParam) {
        com.lzz.model.Roles roles = new com.lzz.model.Roles();
        String clientIp = ClientSign.clientIp();
        String roleName = clientIp + queryParam.getRoleName();
        roles.setClientId( queryParam.getClientId() );
        roles.setRoleName( roleName );
        roles.setMembers( queryParam.getMembers() );
        roles.setMetric( queryParam.getMetric() );
        roles.setService( queryParam.getService() );
        roles.setAddTime( CommonUtil.getTime() );
        return roles;
    }

    public int getTypeTime( String type ){
        int time = CommonUtil.getTime();
        switch (type){
            case "minute":
                time = time - 30*60;
                break;
            case "hour":
                time = time - 24*60*60;
                break;
            case "day":
                time = time - 30*24*60*60;
                break;
        }
        return time;
    }
}
