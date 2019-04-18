package com.lemi.msloan.util;

/**
 * Created by WeiJinTechnology on 2017/10/31.
 */

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.*;


public class WeixinUtils {
    private static final String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID1&secret=SECRET1&code=CODE1&grant_type=authorization_code";
    private static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID1&secret=SECRET1";
    private static final String ticketUrl = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN1";
//    public static final String appId = WxSetting.appId;
    public static final String appSecret = WxSetting.WECHAT_ACCESS_SECRET;

    public static String getOpenId(HttpServletRequest request) throws Exception {
        String openId = null;
        String code = request.getParameter("code");
        if (StringUtils.isNotEmpty(code)) {
            String url = openIdUrl.replace("APPID1", WxSetting.WECHAT_APP_ID)
                    .replace("SECRET1", appSecret).replace("CODE1", code);
            String result = getURLContent(url);
            //System.out.println(result);
            openId = result.split("openid")[1].split("scope")[0]
                    .replaceAll("\"", "").replaceAll(",", "")
                    .replaceAll(" ", "").replaceAll(":", "");
           // System.out.println("openId" + openId);
        }
        return openId;
    }

    public static Map<String, String> setShareConfig(
            HttpServletRequest request, String url) throws Exception {
        ServletContext context = request.getSession().getServletContext();
        String accessToken = (String) context.getAttribute("accessToken_jssdk");
        if (StringUtils.isBlank(accessToken)) {
            accessToken = getAccessToken();
            context.setAttribute("accessToken_jssdk", accessToken);
        }
        String jsapiTicket = (String) context.getAttribute("jsapiTicket");
        if (StringUtils.isEmpty(jsapiTicket)) {
            jsapiTicket = getTicket(accessToken);
            context.setAttribute("jsapiTicket", jsapiTicket);
        }
        Map<String, String> ret = sign(jsapiTicket, url);
        request.setAttribute("url", ret.get("url"));
        request.setAttribute("jsapi_ticket", ret.get("jsapi_ticket"));
        request.setAttribute("nonceStr", ret.get("nonceStr"));
        request.setAttribute("timestamp", ret.get("timestamp"));
        request.setAttribute("signature", ret.get("signature"));
        request.setAttribute("appId", WxSetting.WECHAT_APP_ID);
        ret.put("appId",WxSetting.WECHAT_APP_ID);
        return ret;
    };

    public static String getTicket(String accessToken) throws Exception {
        String ticket = null;
        //if (accessToken!=null&&!accessToken.trim().equals("")) {
        if (!StringUtils.isBlank(accessToken)) {
        String url = ticketUrl.replace("ACCESS_TOKEN1", accessToken);
            String result = getURLContent(url);
            //System.out.println(result);
            ticket = result.split("ticket")[1].split("expires_in")[0]
                    .replaceAll("\"", "").replaceAll(",", "").replaceAll(" ", "")
                    .replaceAll(":", "");
            //System.out.println("ticket:" + ticket);
        }
        return ticket;
    }

    public static String getAccessToken() throws Exception {
        String accessToken = null;
        String url = accessTokenUrl.replace("APPID1", WxSetting.WECHAT_APP_ID).replace("SECRET1",
                appSecret);
        String result = getURLContent(url);
        //System.out.println(result);
        if(result.indexOf("errcode")==-1) {
            accessToken = result.split("access_token")[1].split("expires_in")[0]
                    .replaceAll("\"", "").replaceAll(",", "").replaceAll(" ", "")
                    .replaceAll(":", "");
            //System.out.println("accessToken:" + accessToken);
        }
        return accessToken;
    }

    public static String getURLContent(String url)
            throws MalformedURLException, IOException {
        StringBuffer result = new StringBuffer();
        BufferedReader in = null;
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 定义 BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            //result += line;
            result = result.append(line);
        }
        // 使用finally块来关闭输入流
        if (in != null) {
            in.close();
        }
        return result.toString();
    }

    private static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = UUID.randomUUID().toString().replaceAll("-", "")
                .substring(0, 16).toLowerCase();//        String nonce_str = "a6ab46c4c4494198";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String string1;
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        //System.out.print(signature);
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}

