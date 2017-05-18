package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.peyman.publictransportationtimetable.utilities.NetworkUtilities;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "com.example.peyman.publictransportationtimetable.EXTRA_URL";
    //public static final String EXTRA_ARRIVALSTATION = "com.example.peyman.publictransportationtimetable.ARRIVALSTATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Search button */
    public void searchConnection(View view) {

        Intent intent = new Intent(this, SearchResultActivity.class);
        EditText from = (EditText) findViewById(R.id.editText5);
        EditText to = (EditText) findViewById(R.id.editText3);
        String depStation = from.getText().toString();
        String arrStation = to.getText().toString();
        HashMap args = new HashMap<String, String>();
        args.put("from", depStation);
        args.put("to", arrStation);
        NetworkUtilities n = new NetworkUtilities("connection", args);
        intent.putExtra(EXTRA_URL, n.getQueryUrl().toString());
        //intent.putExtra(EXTRA_ARRIVALSTATION, arrStation);
        startActivity(intent);
    }

}
