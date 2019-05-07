package com.android.tvnet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.android.tvnet.R;
import com.android.tvnet.util.PhoneUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.layout_sing_in)
    LinearLayout sing_in;
    @BindView(R.id.invalid_message)
    AppCompatTextView invalidTextView;
    @BindView(R.id.et_login)
    AppCompatEditText editLogin;
    @BindView(R.id.et_password)
    AppCompatEditText editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sing_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email = editLogin.getText().toString();
        String pass = editPassword.getText().toString();

        if (ownValidation(email, pass)) {
            //ToDo Request For Login and Save User Token by Shared SharedPrefsService
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    private boolean ownValidation(String email, String pass) {

        Pattern patternEmail = Patterns.EMAIL_ADDRESS;
        Matcher mailMarcher = patternEmail.matcher(email);


        boolean loginValid = mailMarcher.matches();
        boolean passValid = pass.length() > 0;


        if (loginValid && passValid) {
            allValid();

            //hide keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
            return true;
        } else {


            if (!passValid) {
                inValidEditText(editPassword, invalidTextView, getResources().getString(R.string.write_pass));

            } else {
                validEditText(editPassword, invalidTextView);
            }

            if (!loginValid) {
                inValidEditText(editLogin, invalidTextView, getResources().getString(R.string.incorrect_email));

            } else {
                validEditText(editLogin, invalidTextView);
            }

            return false;
        }
    }

    private void allValid() {
        validEditText(editLogin, invalidTextView);
        validEditText(editPassword, invalidTextView);
    }

    private void validEditText(AppCompatEditText editText, AppCompatTextView massTextView) {

        editText.setTextColor(getColor(R.color.white));
        editText.setHintTextColor(getColor(R.color.grey));
        editText.setBackgroundDrawable(getDrawable(R.drawable.edit_text_shape));
        massTextView.setVisibility(View.GONE);
    }

    private void inValidEditText(AppCompatEditText editText, AppCompatTextView massTextView, String massage) {
        massTextView.setVisibility(View.VISIBLE);
        massTextView.setText(massage);
        editText.setBackgroundDrawable(getDrawable(R.drawable.edit_text_inval_shap));
        editText.setTextColor(getColor(R.color.red));
        editText.setHintTextColor(getColor(R.color.red));

        //vibration
        PhoneUtil.getInstance().vibrating(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
