package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.Sul;

import java.util.ArrayList;

public class MoreSulListViewAdapter extends BaseAdapter {

    private ArrayList<Sul> array = new ArrayList<>();

    TextView textView;
    ImageView stepper_plus;
    ImageView stepper_minus;
    ImageView heart;

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

        if(!array.get(pos).isFavourite()){
            heart.setImageResource(R.drawable.ic_favourite_hollow);
        }
        else{
            heart.setImageResource(R.drawable.ic_favourite_filled);
        }
        String text = String.valueOf(array.get(pos).getSul_name()) + " " + 2 + array.get(pos).getSul_unit();
        textView.setText(text);
        stepper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        stepper_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(array.get(pos).isFavourite()){
                    array.get(pos).setFavourite(false);
                    setHeartHollow((ImageView) v.findViewById(R.id.favourite));
                }
                else{
                    array.get(pos).setFavourite(true);
                    setHeartFilled((ImageView) v.findViewById(R.id.favourite));
                }
            }
        });

        return convertView;
    }

    void setHeartHollow(ImageView heart){
        heart.setImageResource(R.drawable.ic_favourite_hollow);
    }
    void setHeartFilled(ImageView heart){
        heart.setImageResource(R.drawable.ic_favourite_filled);
    }

}