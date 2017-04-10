package com.lzz.dao;

import com.lzz.util.Sqlite;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by lzz on 17/4/10.
 */
public class SendLogs {

    public boolean insertSendLogs(JSONObject reqData){
        int roleId = reqData.getInt("roleid");
        String service = reqData.getString("service");
        String type = reqData.getString("type");
        String pingUrl = reqData.getString("ping_url");
        int pingTimeout = reqData.getInt("ping_timeout");
        String errorMessage = reqData.getString("error_message");
        int errorCode = reqData.getInt("error_code");
        String members = reqData.getString("members");
        int add_time = (int) new Date().getTime();

        String sql = "insert into send_logs(roleid, service, type, ping_url, ping_timeout, error_message, error_code, members, add_time) " +
                "VALUES ("+ roleId+",'"+ service+"','"+ type+"','"+pingUrl+"',"+pingTimeout+",'"+errorMessage+"',"+errorCode+",'"+members+"'," + add_time + ")";
        int res = Sqlite.getSqlite().insert(sql);
        if( res == 0 ){
            return false;
        }
        return true;
    }

    public List selectSendLogs(){
        String sql = "select * from send_logs";
        List list = Sqlite.getSqlite().select(sql);
        System.out.println(list);
        return list;
    }
}