package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.herojeff.sulchedule.data.RecordDayMoreInfoManager;

public class MoreInfoListViewAdapter extends BaseAdapter {

    private final RecordDayMoreInfoManager manager;

    public MoreInfoListViewAdapter(RecordDayMoreInfoManager manager) {
        this.manager = manager;
    }

    @Override
    public int getCount() {
        return manager.size();
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
    public View getView(final int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_moreinfo_item, parent, false);
        }

        TextView location = convertView.findViewById(R.id.textview_location);
        TextView rest = convertView.findViewById(R.id.textview_rest);

        if (manager.get(pos).getLocation().length() == 0) {
            location.setVisibility(View.GONE);
        } else {
            location.setText(manager.get(pos).getLocation());
        }
        rest.setText(buildSummaryString(pos));

        return convertView;
    }

    private String buildSummaryString(int pos) {
        int count = 0;
        StringBuilder returnString = new StringBuilder();

        if (manager.get(pos).getLocation().length() == 0) {
            //add friends
            for (String item : manager.getFriendList()) {
                count++;
                returnString.append(item).append(", ");
            }
            returnString.delete(returnString.length() - 3, returnString.length() - 1);

            if (manager.get(pos).getCustomExpense() != 0) {
                returnString.append("과(와) ").append(manager.get(pos).getCustomExpense()).append("원");
            }
        }


        return returnString.toString();
    }
}
