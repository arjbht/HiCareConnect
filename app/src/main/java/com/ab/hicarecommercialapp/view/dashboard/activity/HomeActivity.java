package com.ab.hicarecommercialapp.view.dashboard.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ab.hicarecommercialapp.model.update.UpdateData;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.DownloadApk;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.fragment.EmptyBranchFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.account.AccountFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.BottomSheetFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.HomeFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.ComplaintFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.complaints.CreateComplaintFragment;
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
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.ab.hicarecommercialapp.BaseApplication.getRealm;

public class HomeActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener, BranchView, UpdateAppView, LocationManagerListner, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

//    @BindView(R.id.bottom_navigation)
//    BottomNavigationView bottomNavigation;

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

    @BindView(R.id.lnrGuide)
    LinearLayout lnrGuide;

    @BindView(R.id.imgCancel)
    ImageView imgCancel;

    @BindView(R.id.bottom_navigation2)
    SpaceNavigationView spaceNavigationView;

    RealmResults<Branch> mBranchRealmResults;
    private Location mLocation;
    private LocationManagerListner mListner;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private android.location.LocationManager mLocationManager;
    private long UPDATE_INTERVAL = 10 * 10000;  /* 1 sec */
    private long FASTEST_INTERVAL = 100000; /* 1 sec */
    int skip = 0;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        setSupportActionBar(toolbarView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
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
            SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.SHOW_GUIDE, false);
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            lnrGuide.setVisibility(View.GONE);
        });
//        if (getmLocation() != null) {
//            getBranchList();
//        }


        imgCancel.setOnClickListener(view -> {
            SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.SHOW_GUIDE, false);
            lnrGuide.setVisibility(View.GONE);
        });

        if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.SHOW_GUIDE)) {
            lnrGuide.setVisibility(View.VISIBLE);
        } else {
            lnrGuide.setVisibility(View.GONE);
        }

        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_orders));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_notification));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_user));
        spaceNavigationView.hideAllBadges();


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
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


//    private void setupNavigationView() {
//
//
//        if (bottomNavigation != null) {
//            // Select first menu item by default and show Fragment accordingly.
//            Menu menu = bottomNavigation.getMenu();
//            selectFragment(menu.getItem(0));
//            bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
//                selectFragment(menuItem);
//                return false;
//            });
//        }
//    }

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
        if (fragment < 1) {
            AppUtils.showExitAlert(this);
        } else {
            if (fragment1 instanceof OnBackPressListener) {
                ((OnBackPressListener) fragment1).onBackPressed();
            }
//            spaceNavigationView.changeCurrentItem(0);
            int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntry > 1) {
                for (int i = 0; i < backStackEntry; i++) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
            }
            setUpSpaceNavigationView();
//            replaceFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");
//            bottomNavigation.setSelectedItemId(R.id.nav_home);
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
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.COMPANY_NAME, branch.getData().get(0).getAccountName());
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.ACCOUNT_ID, branch.getData().get(0).getAccountKey());
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.ACCOUNT_ADDRESS, branch.getData().get(0).getBillingStreet());
                    SharedPreferencesUtility.savePrefString(this, SharedPreferencesUtility.ACCOUNT_NAME, branch.getData().get(0).getAccountName());
                    if (branch.getData().get(0).getBillingStreet() != null) {
                        txtLocation.setText(branch.getData().get(0).getBillingStreet());
                    }
                }
                setUpSpaceNavigationView();
//                setupNavigationView();
            } else {
                setUpSpaceNavigationView();
//                setupNavigationView();
            }
        }
    }

    private void setUpSpaceNavigationView() {
        mBranchRealmResults = getRealm().where(Branch.class).findAll();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                replaceFragment(CreateComplaintFragment.newInstance("SOS Complaint", true), "HomeFragment-ComplaintFragment");
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        SharedPreferencesUtility.savePrefBoolean(HomeActivity.this, SharedPreferencesUtility.SHOW_GUIDE, false);
                        lnrGuide.setVisibility(View.GONE);

                        if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                            replaceFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");
                        } else {
                            replaceFragment(EmptyBranchFragment.newInstance("Home"), "HomeActivity-EmptyBranchFragment");
                        }

                        break;
                    case 1:
                        SharedPreferencesUtility.savePrefBoolean(HomeActivity.this, SharedPreferencesUtility.SHOW_GUIDE, false);
                        lnrGuide.setVisibility(View.GONE);
                        if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                            replaceFragment(OrderFragment.newInstance(), "HomeActivity-OrderFragment");
                        } else {
                            replaceFragment(EmptyBranchFragment.newInstance("Orders"), "HomeActivity-EmptyBranchFragment");
                        }

                        break;
                    case 2:
                        SharedPreferencesUtility.savePrefBoolean(HomeActivity.this, SharedPreferencesUtility.SHOW_GUIDE, false);
                        lnrGuide.setVisibility(View.GONE);
                        replaceFragment(NotificationFragment.newInstance(), "HomeActivity-NotificationFragment");

                        break;
                    case 3:
                        SharedPreferencesUtility.savePrefBoolean(HomeActivity.this, SharedPreferencesUtility.SHOW_GUIDE, false);
                        lnrGuide.setVisibility(View.GONE);
                        replaceFragment(AccountFragment.newInstance(), "HomeActivity-AccountFragment");
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

                switch (itemIndex) {
                    case 0:
                        if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                            replaceFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");
                        } else {
                            replaceFragment(EmptyBranchFragment.newInstance("Home"), "HomeActivity-EmptyBranchFragment");
                        }

                        break;
                    case 1:
                        if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                            replaceFragment(OrderFragment.newInstance(), "HomeActivity-OrderFragment");
                        } else {
                            replaceFragment(EmptyBranchFragment.newInstance("Orders"), "HomeActivity-EmptyBranchFragment");
                        }

                        break;
                    case 2:
                        replaceFragment(NotificationFragment.newInstance(), "HomeActivity-NotificationFragment");

                        break;
                    case 3:
                        replaceFragment(AccountFragment.newInstance(), "HomeActivity-AccountFragment");
                        break;
                }
            }
        });
        spaceNavigationView.changeCurrentItem(0);
    }


    @Override
    public void setVersionUpdate(UpdateData response) {
        if (response != null) {
            checkCurrentVersion(response.getApkurl(), response.getVersion(), response.getApktype());
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(this, "Error", message);
    }

    private void checkCurrentVersion(final String apkurl, String version, final String apktype) {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String mobileVersion = pInfo.versionName;
        if (Float.parseFloat(mobileVersion) < Float.parseFloat(version)) {
            String title = "New update available";
            String messageAlert = "<html><body><p>Please update your app to new version.<br><br>Current app version: " + mobileVersion + "<br><br>New version: " + version + "</p></body></html>";
            AppUtils.showDownloadActionAlertBox(HomeActivity.this, title, String.valueOf(Html.fromHtml(messageAlert)), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (AppUtils.checkConnection(HomeActivity.this)) {
                        ProgressDialog progress = new ProgressDialog(HomeActivity.this);
                        if (apktype.equalsIgnoreCase("url")) {
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        } else {
                            DownloadApk downloadAndInstall = new DownloadApk();
                            progress.setCancelable(false);
                            progress.setMessage("Downloading...");
                            downloadAndInstall.setContext(HomeActivity.this, progress);
                            downloadAndInstall.execute(apkurl);
                        }

                    } else {
                        AppUtils.showOkActionAlertBox(HomeActivity.this, "No Internet Found.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                    }
                }
            });

        }
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