package com.lzz;

import com.lzz.dao.Logs;
import com.lzz.dao.Roles;
import com.lzz.util.CommonUtil;
import com.lzz.util.HttpClient;
import com.lzz.util.Wechat;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportPingStatus() {
        Roles roles = new Roles();
        List<LinkedCaseInsensitiveMap> role_list = roles.getPingRoles();
        for( int i = 0; i < role_list.size(); i++ ){
            System.out.println(role_list.get(i));
            LinkedCaseInsensitiveMap role = (LinkedCaseInsensitiveMap)role_list.get(i);
            String url = (String) role.get("ping_url");
            String members = (String) role.get("members");
            JSONObject res = HttpClient.urlPing( url );
            System.out.println(res);
            System.out.println("----------");
            if( res.getInt("errorCode") != 0 ){
                com.lzz.model.Logs logs = new com.lzz.model.Logs();
                logs.setRoleid((Integer) role.get("id"));
                logs.setType((String) role.get("type"));
                logs.setService((String) role.get("service"));
                logs.setPingUrl((String) role.get("ping_url"));
                logs.setMetric((Integer) role.get("metric"));
                logs.setMembers((String) role.get("members"));
                logs.setClientId((String) role.get("client_id"));
                logs.setErrorMessage((String) role.get(res.get("errorMessage")));
                logs.setAddTime(CommonUtil.getTime());
                Logs sendLogs = new Logs();
                sendLogs.insertLogs(logs);
                Wechat.sendTextMessage(members, url + "请求失败！！" + res.getString("errorMessage"));
            }
        }
    }
}