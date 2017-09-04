package huawei.com.util;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：杨松松
 * 日期：2017/7/24 21:31
 * 邮箱：1026334022@qq.com
 */

public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";
    /**
     * 异步 Get方法
     */
    private void okHttp_asynchronousGet(){
        try {
            final String result = null;
            Log.i(TAG,"main thread id is "+Thread.currentThread().getId());
            String url = "https://api.github.com/";
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    // 注：该回调是子线程，非主线程
                    Log.i(TAG,"callback thread id is "+Thread.currentThread().getId());
                    Log.i(TAG,response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String httpURLConnection(String uri){
        String result = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.connect();
            if (connection.getResponseCode()  == HttpURLConnection.HTTP_OK){
                StringBuilder builder = new StringBuilder();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                String s = null;
                while ((s = reader.readLine()) !=null){
                    builder.append(s);
                }
                result = builder.toString();
            }
            if (connection.getInputStream() !=null){
                connection.getInputStream().close();
            }

            if (connection !=null){
                connection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
