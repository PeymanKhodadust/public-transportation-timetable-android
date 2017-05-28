package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.peyman.publictransportationtimetable.utilities.RestApiQueryTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;

public class SearchResultActivity extends AppCompatActivity {

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String result = intent.getStringExtra(RestApiQueryTask.EXTRA_JSON_STRING);
        jsonObject = null;
        String sectionString = null;
        JSONObject connection;
        JSONArray sections, connections = null;
        try{
            jsonObject = new JSONObject(result);
            connections = jsonObject.getJSONArray("connections");
            connection = (JSONObject) connections.get(0);
            sections = (JSONArray) connection.get("sections");
            sectionString = sections.get(0).toString();
        }catch (JSONException je) {
            je.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(sectionString);
    }
}
