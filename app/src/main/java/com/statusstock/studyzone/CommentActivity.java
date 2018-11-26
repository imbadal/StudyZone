package com.statusstock.studyzone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.statusstock.studyzone.Model.Comment;
import com.statusstock.studyzone.ViewHolder.ViewHolderComment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CommentActivity extends AppCompatActivity {

    ProgressBar pb;
    private RecyclerView commentList;
    private ImageView postComment;
    private EditText commentInputText;
    private DatabaseReference userRef, CommentRef;
    private FirebaseAuth mAuth;
    private String post_key, current_user_id, saveCurrentDate, saveCurrentTime, commentsRandomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        pb = findViewById(R.id.pb_comment);

        post_key = getIntent().getExtras().get("postKey").toString();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        CommentRef = FirebaseDatabase.getInstance().getReference().child("Comment").child(post_key).child("Comment");
        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view_comment);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        toolbar.setTitle("Comment");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        commentList = findViewById(R.id.commentList);
        commentInputText = findViewById(R.id.commentInput);
        postComment = findViewById(R.id.commentSend);

        commentList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        commentList.setLayoutManager(linearLayoutManager);

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            String userName = dataSnapshot.child("username").getValue().toString();
                            ValidateComment(userName);
                            commentInputText.setText(" ");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        pb.setVisibility(View.VISIBLE);


        FirebaseRecyclerAdapter<Comment, ViewHolderComment> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Comment, ViewHolderComment>(Comment.class, R.layout.row_comments, ViewHolderComment.class, CommentRef) {


            @Override
            protected void populateViewHolder(ViewHolderComment viewHolderComment, final Comment Comment, int position) {


                viewHolderComment.setComment(Comment.getComment());
                viewHolderComment.setTime(Comment.getTime());
                viewHolderComment.setDate(Comment.getDate());
                viewHolderComment.setUserName(Comment.getUsername());


                viewHolderComment.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                pb.setVisibility(View.GONE);
            }

            @Override
            public ViewHolderComment onCreateViewHolder(ViewGroup parent, int viewType) {


                ViewHolderComment viewHolderComment = super.onCreateViewHolder(parent, viewType);
                viewHolderComment.setOnclickListener(new ViewHolderComment.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {


                    }
                });


                return viewHolderComment;
            }
        };

        CommentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        commentList.setAdapter(firebaseRecyclerAdapter);


    }

    private void ValidateComment(String userName) {

        String commentText = commentInputText.getText().toString();
        if (TextUtils.isEmpty(commentText)) {

            Toast.makeText(this, "Please enter your answer", Toast.LENGTH_SHORT).show();

        } else {

            Calendar callForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
            saveCurrentDate = currentDate.format(callForDate.getTime());

            Calendar callForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
            saveCurrentTime = currentTime.format(callForTime.getTime());

            commentsRandomKey = current_user_id + saveCurrentDate + saveCurrentTime;


            HashMap commentsMap = new HashMap();
            commentsMap.put("uid", current_user_id);
            commentsMap.put("comment", commentText);
            commentsMap.put("date", saveCurrentDate);
            commentsMap.put("time", saveCurrentTime);
            commentsMap.put("username", userName);


            CommentRef.child(commentsRandomKey).updateChildren(commentsMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "Your comment has been recorded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

