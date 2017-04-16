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
        List apps = roleLogic.getServices();
        System.out.println(apps);
        model.addAttribute("apps", apps );
        return "add_role";
    }

    @RequestMapping("/role/list")
    public String list(@RequestParam(value="role", defaultValue="") String role, @RequestParam(value="service", defaultValue="") String service, @RequestParam(value="timeType", defaultValue="minute") String timeType,Model model) {
        List roles = roleLogic.getRoles();
        List services = roleLogic.getServices();
        List logs = roleLogic.getLogs();
        HashMap<String, Integer> serviceGroup = new HashMap<String, Integer>();
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
            if( serviceGroup.containsKey(key) ){
                int value = serviceGroup.get(key);
                serviceGroup.put(key2, ++value);
            }else{
                serviceGroup.put(key2, 1);
            }
        }
        model.addAttribute("role", role );
        model.addAttribute("service", service );
        model.addAttribute("timeType", timeType );
        model.addAttribute("role_group", roleGroup );
        model.addAttribute("service_group", serviceGroup );
        model.addAttribute("role_list", roles );
        model.addAttribute("services", services );
        model.addAttribute("logs", logs );
        return "list_role";
    }
}