package com.lzz.control;

import com.lzz.logic.RoleLogic;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class RoleController {
    RoleLogic roleLogic = new RoleLogic();

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/role/add")
    public String add( Model model) {
        JSONObject userList = roleLogic.getUserList();
        System.out.println(userList);
        model.addAttribute("userLsit", userList );
        List apps = roleLogic.getApps();
        System.out.println(apps);
        model.addAttribute("apps", apps );
        return "add_role";
    }

    @RequestMapping("/role/list")
    public String list(@RequestParam(value="role", required=false, defaultValue="") String role, @RequestParam(value="app", required=false, defaultValue="") String app,Model model) {
        List roles = roleLogic.getRoles();
        List apps = roleLogic.getApps();
        List logs = roleLogic.getLogs();
        HashMap<String, Integer> appGroup = new HashMap<String, Integer>();
        HashMap<Integer, Integer> roleGroup = new HashMap<Integer, Integer>();
        for( int i = 0; i < logs.size(); i++ ){
            Integer key = (Integer) ((LinkedCaseInsensitiveMap)logs.get(i)).get("roleid");
            if( roleGroup.containsKey(key) ){
                int value = roleGroup.get(key);
                roleGroup.put(key, ++value);
            }else{
                roleGroup.put(key, 1);
            }

            String key2 = (String) ((LinkedCaseInsensitiveMap)logs.get(i)).get("service");
            if( appGroup.containsKey(key) ){
                int value = appGroup.get(key);
                appGroup.put(key2, ++value);
            }else{
                appGroup.put(key2, 1);
            }
        }
        model.addAttribute("role", role );
        model.addAttribute("app", app );
        model.addAttribute("role_group", roleGroup );
        model.addAttribute("app_group", appGroup );
        model.addAttribute("role_list", roles );
        model.addAttribute("apps", apps );
        model.addAttribute("logs", logs );
        return "list_role";
    }
}