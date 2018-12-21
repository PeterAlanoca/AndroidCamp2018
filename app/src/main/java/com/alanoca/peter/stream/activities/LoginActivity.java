package com.alanoca.peter.stream.activities;

import android.os.Bundle;
import com.alanoca.peter.stream.R;
import com.alanoca.peter.stream.base.BaseActivity;
import com.alanoca.peter.stream.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        replaceFragment(LoginFragment.newInstance());
    }

}
