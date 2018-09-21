package no.lwb.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static void main(String[] args) throws MalformedURLException {
        //
        String url_ = "https://restapi.amap.com/v3/ip?output=xml&key=a32df706511ae047c17030d88276a1ff&ip=114.247.50.2";
        URL url = new URL(url_);
        System.out.println(url.getQuery()); // ? 后面条件
        getFromUrl(url_);
    }

    public static void getFromUrl(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setRequestProperty();
            httpURLConnection.connect();
            int code = httpURLConnection.getResponseCode();
            InputStream inputStream = httpURLConnection.getErrorStream();
            byte[] bytes = new byte[1024];
            int count = 0;
            if (inputStream == null) {
                inputStream = httpURLConnection.getInputStream();
            }
            StringBuilder stringBuilder = new StringBuilder();
            while ((count = inputStream.read(bytes)) > 0) {
                String value = new String(bytes, 0, count);
                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
