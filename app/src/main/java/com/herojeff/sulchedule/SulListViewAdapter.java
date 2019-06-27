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

import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SaveManager;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;

import java.util.ArrayList;

public class SulListViewAdapter extends BaseAdapter {

    EventListener listener;

    ArrayList<Sul> favourites;

    TextView textView;
    ImageView stepper_minus;
    ImageView stepper_plus;
    View favourite_bar;

    public SulListViewAdapter() {
    }

    public SulListViewAdapter(ArrayList<Sul> favourites, EventListener listener) {
        this.favourites = favourites;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if (favourites.size() == 0) {
            return 1;
        } else {
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
        favourite_bar = convertView.findViewById(R.id.favourite_bar);
        RelativeLayout stepper_container = convertView.findViewById(R.id.stepper_container);

        if (favourites.size() == 0) {
            textView.setText("즐겨찾기를 추가하세요.");
            textView.setTextColor(Color.argb(150, 255, 255, 255));
            stepper_plus.setVisibility(View.GONE);
            stepper_minus.setVisibility(View.GONE);
            stepper_container.setVisibility(View.GONE);
            favourite_bar.setVisibility(View.INVISIBLE);
            return convertView;
        }

        if (pos >= SharedResources.getFavouriteSuls().size()) {
            favourite_bar.setVisibility(View.INVISIBLE);
        }


        final int year = CustomDayManager.getYear();
        final int month = CustomDayManager.getMonth();
        final int day = CustomDayManager.getDay();
        int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(favourites.get(pos).getSul_name());
        textView.setText(favourites.get(pos).getSul_name() + " " + count + favourites.get(pos).getSul_unit());
        stepper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(favourites.get(pos).getSul_name());
                --count;
                if (count < 0) {
                    count = 0;
                }
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(favourites.get(pos).getSul_name(), count);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, count);
                listener.callUpdateTipString(false);
                SaveManager.saveRecordArrayList();
            }
        });
        stepper_minus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(favourites.get(pos).getSul_name(), 0);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, 0);
                listener.callUpdateTipString(false);
                SaveManager.saveRecordArrayList();
                return true;
            }
        });
        stepper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(favourites.get(pos).getSul_name());
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(favourites.get(pos).getSul_name(), ++count);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, count);
                listener.callUpdateTipString(false);
                SaveManager.saveRecordArrayList();
            }
        });


        return convertView;
    }

    public void setTextView(TextView tv, int pos, int count) {
        String k = favourites.get(pos).getSul_name() + " " + count + favourites.get(pos).getSul_unit();
        tv.setText(k);
    }


    public interface EventListener {
        void callUpdateTipString(boolean refresh_default);
    }

}