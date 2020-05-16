package com.ab.hicarecommercialapp.view.login;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.handler.OtpReceivedInterface;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.utils.AppSignatureHelper;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SMSListener;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.utils.notification.OneSIgnalHelper;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mukesh.OtpView;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseFragment implements LoginView, VerifyUserView, OtpReceivedInterface,  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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
    private static final String ARG_OTP = "ARG_OTP";
    private String mobile = "";
    private String password = "";
    private String otp = "";
    private static final int LOGIN_REQUEST = 1000;
    private SMSListener mSmsBroadcastReceiver;
    private GoogleApiClient mGoogleApiClient;

    public VerifyFragment() {
        // Required empty public constructor
    }

    public static VerifyFragment newInstance(String mobile, String userPassword, String loginOtp) {
        Bundle args = new Bundle();
        args.putString(ARG_MOBILE, mobile);
        args.putString(ARG_PASSWORD, userPassword);
        args.putString(ARG_OTP, loginOtp);
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
            otp = getArguments().getString(ARG_OTP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        ButterKnife.bind(this, view);
        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(getActivity());
        appSignatureHelper.getAppSignatures();
        mSmsBroadcastReceiver = new SMSListener();
        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        mSmsBroadcastReceiver.setOnOtpListeners(VerifyFragment.this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        getActivity().registerReceiver(mSmsBroadcastReceiver, intentFilter);
        startSMSListener();
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

        btnVerify.setOnClickListener(view13 -> {
            if (isOtpVerified()) {
                if (edtOtp.getText().toString().equals(otp)) {
                    getUserVerified();
                } else {
                    Toast.makeText(getActivity(), "Invalid OTP!", Toast.LENGTH_SHORT).show();
                }
            }else {
                startSMSListener();
            }
        });
    }

    private boolean isOtpVerified() {
        if (edtOtp.getText().toString().length() < 4) {
            Toast.makeText(getActivity(), "Invalid OTP!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
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
            otp = response.getData().getLoginOtp();
            startSMSListener();
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

    @Override
    public void onOtpReceived(String otp) {
        Log.e("Otp Received", otp);
        edtOtp.setText(otp);
        if (edtOtp.length() == 4) {
            getUserVerified();
        }
    }

    public void startSMSListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(getActivity());
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(aVoid -> Log.e("TAG_OTP", "SMS Retriever starts"));
        mTask.addOnFailureListener(e -> Log.e("TAG_OTP", "Error"));
    }

    @Override
    public void onOtpTimeout() {
        Log.e("TAG_OTP", "Time out, please resend");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
