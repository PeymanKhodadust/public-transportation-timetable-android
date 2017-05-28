package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.peyman.publictransportationtimetable.utilities.RestApiQueryTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity /*implements ResultAdapter.ListItemClickListener*/{

    private List<String> nameList;
    private ResultAdapter mAdapter;
    private RecyclerView mResultList;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        nameList = new ArrayList<>();
        nameList.add("Alice");
        nameList.add("Bob");
        nameList.add("Cindy");

        mResultList = (RecyclerView) findViewById(R.id.rv_results);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mResultList.setLayoutManager(layoutManager);
        mAdapter = new ResultAdapter(nameList);

        mResultList.setAdapter(mAdapter);

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

        //TextView tw = (TextView) findViewById(R.id.text);
        //tw.setText(sectionString);

    }

    /*@Override
    public void onListItemClick(int clickedItemIndex) {
        Log.v(this.getLocalClassName(), "clickedItemIndex: " + clickedItemIndex);
    }*/
}
