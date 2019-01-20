package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SulListViewAdapter extends BaseAdapter {

//    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    String[] arr = {"뭔 술 몇 병","ㅁㄴㅇㄹㅁㄴㅇ","ㅁㅇㄹ42", "이이"};

    public SulListViewAdapter(){

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