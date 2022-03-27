package com.doubleclick.uberappjavakotlin.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.doubleclick.uberappjavakotlin.R;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        MediaPlayer player = MediaPlayer.create(SplachActivity.this, R.raw.hobk);
        player.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplachActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}