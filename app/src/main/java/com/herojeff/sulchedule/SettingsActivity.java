package com.herojeff.sulchedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.herojeff.sulchedule.data.CustomColor;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back_text_button;

    TextView item4, item5, item6, item7, item8;
    RelativeLayout item1, item2, item3;
    Switch item1_switch, item2_switch, item3_switch;
    ImageView item1_help, item2_help, item3_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.getWindow().setNavigationBarColor(CustomColor.color_primary_dark);

        back_text_button = findViewById(R.id.back_text_button);
        item1 = findViewById(R.id.setting_item1);
        item2 = findViewById(R.id.setting_item2);
        item3 = findViewById(R.id.setting_item3);
        item4 = findViewById(R.id.setting_item4);
        item5 = findViewById(R.id.setting_item5);
        item6 = findViewById(R.id.setting_item6);
        item7 = findViewById(R.id.setting_item7);
        item8 = findViewById(R.id.setting_item8);
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
        item1_switch.setOnClickListener(this);
        item2_switch.setOnClickListener(this);
        item3_switch.setOnClickListener(this);
        item1_help.setOnClickListener(this);
        item2_help.setOnClickListener(this);
        item3_help.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back_text_button:
                finish();
                break;
            case R.id.setting_item1:

                break;
            case R.id.setting_item2:
                break;
            case R.id.setting_item3:
                break;
            case R.id.setting_item4:
                break;
            case R.id.setting_item5:
                break;
            case R.id.setting_item6:
                break;
            case R.id.setting_item7:
                break;
            case R.id.setting_item8:
                break;
        }
    }
}
