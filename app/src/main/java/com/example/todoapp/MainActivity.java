package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.adapter.TasksAdapter;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton actionButton;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private List<Task> list = new ArrayList<>();

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

        tasks = findViewById(R.id.rv_tasks);

        actionButton = findViewById(R.id.ab_add);
        actionButton.setOnClickListener(view -> addTask());

        logout = findViewById(R.id.tv_logout);
        logout.setOnClickListener(view -> signOut());
    }

    private void addTask() {
        EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(16, 0, 16, 0);
        input.setLayoutParams(lp);
        input.setPadding(8, 8, 8, 8);
        input.setHint("Задача");

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Новая задача")
                .setMessage("Ведите текст задачи")
                .setView(input)
                .setPositiveButton("Сохранить", (dialogInterface, i) -> {
                    list = new ArrayList<>();
                    reference.child(auth.getCurrentUser().getUid()).child("tasks")
                            .push().setValue(new Task(input.getText().toString().trim(), false));
                })
                .setNegativeButton("Отменить", null)
                .create()
                .show();
    }

    private void setUserData() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            reference.child(user.getUid()).getRef().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User currentUser = snapshot.getValue(User.class);
                    Log.d("MAIN", currentUser.toString());
                    email.setText(currentUser.getEmail());
                    name.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
                    for (DataSnapshot ds : snapshot.child("tasks").getChildren()) {
                        Log.d("TASKMY", ds.getValue(Task.class).getTask());
                        list.add(ds.getValue(Task.class));
                        TasksAdapter adapter = new TasksAdapter(list);
                        tasks.setAdapter(adapter);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("MAIN", error.getMessage());
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