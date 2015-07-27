package com.example.vaibhav.Utility;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.client.Response;

/**
 * Created by Vaibhav on 7/14/2015.
 */
public class Util {

    private static volatile Util instance;
    private Context context;

    private Util(Context ctxt){
        this.context = ctxt;
    }
    public static Util getInstance(Context context) {
        if (instance == null) {
            synchronized (Util.class) {
                instance = new Util(context);
            }
        }
        return instance;
    }

    public String getResponse(Response response) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(convertStreamToString(response.getBody().in()));
            return gson.toJson(je);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            try {
                return convertStreamToString(response.getBody().in());
            } catch (IOException e1) {
                e1.printStackTrace();
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
