package com.lzz.control;

import com.lzz.dao.RoleDao;
import com.lzz.logic.LogLogic;
import com.lzz.logic.RoleLogic;
import com.lzz.model.WarnParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/4/2.
 */
@Controller
public class LogController {
    @Resource
    private RoleLogic roleLogic;
    @Resource
    private LogLogic logLogic;

    @RequestMapping("/log_statistic")
    public String list( @RequestParam(value="service", defaultValue="") String service, @RequestParam(value="timeType", defaultValue="minute") String timeType, Model model) {

        List logs = logLogic.getLogs(service, timeType, "service");

        List roles = roleLogic.getRoles( service );
        Map<Integer, List> metricsMap = new HashMap<>();
        for(int i = 0; i < roles.size(); i++){
            LinkedCaseInsensitiveMap tmp = (LinkedCaseInsensitiveMap) roles.get(i);
            Integer roleId = (Integer) tmp.get("id");
            List metrics = logLogic.getMetricGroup(service, timeType, roleId);
            metricsMap.put( roleId, metrics );
        }
        List services = roleLogic.getServices();

        model.addAttribute("service", service );
        model.addAttribute("timeType", timeType );
        model.addAttribute("role_list", roles );
        model.addAttribute("services", services );
        model.addAttribute("logs", logs );
        model.addAttribute("metricsMap", metricsMap );
        return "log_statistic";
    }


    /**
     * 报警接口，用户可以直接 curl 将数据发送过来
     */
    @RequestMapping(value="/mosquito", method = RequestMethod.POST)
    @ResponseBody
    public void add_log(@RequestBody WarnParam queryParam){
        System.out.println(queryParam);
        // 根据 roleID 和传递过来的数据写入到 log
        int roleId = queryParam.getRoleId();
        RoleDao roleDao = new RoleDao();
        Map roleModel = roleDao.getRoleById( roleId );
        String service = (String) roleModel.get("service");
        String members = (String) roleModel.get("members");

        logLogic.addLog( roleId, service, members, queryParam);
        double metric = queryParam.getMetric();
        double metricValue = queryParam.getMetricValue();
        String message = queryParam.getErrorMessage();
        // 判断是否报警
        roleLogic.sendMessage( roleId, metric, metricValue, message,members);
    }

}
