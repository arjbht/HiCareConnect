package com.ab.hicarecommercialapp.view.login;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.utils.notification.OneSIgnalHelper;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.mukesh.OtpView;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseFragment implements LoginView, VerifyUserView {

    @BindView(R.id.otp_view)
    OtpView edtOtp;

    @BindView(R.id.txtResend)
    TextView txtResend;

    @BindView(R.id.txtMobileConfirm)
    TextView txtMobileConfirm;

    @BindView(R.id.btnVerify)
    Button btnVerify;

    @BindView(R.id.loader)
    MKLoader loader;

    private static final String ARG_MOBILE = "ARG_MOBILE";
    private static final String ARG_PASSWORD = "ARG_PASSWORD";
    private String mobile = "";
    private String password = "";
    private static final int LOGIN_REQUEST = 1000;

    public VerifyFragment() {
        // Required empty public constructor
    }

    public static VerifyFragment newInstance(String mobile, String userPassword) {
        Bundle args = new Bundle();
        args.putString(ARG_MOBILE, mobile);
        args.putString(ARG_PASSWORD, userPassword);
        VerifyFragment fragment = new VerifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mobile = getArguments().getString(ARG_MOBILE);
            password = getArguments().getString(ARG_PASSWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String resend = "<font color=#4682B4>Didn't receive the verification OTP?</font> <font color=#4682B4>Resend again</font>";
        txtResend.setText(Html.fromHtml(resend));
        txtMobileConfirm.setText("Enter the 4 digit OTP that was sent to " + mobile);
        String code = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_CODE);
        txtResend.setOnClickListener(view1 -> {
            VerifyUserPresenter presenter = new VerifyUserPresenter(this);
            presenter.getUserVerified(mobile, code, true);
        });

        btnVerify.setOnClickListener(view12 -> getUserVerified());
    }

    private void getUserVerified() {
        String otp = edtOtp.getText().toString();
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
    public void setVerifyUserResponse(VerifyUserResponse response) {
        if (response.getSuccess()) {

        }
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
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }
}
