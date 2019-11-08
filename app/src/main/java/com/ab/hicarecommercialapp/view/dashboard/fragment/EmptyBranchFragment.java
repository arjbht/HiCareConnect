package com.ab.hicarecommercialapp.view.dashboard.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyBranchFragment extends BaseFragment {
    String title = "";
    private static final String ARG_TITLE = "ARG_TITLE";

    public static EmptyBranchFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        EmptyBranchFragment fragment = new EmptyBranchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public EmptyBranchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_empty_branch, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(title);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.VISIBLE);
    }
}
