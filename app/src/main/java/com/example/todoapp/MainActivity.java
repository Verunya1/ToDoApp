package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.adapter.TasksAdapter;
import com.example.todoapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name;
    private TextView email;
    private RecyclerView tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initFields();

        test();
    }

    private void initFields() {
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
    }

    private void test() {
    }
}