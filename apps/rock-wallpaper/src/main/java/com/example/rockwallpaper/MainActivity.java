package com.example.rockwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button setWallpaperBtn = findViewById(R.id.btnSetWallpaper);
        setWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRockWallpaper();
            }
        });
    }

    private void setRockWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.minimalist_rock);
        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(this, "Sayanth Rock Wallpaper Applied!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error setting wallpaper", Toast.LENGTH_SHORT).show();
        }
    }
}