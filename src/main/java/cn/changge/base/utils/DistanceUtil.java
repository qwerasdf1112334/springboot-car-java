package cn.changge.base.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 位置相关工具类
 */
public class DistanceUtil {
    
    public static Point getPoint(String address) {
        String Application_ID = "t63xA9uD2SQ4u4Vi70qncQF72zX0mET9";//配置上自己的百度地图应用的AK
        try {
            String sCurrentLine;
            String sTotalString;
            sCurrentLine = "";
            sTotalString = "";
            InputStream l_urlStream;
//          URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+address.replaceAll(" ","")+"&output=json&ak="+Application_ID+"&callback=showLocation");
            URL l_url = new URL("http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + Application_ID + "&callback=showLocation");
            HttpURLConnection l_connection = (HttpURLConnection) l_url.openConnection();
            l_connection.connect();
            l_urlStream = l_connection.getInputStream();
            java.io.BufferedReader l_reader = new java.io.BufferedReader(new InputStreamReader(l_urlStream));
            String str = l_reader.readLine();
            System.out.println(str);
            //用经度分割返回的网页代码  
            String s = "," + "\"" + "lat" + "\"" + ":";
            String strs[] = str.split(s, 2);
            String s1 = "\"" + "lng" + "\"" + ":";
            String a[] = strs[0].split(s1, 2);
            s1 = "}" + "," + "\"";
            String a1[] = strs[1].split(s1, 2);

            Point point = new Point();
            point.setLng(Double.valueOf(a[1]));
            point.setLat(Double.valueOf(a1[0]));
            return point;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}