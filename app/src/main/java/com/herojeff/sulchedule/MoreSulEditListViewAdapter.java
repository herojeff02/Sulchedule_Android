package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MoreSulEditListViewAdapter extends BaseAdapter {

    //    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    String[] arr = {"뭔 술 몇 병", "ㅁㄴㅇㄹㅁㄴㅇ", "ㅁㅇㄹ이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이", "ㅁㅇㄹ42", "이이"};

    TextView edit_button;
    TextView remove_button;

    public MoreSulEditListViewAdapter() {

    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = inflater.inflate(R.layout.listview_more_sul_edit, parent, false);
        }

        edit_button = convertView.findViewById(R.id.edit_button);
        remove_button = convertView.findViewById(R.id.remove_button);

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
                System.out.println("remove!");
                System.out.println(pos);
            }
        });

        return convertView;
    }

}