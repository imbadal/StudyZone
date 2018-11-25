package com.statusstock.studyzone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView switchToLogin;
    private EditText userEmail, userPassword, userConfirmPassword;
    private Button createAccount;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.register_email);
        userPassword = findViewById(R.id.register_password);
        userConfirmPassword = findViewById(R.id.register_confirm_password);
        createAccount = findViewById(R.id.register_create_account);
        loadingBar = new ProgressDialog(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

        switchToLogin = findViewById(R.id.switchToLogin);
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToLoginActivity();
            }
        });
    }

    private void CreateNewAccount() {

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String confirmPassword = userConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(confirmPassword)) {

            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();

        } else if (!password.equals(confirmPassword)) {

            Toast.makeText(this, "Your password do not match", Toast.LENGTH_SHORT).show();

        } else {

            loadingBar.setTitle("Creating new account");
            loadingBar.setMessage("Please wait while we are creating the account for you");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(false);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        loadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Your Account has been created...", Toast.LENGTH_SHORT).show();

                        SendUserToSetUpActivity();

                    } else {

                        loadingBar.dismiss();
                        String message = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Something went wrong..." + message, Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            SendUserToMainActivity();

        }

    }


    private void SendUserToSetUpActivity() {

        Intent setUpIntent = new Intent(RegisterActivity.this, SetUpActivity.class);
        setUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setUpIntent);
        finish();

    }

    private void SwitchToLoginActivity() {

        Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(registerIntent);
        finish();
    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}