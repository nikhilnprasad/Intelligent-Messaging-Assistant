package com.vivekbalachandra.android.ima.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by vivek on 4/3/17.
 */

public class Provider extends ContentProvider {

    private static final String TAG=Provider.class.getSimpleName();
    public static final String data_provider_authority ="com.vivekbalachandra.android.ima.Data";
    public static final String uri="content://"+ data_provider_authority;
    //public static final Uri CONTENT_URI=Uri.parse(uri);
    public static final int TABLE_USER=1;
    public static final int TABLE_MESSAGES=2;
    private SQLiteDatabase db=null;
    public static final UriMatcher URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        URI_MATCHER.addURI(data_provider_authority,FieldsClass.TABLE_USERS,TABLE_USER);
        URI_MATCHER.addURI(data_provider_authority,FieldsClass.TABLE_MESSAGES,TABLE_MESSAGES);
    }

    @Override
    public boolean onCreate() {
        DATABASE dbHelper = new DATABASE(getContext());
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] strings1, String s1) {
        DATABASE dbHelper = new DATABASE(getContext());
        Log.d(TAG,"called content provider");
        if(db==null)
        db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        int match=URI_MATCHER.match(uri);
        Log.d(TAG,"matched url code :"+match);
        switch (match) {
            case TABLE_USER:
                cursor= db.query(FieldsClass.TABLE_USERS,columns,selection,strings1,null,null,s1);
                break;
            case TABLE_MESSAGES:

                cursor= db.query(FieldsClass.TABLE_MESSAGES,columns,selection,strings1,null,null,s1);
                Log.d(TAG,"querying app_messages database");
                break;

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        DATABASE dbHelper = new DATABASE(getContext());
        long rowid=-1;
        if(db==null)
            db=dbHelper.getReadableDatabase();
        switch (URI_MATCHER.match(uri)) {
            case TABLE_USER:
                rowid=db.insert(FieldsClass.TABLE_USERS,null,contentValues);
                break;
            case TABLE_MESSAGES:
                rowid=db.insert(FieldsClass.TABLE_MESSAGES,null,contentValues);
                break;
        }
        Uri.Builder builder=uri.buildUpon();
        builder.appendPath(String.valueOf(rowid));
        getContext().getContentResolver().notifyChange(uri,null);
        return builder.build();
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        return 0;
    }
}
