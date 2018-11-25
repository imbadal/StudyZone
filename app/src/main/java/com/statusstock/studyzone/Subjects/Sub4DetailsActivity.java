package com.statusstock.studyzone.Subjects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.statusstock.studyzone.Model.SubjectDetails;
import com.statusstock.studyzone.R;
import com.statusstock.studyzone.ViewHolder.ViewHolderSubjectDetails;
import com.statusstock.studyzone.WebViewActivity;

public class Sub4DetailsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view_Sub4Details);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Index");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        pb = findViewById(R.id.pro_Sub4Details);
        mRecyclerView = findViewById(R.id.recyclerView_Sub4Details);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Sub4Details");

        pb.setVisibility(View.VISIBLE);


        FirebaseRecyclerAdapter<SubjectDetails, ViewHolderSubjectDetails> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SubjectDetails, ViewHolderSubjectDetails>(SubjectDetails.class, R.layout.row_subject_content, ViewHolderSubjectDetails.class, mRef) {


            @Override
            protected void populateViewHolder(ViewHolderSubjectDetails viewHolderSubjectDetails, final SubjectDetails subjectDetails, int position) {

                viewHolderSubjectDetails.setChapter(subjectDetails.getChapter());

                viewHolderSubjectDetails.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = subjectDetails.getUrl();

                        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });

                pb.setVisibility(View.GONE);
            }

            @Override
            public ViewHolderSubjectDetails onCreateViewHolder(ViewGroup parent, int viewType) {


                ViewHolderSubjectDetails viewHolderSubjectDetails = super.onCreateViewHolder(parent, viewType);
                viewHolderSubjectDetails.setOnclickListener(new ViewHolderSubjectDetails.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {


                    }
                });


                return viewHolderSubjectDetails;
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


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
