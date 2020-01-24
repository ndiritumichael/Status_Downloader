package com.keapps.statussaver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

public class Splash extends AppCompatActivity {
    final int REQUEST_CODE = 1;
    @BindView(R.id.Messagetxt) TextView Welcome;
    Handler  handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(ContextCompat.checkSelfPermission(Splash.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(Splash.this,"Permission was granted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Splash.this,Main2Activity.class);
                    startActivity(intent);
                    finish();

                }
                else
                StoragePermission();

            }
        },1000);



    }

    private void StoragePermission() {

            requestStoragePermision();

    }

    private void requestStoragePermision() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("permission")
                    .setMessage("you need to allow this permision")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Splash.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }).create().show();

        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
        }
    }
}
