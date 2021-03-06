package com.example.dex.popularmovies.model.data;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public final class Video {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("iso_639_1")
    private String isoLanguageCode;
    @Expose
    @SerializedName("iso_3166_1")
    private String isoCountryCode;
    @Expose
    @SerializedName("key")
    private String key;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("site")
    private String site;
    @Expose
    @SerializedName("size")
    private int size;
    @Expose
    @SerializedName("type")
    private String type;

    /**
     * Public no-args constructor for serialization
     */
    public Video() {

    }

    /**
     * @param id
     * @param isoLanguageCode
     * @param isoCountryCode
     * @param key
     * @param name
     * @param site
     * @param size
     * @param type
     */
    public Video(String id, String isoLanguageCode, String isoCountryCode, String key, String name, String site, int size, String type) {
        this.id = id;
        this.isoLanguageCode = isoLanguageCode;
        this.isoCountryCode = isoCountryCode;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getIsoLanguageCode() {
        return isoLanguageCode;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

    public String getVideoUrl() {
        if ("youtube".equals(this.getSite().toLowerCase())) {
            return "https://www.youtube.com/watch?v=" + this.getKey();
        } else {
            return null;
        }
    }
}
