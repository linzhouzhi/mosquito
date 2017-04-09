package com.lzz.control;

import com.lzz.logic.RoleLogic;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "add_role";
    }

    @RequestMapping("/role/list")
    public String list(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name + message );
        return "list_role";
    }

}