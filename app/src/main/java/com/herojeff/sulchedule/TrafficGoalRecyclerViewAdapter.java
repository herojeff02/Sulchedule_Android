package com.herojeff.sulchedule;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.herojeff.sulchedule.data.RecordMonth;
import com.herojeff.sulchedule.data.SharedResources;

public class TrafficGoalRecyclerViewAdapter extends RecyclerView.Adapter<TrafficGoalRecyclerViewAdapter.TrafficGoalRecyclerViewHolder> {

    int year = SharedResources.getYear();
    int month = SharedResources.getMonth();
    RecordMonth recordMonth = SharedResources.getRecordMonth(year, month);


    String[] left_bottom = {
        SharedResources.getMonth() + "월 음주 일수", SharedResources.getMonth() + "월 연이은 음주 일수", "지출액", "열량"
    };
    int[] right_top = {
            recordMonth.getGoal_daysOfMonth(), recordMonth.getGoal_streakOfMonth(), recordMonth.getTotalExpense(), recordMonth.getTotalCalorie()
    };
    String[] left_top = {
            recordMonth.stat_daysOfMonth() + "일", recordMonth.stat_streakOfMonth() + "일", recordMonth.stat_totalExpense() + "원", recordMonth.stat_caloriesOfMonth() + "kcal"
    };
    double[] bar_t = {
            recordMonth.goalStat_daysOfMonth(), recordMonth.goalStat_streakOfMonth(), recordMonth.goalStat_totalExpense(), recordMonth.goalStat_caloriesOfMonth()
    };

    @NonNull
    @Override
    public TrafficGoalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_goal_item, viewGroup, false);

        recordMonth.setGoal_caloriesOfMonth(200);

        return new TrafficGoalRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, final int i) {
        trafficGoalRecyclerViewHolder.text_left_top.setText(left_top[i]);
        trafficGoalRecyclerViewHolder.text_right_top.setText(String.valueOf(right_top[i]));
        trafficGoalRecyclerViewHolder.text_left_bottom.setText(left_bottom[i]);
        trafficGoalRecyclerViewHolder.text_right_bottom.setText("한도");

        trafficGoalRecyclerViewHolder.itemView.post(new Runnable()
        {
            @Override
            public void run()
            {
                int cellWidth = trafficGoalRecyclerViewHolder.graph_full_bar.getWidth();// this will give you cell width dynamically
                trafficGoalRecyclerViewHolder.graph_overlay.setLayoutParams(new RelativeLayout.LayoutParams((int) (cellWidth * bar_t[i]), trafficGoalRecyclerViewHolder.graph_full_bar.getHeight()));
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull TrafficGoalRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class TrafficGoalRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView text_left_top;
        TextView text_right_top;
        TextView text_left_bottom;
        TextView text_right_bottom;
        ImageView graph_overlay;
        ImageView graph_full_bar;

        public TrafficGoalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            text_left_top = itemView.findViewById(R.id.text_left_top);
            text_right_top = itemView.findViewById(R.id.text_right_top);
            text_left_bottom = itemView.findViewById(R.id.text_left_bottom);
            text_right_bottom = itemView.findViewById(R.id.text_right_bottom);
            graph_overlay = itemView.findViewById(R.id.graph_overlay);
            graph_full_bar = itemView.findViewById(R.id.graph_full_bar);


        }
    }
}
