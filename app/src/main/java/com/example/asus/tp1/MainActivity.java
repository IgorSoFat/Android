package com.example.asus.tp1;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.viewPager = (ViewPager)findViewById(R.id.pager);

        this.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        this.viewPager.setCurrentItem(0); // Issou
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                this.viewPager.setCurrentItem(1);
                return true;
            case R.id.action_list:
                this.viewPager.setCurrentItem(2);
                return true;
            case R.id.action_position:
                this.viewPager.setCurrentItem(3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
