package com.example.peyman.publictransportationtimetable.utilities;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by peyman on 16.05.17.
 */

public class NetworkUtilities {
    private final static String PUBLIC_TRANSPORT_BASE_URL = "http://transport.opendata.ch/v1/";

    private URL queryUrl;

    public NetworkUtilities(String query, HashMap<String, String> paramsAndValues) {
        Uri.Builder builder = Uri.parse(PUBLIC_TRANSPORT_BASE_URL + query).buildUpon();
        for (String key: paramsAndValues.keySet()) {
            builder.appendQueryParameter(key, paramsAndValues.get(key));
        }
        Uri builtUri = builder.build();
        try {
            queryUrl = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public URL getQueryUrl() {
        return queryUrl;
    }
}
