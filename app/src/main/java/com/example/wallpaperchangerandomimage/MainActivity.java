package com.example.wallpaperchangerandomimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int i=0;
    private Button changeWallpaper,stop;
    boolean stop_wallpaper=false;
    Timer myTimer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        changeWallpaper = findViewById(R.id.changeWallpaper);
        stop = findViewById(R.id.stop);
        changeWallpaper.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myTimer = new Timer();
                myTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        set_wallpaper();
                    }
                },500,30000);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_wallpaper_func();
            }
        });
    }

    public void stop_wallpaper_func()
    {
        stop_wallpaper=true;
        myTimer.cancel();
        Toast.makeText(getApplicationContext(),"Stopping change wallpaper set application",Toast.LENGTH_LONG).show();
        System.out.println("Stopping change wallpaper set application\n stop_wallpaper = "+stop_wallpaper);
    }
    private void set_wallpaper() {

        final int[] names = new int[3];
        names[0]=R.drawable.wallpaper0;
        names[1]=R.drawable.wallpaper1;
        names[2]=R.drawable.wallpaper2;

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), names[i]);
            WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
            try {
                manager.setBitmap(bitmap);

                System.out.println("Wallpaper Set!");
                i++;
            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("Error, Something went wrong, try again later");
            }
            if (i>2) {
                i = 0;
            }

            System.out.println("i = "+i+" names[i] = "+names[i]);
            for (int j =0; j<names.length;j++)
            {
                System.out.println("\n\nnames["+j+"] = "+names[j]);
            }

    }

}