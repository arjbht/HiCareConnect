package com.ab.hicarecommercialapp.view.company;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseActivity;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.location_service.LocationManager;
import com.ab.hicarecommercialapp.location_service.listner.LocationManagerListner;

public class CompanyCodeActivity extends BaseActivity implements LocationManagerListner {
    private Location mLocation;
    private LocationManagerListner mListner;
    private android.location.LocationManager locationManager;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_code);
        askPermissions();
        addFragment(CompanyCodeFragment.newInstance(), "CompanyCodeActivity - CompanyCodeFragment");
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

    public void askPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_PHONE_STATE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(
                            "Please allow all permissions in App Settings for additional functionality.")
                            .setCancelable(false)
                            .setPositiveButton("Allow", (dialog, id) -> Toast.makeText(CompanyCodeActivity.this, "Welcome", Toast.LENGTH_SHORT).show())
                            .setNegativeButton("Deny", (dialog, id) -> {
                                // Permission denied
                            });
                    alert = builder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_PHONE_STATE, Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_REQUEST_CODE);
                }
            }
        }
    }

}
