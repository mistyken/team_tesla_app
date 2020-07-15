package com.example.cmpe235teslaapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//We will use the following code to load Splash Screen first then move it to Main Activity
//        startActivity(new Intent(this, MainActivity.class));

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(!isFinishing()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 900); // This will display splash screen for real loading time + delay
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}