package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView dataRes;
    private Button search;
    private EditText inputId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search =findViewById(R.id.search);
        inputId = findViewById(R.id.iduser);
        dataRes = findViewById(R.id.textView2);
        final OkHttpClient client =new OkHttpClient();
        final String url="https://reqres.in/api/users/";

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=inputId.getText().toString().trim();
                Request myrequest = new Request.Builder().url(url+id).build();
                client.newCall(myrequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                           final String data = response.body().string();
                            MainActivity.this.runOnUiThread(new Runnable(){

                                @Override
                                public void run(){
                                    
                                    dataRes.setText(data);


                                } });
                        }

                    }
                });
            }
        });

    }
}