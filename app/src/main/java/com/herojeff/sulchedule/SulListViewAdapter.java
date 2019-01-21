package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SulListViewAdapter extends BaseAdapter {

    //    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    String[] arr = {"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17", "ㅁㄴㅇㄹㅁㄴㅇ", "ㅁㅇㄹ42", "이이"};

    public SulListViewAdapter() {

    }

    @Override
    public int getCount() {
        return arr.length;
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
            convertView = inflater.inflate(R.layout.listview_sul, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.string_sul);

        textView.setText(String.valueOf(arr[pos]));

        return convertView;
    }

}