package com.herojeff.sulchedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.RecordDay;
import com.herojeff.sulchedule.data.RecordMonth;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;
import com.herojeff.sulchedule.helper.ListViewResizeUtility;

import java.util.ArrayList;

public class PastItemRecyclerViewAdapter extends RecyclerView.Adapter<PastItemRecyclerViewAdapter.PastItemRecyclerViewHolder> {

    public RecyclerView.Adapter adapter;
    public PastFragment parentFragment;
    boolean headerFlag = false;
    boolean headerFlag_Big;

    int currMonth, currIndex = 0;

    TextView button_left_interceptor;
    TextView button_right_interceptor;
    TextView left;
    TextView right;

    TextView title_1;
    TextView title_2;
    TextView title_3;
    TextView desc_1;
    TextView desc_2;
    TextView desc_3;

    ArrayList<RecordDay> recordDays;

    View view;

    public PastItemRecyclerViewAdapter(boolean big, TextView left, TextView right, ArrayList<RecordDay> recordDays, int currMonth, int currIndex) {
        this.headerFlag_Big = big;
        this.left = left;
        this.right = right;
        this.recordDays = recordDays;
        this.currIndex = currIndex;
        this.currMonth = currMonth;
    }

    public void clickHeader(boolean isLeft) {
        if (isLeft) {
            headerFlag_Big = false;
            right.setTextColor(CustomColor.color_white);
            left.setTextColor(CustomColor.color_accent);
            parentFragment.setBig(false);
        } else {
            headerFlag_Big = true;
            right.setTextColor(CustomColor.color_accent);
            left.setTextColor(CustomColor.color_white);
            parentFragment.setBig(true);
        }
    }

    @NonNull
    @Override
    public PastItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //determines which item to load
        if (i == 0) {
            headerFlag = true;
            if (!headerFlag_Big) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_header_small, viewGroup, false);
            } else {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_header_big, viewGroup, false);

                //set listview content
                ListView adapter_past_inner_listview_for_header = view.findViewById(R.id.recyclerview_past_inner_item_container_header);
                PastItemRecyclerViewInnerListViewAdapter adapter_past_inner_listview_adapter = new PastItemRecyclerViewInnerListViewAdapter(null, SharedResources.getRecordMonth(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth()), true);
                adapter_past_inner_listview_for_header.setAdapter(adapter_past_inner_listview_adapter);
                adapter_past_inner_listview_for_header.setDividerHeight(0);
                //set listview height not to clip content
                ListViewResizeUtility.setListViewHeightBasedOnItems(adapter_past_inner_listview_for_header);

                if (view.findViewById(R.id.title_1) != null) {
                    title_1 = view.findViewById(R.id.title_1);
                    title_2 = view.findViewById(R.id.title_2);
                    title_3 = view.findViewById(R.id.title_3);
                    desc_1 = view.findViewById(R.id.desc_1);
                    desc_2 = view.findViewById(R.id.desc_2);
                    desc_3 = view.findViewById(R.id.desc_3);

                    RecordMonth.MonthlyBest monthlyBest = SharedResources.getRecordMonth(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth()).getMonthlyBest();

                    if (monthlyBest.drink_count != 0) {
                        title_1.setText(SharedResources.getSul(monthlyBest.drink_index).getSul_name());
                        desc_1.setText(monthlyBest.drink_count + "병, " + monthlyBest.drink_expense + "원, " + monthlyBest.drink_calorie + "kcal");
                    } else {
                        title_1.setText("정보 부족");
                        desc_1.setText("가장 좋아하는 술");
                    }
                    if (monthlyBest.whom != null) {
                        title_2.setText(monthlyBest.whom);
                        desc_2.setText(monthlyBest.whom_count + "회, " + monthlyBest.whom_expense + "원, " + monthlyBest.whom_calorie + "kcal");
                    } else {
                        title_2.setText("정보 부족");
                        desc_2.setText("함께한 사람");
                    }
                    if (monthlyBest.loc != null) {
                        title_3.setText(monthlyBest.loc);
                        desc_3.setText(monthlyBest.loc_count + "회, " + monthlyBest.loc_expense + "원, " + monthlyBest.loc_calorie + "kcal");
                    } else {
                        title_3.setText("정보 부족");
                        desc_3.setText("좋아하는 장소");
                    }
                }
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
        } else {
            headerFlag = false;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_past_item, viewGroup, false);

            //set listview content
            ListView adapter_past_inner_listview = view.findViewById(R.id.recyclerview_past_inner_item_container);
            TextView tv = view.findViewById(R.id.text_date);

            if(i-1 < currIndex) {
                tv.setText(currMonth - 1 + "월 " + recordDays.get(i - 1).getDay() + "일 (" + CustomDayManager.getWeekDayKorean() + ")");
                if(recordDays.get(i-1).containsDeletedSul()) {
                    tv.append(" (삭제된 주류 포함)");
                }
            }
            else{
                tv.setText(currMonth + "월 " + recordDays.get(i - 1).getDay() + "일 (" + CustomDayManager.getWeekDayKorean() + ")");
                if(recordDays.get(i-1).containsDeletedSul()) {
                    tv.append(" (삭제된 주류 포함)");
                }
            }

            PastItemRecyclerViewInnerListViewAdapter adapter_past_inner_listview_adapter = new PastItemRecyclerViewInnerListViewAdapter(recordDays.get(i - 1), null, false);
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
        if (pastItemRecyclerViewHolder.isHeader) {
            headerBindInit(pastItemRecyclerViewHolder);
        } else {
            bodyBindInit(pastItemRecyclerViewHolder, i);
        }
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
        return recordDays.size() + 1;
    }

    public class PastItemRecyclerViewHolder extends RecyclerView.ViewHolder {
        boolean isHeader = headerFlag;

        public PastItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
