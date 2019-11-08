package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewComplaintAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewComplaintDetailsAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewComplaintGalleryAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewOrderAdapter;
import com.ab.hicarecommercialapp.model.complaint.Attachment;
import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;
import com.ab.hicarecommercialapp.model.complaint.InteractionLogs;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.TimeUtil;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderDetailsFragment;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintDetailsFragment extends BaseFragment implements ComplaintDetailView, ComplaintInteractionView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.imgComplaint)
    ImageView imgComplaint;

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtTicketId)
    TextView txtTicketId;

    @BindView(R.id.txtDate)
    TextView txtDate;

    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @BindView(R.id.txtStatus)
    TextView txtStatus;

    @BindView(R.id.loader)
    MKLoader loader;

    @BindView(R.id.recycleGallery)
    RecyclerView recyclerGallery;

    List<Attachment> imgList = null;

    private RecyclerViewComplaintGalleryAdapter galleryAdapter;

    private static final String ARG_COMPLAINT = "ARG_COMPLAINT";
    private int complaintId;
    private RecyclerViewComplaintDetailsAdapter mAdapter;


    public ComplaintDetailsFragment() {
        // Required empty public constructor
    }

    public static ComplaintDetailsFragment newInstance(Integer id) {
        Bundle args = new Bundle();
        args.putInt(ARG_COMPLAINT, id);
        ComplaintDetailsFragment fragment = new ComplaintDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            complaintId = getArguments().getInt(ARG_COMPLAINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_details, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Complaint Details");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.GONE);

        mAdapter =
                new RecyclerViewComplaintDetailsAdapter(getActivity());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setClipToPadding(false);
        recycleView.setAdapter(mAdapter);

        galleryAdapter = new RecyclerViewComplaintGalleryAdapter(getActivity());
        recyclerGallery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerGallery.setHasFixedSize(true);
        recyclerGallery.setClipToPadding(false);
        recyclerGallery.setAdapter(galleryAdapter);


        getComplaintsById();
        getInteractions();
    }

    private void getInteractions() {
        ComplaintInteractionPresenter presenter = new ComplaintInteractionPresenter(this);
        presenter.getComplaintInteractions(complaintId);
    }

    private void getComplaintsById() {
        ComplaintDetailPresenter presenter = new ComplaintDetailPresenter(this);
        presenter.getComplaintById(complaintId);
    }


    @Override
    public void showLoading() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void setInteractions(List<InteractionLogs> complaints) {
        if (complaints != null && complaints.size() > 0) {
            mAdapter.setData(complaints);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setComplaintDetail(ComplaintDetailResponse complaints) {
        Complaints response = complaints.getData();
        imgList = new ArrayList<>();
//        if (response.getAttachmentUrl() != null && response.getAttachmentUrl().length() > 0) {
//            Picasso.get().load(response.getAttachmentUrl()).error(R.drawable.ic_image).into(imgComplaint);
//        }
        if (response.getTitle() != null && response.getTitle().length() > 0) {
            txtTitle.setText(response.getTitle());
        }

        if (response.getStatus() != null && response.getStatus().length() > 0) {
            txtStatus.setText(response.getStatus());
        } else {
            txtStatus.setText("N/D");
        }

        if (response.getRemark() != null && response.getRemark().length() > 0) {
            txtDescription.setText(response.getRemark());
        }

        if (response.getCreatedonText() != null && response.getCreatedonText().length() > 0) {
            String mDate = null;
            try {
                mDate = TimeUtil.reFormatDateTime(response.getCreatedonText(), "MMM dd, yyyy");
                txtDate.setText(mDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            txtDate.setText("N/D");
        }
        txtTicketId.setText(response.getFreshdeskTicketId());
        imgList = response.getAttachmentList();
        if (response.getAttachmentList() != null && response.getAttachmentList().size() > 0) {
            galleryAdapter.setData(response.getAttachmentList());
            galleryAdapter.notifyDataSetChanged();
            Picasso.get().load(response.getAttachmentList().get(0).getAttachmentUrl()).into(imgComplaint);
            galleryAdapter.setOnImageClickListener((view, position) -> {
                Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                for (int i = 0; i <= response.getAttachmentList().size(); i++) {
                    if (view.getBackground() != null) {
                        view.setBackground(null);
                    }
                }
                view.setBackground(highlight);
                Picasso.get().load(response.getAttachmentList().get(position).getAttachmentUrl()).into(imgComplaint);
            });
        }

    }


    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error ", message);
    }
}
