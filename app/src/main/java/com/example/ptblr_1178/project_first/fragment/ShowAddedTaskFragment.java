package com.example.ptblr_1178.project_first.fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.adapter.TaskAdapter;
import com.example.ptblr_1178.project_first.contract.RegisterContract;
import com.example.ptblr_1178.project_first.database.RegisterDbHelper;
import com.example.ptblr_1178.project_first.model.TaskModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAddedTaskFragment extends Fragment implements HomeFragment.onDbOpListner {

    // for recycler view we need 3 things...
    // 1- layout manager : it add items om the taskModelList into rows and columns.
    // 2- view Holder : each item is represented  using a class called the view holder.
    // 3- adapter : its responsibility is to place the view holder in correct position.

    // ADAPTER is JAVA file and inside ADAPTER(java file) we create class for view holder.

    private static final String TAG = "ShowAddedTaskFragment";

    public List<TaskModel> taskModelList = new ArrayList<>();
    TaskAdapter adapter;
    TextView tv1, tv2;
    TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fab,fab2;
    private Context mCtx;

    public ShowAddedTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_added_task, container, false);
        this.mCtx = getContext();

        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        taskAdapter = new TaskAdapter(getActivity(), taskModelList);
        recyclerView.setAdapter(taskAdapter);

        tv1 = view.findViewById(R.id.tv_name);
        tv2 = view.findViewById(R.id.tv_desc);

        fab = view.findViewById(R.id.floatingActionButton);
        fab2=view.findViewById(R.id.floatingActionButton2);


        readTask();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_id, new AddTaskFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_id, new UpdateFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;


    }


    // TO READ DATA FROM DATABASE WE CREATE METHOD


    public void readTask() {

        String tname = "";
        String tDesc = "";
        RegisterDbHelper registerDbHelper = new RegisterDbHelper(getActivity());

        SQLiteDatabase sqLiteDatabase = registerDbHelper.getWritableDatabase();

        Cursor cursor = registerDbHelper.readTask(sqLiteDatabase);

        while (cursor.moveToNext()) {

            String taskName = cursor.getString(cursor.getColumnIndex(RegisterContract.ReadData.TASK_NAME));
            String taskDesc = cursor.getString(cursor.getColumnIndex(RegisterContract.ReadData.TASK_DES));

//            tname = tname + "\n\n" + taskName;
//            tDesc = tDesc + "\n\n" + taskDesc;


            TaskModel taskModel = new TaskModel(taskName, taskDesc);
            taskModelList.add(taskModel);
            Log.d(TAG, "" + taskName);
            Log.d(TAG, "" + taskDesc);
            Log.d(TAG, "taskModelList " + taskModelList.size());
        }
        taskAdapter.notifyDataSetChanged();
        //tv1.setText(tname);
        // tv2.setText(tDesc);


    }


    @Override
    public void dBOpPerformed(int method) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
    }
}
