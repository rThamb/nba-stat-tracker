package util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyHttpClient {

    public String get(String url){
        HttpGet request = new HttpGet(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            String result = "";

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
