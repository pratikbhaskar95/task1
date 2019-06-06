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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    EditText name, mobile, email, password,cpassword;
    Button btn_reg;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        name = view.findViewById(R.id.et_rname);
        mobile = view.findViewById(R.id.et_rphone);
        email = view.findViewById(R.id.et_emailid);
        password = view.findViewById(R.id.et_paasword);
        cpassword = view.findViewById(R.id.et_cnfpwd);
        btn_reg = view.findViewById(R.id.btn_register);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameValue = name.getText().toString();
                String phoneValue = mobile.getText().toString();
                String emailValue = email.getText().toString();
                String paaswordValue = password.getText().toString();
                String cnfPaaswordValue = cpassword.getText().toString();


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

                registerDbHelper.addContact(nameValue, phoneValue, emailValue, paaswordValue, cnfPaaswordValue, database);

                // now , close the database connnnection

                registerDbHelper.close();

                name.setText("");
                mobile.setText("");
                email.setText("");
                password.setText("");
                cpassword.setText("");

                Toast.makeText(getActivity(), "" + "REGISTRATION SUCCESSFUL..", Toast.LENGTH_SHORT).show();


            }
        });


        return view;
    }

}
