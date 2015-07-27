package com.example.vaibhav.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vaibhav.R;
import com.example.vaibhav.model.VideoList;

import java.util.ArrayList;

/**
 * Created by Vaibhav on 7/15/2015.
 */
public class VideoAdapter extends BaseAdapter{

    Context context;
    ArrayList<VideoList> videoLists;
    private LayoutInflater inflater;
    public VideoAdapter(Context context, ArrayList<VideoList> list){
        this.context = context;
        this.videoLists = list;
        inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return videoLists.size();
    }

    @Override
    public Object getItem(int position) {
        return videoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView)view.findViewById(R.id.imageView);
            holder.title = (TextView)view.findViewById(R.id.textView);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }
        VideoList videoObj = (VideoList)getItem(position);
        Glide.with(context).load(videoObj.getUrl()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thumbnail);
        holder.title.setText(videoObj.getTitle());

        return view;
    }

    public static class ViewHolder{
        ImageView thumbnail;
        TextView title;
    }
}
