package com.example.peyman.publictransportationtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.peyman.publictransportationtimetable.utilities.NetworkUtilities;
import com.example.peyman.publictransportationtimetable.utilities.RestApiQueryTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
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
        HashMap args = new HashMap<>();
        args.put("from", depStation);
        args.put("to", arrStation);
        RestApiQueryTask restApiQueryTask = new RestApiQueryTask(this, intent);
        restApiQueryTask.execute(NetworkUtilities.createUrl("connections", args));
    }

}
