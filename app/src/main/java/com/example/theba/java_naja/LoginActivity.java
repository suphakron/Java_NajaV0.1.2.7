package com.example.theba.java_naja;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookWebFallbackDialog;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private EditText idField;
    private EditText pwdField;
    private Button logbut;

    public CallbackManager mCallbackManager;
    private static final String TAG = "Face-LOG";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitleTextColor(Color.parseColor("#d78c01"));
        getSupportActionBar().setTitle(Html.fromHtml("<H1 style='color:red;text-align:center;'>Java Naja</H1>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        mAuth = FirebaseAuth.getInstance();

        idField = (EditText) findViewById(R.id.iduser);
        pwdField = (EditText) findViewById(R.id.pwduser);

        logbut = (Button) findViewById(R.id.Log_button);

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

//                if(AccessToken.getCurrentAccessToken() != null){
//                    Toast.makeText(LoginActivity.this, AccessToken.getCurrentAccessToken().getExpires().toString(), Toast.LENGTH_SHORT).show();
//                }
                if(firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
            }
        };

        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthLogin();
            }
        });

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Authentication Success.",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                // //LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Authentication Cancel.",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "facebook:onCancel");
                // ...
                updateUI(null);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Authentication error.",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "facebook:onError", error);
                // ...
                updateUI(null);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void AuthLogin() {
        String Email = idField.getText().toString();
        String Pwd = pwdField.getText().toString();

        if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pwd) ) {

            Toast.makeText(LoginActivity.this, "Field is Empty.", Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(Email, Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Sing-in Problem", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private void updateUI(FirebaseUser user){
        if(user != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser CurrentUser = mAuth.getCurrentUser();
        updateUI(CurrentUser);
        mAuth.addAuthStateListener(mAuthListener);
    }
}
