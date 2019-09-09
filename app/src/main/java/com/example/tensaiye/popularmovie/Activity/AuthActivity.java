package com.example.tensaiye.popularmovie.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tensaiye.popularmovie.R;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

// References for Firebase Google Authentication: https://firebase.google.com/docs/auth/android/google-signin & https://www.youtube.com/watch?v=FtIc5UYXeKk&t=700s
public class AuthActivity extends AppCompatActivity {

    static final int GOOGLE_SIGN = 123;
    private FirebaseAuth mAuth;
    Button signIn, signOut, continue_btn;
    TextView text, emailAuth, emailText;
    ImageView image;
    ProgressBar progressBar;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        signIn = findViewById(R.id.login);
        signOut = findViewById(R.id.logout);
        continue_btn = findViewById(R.id.Continue);

        text = findViewById(R.id.text);
        progressBar = findViewById(R.id.progress_circular);
        emailAuth = findViewById(R.id.Email);
        emailText = findViewById(R.id.emailText);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED,
                android.graphics.PorterDuff.Mode.MULTIPLY);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInGoogle();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MovieIntent = new Intent(AuthActivity.this, MainActivity.class);
                AuthActivity.this.startActivity(MovieIntent);
            }
        });
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void SignInGoogle() {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, GOOGLE_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("SignError", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent MovieIntent = new Intent(AuthActivity.this, MainActivity.class);
                            AuthActivity.this.startActivity(MovieIntent);

                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.auth_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            emailAuth.setText(email);
            emailAuth.setVisibility(View.VISIBLE);
            emailText.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.VISIBLE);
            continue_btn.setVisibility(View.VISIBLE);
        } else {
            signIn.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.INVISIBLE);
            emailAuth.setVisibility(View.INVISIBLE);
            emailText.setVisibility(View.INVISIBLE);
            continue_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        googleSignInClient.signOut().addOnCompleteListener(this, task -> updateUI(null));
    }
}
