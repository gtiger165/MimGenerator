package com.example.mimgenerator.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ResultJson {
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private JsonObject data;

    public ResultJson(boolean success, JsonObject data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonObject getData() {
        return data;
    }
}
