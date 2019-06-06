package com.example.ptblr_1178.project_first.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.database.RegisterDbHelper;


public class DeleteFragment extends Fragment {


     EditText etDelete;
     Button btnDelete;


    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete, container, false);

        etDelete=view.findViewById(R.id.et_delete_id);
        btnDelete=view.findViewById(R.id.btn_delete_id);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();

            }
        });

        return view;
    }


    private void deleteTask(){


        //for deleting information into database
        // we have to create the object of the SQLiteOpenHelper class
        //we have subClass of SQLiteOpenHelperClass so..

        RegisterDbHelper registerDbHelper=new RegisterDbHelper(getActivity());

        // now, we create object of SQLite database
        // for real time app. we do not put writable or readable on the main thread.
        // we have to use BackGround thread to perform database opr. bcz it is a long running process

        SQLiteDatabase database=registerDbHelper.getWritableDatabase();



        String task=etDelete.getText().toString();

        registerDbHelper.deleteTask(task,database);
        registerDbHelper.close();

        etDelete.setText("");


        Toast.makeText(getActivity(), "TASK DELETED...", Toast.LENGTH_SHORT).show();





    }

}
