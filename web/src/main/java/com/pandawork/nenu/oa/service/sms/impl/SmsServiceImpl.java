package com.pandawork.nenu.oa.service.sms.impl;

import com.pandawork.nenu.oa.common.util.SmsClientSend;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.service.sms.SmsService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Taoyongpan on 2017/5/15.
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    /**
     * 发短信
     * @param text
     * @param mobile
     * @return
     * @throws IOException
     */


    /**
     * ！！！！！！！！改短信功能的时候把String reslut = SmsClientSend.sendSms(WebConstants.url , WebConstants.userid , WebConstants.account , WebConstants.password , mobile , text);打开，把String reslut = null;注掉   等功能改好之后记得换回来！！！！保持现在的样子。这个不要提交更改后的。
     */

    public  String sendSms(String text, String mobile) throws IOException {
        //String reslut = SmsClientSend.sendSms(WebConstants.url , WebConstants.userid , WebConstants.account , WebConstants.password , mobile , text);
        String reslut = null;

        return reslut;
    }

}
