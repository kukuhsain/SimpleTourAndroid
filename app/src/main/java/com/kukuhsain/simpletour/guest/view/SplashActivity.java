package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.local.PreferencesHelper;

import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("accessToken...");
        Timber.d(PreferencesHelper.getInstance().getAccessToken());
        Timber.d("loggedInStatus");
        Timber.d(String.valueOf(PreferencesHelper.getInstance().getLoggedInStatus()));
        new Handler().postDelayed(() -> {
            if (PreferencesHelper.getInstance().getLoggedInStatus()) {
                startActivity(new Intent(SplashActivity.this, DestinationsActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                finish();
            }
        }, 2000);
    }
}
