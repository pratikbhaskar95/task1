package com.example.ptblr_1178.project_first.contract;

import android.provider.BaseColumns;

/**
 * Created by ptblr-1178 on 20/5/19.
 */

public final class RegisterContract {

    // creating constructor so that it doesnt get accidently initiallized

    private RegisterContract(){}

    // creating Inner class to define DataBase Schema

    public static class RegisterEntry implements BaseColumns{

        public static final String TABLE_NAME="registration_info";
        public static final String NAME="name";
        public static final String PHONE="phone";
        public static final String EMAIL="email";
        public static final String PASSWORD="paasword";
        public static final String CONFIRMPAASWORD="confirmPaasword";

//        public static final String TABLE_NAME2="task_info";
//        public static final String TASK_NAME="t_name";
//        public static final String TASK_DES="task_des";
    }

    public static class ReadData implements BaseColumns{

        public static final String TABLE_NAME2="task_info";
        public static final String TASK_NAME="t_name";
        public static final String TASK_DES="task_des";
    }

}
