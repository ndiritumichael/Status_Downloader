package com.keapps.statussaver.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keapps.statussaver.Adapters.ImageAdapter;
import com.keapps.statussaver.Main2Activity;
import com.keapps.statussaver.MainActivity;
import com.keapps.statussaver.Model.StatusModel;
import com.keapps.statussaver.R;
import com.keapps.statussaver.Utils.MyConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class ImageFragment extends Fragment {
    OnSendMessageListener onSendMessageListener;
    private static final String TAG = "MyActivity";

    @BindView(R.id.imageRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Handler handler = new Handler();
    ImageAdapter imageAdapter;

    ArrayList<StatusModel> imageModelList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ButterKnife.bind(this,view);
        imageModelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getStatus();  }

    public void getStatus()  {
        Log.v(TAG,"mikewildid this and that");
        if (MyConstants.STATUS_DIRECTORY.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusfiles = MyConstants.STATUS_DIRECTORY.listFiles();
                    if (statusfiles != null && statusfiles.length> 0){
                        progressBar.setVisibility(View.GONE);


                        Arrays.sort(statusfiles);

                        /*for (File file :files)
                        {
                            if (file.getName().endsWith(".jpg")|| (file.getName().endsWith(".gif"))|| (file.getName().endsWith(".mp4")) ){

                                if (infiles.contains(file)){
                                    infiles.add(file);
                                }
                            }
                        }*/



                        for (final File statusFile:statusfiles){


                            boolean show = false;
                            for (String ext: MyConstants.pictureExtensions){

                                if (statusFile.getName().endsWith(ext)){
                                    show = true;
                                }
                            }

                            if (show ) {

                                StatusModel statusModel = new StatusModel(statusFile, statusFile.getName(), statusFile.getAbsolutePath());
                                statusModel.setThumbnail(getThumbnail(statusModel));
                                if (!statusModel.getVideo()) {
                                    imageModelList.add(statusModel);
                                    //Toast.makeText(getContext(), "The size is"+imageModelList.size(), Toast.LENGTH_SHORT).show();
                                    Log.v(TAG, "mikewildid this but" + imageModelList.size());

                                }
                            }
                            
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                imageAdapter = new ImageAdapter(imageModelList,getContext(),ImageFragment.this);
                                recyclerView.setAdapter(imageAdapter);
                                imageAdapter.notifyDataSetChanged();


                            }
                        });


                    }
                    else {
                        Log.v(TAG,"No status");
                    }
                }
            }).start();
        } else {

            Log.v(TAG,"the Dir does not exist");
            Log.v(TAG,MyConstants.STATUS_DIRECTORY.toString());

        }
    }

    public Bitmap getThumbnail(StatusModel statusModel) {


            if (statusModel.getVideo()){
                return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
            }
            else
                return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath())
                        ,MyConstants.THUMBSIZE,MyConstants.THUMBSIZE);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.image_fragment,container,false);
        return view;
    }


    public void downloadImage(StatusModel statusmodel) throws IOException {
        File file = new File(MyConstants.APP_DIR);
        if (!file.exists()){
            file.mkdirs();
        }

        File destFile = new File(file+File.separator+statusmodel.getTitle());
        if (destFile.exists()){
            destFile.delete();
        }

        copyFile(statusmodel.getFile(),destFile);
        Toast.makeText(getActivity(),"Download complete",Toast.LENGTH_LONG).show();
        Log.v("Activity","File downloaded as should be");
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(destFile));
        getActivity().sendBroadcast(intent);


    }

    private void copyFile(File file, File destFile) throws IOException {

        if (!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdirs();
        }
        if (!destFile.exists()){
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        source= new FileInputStream(file).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source,0,source.size());
        source.close();
        destination.close();
    }



    public interface OnSendMessageListener{

        void onMessageSend(String filePath);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            onSendMessageListener = (OnSendMessageListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnMessageSend");
        }

    }


    public void loadPic(String x) {
        Log.d("ufala","the string is"+ x);


        onSendMessageListener.onMessageSend(x);
    }
}
