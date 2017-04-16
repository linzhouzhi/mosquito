package com.lzz.dao;

import com.lzz.util.ClientSign;
import com.lzz.util.Sqlite;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 17/4/10.
 */
public class Roles {
    /**
     * 添加一条 报警规则
     * @param roles
     * @return
     */
    public int insertRole(com.lzz.model.Roles roles){
        String clientIp = roles.getClientId();
        if( clientIp == "" || clientIp == null ){
            clientIp = ClientSign.clientIp();
        }

        String role_name = roles.getRoleName();
        String service = roles.getService();
        String type = roles.getType();
        String pingUrl = roles.getPingUrl();
        int metric = roles.getMetric();
        String members = roles.getMembers();
        int add_time = (int) new Date().getTime();
        String sql = "insert into roles(role_name, service, type, ping_url, metric, client_id, members, add_time) " +
                "VALUES ('"+ role_name+"','"+ service+"','"+type+"','"+pingUrl+"',"+metric+",'"+clientIp+"','"+members+"'," + add_time + ")";
        int res = Sqlite.getSqlite().insert(sql);
        return res;
    }

    public Map selectRole(String clientId){
        String sql = "select * from roles where client_id='" + clientId + "'";
        Map resMap = Sqlite.getSqlite().selectRow(sql);
        return resMap;
    }

    /**
     * 获取 clientID 最新的roleid
     * @param clientId
     * @return
     */
    public int getLastIdRoles( String clientId ){
        String sql = "select max(id) as id from roles where client_id='" + clientId + "'";
        Map resMap = Sqlite.getSqlite().selectRow(sql);
        int id = (int) resMap.get("id");
        return id;
    }

    /**
     * 选择所有 role
     * @return
     */
    public List selectRoles( String clientId ){
        String sql = "select * from roles where client_id='" + clientId + "'";
        List list = Sqlite.getSqlite().select(sql);
        System.out.println(list);
        return list;
    }

    /**
     *  获取 ping 类型的 roles
     * @return
     */
    public List getPingRoles(){
        String sql = "select * from roles where type='ping'";
        List list = Sqlite.getSqlite().select(sql);
        System.out.println(list);
        return list;
    }

    public int getRoleId( String roleName ){
        String sql = "select id from roles where role_name='" + roleName + "'";
        System.out.println( sql );
        Map map = Sqlite.getSqlite().selectRow( sql );
        if( map == null ){
            return -1;
        }
        int roleId = (int) map.get("id");
        return roleId;
    }

    public List selectServices(){
        String sql = "select service,count(*) as c from roles group by service";
        List list = Sqlite.getSqlite().select(sql);
        return list;
    }

}
