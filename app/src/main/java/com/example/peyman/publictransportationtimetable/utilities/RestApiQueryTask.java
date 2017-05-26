package com.example.peyman.publictransportationtimetable.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by peyman on 16.05.17.
 */

public class RestApiQueryTask extends AsyncTask<URL, Void, String> {
    public static final String EXTRA_JSON_STRING = "com.example.peyman.publictransportationtimetable.EXTRA_JSON_STRING";
    private Context context;
    private Intent  intent;

    public RestApiQueryTask(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL searchUrl = urls[0];
        String result = null;
        try {
            result = NetworkUtilities.getRequestFromHttpUrl(searchUrl);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v(this.getClass().toString(), result != null ? result : "result equals null");
        intent.putExtra(EXTRA_JSON_STRING, result);
        context.startActivity(intent);
    }
}
