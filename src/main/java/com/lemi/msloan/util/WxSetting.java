package com.lemi.msloan.util;

/**
 * Created by Administrator on 2018/10/15.
 */
public class WxSetting {

    //米书吧
    public final static String WECHAT_APP_ID = "wx3fbf6f30ae7bb620";
    //
    public final static String WECHAT_ACCESS_SECRET = "c19efa7e0f1a2cef0caa1e23dab91589";

    public final static String DO_MAIN = "https://api.msloan.joymetec.com/";

    public final static String H5_MAIN = "https://h5.msloan.joymetec.com/#/";

    public final static String BIZ =  "MzI3MzkzMDA5NA==";

    public final static String GZURL = "https://mp.weixin.qq.com/mp/profile_extaction=home&__biz="+BIZ+"#wechat_redirect";

    //public final static String H5_SHARE = H5_MAIN + "qrcode?accountId=";
    public final static String H5_SHARE = H5_MAIN + "qrcode/";

    //分享url
    public final static String API_REDIRECT_URI = WxSetting.DO_MAIN + "token/redirect";

    public final static String GETSHAREURL= WxSetting.DO_MAIN+"token/shareContent";

//    public final static String TOKEN = "64b600bea4264c8b8863888bb6157737";

    //snsapi_base不弹出授权页面，直接跳转，只能获取用户openid；snsapi_userinfo 弹出授权页面，可通过openid拿到昵称、性别、所在地
//    public final static String SCOPE = "snsapi_userinfo";


}
