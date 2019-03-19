package com.herojeff.sulchedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.helper.ListViewResizeUtility;

public class MoreSulEditActivity extends AppCompatActivity {

    LinearLayout back_text_button;
    ImageButton add_button;

    ListView listview_more_sul_edit;
    MoreSulEditListViewAdapter adapter_more_sul_edit;
    ScrollView scrollview_sul_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_sul_edit);
        this.getWindow().setNavigationBarColor(CustomColor.color_primary_dark);

        scrollview_sul_edit = findViewById(R.id.scrollview_sul_edit);
        listview_more_sul_edit = findViewById(R.id.listview_more_sul_edit);
        adapter_more_sul_edit = new MoreSulEditListViewAdapter();
        adapter_more_sul_edit.passParent(listview_more_sul_edit, adapter_more_sul_edit);
        listview_more_sul_edit.setAdapter(adapter_more_sul_edit);
        listview_more_sul_edit.setDividerHeight(0);
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_sul_edit);
        listview_more_sul_edit.setFocusable(false);

        back_text_button = findViewById(R.id.back_text_button);
        add_button = findViewById(R.id.add_button);

        back_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSul();
            }
        });
    }

    void showAddSul() {
        final AddSulDialog addCateDialog = new AddSulDialog(this, false, "");
        addCateDialog.show();
        addCateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (addCateDialog.get_did_add()) {
                    adapter_more_sul_edit = new MoreSulEditListViewAdapter();
                    adapter_more_sul_edit.passParent(listview_more_sul_edit, adapter_more_sul_edit);
                    listview_more_sul_edit.setAdapter(adapter_more_sul_edit);
                    ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_sul_edit);
                    scrollview_sul_edit.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
    }
}
