package com.ab.hicarecommercialapp.view.login;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.account.AccountFragment;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ab.hicarecommercialapp.BaseApplication.getRealm;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements VerifyUserView {
    @BindView(R.id.edtMobile)
    EditText edtMobile;

    @BindView(R.id.btnProceed)
    Button btnProceed;

    @BindView(R.id.loader)
    MKLoader loader;

    @BindView(R.id.imgLogo)
    ImageView imgLogo;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String base64String = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_IMAGE);
//        String base64Image = base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgLogo.setImageBitmap(decodedByte);
        btnProceed.setOnClickListener(view1 -> getLogin());
    }

    private void getLogin() {
        if (isValidateUser()) {
            String code = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_CODE);
            VerifyUserPresenter presenter = new VerifyUserPresenter(this);
            presenter.getUserVerified(edtMobile.getText().toString(), code, false);
        }
    }

    private boolean isValidateUser() {
        if (edtMobile.getText().toString().length() == 0) {
            edtMobile.setError("This field is required!");
            return false;
        } else if (edtMobile.getText().toString().length() < 10) {
            edtMobile.setError("Invalid Mobile Number!");
            return false;
        } else {
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
    public void setVerifyUserResponse(VerifyUserResponse response) {
        if (response.getSuccess()) {
            replaceFragment(VerifyFragment.newInstance(edtMobile.getText().toString(), response.getData().getUserPassword()), "LoginFragment-VerifyFragment");
        } else {
            AppUtils.showDialogMessage(getActivity(), "Error ", response.getErrorMessage());
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}
