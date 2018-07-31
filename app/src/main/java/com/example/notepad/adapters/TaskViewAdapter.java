package com.example.notepad.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad.DAL.QueryOperations;
import com.example.notepad.R;
import com.example.notepad.models.Task;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.notepad.MainActivity.db;

public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private Context context;

    public TaskViewAdapter(Context context, List<Task> taskList) {
        this.context = context;
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


        taskViewHolder.isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                QueryOperations queryOperations = new QueryOperations(db);

                queryOperations.done(task.getId(), b)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe();
            }
        });
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
