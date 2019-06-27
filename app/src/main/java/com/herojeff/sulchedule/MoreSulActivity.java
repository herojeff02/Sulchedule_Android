package com.herojeff.sulchedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.helper.ListViewResizeUtility;

public class MoreSulActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back_text_button;
    ImageButton edit_button;
    ImageButton add_button;
    LinearLayout pill_more_sul_add;
    ListView listview_more_sul;
    MoreSulListViewAdapter adapter_more_sul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_sul);
        this.getWindow().setNavigationBarColor(CustomColor.color_primary_dark);

        listview_more_sul = findViewById(R.id.listview_more_sul);
        adapter_more_sul = new MoreSulListViewAdapter(SharedResources.getSuls());
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
    protected void onRestart() {
        super.onRestart();
        adapter_more_sul = new MoreSulListViewAdapter(SharedResources.getSuls());
        listview_more_sul.setAdapter(adapter_more_sul);
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_sul);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.add_button:
                showAddSul();
                break;
            case R.id.pill_more_sul_add:
                showAddSul();
                break;
        }
    }

    void showAddSul() {
        final AddSulDialog addCateDialog = new AddSulDialog(this, false, "");
        addCateDialog.show();
        addCateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adapter_more_sul = new MoreSulListViewAdapter(SharedResources.getSuls());
                listview_more_sul.setAdapter(adapter_more_sul);
                ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_sul);
            }
        });
    }
}
