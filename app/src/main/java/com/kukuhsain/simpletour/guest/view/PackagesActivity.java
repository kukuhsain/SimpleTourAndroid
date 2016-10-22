package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.pojo.Destination;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;
import com.kukuhsain.simpletour.guest.view.adapter.PackageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 08/10/16.
 */

public class PackagesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.iv_background_image) ImageView ivBackgroundImage;
    @BindView(R.id.tv_description) TextView tvDescription;
    @BindView(R.id.rv_packages) RecyclerView rvPackages;

    private PackageAdapter packageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String destinationString = getIntent().getStringExtra("destination");
        Destination destination = (new Gson()).fromJson(destinationString, Destination.class);
        collapsingToolbarLayout.setTitle(destination.getTitle());
        tvDescription.setText(destination.getContent());
        Glide.with(this)
                .load(SimpleTourApi.BASE_URL+destination.getImageUrl())
                .into(ivBackgroundImage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPackages.setLayoutManager(layoutManager);

        packageAdapter = new PackageAdapter(this, generateDummyData());
        rvPackages.setAdapter(packageAdapter);
    }

    private List<String> generateDummyData() {
        List<String> dummyData = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            dummyData.add("Lorem Ipsum dummy data #"+i);
        }
        return dummyData;
    }

    public void onItemClicked(String onePackage) {
        Intent intent = new Intent(this, ReservationActivity.class);
        /*intent.putExtra("destination", (new Gson()).toJson(destination));*/
        runOnUiThread(() -> startActivity(intent));
    }
}
