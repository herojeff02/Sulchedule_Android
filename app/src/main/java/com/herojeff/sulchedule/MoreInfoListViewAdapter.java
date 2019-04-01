package com.herojeff.sulchedule;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

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
            convertView = new PillSelector(parent.getContext(), pos);
        }

        EditText editText = convertView.findViewById(R.id.edittext_more_info);

        PillSelector pill = new PillSelector(parent.getContext(), pos);

        return convertView;
    }
}
