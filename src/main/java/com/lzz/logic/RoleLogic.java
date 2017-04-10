package com.lzz.logic;

import com.lzz.dao.Roles;
import com.lzz.dao.SendLogs;
import com.lzz.util.ClientSign;
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
    public boolean addRole(JSONObject reqData){
        Roles roles = new Roles();
        boolean res = roles.insertRole(reqData);
        return res;
    }

    /**
     * 根据 clientid 获取roles
     * @return
     */
    public List getRoles() {
        Roles roles = new Roles();
        String clientid = ClientSign.clientIp();
        List list = roles.selectRole(clientid);
        return list;
    }

    public List getApps(){
        Roles roles = new Roles();
        List list = roles.selectApp();
        return list;
    }

    public List getLogs() {
        SendLogs logs = new SendLogs();
        List list = logs.selectSendLogs();
        return list;
    }
}
