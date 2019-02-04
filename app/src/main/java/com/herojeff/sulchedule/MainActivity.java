package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.herojeff.sulchedule.data.SharedResources;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Fragment todayFragment;
    Fragment pastFragment;
    Fragment trafficFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.fragment_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        todayFragment = new TodayFragment();
        pastFragment = new PastFragment();
        ((PastFragment) pastFragment).setPastFragment((PastFragment) pastFragment);
        trafficFragment = new TrafficFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, todayFragment);
        transaction.commit();

        SharedResources.addSul("소주", 300, 4000, "병");
        SharedResources.addSul("소주", 300, 4000, "병");
        SharedResources.addSul("소주 잔", 50, 650, "잔");
        SharedResources.addSul("병맥주 330ml", 122, 2000, "병");
        SharedResources.addSul("병맥주 500ml", 185, 3000, "병");
        SharedResources.addSul("생맥주 500cc", 185, 4000, "잔");
        SharedResources.addSul("캔맥주 355ml", 152, 2000, "캔");
        SharedResources.addSul("레드와인", 84, 12000, "잔");
        SharedResources.addSul("화이트와인", 74, 12000, "잔");
        SharedResources.addSul("막걸리", 345, 2000, "병");
        SharedResources.setFavouriteSul("소주", true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                SharedResources.getRecordMonth(SharedResources.getYear(), SharedResources.getMonth()).cleanup();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_today:
                        fragment = todayFragment;
                        break;
                    case R.id.navigation_past:
                        fragment = pastFragment;
                        break;
                    case R.id.navigation_traffic:
                        fragment = trafficFragment;
                        break;
                    default:
                        fragment = todayFragment;
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
