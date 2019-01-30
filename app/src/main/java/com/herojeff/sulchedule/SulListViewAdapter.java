package com.herojeff.sulchedule;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;

import java.util.ArrayList;

public class SulListViewAdapter extends BaseAdapter {

    ArrayList<Sul> favourites;

    TextView textView;
    ImageView stepper_minus;
    ImageView stepper_plus;

    public SulListViewAdapter() {
    }

    public SulListViewAdapter(ArrayList<Sul> favourites) {
        this.favourites = favourites;
    }

    @Override
    public int getCount() {
        if(favourites.size() == 0) {
            return 1;
        }
        else{
            return favourites.size();
        }
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

        textView = convertView.findViewById(R.id.textview_sul);
        stepper_minus = convertView.findViewById(R.id.stepper_minus);
        stepper_plus = convertView.findViewById(R.id.stepper_plus);
        RelativeLayout stepper_container = convertView.findViewById(R.id.stepper_container);


        if(favourites.size() == 0){
            textView.setText("즐겨찾기를 추가하세요.");
            textView.setTextColor(Color.argb(150, 255,255,255));
            stepper_plus.setVisibility(View.GONE);
            stepper_minus.setVisibility(View.GONE);
            stepper_container.setVisibility(View.GONE);
            return convertView;
        }

        int count = SharedResources.getRecordDay(SharedResources.year, SharedResources.month, SharedResources.day).getCertain_sul_count(favourites.get(pos).getSul_name());
        textView.setText(String.valueOf(favourites.get(pos).getSul_name()) + " " + count + favourites.get(pos).getSul_unit());
        stepper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(SharedResources.year, SharedResources.month, SharedResources.day).getCertain_sul_count(favourites.get(pos).getSul_name());
                --count;
                if(count<0){
                    count = 0;
                }
                SharedResources.getRecordDay(SharedResources.year, SharedResources.month, SharedResources.day).setCertain_sul_count(favourites.get(pos).getSul_name(), count);
                TextView tv = v.findViewById(R.id.textview_sul);
                tv.setText(String.valueOf(favourites.get(pos).getSul_name()) + " " + count + favourites.get(pos).getSul_unit());
            }
        });
        stepper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(SharedResources.year, SharedResources.month, SharedResources.day).getCertain_sul_count(favourites.get(pos).getSul_name());
                SharedResources.getRecordDay(SharedResources.year, SharedResources.month, SharedResources.day).setCertain_sul_count(favourites.get(pos).getSul_name(), ++count);
                setTextView((TextView)v.findViewById(R.id.textview_sul), pos, count);
            }
        });


        return convertView;
    }


    public void setTextView(TextView tv, int pos, int count) {
        String k = String.valueOf(favourites.get(pos).getSul_name()) + " " + count + favourites.get(pos).getSul_unit();
        tv.setText(k);
    }
}