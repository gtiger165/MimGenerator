package com.example.mimgenerator.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class SingleMeme {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("width")
    private int witdh;
    @SerializedName("height")
    private int height;
    @SerializedName("box_count")
    private int boxCount;

    public SingleMeme(String id, String name, String url, int witdh, int height, int boxCount) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.witdh = witdh;
        this.height = height;
        this.boxCount = boxCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getWitdh() {
        return witdh;
    }

    public int getHeight() {
        return height;
    }

    public int getBoxCount() {
        return boxCount;
    }
}
