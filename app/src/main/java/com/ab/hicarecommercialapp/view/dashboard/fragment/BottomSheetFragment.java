package com.ab.hicarecommercialapp.view.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewLocationAdapter;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.utils.MyDividerItemDecoration;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

import static com.ab.hicarecommercialapp.BaseApplication.getRealm;

/**
 * Created by Arjun Bhatt on 9/23/2019.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.recycleLocation)
    RecyclerView recyclerView;

    @BindView(R.id.imgClose)
    ImageView imgClose;

    @BindView(R.id.lnrClose)
    LinearLayout lnrClose;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewLocationAdapter adapter;
    private RealmResults<Branch> branchRealmListResults;
    private TextView txtLocation;

    public BottomSheetFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_location_bottom_sheet, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtLocation = getActivity().findViewById(R.id.txtLocation);
        imgClose.setOnClickListener(view1 -> dismiss());
        lnrClose.setOnClickListener(view12 -> dismiss());
        adapter = new RecyclerViewLocationAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0));
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        branchRealmListResults = getRealm().where(Branch.class).findAll();
        getBranchList();

    }

    private void getBranchList() {

        if (branchRealmListResults != null) {
            if (branchRealmListResults.size() > 0) {
                adapter.setData(branchRealmListResults);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "No Branch Available!", Toast.LENGTH_SHORT).show();
            }
        }
        adapter.setOnItemClickListener((view, position) -> {
            SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE, true);
            SharedPreferencesUtility.savePrefString(getActivity(),
                    SharedPreferencesUtility.ACCOUNT_ID,
                    branchRealmListResults.get(position).getAccountKey());
            SharedPreferencesUtility.savePrefString(getActivity(),
                    SharedPreferencesUtility.ACCOUNT_ADDRESS,
                    branchRealmListResults.get(position).getBillingStreet());
            SharedPreferencesUtility.savePrefString(getActivity(), SharedPreferencesUtility.COMPANY_NAME, branchRealmListResults.get(position).getAccountName());
            txtLocation.setText(branchRealmListResults.get(position).getBillingStreet());
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .detach(HomeFragment.newInstance())
                    .replace(R.id.container, HomeFragment.newInstance(), "BottomSheetFragment-HomeFragment")
                    .commit();
            dismiss();
        });
    }

}
