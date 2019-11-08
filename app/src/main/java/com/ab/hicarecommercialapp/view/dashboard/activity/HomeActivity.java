package com.ab.hicarecommercialapp.view.dashboard.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseActivity;
import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.handler.OnBackPressListener;
import com.ab.hicarecommercialapp.location_service.LocationManager;
import com.ab.hicarecommercialapp.location_service.listner.LocationManagerListner;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.branch.BranchResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.fragment.EmptyBranchFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.account.AccountFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.BottomSheetFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.HomeFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.notifications.NotificationFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderFragment;
import com.ab.hicarecommercialapp.view.login.BranchPresenter;
import com.ab.hicarecommercialapp.view.login.BranchView;
import com.ab.hicarecommercialapp.view.login.LoginActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.ab.hicarecommercialapp.BaseApplication.getRealm;

public class HomeActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener, BranchView, LocationManagerListner, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @BindView(R.id.container)
    FrameLayout container;

    @BindView(R.id.customToolbar)
    CardView cToolbar;

    @BindView(R.id.toolbar)
    CardView toolbar;

    @BindView(R.id.toolbarView)
    Toolbar toolbarView;

    @BindView(R.id.txtLocation)
    TextView txtLocation;

    @BindView(R.id.loader)
    MKLoader loader;

    RealmResults<Branch> mBranchRealmResults;
    private Location mLocation;
    private LocationManagerListner mListner;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private android.location.LocationManager mLocationManager;
    private long UPDATE_INTERVAL = 10 * 10000;  /* 1 sec */
    private long FASTEST_INTERVAL = 100000; /* 1 sec */
    int skip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (android.location.LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationManager.Builder builder = new LocationManager.Builder(this);
        builder.setLocationListner(this);
        builder.build();
        cToolbar.setOnClickListener(view -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });
//        if (getmLocation() != null) {
//            getBranchList();
//        }

    }

    private void getBranchList(double latitude, double longitude) {
        RealmResults<LoginResponse> mloginRealmModel =
                BaseApplication.getRealm().where(LoginResponse.class).findAll();
        if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
            String userId = mloginRealmModel.get(0).getUserId();
            String companyCode = mloginRealmModel.get(0).getCompanyCode();
            String lat = String.valueOf(latitude);
            String lon = String.valueOf(longitude);
            BranchPresenter presenter = new BranchPresenter(this);
            presenter.getBranchList(userId, companyCode, lat, lon);
        }
    }


    private void setupNavigationView() {
        if (bottomNavigation != null) {
            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigation.getMenu();
            selectFragment(menu.getItem(0));
            bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
                selectFragment(menuItem);
                return false;
            });
        }
    }

    private void selectFragment(MenuItem item) {
        item.setChecked(true);
        mBranchRealmResults = getRealm().where(Branch.class).findAll();
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                    replaceFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");
                } else {
                    replaceFragment(EmptyBranchFragment.newInstance("Home"), "HomeActivity-EmptyBranchFragment");
                }

                break;
            case R.id.nav_orders:
                if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                    replaceFragment(OrderFragment.newInstance(), "HomeActivity-OrderFragment");
                } else {
                    replaceFragment(EmptyBranchFragment.newInstance("Orders"), "HomeActivity-EmptyBranchFragment");
                }

                break;
            case R.id.nav_notification:
                replaceFragment(NotificationFragment.newInstance(), "HomeActivity-NotificationFragment");

                break;
            case R.id.nav_account:
                replaceFragment(AccountFragment.newInstance(), "HomeActivity-AccountFragment");

                break;
        }
    }

    @Override
    public void onBackPressed() {
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment <= 1) {
            AppUtils.showExitAlert(this);
        } else {
            if (fragment1 instanceof OnBackPressListener) {
                ((OnBackPressListener) fragment1).onBackPressed();
            }
//            replaceFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.container);
        switch (item.getItemId()) {
            case android.R.id.home:
                if (fragment1 instanceof OnBackPressListener) {
                    ((OnBackPressListener) fragment1).onBackPressed();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackStackChanged() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showLoading() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void setBranch(BranchResponse branch) {
        if (branch != null) {
            if (branch.getData().size() > 0) {

                // delete all previous record
                Realm.getDefaultInstance().beginTransaction();
                Realm.getDefaultInstance().where(Branch.class).findAll().deleteAllFromRealm();
                Realm.getDefaultInstance().commitTransaction();

                // add new record
                getRealm().beginTransaction();
                getRealm().copyToRealmOrUpdate(branch.getData());
                getRealm().commitTransaction();
                if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    txtLocation.setText(SharedPreferencesUtility.getPrefString(this, SharedPreferencesUtility.ACCOUNT_ADDRESS));
                } else {
//                    SharedPreferencesUtility.savePrefString(this,SharedPreferencesUtility.ACCOUNT_ADDRESS,branch.getData().get(0).getBillingStreet());
                    SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.IS_ACCOUNT_THERE, true);
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.ACCOUNT_ID, branch.getData().get(0).getAccountKey());
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.ACCOUNT_ADDRESS, branch.getData().get(0).getBillingStreet());
                    if (branch.getData().get(0).getBillingStreet() != null) {
                        txtLocation.setText(branch.getData().get(0).getBillingStreet());
                    }
                }
                setupNavigationView();
            } else {
                setupNavigationView();
            }
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(this, "Error", message);
    }

    @Override
    public void locationFetched(Location mLocation, Location oldLocation, String time,
                                String locationProvider) {
        this.mLocation = mLocation;
        if (mListner != null) {
            mListner.locationFetched(mLocation, oldLocation, time, locationProvider);
        }
    }

    public Location getmLocation() {
        return mLocation;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {

        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                .setInterval(UPDATE_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (skip == 0) {
            getBranchList(location.getLatitude(), location.getLongitude());
            skip++;
        }
    }
}