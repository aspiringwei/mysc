package no.lwb.base.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static void main(String[] args) {
        getFromUrl("https://www.baidu.com");
    }
    public static int getFromUrl(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
            httpURLConnection.connect();
            return httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
