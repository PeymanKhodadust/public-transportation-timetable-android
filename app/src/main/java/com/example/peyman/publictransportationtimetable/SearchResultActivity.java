package com.example.peyman.publictransportationtimetable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.peyman.publictransportationtimetable.utilities.RestApiQueryTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchResultActivity extends AppCompatActivity implements ResultAdapter.ListItemClickListener{
    public static final String EXTRA_CONNECTION_JSON_OBJECT = "com.example.peyman.publictransportationtimetable.EXTRA_CONNECTION_JSON_OBJECT";

    private ResultAdapter mAdapter;
    private RecyclerView mResultList;
    JSONObject resultObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        String result = intent.getStringExtra(RestApiQueryTask.EXTRA_JSON_STRING);
        resultObject = null;
        JSONArray connections = null;
        try{
            resultObject = new JSONObject(result);
            connections = resultObject.getJSONArray("connections");

        }catch (JSONException je) {
            je.printStackTrace();
        }

        mResultList = (RecyclerView) findViewById(R.id.rv_results);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mResultList.setLayoutManager(layoutManager);
        mAdapter = new ResultAdapter(connections, this);
        mResultList.setAdapter(mAdapter);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        JSONObject resultDetail = null;
        try{
            resultDetail = resultObject.getJSONArray("connections").getJSONObject(clickedItemIndex);
        }catch (JSONException je) {
            je.printStackTrace();
        }
        Intent intent = new Intent(this, ResultDetailActivity.class);
        intent.putExtra(EXTRA_CONNECTION_JSON_OBJECT, resultDetail.toString());

        Context context = this;
        context.startActivity(intent);
    }
}
