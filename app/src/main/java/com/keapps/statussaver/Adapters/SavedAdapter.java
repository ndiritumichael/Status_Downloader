package com.keapps.statussaver.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.keapps.statussaver.Model.StatusModel;
import com.keapps.statussaver.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedAdapter extends RecyclerView.Adapter <SavedAdapter.MediaHolder>{

    private final List<StatusModel> videoList;
    Context context;

    public SavedAdapter(List<StatusModel> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MediaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_status,parent,false);
        return new MediaHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MediaHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MediaHolder extends  RecyclerView.ViewHolder{

        @BindView(R.id.cardClick)
        CardView cardView;

        @BindView(R.id.thumbView)
        ImageView imageView;
        @BindView(R.id.saveToGallery)
        ImageButton imageButtonDownload;
        public MediaHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);;
        }
    }
}
