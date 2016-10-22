package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kukuhsain.simpletour.guest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 22/10/16.
 */

public class ReservationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_book)
    public void goToContactDetails() {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        /*intent.putExtra("destination", (new Gson()).toJson(destination));*/
        startActivity(intent);
    }
}
