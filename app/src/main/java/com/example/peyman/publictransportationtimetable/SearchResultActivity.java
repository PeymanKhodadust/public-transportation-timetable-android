package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.URL;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.EXTRA_URL);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(url);
        System.out.print("--------------------------url: " + url);
    }
}
