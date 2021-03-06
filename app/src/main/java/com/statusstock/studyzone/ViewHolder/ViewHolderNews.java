package com.statusstock.studyzone.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.statusstock.studyzone.R;

public class ViewHolderNews extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;



    public ViewHolderNews(final View itemView) {
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

    }

    public void setTitle(String title) {
        TextView news_title = (TextView) mView.findViewById(R.id.post_title_news);
        news_title.setText(title);
    }

    public void setSports(String sports) {
        TextView news_title = (TextView) mView.findViewById(R.id.sports);
        news_title.setText(sports);
    }

    public void setTime(String time) {
        TextView news_title = (TextView) mView.findViewById(R.id.time);
        news_title.setText(time);
    }

    public void setImage(Context ctx, String image) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.bgImage);
//        Picasso.with(ctx).load(image).resize(100,100).onlyScaleDown().into(post_image);
        Glide.with(ctx).load(image).into(post_image);


    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
