package com.qsxuan.recentcontests.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Http 工具类
 *
 * @author qinshaoxuan
 */

public class HttpClientUtil {
    private static HttpClient client = new DefaultHttpClient();

    public static String get(String api) {
        String result = "";
        HttpGet httpGet = new HttpGet(api);
        try {
            HttpResponse response = client.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if (200 == code) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            } else {
                result = String.valueOf(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return result;
    }
}
