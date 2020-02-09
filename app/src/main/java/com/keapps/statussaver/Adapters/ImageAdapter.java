package com.keapps.statussaver.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.keapps.statussaver.Fragments.ImageFragment;
import com.keapps.statussaver.Fragments.PictureView;
import com.keapps.statussaver.Main2Activity;
import com.keapps.statussaver.MainActivity;
import com.keapps.statussaver.Model.StatusModel;
import com.keapps.statussaver.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import static android.content.ContentValues.TAG;


public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ImageHolder>{


    private final List<StatusModel> imageList;
    Context context;
    ImageFragment imageFragment;

    public ImageAdapter(List<StatusModel> imageList, Context context, ImageFragment imageFragment) {
        this.imageList = imageList;
        this.context = context;
        this.imageFragment = imageFragment;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.image_status,parent,false);

        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageHolder holder, final int position) {

        final StatusModel statusModel = imageList.get(position);
        holder.imageView.setImageBitmap(statusModel.getThumbnail());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                String x = statusModel.getFile().toString();
                if (x != null) {
                    imageFragment.loadPic(x);
                }
                else{ Log.d("errors","Cant load image");}

            }
        });
        /*holder.imageButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusModel statusModel = imageList.get(position);
                if (statusModel != null){

                    try {
                        imageFragment.DownloadImage(statusModel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardClick) CardView cardView;

        @BindView(R.id.thumbView) ImageView imageView;
        @BindView(R.id.saveToGallery) ImageButton imageButtonDownload;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imageButtonDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatusModel statusmodel = imageList.get(getAdapterPosition());
                    if (statusmodel !=  null){

                        try {
                            imageFragment.downloadImage(statusmodel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }
    }
}
