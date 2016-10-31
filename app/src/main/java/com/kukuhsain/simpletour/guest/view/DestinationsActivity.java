package com.kukuhsain.simpletour.guest.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.local.PreferencesHelper;
import com.kukuhsain.simpletour.guest.model.pojo.Destination;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;
import com.kukuhsain.simpletour.guest.view.adapter.DestinationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kukuh on 08/10/16.
 */

public class DestinationsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_destinations) RecyclerView rvDestinations;

    RecyclerView.LayoutManager rvLayoutManager;
    RecyclerView.Adapter rvAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);
        ButterKnife.bind(this);

        rvLayoutManager = new LinearLayoutManager(this);
        rvDestinations.setLayoutManager(rvLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Destinations");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d(SimpleTourApi.BASE_URL);
        SimpleTourApi.getInstance().getDestinations()
                .subscribeOn(Schedulers.io())
                .subscribe(destinations -> {
                    rvAdapter = new DestinationAdapter(this, destinations);
                    runOnUiThread(() -> {
                        rvDestinations.setAdapter(rvAdapter);
                        if (progressDialog.isShowing()) {
                            progressDialog.hide();
                        }
                    });
                }, throwable -> {
                    throwable.printStackTrace();
                    runOnUiThread(() -> {
                        Toast.makeText(DestinationsActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.hide();
                        }
                    });
                });
    }

    public void onItemClicked(Destination destination) {
        Intent intent = new Intent(this, PackagesActivity.class);
        intent.putExtra("destination", (new Gson()).toJson(destination));
        runOnUiThread(() -> startActivity(intent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (PreferencesHelper.getInstance().getLoggedInStatus()) {
            menuInflater.inflate(R.menu.menu_logged_in, menu);
        } else {
            menuInflater.inflate(R.menu.menu_not_logged_in, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_in:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.btn_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.menu_sign_out:
                PreferencesHelper.getInstance().clearData();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
