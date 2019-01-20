package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.fragment_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new TodayFragment());
        transaction.commit();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_today:
                        fragment = TodayFragment.newInstance("test1", "test2");
                        fragment = new TodayFragment();
                        break;
                    case R.id.navigation_past:
//                        fragment = PastFragment.newInstance("test1", "test2");
                        fragment = new PastFragment();
//                        difference?
                        break;
                    case R.id.navigation_traffic:
                        fragment = TrafficFragment.newInstance("test1", "test2");
//                        fragment = new TrafficFragment();
//                        difference?
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                return true;
            }
        });
    }

}
