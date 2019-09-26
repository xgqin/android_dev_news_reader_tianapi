package com.glriverside.xgqin.ggnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    private ListView lvNewsList;
    private List<News> newsData;

    private int page = 1;

    private NewsAdapter adapter;

    private int mCurrentColIndex = 0;

    private int[] mCols = new int[]{Constants.NEWS_COL5,
            Constants.NEWS_COL7, Constants.NEWS_COL8,
            Constants.NEWS_COL10, Constants.NEWS_COL11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        newsData = new ArrayList<>();
        adapter = new NewsAdapter(MainActivity.this, R.layout.list_item, newsData);
        lvNewsList.setAdapter(adapter);

        refreshData(page);
    }

    private void initView() {
        lvNewsList = findViewById(R.id.lv_news_list);

        lvNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                News news = adapter.getItem(i);
                intent.putExtra(Constants.NEWS_DETAIL_URL_KEY, news.getContentUrl());

                startActivity(intent);
            }
        });
    }

    private void refreshData(final int page) {
        new NewsListAsyncTask(MainActivity.this, adapter)
                .execute(new Integer[]{
                        mCols[mCurrentColIndex],
                        Constants.NEWS_NUM,
                        page});
    }
}
