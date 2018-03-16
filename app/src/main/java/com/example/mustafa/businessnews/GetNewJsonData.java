package com.example.mustafa.businessnews;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mustafa on 16/03/2018.
 */

public class GetNewJsonData extends GetRawData {

    private static final String LOG_TAG = GetNewJsonData.class.getSimpleName();

    private List<New> mNews;
    private Uri mDestinationUri;

    public GetNewJsonData(String searchCriteria, boolean matchAll){
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mNews = new ArrayList<>();

    }

    public List<New> getNews(){
        return mNews;
    }

    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll){
        final String NEW_BASE_API_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=074865eadbda4f82b8ee4387282688a9";
        final String TAGS_PARAM = "q";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";

        mDestinationUri = Uri.parse(NEW_BASE_API_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM,searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM,(matchAll?"all":"any"))
                .appendQueryParameter(FORMAT_PARAM,"json")
                .build();

        return mDestinationUri != null;

    }

    private void processResult(){
        final String NEW_ARTICLES = "articles";
        final String NEW_AUTHOR = "author";
        final String NEW_TITLE = "title";
        final String NEW_DESCRIPTION = "description";
        final String NEW_URL_IMAGE = "urlToImage";

        if (getDownloadStatus() != DownloadStatus.OK){
            Log.e(LOG_TAG,"no se a descargado el JSON");
            return;
        }

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = jsonData.getJSONArray(NEW_ARTICLES);

            for (int i = 0; i<itemsArray.length(); i++){
                JSONObject jsonNew = itemsArray.getJSONObject(i);
                String title = jsonNew.getString(NEW_TITLE);
                String author = jsonNew.getString(NEW_AUTHOR);
                String description = jsonNew.getString(NEW_DESCRIPTION);
                String imageUrl = jsonNew.getString(NEW_URL_IMAGE);

                New mNew = new New(title,author,imageUrl,description);
                mNews.add(mNew);
            }

            for (New mNew: mNews){
                Log.d(LOG_TAG,"New: "+mNew.toString());
            }

        }catch (JSONException e){
            Log.e(LOG_TAG,"No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute(){
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG,"BUILD URI: "+ mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData{

        public void onPostExecute (String webData){
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params){
            String [] par = {mDestinationUri.toString()};

            return super.doInBackground(par);
        }

    }
}
