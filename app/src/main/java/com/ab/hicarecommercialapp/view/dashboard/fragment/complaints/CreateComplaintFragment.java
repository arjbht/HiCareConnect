package com.ab.hicarecommercialapp.view.dashboard.fragment.complaints;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.complaint.Attachment;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentRequest;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.utils.AppUtils;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateComplaintFragment extends BaseFragment implements ComplaintTypeView, ComplaintAttachmentView, CreateComplaintView {

    @BindView(R.id.spnType)
    Spinner spnType;

    @BindView(R.id.edtTitle)
    EditText edtTitle;

    @BindView(R.id.edtComment)
    EditText edtComment;

    @BindView(R.id.btnComplaint)
    Button btnComplaint;

    @BindView(R.id.loader)
    MKLoader loader;

    @BindView(R.id.lnrImage1)
    LinearLayout lnrImage1;

    @BindView(R.id.lnrImage2)
    LinearLayout lnrImage2;

    @BindView(R.id.lnrImage3)
    LinearLayout lnrImage3;

    @BindView(R.id.lnrImage4)
    LinearLayout lnrImage4;

    @BindView(R.id.lnrImage5)
    LinearLayout lnrImage5;

    @BindView(R.id.imgAdd1)
    ImageView imgAdd1;

    @BindView(R.id.imgAdd2)
    ImageView imgAdd2;

    @BindView(R.id.imgAdd3)
    ImageView imgAdd3;

    @BindView(R.id.imgAdd4)
    ImageView imgAdd4;

    @BindView(R.id.imgAdd5)
    ImageView imgAdd5;

    @BindView(R.id.imgPhoto1)
    ImageView imgPhoto1;

    @BindView(R.id.imgPhoto2)
    ImageView imgPhoto2;

    @BindView(R.id.imgPhoto3)
    ImageView imgPhoto3;

    @BindView(R.id.imgPhoto4)
    ImageView imgPhoto4;

    @BindView(R.id.imgPhoto5)
    ImageView imgPhoto5;

    @BindView(R.id.cardPhoto1)
    CardView cardPhoto1;

    @BindView(R.id.cardPhoto2)
    CardView cardPhoto2;

    @BindView(R.id.cardPhoto3)
    CardView cardPhoto3;

    @BindView(R.id.cardPhoto4)
    CardView cardPhoto4;

    @BindView(R.id.cardPhoto5)
    CardView cardPhoto5;

    @BindView(R.id.imageCancel1)
    ImageButton imageCancel1;

    @BindView(R.id.imageCancel2)
    ImageButton imageCancel2;

    @BindView(R.id.imageCancel3)
    ImageButton imageCancel3;

    @BindView(R.id.imageCancel4)
    ImageButton imageCancel4;

    @BindView(R.id.imageCancel5)
    ImageButton imageCancel5;

    private ArrayList<Attachment> imageItem = null;

    private ArrayList<String> urlList = null;


    private String[] arrayStatus = null;
    private String Type = "";
    RealmResults<Branch> mBranchRealmResults;

    private String Attachment = "";
    private String selectedImagePath = "";
    private Bitmap bitmap;
    private File imgFile;
    String encodedImage = "";

    public CreateComplaintFragment() {
        // Required empty public constructor
    }

    public static CreateComplaintFragment newInstance() {
        Bundle args = new Bundle();
        CreateComplaintFragment fragment = new CreateComplaintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_complaint, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Lodge Complaint");
        showBackButton(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnComplaint.setOnClickListener(view12 -> createComplaint());
        imageItem = new ArrayList<>();
        urlList = new ArrayList<>();
        lnrImage1.setOnClickListener(view1 -> showFileChooser(1));
        lnrImage2.setOnClickListener(view1 -> showFileChooser(2));
        lnrImage3.setOnClickListener(view1 -> showFileChooser(3));
        lnrImage4.setOnClickListener(view1 -> showFileChooser(4));
        lnrImage5.setOnClickListener(view1 -> showFileChooser(5));

        imageCancel1.setOnClickListener(view13 -> {
            cardPhoto1.setVisibility(View.GONE);
            imgAdd1.setVisibility(View.VISIBLE);
            imageCancel1.setVisibility(View.GONE);
            lnrImage1.setEnabled(true);
        });
        imageCancel2.setOnClickListener(view14 -> {
            cardPhoto2.setVisibility(View.GONE);
            imgAdd2.setVisibility(View.VISIBLE);
            imageCancel2.setVisibility(View.GONE);
            lnrImage2.setEnabled(true);
        });
        imageCancel3.setOnClickListener(view14 -> {
            cardPhoto3.setVisibility(View.GONE);
            imgAdd3.setVisibility(View.VISIBLE);
            imageCancel3.setVisibility(View.GONE);
            lnrImage3.setEnabled(true);
        });
        imageCancel4.setOnClickListener(view14 -> {
            cardPhoto4.setVisibility(View.GONE);
            imgAdd4.setVisibility(View.VISIBLE);
            imageCancel4.setVisibility(View.GONE);
            lnrImage4.setEnabled(true);
        });
        imageCancel5.setOnClickListener(view14 -> {
            cardPhoto5.setVisibility(View.GONE);
            imgAdd5.setVisibility(View.VISIBLE);
            imageCancel5.setVisibility(View.GONE);
            lnrImage5.setEnabled(true);
        });
        getComplaintType();
    }

    private void createComplaint() {
        if (isValidate()) {
            if ((HomeActivity) getActivity() != null) {
                RealmResults<LoginResponse> mloginRealmModel =
                        BaseApplication.getRealm().where(LoginResponse.class).findAll();

                if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                    Integer userId = Integer.parseInt(mloginRealmModel.get(0).getUserId());
                    String mobile = mloginRealmModel.get(0).getMobile();
                    String userName = mloginRealmModel.get(0).getName();
                    String email = mloginRealmModel.get(0).getEmail();
                    boolean isAdmin = Boolean.parseBoolean(mloginRealmModel.get(0).getIsAdmin());
                    if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                        String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                        String companyName = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.COMPANY_NAME);
                        CreateComplaintRequest request = new CreateComplaintRequest();
                        request.setAttachmentList(imageItem);
                        request.setAccountNo(accountNo);
                        request.setTitle(edtTitle.getText().toString());
                        request.setRemarks(edtComment.getText().toString());
                        request.setComplaintId(0);
                        request.setUserId(userId);
                        request.setComplaintType(Type);
                        request.setAttachment("");
                        request.setAccountName(companyName);
                        request.setUserName(userName);
                        request.setStatus("Open");
                        request.setMobile(mobile);
                        request.setEmail(email);
                        request.setFreshdeskTicketId("");
                        CreateComplaintPresenter presenter = new CreateComplaintPresenter(this);
                        presenter.getCreateComplaint(request);
                    } else {
                        mBranchRealmResults = getRealm().where(Branch.class).findAll();
                        if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                            String accountNo = mBranchRealmResults.get(0).getAccountKey();
                            String companyName = mBranchRealmResults.get(0).getAccountName();
                            CreateComplaintRequest request = new CreateComplaintRequest();
                            request.setAttachmentList(imageItem);
                            request.setAccountNo(accountNo);
                            request.setTitle(edtTitle.getText().toString());
                            request.setRemarks(edtComment.getText().toString());
                            request.setComplaintId(0);
                            request.setUserId(userId);
                            request.setComplaintType(Type);
                            request.setAttachment("");
                            request.setAccountName(companyName);
                            request.setUserName(userName);
                            request.setStatus("Open");
                            request.setMobile(mobile);
                            request.setEmail(email);
                            request.setFreshdeskTicketId("");
                            CreateComplaintPresenter presenter = new CreateComplaintPresenter(this);
                            presenter.getCreateComplaint(request);

                        }
                    }
                }
            }
        }
    }

    private boolean isValidate() {
        if (edtTitle.getText().toString().length() == 0) {
            edtTitle.setError("This field is required!");
            return false;
        } else if (edtComment.getText().toString().length() == 0) {
            edtComment.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }

    private void getComplaintType() {
        ComplaintTypePresenter presenter = new ComplaintTypePresenter(this);
        presenter.getComplaintType();
    }

    private void showFileChooser(int imagePosition) {

        PickImageDialog.build(new PickSetup()).setOnPickResult(pickResult -> {
            if (pickResult.getError() == null) {
                imgFile = new File(pickResult.getPath());
                selectedImagePath = pickResult.getPath();
                if (selectedImagePath != null) {
                    Bitmap bit = new BitmapDrawable(getActivity().getResources(),
                            selectedImagePath).getBitmap();
                    int i = (int) (bit.getHeight() * (1024.0 / bit.getWidth()));
                    bitmap = Bitmap.createScaledBitmap(bit, 1024, i, true);
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                switch (imagePosition) {
                    case 1:
                        imgAdd1.setVisibility(View.GONE);
                        cardPhoto1.setVisibility(View.VISIBLE);
                        imgPhoto1.setImageBitmap(bitmap);
                        imageCancel1.setVisibility(View.VISIBLE);
                        lnrImage1.setEnabled(false);
                        getUploadAttachment();
                        break;

                    case 2:
                        imgAdd2.setVisibility(View.GONE);
                        cardPhoto2.setVisibility(View.VISIBLE);
                        imgPhoto2.setImageBitmap(bitmap);
                        imageCancel2.setVisibility(View.VISIBLE);
                        lnrImage2.setEnabled(false);
                        getUploadAttachment();
                        break;

                    case 3:
                        imgAdd3.setVisibility(View.GONE);
                        cardPhoto3.setVisibility(View.VISIBLE);
                        imgPhoto3.setImageBitmap(bitmap);
                        imageCancel3.setVisibility(View.VISIBLE);
                        lnrImage3.setEnabled(false);
                        getUploadAttachment();
                        break;

                    case 4:
                        imgAdd4.setVisibility(View.GONE);
                        cardPhoto4.setVisibility(View.VISIBLE);
                        imgPhoto4.setImageBitmap(bitmap);
                        imageCancel4.setVisibility(View.VISIBLE);
                        lnrImage4.setEnabled(false);
                        getUploadAttachment();
                        break;

                    case 5:
                        imgAdd5.setVisibility(View.GONE);
                        cardPhoto5.setVisibility(View.VISIBLE);
                        imgPhoto5.setImageBitmap(bitmap);
                        imageCancel5.setVisibility(View.VISIBLE);
                        lnrImage5.setEnabled(false);
                        getUploadAttachment();
                        break;

                    default:
                        imgPhoto1.setVisibility(View.GONE);
                        imgPhoto2.setVisibility(View.GONE);
                        imgPhoto3.setVisibility(View.GONE);
                        imgPhoto4.setVisibility(View.GONE);
                        imgPhoto5.setVisibility(View.GONE);
                        break;
                }
            }
        }).show(getActivity());

    }

    private void getUploadAttachment() {
        ComplaintattachmentPresenter presenter = new ComplaintattachmentPresenter(this);
        ComplaintAttachmentRequest request = new ComplaintAttachmentRequest();
        request.setAttachment(encodedImage);
        request.setAttachmentUrl("");
        request.setComplaintId(0);
        request.setFileName(selectedImagePath);
        request.setId(0);
        presenter.getAttachment(request);
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
    public void setAttachment(ComplaintAttachmentResponse response) {
        if (response.getSuccess()) {
            imageItem.add(response.getData());
        }
    }

    @Override
    public void setCreateComplaint(CreateComplaintResponse response) {
        if (response.getIsSuccess()) {
            imageItem.clear();
            urlList.clear();
            complaintSuccessDialog();
        }
    }

    private void complaintSuccessDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.layout_complaint_dialog, null);
        Button cancel = (Button) dialogview.findViewById(R.id.dialog_cancel);
        builder.setView(dialogview);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();

        cancel.setOnClickListener(view -> {
            dialog.cancel();
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    @Override
    public void setComplaintType(List<ComplaintType> types) {
        if (types != null && types.size() > 0) {
            List<String> list = new ArrayList<>();
            for (ComplaintType type : types) {
                list.add(type.getType());
            }
            arrayStatus = new String[list.size()];
            arrayStatus = list.toArray(arrayStatus);
            ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.layout_spinner, arrayStatus);
            statusAdapter.setDropDownViewResource(R.layout.layout_spinner_popup);
            spnType.setAdapter(statusAdapter);
            spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    Type = spnType.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    @Override
    public void onErrorLoading(String message) {
        AppUtils.showDialogMessage(getActivity(), "Error", message);
    }
}
