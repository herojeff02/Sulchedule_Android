package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;
import com.herojeff.sulchedule.helper.ListViewResizeUtility;

import java.util.ArrayList;

public class MoreSulEditListViewAdapter extends BaseAdapter {

    TextView edit_button;
    TextView remove_button;
    TextView text_title;
    TextView text_left_top;
    TextView text_left_bottom;
    TextView text_right_top;
    MoreSulEditListViewAdapter adapter;
    ListView listview;
    private ArrayList<Sul> suls = SharedResources.getSuls();

    public MoreSulEditListViewAdapter() {

    }

    public void passParent(ListView listview_more_sul_edit, MoreSulEditListViewAdapter adapter) {
        this.adapter = adapter;
        this.listview = listview_more_sul_edit;
    }

    @Override
    public int getCount() {
        return suls.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_more_sul_edit_item, parent, false);
        }

        edit_button = convertView.findViewById(R.id.setting_button);
        remove_button = convertView.findViewById(R.id.remove_button);
        text_title = convertView.findViewById(R.id.text_title);
        text_left_top = convertView.findViewById(R.id.text_left_top);
        text_right_top = convertView.findViewById(R.id.text_right_top);
        text_left_bottom = convertView.findViewById(R.id.text_left_bottom);

        text_title.setText(suls.get(pos).sul_name);
        text_left_top.setText(suls.get(pos).getSul_unit());
        text_right_top.setText(String.valueOf(suls.get(pos).getSul_calorie()) + "kcal");
        text_left_bottom.setText(String.valueOf(suls.get(pos).getSul_price()) + "원");

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("edit!");
                System.out.println(pos);
            }
        });
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k = suls.get(pos).sul_name;
                SharedResources.removeSul(k);
                suls = SharedResources.getSuls();
                adapter.notifyDataSetChanged();
                ListViewResizeUtility.setListViewHeightBasedOnItems(listview);
            }
        });

        return convertView;
    }

}