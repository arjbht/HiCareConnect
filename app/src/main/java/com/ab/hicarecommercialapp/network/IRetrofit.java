package com.ab.hicarecommercialapp.network;

import com.ab.hicarecommercialapp.model.audit.AuditResponse;
import com.ab.hicarecommercialapp.model.branch.BranchResponse;
import com.ab.hicarecommercialapp.model.company.CompanyResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentRequest;
import com.ab.hicarecommercialapp.model.complaint.ComplaintAttachmentResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintDetailResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintInteractionResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.ComplaintResponse;
import com.ab.hicarecommercialapp.model.complaint.ComplaintTypeResponse;
import com.ab.hicarecommercialapp.model.complaint.Complaints;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintRequest;
import com.ab.hicarecommercialapp.model.complaint.CreateComplaintResponse;
import com.ab.hicarecommercialapp.model.expert.ExpertRequest;
import com.ab.hicarecommercialapp.model.expert.ExpertResponse;
import com.ab.hicarecommercialapp.model.graph.GraphRequest;
import com.ab.hicarecommercialapp.model.graph.GraphResponse;
import com.ab.hicarecommercialapp.model.invoice.InvoiceRequest;
import com.ab.hicarecommercialapp.model.invoice.InvoiceResponse;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.model.login.VerifyUserResponse;
import com.ab.hicarecommercialapp.model.order.OrderRequest;
import com.ab.hicarecommercialapp.model.order.OrderResponse;
import com.ab.hicarecommercialapp.model.register_user.RegisterRequest;
import com.ab.hicarecommercialapp.model.register_user.RegisterResponse;
import com.ab.hicarecommercialapp.model.service.MyServiceRequest;
import com.ab.hicarecommercialapp.model.service.ServiceResponse;
import com.ab.hicarecommercialapp.model.service.UpcomingServiceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Arjun Bhatt on 9/19/2019.
 */
public interface IRetrofit {
    String BASE_URL = "http://apps.hicare.in/B2Bapps/api/";

//    /*Login*/
//    @FormUrlEncoded
//    @POST("login")
//    Call<LoginResponse> login(@Field("grant_type") String grantType,
//                              @Field("UserName") String username, @Field("Password") String password);

    /*[Login]*/

    @FormUrlEncoded
    @POST("Login")
    Call<LoginResponse> login(@Field("grant_type") String grantType, @Field("UserName") String username, @Field("Password") String password,
                              @Header("Content-Type") String content_type, @Header("IMEINo") String imei,
                              @Header("AppVersion") String version, @Header("DeviceInfo") String deviceinfo,
                              @Header("PlayerId") String mStrPlayerId, @Header("CompanyCode") String companyCode);

    /*GetActiveOrders*/

    @POST("orders/GetActiveOrders")
    Call<OrderResponse> getAllActiveOrders(
            @Body OrderRequest request);

    /*GetOrders*/

    @POST("orders/GetOrders")
    Call<OrderResponse> getOrders(
            @Body OrderRequest request);

    /*[GetAllServiceRequest]*/

    @POST("servicerequest/GetAllServiceRequest")
    Call<ServiceResponse> getAllServiceRequest(@Body MyServiceRequest request);

    /*[GetInvoices]*/

    @POST("invoice/GetInvoices")
    Call<InvoiceResponse> getInvoices(@Body InvoiceRequest request);

    /*[GetComplaint]*/

    @POST("complaint/GetComplaint")
    Call<ComplaintResponse> getComplaints(@Body ComplaintRequest request);

    /*[ComplaintByCaseNo]*/

    @GET("complaint/GetComplaintByCaseNo")
    Call<ComplaintDetailResponse> getComplaintsByCaseNumber(@Query("complaintId") int complaintId);

    /*[Create Complaints]*/

    @POST("complaint/CreateComplaintV2")
    Call<CreateComplaintResponse> getCreateComplaints(@Body CreateComplaintRequest request);

    /*[GetInvoiceServices]*/

    @POST("servicerequest/GetInvoiceServices")
    Call<ServiceResponse> getInvoiceServices(@Body MyServiceRequest request);

    /*[GetAccountJobCards]*/

    @POST("servicerequest/GetAccountJobCards")
    Call<ServiceResponse> getAccountJobCards(@Body MyServiceRequest request);

    /*[GetInvoiceJobCards]*/

    @POST("servicerequest/GetInvoiceJobCards")
    Call<ServiceResponse> getInvoiceJobCards(@Body MyServiceRequest request);

    /*[GetGraphData]*/

    @POST("charts/GetServiceDeliveryChart")
    Call<GraphResponse> getGraphData(@Body GraphRequest request);

    /*[VerifyCompanyCode]*/

    @GET("User/VerifyCompanyCode")
    Call<CompanyResponse> getVerifyCompanyCode(@Query("companyCode") String companyCode);


    /*[VerifyCompanyCode]*/

    @GET("User/VerifyUser")
    Call<VerifyUserResponse> getVerifyUser(@Query("mobileno") String mobile,
                                           @Query("companyCode") String companyCode,
                                           @Query("resendOtp") Boolean isResend);

    /*[GetBranchList]*/

    @GET("User/GetBranchListV2")
    Call<BranchResponse> getBranchList(@Query("userId") String userId,
                                       @Query("companyCode") String companyCode,
                                       @Query("latitude") String latitude,
                                       @Query("longitude") String longitude);

    /*[UserRegistration]*/

    @POST("User/UserRegistration")
    Call<RegisterResponse> getUserRegistered(@Body RegisterRequest request);

    /*[GetTodaysService]*/

    @POST("servicerequest/GetTodaysService")
    Call<ServiceResponse> getTodaysServices(@Body MyServiceRequest request);

    /*[GetUpcomingServices]*/

    @POST("servicerequest/GetUpcomingServices")
    Call<UpcomingServiceResponse> getUpcomingServices(@Body MyServiceRequest request);

    /*[Complaint Types]*/

    @POST("complaint/GetComplaintType")
    Call<ComplaintTypeResponse> getComplaintTypes();

    /*[GetCustomerAudits]*/

    @GET("audits/GetCustomerAudits")
    Call<AuditResponse> getCustomerAudits(@Query("accountId") String accountId,
                                          @Query("startDate") String startDate,
                                          @Query("endDate") String endDate);

    /*[GetComplaintInteraction]*/

    @GET("complaint/GetComplaintInteraction")
    Call<ComplaintInteractionResponse> getComplaintInteractions(@Query("complaintId") int complaintId);

    /*[CallExpert]*/

    @POST("User/CallExpert")
    Call<ExpertResponse> getAnExpert(@Body ExpertRequest request);

    /*[GetServiceHistory]*/

    @GET("servicerequest/GetServiceRequestByAccountNo")
    Call<ServiceResponse> getServiceHistory(@Query("AccountNo") String accountNo,
                                            @Query("startDate") String startDate,
                                            @Query("endDate") String endDate,
                                            @Query("PageOffset") Integer pageOffset,
                                            @Query("PageSize") Integer pageSize);

    /*[UploadComplaintAttachment]*/

    @POST("Complaint/UploadComplaintAttachment")
    Call<ComplaintAttachmentResponse> getUploadComplaintAttachment(@Body ComplaintAttachmentRequest request);


}
