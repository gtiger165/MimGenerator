package com.example.mimgenerator.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mimgenerator.model.ResultJson;
import com.example.mimgenerator.model.SingleMeme;
import com.example.mimgenerator.network.NetworkClient;
import com.example.mimgenerator.network.NetworkService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemeViewModel extends ViewModel {
    private String TAG = MemeViewModel.class.getSimpleName();
    private MutableLiveData<JsonArray> listResult;
    private boolean error = false;

    public void loadMemes(){
        NetworkService service = NetworkClient.getClient().create(NetworkService.class);

        Call<ResultJson> call = service.getMemes();

        call.enqueue(new Callback<ResultJson>() {
            @Override
            public void onResponse(Call<ResultJson> call, Response<ResultJson> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().isSuccess()) {
                        Log.d(TAG, "onResponse: response body -> " + response.body().getData());
                        listResult.setValue(response.body().getData().getAsJsonArray("memes"));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultJson> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                listResult.setValue(null);
            }
        });
    }

    public LiveData<JsonArray> getMemes(){
        if (listResult == null) {
            listResult = new MutableLiveData<>();
        }

        return listResult;
    }
}
