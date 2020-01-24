package com.keapps.statussaver.Model;

import android.graphics.Bitmap;

import java.io.File;
import java.nio.channels.FileChannel;

public class StatusModel {
    private static final String  MP4 = ".mp4";
    private final File file;
    private Bitmap thumbnail;
    private final String title,path;
    private Boolean isVideo;


    public StatusModel(File file, String title, String path) {
        this.file = file;
        this.title = title;
        this.path = path;
        this.isVideo = file.getName().endsWith(MP4);
    }

    public File getFile() {
        return file;
    }



    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }
}
