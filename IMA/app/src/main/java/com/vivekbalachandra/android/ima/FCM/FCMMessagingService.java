package com.vivekbalachandra.android.ima.FCM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.vivekbalachandra.android.ima.Data.DATABASE;
import com.vivekbalachandra.android.ima.Data.FieldsClass;
import com.vivekbalachandra.android.ima.Data.Models.DataMessage;
import com.vivekbalachandra.android.ima.Data.Provider;

import java.util.Map;

/**
 * Created by vivek on 2/3/17.
 */

public class FCMMessagingService extends FirebaseMessagingService {

    private static final String TAG=FCMMessagingService.class.getSimpleName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG+"TEST","CALLED onMessage recive");
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message has data payload: " + remoteMessage.getData());
            parseData(remoteMessage.getData());


        }


        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }


    }

    private void parseData(Map<String, String> data) {
        Gson gson=new Gson();
        String json=gson.toJson(data);
        DataMessage message=gson.fromJson(json,DataMessage.class);
        storeToDatabase(getApplicationContext(),1,message);

    }
    public int storeToDatabase(Context context, int direction,DataMessage data) {

        Uri rowid1=null;
        DATABASE dbHelper=new DATABASE(context);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        if(db!=null) {
            String[] projection={FieldsClass.TABLE_USERS_COLUMN1};
            String[] selectArgd={data.getUserid()};
            Cursor cursor=db.query(FieldsClass.TABLE_USERS,projection,FieldsClass.TABLE_USERS_COLUMN1+" LIKE ?",selectArgd,null,null,null);
            if(cursor.getCount()>0) {
                Log.d(TAG,"not a new user");

            }
                else{
                db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(FieldsClass.TABLE_USERS_COLUMN1,data.getUserid());
                values.put(FieldsClass.TABLE_USERS_COLUMN2,data.getUsername());
                values.put(FieldsClass.TABLE_USERS_COLUMN3,"h@g.com");
                rowid1=getContentResolver().insert(Uri.parse(Provider.uri+"/"+FieldsClass.TABLE_USERS),values);
                if(Long.valueOf(rowid1.getLastPathSegment())<0)
                    Log.d(TAG,"new user insert failure");
                else
                    Log.d(TAG,"new user insrt success");

                values.clear();
            }
            if (cursor!=null)
            cursor.close();
            db.close();
        }


        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values=new ContentValues();
        values.put(FieldsClass.TABLE_MESSAGES_COLUMN1,data.getUserid());
        values.put(FieldsClass.TABLE_MESSAGES_COLUMN2,direction);db.query(FieldsClass.TABLE_MESSAGES,null,null,null,null,null,null);
        values.put(FieldsClass.TABLE_MESSAGES_COLUMN3,data.getType());
        values.put(FieldsClass.TABLE_MESSAGES_COLUMN4,data.getMessage());
        values.put(FieldsClass.TABLE_MESSAGES_COLUMN5,data.getTime());
        Uri rowid2=getContentResolver().insert(Uri.parse(Provider.uri+"/"+FieldsClass.TABLE_MESSAGES),values);
        values.clear();
        db.close();
        if(Long.valueOf(rowid2.getLastPathSegment())<0)
        {
            Log.d(TAG,"insert failure");
            return -1;
        }

        Log.d(TAG,"new message insert success");
        return 1;
    }
}
