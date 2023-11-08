package cn.changge.base.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class SendCodeUtils {
//    public static void main(String[] args) {
//        sendCode("15520855676","9527");
//    }

    public static boolean sendCode(String phone,String code){

        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("https://utf8api.smschinese.cn/");
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            NameValuePair[] data ={
                    new NameValuePair("Uid", "3333333qqqq"),
                    new NameValuePair("Key", "2D3363CDB0CABCDBD7CFC97613452FDF"),
                    new NameValuePair("smsMob",phone),
                    new NameValuePair("smsText","您的验证码为:"+code+",请在5分钟使用")};
            post.setRequestBody(data);

            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:"+statusCode); //HTTP状态码
            for(Header h : headers){
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println("发送结果:"+result);  //打印返回消息状态
            post.releaseConnection();
            if (Integer.valueOf(result)>0)return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
