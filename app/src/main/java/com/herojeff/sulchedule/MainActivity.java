package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SaveManager;
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

        SaveManager.setPrefs(getPreferences(MODE_PRIVATE));
        if (!SaveManager.getFirstLaunch()) {
            SaveManager.load();
        }
        else{
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

            tutorial();
        }

        CustomDayManager.initCustomDay();

        frameLayout = findViewById(R.id.fragment_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        todayFragment = new TodayFragment();
        pastFragment = new PastFragment();
        ((PastFragment) pastFragment).setPastFragment((PastFragment) pastFragment);
        trafficFragment = new TrafficFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, todayFragment).commit();

        this.getWindow().setNavigationBarColor(CustomColor.color_primary);
        this.getWindow().setStatusBarColor(CustomColor.color_primary_dark_dark);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                CustomDayManager.initCustomDay();
                SharedResources.getRecordMonth(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth()).cleanup();


                switch (item.getItemId()) {
                    case R.id.navigation_today:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, todayFragment).commit();
                        break;
                    case R.id.navigation_past:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pastFragment).commit();
                        break;
                    case R.id.navigation_traffic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, trafficFragment).commit();
                        break;
                }
                return true;
            }
        });

        testField();
    }

    private void tutorial() {

    }

    private void testField() {
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedResources.cleanup();
        SaveManager.save();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedResources.cleanup();
        SaveManager.save();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        SharedResources.cleanup();
        SaveManager.save();
    }
}
