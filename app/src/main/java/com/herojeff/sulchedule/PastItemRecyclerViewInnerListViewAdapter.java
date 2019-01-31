package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.RecordDay;
import com.herojeff.sulchedule.data.SharedResources;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PastItemRecyclerViewInnerListViewAdapter extends BaseAdapter {

    class StringPair{
        String top;
        String bottom;

        public StringPair(String top, String bottom) {
            this.top = top;
            this.bottom = bottom;
        }
    }

    ArrayList<StringPair> arr = new ArrayList<>();

    TextView title;
    TextView description;

    public PastItemRecyclerViewInnerListViewAdapter(@Nullable RecordDay recordDay, boolean shouldLoadAsHeader) {
        if(shouldLoadAsHeader){

        }
        else{
            arr.add(new StringPair(String.valueOf(recordDay.getExpense()) + "원", "총 지출액"));
            arr.add(new StringPair(String.valueOf(recordDay.getCalorie()) + "kcal", "총 열량"));
            HashMap<Integer, Integer> sul_list = recordDay.getSul_list();
            for(int i:sul_list.keySet()){
                arr.add(new StringPair(String.valueOf(sul_list.get(i)) + SharedResources.getSul(i).getSul_unit(), SharedResources.getSul(i).getSul_name()));
            }
            ArrayList<String> friend_list = recordDay.getFriend_list();
            String temp = "";
            for(int i=0;i<friend_list.size();i++){
                temp = temp.concat(friend_list.get(i) + ", ");
            }
            if(temp.length() >= 2) {
                temp = temp.substring(0, temp.length() - 2);
                arr.add(new StringPair(temp, "함께한 사람"));
            }
            ArrayList<String> location_list = recordDay.getLocation_list();
            temp = "";
            for(int i=0;i<location_list.size();i++){
                temp = temp.concat(location_list.get(i) + ", ");
            }
            if(temp.length() >= 2) {
                temp = temp.substring(0, temp.length() - 2);
                arr.add(new StringPair(temp, "장소"));
            }
        }
    }

    @Override
    public int getCount() {
        return arr.size();
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
            convertView = inflater.inflate(R.layout.recyclerview_past_item_inner_item, parent, false);
        }
        title = convertView.findViewById(R.id.text_title);
        description = convertView.findViewById(R.id.text_desc);

        title.setText(arr.get(pos).top);
        description.setText(arr.get(pos).bottom);

        return convertView;
    }

}