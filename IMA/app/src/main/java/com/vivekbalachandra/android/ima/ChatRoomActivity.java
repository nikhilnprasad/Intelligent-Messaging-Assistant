package com.vivekbalachandra.android.ima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        int position=getIntent().getIntExtra("position",-1);
        TextView tv= (TextView) findViewById(R.id.ph);
        tv.setText(tv.getText().toString()+position);

    }
}
