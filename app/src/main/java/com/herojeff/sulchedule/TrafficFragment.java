package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;


public class TrafficFragment extends Fragment {

    ImageView traffic_red;
    ImageView traffic_yellow;
    ImageView traffic_green;
    TextView text_encouragement;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    boolean edit_mode = false;

    public TrafficFragment() {
        // Required empty public constructor
    }

    public static TrafficFragment newInstance(String param1, String param2) {
        TrafficFragment fragment = new TrafficFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic, null);

        recyclerView = view.findViewById(R.id.recyclerview_goal);
        recyclerView.setHasFixedSize(true);

        traffic_red = view.findViewById(R.id.traffic_red);
        traffic_yellow = view.findViewById(R.id.traffic_yellow);
        traffic_green = view.findViewById(R.id.traffic_green);
        text_encouragement = view.findViewById(R.id.text_encouragement);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TrafficGoalRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final float alpha = 0.25f;

        double k = SharedResources.getRecordMonth().getTrafficSignal();

        if (k < 0) {
            text_encouragement.setText("목표를 설정하세요.");
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(alpha);
        } else if (k < 0.7) {
            text_encouragement.setText("잘 하고 있어요!");
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(1f);
        } else if (k < 1) {
            text_encouragement.setText("아슬아슬해요!");
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(1f);
            traffic_green.setAlpha(alpha);
        } else {
            text_encouragement.setText("어떡하려고 그래요…?");
            traffic_red.setAlpha(1f);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(alpha);
        }
    }
}
