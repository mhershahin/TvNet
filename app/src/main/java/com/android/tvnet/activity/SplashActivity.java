package com.android.tvnet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.tvnet.R;
import com.android.tvnet.util.SharedPrefsService;
import com.github.loadingview.LoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_loader)
    LoadingView splashLoader;

    private int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        splashLoader.start();
        final Activity context = this;
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//if getUserToken is null automatically is going in login page
                    if (SharedPrefsService.getInstance().getUserToken(context) != null) {
                        goMainActivity();
                    }

                }
            }
        };
        timer.start();
    }

    private void goMainActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
    }
}