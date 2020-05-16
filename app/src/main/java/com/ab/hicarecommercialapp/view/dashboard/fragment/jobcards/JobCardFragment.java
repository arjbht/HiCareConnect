package com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewJobCardAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobCardFragment extends BaseFragment {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    private RecyclerViewJobCardAdapter mJobCardAdapter;
    private RecyclerView.LayoutManager mLinearLayout;

    public JobCardFragment() {
        // Required empty public constructor
    }

    public static JobCardFragment newInstance() {
        Bundle args = new Bundle();
        JobCardFragment fragment = new JobCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_card, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Get an Expert");
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
        recyclerView.setHasFixedSize(true);
        mLinearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayout);
        mJobCardAdapter = new RecyclerViewJobCardAdapter(getActivity());
        recyclerView.setAdapter(mJobCardAdapter);
        mJobCardAdapter.setOnMenuItemClickListener((view1, position) -> {
            PopupMenu popup = new PopupMenu(getActivity(), view1);
            popup.inflate(R.menu.jobcard_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.idView:
                        break;
                    case R.id.idDownload:
                        break;
                }
                return false;
            });
            //displaying the popup
            popup.show();

        });
    }
}
