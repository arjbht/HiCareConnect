package com.ab.hicarecommercialapp.view.dashboard.fragment.audits;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuditViewPdfFragment extends BaseFragment {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    private static final String ARG_URL = "ARG_URL";
    private String uri = "";

    public AuditViewPdfFragment() {
        // Required empty public constructor
    }

    public static AuditViewPdfFragment newInstance(String pdfReport) {
        Bundle args = new Bundle();
        args.putString(ARG_URL, pdfReport);
        AuditViewPdfFragment fragment = new AuditViewPdfFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uri = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audit_view_pdf, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Audit Report");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pdfView.fromUri(Uri.parse("http://b2bauditapp.hicare.in/reports/227341573047062.pdf"));

    }
}
