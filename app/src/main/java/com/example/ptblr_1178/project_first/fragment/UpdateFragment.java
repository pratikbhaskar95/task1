package com.example.ptblr_1178.project_first.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.database.RegisterDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    EditText et_update_task,et_update_desc;
    Button btn_update,btn_delete;


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);

        et_update_task=view.findViewById(R.id.update_task_id);
        et_update_desc=view.findViewById(R.id.update_desc_id);


        btn_delete=view.findViewById(R.id.btn_delete_id2);
        btn_update=view.findViewById(R.id.btn_update);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_id, new DeleteFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask();

            }
        });


        return view;
    }

    // method to update contact
    // we will set this method on the onClick button of update update fragment

    private void updateTask(){

        String task_name=et_update_task.getText().toString();
        String task_desc=et_update_desc.getText().toString();

        //for updating information into database
        // we have to create the object of the SQLiteOpenHelper class
        //we have subClass of SQLiteOpenHelperClass so..

        RegisterDbHelper registerDbHelper= new RegisterDbHelper(getActivity());

        // now, we create object of SQLite database
        // for real time app. we donot put writable or readable on the main thread.
        // we have to use BackGround thread to perform database opr. bcz it is a long running operation

        SQLiteDatabase database=registerDbHelper.getWritableDatabase();


        // Now, update data into the table
        // updateContact is method in SQLiteOpeHelper SubClass.

        registerDbHelper.updateTask(task_name,task_desc,database);

        registerDbHelper.close();

        Toast.makeText(getActivity(), "task updated...", Toast.LENGTH_SHORT).show();

        et_update_task.setText("");
        et_update_desc.setText("");



    }

}
