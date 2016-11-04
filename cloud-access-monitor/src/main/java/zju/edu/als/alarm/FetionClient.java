package zju.edu.als.alarm;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzq on 2016/11/3.
 */
public class FetionClient {
    private static final Logger logger = LoggerFactory.getLogger(FetionClient.class);
    private static PoolingHttpClientConnectionManager cm ;
    private static RequestConfig requestConfig;
    private static CloseableHttpClient httpClient;
    private static final String url="http://w.ibtf.net/f.php";
    static {
        cm= new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(10);
        requestConfig= RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        httpClient= HttpClientBuilder.create()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
    public static void sendMessage(String phone,String pwd,String to,String msg){
        List<NameValuePair> nameValuePairList= new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("phone", phone));
        nameValuePairList.add(new BasicNameValuePair("pwd", pwd));
        nameValuePairList.add(new BasicNameValuePair("to", to));
        nameValuePairList.add(new BasicNameValuePair("msg", msg));
        nameValuePairList.add(new BasicNameValuePair("type", "0"));
        try {
            HttpPost request = new HttpPost(url);
            request.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            CloseableHttpResponse response  = httpClient.execute(request);
            try {
                response.close();
            }catch (IOException e){
                logger.error("Invoke sendMessage Response Close Exception ",e);
            }
        } catch (Exception e) {
           logger.error("Invoke sendMessage httpClient execute Exception",e);
        }
    }
}
