package com.statusstock.studyzone.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.statusstock.studyzone.R;

public class ViewHolderSubjectDetails extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;


    public ViewHolderSubjectDetails(final View itemView) {
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

    public void setChapter(String text) {
        TextView chapter = (TextView) mView.findViewById(R.id.chapter);
        chapter.setText(text);
    }


    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
