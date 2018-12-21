package com.alanoca.peter.stream.activities;

import android.os.Bundle;

import com.alanoca.peter.stream.R;
import com.alanoca.peter.stream.base.BaseActivity;
import com.alanoca.peter.stream.fragments.PlayerFragment;
import com.alanoca.peter.stream.model.Data;

public class PlayerActivity extends BaseActivity {

    private static String KEY_DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Data data = (Data) getIntent().getSerializableExtra(KEY_DATA);

        replaceFragment(PlayerFragment.newInstance(data));
    }
}
