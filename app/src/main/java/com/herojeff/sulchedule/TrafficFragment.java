package com.herojeff.sulchedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herojeff.sulchedule.data.SharedResources;

import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.grantland.widget.AutofitHelper;


public class TrafficFragment extends Fragment implements TrafficIndicatorUpdateListener {

    ImageView traffic_red;
    ImageView traffic_yellow;
    ImageView traffic_green;
    TextView text_encouragement;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

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
        adapter = new TrafficGoalRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        AutofitHelper.create(text_encouragement);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initTrafficLight();
    }

    public void initTrafficLight(){
        final float alpha = 0.25f;

        double k = SharedResources.getRecordMonth().getTrafficSignal();

        if (k < 0) {
            text_encouragement.setText("노란 글씨를 눌러 목표 설정");
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(alpha);
        } else if (k < 0.7) {
            String[] strings = {"잘하고 있어요!", "이대로만 하면 돼요!", "파이팅!!!"};
            text_encouragement.setText(strings[new Random().nextInt(strings.length)]);
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(1f);
        } else if (k < 1) {
            String[] strings = {"아슬아슬해요!", "자칫하면..."};
            text_encouragement.setText(strings[new Random().nextInt(strings.length)]);
            traffic_red.setAlpha(alpha);
            traffic_yellow.setAlpha(1f);
            traffic_green.setAlpha(alpha);
        } else {
            String[] strings = {"어떡하려고 그래요…?", "미자 아줌마! 배에 힘 안 줬다!", "어 그렇게 드로우를 많이 하면", "오 심상치 않은데? 오 심상치..."};
            text_encouragement.setText(strings[new Random().nextInt(strings.length)]);
            traffic_red.setAlpha(1f);
            traffic_yellow.setAlpha(alpha);
            traffic_green.setAlpha(alpha);
        }
    }

    @Override
    public void trafficIndicatorInit() {
        initTrafficLight();
    }
}
