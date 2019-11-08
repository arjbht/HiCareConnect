package com.ab.hicarecommercialapp.view.company;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.company.CompanyResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.login.LoginActivity;
import com.ab.hicarecommercialapp.view.login.SelectUserFragment;
import com.tuyenmonkey.mkloader.MKLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyCodeFragment extends BaseFragment implements CompanyView {

    @BindView(R.id.edtCode)
    EditText edtCode;

    @BindView(R.id.btnVerify)
    Button btnVerify;

    @BindView(R.id.loader)
    MKLoader loader;

    public CompanyCodeFragment() {
        // Required empty public constructor
    }

    public static CompanyCodeFragment newInstance() {
        Bundle args = new Bundle();
        CompanyCodeFragment fragment = new CompanyCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_code, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnVerify.setOnClickListener(view1 -> getCodeVerified());
    }

    private void getCodeVerified() {
        if (isValidate(edtCode)) {
            CompanyPresenter presenter = new CompanyPresenter(this);
            presenter.verifyCompanyCode(edtCode.getText().toString());
        }
    }

    private boolean isValidate(EditText edtCode) {
        if (edtCode.getText().length() == 0) {
            edtCode.setError("This field is required!");
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
    public void setCompanyResponse(CompanyResponse response) {
        if (response.getSuccess()) {
            SharedPreferencesUtility.savePrefString(getActivity(), SharedPreferencesUtility.COMPANY_CODE, response.getData().getCompanyCode());
            if (response.getData().getIsSelfRegisteredAllowed() == 1) {
                SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_SELF_REGISTERED, true);
            } else {
                SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_SELF_REGISTERED, false);
            }
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            getActivity().finish();
        } else {
            AppUtils.showDialogMessage(getActivity(), "Error", response.getErrorMessage());
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }
}
