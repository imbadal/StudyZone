package com.statusstock.studyzone.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.statusstock.studyzone.R;

public class ViewHolderForum extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;


    public ViewHolderForum(final View itemView) {
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

    public void setQsn(String text) {
        TextView qsn = (TextView) mView.findViewById(R.id.qsn);
        qsn.setText(text);
    }

    public void setDesc(String text) {
        TextView desc = (TextView) mView.findViewById(R.id.desc);
        desc.setText(text);
    }

    public void setTime(String text) {
        TextView time = (TextView) mView.findViewById(R.id.time);
        time.setText(text);
    }

    public void setDate(String text) {
        TextView date = (TextView) mView.findViewById(R.id.date);
        date.setText(text);
    }

    public void setUFullName(String text) {
        TextView name = (TextView) mView.findViewById(R.id.userFullName);
        name.setText(text);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
