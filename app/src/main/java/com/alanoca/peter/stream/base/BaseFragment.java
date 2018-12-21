package com.alanoca.peter.stream.base;

import android.support.v4.app.Fragment;

import com.alanoca.peter.stream.model.Data;

public abstract class BaseFragment extends Fragment {

    protected void showToast(String message) {
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).showToast(message);
        }
    }

    protected void replaceActivity(Class<?> cls, Data data) {
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).replaceActivity(cls, data);
        }
    }

    protected void replaceFragment(Fragment fragment) {
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).replaceFragment(fragment);
        }
    }
}
