package com.example.ptblr_1178.project_first.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.fragment.UpdateFragment;
import com.example.ptblr_1178.project_first.model.TaskModel;

import java.util.List;

/**
 * Created by ptbr-1167 on 23/5/19.
 */

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<TaskModel> taskList;


    public TaskAdapter(Context mCtx, List<TaskModel> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        TaskModel t = taskList.get(position);
        holder.textViewName.setText(t.getName());
        holder.textViewDesc.setText(t.getDesc());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDesc;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_name);
            textViewDesc = itemView.findViewById(R.id.tv_desc);

        }

    }


}
