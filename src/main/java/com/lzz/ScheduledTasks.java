package com.lzz;

import com.lzz.dao.Roles;
import com.lzz.dao.SendLogs;
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

    @Scheduled(fixedRate = 600000)
    public void reportPingStatus() {
        Roles roles = new Roles();
        List<LinkedCaseInsensitiveMap> role_list = roles.selectRole();
        for( int i = 0; i < role_list.size(); i++ ){
            System.out.println(role_list.get(i));
            LinkedCaseInsensitiveMap role = (LinkedCaseInsensitiveMap)role_list.get(i);
            String url = (String) role.get("ping_url");
            String members = (String) role.get("members");
            JSONObject res = HttpClient.urlPing( url );
            System.out.println(res);
            System.out.println("----------");
            if( res.getInt("errorCode") != 0 ){
                JSONObject log = new JSONObject();
                log.put("roleid", role.get("id"));
                log.put("type", role.get("type"));
                log.put("service", role.get("service"));
                log.put("ping_url", role.get("ping_url"));
                log.put("ping_timeout", role.get("ping_timeout"));
                log.put("members", role.get("members"));
                log.put("error_code", res.get("errorCode"));
                log.put("error_message", res.get("errorMessage"));
                SendLogs sendLogs = new SendLogs();
                sendLogs.insertSendLogs(log);
                Wechat.sendTextMessage(members, url + "请求失败！！" + res.getString("errorMessage"));
            }
        }
    }
}