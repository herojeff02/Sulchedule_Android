package com.herojeff.sulchedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PastItemRecyclerViewInnerListViewAdapter extends BaseAdapter {

    //    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    String[] arr = {"1 2 3 4", "ㅁㄴㅇㄹㅁㄴㅇ"};

    TextView title;
    TextView description;

    public PastItemRecyclerViewInnerListViewAdapter(boolean shouldLoadAsHeader) {
        if(shouldLoadAsHeader){

        }
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
            convertView = inflater.inflate(R.layout.recyclerview_past_item_inner_item, parent, false);
        }
        title = convertView.findViewById(R.id.text_title);
        description = convertView.findViewById(R.id.text_desc);

        title.setText(String.valueOf(arr[pos]));
        description.setText("desc");
        return convertView;
    }

}