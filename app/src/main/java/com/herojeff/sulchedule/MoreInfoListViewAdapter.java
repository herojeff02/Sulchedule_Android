package com.herojeff.sulchedule;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.herojeff.sulchedule.data.ModeValuePair;

import java.util.ArrayList;

public class MoreInfoListViewAdapter extends BaseAdapter {
    ArrayList<ModeValuePair> array;
    public MoreInfoListViewAdapter(ArrayList<ModeValuePair> initMoreData) {
        array = initMoreData;
    }

    @Override
    public int getCount() {
        return array.size();
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
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (pos <= 2) {
                convertView = new PillSelector(parent.getContext(), pos, true);
            } else {
                convertView = new PillSelector(parent.getContext(), 0, false);
            }
        }

        return convertView;
    }
}
