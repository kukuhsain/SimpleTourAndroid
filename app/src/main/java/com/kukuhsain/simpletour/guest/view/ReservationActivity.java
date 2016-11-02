package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.pojo.Package;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 22/10/16.
 */

public class ReservationActivity extends AppCompatActivity {
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.iv_background_image) ImageView ivBackgroundImage;
    @BindView(R.id.tv_description) TextView tvDescription;
    @BindView(R.id.tv_price) TextView tvPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        String packageString = getIntent().getStringExtra("package");
        Package onePackage = (new Gson()).fromJson(packageString, Package.class);

        collapsingToolbarLayout.setTitle(onePackage.getTitle());
        tvDescription.setText(onePackage.getContent());
        tvPrice.setText(onePackage.getPrice()+" / person");
        Glide.with(this)
                .load(SimpleTourApi.BASE_URL+onePackage.getImageUrl())
                .into(ivBackgroundImage);
    }

    @OnClick(R.id.btn_book)
    public void goToContactDetails() {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        /*intent.putExtra("destination", (new Gson()).toJson(destination));*/
        startActivity(intent);
    }
}
