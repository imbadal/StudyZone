package com.statusstock.studyzone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView userName, userProfileName, userRole, userAbout, userDOB, userEmail;

    private DatabaseReference profileUserRef;
    private FirebaseAuth mAuth;
    private String current_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        profileUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id);

        userName = findViewById(R.id.userName);
        userProfileName = findViewById(R.id.userProfileName);
        userRole = findViewById(R.id.userRole);
        userAbout = findViewById(R.id.userAbout);
        userEmail = findViewById(R.id.userEmail);

        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String myUserName = dataSnapshot.child("username").getValue().toString();
                    String myProfileName = dataSnapshot.child("fullname").getValue().toString();
                    String myProfileStatus = dataSnapshot.child("about").getValue().toString();
                    String myEmail = dataSnapshot.child("email").getValue().toString();
                    String myRole = dataSnapshot.child("role").getValue().toString();

                    userName.setText("@" + myUserName);
                    userProfileName.setText(myProfileName);
                    userAbout.setText(myProfileStatus);
                    userRole.setText(myRole);
                    userEmail.setText(myEmail);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
