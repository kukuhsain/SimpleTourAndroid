package com.kukuhsain.simpletour.guest.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Toast;

import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 08/10/16.
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.et_name) TextInputEditText etName;
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @BindView(R.id.et_password) TextInputEditText etPassword;
    @BindView(R.id.et_phone) TextInputEditText etPhone;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        showLoading();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();

        if (name.isEmpty()) {
            etName.setError("Please insert your name!");
            etName.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please insert a valid email!");
            etEmail.requestFocus();
        } else if (password.isEmpty()) {
            etPassword.setError("Please insert password!");
            etPassword.requestFocus();
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            etPhone.setError("Please insert a valid phone number!");
            etPhone.requestFocus();
        } else {
            SimpleTourApi.getInstance()
                    .register(name, email, password, phone)
                    .subscribeOn(Schedulers.io())
                    .subscribe(jsonObject -> {
                        runOnUiThread(() -> {
                            dismissLoading();
                            startActivity(new Intent(this, DestinationsActivity.class));
                            finish();
                        });
                    }, throwable -> {
                        runOnUiThread(() -> {
                            dismissLoading();
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    });

        }
    }

    private void showLoading() {
        if (progressDialog != null) {
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }
    }

    private void dismissLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
