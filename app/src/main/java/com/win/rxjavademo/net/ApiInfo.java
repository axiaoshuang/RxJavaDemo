package com.win.rxjavademo.net;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by 1234356 on 2016/11/29.
 */

public class ApiInfo {
    public static final String BASE_URL="http://m2.qiushibaike.com";
    private static  Service mService;
    private static final long mTimeOut=3000;
    public  interface Service{
        @GET("/article/list/suggest")
        Observable<Response<String>> getContent(@Query("page") int page);
    }


    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("retrofitBackInfo","retrofitBackInfo = "+message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient  client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(mTimeOut, TimeUnit.SECONDS)
                .readTimeout(mTimeOut, TimeUnit.SECONDS)
                .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                .build();

        mService=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加rx适配器
                .addConverterFactory(ScalarsConverterFactory.create())  //string转换器
                .client(client)
                .build()
                .create(ApiInfo.Service.class);
    }

    public static Service getmService() {
        return mService;
    }
}
