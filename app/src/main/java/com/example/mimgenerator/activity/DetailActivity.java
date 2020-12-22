package com.example.mimgenerator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mimgenerator.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MEME = "extra_meme";
    ImageView ivMeme;
    Button btnLogo, btnText;
    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Meme");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }

        prepareView();

        try {
            showMeme();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showMeme() throws JSONException {
        if (getIntent() != null) {
            object = new JSONObject(Objects.requireNonNull(getIntent().getStringExtra(EXTRA_MEME)));

            Glide.with(this)
                    .load(object.getString("url"))
                    .placeholder(R.drawable.ic_baseline_photo_24)
                    .override(
                            Integer.parseInt(object.getString("width")),
                            Integer.parseInt(object.getString("height"))
                    )
                    .into(ivMeme);
        }
    }

    private void prepareView() {
        ivMeme = findViewById(R.id.detail_img_meme);
        btnLogo = findViewById(R.id.btn_add_logo);
        btnText = findViewById(R.id.btn_add_text);

        btnLogo.setOnClickListener(this);
        btnText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}