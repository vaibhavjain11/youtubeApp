package com.example.vaibhav.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vaibhav.R;
import com.example.vaibhav.Utility.Constants;
import com.example.vaibhav.Utility.Util;
import com.example.vaibhav.adapter.VideoAdapter;
import com.example.vaibhav.model.VideoList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mygaadi.lms.RetrofitRequest;


public class VideoListActivity extends Activity implements AdapterView.OnItemClickListener {


    ArrayList<VideoList> videoListsArray;

    private static final String TAG = "VideoListActivity";



    ListView listView;
    VideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);



        videoListsArray = new ArrayList<>();


        adapter = new VideoAdapter(this,videoListsArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        HashMap<String,String> params = new HashMap<>();
        params.put("part" , "snippet");
        params.put("channelId","UCMSjsvDuobchFSw5U1SDaqg");
        params.put("key" , "AIzaSyCeidmMOEVBND3gLlE_2Ge50JUrJw5PMOU");
        RetrofitRequest.getVideoList(params,new Callback<Response>(){


            @Override
            public void success(Response response, Response response2) {

                String res = Util.getInstance(VideoListActivity.this).getResponse(response);
                getDataFromJson(res);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void getDataFromJson(String jsonStr){

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray(Constants.ITEMS);
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject obj = new JSONObject(jsonArray.get(i).toString());
                JSONObject idObj = obj.getJSONObject(Constants.ID);
                String videoId = idObj.getString(Constants.VIDEO_ID);
                JSONObject objSnippet = obj.getJSONObject(Constants.SNIPPET);
                String title = objSnippet.getString(Constants.TITLE);
                JSONObject thumbnail = objSnippet.getJSONObject(Constants.THUMBNAIL);
                JSONObject thumbnailDefault = thumbnail.getJSONObject(Constants.DEFAULT);
                String thumnailUrl = thumbnailDefault.getString(Constants.URL);

                VideoList videoList = new VideoList(videoId,title,thumnailUrl);
                videoListsArray.add(videoList);
                Log.i(TAG , videoId + "  " + title + " " + thumnailUrl);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called when activity gets invisible. Connection to Play Services needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Saves the resolution state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + videoListsArray.get(position).getVideoId()));
        startActivity(intent);
    }
}
