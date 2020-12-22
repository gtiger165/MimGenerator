package com.example.mimgenerator.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mimgenerator.R;
import com.example.mimgenerator.activity.DetailActivity;
import com.example.mimgenerator.model.SingleMeme;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MemeViewHolder> {
    private JsonArray listMeme = new JsonArray();

    public MemeAdapter() {
    }

    public void addAll(JsonArray mListMeme){
        listMeme.addAll(mListMeme);
        notifyDataSetChanged();
    }

    public void clear(){
        listMeme = new JsonArray();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_image, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listMeme.size();
    }

    public class MemeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivMeme;
        TextView tvId, tvName, tvWidth, tvHeight, tvBoxCount;

        public MemeViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMeme = itemView.findViewById(R.id.item_image);
            tvId = itemView.findViewById(R.id.item_id);
            tvName = itemView.findViewById(R.id.item_name);
            tvWidth = itemView.findViewById(R.id.item_width);
            tvHeight = itemView.findViewById(R.id.item_height);
            tvBoxCount = itemView.findViewById(R.id.item_box_count);

            itemView.setOnClickListener(this);
        }

        void bind(int i){
            JsonObject meme = listMeme.get(i).getAsJsonObject();

            tvId.setText(meme.get("id").getAsString());
            tvName.setText(meme.get("name").getAsString());
            tvWidth.setText(meme.get("width").getAsString());
            tvHeight.setText(meme.get("height").getAsString());
            tvBoxCount.setText(meme.get("box_count").getAsString());

            Glide.with(itemView.getContext())
                    .load(meme.get("url").getAsString())
                    .placeholder(R.drawable.ic_baseline_photo_24)
                    .centerCrop()
                    .into(ivMeme);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            JsonObject object = listMeme.get(index).getAsJsonObject();

            Intent detail = new Intent(v.getContext(), DetailActivity.class);
            detail.putExtra(DetailActivity.EXTRA_MEME, object.toString());
            v.getContext().startActivity(detail);
        }
    }
}
