package com.herojeff.sulchedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;

public class PastItemRecyclerViewAdapter extends RecyclerView.Adapter<PastItemRecyclerViewAdapter.PastItemRecyclerViewHolder> {

    public RecyclerView.Adapter adapter;
    public PastFragment parentFragment;
    boolean headerFlag = false;
    boolean headerFlag_Big;

    TextView button_left_interceptor;
    TextView button_right_interceptor;
    TextView left;
    TextView right;

    View view;

    public PastItemRecyclerViewAdapter(boolean big, TextView left, TextView right){
        this.headerFlag_Big = big;
        this.left = left;
        this.right = right;
    }

    public void clickHeader(boolean isLeft){
        if(isLeft){
            headerFlag_Big = false;
            right.setTextColor(SharedResources.color_white);
            left.setTextColor(SharedResources.color_accent);
            parentFragment.setBig(false);
        }
        else{
            headerFlag_Big = true;
            right.setTextColor(SharedResources.color_accent);
            left.setTextColor(SharedResources.color_white);
            parentFragment.setBig(true);
        }
    }

    @NonNull
    @Override
    public PastItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //determines which item to load
        if(i==0){
            headerFlag = true;
            if(!headerFlag_Big) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_header_small, viewGroup, false);
            }
            else{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_header_big, viewGroup, false);

                //set listview content
                ListView adapter_past_inner_listview_for_header = view.findViewById(R.id.recyclerview_past_inner_item_container_header);
                PastItemRecyclerViewInnerListViewAdapter adapter_past_inner_listview_adapter = new PastItemRecyclerViewInnerListViewAdapter(true);
                adapter_past_inner_listview_for_header.setAdapter(adapter_past_inner_listview_adapter);
                adapter_past_inner_listview_for_header.setDividerHeight(0);
                //set listview height not to clip content
                ListViewResizeUtility.setListViewHeightBasedOnItems(adapter_past_inner_listview_for_header);
            }

            //onclicklistener for header
            button_left_interceptor = view.findViewById(R.id.button_left_touch_interceptor);
            button_right_interceptor = view.findViewById(R.id.button_right_touch_interceptor);
            button_left_interceptor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHeader(true);
                }
            });
            button_right_interceptor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHeader(false);
                }
            });
        }
        else{
            headerFlag = false;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_item, viewGroup, false);

            //set listview content
            ListView adapter_past_inner_listview = view.findViewById(R.id.recyclerview_past_inner_item_container);
            PastItemRecyclerViewInnerListViewAdapter adapter_past_inner_listview_adapter = new PastItemRecyclerViewInnerListViewAdapter(false);
            adapter_past_inner_listview.setAdapter(adapter_past_inner_listview_adapter);
            adapter_past_inner_listview.setDividerHeight(0);
            //set listview height not to clip content
            ListViewResizeUtility.setListViewHeightBasedOnItems(adapter_past_inner_listview);
        }


        PastItemRecyclerViewHolder viewHolder = new PastItemRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PastItemRecyclerViewHolder pastItemRecyclerViewHolder, int i) {
        if(pastItemRecyclerViewHolder.isHeader){
            headerBindInit(pastItemRecyclerViewHolder);
        }
        else {
            bodyBindInit(pastItemRecyclerViewHolder, i);
        }
//        Movie movie = moviesList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }

    //헤더 바인드
    private void headerBindInit(final PastItemRecyclerViewHolder holder) {
    }

    //헤더가 아닌경우 게시물들을 바인드
    private void bodyBindInit(final PastItemRecyclerViewHolder holder, final int position) {
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 3;
//        return 데이터의 갯수+1;
    }

    public class PastItemRecyclerViewHolder extends RecyclerView.ViewHolder{
        boolean isHeader = headerFlag;
        public PastItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
