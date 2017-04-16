package com.lzz.control;

import com.lzz.logic.RoleLogic;
import com.lzz.model.QueryParam;
import com.lzz.model.Roles;
import com.lzz.util.Wechat;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzz on 17/4/10.
 */
@RestController
public class RoleRestController {
    RoleLogic roleLogic = new RoleLogic();
    /**
     * 提交 role
     * @param requestBody
     * @return
     */
    @RequestMapping(value="role/role_submit_ajax", method = RequestMethod.POST)
    public JSONObject role_submit_ajax(@RequestBody Roles requestBody){
        System.out.println(requestBody);
        roleLogic.addRole(requestBody);
        return new JSONObject();
    }

    /**
     * 报警接口，用户可以直接 curl 将数据发送过来
     */
    @RequestMapping(value="role/add_log", method = RequestMethod.POST)
    public void add_log(@RequestBody QueryParam queryParam){
        System.out.println(queryParam);
        // 根据目的主机 ip 生成 service name 写入到 roles
        Roles roles = roleLogic.getRolesParam( queryParam );
        int roleId = roleLogic.addRestRole(roles);
        System.out.println( "roleidddd-------" + roleId);
        // 根据 roleID 和传递过来的数据写入到 log
        roleLogic.addLog( roleId, queryParam);
        // 判断是否报警
        int metric = queryParam.getMetric();
        int metricValue = queryParam.getMetricValue();
        if( metric > metricValue ){
            String members = queryParam.getMembers();
            Wechat.sendTextMessage(members, "metric" + metricValue);
        }
    }
}
