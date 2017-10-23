package com.example.phoche.localcertdemo;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Create by qinpc on 2017/10/12
 */
public class OkhttpClientUtils {

    private OkHttpClient mClient;

    public static OkhttpClientUtils getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static OkhttpClientUtils INSTANCE = new OkhttpClientUtils();
    }


    public OkHttpClient getClient(Context context) {
        if (mClient == null) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open("videojj.com.cer");
            } catch (IOException e) {
                e.printStackTrace();
            }

            HttpsUtils.SSLParams sslParams =
                    HttpsUtils.getSslSocketFactory(new InputStream[]{inputStream},
                    null,
                    null);
                mClient = new OkHttpClient
                        .Builder()
                        /*.dns(OkHttpDns.getInstance(context.getApplicationContext()))*/
                        .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                        .build();
        }
        return mClient;
    }

}
