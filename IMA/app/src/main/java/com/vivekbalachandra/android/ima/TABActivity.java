package com.vivekbalachandra.android.ima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.vivekbalachandra.android.ima.Adapters.ViewPagerAdapter;
import com.vivekbalachandra.android.ima.Fragments.ChatFragment;
import com.vivekbalachandra.android.ima.Fragments.Reminder;

public class TABActivity extends AppCompatActivity {

    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mTableLayout= (TabLayout) findViewById(R.id.tab);
        mViewPager= (ViewPager) findViewById(R.id.swiper);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Reminder(),"Reminder");
        adapter.addFragment(new ChatFragment(),"Chat");
        adapter.addFragment(new Reminder(),"summary");
        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.setTabTextColors(getResources().getColorStateList(R.color.tab_text));
        mTableLayout.getTabAt(0).setText("Reminders");
        mTableLayout.getTabAt(1).setText("Chats");
        mTableLayout.getTabAt(2).setText("summary");

        
    }
}
