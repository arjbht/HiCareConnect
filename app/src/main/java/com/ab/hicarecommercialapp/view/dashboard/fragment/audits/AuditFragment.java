package com.ab.hicarecommercialapp.view.dashboard.fragment.audits;


import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ab.hicarecommercialapp.BaseApplication;
import com.ab.hicarecommercialapp.BaseFragment;
import com.ab.hicarecommercialapp.BuildConfig;
import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.adapter.RecyclerViewAuditAdapter;
import com.ab.hicarecommercialapp.adapter.RecyclerViewInvoiceAdapter;
import com.ab.hicarecommercialapp.model.audit.Audit;
import com.ab.hicarecommercialapp.model.branch.Branch;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.utils.PdfDownloader;
import com.ab.hicarecommercialapp.utils.SharedPreferencesUtility;
import com.ab.hicarecommercialapp.utils.notification.NLService;
import com.ab.hicarecommercialapp.view.dashboard.activity.HomeActivity;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceDetailsFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoiceFragment;
import com.ab.hicarecommercialapp.view.dashboard.fragment.invoices.InvoicePresenter;
import com.squareup.picasso.Downloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuditFragment extends BaseFragment implements AuditView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.shimmerAudit)
    LinearLayout shimmerAudit;

    @BindView(R.id.emptyBox)
    LinearLayout emptyTask;

    private Integer pageNumber = 0;
    int id = 1;
    private RecyclerViewAuditAdapter adapter;
    RealmResults<Branch> mBranchRealmResults;
    private static final String TAG = "AuditFragment";
    //    ArrayList<AsyncTask<String, String, Void>> arr;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyManager;
    private NotificationReceiver nReceiver;
    int counter = 0;

    public AuditFragment() {
        // Required empty public constructor
    }

    public static AuditFragment newInstance() {
        Bundle args = new Bundle();
        AuditFragment fragment = new AuditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String event = intent.getExtras().getString(NLService.NOT_EVENT_KEY);
            Log.i("NotificationReceiver", "NotificationReceiver onReceive : " + event);
            if (event.trim().contentEquals(NLService.NOT_REMOVED)) {
//                killTasks();
            }
        }
    }

//    private void killTasks() {
//        if (null != arr & arr.size() > 0) {
//            for (AsyncTask<String, String, Void> a : arr) {
//                if (a != null) {
//                    Log.i("NotificationReceiver", "Killing download thread");
//                    a.cancel(true);
//                }
//            }
//            mNotifyManager.cancelAll();
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audit, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Audits");
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

        adapter =
                new RecyclerViewAuditAdapter(getActivity());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setClipToPadding(false);
        recycleView.setAdapter(adapter);
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && isLastItemDisplaying(recycleView)) {
                    pageNumber += 10;
                    progressBar.setVisibility(View.VISIBLE);
                    getCustomerAudits();
                }
            }
        });
        getCustomerAudits();

    }


    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION
                    && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void getCustomerAudits() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> mloginRealmModel =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (mloginRealmModel != null && mloginRealmModel.size() > 0) {
                mBranchRealmResults = getRealm().where(Branch.class).findAll();
                if (SharedPreferencesUtility.getPrefBoolean(getActivity(), SharedPreferencesUtility.IS_ACCOUNT_THERE)) {
                    String accountNo = SharedPreferencesUtility.getPrefString(getActivity(), SharedPreferencesUtility.ACCOUNT_ID);
                    AuditPresenter presenter = new AuditPresenter(this);
                    presenter.getCustomerAudit(accountNo, "", "");
                } else {
                    if (mBranchRealmResults != null && mBranchRealmResults.size() > 0) {
                        String accountNo = mBranchRealmResults.get(0).getAccountKey();
                        AuditPresenter presenter = new AuditPresenter(this);
                        presenter.getCustomerAudit(accountNo, "", "");
                    }
                }
            }
        }

    }

    @Override
    public void showLoading() {
        shimmerAudit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerAudit.setVisibility(View.GONE);
    }

    @Override
    public void setAudits(List<Audit> audits) {
        progressBar.setVisibility(View.GONE);
        if (audits != null) {
            if (pageNumber == 0 && audits.size() > 0) {
                adapter.setData(audits);
                adapter.notifyDataSetChanged();
                emptyTask.setVisibility(View.GONE);
            } else if (audits.size() > 0) {
                adapter.addData(audits);
                adapter.notifyDataSetChanged();
                emptyTask.setVisibility(View.GONE);
            } else {
                pageNumber -= 10;
                emptyTask.setVisibility(View.VISIBLE);
            }
        } else {
            emptyTask.setVisibility(View.VISIBLE);
        }

        adapter.setOnMenuItemClickListener((view1, position) -> {
            PopupMenu popup = new PopupMenu(getActivity(), view1);
            popup.inflate(R.menu.jobcard_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.idView:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(audits.get(position).getPdfReport()));
                        startActivity(browserIntent);
//                        replaceFragment(AuditViewPdfFragment.newInstance(audits.get(position).getPdfReport()), "AuditFragment-AuditViewPdfFragment");
//                        viewPDF();

                        break;
                    case R.id.idDownload:
//                        String extStorageDirectory = Environment.getExternalStorageDirectory()
//                                .toString();
//                        File folder = new File(extStorageDirectory, "pdf");
//                        folder.mkdir();
//                        File file = new File(folder, "Read.pdf");
//                        try {
//                            file.createNewFile();
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                        PdfDownloader.DownloadFile(audits.get(position).getPdfReport(), file);
                        downloadPDF(audits.get(position).getPdfReport());
                        break;
                }
                return false;
            });
            //displaying the popup
            popup.show();
        });
    }

    private void downloadPDF(String pdfReport) {
        mNotifyManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getActivity());
        mBuilder.setContentTitle("Downloading Pdf...").setContentText("Download in progress").setSmallIcon(R.mipmap.logo);
        // Start a lengthy operation in a background thread
        mBuilder.setProgress(0, 0, true);
        mNotifyManager.notify(id, mBuilder.build());
        mBuilder.setAutoCancel(true);

//        arr = new ArrayList<AsyncTask<String, String, Void>>();
//        int incr;
//        for (incr = 0; incr < urlsToDownload.length; incr++) {
//            ImageDownloader imageDownloader = new ImageDownloader();
//            imageDownloader.execute(urlsToDownload[incr]);
//            arr.add(imageDownloader);
//        }
        Log.v(TAG, "download() Method invoked ");
        Log.v(TAG, "download() Method HAVE PERMISSIONS ");
        new DownloadFile().execute(pdfReport, "HiCare.pdf");
        Log.v(TAG, "download() Method completed ");

        ContentResolver contentResolver = getActivity().getContentResolver();
        String enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = getActivity().getPackageName();

        // check to see if the enabledNotificationListeners String contains our
        // package name
        if (enabledNotificationListeners == null || !enabledNotificationListeners.contains(packageName)) {
            // in this situation we know that the user has not granted the app
            // the Notification access permission
            // Check if notification is enabled for this application
            Log.i("ACC", "Dont Have Notification access");
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        } else {
            Log.i("ACC", "Have Notification access");
        }

        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NLService.NOT_TAG);
        getActivity().registerReceiver(nReceiver, filter);


    }

    private void viewPDF() {
        Log.v(TAG, "view() Method invoked ");

        File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  // -> filename = maven.pdf
        File pdfFile = new File(d, "maven.pdf");

        Log.v(TAG, "view() Method pdfFile " + pdfFile.getAbsolutePath());

        Uri path = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileprovider", pdfFile);

        Log.v(TAG, "view() Method path " + path);

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }

        Log.v(TAG, "view() Method completed ");
    }

    @Override
    public void onErrorLoading(String message) {


    }

    private class DownloadFile extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            Log.v(TAG, "doInBackground() Method invoked ");

            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> HiCare.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File pdfFile = new File(folder, fileName);
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsolutePath());
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsoluteFile());

            try {
                pdfFile.createNewFile();
                Log.v(TAG, "doInBackground() file created" + pdfFile);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground() error" + e.getMessage());
                Log.e(TAG, "doInBackground() error" + e.getStackTrace());


            }
            PdfDownloader.downloadFile(fileUrl, pdfFile);
            Log.v(TAG, "doInBackground() file download completed");

            return fileUrl;
        }


        @Override
        protected void onPostExecute(String fileUrl) {
            super.onPostExecute(fileUrl);
            Log.i("Async-Example", "onPostExecute Called");

            // When the loop is finished, updates the notification
            if (counter >= 0) {
                mBuilder.setContentTitle(fileUrl);
                mBuilder.setContentText("Download complete")
                        // Removes the progress bar
                        .setProgress(0, 0, false);
                mNotifyManager.notify(id, mBuilder.build());
            } else {
                int per = (int) (((counter + 1)) * 100f);
                Log.i("Counter", "Counter : " + counter + ", per : " + per);
                mBuilder.setContentText("Downloaded (" + per + "/100");
                mBuilder.setProgress(100, per, false);
                // Displays the progress bar for the first time.
                mNotifyManager.notify(id, mBuilder.build());
            }
            counter++;

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(nReceiver);
    }

}
