package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.herojeff.sulchedule.data.SharedResources;

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

        final FragmentManager transaction = getSupportFragmentManager();
        transaction.beginTransaction().add(R.id.fragment_container, todayFragment);
        transaction.beginTransaction().commit();

        if (SharedResources.first_launch_ever) {
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

            SharedResources.first_launch_ever = false;
        }

        this.getWindow().setNavigationBarColor(SharedResources.color_primary);
        this.getWindow().setStatusBarColor(SharedResources.color_primary_dark);

        transaction.beginTransaction().add(R.id.fragment_container, todayFragment).commit();
        transaction.beginTransaction().add(R.id.fragment_container, pastFragment).commit();
        transaction.beginTransaction().add(R.id.fragment_container, trafficFragment).commit();
        transaction.beginTransaction().show(todayFragment).commit();
        transaction.beginTransaction().hide(pastFragment).commit();
        transaction.beginTransaction().hide(trafficFragment).commit();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                SharedResources.getRecordMonth(SharedResources.getYear(), SharedResources.getMonth()).cleanup();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_today:
                        transaction.beginTransaction().show(todayFragment).commit();
                        transaction.beginTransaction().hide(pastFragment).commit();
                        transaction.beginTransaction().hide(trafficFragment).commit();
                        break;
                    case R.id.navigation_past:
                        transaction.beginTransaction().hide(todayFragment).commit();
                        transaction.beginTransaction().show(pastFragment).commit();
                        transaction.beginTransaction().hide(trafficFragment).commit();
                        break;
                    case R.id.navigation_traffic:
                        transaction.beginTransaction().hide(todayFragment).commit();
                        transaction.beginTransaction().hide(pastFragment).commit();
                        transaction.beginTransaction().show(trafficFragment).commit();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }


}
