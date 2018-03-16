package com.example.daniel.infrmr.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.infrmr.R;
import com.example.daniel.infrmr.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/7/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    Context context;
    ArrayList<Article> articles = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false);

        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final ArticleAdapter.ViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.newsTitle.setText(article.getTitle());
        holder.newsDescription.setText(article.getDescription());
        holder.newsAuthor.setText(article.getAuthor());
        holder.date.setText(Util.dateFormatted(article.getPublishedDate()));

        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.articleImage.getDrawable();

        if(bitmapDrawable != null) {
            Bitmap  bitmap = bitmapDrawable.getBitmap();

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int bgColor = palette.getDominantColor(ContextCompat.getColor(context, android.R.color.black));
                holder.date.setBackgroundColor(bgColor);
                holder.newsAuthor.setTextColor(bgColor);
            }
        });
        }
        Picasso.with(context)
                .load(article.getImageUrl())
                .into(holder.articleImage);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView articleImage;
        public TextView  date, newsTitle, newsDescription, newsAuthor;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            articleImage = itemView.findViewById(R.id.newsImageId);
            date = itemView.findViewById(R.id.date);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDescription = itemView.findViewById(R.id.descriptionNews);
            newsAuthor = itemView.findViewById(R.id.author);



        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClicked(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(View view, int position);
    }
}
