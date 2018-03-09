package com.pandawork.nenu.oa.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * user: mayuan
 * date: 2016/4/25
 * time: 18:05
 **/
public class TokenUtil {
    public static String getMd5Token(){
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String Stoday = sdf.format(today);
        //获取yy1,yy2,mm,dd
        Integer yy1 = Integer.parseInt(Stoday.substring(0,2));
        Integer yy2 = Integer.parseInt(Stoday.substring(2,4));
        Integer mm = Integer.parseInt(Stoday.substring(4,6));
        Integer dd = Integer.parseInt(Stoday.substring(6));
        //对日期MD5加密
        Stoday = CommonUtil.md5(Stoday);
        StringBuffer token = new StringBuffer();
        //获取第20，13，09，03位
        token.append(Stoday.charAt(19))
                .append(Stoday.charAt(12))
                .append(Stoday.charAt(8))
                .append(Stoday.charAt(2));
        //获取第yy,yy,mm,dd位
        token.append(Stoday.charAt(yy1-1))
                .append(Stoday.charAt(yy2-1))
                .append(Stoday.charAt(mm-1))
                .append(Stoday.charAt(dd-1));
        String tokens = token.toString().toUpperCase();
        return tokens;
    }
}
