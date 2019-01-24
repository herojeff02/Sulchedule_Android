package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreSulListViewAdapter extends BaseAdapter {

    //    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    String[] arr = {"뭔 술 몇 병", "ㅁㄴㅇㄹㅁㄴㅇ", "ㅁㅇㄹ이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이"};

    TextView textView;
    ImageView stepper_plus;
    ImageView stepper_minus;

    public MoreSulListViewAdapter() {

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
            convertView = inflater.inflate(R.layout.listview_more_sul_item, parent, false);
        }

        textView = convertView.findViewById(R.id.string_sul);

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