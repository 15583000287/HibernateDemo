package com.example.jpademo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

public class SecureTools {
    public static String pwdLock(String pwd){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(pwd.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
//            throw new SpeedException("MD5加密出现错误");
        }
        return null;
    }
    public static String pwdLock(String pwd,String solt){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            String str = pwd;
            Optional<String> slotOpt =Optional.ofNullable(solt);
            if(slotOpt.isPresent()){
                str += slotOpt.get();
            }
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
//            throw new SpeedException("MD5加密出现错误");
        }
        return null;
    }
}
