package com.woniuxy.utils;

import java.util.Random;

public class SaltUtils {
    //通过传入的参数，生成随机的 count 位数的盐值
    public static String getSalt(int count){
        char[] chars = "qwertyuiopasdfghjklzxcvbnmMNBVCXZLKJHGFDSAPOIUYTREWQ0123456789#@!$&".toCharArray();

        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < count;i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
