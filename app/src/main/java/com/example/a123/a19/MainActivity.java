package com.example.a123.a19;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    //文件名
    private final static String MyFileName="myfile";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            if (checkSelfPermission(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.MOUNT_FORMAT_FILESYSTEMS}, 1);
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
            }

        Button btnWrite=(Button)findViewById(R.id.buttonWrite);
        Button btnRead=(Button)findViewById(R.id.buttonRead);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/"+MyFileName);

                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content = "WANGSHENG 2014011406 single";
                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                        } finally {
                            if (out != null)
                                out.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try {

                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/"+ MyFileName);
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);

                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}