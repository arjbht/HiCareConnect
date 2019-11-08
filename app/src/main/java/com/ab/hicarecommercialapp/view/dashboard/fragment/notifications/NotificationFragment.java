package com.ab.hicarecommercialapp.view.dashboard.fragment.notifications;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewNotificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewNotificationAdapter mRecyclerViewNotificationAdapter;


    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Notifications");
        showBackButton(false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mRecyclerViewNotificationAdapter = new RecyclerViewNotificationAdapter(getActivity());
        recyclerView.setAdapter(mRecyclerViewNotificationAdapter);
    }
}
