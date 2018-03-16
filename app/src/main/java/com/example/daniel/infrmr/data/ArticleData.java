package com.example.daniel.infrmr.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.daniel.infrmr.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/7/2018.
 */

public class ArticleData {

    ArrayList<Article> articles = new ArrayList<>();

    public void getNews(final ArticleAsyncCallBack callBack){

        String url = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=b30395dc29f84bc0b2e196b0d4419c69";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray articleArray = response.getJSONArray("articles");

                    for (int i = 0; i <articleArray.length() ; i++) {

                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article = new Article();

                        article.setAuthor(articleObject.getString("author"));
                        article.setDescription(articleObject.getString("description"));
                        article.setTitle(articleObject.getString("title"));
                        article.setNewsUrl(articleObject.getString("url"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishedDate(articleObject.getString("publishedAt"));

                        articles.add(article);

                    }

                    if(callBack != null) callBack.processFinish(articles);

                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
