package com.statusstock.studyzone.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.statusstock.studyzone.R;

public class ViewHolderComment extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;


    public ViewHolderComment(final View itemView) {
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

    public void setComment(String text) {
        TextView comment = (TextView) mView.findViewById(R.id.comment);
        comment.setText(text);
    }


    public void setTime(String text) {
        TextView time = (TextView) mView.findViewById(R.id.time_comment);
        time.setText(text);
    }

    public void setDate(String text) {
        TextView date = (TextView) mView.findViewById(R.id.date_comment);
        date.setText(text);
    }

    public void setUserName(String text) {
        TextView name = (TextView) mView.findViewById(R.id.userFullName_comment);
        name.setText("@" + text);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
