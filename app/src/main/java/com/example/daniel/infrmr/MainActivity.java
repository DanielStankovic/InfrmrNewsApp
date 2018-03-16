package com.example.daniel.infrmr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.daniel.infrmr.data.Article;
import com.example.daniel.infrmr.data.ArticleAdapter;
import com.example.daniel.infrmr.data.ArticleAsyncCallBack;
import com.example.daniel.infrmr.data.ArticleData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ArticleData().getNews(new ArticleAsyncCallBack() {
            @Override
            public void processFinish(final ArrayList<Article> articles) {

                recyclerView = findViewById(R.id.recyclerView);
                articleAdapter = new ArticleAdapter(getApplicationContext(), articles);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdapter);

                articleAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        Article article = articles.get(position);

                        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                        intent.putExtra("url", article.getNewsUrl());
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
