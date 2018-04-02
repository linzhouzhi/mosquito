package com.lzz.logic;

import com.lzz.dao.LogDao;
import com.lzz.dao.RoleDao;
import com.lzz.model.LogModel;
import com.lzz.model.QueryParam;
import com.lzz.util.ClientSign;
import com.lzz.util.CommonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/4/2.
 */
@Component
public class LogLogic {
    @Resource
    private LogDao logDao;

    public LogLogic(){

    }


    public List getMetricGroup(String target, String timeType,int roleid){
        List list = null;
        int dateTime = getTypeTime( timeType );
        list = logDao.selectByServiceGroupLogs(target, timeType, dateTime,roleid);
        return list;
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


    public boolean addLog(int roleId, QueryParam queryParam){
        RoleDao roleDao = new RoleDao();
        Map roleModel = roleDao.getRoleById( roleId );
        LogModel logs = new LogModel();
        logs.setService((String) roleModel.get("service"));
        logs.setRoleid( roleId );
        logs.setMembers( queryParam.getMembers() );
        logs.setClientId( ClientSign.clientIp() );
        logs.setErrorMessage( queryParam.getErrorMessage() );
        logs.setMetricValue( queryParam.getMetricValue() );
        logs.setAddTime( CommonUtil.getTime() );
        logs.setDay( CommonUtil.getDay() );
        logs.setHour( CommonUtil.getHour() );
        logs.setMinute( CommonUtil.getMinute() );
        boolean res = LogDao.insertLogs( logs );
        return  res;
    }


    public List getLogs(String target, String timeType, String type) {
        List list = null;
        int dateTime = getTypeTime( timeType );
        if( type.equals("role") ){
            list = logDao.selectLogsByRoleid(target, dateTime);
        }else {
            list = logDao.selectLogsByService(target, dateTime);
        }
        return list;
    }
}
