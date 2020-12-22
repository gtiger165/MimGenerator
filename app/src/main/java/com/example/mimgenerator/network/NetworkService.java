package com.example.mimgenerator.network;

import com.example.mimgenerator.model.ResultJson;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {
    @GET("get_memes")
    Call<ResultJson> getMemes();

    @POST("caption_image")
    Call<ResultJson> setCaptionImage();

    @POST("input_absen_pgw")
    Call<ResultJson> setCaptionImage(
            @Field("template_id") String templateId,
            @Field("username") String username,
            @Field("password") String password,
            @Field("text0") String text0,
            @Field("text1") String text1
    );

}
