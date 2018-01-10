package com.pbtd.tv.launcher.view.fragment;

import android.support.v4.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.pbtd.tv.launcher.MyApplication;
import com.pbtd.tv.launcher.volley.VolleyController;

import java.util.Map;

/**
 * Created by SS on 2017/6/15.
 */

public abstract class BaseFragment extends Fragment {
//    //是否可见
//    protected boolean isVisable;
//    // 标志位，标志Fragment已经初始化完成。
//    public boolean isPrepared = false;
//
//    /**
//     * 实现Fragment数据的缓加载
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//            isVisable = true;
//            onVisible();
//        } else {
//            isVisable = false;
//            onInVisible();
//        }
//    }
//    protected void onInVisible() {
//
//    }
//    protected void onVisible() {
//        //加载数据
//        loadData();
//    }
//    protected abstract void loadData();
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
        VolleyController volleyController = new VolleyController(getContext(),callback);
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
        VolleyController volleyController = new VolleyController(getActivity(), callback);
        volleyController.requestPostAction(url, bodyMap, hearderMap);

    }


}
