package com.ab.hicarecommercialapp.view.login;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.location_service.listner.LocationManagerListner;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterRequest;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.utils.notification.OneSIgnalHelper;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment implements SignUpView, LoginView, LocationManagerListner {
    @BindView(R.id.edtFName)
    EditText edtFName;

    @BindView(R.id.edtLName)
    EditText edtLName;

    @BindView(R.id.edtMobile)
    EditText edtMobile;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @BindView(R.id.loader)
    MKLoader loader;

    private String versionNo = "";

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((LoginActivity) getActivity()).getmLocation() != null) {
            btnSignUp.setOnClickListener(view1 -> getSignUp());
        } else {
            ((LoginActivity) getActivity()).showLocationPopUp();
            ((LoginActivity) getActivity()).setLocationListner(this);
        }

    }

    private void getSignUp() {
        if (isValidated()) {
            TelephonyManager tele = (TelephonyManager) getActivity().getSystemService(getActivity().TELEPHONY_SERVICE);
            try {
                PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                versionNo = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            RegisterRequest request = new RegisterRequest();
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            request.setIMEINo(tele.getDeviceId());
            request.setAppVersion(versionNo);
            request.setCompanyCode(SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_CODE));
            request.setDeviceInfo(Build.DEVICE);
            OneSIgnalHelper oneSIgnalHelper = new OneSIgnalHelper(getActivity());
            request.setPlayerId(oneSIgnalHelper.getmStrUserID());
            request.setDeviceName(Build.MODEL);
            request.setEmail(edtEmail.getText().toString());
            request.setMobile(edtMobile.getText().toString());
            request.setFirstName(edtFName.getText().toString());
            request.setLastName(edtLName.getText().toString());
            request.setUser(edtMobile.getText().toString());
            request.setLatitude(((LoginActivity) getActivity()).getmLocation().getLatitude());
            request.setLongitude(((LoginActivity) getActivity()).getmLocation().getLongitude());
            SignUpPresenter presenter = new SignUpPresenter(this);
            presenter.getUserRegistered(request);
        }
    }

    private boolean isValidated() {
        if (edtFName.getText().toString().length() == 0) {
            edtFName.setError("This field is required!");
            return false;
        } else if (edtLName.getText().toString().length() == 0) {
            edtLName.setError("This field is required!");
            return false;
        }  else if (edtMobile.getText().toString().length() == 0) {
            edtMobile.setError("This field is required!");
            return false;
        } else if (edtMobile.getText().toString().length() < 10) {
            edtMobile.setError("Mobile number is invalid!");
            return false;
        }else if (edtEmail.getText().toString().length()>0){
            if(!edtEmail.getText().toString().trim().matches(emailPattern)){
                return false;
            }else {
                return true;
            }
        }
        else {
            return true;
        }
    }

    @Override
    public void showLoading() {
//        AppUtils.showProgressDialog(getActivity());
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        AppUtils.dismissProgressDialog();
        loader.setVisibility(View.GONE);

    }

    @Override
    public void setLoginResponse(LoginResponse response) {
        if (response != null) {
            getRealm().beginTransaction();
            getRealm().deleteAll();
            getRealm().commitTransaction();
            // add new record
            getRealm().beginTransaction();
            getRealm().copyToRealmOrUpdate(response);
            getRealm().commitTransaction();
            SharedPreferencesUtility.savePrefBoolean(getActivity(),
                    SharedPreferencesUtility.IS_USER_LOGIN, true);
            startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            getActivity().finish();
        }
    }

    @Override
    public void setRegisterResponse(RegisterResponse response) {
        if (response.getSuccess()) {
            String mobile = response.getData().getMobileNo();
            String password = response.getData().getUserPassword();
            getRegistered(mobile, password);
        } else {
            AppUtils.showDialogMessage(getActivity(), "Error", response.getErrorMessage());
        }
    }

    private void getRegistered(String mobile, String password) {
        PackageInfo pinfo = null;
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = pinfo.versionName;
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String device_info = Build.MODEL;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String imei = telephonyManager.getDeviceId();
        OneSIgnalHelper oneSIgnalHelper = new OneSIgnalHelper(getActivity());
        String mStrPlayerId = oneSIgnalHelper.getmStrUserID();
        String companyCode = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_CODE);
        LoginPresenter presenter = new LoginPresenter(this);
        presenter.getLogin(mobile, password, versionName, imei, device_info, mStrPlayerId, companyCode);
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }

    @Override
    public void locationFetched(Location mLocation, Location oldLocation, String time, String locationProvider) {
        ((LoginActivity) getActivity()).setLocationListner(null);
    }
}
