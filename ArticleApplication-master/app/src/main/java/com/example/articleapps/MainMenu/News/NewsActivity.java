package com.example.articleapps.MainMenu.News;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.articleapps.MainMenu.News.Adapter.FeedAdapter;
import com.example.articleapps.MainMenu.News.Adapter.PopularNewsAdapter;
import com.example.articleapps.MainMenu.News.Common.HTTODataHandler;
import com.example.articleapps.MainMenu.News.model.RSSObject;
import com.example.articleapps.R;
import com.google.gson.Gson;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView rv_popular_news;
    RSSObject rssObject;
    private final String RSS_link="https://www.24h.com.vn/upload/rss/amthuc.rss";
    private final String RSS_to_Json_API="https://api.rss2json.com/v1/api.json?rss_url=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.rv_news);
        rv_popular_news=findViewById(R.id.rv_popular_news);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this.getBaseContext(), LinearLayoutManager.HORIZONTAL,false);
        rv_popular_news.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsActivity.this.getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadRSS();
    }
    private void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog mDialog = new ProgressDialog(NewsActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("XIN CHỜ....");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTODataHandler http = new HTTODataHandler();
                result= http.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,NewsActivity.this.getBaseContext());
                PopularNewsAdapter popularNewsAdapter = new PopularNewsAdapter(rssObject,NewsActivity.this.getBaseContext());
                rv_popular_news.setAdapter(popularNewsAdapter);
                recyclerView.setAdapter(adapter);
                popularNewsAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
    }
}
