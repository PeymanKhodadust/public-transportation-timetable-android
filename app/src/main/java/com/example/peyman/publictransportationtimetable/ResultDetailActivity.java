package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        Intent intent = this.getIntent();
        String resultDetailObject = intent.getStringExtra(SearchResultActivity.EXTRA_CONNECTION_JSON_OBJECT);
        TextView tv = (TextView) findViewById(R.id.textView_res);
        tv.setText(resultDetailObject);
    }
}
