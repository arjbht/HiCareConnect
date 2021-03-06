package com.ab.hicarecommercialapp;

import android.app.Application;

import com.ab.hicarecommercialapp.database.realm.RealmString;
import com.ab.hicarecommercialapp.database.realm.RealmStringListTypeAdapter;
import com.ab.hicarecommercialapp.model.login.LoginResponse;
import com.ab.hicarecommercialapp.network.HeaderInterceptor;
import com.ab.hicarecommercialapp.network.IRetrofit;
import com.ab.hicarecommercialapp.network.RequestHeader;
import com.ab.hicarecommercialapp.utils.notification.OneSIgnalHelper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    private static volatile Realm REALM = null;
    private static volatile IRetrofit IRETROFIT = null;
    private OneSIgnalHelper mOneSignalHelper;


    public static synchronized Realm getRealm() {
        if (REALM != null) {
            return REALM;
        } else {
            RealmConfiguration realmConfig =
                    new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfig);
            REALM = Realm.getDefaultInstance();
            return REALM;
        }
    }


    public static synchronized IRetrofit getRetrofitAPI(boolean autohrised) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        gsonBuilder.registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
        }.getType(), RealmStringListTypeAdapter.INSTANCE);

        Gson gson = gsonBuilder.create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        if (autohrised) httpClientBuilder.addInterceptor(new HeaderInterceptor(getHeader()));

        IRETROFIT = new Retrofit.Builder().baseUrl(IRetrofit.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build()
                .create(IRetrofit.class);

        return IRETROFIT;
    }

    private static RequestHeader getHeader() {
        RequestHeader header = null;
        RealmResults<LoginResponse> query =
                BaseApplication.getRealm().where(LoginResponse.class).findAll();
        if (query != null && query.size() > 0) {
            header = new RequestHeader();
            header.setHeaderName("Authorization");
            header.setHeaderValue(query.get(0).getTokenType() + " " + query.get(0).getAccessToken());
        }
        return header;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mOneSignalHelper = new OneSIgnalHelper(this);

        // initialise the realm database
        try {
            Realm.init(this);
            RealmConfiguration realmConfiguration =
                    new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // configuring the font for calligraphy
        try {
            CalligraphyConfig.initDefault(
                    new CalligraphyConfig.Builder().setDefaultFontPath("fonts/font.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
