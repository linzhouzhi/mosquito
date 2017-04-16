package com.lzz.logic;

import com.lzz.dao.Roles;
import com.lzz.dao.Logs;
import com.lzz.model.QueryParam;
import com.lzz.util.ClientSign;
import com.lzz.util.CommonUtil;
import com.lzz.util.Wechat;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by lzz on 17/4/9.
 */
public class RoleLogic {
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
        int res = roles.insertRole(reqData);
        if( res != 0 ){
            return true;
        }
        return false;
    }

    public int addRestRole(com.lzz.model.Roles rolesData){
        Roles roles = new Roles();
        String clientIp = ClientSign.clientIp();
        if( clientIp.equals("0:0:0:0:0:0:0:1") ){
            clientIp = "127.0.0.1";
        }
        String roleName = clientIp + "_" + rolesData.getRoleName();
        System.out.println(roleName + "------------rolename");
        int roleId = roles.getRoleId( roleName );
        if( roleId != -1 ){
            return roleId;
        }
        rolesData.setClientId( clientIp );
        rolesData.setRoleName( roleName );
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
        if( clientid.equals("0:0:0:0:0:0:0:1") ){
            clientid = "127.0.0.1";
        }
        List list = roles.selectRoles(clientid);
        return list;
    }

    public List getServices(){
        Roles roles = new Roles();
        List list = roles.selectApp();
        return list;
    }

    public List getLogs() {
        Logs logs = new Logs();
        List list = logs.selectSendLogs();
        return list;
    }

    public boolean addLog(int roleId, QueryParam queryParam){
        com.lzz.model.Logs logs = new com.lzz.model.Logs();
        logs.setRoleid( roleId );
        logs.setMembers( queryParam.getMembers() );
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
        String clientId = clientIp + queryParam.getClientId();
        roles.setClientId( clientId );
        roles.setRoleName( queryParam.getRoleName() );
        roles.setMembers( queryParam.getMembers() );
        roles.setMetric( queryParam.getMetric() );
        roles.setService( queryParam.getService() );
        roles.setAddTime( CommonUtil.getTime() );
        return roles;
    }
}
