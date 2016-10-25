package com.kukuhsain.simpletour.guest.model.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.kukuhsain.simpletour.guest.SimpleTourApp;

/**
 * Created by kukuh on 25/10/16.
 */

public class PreferencesHelper {
    private static PreferencesHelper INSTANCE;
    private SharedPreferences sharedPreferences;

    private PreferencesHelper() {
        sharedPreferences = SimpleTourApp.getInstance()
                .getSharedPreferences("simpletour.guest.sp", Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }

    public void putAccessToken(String accessToken) {
        sharedPreferences.edit().putString("accessToken", accessToken).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString("accessToken", "");
    }
}
