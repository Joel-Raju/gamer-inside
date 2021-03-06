package com.raju.joel.gamerinside.news;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.NewsArticle;
import com.raju.joel.gamerinside.newsdetail.NewsListener;
import com.raju.joel.gamerinside.ui.OnBottomReachedListener;
import com.raju.joel.gamerinside.util.TextUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joel on 10-Sep-17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVH> {

    public static final String IMAGE_BASE_URL = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/";

    public static final String IMAGE_FILE_EXTENSION = ".png";

    private NewsListener mNewsArticleListener;

    private List<NewsArticle> mNewsArticleList;

    private Context mContext;

    private OnBottomReachedListener mOnBottomReachedListener;

    public NewsAdapter(Context context, List<NewsArticle> newsArticleList,
                       NewsListener newsArticleListener,
                       OnBottomReachedListener onBottomReachedListener) {
        this.mContext = context;
        setList(newsArticleList);
        this.mNewsArticleListener = newsArticleListener;
        this.mOnBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public NewsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_article, parent, false);
        return new NewsVH(itemView);
    }

    public void replaceData(List<NewsArticle> newsArticles) {
        setList(newsArticles);
        notifyDataSetChanged();
    }

    private void setList(List<NewsArticle> newsArticles) {
        mNewsArticleList = newsArticles;
    }

    @Override
    public void onBindViewHolder(NewsVH holder, int position) {
        if (position == (mNewsArticleList.size()-1)) {
            mOnBottomReachedListener.onBottomReached();
        }
        final NewsArticle article = mNewsArticleList.get(position);
        String imageUrl = getNewsImagePath(article.getPulseImage().getCloudId());

        holder.newsTitle.setText(article.getTitle());
        holder.newsDate.setText(TextUtils.
                getFormattedDateForArticleFromUnixEpoch(article.getCreatedTimestamp()));
        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.newsImage);

        holder.newsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsArticleListener.onNewsArticleClickListener(article);
            }
        });
    }

    private static String getNewsImagePath(String cloudId) {
        return IMAGE_BASE_URL + cloudId + IMAGE_FILE_EXTENSION;
    }

    @Override
    public int getItemCount() {
        return mNewsArticleList.size();
    }

    public class NewsVH extends RecyclerView.ViewHolder {
        private CardView newsCard;
        private TextView newsTitle;
        private ImageView newsImage;
        private TextView newsDate;

        public NewsVH(View itemView) {
            super(itemView);

            newsCard  = (CardView)  itemView.findViewById(R.id.news_card);
            newsTitle = (TextView)  itemView.findViewById(R.id.news_article_card_title);
            newsImage = (ImageView) itemView.findViewById(R.id.news_article_card_image);
            newsDate  = (TextView)  itemView.findViewById(R.id.news_article_card_date);
        }
    }
}
