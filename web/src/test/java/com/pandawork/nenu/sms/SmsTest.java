package com.pandawork.nenu.sms;

import com.pandawork.nenu.oa.service.sms.SmsService;
import com.pandawork.nenu.oa.service.sms.impl.SmsServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by Taoyongpan on 2017/5/15.
 */
public class SmsTest {

    @Autowired
    SmsService smsService;

    @Test
    public void sendMessage() throws IOException {

        SmsServiceImpl SmsServiceImpl = new SmsServiceImpl();

        //修改为您的apikey
        String apikey = "a1430ce3a0827c46c6468b9104a18549";
        //修改为您要发送的手机号
        String mobile = "17684373801";

        /**************** 使用通用接口发短信 *****************/
        //设置您要发送的内容
        String text = "您的验证码是1234【pandawork】";
        //发短信调用示例
        System.out.println(SmsServiceImpl.sendSms( text, mobile));
    }
}
