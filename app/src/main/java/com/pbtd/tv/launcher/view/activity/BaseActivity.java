package com.pbtd.tv.launcher.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pbtd.tv.launcher.utils.ResolutionUtil;
import com.pbtd.tv.launcher.volley.VolleyController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**所有activity的基类
 * Created by SS on 2017/6/13.
 */

public class BaseActivity extends AppCompatActivity {


    /**
     * 默认传输字符集
     */
    protected static final String CHAR_SET = "utf-8";
    protected Gson gson;
    protected ResolutionUtil resolutionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        gson = new Gson();
        resolutionUtil = new ResolutionUtil(this);
    }

    /**
     * 格式化url
     * @param url
     * @param params
     * @return
     */
    protected static String urlFormat(String url, Object... params){
        if (params==null) return url;

        for (int i=0; i<params.length;i++)
            params[i] = urlEncode((String)params[i]);

        return String.format(url, params);
    }

    private static String urlEncode(String param){
        if (TextUtils.isEmpty(param)) return "";
        try {
            String result =  URLEncoder.encode(param,CHAR_SET);
            return result;
        } catch (UnsupportedEncodingException e) {
            Log.i("msg", e.getMessage());
        }
        return "";
    }

    /**
     * volley出现Error
     * @param error
     */
    protected String getVolleyErrorTitle(VolleyError error){
        if(error instanceof TimeoutError)
            return "连接超时";

        if((error instanceof ServerError) || (error instanceof AuthFailureError))
            return getServiceError(error);

        if((error instanceof NetworkError) || (error instanceof NoConnectionError))
            return "连接访问异常";


        return "获取数据异常，请稍后再试!";
    }

    private String getServiceError(VolleyError error){

        NetworkResponse response = error.networkResponse;

        if(response != null){
            return "httpStateCode : " + response.statusCode;
        }

        return "连接出错";

    }


    /**
     * 进行get请求
     * @param url
     * @param callback
     */
    protected void requestGetUrl(String url,VolleyController.VolleyCallback callback){
        VolleyController volleyController = new VolleyController(this, callback);
        volleyController.requestGetAction(url);
    }

    /**
     * 进行post请求
     * @param url
     * @param bodyMap
     * @param hearderMap
     * @param callback
     */
    protected void requestPostUrl(String url, Map<String, String> bodyMap, Map<String, String> hearderMap, VolleyController.VolleyCallback callback){
        VolleyController volleyController = new VolleyController(this, callback);
        volleyController.requestPostAction(url, bodyMap, hearderMap);

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
