package com.example.mustafa.businessnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mustafa on 16/03/2018.
 */

public class SplashActivity extends AppCompatActivity {

    long Delay = 1250;

    public void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer RunSplash = new Timer();

        TimerTask ShowSplash =  new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent myIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(myIntent);

            }
        };

        RunSplash.schedule(ShowSplash,Delay);
    }
}
