package com.keapps.statussaver.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keapps.statussaver.Adapters.ImageAdapter;
import com.keapps.statussaver.Adapters.VideoAdapter;
import com.keapps.statussaver.Model.StatusModel;
import com.keapps.statussaver.R;
import com.keapps.statussaver.Utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoFragment extends Fragment {

    @BindView(R.id.video_Recycler)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarVideo)
    ProgressBar progressBar;
    Handler handler = new Handler();
    ArrayList<StatusModel> videoModelList;
    VideoAdapter videoAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.video_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        getStatusVideos();
        videoModelList = new ArrayList<>();

    }

    private void getStatusVideos() {
        if (MyConstants.STATUS_DIRECTORY.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusfiles = MyConstants.STATUS_DIRECTORY.listFiles();
                    if (statusfiles != null && statusfiles.length> 0){

                        Arrays.sort(statusfiles);
                        for (final File statusFile:statusfiles){
                            StatusModel statusModel = new StatusModel(statusFile,statusFile.getName(),statusFile.getAbsolutePath());
                            statusModel.setThumbnail(getThumbnail(statusModel));
                            if (statusModel.getVideo()){
                                videoModelList.add(statusModel);
                            }

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                videoAdapter = new VideoAdapter(videoModelList,getContext(),VideoFragment.this);
                                recyclerView.setAdapter(videoAdapter    );
                                videoAdapter.notifyDataSetChanged();


                            }
                        });


                    }
                }
            }).start();
        }
    }

    private Bitmap getThumbnail(StatusModel statusModel) {


        if (statusModel.getVideo()){
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
        }
        else
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath())
                    ,MyConstants.THUMBSIZE,MyConstants.THUMBSIZE);

    }
}
