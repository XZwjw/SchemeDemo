package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String scheme = intent.getScheme();
        Uri uri = intent.getData();
        Log.d("TAG","scheme:"+scheme);
        if(uri != null) {
            SchemeEntry entry = new SchemeEntry();
            entry.setHost(uri.getHost());
            entry.setDataString(intent.getDataString());
            entry.setKey1(uri.getQueryParameter("key1"));
            entry.setPath(uri.getPath());
            entry.setPath1(uri.getEncodedPath());
            entry.setQueryString(uri.getQuery());
            Log.d(TAG,entry.toString());
            TextView tv_entrance = findViewById(R.id.text);
            tv_entrance.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
