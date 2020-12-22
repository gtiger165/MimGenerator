package com.example.mimgenerator.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mimgenerator.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = DetailActivity.class.getSimpleName();
    public static final String EXTRA_MEME = "extra_meme";
    public static final int REQUEST_IMAGE_DATA = 768;
    private String urlImage;
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
            urlImage = object.getString("url");

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
        switch (v.getId()) {
            case R.id.btn_add_logo:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, REQUEST_IMAGE_DATA);
                break;
            case R.id.btn_add_text:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult: " + data.getData());
        }
    }

    private void displayOverlayImage(Context c, String path, ImageView iv) {
        try {
            Glide.with(c)
                    .asBitmap()
                    .load(path)
                    .error(R.drawable.ic_baseline_photo_24)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}