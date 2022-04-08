package com.doubleclick.uberappjavakotlin.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.doubleclick.uberappjavakotlin.R;

public class SplachActivity extends AppCompatActivity {


    // like nitfilx
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        WebView webView = findViewById(R.id.web);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
//        webView.loadUrl("https://www.tutorialspoint.com/android/index.htm");
        webView.loadUrl("file:///android_asset/index.html");

//        MediaPlayer player = MediaPlayer.create(SplachActivity.this, R.raw.hobk);
//        player.start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(SplachActivity.this, MainActivity.class);
//                startActivity(mainIntent);
//                finish();
//            }
//        }, 2000);
    }
}