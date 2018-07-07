package com.lzz.util;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lzz on 17/4/9.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WechatTest {
    @Test
    public void getAccessTokenTest(){
        String res = Wechat.getAccessToken();
        System.out.println(res);

    }

    @Test
    public void messageSendTest(){
        boolean res = Wechat.sendTextMessage("linzhouzhi|lzz363216", "报警邮件去看一下啊！！！");
        System.out.println(res);
    }

    @Test
    public void allUserListTest(){
        JSONObject res = Wechat.allUserList();
        System.out.println(res);
    }

    @Test
    public void testGet() throws IOException {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx18a1a4265b89b855&corpsecret=rIBc9ZnLRa40MRsnA6_WHLNgey7wIoUqp_Vq4lmwTrM";
        JSONObject res = HttpClient.httpGet( url );
        System.out.println( res );
    }
}
