package com.vivekbalachandra.android.ima.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vivek on 2/3/17.
 */

public class DATABASE extends SQLiteOpenHelper {

    String TAG=DATABASE.class.getName();
    static final String DataBaseName="AppRDB";
    public DATABASE(Context context) {
        super(context, DataBaseName, null, FieldsClass.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG+DataBaseName,FieldsClass.CREATE_TABLE1+"table2 "+FieldsClass.CREATE_TABLE2);
        sqLiteDatabase.execSQL(FieldsClass.CREATE_TABLE1);
     sqLiteDatabase.execSQL(FieldsClass.CREATE_TABLE2);
        Log.d(TAG+DataBaseName,"table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+FieldsClass.TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+FieldsClass.TABLE_MESSAGES);
        onCreate(sqLiteDatabase);
    }
}
