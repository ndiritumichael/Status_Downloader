package com.keapps.statussaver.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.keapps.statussaver.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureView extends Fragment {

    @BindView(R.id.pictureLoad) ImageView imageLoad;
    @BindView(R.id.saveImage) Button saveButton;
    @BindView(R.id.videoLoad)
    VideoView videoView;
    String value;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        value = bundle.getString("key");
        Log.v("mainactivity", "this is the key" + value);
        //Picasso.get().load(value).into(imageLoad);
        View view = inflater.inflate(R.layout.picture_view,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        boolean show = false;
        if (value!= null){

            if (value.endsWith(".mp4")){
                show = true;

            }

            if (show){ videoView.setVideoURI(Uri.parse(value));
            videoView.setVisibility(View.VISIBLE);
                videoView.start();

            }
            else {
                imageLoad.setVisibility(View.VISIBLE);


                Log.v("mainactivity", "this is the key" + value);
                if (value != null) {

                    Log.d("picture","was loaded");
                    Picasso.get()
                            .load(new File(value))
                            .placeholder(R.drawable.ic_save)
                            .error(R.drawable.ic_launcher_background)
                            .into( imageLoad);

                } else {
                    Log.v("ndirituerrors", "Null errors");
                }

            }

        }
        else {
            Log.d("null","no value present");
        }



    }
}
