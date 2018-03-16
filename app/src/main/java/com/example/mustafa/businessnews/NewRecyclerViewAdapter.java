package com.example.mustafa.businessnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mustafa on 15/03/2018.
 */

public class NewRecyclerViewAdapter extends RecyclerView.Adapter<NewRecyclerViewHolder> {

    private static final String LOG_TAG = NewRecyclerViewAdapter.class.getSimpleName();


    private List<New> mNewsList;
    private Context mContext;

    public NewRecyclerViewAdapter(Context context, List<New> newList){
        mNewsList = newList;
        mContext = context;
    }

    @Override
    public NewRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,null,false);

        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
        );

        view.setLayoutParams(p);

        NewRecyclerViewHolder newRecyclerViewHolder = new NewRecyclerViewHolder(view);

        return newRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mNewsList != null ? mNewsList.size() : 0);
    }

    @Override
    public void onBindViewHolder(NewRecyclerViewHolder holder, int position) {
        New newtem = mNewsList.get(position);

        Log.d(LOG_TAG,"Processing: "+newtem.getmTitle()+" -> "+ Integer.toString(position));

        Picasso.with(mContext).load(newtem.getmImage())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .into(holder.getmThumbnail());
    }

    public void loadNewData(List<New> news){
        mNewsList = news;

        notifyDataSetChanged();
    }

    public New getNew (int position){
        return (mNewsList != null ? mNewsList.get(position) : null);
    }


}
