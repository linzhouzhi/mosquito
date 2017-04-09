package com.lzz.logic;

import com.lzz.util.Wechat;
import net.sf.json.JSONObject;

/**
 * Created by lzz on 17/4/9.
 */
public class RoleLogic {
    public JSONObject getUserList(){
        JSONObject res = Wechat.allUserList();
        return res;
    }
}
