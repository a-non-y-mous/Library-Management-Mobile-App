package com.example.librarymanagement;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName,regUsername,regPassword,regEmail,regPhone,regRoll,regLibrary;
    Button regBtn,regToLogInBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regPassword = findViewById(R.id.reg_password);
        regEmail = findViewById(R.id.reg_email);
        regPhone = findViewById(R.id.reg_phone);
        regRoll = findViewById(R.id.reg_rollno);
        regLibrary = findViewById(R.id.reg_libraryid);
        regBtn = findViewById(R.id.reg_go);
        regToLogInBtn = findViewById(R.id.reg_signIn);
    }

    private Boolean validateName(){
     String val=regName.getEditText().getText().toString();
     if(val.isEmpty()){
         regName.setError("Field cannot be empty");
         return false;
     }else if(val.length()>=30) {
         regUsername.setError("Name should not exceed 30 characters");
         return false;
     }
     else{
         regName.setError(null);
         regName.setErrorEnabled(false);
         return true;
     }
    }
    private Boolean validateUserName(){
        String val=regUsername.getEditText().getText().toString();
        String noWhiteSpace="(\\A\\w{4,20}\\z)";
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }  else if(val.length()>=15) {
            regUsername.setError("Username should not exceed 15 characters");
            return false;
        }
         else if(!val.matches(noWhiteSpace)){
             regUsername.setError("No spaces allowed");
             return false;
        }
        else{
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val=regEmail.getEditText().getText().toString();
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=50) {
            regUsername.setError("Email id should not exceed 50 characters");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid Email id");
            return(false);
        }
        else{
            regEmail.setError(null);
            return true;
        }
    }
    private Boolean validatePhone(){
        String val=regPhone.getEditText().getText().toString();
        String noWhiteSpace="(\\A\\w{4,20}\\z)";
        if(val.isEmpty()){
            regPhone.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=11) {
            regUsername.setError("Mobile number is invalid");
            return false;
        }
        else if(!val.matches(noWhiteSpace))
        {
            regPhone.setError("Spaces not allowed");
            return false;
        }
        else{
            regPhone.setError(null);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val=regPassword.getEditText().getText().toString();
        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()<8||val.length()>30){
            regPassword.setError("Password length must be between 8 and 30 characters long");
            return false;
        }
        else{
            regPassword.setError(null);
            return true;
        }
    }
    private Boolean validateRoll(){
        String val=regRoll.getEditText().getText().toString();
        String noWhiteSpace="(\\A\\w{4,20}\\z)";
        if(val.isEmpty()){
            regRoll.setError("Field cannot be empty");
            return (false);
        }
        else if(!val.matches(noWhiteSpace)) {
            regRoll.setError("No spaces allowed");
            return (false);
        }else if(val.length()!=12) {
            regUsername.setError("Roll number should be 12 characters");
            return false;
        }
        else{
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateLibrary(){
        String val=regLibrary.getEditText().getText().toString();
        String noWhiteSpace="(\\A\\w{4,20}\\z)";
        if(val.isEmpty()){
            regLibrary.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
           regLibrary.setError("Spaces not allowed");
           return false;
        }
        else{
            regName.setError(null);
            return true;
        }
    }
    public void registerUser(View view) {

           rootNode= FirebaseDatabase.getInstance();
           reference=rootNode.getReference("users");
           if(!validateName()|!validateEmail()|!validateLibrary()|!validatePassword()|!validatePhone()|!validateRoll()|!validateUserName())
           {
               return;
           }
           String name=regName.getEditText().getText().toString();
           String username=regUsername.getEditText().getText().toString();
           String password=regPassword.getEditText().getText().toString();
           String email=regEmail.getEditText().getText().toString();
           String phone=regPhone.getEditText().getText().toString();
           String library=regLibrary.getEditText().getText().toString();
           String roll=regRoll.getEditText().getText().toString();
           UserHelperClass helperClass=new UserHelperClass(name,username,password,email,phone,roll,library);
           reference.child(username).setValue(helperClass);
        }
}