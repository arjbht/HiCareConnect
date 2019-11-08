package com.ab.hicarecommercialapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by arjun on 06/03/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context context) {
        try {
            super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, tag).commit();
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(null)
                .commit();
    }
}
