package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todoapp.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password, passwordConfirm;
    private Button register;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initFields();
    }

    private void initFields() {
        firstName = findViewById(R.id.et_firstname);
        lastName = findViewById(R.id.et_lastname);
        email = findViewById(R.id.et_email_reg);
        password = findViewById(R.id.et_password_reg);
        passwordConfirm = findViewById(R.id.et_password_confirm);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        login = findViewById(R.id.tv_enter);
        login.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}