package com.example.ptblr_1178.project_first.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.database.RegisterDbHelper;
import com.example.ptblr_1178.project_first.model.RegisterModel;

import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.CONFIRMPAASWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.EMAIL;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.NAME;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PASSWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PHONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // creating instance of INTERFACE

    onDbOpListner dbOpListner;

    RegisterDbHelper sqliteHelper;


    Button btn_login;
    EditText et_phone, et_paasword;
    TextView tv_text;

    public HomeFragment() {
        // Required empty public constructor
    }


    // For this fragment to communicate with main activity we have to create INTERFACE

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        et_phone = view.findViewById(R.id.et_phoneno);
        et_paasword = view.findViewById(R.id.et_paasword);


        btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        tv_text = view.findViewById(R.id.tv_register);
        tv_text.setOnClickListener(this);

        sqliteHelper = new RegisterDbHelper(getContext());


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_register:
                dbOpListner.dBOpPerformed(1);
                break;

            case R.id.btn_login:
                dbOpListner.dBOpPerformed(2);

                break;


        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        //dbOpListner is the instance of the interface

        try {
            dbOpListner = (onDbOpListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement the interface method");
        }
    }

    public interface onDbOpListner {
        public void dBOpPerformed(int method);
    }
}
