package com.ab.hicarecommercialapp.view.dashboard.fragment.account;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.view.company.CompanyCodeActivity;
import com.ab.hicarecommercialapp.view.company.CompanyCodeFragment;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.audits.AuditFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.jobcards.JobCardFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.notifications.NotificationFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.orders.OrderFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.services.ServiceHistoryFragment;
import com.ab.hicarecommercialapp.view.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment {

    @BindView(R.id.idOrders)
    ConstraintLayout idOrders;

    @BindView(R.id.idAudits)
    ConstraintLayout idAudits;

    @BindView(R.id.idServiceHistory)
    ConstraintLayout idServiceHistory;

    @BindView(R.id.idNotifications)
    ConstraintLayout idNotifications;

    @BindView(R.id.lnrLogout)
    LinearLayout lnrLogout;

    @BindView(R.id.lnrFeedback)
    ConstraintLayout lnrFeedback;

    @BindView(R.id.lnrRating)
    ConstraintLayout lnrRating;

    @BindView(R.id.lnrFaq)
    ConstraintLayout lnrFaq;

    @BindView(R.id.lnrAbout)
    ConstraintLayout lnrAbout;

    @BindView(R.id.lnrContact)
    ConstraintLayout lnrContact;

    @BindView(R.id.lnrPolicy)
    ConstraintLayout lnrPolicy;

    @BindView(R.id.lnrConditions)
    ConstraintLayout lnrConditions;

    @BindView(R.id.txtThumbnail)
    TextView txtThumbnail;

    @BindView(R.id.txt_username)
    TextView txtUsername;

    @BindView(R.id.txt_userdetails)
    TextView txtUserDetails;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Account");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((GradientDrawable) txtThumbnail.getBackground()).setColor(Color.parseColor("#4682B4"));
        getActivity().findViewById(R.id.cToolbar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.navigationBorder).setVisibility(View.VISIBLE);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bottom_navigation);
        Menu menu = navigationView.getMenu();

        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                String name = mloginRealmModel.get(0).getName();
                String mobile = mloginRealmModel.get(0).getMobile();
                String email = mloginRealmModel.get(0).getEmail();
                char first = name.charAt(0);
                txtThumbnail.setText(String.valueOf(first));
                txtUsername.setText(name);
                txtUserDetails.setText(mobile + " | " + email);

            }
        }


        idOrders.setOnClickListener(view1 -> {
            menu.getItem(1).setChecked(true);
            replaceFragment(OrderFragment.newInstance(), "AccountFragment-OrderFragment");
        });


        idAudits.setOnClickListener(view1 -> {
            menu.getItem(1).setChecked(true);
            replaceFragment(AuditFragment.newInstance(), "AuditFragment-OrderFragment");
        });

        idNotifications.setOnClickListener(view1 -> {
            menu.getItem(2).setChecked(true);
            replaceFragment(NotificationFragment.newInstance(), "AccountFragment-OrderFragment");
        });

        lnrFeedback.setOnClickListener(view13 -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@hicare.in"});
            i.putExtra(Intent.EXTRA_SUBJECT, "HiCare Android App Feedback");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

        lnrRating.setOnClickListener(view14 -> {
            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
            }
        });
        lnrPolicy.setOnClickListener(view15 -> replaceFragment(PrivacyPolicyFragment.newInstance(), "AccountFragment-PrivacyPolicyFragment"));

        lnrFaq.setOnClickListener(view16 -> replaceFragment(FaqFragment.newInstance(), "AccountFragment-FaqFragment"));

        lnrAbout.setOnClickListener(view17 -> replaceFragment(AboutUsFragment.newInstance(), "AccountFragment-AboutUsFragment"));

        lnrContact.setOnClickListener(view18 -> replaceFragment(ContactUsFragment.newInstance(), "AccountFragment-ContactUsFragment"));

        lnrConditions.setOnClickListener(view19 -> replaceFragment(TermsAndConditionsFragment.newInstance(), "AccountFragment-TermsAndConditionsFragment"));

        idServiceHistory.setOnClickListener(view1 -> replaceFragment(ServiceHistoryFragment.newInstance(), "AccountFragment-ServiceHistoryFragment"));

        lnrLogout.setOnClickListener(view12 -> {
            SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_USER_LOGIN,
                    false);
            SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE, false);
            getActivity().startActivity(new Intent(getActivity(), CompanyCodeActivity.class));
            getActivity().finish();
        });
    }
}
