package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.local.PreferencesHelper;
import com.kukuhsain.simpletour.guest.model.pojo.Package;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 22/10/16.
 */

public class ReservationActivity extends AppCompatActivity {
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.iv_background_image) ImageView ivBackgroundImage;
    @BindView(R.id.tv_description) TextView tvDescription;
    @BindView(R.id.tv_price) TextView tvPrice;
    @BindView(R.id.et_people) EditText etPeople;
    @BindView(R.id.btn_sign_group) LinearLayout btnSignGroup;
    @BindView(R.id.btn_book) Button btnBook;

    Package onePackage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        String packageString = getIntent().getStringExtra("package");
        onePackage = (new Gson()).fromJson(packageString, Package.class);

        collapsingToolbarLayout.setTitle(onePackage.getTitle());
        tvDescription.setText(onePackage.getContent());
        tvPrice.setText(onePackage.getPrice()+" / person");
        Glide.with(this)
                .load(SimpleTourApi.BASE_URL+onePackage.getImageUrl())
                .into(ivBackgroundImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferencesHelper.getInstance().getLoggedInStatus()) {
            btnSignGroup.setVisibility(View.GONE);
            btnBook.setVisibility(View.VISIBLE);
        } else {
            btnSignGroup.setVisibility(View.VISIBLE);
            btnBook.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void goToSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @OnClick(R.id.btn_sign_in)
    public void goToSignIn() {
        startActivity(new Intent(this, SignInActivity.class));
    }

    @OnClick(R.id.btn_book)
    public void bookPackage() {
        int numberOfPeople = Integer.parseInt(etPeople.getText().toString());
        new AlertDialog.Builder(this)
                .setTitle("Are You Sure?")
                .setMessage("You are going to book this package. Are you sure?")
                .setPositiveButton("Book", (dialogInterface, i) -> {
                    SimpleTourApi.getInstance()
                            .bookPackage(onePackage.getPackageId(), numberOfPeople)
                            .subscribeOn(Schedulers.io())
                            .subscribe(reservation -> {
                                Intent intent = new Intent(this, DestinationsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                runOnUiThread(() -> startActivity(intent));
                            }, throwable -> {
                                throwable.printStackTrace();
                                runOnUiThread(() -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show());
                            });
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .create()
                .show();
    }
}
