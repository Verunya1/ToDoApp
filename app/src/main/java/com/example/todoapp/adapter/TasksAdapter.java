package com.example.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.models.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    private List<Task> list;

    class TaskHolder extends RecyclerView.ViewHolder {

        private CheckBox task;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.cb_task);
        }
    }

    public TasksAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        TaskHolder taskHolder = new TaskHolder(view);
        return taskHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task t = list.get(position);

        holder.task.setChecked(t.isDone());
        holder.task.setText(t.getTask());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
