package com.statusstock.studyzone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.statusstock.studyzone.CommentActivity;
import com.statusstock.studyzone.ForumActivity;
import com.statusstock.studyzone.MainActivity;
import com.statusstock.studyzone.Model.Forum;
import com.statusstock.studyzone.R;
import com.statusstock.studyzone.ViewHolder.ViewHolderForum;

public class ForumFragment extends androidx.core.app.Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForumActivity.class);
                startActivity(intent);
            }
        });

        pb = view.findViewById(R.id.pb_ForumFragment);
        mRecyclerView = view.findViewById(R.id.myRecycleView_ForumFragment);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Forum");

        pb.setVisibility(View.VISIBLE);


        FirebaseRecyclerAdapter<Forum, ViewHolderForum> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Forum, ViewHolderForum>(Forum.class, R.layout.row_forum, ViewHolderForum.class, mRef) {


            @Override
            protected void populateViewHolder(ViewHolderForum viewHolderForum, final Forum Forum, int position) {

                final String postKey = getRef(position).getKey();

                viewHolderForum.setQsn(Forum.getqsn());
                viewHolderForum.setTime(Forum.getTime());
                viewHolderForum.setDate(Forum.getDate());
                viewHolderForum.setDesc(Forum.getDesc());
                viewHolderForum.setUFullName(Forum.getUfulname());


                viewHolderForum.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                viewHolderForum.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), CommentActivity.class);
                        intent.putExtra("postKey", postKey);
                        startActivity(intent);


                    }
                });

                pb.setVisibility(View.GONE);
            }

            @Override
            public ViewHolderForum onCreateViewHolder(ViewGroup parent, int viewType) {


                ViewHolderForum viewHolderForum = super.onCreateViewHolder(parent, viewType);
                viewHolderForum.setOnclickListener(new ViewHolderForum.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {


                    }
                });


                return viewHolderForum;
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
            public void onScrollStateChanged(RecyclerView recyclerView, int Forumtate) {

                super.onScrollStateChanged(recyclerView, Forumtate);
            }
        });


        return view;
    }
}
