package com.jk.luckydraw.service.jkjw;

import com.jk.luckydraw.config.ConstantConf;
import com.jk.luckydraw.utils.HttpClientUtil;
import com.jk.luckydraw.utils.Md5Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SendSms implements Runnable{
    private String name;
    private String question;
    private Integer deductScore;
    private Integer remainingScore;
    private String phoneNumber;
    @Override
    public void run() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("accountSid", ConstantConf.ACCOUNTSID);
        params.put("to", phoneNumber);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        params.put("timestamp", timestamp);
        String sig = Md5Util.getMd532(ConstantConf.ACCOUNTSID+ConstantConf.AUTH_TOKEN+timestamp);
        params.put("sig", sig);
        params.put("templateid",ConstantConf.TEMPLATEID);
        String smsContent = name+","+question+","+deductScore+","+remainingScore;
        params.put("param",smsContent);
        String string = HttpClientUtil.post(ConstantConf.SMS_URL, params);
    }

    public SendSms() {
    }

    public SendSms(String name, String question, Integer deductScore, Integer remainingScore, String phoneNumber) {
        this.name = name;
        this.question = question;
        this.deductScore = deductScore;
        this.remainingScore = remainingScore;
        this.phoneNumber = phoneNumber;
    }
}
