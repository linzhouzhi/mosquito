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
}
