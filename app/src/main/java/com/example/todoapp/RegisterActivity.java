package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password, passwordConfirm;
    private Button register;
    private TextView login;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initFirebase();
        initFields();
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");
    }

    private void initFields() {
        firstName = findViewById(R.id.et_firstname);
        lastName = findViewById(R.id.et_lastname);
        email = findViewById(R.id.et_email_reg);
        password = findViewById(R.id.et_password_reg);
        passwordConfirm = findViewById(R.id.et_password_confirm);

        register = findViewById(R.id.btn_register);
        register.setOnClickListener(view -> {
            User user = new User();
            user.setEmail(email.getText().toString().trim());
            user.setFirstName(firstName.getText().toString().trim());
            user.setLastName(lastName.getText().toString().trim());

            registerUser(user, password.getText().toString().trim());
        });

        login = findViewById(R.id.tv_enter);
        login.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser(User user, String p) {
        if (user.getEmail().isEmpty() || p.isEmpty() ||
        firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty())
            Toast.makeText(RegisterActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
        else if (p.length() < 8)
            Toast.makeText(RegisterActivity.this, "Пароль должен содержать не менее 8 символов", Toast.LENGTH_SHORT).show();
        else if (!p.equals(passwordConfirm.getText().toString().trim()))
            Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
        else if (!user.getEmail().contains("@"))
            Toast.makeText(RegisterActivity.this, "Email другого формата", Toast.LENGTH_SHORT).show();
        else {
            auth.createUserWithEmailAndPassword(user.getEmail(), p).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    reference.setValue(auth.getCurrentUser().getUid());
                    reference.child(auth.getCurrentUser().getUid()).setValue(user);
                    Toast.makeText(RegisterActivity.this, "Регистация прошла успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(RegisterActivity.this, "Ошибка регистрации или пользователь уже существует", Toast.LENGTH_SHORT).show();
            });
        }
    }
}