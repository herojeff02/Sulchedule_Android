package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;

public class MoreInfoListViewAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 3;
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
            convertView = inflater.inflate(R.layout.listview_more_info_item, parent, false);
        }

        EditText editText = convertView.findViewById(R.id.edittext_more_info);
        editText.setText("");

        TextView pill_selector_string = convertView.findViewById(R.id.pill_selector_string);
        PillSelector pill = new PillSelector(pos + 1);
        pill_selector_string.setText(pill.getModeString());

        return convertView;
    }
}
