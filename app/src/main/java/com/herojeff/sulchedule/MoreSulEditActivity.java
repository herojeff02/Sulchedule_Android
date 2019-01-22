package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MoreSulEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_sul_edit);

        ListView listview_more_sul_edit = findViewById(R.id.listview_more_sul_edit);
        MoreSulEditListViewAdapter adapter_more_sul_edit = new MoreSulEditListViewAdapter();
        listview_more_sul_edit.setAdapter(adapter_more_sul_edit);
        listview_more_sul_edit.setDividerHeight(0);


    }
}
