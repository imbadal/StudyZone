package com.statusstock.studyzone.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.statusstock.studyzone.R;

public class ViewHolderSubject extends RecyclerView.ViewHolder {

    public View mView;
    private ClickListener mClickListener;


    public ViewHolderSubject(final View itemView) {
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

    public void setAns(String text) {
        TextView ans = (TextView) mView.findViewById(R.id.ans);
        ans.setText(text);
    }

    public void setNum(String text) {
        TextView num = (TextView) mView.findViewById(R.id.qsn_no);
        num.setText(text);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
