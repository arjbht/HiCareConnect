package com.ab.hicarecommercialapp.view.login;

import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.branch.BranchResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.service.Service_Details;

import java.util.List;

/**
 * Created by Arjun Bhatt on 10/16/2019.
 */
public interface BranchView {
    void showLoading();
    void hideLoading();
    void setBranch(BranchResponse branch);
    void onErrorLoading(String message);
}
