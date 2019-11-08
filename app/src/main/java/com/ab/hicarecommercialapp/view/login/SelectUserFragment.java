package com.ab.hicarecommercialapp.view.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.view.company.CompanyCodeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectUserFragment extends BaseFragment {
    @BindView(R.id.isLogin)
    Button isLogin;

    @BindView(R.id.isNewUser)
    Button isNewUser;

    public SelectUserFragment() {
        // Required empty public constructor
    }

    public static SelectUserFragment newInstance() {
        Bundle args = new Bundle();
        SelectUserFragment fragment = new SelectUserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_user, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction().remove(CompanyCodeFragment.newInstance()).commit();
        getActivity().getSupportFragmentManager().popBackStack("CompanyCodeFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        isLogin.setOnClickListener(view1 -> replaceFragment(LoginFragment.newInstance(), "SelectUserFragment-LoginFragment"));
        isNewUser.setOnClickListener(view12 -> replaceFragment(SignUpFragment.newInstance(), "SelectUserFragment-SignUpFragment"));
    }
}
