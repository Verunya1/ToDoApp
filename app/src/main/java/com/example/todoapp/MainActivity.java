package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.adapter.TasksAdapter;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView logout;
    private ImageView image;
    private TextView name;
    private TextView email;
    private RecyclerView tasks;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initFields();
        setUserData();
    }

    private void initFields() {
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        image = findViewById(R.id.iv_image);
        name = findViewById(R.id.tv_name);
        email = findViewById(R.id.tv_email);

        List<Task> list = new ArrayList<>();
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", true));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", true));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Сделать домашку", true));
        list.add(new Task("Сделать домашку", false));
        list.add(new Task("Что-то слишком дофига домашки, пойду поем", true));

        tasks = findViewById(R.id.rv_tasks);
        TasksAdapter adapter = new TasksAdapter(list);
        tasks.setAdapter(adapter);

        logout = findViewById(R.id.tv_logout);
        logout.setOnClickListener(view -> {
            signOut();
        });
    }

    private void setUserData() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            email.setText(user.getEmail());
            reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User currentUser = snapshot.getValue(User.class);
                    if (currentUser != null) {
                        email.setText(currentUser.getEmail());
                        name.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void signOut() {
        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}