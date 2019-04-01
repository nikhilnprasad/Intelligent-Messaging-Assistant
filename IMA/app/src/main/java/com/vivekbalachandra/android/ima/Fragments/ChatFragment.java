package com.vivekbalachandra.android.ima.Fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vivekbalachandra.android.ima.ChatRoomActivity;
import com.vivekbalachandra.android.ima.Data.DATABASE;
import com.vivekbalachandra.android.ima.Data.FieldsClass;
import com.vivekbalachandra.android.ima.Data.Provider;
import com.vivekbalachandra.android.ima.R;


public class ChatFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private final static String TAG=ChatFragment.class.getSimpleName();
    private View rootView;
    TextView tv;
    private Button btn;
    private static final int LOADER_ID=111;
    SimpleCursorAdapter cursorAdapter;
    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_chat, container, false);

        return  rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            mListener=null;
        }*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Cursor cursor= getActivity().getContentResolver().query(Uri.parse(Provider.uri+"/"+FieldsClass.TABLE_USERS),null,null,null,null,null);

        String[] from={FieldsClass.TABLE_USERS_COLUMN2};
        int[] to={R.id.user};
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        ListView listView= (ListView) rootView.findViewById(R.id.userlist);
        cursorAdapter=new SimpleCursorAdapter(getContext(),R.layout.list_item_view,cursor,from,to,0);

        listView.setAdapter(cursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), ChatRoomActivity.class);
                       intent.putExtra("position",i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //    mListener = null;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Log.d(TAG,"content is change");
        CursorLoader cursorLoader=new CursorLoader(getContext(),Uri.parse(Provider.uri+"/"+FieldsClass.TABLE_USERS),null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG,"load finished updating ui");
        cursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
     cursorAdapter.swapCursor(null);
    }
}
