package com.lzz.util;

import org.junit.Test;

/**
 * Created by lzz on 17/4/10.
 */
public class ClientSignTest {
    @Test
    public void clientIpTest(){
        String ip = ClientSign.clientIp();
        System.out.println(ip);
    }
}
