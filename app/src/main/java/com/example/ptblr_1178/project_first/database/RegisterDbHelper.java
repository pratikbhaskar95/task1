package com.example.ptblr_1178.project_first.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ptblr_1178.project_first.contract.RegisterContract;
import com.example.ptblr_1178.project_first.model.RegisterModel;
import com.example.ptblr_1178.project_first.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.CONFIRMPAASWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.EMAIL;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.NAME;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PASSWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PASSWORD;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.PHONE;
import static com.example.ptblr_1178.project_first.contract.RegisterContract.RegisterEntry.TABLE_NAME;

/**
 * Created by ptblr-1178 on 20/5/19.
 */

public class RegisterDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "register_db";

    public static final int DATABASE_VERSION = 1;

//    creating object for database
    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + NAME + " text," + PHONE +
            " number," + EMAIL + " text," + PASSWORD +
            " number," + CONFIRMPAASWORD + " number);";


    //creating table
    //while creating table make sure of the spaces & comma's while declaring the datatype of the entry(SQL QUERY)
    public static final String CREATE_TABLE2 = "create table " + RegisterContract.ReadData.TABLE_NAME2 +
            "(" + RegisterContract.ReadData.TASK_NAME + " text," + RegisterContract.ReadData.TASK_DES +
            " text );";
    public static final String DROP_TABLE = "drop if exists" + TABLE_NAME;
    public static final String DROP_TABLE2 = "drop if exists" + RegisterContract.ReadData.TABLE_NAME2;
    public SQLiteDatabase sqLiteDatabase;


//    creating arraylist
    List<RegisterModel> registerModelList = new ArrayList<RegisterModel>();

    List<TaskModel> taskModelList = new ArrayList<TaskModel>();

//    creating methods to insert the data from taskModelList

    public RegisterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertRegisterList(RegisterModel registerModel) {
        try {
            sqLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", registerModel.getName());
            contentValues.put("PHONE", registerModel.getPhoneno());
            contentValues.put("EMAIL", registerModel.getEmail());
            contentValues.put("PASSWORD", registerModel.getPassword());
            contentValues.put("CONFIRMPAASWORD", registerModel.getCpassword());
            sqLiteDatabase.insert("CREATE_TABLE", null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


    //creating default constructor bcz it doesnt have default constructor

    public void insertTaskList(TaskModel taskModel) {

        try {
            sqLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("TASK_NAME", taskModel.getName());
            contentValues.put("TASK_DES", taskModel.getDesc());
            sqLiteDatabase.insert("CREATE_TABLE2", null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

        sqLiteDatabase.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(DROP_TABLE);

        sqLiteDatabase.execSQL(DROP_TABLE2);
        onCreate(sqLiteDatabase);

    }


    // method to put information into DataBase...

    public void addContact(String name, String phone, String email, String paasword, String cnfPaasword, SQLiteDatabase database)

    {

        //we create object of content values for putting information into the database

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(PHONE, phone);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, paasword);
        contentValues.put(CONFIRMPAASWORD, cnfPaasword);

        // INSERT() will add ROW to the table.....

        database.insert(TABLE_NAME, null, contentValues);
        Log.e("TAG", "addContact: "+ name+phone+email);

    }


    public void addTask(String taskname, String description, SQLiteDatabase database)

    {

        //we create object of content values for putting information into the database

        ContentValues contentValues = new ContentValues();

        contentValues.put(RegisterContract.ReadData.TASK_NAME, taskname);

        contentValues.put(RegisterContract.ReadData.TASK_DES, description);

        // INSERT() will add ROW to the table.....

        database.insert(RegisterContract.ReadData.TABLE_NAME2, null, contentValues);
        Log.d("database operation","ONE ROW INSERTED");

    }

    // METHOD TO READ DATA

    public Cursor readTask(SQLiteDatabase sqLiteDatabase) {
        // specify which columns to read

        String[] projection = {RegisterContract.ReadData.TASK_NAME,
                RegisterContract.ReadData.TASK_DES};

        Cursor cursor = sqLiteDatabase.query(RegisterContract.ReadData.TABLE_NAME2, projection,
                null, null, null, null, null);


        return cursor;


    }


    // METHOD TO UPDATE

    public void updateTask(String task_name,String task_desc,SQLiteDatabase database) {


        // we are going to update task desccription on the basis of task name

        ContentValues contentValues = new ContentValues();

        contentValues.put(RegisterContract.ReadData.TASK_NAME, task_name);

        contentValues.put(RegisterContract.ReadData.TASK_DES, task_desc);

        // now, UPDATE ROW ON THE BASIS OF TASK NAME
        // WE PROVIDE CONDITION (SELECTIION)

        String selection = RegisterContract.ReadData.TASK_NAME + " =? " + task_name;

        database.update(RegisterContract.ReadData.TABLE_NAME2, contentValues, selection, null);


//         updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
    }


        // METHOD TO DELETE TASK FROM DATABASE

        public void deleteTask(String task,SQLiteDatabase database)
    {


        // specify condition for deleting a row from database....

        // here selection and selection arguments are declared in single statement

        String selection= RegisterContract.ReadData.TASK_NAME+" = "+task;
        database.delete(RegisterContract.ReadData.TABLE_NAME2,selection,null);


    }





}



