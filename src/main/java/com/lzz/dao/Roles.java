package com.lzz.dao;

import com.lzz.util.ClientSign;
import com.lzz.util.Sqlite;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by lzz on 17/4/10.
 */
public class Roles {
    /**
     * 添加一条 报警规则
     * @param reqData
     * @return
     */
    public boolean insertRole(JSONObject reqData){
        String clientIp = ClientSign.clientIp();
        String role_name = reqData.getString("role_name");
        String service = reqData.getString("service");
        String type = reqData.getString("type");
        String pingUrl = reqData.getString("ping_url");
        int pingTimeout = reqData.getInt("ping_timeout");
        String members = reqData.getString("members");
        int add_time = (int) new Date().getTime();
        String sql = "insert into roles(service, role_name, type, ping_url, ping_timeout, client_id, members, add_time) " +
                "VALUES ('"+ role_name+"','"+ service+"','"+type+"','"+pingUrl+"',"+pingTimeout+",'"+clientIp+"','"+members+"'," + add_time + ")";
        int res = Sqlite.getSqlite().insert(sql);
        if( res == 0 ){
            return false;
        }
        return true;
    }

    public List selectRole(String clientid){
        String sql = "select * from roles where client_id='" + clientid + "'";
        List list = Sqlite.getSqlite().select(sql);
        return list;
    }

    /**
     * 选择所有 role
     * @return
     */
    public List selectRole(){
        String sql = "select * from roles";
        List list = Sqlite.getSqlite().select(sql);
        System.out.println(list);
        return list;
    }

    public List selectApp(){
        String sql = "select service,count(*) as c from roles group by service";
        List list = Sqlite.getSqlite().select(sql);
        return list;
    }

}
