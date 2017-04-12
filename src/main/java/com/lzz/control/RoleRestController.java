package com.lzz.control;

import com.lzz.logic.RoleLogic;
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
    public JSONObject role_submit_ajax(@RequestBody String requestBody){
        System.out.println(requestBody);
        JSONObject jsonObject = JSONObject.fromObject(requestBody);
        roleLogic.addRole(jsonObject);
        return new JSONObject();
    }

    /**
     * 报警接口，用户可以直接 curl 将数据发送过来
     */
    @RequestMapping(value="role/add_log", method = RequestMethod.POST)
    public JSONObject add_log(@RequestBody String requestBody){
        // 根据目的主机 ip 生成 service name 写入到 roles 并设置 status -1 (表示不走定时任务),获取 roleid
        // 根据 roleID 和传递过来的数据写入到 log
        // 判断是否报警
        return new JSONObject();
    }
}
