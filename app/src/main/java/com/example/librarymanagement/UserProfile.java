package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNo, username, rollNo, libraryId;
    TextView fullNameLabel,userNameLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);
        fullName=findViewById(R.id.name_profile);
        email=findViewById(R.id.email_profile);
        rollNo =findViewById(R.id.rollno_profile);
        phoneNo=findViewById(R.id.phone_profile);
        username=findViewById(R.id.username_profile);
        libraryId =findViewById(R.id.libraryid_profile);
        fullNameLabel=findViewById(R.id.fullname_field);
        userNameLabel=findViewById(R.id.username_field);
        showAllUserData();
    }
    private void showAllUserData() {
        Intent intent=getIntent();
        String user_username=intent.getStringExtra("username");
        String user_roll=intent.getStringExtra("roll");
        String user_email=intent.getStringExtra("email");
        String user_library=intent.getStringExtra("library");
        String user_phone=intent.getStringExtra("phone");
        String user_name=intent.getStringExtra("name");
        fullNameLabel.setText(user_name);
        userNameLabel.setText(user_username);
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phone);
        username.getEditText().setText(user_username);
        rollNo.getEditText().setText(user_roll);
        libraryId.getEditText().setText(user_library);
    }
}