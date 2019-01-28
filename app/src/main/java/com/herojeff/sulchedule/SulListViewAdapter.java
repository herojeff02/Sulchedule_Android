package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SulListViewAdapter extends BaseAdapter {

    String[] arr = {"1 2 3 4", "ㅁㄴㅇㄹㅁㄴㅇ", "ㅁㅇㄹ42", "이이"};

    TextView textView;
    ImageView stepper_minus;
    ImageView stepper_plus;

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
            convertView = inflater.inflate(R.layout.listview_sul_item, parent, false);
        }

        textView = convertView.findViewById(R.id.string_sul);
        stepper_minus = convertView.findViewById(R.id.stepper_minus);
        stepper_plus = convertView.findViewById(R.id.stepper_plus);

        textView.setText(String.valueOf(arr[pos]));
        stepper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("minus at " + pos);
            }
        });
        stepper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("plus at " + pos);
            }
        });


        return convertView;
    }

}