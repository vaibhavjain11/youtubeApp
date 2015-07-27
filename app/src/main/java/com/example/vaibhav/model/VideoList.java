package com.example.vaibhav.model;

/**
 * Created by Vaibhav on 7/15/2015.
 */
public class VideoList {

    String videoId;
    String title;
    String url;

    public VideoList(String id, String title, String url){
        this.videoId = id;
        this.title = title;
        this.url = url;
    }

    public String getVideoId(){
        return this.videoId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getUrl(){
        return this.url;
    }
}
