package com.kukuhsain.simpletour.guest.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Toast;

import com.kukuhsain.simpletour.guest.R;
import com.kukuhsain.simpletour.guest.model.local.PreferencesHelper;
import com.kukuhsain.simpletour.guest.model.remote.SimpleTourApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.schedulers.Schedulers;

/**
 * Created by kukuh on 08/10/16.
 */

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @BindView(R.id.et_password) TextInputEditText etPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please insert a valid email!");
            etEmail.requestFocus();
        } else if (password.isEmpty()) {
            etPassword.setError("Please insert password!");
            etPassword.requestFocus();
        } else {
            showLoading();
            SimpleTourApi.getInstance()
                    .login(email, password)
                    .subscribeOn(Schedulers.io())
                    .subscribe(accessToken -> {
                        PreferencesHelper.getInstance().putAccessToken(accessToken);
                        PreferencesHelper.getInstance().putLoggedInStatus(true);
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

    @OnClick(R.id.btn_sign_up)
    public void goToSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
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
