package com.example.mustafa.businessnews;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by mustafa on 15/03/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String NEW_TRANSFER = "NEW_TRANSFER";

    private Toolbar mToolbar;

    protected Toolbar activateToolbar(){
        if (mToolbar == null){
            mToolbar=findViewById(R.id.app_bar);
            if (mToolbar != null){
                setSupportActionBar( mToolbar);
            }
        }

        return mToolbar;
    }


    public Toolbar activateToolbarWithBackEnabled(){
        activateToolbar();

        if (mToolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return mToolbar;
    }


}
