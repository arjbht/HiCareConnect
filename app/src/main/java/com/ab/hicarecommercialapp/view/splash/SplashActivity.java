package com.ab.hicarecommercialapp.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.hicarecommercialapp.BaseActivity;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.location_service.LocationManager;
import com.ab.hicarecommercialapp.location_service.listner.LocationManagerListner;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.company.CompanyCodeActivity;
import com.ab.hicarecommercialapp.view.company.CompanyCodeFragment;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.login.LoginActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity  {

    @BindView(R.id.imgSplash)
    ImageView imgSplash;

    @BindView(R.id.txtVersion)
    TextView txtVersion;


    private static int SPLASH_TIME_OUT = 3000;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Picasso.get().load(R.mipmap.splash).into(imgSplash);
        loadSplashScreen();
        PackageInfo pInfo = null;
        SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.IS_TODAY, true);

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String mobileVersion = pInfo.versionName;
            txtVersion.setText("V " + mobileVersion);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadSplashScreen() {
        try {
            handler.postDelayed(() -> {

//                locationManager =
//                        (android.location.LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

//                if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
                    if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.IS_USER_LOGIN)) {
                        startActivity(new Intent(this, HomeActivity.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }else {
                        startActivity(new Intent(this, CompanyCodeActivity.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
//                }else {
//                    handler.removeCallbacksAndMessages(null);
//                }

            }, SPLASH_TIME_OUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
