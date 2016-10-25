package com.kukuhsain.simpletour.guest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;

import com.kukuhsain.simpletour.guest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by kukuh on 08/10/16.
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.et_name) TextInputEditText etName;
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @BindView(R.id.et_password) TextInputEditText etPassword;
    @BindView(R.id.et_phone) TextInputEditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        Timber.d("Sign Up...");

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
            startActivity(new Intent(this, DestinationsActivity.class));
        }
    }
}
