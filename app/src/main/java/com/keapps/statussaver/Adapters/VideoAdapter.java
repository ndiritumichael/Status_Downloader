package com.keapps.statussaver.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keapps.statussaver.Fragments.ImageFragment;
import com.keapps.statussaver.Fragments.VideoFragment;
import com.keapps.statussaver.Model.StatusModel;
import com.keapps.statussaver.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private final List<StatusModel> videoList;
    Context context;

    public VideoAdapter(List<StatusModel> videoList, Context context, VideoFragment videoFragment) {
        this.videoList = videoList;
        this.context = context;
        this.videoFragment = videoFragment;
    }

    VideoFragment videoFragment;
    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_status,parent,false);


        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        StatusModel statusModel = videoList.get(position);
        holder.imageView.setImageBitmap(statusModel.getThumbnail());


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbView)
        ImageView imageView;
        //@BindView(R.id.saveToGallery)
        //ImageButton imageButton;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
