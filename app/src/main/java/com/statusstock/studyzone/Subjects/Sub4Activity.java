package com.statusstock.studyzone.Subjects;

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
import com.statusstock.studyzone.R;
import com.statusstock.studyzone.Subject;
import com.statusstock.studyzone.ViewHolderSubject;

public class Sub4Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view_Sub4);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sub4");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        pb = findViewById(R.id.pro_Sub4);
        mRecyclerView = findViewById(R.id.recyclerView_Sub4);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Sub4");

        pb.setVisibility(View.VISIBLE);


        FirebaseRecyclerAdapter<Subject, ViewHolderSubject> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Subject, ViewHolderSubject>(Subject.class, R.layout.row_subject, ViewHolderSubject.class, mRef) {


            @Override
            protected void populateViewHolder(ViewHolderSubject viewHolderSubject, final Subject subject, int position) {

                viewHolderSubject.setQsn(subject.getQsn());
                viewHolderSubject.setAns(subject.getAns());
                viewHolderSubject.setNum(subject.getNum());


                pb.setVisibility(View.GONE);
            }

            @Override
            public ViewHolderSubject onCreateViewHolder(ViewGroup parent, int viewType) {


                ViewHolderSubject viewHolderSubject = super.onCreateViewHolder(parent, viewType);
                viewHolderSubject.setOnclickListener(new ViewHolderSubject.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {


                    }
                });


                return viewHolderSubject;
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
