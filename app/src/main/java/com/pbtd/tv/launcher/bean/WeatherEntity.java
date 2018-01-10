package com.pbtd.tv.launcher.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhouyong on 16/5/23.
 */
public class WeatherEntity extends BaseEntity{
    public String cityname;
    public String max;
    public String min;
    public String pyName;
    public String stateDetailed;

    public void parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject weatherJson = jsonObject.getJSONObject("weather");
            cityname = weatherJson.optString("cityname");
            max = weatherJson.optString("max");
            min = weatherJson.optString("min");
            pyName = weatherJson.optString("pyName");
            stateDetailed = weatherJson.optString("stateDetailed");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
