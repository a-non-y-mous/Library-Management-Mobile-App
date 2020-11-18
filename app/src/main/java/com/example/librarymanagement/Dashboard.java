package com.example.librarymanagement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    Button callSignUp;
    TextView logoText, sloganText, forgotText, goText;
    TextInputLayout usernameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        callSignUp = findViewById(R.id.signup_screen);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        forgotText = findViewById(R.id.forgot_name);
        goText = findViewById(R.id.go_name);
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, SignUp.class);
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(logoText, "logo_text");
                pairs[1] = new Pair<View, String>(sloganText, "slogan_text");
                pairs[2] = new Pair<View, String>(forgotText, "forgot_text");
                pairs[3] = new Pair<View, String>(goText, "go_text");
                pairs[4] = new Pair<View, String>(usernameText, "username_text");
                pairs[5] = new Pair<View, String>(passwordText, "password_text");


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }

        });
    }

    private Boolean validateUsername() {
        String val = usernameText.getEditText().getText().toString();

        if (val.isEmpty()) {
            usernameText.setError("Field can not be empty");
            return false;
        } else {
            usernameText.setError(null);
            usernameText.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = passwordText.getEditText().getText().toString();
        if (val.isEmpty()) {
            passwordText.setError("Field can not be empty");
            return false;
        } else {
            passwordText.setError(null);
            passwordText.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = usernameText.getEditText().getText().toString().trim();
        final String userEnteredPassword = passwordText.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usernameText.setError(null);
                    usernameText.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        usernameText.setError(null);
                        usernameText.setErrorEnabled(false);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneFromDB = snapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                        String libraryFromDB = snapshot.child(userEnteredUsername).child("library").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String rollFromDB = snapshot.child(userEnteredUsername).child("roll").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("phone", phoneFromDB);
                        intent.putExtra("library", libraryFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("roll", rollFromDB);
                        startActivity(intent);
                    } else {
                        passwordText.setError("Wrong Password");
                    }
                } else {
                    usernameText.setError("No such user exists");
                    passwordText.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}