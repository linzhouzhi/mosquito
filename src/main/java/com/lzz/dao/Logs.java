package com.lzz.dao;

import com.lzz.util.CommonUtil;
import com.lzz.util.Sqlite;

import java.util.List;

/**
 * Created by lzz on 17/4/10.
 */
public class Logs {

    public static boolean insertLogs(com.lzz.model.Logs logs){
        int roleId = logs.getRoleid();
        String service = logs.getService();
        String type = logs.getType();
        String pingUrl = logs.getPingUrl();
        int metric_value = logs.getMetricValue();
        String errorMessage = logs.getErrorMessage();
        int errorCode = logs.getErrorCode();
        String members = logs.getMembers();
        int add_time = logs.getAddTime();

        String sql = "insert into logs(roleid, service, type, ping_url, metric_value, error_message, error_code, members, add_time, day, hour, minute) " +
                "VALUES ("+ roleId+",'"+ service+"'," +
                "'"+ type+"','"+pingUrl+"',"+metric_value+"," +
                "'"+errorMessage+"',"+errorCode+"," +
                "'"+members+"'," + add_time + "," +
                CommonUtil.getDay() + "," +
                CommonUtil.getHour() + "," +
                CommonUtil.getMinute() + ")";
        int res = Sqlite.getSqlite().insert(sql);
        if( res == 0 ){
            return false;
        }
        return true;
    }

    public List selectSendLogs(){
        String sql = "select * from logs";
        List list = Sqlite.getSqlite().select(sql);
        System.out.println(list);
        return list;
    }
}
