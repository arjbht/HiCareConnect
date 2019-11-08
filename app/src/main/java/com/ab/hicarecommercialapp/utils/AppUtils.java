package com.ab.hicarecommercialapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.util.Log;

import com.ab.hicarecommercialapp.R;
import com.ab.hicarecommercialapp.view.login.LoginActivity;

/**
 * Created by Arjun Bhatt on 9/25/2019.
 */
public class AppUtils {

    private static ProgressDialog mProgressDialog;

    public static void showExitAlert(Activity context) {
        try {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            dialog.setTitle("Exit");
            dialog.setMessage("Do you want to exit?");
            dialog.setPositiveButton("Yes", (dialogInterface, i) -> context.finish());
            dialog.setNegativeButton("No", null);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showDialogMessage(Activity context, String title, String message) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setNeutralButton("Dismiss", (dialogInterface, i) -> dialogInterface.dismiss()).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void showProgressDialog(Activity context) {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        Log.i("ProgressDialog", "showProgressDialog");
        try {
            mProgressDialog = new ProgressDialog(context, R.style.TransparentProgressDialog);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    public static void dismissProgressDialog() {
        if (mProgressDialog != null) {
            if (!mProgressDialog.isShowing()) {
                return;
            }
            Log.i("ProgressDialog", "dismissProgressDialog");
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Integer getImageByCode(String type) {
        int image = 0;
        switch (type) {
            case "CMS":

                image = R.drawable.ic_cockroach;

                break;

            case "CMSGENEX":

                image = R.drawable.ic_cockroach;

                break;

            case "BBMS":

                image = R.drawable.ic_bed_bug;

                break;

            case "BMS":

                image = R.drawable.ic_dove;

                break;

            case "CARCMS":

                image = R.drawable.ic_mosquito;

                break;

            case "FMS":

                image = R.drawable.ic_fly;

                break;

            case "LMS":

                image = R.drawable.ic_lizard;

                break;

            case "MMS":

                image = R.drawable.ic_mosquito;

                break;

            case "RMS":

                image = R.drawable.ic_rat;

                break;

            case "SRS":

                image = R.drawable.ic_snake;

                break;

            case "Stericare":

                image = R.drawable.logo;

                break;

            case "TMSPOST":

                image = R.drawable.ic_termite;

                break;

            case "TMSPRE":

                image = R.drawable.ic_termite;

                break;

            case "WBMS":

                image = R.drawable.ic_beetle;

                break;

            case "Termites for Post":

                image = R.drawable.ic_termite;

                break;

            case "Bed Bugs":

                image = R.drawable.ic_bed_bug;

                break;

            case "HCS":

                image = R.drawable.logo;

                break;

            case "Insp":

                image = R.drawable.logo;

                break;

            default:

                image = R.mipmap.logo;

                break;

        }
        return image;
    }

    public static String GetSMSServiceType(String type) {
        String serviceType = "";

        if (type.equals("CMS")) {
            serviceType = "Cockroach Control";
        } else if (type.equals("CMSGENEX")) {
            serviceType = "Cockroach Control";
        } else if (type.equals("BBMS")) {
            serviceType = "Bed Bugs Control";
        } else if (type.equals("BMS")) {
            serviceType = "Bird Netting";
        } else if (type.equals("CARCMS")) {
            serviceType = "Car Cockroach Control";
        } else if (type.equals("FMS")) {
            serviceType = "Fly Control";
        } else if (type.equals("LMS")) {
            serviceType = "Lizard Control";
        } else if (type.equals("MMS")) {
            serviceType = "Mosquito Control";
        } else if (type.equals("RMS")) {
            serviceType = "Rodent Control";
        } else if (type.equals("SRS")) {
            serviceType = "Snake Control";
        } else if (type.equals("Stericare")) {
            serviceType = "Stericare";
        } else if (type.equals("TMSPOST")) {
            serviceType = "Termite Control";
        } else if (type.equals("TMSPRE")) {
            serviceType = "Termite Control";
        } else if (type.equals("WBMS")) {
            serviceType = "Wood Borer Control";
        } else if (type.equals("Termites for Post")) {
            serviceType = "Termite Control";
        } else if (type.equals("Bed Bugs")) {
            serviceType = "Bed Bugs Control";
        } else if (type.equals("HCS")) {
            serviceType = "House Cleaning";
        } else if (type.equals("Insp")) {
            serviceType = "Inspection";
        } else {
            serviceType = type;
        }
//        switch (type)
//        {

//            case "CMS":
//
//                serviceType = "Cockroach Control";
//
//                break;
//
//            case "CMSGENEX":
//
//                serviceType = "Cockroach Control";
//
//                break;
//
//            case "BBMS":
//
//                serviceType = "Bed Bugs Control";
//
//                break;

//            case "BMS":
//
//                serviceType = "Bird Netting";
//
//                break;

//            case "CARCMS":
//
//                serviceType = "Car Cockroach Control";
//
//                break;

//            case "FMS":
//
//                serviceType = "Fly Control";
//
//                break;

//            case "LMS":
//
//                serviceType = "Lizard Control";
//
//                break;

//            case "MMS":
//
//                serviceType = "Mosquito Control";
//
//                break;

//            case "RMS":
//
//                serviceType = "Rodent Control";
//
//                break;

//            case "SRS":
//
//                serviceType = "Snake Control";
//
//                break;
//
//            case "Stericare":
//
//                serviceType = "Stericare";
//
//                break;

//            case "TMSPOST":
//
//                serviceType = "Termite Control";
//
//                break;

//            case "TMSPRE":
//
//                serviceType = "Termite Control";
//
//                break;
//
//            case "WBMS":
//
//                serviceType = "Wood Borer Control";
//
//                break;

//            case "Termites for Post":
//
//                serviceType = "Termite Control";
//
//                break;

//            case "Bed Bugs":
//
//                serviceType = "Bed Bugs Control";
//
//                break;

//            case "HCS":
//
//                serviceType = "House Cleaning";
//
//                break;

//            case "Insp":
//
//                serviceType = "Inspection";
//
//                break;
//
//            default:
//
//                serviceType = type;
//
//                break;

//        }

        return serviceType;

    }

}
