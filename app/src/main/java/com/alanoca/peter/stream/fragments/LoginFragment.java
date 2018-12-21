package com.alanoca.peter.stream.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alanoca.peter.stream.R;
import com.alanoca.peter.stream.activities.PlayerActivity;
import com.alanoca.peter.stream.base.BaseFragment;
import com.alanoca.peter.stream.model.Data;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment  extends BaseFragment implements FacebookCallback<LoginResult>, ValueEventListener{

    private CallbackManager callbackManager;
    private DatabaseReference databaseReference;
    private ImageView imageView;
    private ProgressBar progressBar;
    private LoginButton loginButton;
    private Data data;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        callbackManager = CallbackManager.Factory.create();
        imageView = view.findViewById(R.id.imageView);
        progressBar = view.findViewById(R.id.progressBar);
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, this);
        databaseReference = FirebaseDatabase.getInstance().getReference("data");
        databaseReference.addValueEventListener(this);
        return view;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        data = dataSnapshot.getValue(Data.class);
        if (isLoggedIn()) {
            loginButton.setVisibility(View.GONE);
            replaceActivity(PlayerActivity.class, data);
        } else {
            progressBar.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        showToast("Conexión con firebase cancelada");
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        replaceActivity(PlayerActivity.class, data);
    }

    @Override
    public void onCancel() {
        showToast("Inicio de sesión cancelada");
    }

    @Override
    public void onError(FacebookException error) {
        showToast("Error en el inicio de sesión");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

}
