package com.ab.hicarecommercialapp.model.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 9/29/2019.
 */
public class Invoices implements Parcelable {

    @SerializedName("SFDC_Invoice_Number__c")
    @Expose
    private String sFDCInvoiceNumberC;
    @SerializedName("Service_Invoice_Attachment__c")
    @Expose
    private Object serviceInvoiceAttachmentC;
    @SerializedName("AttachmentId__c")
    @Expose
    private Object attachmentIdC;
    @SerializedName("InvoiceDate__c")
    @Expose
    private String invoiceDateC;
    @SerializedName("InvoiceDateText")
    @Expose
    private String InvoiceDateText;
    @SerializedName("GSTN_Invoice_Number__c")
    @Expose
    private Object gSTNInvoiceNumberC;
    @SerializedName("Gross_Price__c")
    @Expose
    private Double grossPriceC;
    @SerializedName("Net_Cost__c")
    @Expose
    private Double netCostC;
    @SerializedName("Tax_Line_Item_1_Percent__c")
    @Expose
    private Double taxLineItem1PercentC;
    @SerializedName("Tax_Line_Item_2_Percent__c")
    @Expose
    private Double taxLineItem2PercentC;
    @SerializedName("Service_Tax__c")
    @Expose
    private Double serviceTaxC;
    @SerializedName("Taxvalue__c")
    @Expose
    private Object taxvalueC;
    @SerializedName("Total_value__c")
    @Expose
    private Double totalValueC;
    @SerializedName("Outstanding_Amount__c")
    @Expose
    private Double outstandingAmountC;
    @SerializedName("Total_Credited_Amount__c")
    @Expose
    private Double totalCreditedAmountC;
    @SerializedName("Total_Debited_Amount__c")
    @Expose
    private Double totalDebitedAmountC;
    @SerializedName("Total_Knock_off_Payment__c")
    @Expose
    private Double totalKnockOffPaymentC;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DataType")
    @Expose
    private String dataType;



    public String getSFDCInvoiceNumberC() {
        return sFDCInvoiceNumberC;
    }

    public void setSFDCInvoiceNumberC(String sFDCInvoiceNumberC) {
        this.sFDCInvoiceNumberC = sFDCInvoiceNumberC;
    }

    public Object getServiceInvoiceAttachmentC() {
        return serviceInvoiceAttachmentC;
    }

    public void setServiceInvoiceAttachmentC(Object serviceInvoiceAttachmentC) {
        this.serviceInvoiceAttachmentC = serviceInvoiceAttachmentC;
    }

    public Object getAttachmentIdC() {
        return attachmentIdC;
    }

    public void setAttachmentIdC(Object attachmentIdC) {
        this.attachmentIdC = attachmentIdC;
    }

    public String getInvoiceDateC() {
        return invoiceDateC;
    }

    public void setInvoiceDateC(String invoiceDateC) {
        this.invoiceDateC = invoiceDateC;
    }

    public Object getGSTNInvoiceNumberC() {
        return gSTNInvoiceNumberC;
    }

    public void setGSTNInvoiceNumberC(Object gSTNInvoiceNumberC) {
        this.gSTNInvoiceNumberC = gSTNInvoiceNumberC;
    }

    public Double getGrossPriceC() {
        return grossPriceC;
    }

    public void setGrossPriceC(Double grossPriceC) {
        this.grossPriceC = grossPriceC;
    }

    public Double getNetCostC() {
        return netCostC;
    }

    public void setNetCostC(Double netCostC) {
        this.netCostC = netCostC;
    }

    public Double getTaxLineItem1PercentC() {
        return taxLineItem1PercentC;
    }

    public void setTaxLineItem1PercentC(Double taxLineItem1PercentC) {
        this.taxLineItem1PercentC = taxLineItem1PercentC;
    }

    public Double getTaxLineItem2PercentC() {
        return taxLineItem2PercentC;
    }

    public void setTaxLineItem2PercentC(Double taxLineItem2PercentC) {
        this.taxLineItem2PercentC = taxLineItem2PercentC;
    }

    public Double getServiceTaxC() {
        return serviceTaxC;
    }

    public void setServiceTaxC(Double serviceTaxC) {
        this.serviceTaxC = serviceTaxC;
    }

    public Object getTaxvalueC() {
        return taxvalueC;
    }

    public void setTaxvalueC(Object taxvalueC) {
        this.taxvalueC = taxvalueC;
    }

    public Double getTotalValueC() {
        return totalValueC;
    }

    public void setTotalValueC(Double totalValueC) {
        this.totalValueC = totalValueC;
    }

    public Double getOutstandingAmountC() {
        return outstandingAmountC;
    }

    public void setOutstandingAmountC(Double outstandingAmountC) {
        this.outstandingAmountC = outstandingAmountC;
    }

    public Double getTotalCreditedAmountC() {
        return totalCreditedAmountC;
    }

    public void setTotalCreditedAmountC(Double totalCreditedAmountC) {
        this.totalCreditedAmountC = totalCreditedAmountC;
    }

    public Double getTotalDebitedAmountC() {
        return totalDebitedAmountC;
    }

    public void setTotalDebitedAmountC(Double totalDebitedAmountC) {
        this.totalDebitedAmountC = totalDebitedAmountC;
    }

    public Double getTotalKnockOffPaymentC() {
        return totalKnockOffPaymentC;
    }

    public void setTotalKnockOffPaymentC(Double totalKnockOffPaymentC) {
        this.totalKnockOffPaymentC = totalKnockOffPaymentC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInvoiceDateText() {
        return InvoiceDateText;
    }

    public void setInvoiceDateText(String invoiceDateText) {
        InvoiceDateText = invoiceDateText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
