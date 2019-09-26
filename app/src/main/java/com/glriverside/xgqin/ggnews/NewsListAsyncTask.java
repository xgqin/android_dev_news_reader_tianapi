package com.glriverside.xgqin.ggnews;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsListAsyncTask extends AsyncTask<Integer, Void, String> {

    private static final String TAG = NewsListAsyncTask.class.getSimpleName();

    private NewsAdapter adapter;
    private Context context;

    public NewsListAsyncTask(Context context, NewsAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(Integer... integers) {

        Integer col = integers[0];
        Integer newsNum = integers[1];
        Integer page = integers[2];

        NewsRequest requestObj = new NewsRequest();

        requestObj.setCol(col);
        requestObj.setNum(newsNum);
        requestObj.setPage(page);
        String urlParams = requestObj.toString();

        Request request = new Request.Builder()
                .url(Constants.GENERAL_NEWS_URL + urlParams)
                .get().build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String body = response.body().string();
                return body;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String body) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseResponse<List<News>>>() {}.getType();

        BaseResponse<List<News>> newsListResponse = gson.fromJson(body, type);
        for (News news:newsListResponse.getData()) {
            adapter.add(news);
        }

        adapter.notifyDataSetChanged();
        super.onPostExecute(body);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
