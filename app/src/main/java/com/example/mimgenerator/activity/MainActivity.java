package com.example.mimgenerator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.mimgenerator.R;
import com.example.mimgenerator.adapter.MemeAdapter;
import com.example.mimgenerator.model.ResultJson;
import com.example.mimgenerator.model.SingleMeme;
import com.example.mimgenerator.viewmodel.MemeViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String TAG = MainActivity.class.getSimpleName();
    RecyclerView rvMeme;
    SwipeRefreshLayout srl;

    private MemeViewModel memeViewModel;
    private MemeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareView();
    }

    private void prepareView() {
        srl = findViewById(R.id.swipe_meme);
        srl.setOnRefreshListener(this);

        rvMeme = findViewById(R.id.rv_list_image);
        adapter = new MemeAdapter();
        rvMeme.setLayoutManager(new GridLayoutManager(this, 3));

        memeViewModel = new ViewModelProvider(this).get(MemeViewModel.class);
        memeViewModel.loadMemes();
        memeViewModel.getMemes().observe(this, getListMemes);
    }

    private Observer<JsonArray> getListMemes = new Observer<JsonArray>() {
        @Override
        public void onChanged(JsonArray jsonArray) {
            srl.setRefreshing(false);
            adapter.clear();

            Log.d(TAG, "onChanged: cek response data -> " + jsonArray);
            adapter.addAll(jsonArray);
            rvMeme.setAdapter(adapter);
        }
    };

    @Override
    public void onRefresh() {
        adapter.clear();
        memeViewModel.loadMemes();
    }
}