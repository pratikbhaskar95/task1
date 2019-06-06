package com.example.ptblr_1178.project_first.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ptblr_1178.project_first.R;
import com.example.ptblr_1178.project_first.database.RegisterDbHelper;
import com.example.ptblr_1178.project_first.fragment.HomeFragment;
import com.example.ptblr_1178.project_first.fragment.RegisterFragment;
import com.example.ptblr_1178.project_first.fragment.ShowAddedTaskFragment;
import com.example.ptblr_1178.project_first.model.RegisterModel;

import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.CONFIRMPAASWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.EMAIL;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.NAME;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PASSWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PHONE;

public class MainActivity extends AppCompatActivity implements HomeFragment.onDbOpListner{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Here we add the home fragment to the Main activity

        if (findViewById(R.id.fragment_container_id) != null) {
            if (savedInstanceState != null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id .fragment_container_id, homeFragment).commit();
        }

    }

    @Override
    public void dBOpPerformed(int method) {

        switch (method)
        {
            case 1:
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_id,new RegisterFragment()).
                         addToBackStack(null).commit();
                break;

            case 2:

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_id,new ShowAddedTaskFragment()).
                        addToBackStack(null).commit();


                break;
        }
    }
}
