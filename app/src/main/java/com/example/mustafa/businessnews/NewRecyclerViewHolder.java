package com.example.mustafa.businessnews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mustafa on 15/03/2018.
 */

public class NewRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView mThumbnail;
    public TextView mTitle;

    public NewRecyclerViewHolder(View itemView) {
        super(itemView);

        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mTitle = itemView.findViewById(R.id.textViewTitle);
    }

    public ImageView getmThumbnail(){
        return mThumbnail;
    }
}
