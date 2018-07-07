package com.lzz;

import com.lzz.util.Wechat;

/**
 * Created by gl49 on 2018/7/7.
 */
public class Test {
    public static void main(String[] args){
        boolean res = Wechat.sendTextMessage("linzhouzhi|lzz363216|leo|jack.ni", "报警邮件去看一下啊！！！");
        System.out.println(res);
    }
}
