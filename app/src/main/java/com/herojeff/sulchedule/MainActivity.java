package com.herojeff.sulchedule;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SaveManager;
import com.herojeff.sulchedule.data.SharedResources;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        } else {
            SharedResources.addSul("소주", 300, 4000, "병");
            SharedResources.addSul("소주", 300, 4000, "병");
            SharedResources.addSul("소주 잔", 50, 650, "잔");
            SharedResources.addSul("병맥주 330ml", 122, 2000, "병");
            SharedResources.addSul("병맥주 500ml", 185, 3000, "병");
            SharedResources.addSul("생맥주 500cc", 185, 4000, "병");
            SharedResources.addSul("캔맥주 355ml", 152, 2000, "캔");
            SharedResources.addSul("레드와인", 84, 12000, "잔");
            SharedResources.addSul("화이트와인", 74, 12000, "잔");
            SharedResources.addSul("막걸리", 345, 2000, "병");
            SharedResources.setFavouriteSul("소주", true);
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

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                if (SaveManager.getFirstLaunch()) {
                    welcomeDialog();
                }
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                if (SaveManager.getFirstLaunch()) {
                    welcomeDialog();
                }
            }
        };
        TedPermission.with(this).setPermissionListener(permissionlistener).setPermissions(Manifest.permission.READ_CONTACTS).check();

        testField();
    }

    void welcomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TodaySettingDialog));
        String cheese = getUsername();
        if (cheese != null) {
            builder.setTitle("환영합니다, " + cheese + " 님!");
        } else {
            builder.setTitle("환영합니다!");
        }
        builder.setMessage("안드로이드에서 뵙게 되니 기분이 새롭네요!\n그동안 간을 잘 지켜 주셨나요?\n술케줄과 함께 건강한 음주 생활을 시작해 봐요.");

        AlertDialog dialog;

        dialog = builder.setPositiveButton("시작해요!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.first_launch_ever = false;
                        dialog.dismiss();
                    }
                }).create();

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
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

    public String getUsername() {
        try {
            Cursor c = getApplication().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
                return c.getString(c.getColumnIndex("display_name"));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
