package com.example.mustafa.businessnews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.mustafa.businessnews.SearchActivity.NEW_QUERY;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<New> mNewList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private NewRecyclerViewAdapter mNewRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        mNewRecyclerViewAdapter = new NewRecyclerViewAdapter(
            MainActivity.this,
                new ArrayList<New>()
        );

        mRecyclerView.setAdapter(mNewRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(
                          MainActivity.this,
                            ViewNewDetailsActivity.class
                        );

                        intent.putExtra(
                          NEW_TRANSFER,
                                mNewRecyclerViewAdapter.getNew(position)
                        );

                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this,"Long Tap",Toast.LENGTH_SHORT).show();
                    }
                }
        ));
    }

    protected void onResume(){
        super.onResume();

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                    getApplicationContext()
                );
        String query = getSavedPreferencesData(NEW_QUERY);
        if (query.length() > 0){
            ProcessNews processNews = new ProcessNews(query,true);
            processNews.execute();
        }
    }

    private String getSavedPreferencesData(String newQuery){
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );
        return sharedPreferences.getString(newQuery,"business");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_settings){
            return true;
        }

        if (id == R.id.menu_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProcessNews extends GetNewJsonData{

        public ProcessNews(String searchCriteria, boolean marchAll){
            super(searchCriteria,marchAll);
        }

        public void execute(){
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData{

            public void onPostExecute(String webData){
                super.onPostExecute(webData);
                mNewRecyclerViewAdapter.loadNewData(getNews());
            }
        }
    }
}
