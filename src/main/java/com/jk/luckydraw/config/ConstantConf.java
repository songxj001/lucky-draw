package com.jk.luckydraw.config;

public class ConstantConf {


    public static final String ACCOUNTSID = "0374867b2c1844dbbe0bf019bf0def28";

    public static final String AUTH_TOKEN = "d05d06f418974fc6aceb9233e38b7539";

    public static final String TEMPLATEID = "1466447914";

    public static final String SMS_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

    public static final String SALT = "ed4ffcd453efab32";

    /**
     * token
     */
    public static final int RESCODE_REFTOKEN_MSG = 1006;		//刷新TOKEN(有返回数据)
    public static final int RESCODE_REFTOKEN = 1007;			//刷新TOKEN

    public static final int JWT_ERRCODE_NULL = 4000;			//Token不存在
    public static final int JWT_ERRCODE_EXPIRE = 4001;			//Token过期
    public static final int JWT_ERRCODE_FAIL = 4002;			//验证不通过

    /**
     * JWT
     */
    public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";			//密匙
    public static final long JWT_TTL = 24*60*60*1000;									//token有效时间

    /**
     * 微信小程序信息
     */
    public static final String APP_ID = "wx955d1f23fbc3e6c7";
    public static final String APP_SECRET = "8f706145c4c4c65e0d600b6e1cb63d4d";

    public static final String WX_QUERY_OPENID = "https://api.weixin.qq.com/sns/jscode2session";


}
