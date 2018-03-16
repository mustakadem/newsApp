package com.example.mustafa.businessnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mustafa on 15/03/2018.
 */

public class ViewNewDetailsActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_details);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();

        New news = (New) intent.getSerializableExtra(PHOTO_TRANSFER);

        TextView newTitle = findViewById(R.id.new_title);
        newTitle.setText("Title: "+news.getmTitle());


        TextView newTags = findViewById(R.id.new_tags);
        newTags.setText("Description: "+ news.getmDescription());

        TextView newAuthor = findViewById(R.id.new_author);
        newAuthor.setText(news.getmAuthor());

        ImageView newImage = findViewById(R.id.new_image);
        Picasso.with(this).load(news.getmImage())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .into(newImage);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_new_detail,menu);

        return true;
    }
}
