package com.example.notepad.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.notepad.R;
import com.example.notepad.models.Task;

import java.util.List;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskViewAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tasks, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        Task task = taskList.get(i);
        taskViewHolder.isDone.setChecked(task.isDone());
        taskViewHolder.createDate.setText(String.valueOf(task.getCreateDate()));
        taskViewHolder.createDate.setTag(task.getCategoryId());
        taskViewHolder.description.setText(task.getDescription());
        taskViewHolder.description.setTag(task.getId());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

     class  TaskViewHolder extends RecyclerView.ViewHolder{
        CheckBox isDone;
        TextView description;
        TextView createDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

         createDate =   itemView.findViewById(R.id.task_create_date_tv);
         description = itemView.findViewById(R.id.task_description_tv);
         isDone = itemView.findViewById(R.id.task_isDone_chb);
        }
    }
}
