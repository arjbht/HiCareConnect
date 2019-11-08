package com.ab.hicarecommercialapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import com.ab.hicarecommercialapp.handler.OnBackPressListener;
import com.ab.hicarecommercialapp.utils.AppUtils;

import io.realm.Realm;

/**
 * Created by arjun on 06/03/19.
 */

public class BaseFragment extends Fragment implements OnBackPressListener {

    private ProgressDialog mProgressDialog;
    private Realm mRealm;
    protected Toolbar toolbar;
    protected ActionBar actionBar;
    protected ActionBarDrawerToggle toggle;
    protected boolean mToolBarNavigationListenerIsRegistered = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mRealm = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mRealm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Realm getRealm() {
        return mRealm;
    }
//  public void showProgressDialog() {
//    if (mProgressDialog != null && mProgressDialog.isShowing()) {
//      return;
//    }
//    Log.i("ProgressDialog", "showProgressDialog");
//    try {
//      mProgressDialog = new ProgressDialog(getActivity(), R.style.TransparentProgressDialog);
//      mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
//      mProgressDialog.setCancelable(false);
//      mProgressDialog.setIndeterminate(true);
//      mProgressDialog.show();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }

//  public void dismissProgressDialog() {
//    if (mProgressDialog != null) {
//      if (!mProgressDialog.isShowing()) {
//        return;
//      }
//      Log.i("ProgressDialog", "dismissProgressDialog");
//      try {
//        mProgressDialog.dismiss();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//  }

    public void showServerError() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Network Error");
            builder.setMessage(
                    "Unable to connect to server, please check your internet connection and try again.");
            builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServerError(String msg) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg);
            builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    protected void addFragment(BaseFragment fragment, String tag) {
        getFragmentManager().beginTransaction().add(R.id.container, fragment, tag).commit();
    }

    protected void replaceFragmentBottom(BaseFragment fragment, String tag) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    protected void storeBoolean(String key, Boolean value) {
        SharedPreferences pref = getActivity().getApplicationContext()
                .getSharedPreferences("hicare_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected Boolean getBoolean(String key, Boolean defauleValue) {
        SharedPreferences pref = getActivity().getApplicationContext()
                .getSharedPreferences("hicare_pref", Context.MODE_PRIVATE);
        return pref.getBoolean(key, defauleValue);
    }

    protected void showBackButton(boolean show) {

        if (show) {
            // Show back button
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!mToolBarNavigationListenerIsRegistered) {
                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mToolBarNavigationListenerIsRegistered = false;
        }

    }

    @Override
    public void onBackPressed() {
        int fragment = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        if (fragment <= 1) {
            AppUtils.showExitAlert(getActivity());
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
