package com.statusstock.studyzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends android.support.v4.app.Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        pb = view.findViewById(R.id.pb_HomeFragment);
        mRecyclerView = view.findViewById(R.id.myRecycleView_HomeFragment);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("News");

        pb.setVisibility(View.VISIBLE);


        FirebaseRecyclerAdapter<News, ViewHolderNews> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News, ViewHolderNews>(News.class, R.layout.news_row, ViewHolderNews.class, mRef) {


            @Override
            protected void populateViewHolder(ViewHolderNews viewHolderNews, final News news, int position) {

                viewHolderNews.setTitle(news.getTitle());
                viewHolderNews.setTime(news.getTime());
                viewHolderNews.setSports(news.getSports());
//                viewHolderNews.setImage(getContext(), news.getImage());
                viewHolderNews.setImage(getActivity().getBaseContext(), news.getImage());


                viewHolderNews.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = news.getUrl();


                        Intent intent = new Intent(getActivity().getApplicationContext(), WebViewActivity.class);
                        intent.putExtra("id", url);
                        startActivity(intent);


                    }
                });

                pb.setVisibility(View.GONE);
            }

            @Override
            public ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {


                ViewHolderNews viewHolderNews = super.onCreateViewHolder(parent, viewType);
                viewHolderNews.setOnclickListener(new ViewHolderNews.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {


                    }
                });


                return viewHolderNews;
            }
        };

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    ((MainActivity) getActivity()).setNavigationVisibility(false);
                } else if (dy < 0) {
                    ((MainActivity) getActivity()).setNavigationVisibility(true);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        return view;
    }
}
