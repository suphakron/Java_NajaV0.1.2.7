package com.example.theba.java_naja;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText Email, FName, LName, Password, ConfirmPassword;
    private String userEmail, userFname, userLname, userPwd, userConPwd;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();

        Email = (EditText) findViewById(R.id.emailuser);
        FName = (EditText) findViewById(R.id.fName);
        LName = (EditText) findViewById(R.id.lName);
        Password = (EditText) findViewById(R.id.pwduser);
        ConfirmPassword = (EditText) findViewById(R.id.pwduser2);
        Submit = (Button) findViewById(R.id.accept);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = Email.getText().toString();
                userFname = FName.getText().toString();
                userLname = LName.getText().toString();
                userPwd = Password.getText().toString();
                userConPwd = ConfirmPassword.getText().toString();

                if(userPwd.equals(userConPwd)) {
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                    
                    //setting rule can update data
                    //***adding info user***

                    Map newPost = new HashMap();
                    newPost.put("Email", userEmail);
                    newPost.put("FName", userFname);
                    newPost.put("LName", userLname);
                    newPost.put("Password", userPwd);
                    current_user_db.setValue(newPost);

                    Toast.makeText(EditProfileActivity.this,"ทำการบันทึกประวัติของคุณแล้ว",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditProfileActivity.this,"รหัสผ่านไม่ตรงกัน กรุณาตรวจสอบ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
