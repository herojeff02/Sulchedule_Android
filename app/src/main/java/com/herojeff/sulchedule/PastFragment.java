package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herojeff.sulchedule.data.RecordDay;
import com.herojeff.sulchedule.data.SharedResources;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class PastFragment extends Fragment {

    PastFragment pastFragment;

    TextView button_left;
    TextView button_right;
    boolean big;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public PastFragment() {
        // Required empty public constructor
    }

    public void setPastFragment(PastFragment pastFragment) {
        this.pastFragment = pastFragment;
    }

    public void setBig(boolean big) {
        this.big = big;
        layoutManager = new LinearLayoutManager(getActivity());

        adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getRecentRecordDays(SharedResources.getYear(), SharedResources.getMonth(), SharedResources.getDay()));
        ((PastItemRecyclerViewAdapter) adapter).adapter = adapter;
        ((PastItemRecyclerViewAdapter) adapter).parentFragment = pastFragment;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past, null);


        button_left = view.findViewById(R.id.button_left);
        button_right = view.findViewById(R.id.button_right);

        recyclerView = view.findViewById(R.id.recyclerview_past);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getRecentRecordDays(SharedResources.getYear(), SharedResources.getMonth(), SharedResources.getDay()));
        ((PastItemRecyclerViewAdapter) adapter).adapter = adapter;
        ((PastItemRecyclerViewAdapter) adapter).parentFragment = pastFragment;

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        button_left.setTextColor(SharedResources.color_accent);
        button_right.setTextColor(SharedResources.color_white);

        setBig(false);
    }
}
