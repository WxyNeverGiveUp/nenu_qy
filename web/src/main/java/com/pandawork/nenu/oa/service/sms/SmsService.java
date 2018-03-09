package com.pandawork.nenu.oa.service.sms;

import java.io.IOException;

/**
 * Created by Taoyongpan on 2017/5/15.
 */
public interface SmsService {
    /**
     * 发短信
     * @param text
     * @param mobile
     * @return
     * @throws IOException
     */
    public String sendSms( String text, String mobile) throws IOException;
}
