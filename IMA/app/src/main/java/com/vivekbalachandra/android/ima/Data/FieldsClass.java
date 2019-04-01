package com.vivekbalachandra.android.ima.Data;

import android.content.Context;

/**
 * Created by vivek on 2/3/17.
 */

public class FieldsClass {
    public static int DB_VERSION=8;



    public static final String TABLE_USERS="app_users";
    public static final String TABLE_USERS_COLUMN1="_id";
    public static final String TABLE_USERS_COLUMN2="user_name";
    public static final String TABLE_USERS_COLUMN3="MAIL";

    public static final String TABLE_MESSAGES="app_messages";
    public static final String TABLE_MESSAGES_COLUMN1="_id";
    public static final String TABLE_MESSAGES_COLUMN2="direction";
    public static final String TABLE_MESSAGES_COLUMN3="type";
    public static final String TABLE_MESSAGES_COLUMN4="message";
    public static final String TABLE_MESSAGES_COLUMN5="time";


    public static final String CREATE_TABLE1="create table "+TABLE_USERS+"("+TABLE_USERS_COLUMN1+" text NOT NULL PRIMARY KEY,"+TABLE_USERS_COLUMN2+" text NOT NULL,"+TABLE_USERS_COLUMN3+" text)";
    public static final String CREATE_TABLE2="create table "+TABLE_MESSAGES+"("+TABLE_MESSAGES_COLUMN1+" text NOT NULL,"+TABLE_MESSAGES_COLUMN2+" INTEGER NOT NULL,"+TABLE_MESSAGES_COLUMN3+" text NOT NULL,"+TABLE_MESSAGES_COLUMN4+" text NOT NULL,"+TABLE_MESSAGES_COLUMN5+" text NOT NULL,"+"FOREIGN KEY("+TABLE_MESSAGES_COLUMN1+") REFERENCES "+TABLE_USERS+"("+TABLE_USERS_COLUMN1+"))";


}
