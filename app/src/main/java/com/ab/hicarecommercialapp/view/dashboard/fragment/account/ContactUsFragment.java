package com.ab.hicarecommercialapp.view.dashboard.fragment.account;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Contact Us");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation2).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.GONE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://hicare.in/contact-info");
    }
}
