package com.lzz.control;

import com.lzz.logic.RoleLogic;
import com.lzz.util.ClientSign;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if( role.equals("") && service.equals("")){
            String clientIp = ClientSign.clientIp();
            role = String.valueOf(roleLogic.getLasteRoleId( clientIp));
        }
        List metrics = new ArrayList();
        List logs = new ArrayList<>();
        Map roleDetail = null;
        if( !role.equals("") && service.equals("") ){
            metrics = roleLogic.getMetricGroup(role, timeType, "role");
            logs = roleLogic.getLogs(role, timeType, "role");
            roleDetail = roleLogic.getRoleDetail(Integer.parseInt(role));
        }
        if( role.equals("") && !service.equals("") ){
            metrics = roleLogic.getMetricGroup(service, timeType, "service");
            logs = roleLogic.getLogs(service, timeType, "service");
        }

        List roles = roleLogic.getRoles();
        List services = roleLogic.getServices();

        model.addAttribute("role", role );
        model.addAttribute("service", service );
        model.addAttribute("timeType", timeType );

        model.addAttribute("role_list", roles );
        model.addAttribute("services", services );
        model.addAttribute("logs", logs );
        model.addAttribute("metrics", metrics );
        model.addAttribute("role_detail", roleDetail );
        return "list_role";
    }

    @RequestMapping("/role/delete")
    public String deleteRoles(@RequestParam(value="roleid", defaultValue="-1") int roleid){
        roleLogic.deleteRoleDetail( roleid );
        return "redirect:/role/list";
    }
}