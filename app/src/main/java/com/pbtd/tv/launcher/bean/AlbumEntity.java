package com.pbtd.tv.launcher.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by zhouyong on 16/1/18.
 */
public class AlbumEntity extends BaseEntity {
    public static int TOTAL; //一共多少条
    public List<String> director;  //导演
    public List<String> actors;  //演员
    public String albumDesc;  //专辑描述
    public String albumId;  //专辑ID
    public String albumName; // 专辑名称
    public String albumNameEn; // 专辑英文明朝
    public String chnId;  // 频道ID
    public String chnName;  // 频道名称
    public List<CpEntity> cpEntities;
    public String duration; //播放时长
    public String focus; //看点
    public String picUrl; //图片
    public String showDate; //上映日期
    public LinkedHashMap<String,String> leafTags;  //地区、类型等标签
    public int isSeries; //是否是多级  0：单集，1:多级
    public int sets; //
    public String playUrl;  //播放地址
    public int totalEpisodes;


    private static class CpEntity implements Serializable {
        public static final long serialVersionUID = 1L;
        public String cpId; //内容提供方ID
        public String sourceChnId;  //片源频道ID
        public String sourceId;  //片源ID
        public String sourceName; //片源名称(银河、爱奇艺、腾讯、搜狐)
        public String sourceNameShort; //片源简称
    }

    public static List<AlbumEntity> parse(String json) throws JSONException {
        List<AlbumEntity> albumEntities = new ArrayList<AlbumEntity>();
        JSONObject jsonObject = new JSONObject(json);
        TOTAL = jsonObject.optInt("total");
        JSONArray jsonArray = jsonObject.optJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i ++){
            AlbumEntity albumEntity = new AlbumEntity();
            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
            JSONArray jsonArray2 = jsonObject2.optJSONArray("actors");
            JSONArray jsonArray3 = jsonObject2.optJSONArray("cpList");
            JSONArray jsonArray4 = jsonObject2.optJSONArray("leafTags");
            parseLeafTags(albumEntity,jsonArray4);
            parseCpList(albumEntity,jsonArray3);
            parseActors(albumEntity, jsonArray2);
            parseAlbum(albumEntity, jsonObject2);
            albumEntities.add(albumEntity);

        }



        return albumEntities;
    }

    private static void parseLeafTags(AlbumEntity albumEntity, JSONArray jsonArray4) throws JSONException {
        LinkedHashMap<String,String> leafTags = new LinkedHashMap<String,String>();
        for(int i = 0; i < jsonArray4.length(); i ++){
            JSONObject jsonObject = jsonArray4.getJSONObject(i);
            String name = jsonObject.optString("tagName");
            String type = jsonObject.optString("typeName");
            leafTags.put(type, name);

        }
        albumEntity.leafTags = leafTags;
    }

    private static void parseCpList(AlbumEntity albumEntity, JSONArray jsonArray3) throws JSONException {
        List<CpEntity> cpEntities = new ArrayList<CpEntity>();
        for(int i = 0; i < jsonArray3.length(); i ++){
            CpEntity cpEntity = new CpEntity();
            JSONObject jsonObject = jsonArray3.getJSONObject(i);
            cpEntity.cpId = jsonObject.optString("cpId");
            cpEntity.sourceChnId = jsonObject.optString("sourceChnId");
            cpEntity.sourceId = jsonObject.optString("sourceId");
            cpEntity.sourceName = jsonObject.optString("sourceName");
            cpEntity.sourceNameShort = jsonObject.optString("sourceNameShort");

            cpEntities.add(cpEntity);

        }
        albumEntity.cpEntities = cpEntities;
    }

    private static void parseAlbum(AlbumEntity albumEntity, JSONObject jsonObject2) {
        albumEntity.albumDesc = jsonObject2.optString("albumDesc");
        albumEntity.albumId = jsonObject2.optString("albumId");
        albumEntity.albumName = jsonObject2.optString("albumName");
        albumEntity.albumNameEn = jsonObject2.optString("albumNameEn");
        albumEntity.chnId = jsonObject2.optString("chnId");
        albumEntity.chnName = jsonObject2.optString("chnName");
        albumEntity.duration = jsonObject2.optString("duration");
        albumEntity.focus = jsonObject2.optString("focus");
        albumEntity.picUrl = jsonObject2.optString("picUrl");
        albumEntity.showDate = jsonObject2.optString("showDate");
        albumEntity.isSeries = jsonObject2.optInt("isSeries");
        albumEntity.sets = jsonObject2.optInt("sets");
    }

    private static void parseActors(AlbumEntity albumEntity, JSONArray jsonArray2) throws JSONException {
        List<String> actors = new ArrayList<String>();
        List<String> director = new ArrayList<String>();

        for (int i = 0; i < jsonArray2.length(); i ++){
            JSONObject jsonObject = jsonArray2.getJSONObject(i);
            String name = jsonObject.optString("name");
            int type = jsonObject.optInt("type");

            if(type == 1){
                director.add(name);
            }else if(type == 3){
                actors.add(name);
            }

        }
        albumEntity.actors = actors;
        albumEntity.director = director;
    }
}
