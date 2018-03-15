package com.jiechic.library.android.snappy.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jiechic.library.android.snappy.Snappy;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        try {
            textView.setText(Snappy.compress("kjsdkjfj").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textView.setText(Snappy.uncompress("kjsdkjfj".getBytes()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        textView.setText(Native.stringFromJNI());
    }
}
