package com.example.phoche.localcertdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

//    private String url = "https://videojj.com";
    private String url = "http://liveapi.videojj" +
        ".com/api/v1/getUser?platformUserId=999&platformId=556c38e7ec69d5bf655a0fb2";
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LocalCertOkhttpModel okhttpModel = new LocalCertOkhttpModel();

        mResult = (TextView) findViewById(R.id.result);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okhttpModel.connet(url, getApplicationContext(), new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResult.setText(e.toString());
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String string = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResult.setText(string);
                            }
                        });

                    }
                });
            }
        });
    }
}
