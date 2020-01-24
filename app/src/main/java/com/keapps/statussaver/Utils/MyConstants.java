package com.keapps.statussaver.Utils;

import android.os.Environment;

import java.io.File;
import java.nio.channels.FileChannel;

public class MyConstants {
    public static final File STATUS_DIRECTORY =
            new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/Media/.Statuses");
    public static final String APP_DIR = Environment.getExternalStorageDirectory() + File.separator + "Statuses";

    public static final int THUMBSIZE= 128;

    public static String pictureExtensions[] = {".gif",".jpg"};
}
