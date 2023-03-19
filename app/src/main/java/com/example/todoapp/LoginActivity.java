package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView register;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initFirebase();
        initFields();
        //initSharedPreferences();
    }

    private void initSharedPreferences() {
        preferences = getPreferences(MODE_PRIVATE);
        String e = preferences.getString(Utils.SHARED_PREFERENCES_EMAIL, "");
        String p = preferences.getString(Utils.SHARED_PREFERENCES_PASSWORD, "");
        if (!(e.isEmpty() || p.isEmpty()))
            loginUser(e, p);
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();

        authStateListener = firebaseAuth -> {
            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                ;
            }
            else {

            }
        };
    }

    private void initFields() {
        email = findViewById(R.id.et_email_login);
        password = findViewById(R.id.et_password_login);

        login = findViewById(R.id.btn_enter);
        login.setOnClickListener(view -> {
            loginUser(email.getText().toString().trim(), password.getText().toString().trim());
        });

        register = findViewById(R.id.tv_regis);
        register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loginUser(String e, String p) {
        if (e.isEmpty() || p.isEmpty())
            Toast.makeText(LoginActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
        else {
            auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    initPreferences(e, p);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Пароль не верный или пользователя не существует", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void initPreferences(String e, String p) {
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.SHARED_PREFERENCES_EMAIL, e);
        editor.putString(Utils.SHARED_PREFERENCES_PASSWORD, p);
        editor.apply();
    }
}