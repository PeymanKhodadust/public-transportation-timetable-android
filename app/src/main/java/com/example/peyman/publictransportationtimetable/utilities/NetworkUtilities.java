package com.example.peyman.publictransportationtimetable.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by peyman on 16.05.17.
 */

public class NetworkUtilities {
    private final static String PUBLIC_TRANSPORT_BASE_URLString = "http://transport.opendata.ch/v1/";
    private URL queryUrl;

    public NetworkUtilities(URL url) {
        queryUrl = url;
    }

    public static URL createUrl(String query, HashMap<String, String> paramsAndValues) {
        URL queryUrl = null;
        Uri.Builder builder = Uri.parse(PUBLIC_TRANSPORT_BASE_URLString + query).buildUpon();
        for (String key: paramsAndValues.keySet()) {
            builder.appendQueryParameter(key, paramsAndValues.get(key));
        }
        Uri builtUri = builder.build();
        try {
            queryUrl = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return queryUrl;
    }

    public static String getRequestFromHttpUrl(URL url) throws IOException{
        Log.v("Networkutilities", "--------------- url = " + url.toString());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");

            if(scanner.hasNext()) {
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
