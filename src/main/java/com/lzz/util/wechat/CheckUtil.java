package com.lzz.util.wechat;

import java.util.Arrays;

/**
 *
 * 校验工具类
 * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，
 * 成为开发者成功，否则接入失败。
 *
 * @author GWCheng
 *
 */
public class CheckUtil {
    //配置微信公众号时填写的Token
    private static final String token = "U5mFSrTQ2ebzV7cD";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        // 拼接字符串
        String[] arr = new String[] { token, timestamp, nonce };
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // SHA1加密
        String tmp = DecriptUtil.SHA1(content.toString());
        return tmp.equals(signature);
    }
}