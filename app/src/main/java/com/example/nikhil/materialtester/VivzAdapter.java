package com.example.nikhil.materialtester;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by nikhil on 06-08-2016.
 */
public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<Information> data = Collections.EMPTY_LIST;
    Context context;

    public VivzAdapter(Context context,List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data =data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        Log.v("Nikhil debug","onCreateViewHolder is called");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Information current = data.get(position);
        holder.title.setText(current.name);
        holder.image.setImageResource(current.iconid);
        Log.v("Nikhil debug", "onBind ViewHolder is called at position " + position);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You clicked at position "+position,Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.custom_text);
            image = (ImageView) itemView.findViewById(R.id.custom_img);

        }
    }
}
