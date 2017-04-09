package com.lzz.util;

/**
 * Created by lzz on 17/4/8.
 */
public class ClientSign {
    static final String type = "";

    /**
     * 返回客户端 ip
     * @return
     */
    public String clientIp(){
        return "";
    }

    /**
     * 返回客户端 cookie uuid
     */
    public String clientUuid(){
        return "";
    }

    /**
     * 返回客户端 id
     * @return
     */
    public String clientId(){
        if( type == "IP" ){
            return clientIp();
        }
        return clientUuid();
    }

}
