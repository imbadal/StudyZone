package com.statusstock.studyzone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ForumActivity extends AppCompatActivity {

    private EditText question, description;
    private Button button;
    private String qsn, desc;

    private ProgressDialog loadingBar;

    private DatabaseReference userRef, forumRef;
    private FirebaseAuth mAuth;

    private String saveCurrentDate, saveCurrentTime, postRandomName, current_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        loadingBar = new ProgressDialog(this);

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        forumRef = FirebaseDatabase.getInstance().getReference().child("Forum");
        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        question = findViewById(R.id.question);
        description = findViewById(R.id.description);

        button = findViewById(R.id.ask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateQuestion();

                SaveForumInfoToDatabase();

            }
        });

    }

    private void SaveForumInfoToDatabase() {

        userRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String userFullName = dataSnapshot.child("fullname").getValue().toString();

                    HashMap forumMap = new HashMap();
                    forumMap.put("uid", current_user_id);
                    forumMap.put("date", saveCurrentDate);
                    forumMap.put("time", saveCurrentTime);
                    forumMap.put("qsn", qsn);
                    forumMap.put("desc", desc);
                    forumMap.put("ufulname", userFullName);

                    forumRef.child(current_user_id + postRandomName).updateChildren(forumMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (task.isSuccessful()) {

                                SendUserToMainActivity();
                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();


                            }

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ValidateQuestion() {


        qsn = question.getText().toString();
        desc = description.getText().toString();

        if (TextUtils.isEmpty(qsn)) {

            loadingBar.dismiss();
            Toast.makeText(this, "Please enter your Question", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(desc)) {
            loadingBar.dismiss();
            Toast.makeText(this, "Please enter your description", Toast.LENGTH_SHORT).show();

        } else {

            loadingBar.setTitle("Adding to Forum");
            loadingBar.setMessage("Please wait, while we are updating your question...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            StoringToFirebase();
        }

    }

    private void StoringToFirebase() {

        Calendar callForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(callForDate.getTime());

        Calendar callForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(callForTime.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}
