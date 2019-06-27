package com.herojeff.sulchedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.RecordMonth;
import com.herojeff.sulchedule.data.SaveManager;
import com.herojeff.sulchedule.data.SharedResources;

import java.util.ArrayList;

public class TrafficGoalRecyclerViewAdapter extends RecyclerView.Adapter<TrafficGoalRecyclerViewAdapter.TrafficGoalRecyclerViewHolder> {

    TrafficIndicatorUpdateListener updateListener;

    int year;
    int month;
    RecordMonth recordMonth;

    boolean[] enabled;
    String[] left_bottom;
    int[] right_top;
    String[] left_top;
    double[] bar_t;

    Context context;

    ArrayList<ArrayList<String>> spinnerValues = new ArrayList<>();

    public TrafficGoalRecyclerViewAdapter(TrafficIndicatorUpdateListener listener) {
        year = CustomDayManager.getTodayYear();
        month = CustomDayManager.getTodayMonth();
        recordMonth = SharedResources.getRecordMonth(year, month);

        updateListener = listener;

        refreshDisplayArrayValue();
    }

    private void refreshDisplayArrayValue() {
        enabled = new boolean[]{
                recordMonth.isEnable_daysOfMonth(), recordMonth.isEnable_streakOfMonth(), recordMonth.isEnable_totalExpense(), recordMonth.isEnable_caloriesOfMonth()
        };
        bar_t = new double[]{
                recordMonth.goalStat_daysOfMonth(), recordMonth.goalStat_streakOfMonth(), recordMonth.goalStat_totalExpense(), recordMonth.goalStat_caloriesOfMonth()
        };
        left_top = new String[]{
                recordMonth.stat_daysOfMonth() + "일", recordMonth.stat_streakOfMonth() + "일", recordMonth.stat_totalExpense() + "원", recordMonth.stat_caloriesOfMonth() + "kcal"
        };
        right_top = new int[]{
                recordMonth.getGoal_daysOfMonth(), recordMonth.getGoal_streakOfMonth(), recordMonth.getGoal_totalExpense(), recordMonth.getGoal_caloriesOfMonth()
        };
        left_bottom = new String[]{
                CustomDayManager.getTodayMonth() + "월 음주 일수", CustomDayManager.getTodayMonth() + "월 연이은 음주 일수", "지출액", "열량"
        };
    }

    @NonNull
    @Override
    public TrafficGoalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_goal_item, viewGroup, false);
        context = view.getContext();

        initSpinnerValue();

        return new TrafficGoalRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, final int i) {
        textInit(trafficGoalRecyclerViewHolder, i);
        barInit(trafficGoalRecyclerViewHolder, i, enabled[i]);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.goal_dialog_item);
        adapter.addAll(spinnerValues.get(i));

        trafficGoalRecyclerViewHolder.text_right_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(adapter, view, trafficGoalRecyclerViewHolder, i);
            }
        });
    }

    private void barInit(final TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, final int i, boolean enabled) {
        if (enabled) {
            trafficGoalRecyclerViewHolder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    int cellWidth = trafficGoalRecyclerViewHolder.graph_full_bar.getWidth();// this will give you cell width dynamically
                    trafficGoalRecyclerViewHolder.graph_overlay.setLayoutParams(new RelativeLayout.LayoutParams((int) (cellWidth * bar_t[i]), trafficGoalRecyclerViewHolder.graph_full_bar.getHeight()));
                }
            });

            if (bar_t[i] >= 1.0) {
                trafficGoalRecyclerViewHolder.graph_overlay.setImageTintList(ColorStateList.valueOf(CustomColor.color_traffic_red));
            } else if (bar_t[i] >= 0.7) {
                trafficGoalRecyclerViewHolder.graph_overlay.setImageTintList(ColorStateList.valueOf(CustomColor.color_traffic_yellow));
            } else {
                trafficGoalRecyclerViewHolder.graph_overlay.setImageTintList(ColorStateList.valueOf(CustomColor.color_traffic_green));
            }
        } else {
            trafficGoalRecyclerViewHolder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    trafficGoalRecyclerViewHolder.graph_overlay.setLayoutParams(new RelativeLayout.LayoutParams(0, trafficGoalRecyclerViewHolder.graph_full_bar.getHeight()));
                }
            });
        }
    }

    private void textInit(final TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, final int i) {
        if (enabled[i]) {
            trafficGoalRecyclerViewHolder.text_right_top.setText(String.valueOf(right_top[i]));
            trafficGoalRecyclerViewHolder.text_right_top.setTextColor(CustomColor.color_white);
        } else {
            trafficGoalRecyclerViewHolder.text_right_top.setTextColor(CustomColor.color_accent);
            trafficGoalRecyclerViewHolder.text_right_top.setText("목표 설정");
        }
        trafficGoalRecyclerViewHolder.text_left_top.setText(left_top[i]);
        if (bar_t[i] >= 1.0 && enabled[i]) {
            trafficGoalRecyclerViewHolder.text_left_top.setTextColor(CustomColor.color_traffic_red);
        } else {
            trafficGoalRecyclerViewHolder.text_left_top.setTextColor(CustomColor.color_white);
        }
        trafficGoalRecyclerViewHolder.text_left_bottom.setText(left_bottom[i]);
        trafficGoalRecyclerViewHolder.text_right_bottom.setText("한도");
    }

    void createDialog(ArrayAdapter<String> adapter, View view, final TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(view.getContext(), R.style.TodaySettingDialog));
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                        trafficGoalRecyclerViewHolder.text_right_top.setText(adapter.getItem(i));
                switch (trafficGoalRecyclerViewHolder.getAdapterPosition()) {
                    case 0:
                        if (i == 0) {
                            SharedResources.getRecordMonth().setEnable_daysOfMonth(false);
                        } else {
                            SharedResources.getRecordMonth().setEnable_daysOfMonth(true);
                            String k = spinnerValues.get(0).get(i);
                            SharedResources.getRecordMonth().setGoal_daysOfMonth(Integer.parseInt(k.substring(0, k.length() - 1)));
                        }
                        break;
                    case 1:
                        if (i == 0) {
                            SharedResources.getRecordMonth().setEnable_streakOfMonth(false);
                        } else {
                            SharedResources.getRecordMonth().setEnable_streakOfMonth(true);
                            String k = spinnerValues.get(1).get(i);
                            SharedResources.getRecordMonth().setGoal_streakOfMonth(Integer.parseInt(k.substring(0, k.length() - 1)));
                        }
                        break;
                    case 2:
                        if (i == 0) {
                            SharedResources.getRecordMonth().setEnable_totalExpense(false);
                        } else {
                            SharedResources.getRecordMonth().setEnable_totalExpense(true);
                            String k = spinnerValues.get(2).get(i);
                            SharedResources.getRecordMonth().setGoal_totalExpense(Integer.parseInt(k.substring(0, k.length() - 1)));
                        }
                        break;
                    case 3:
                        if (i == 0) {
                            SharedResources.getRecordMonth().setEnable_caloriesOfMonth(false);
                        } else {
                            SharedResources.getRecordMonth().setEnable_caloriesOfMonth(true);
                            String k = spinnerValues.get(3).get(i);
                            SharedResources.getRecordMonth().setGoal_caloriesOfMonth(Integer.parseInt(k.substring(0, k.length() - 4)));
                        }
                        break;

                }

                SaveManager.saveRecordArrayList();
                refreshDisplayArrayValue();
            }
        });


        builder.setTitle(left_bottom[i] + " 설정");

        Dialog dialog = builder.create();
        int width = (int) (view.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (view.getResources().getDisplayMetrics().heightPixels * 0.60);

        dialog.show();
        dialog.getWindow().setLayout(width, height);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                barInit(trafficGoalRecyclerViewHolder, i, enabled[i]);
                textInit(trafficGoalRecyclerViewHolder, i);
                trafficIndicatorInit();
            }
        });
    }

    public void trafficIndicatorInit() {
        updateListener.trafficIndicatorInit();
    }


    @Override
    public void onViewAttachedToWindow(@NonNull TrafficGoalRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    void initSpinnerValue() {
        spinnerValues.add(0, new ArrayList<String>());
        spinnerValues.add(1, new ArrayList<String>());
        spinnerValues.add(2, new ArrayList<String>());
        spinnerValues.add(3, new ArrayList<String>());

        spinnerValues.get(0).add("목표 비활성화");
        spinnerValues.get(1).add("목표 비활성화");
        spinnerValues.get(2).add("목표 비활성화");
        spinnerValues.get(3).add("목표 비활성화");

        //0
        for (int i = 0; i <= CustomDayManager.getLastDayOfMonth(CustomDayManager.getMonth()); i++) {
            spinnerValues.get(0).add(i + "일");
        }

        //1
        for (int i = 0; i <= CustomDayManager.getLastDayOfMonth(CustomDayManager.getMonth()); i++) {
            spinnerValues.get(1).add(i + "일");
        }

        //2
        for (int i = 0; i <= 19; i++) {
            spinnerValues.get(2).add(i * 5000 + "원");
        }
        for (int i = 2; i <= 10; i++) {
            spinnerValues.get(2).add(i * 50000 + "원");
        }
        //5000씩, 100000까지, 50000씩, 500000까지

        //3
        for (int i = 0; i <= 19; i++) {
            spinnerValues.get(3).add(i * 50 + "kcal");
        }
        for (int i = 10; i <= 49; i++) {
            spinnerValues.get(3).add(i * 100 + "kcal");
        }
        for (int i = 10; i <= 19; i++) {
            spinnerValues.get(3).add(i * 500 + "kcal");
        }
        for (int i = 2; i <= 20; i++) {
            spinnerValues.get(3).add(i * 5000 + "kcal");
        }
        //50씩, 1000까지, 100씩, 5000까지, 500씩, 10000까지, 5000씩 100000까지
    }

    public class TrafficGoalRecyclerViewHolder extends RecyclerView.ViewHolder {

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
