package com.example.ptblr_1178.project_first.fragment;


import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    EditText editTextName,edittextDesc;

    Button buttonSave;


    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_task, container, false);

        editTextName= view.findViewById(R.id.et_tname);
        edittextDesc= view.findViewById(R.id.et_desc);

        buttonSave = view.findViewById(R.id.button);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskName = editTextName.getText().toString();
                String taskDesc = edittextDesc.getText().toString();

                //for putting information into database
                //we have to create the object of the SQLiteOpenHelper class
                //we have subClass of SQLiteOpenHelperClass so..

                RegisterDbHelper registerDbHelper = new RegisterDbHelper(getActivity());

                //now,we,create object of SQLite database
                // for real time app. we do not put writable or readable on the main thread.
                // we have to use BackGround thread to perform database opr. bcz it is a long running operation

                SQLiteDatabase database = registerDbHelper.getWritableDatabase();

                // Now,put data into the table
                // addContact is method in SQLiteOpeHelper SubClass.

                registerDbHelper.addTask(taskName, taskDesc,  database);

                // now , close the database connnnection

                registerDbHelper.close();

                editTextName.setText("");
                edittextDesc.setText("");

                Toast.makeText(getActivity(), "" + "TASK SUCCESSFULLY ADDED...", Toast.LENGTH_SHORT).show();



            }
        });

        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
