package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SaveManager;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;

import java.util.ArrayList;

public class MoreSulListViewAdapter extends BaseAdapter {

    TextView textView;
    ImageView stepper_plus;
    ImageView stepper_minus;
    ImageView heart;
    private ArrayList<Sul> array = new ArrayList<>();

    public MoreSulListViewAdapter() {

    }

    public MoreSulListViewAdapter(ArrayList<Sul> array) {
        this.array = array;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_more_sul_item, parent, false);
        }

        textView = convertView.findViewById(R.id.textview_sul);
        stepper_minus = convertView.findViewById(R.id.stepper_minus);
        stepper_plus = convertView.findViewById(R.id.stepper_plus);
        heart = convertView.findViewById(R.id.favourite);

        if (!array.get(pos).isFavourite()) {
            heart.setImageResource(R.drawable.ic_favourite_hollow);
        } else {
            heart.setImageResource(R.drawable.ic_favourite_filled);
        }

        final int year = CustomDayManager.getYear();
        final int month = CustomDayManager.getMonth();
        final int day = CustomDayManager.getDay();
        int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(array.get(pos).getSul_name());
        textView.setText(String.valueOf(array.get(pos).getSul_name()) + " " + count + array.get(pos).getSul_unit());
        stepper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(array.get(pos).getSul_name());
                --count;
                if (count < 0) {
                    count = 0;
                }
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(array.get(pos).getSul_name(), count);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, count);
                SaveManager.saveRecordArrayList();
            }
        });
        stepper_minus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(array.get(pos).getSul_name(), 0);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, 0);
                SaveManager.saveRecordArrayList();
                return true;
            }
        });
        stepper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = SharedResources.getRecordDay(year, month, day).getCertain_sul_count(array.get(pos).getSul_name());
                SharedResources.getRecordDay(year, month, day).setCertain_sul_count(array.get(pos).getSul_name(), ++count);
                textView = ((View) ((View) (v.getParent())).getParent()).findViewById(R.id.textview_sul);
                setTextView(textView, pos, count);
                SaveManager.saveRecordArrayList();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (array.get(pos).isFavourite()) {
                    array.get(pos).setFavourite(false);
                    setHeartHollow((ImageView) v.findViewById(R.id.favourite));
                } else {
                    array.get(pos).setFavourite(true);
                    setHeartFilled((ImageView) v.findViewById(R.id.favourite));
                }
                SaveManager.saveSulArrayList();
            }
        });

        return convertView;
    }

    public void setTextView(TextView tv, int pos, int count) {
        String k = String.valueOf(array.get(pos).getSul_name()) + " " + count + array.get(pos).getSul_unit();
        tv.setText(k);
    }

    void setHeartHollow(ImageView heart) {
        heart.setImageResource(R.drawable.ic_favourite_hollow);
    }

    void setHeartFilled(ImageView heart) {
        heart.setImageResource(R.drawable.ic_favourite_filled);
    }

}