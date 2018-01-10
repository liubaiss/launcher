package com.pbtd.tv.launcher.bean;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zhouyong on 15/12/25.
 */
public class VerEntity {
    public int navVer;
    public List<PageEntity> pageEntityList;
    class  PageEntity{
        public int navId;
        public int pageVer;
    }

    public void parseEntity(Context context, String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            navVer = jsonObject.optInt("verNum");

            JSONArray jsonArray = jsonObject.getJSONArray("result");
            pageEntityList = new ArrayList<PageEntity>();
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                PageEntity pageEntity = new PageEntity();
                pageEntity.navId = jsonObject2.optInt("navId");
                pageEntity.pageVer = jsonObject2.optInt("verNum");
                pageEntityList.add(pageEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
