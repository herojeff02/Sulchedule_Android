package com.herojeff.sulchedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SaveManager;
import com.herojeff.sulchedule.data.SharedResources;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back_text_button;

    TextView item1_text, item2_text, item3_text, item4, item5, item6, item7, item8;
    RelativeLayout item1, item2, item3;
    Switch item1_switch, item2_switch, item3_switch;
    ImageView item1_help, item2_help, item3_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.getWindow().setNavigationBarColor(CustomColor.color_primary_dark);
        initButtons();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String url;
        switch (v.getId()) {
            case R.id.back_text_button:
                finish();
                break;

            case R.id.setting_item1:
                SharedResources.enable_smart_tip_string = !SharedResources.enable_smart_tip_string;
                SaveManager.saveUserSettings();
                item1_switch.setChecked(SharedResources.enable_smart_tip_string);
                break;
            case R.id.setting_item2:
                SharedResources.notification_enabled = !SharedResources.notification_enabled;
                SaveManager.saveUserSettings();
                item2_switch.setChecked(SharedResources.notification_enabled);
                break;
            case R.id.setting_item3:
                if(SharedResources.checkEligibleRemoveAdEligible()) {
                    if(SharedResources.isEnable_ad()) {
                        adConfigDialog();
                    }
                    else {
                        SharedResources.setEnable_ad(!SharedResources.isEnable_ad());
                        SaveManager.saveUserSettings();
                        item3_switch.setChecked(SharedResources.isEnable_ad());
                    }
                }
                else{
                    adConfigDialog();
                }
                break;
            case R.id.setting_item4:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"herojeff02@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sulchedule Android Bug Report " + (CustomDayManager.getTodayYear()+1900)+"/" + CustomDayManager.getTodayMonth()+"/" + CustomDayManager.getTodayDay());
                intent.putExtra(Intent.EXTRA_TEXT   , "[버그가 발생한 상황과, 버그의 현상을 자세하게 설명해주세요.]");
                try {
                    startActivity(Intent.createChooser(intent, "메일 전송"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "휴대폰 설정에서 이메일 계정을 등록한 후 사용해 주세요.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.setting_item5:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"herojeff02@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sulchedule Android Feature Request " + (CustomDayManager.getTodayYear()+1900)+"/" + CustomDayManager.getTodayMonth()+"/" + CustomDayManager.getTodayDay());
                intent.putExtra(Intent.EXTRA_TEXT   , "[원하는 기능을 자세하게 설명해 주세요.]");
                try {
                    startActivity(Intent.createChooser(intent, "메일 전송"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "휴대폰 설정에서 이메일 계정을 등록한 후 사용해 주세요.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.setting_item6:
                url ="https://github.com/CLUG-kr/Sulchedule_Android/blob/master/OpenSourceLicense";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.setting_item7:
                url ="https://github.com/CLUG-kr/Sulchedule_Android";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.setting_item8:
                url ="https://github.com/CLUG-kr/Sulchedule_Android/blob/master/%EA%B0%9C%EC%9D%B8%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EB%B0%A9%EC%B9%A8.md";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.setting_item1_help:
                smartTipConfigDialog();
                break;
            case R.id.setting_item2_help:
                notificationConfigDialog();
                break;
            case R.id.setting_item3_help:
                adConfigDialog();
                break;

            case R.id.setting_item1_text:
                smartTipConfigDialog();
                break;
            case R.id.setting_item2_text:
                notificationConfigDialog();
                break;
            case R.id.setting_item3_text:
                adConfigDialog();
                break;

            case R.id.setting_item1_switch:
                SharedResources.enable_smart_tip_string = !SharedResources.enable_smart_tip_string;
                SaveManager.saveUserSettings();
                item1_switch.setChecked(SharedResources.enable_smart_tip_string);
                break;
            case R.id.setting_item2_switch:
                SharedResources.notification_enabled = !SharedResources.notification_enabled;
                SaveManager.saveUserSettings();
                item2_switch.setChecked(SharedResources.notification_enabled);
                break;
            case R.id.setting_item3_switch:
                if(SharedResources.checkEligibleRemoveAdEligible()) {
                    if(SharedResources.isEnable_ad()) {
                        item3_switch.setChecked(true);
                        adConfigDialog();
                    }
                    else {
                        SharedResources.setEnable_ad(!SharedResources.isEnable_ad());
                        SaveManager.saveUserSettings();
                        item3_switch.setChecked(SharedResources.isEnable_ad());
                    }
                }
                else{
                    item3_switch.setChecked(true);
                    adConfigDialog();
                }
                break;
        }
    }

    void smartTipConfigDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TodaySettingDialog));
        builder.setTitle("스마트 팁");
        builder.setMessage("스마트 팁은 열량, 지출액에 따라 유동적으로 메시지를 화면 상단에 표시합니다.\n끄면 지출액과 열량만이 표시됩니다.");

        AlertDialog dialog;

        builder.setPositiveButton("켜기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.enable_smart_tip_string = true;
                        item1_switch.setChecked(true);
                        SaveManager.saveUserSettings();
                    }
                }).create();
        dialog = builder.setNegativeButton("끄기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.enable_smart_tip_string = false;
                        item1_switch.setChecked(false);
                        SaveManager.saveUserSettings();
                    }
                }).create();

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(CustomColor.color_accent);
    }

    void adConfigDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TodaySettingDialog));
        builder.setTitle("광고 설정");
        AlertDialog dialog;

        if(SharedResources.checkEligibleRemoveAdEligible()) {
            builder.setMessage("축하합니다! 이제 광고를 보지 않으셔도 됩니다.\n하지만 광고를 켜 두시면 통학러 개발자의 차비를 지원하실 수 있습니다.");

            builder.setPositiveButton("켜기",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedResources.setEnable_ad(true);
                            item3_switch.setChecked(true);
                            SaveManager.saveUserSettings();
                        }
                    }).create();
            dialog = builder.setNegativeButton("끄기",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedResources.setEnable_ad(false);
                            item3_switch.setChecked(false);
                            SaveManager.saveUserSettings();
                        }
                    }).create();
        }
        else {
            builder.setMessage("화면 하단에 광고가 표시됩니다.\n건강 신호등 탭의 네 목표를 한번에 달성하셔야 광고를 끌 수 있습니다.");

            dialog = builder.setPositiveButton("닫기",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
        }

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(CustomColor.color_accent);
    }

    void notificationConfigDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TodaySettingDialog));
        builder.setTitle("알림 설정");
        builder.setMessage("주기적으로 음주 기록을 상기하는 알림을 표시합니다.");

        AlertDialog dialog;

        builder.setPositiveButton("켜기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.notification_enabled = true;
                        item2_switch.setChecked(true);
                        SaveManager.saveUserSettings();
                    }
                }).create();
        dialog = builder.setNegativeButton("끄기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.notification_enabled = false;
                        item2_switch.setChecked(false);
                        SaveManager.saveUserSettings();
                    }
                }).create();

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(CustomColor.color_accent);
    }

    void adForceOffDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.TodaySettingDialog));
        builder.setTitle("광고 강제 제거");
        builder.setMessage("광고를 영구적으로 숨기시겠습니까?");

        AlertDialog dialog;

        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.ad_force_off = true;
                        SaveManager.saveUserSettings();
                    }
                }).create();
        dialog = builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.ad_force_off = false;
                        SaveManager.saveUserSettings();
                    }
                }).create();

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(CustomColor.color_accent);
    }

    void initButtons(){
        back_text_button = findViewById(R.id.back_text_button);
        item1 = findViewById(R.id.setting_item1);
        item2 = findViewById(R.id.setting_item2);
        item3 = findViewById(R.id.setting_item3);
        item4 = findViewById(R.id.setting_item4);
        item5 = findViewById(R.id.setting_item5);
        item6 = findViewById(R.id.setting_item6);
        item7 = findViewById(R.id.setting_item7);
        item8 = findViewById(R.id.setting_item8);
        item1_text = findViewById(R.id.setting_item1_text);
        item2_text = findViewById(R.id.setting_item2_text);
        item3_text = findViewById(R.id.setting_item3_text);
        item1_switch = findViewById(R.id.setting_item1_switch);
        item2_switch = findViewById(R.id.setting_item2_switch);
        item3_switch = findViewById(R.id.setting_item3_switch);
        item1_help = findViewById(R.id.setting_item1_help);
        item2_help = findViewById(R.id.setting_item2_help);
        item3_help = findViewById(R.id.setting_item3_help);

        back_text_button.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        item7.setOnClickListener(this);
        item8.setOnClickListener(this);
        item1_help.setOnClickListener(this);
        item2_help.setOnClickListener(this);
        item3_help.setOnClickListener(this);
        item1_text.setOnClickListener(this);
        item2_text.setOnClickListener(this);
        item3_text.setOnClickListener(this);

        item1_switch.setChecked(SharedResources.enable_smart_tip_string);
        item2_switch.setChecked(SharedResources.notification_enabled);
        item3_switch.setChecked(SharedResources.isEnable_ad());

        item1_switch.setOnClickListener(this);
        item2_switch.setOnClickListener(this);
        item3_switch.setOnClickListener(this);

        item7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                adForceOffDialog();
                return true;
            }
        });
    }
}
