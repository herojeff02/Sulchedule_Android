package com.herojeff.sulchedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MoreSulActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back_text_button;
    ImageButton edit_button;
    ImageButton add_button;
    LinearLayout pill_more_sul_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_sul);

        ListView listview_more_sul = findViewById(R.id.listview_more_sul);
        MoreSulListViewAdapter adapter_more_sul = new MoreSulListViewAdapter();
        listview_more_sul.setAdapter(adapter_more_sul);
        listview_more_sul.setDividerHeight(0);
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_sul);
        listview_more_sul.setFocusable(false);

        back_text_button = findViewById(R.id.back_text_button);
        back_text_button.setOnClickListener(this);
        edit_button = findViewById(R.id.setting_button);
        edit_button.setOnClickListener(this);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(this);
        pill_more_sul_add = findViewById(R.id.pill_more_sul_add);
        pill_more_sul_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back_text_button:
                finish();
                break;
            case R.id.setting_button:
                intent = new Intent(getApplicationContext(), MoreSulEditActivity.class);
                startActivity(intent);
                break;
            case R.id.add_button:
                System.out.println("add sul");
                break;
            case R.id.pill_more_sul_add:
                System.out.println("add sul");
                break;
        }
    }
}
