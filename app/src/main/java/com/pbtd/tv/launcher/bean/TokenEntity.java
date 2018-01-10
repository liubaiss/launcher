package com.pbtd.tv.launcher.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenEntity extends BaseEntity {

	public int state;
	public String buss_id;
	public String bgimage_url;
    public String buss_name;
    public String logo_url;

    public void parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            state = jsonObject.optInt("state");
            buss_id = jsonObject.optString("buss_id");
            bgimage_url = jsonObject.optString("bgimage_url");
            buss_name = jsonObject.optString("buss_name");
            logo_url = jsonObject.optString("logourl");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
