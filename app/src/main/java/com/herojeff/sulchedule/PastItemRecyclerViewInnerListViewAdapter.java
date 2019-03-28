package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.herojeff.sulchedule.data.RecordDay;
import com.herojeff.sulchedule.data.RecordMonth;
import com.herojeff.sulchedule.data.SharedResources;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class PastItemRecyclerViewInnerListViewAdapter extends BaseAdapter {

    ArrayList<StringPair> arr = new ArrayList<>();
    TextView title;
    TextView description;

    public PastItemRecyclerViewInnerListViewAdapter(@Nullable RecordDay recordDay, @Nullable RecordMonth recordMonth, boolean shouldLoadAsHeader) {
        if (shouldLoadAsHeader) {
            arr.add(new StringPair(String.valueOf(recordMonth.getTotalExpense()) + "원", "총 지출액"));
            arr.add(new StringPair(String.valueOf(recordMonth.getTotalCalorie()) + "kcal", "총 열량"));

            HashMap<Integer, Integer> sulCompilation = recordMonth.getMonthlySulCompilation();
            HashMap<String, Integer> friendCompilation = recordMonth.getMonthlyFriendCompilation();
            HashMap<String, Integer> locationCompilation = recordMonth.getMonthlyLocationCompilation();
            for (int i : sulCompilation.keySet()) {
                if (SharedResources.getSul(i).isSul_enabled() && sulCompilation.get(i) != 0) {
                    arr.add(new StringPair(String.valueOf(sulCompilation.get(i)) + SharedResources.getSul(i).getSul_unit(), SharedResources.getSul(i).getSul_name()));
                }
            }
            for (String i : friendCompilation.keySet()) {
                arr.add(new StringPair(String.valueOf(friendCompilation.get(i)) + "회 함께함", i));
            }
            for (String i : locationCompilation.keySet()) {
                arr.add(new StringPair(String.valueOf(locationCompilation.get(i)) + "회 방문", i));
            }

        } else {
            arr.add(new StringPair(String.valueOf(recordDay.getExpense()) + "원", "총 지출액"));
            arr.add(new StringPair(String.valueOf(recordDay.getCalorie()) + "kcal", "총 열량"));
            HashMap<Integer, Integer> sul_list = recordDay.getSul_list();
            for (int i : sul_list.keySet()) {
                if (sul_list.get(i) != 0) {
                    if (SharedResources.getSul(i).isSul_enabled() && recordDay.getCertain_sul_count(i) != 0) {
                        arr.add(new StringPair(String.valueOf(sul_list.get(i)) + SharedResources.getSul(i).getSul_unit(), SharedResources.getSul(i).getSul_name()));
                    }
                }
            }
            ArrayList<String> friend_list = recordDay.getFriend_list();
            String temp = "";
            for (int i = 0; i < friend_list.size(); i++) {
                temp = temp.concat(friend_list.get(i) + ", ");
            }
            if (temp.length() >= 2) {
                temp = temp.substring(0, temp.length() - 2);
                arr.add(new StringPair(temp, "함께한 사람"));
            }
            ArrayList<String> location_list = recordDay.getLocation_list();
            temp = "";
            for (int i = 0; i < location_list.size(); i++) {
                temp = temp.concat(location_list.get(i) + ", ");
            }
            if (temp.length() >= 2) {
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

    class StringPair {
        String top;
        String bottom;

        public StringPair(String top, String bottom) {
            this.top = top;
            this.bottom = bottom;
        }
    }

}