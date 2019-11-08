package com.ab.hicarecommercialapp.view.login;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.ab.hicarecommercialapp.BaseActivity;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.location_service.LocationManager;
import com.ab.hicarecommercialapp.location_service.listner.LocationManagerListner;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.company.CompanyCodeFragment;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;

public class LoginActivity extends BaseActivity implements LocationManagerListner {
    private Location mLocation;
    private LocationManagerListner mListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.IS_USER_LOGIN)) {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();
        } else {
            if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.IS_SELF_REGISTERED)) {
                addFragment(LoginFragment.newInstance(), "LoginActivity-LoginFragment");
            } else {
                addFragment(SelectUserFragment.newInstance(), "LoginActivity-SelectUserFragment");
            }
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        LocationManager.Builder builder = new LocationManager.Builder(this);
        builder.setLocationListner(this);
        builder.build();
    }

    @Override
    public void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider) {
        this.mLocation = mLocation;
        if (mListner != null) {
            mListner.locationFetched(mLocation, oldLocation, time, locationProvider);
        }
    }

    public Location getmLocation() {
        return mLocation;
    }

    public void showLocationPopUp() {

        LocationManager.Builder builder = new LocationManager.Builder(this);
        builder.setLocationListner(this);
        builder.build();
    }

    public void setLocationListner(LocationManagerListner locationListner) {
        this.mListner = locationListner;
    }
}
