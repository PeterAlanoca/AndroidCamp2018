package com.alanoca.peter.stream.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alanoca.peter.stream.R;
import com.alanoca.peter.stream.model.Data;

public abstract class BaseActivity extends AppCompatActivity {

    private static String KEY_DATA = "DATA";

    protected void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void replaceActivity(Class<?> cls) {
        startActivity(new Intent(getApplicationContext(), cls));
        finish();
    }

    protected void replaceActivity(Class<?> cls, Data data) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.putExtra(KEY_DATA, data);
        startActivity(intent);
        finish();
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment)
                .commit();
    }
}
