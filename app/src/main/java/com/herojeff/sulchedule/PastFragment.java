package com.herojeff.sulchedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SharedResources;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

        if (big) {
            adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getMonthlyRecordDayArray(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth()), CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), 0);
        } else {
            adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getRecentRecordDays(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), CustomDayManager.getTodayDay()), CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), SharedResources.getMonthlyRecordDayArrayFromLastMonth(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), CustomDayManager.getTodayDay()).size());
        }

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

        if (big) {
            adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getMonthlyRecordDayArray(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth()), CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), 0);
        } else {
            adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right, SharedResources.getRecentRecordDays(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), CustomDayManager.getTodayDay()), CustomDayManager.getTodayMonth(), CustomDayManager.getTodayYear(), SharedResources.getMonthlyRecordDayArrayFromLastMonth(CustomDayManager.getTodayYear(), CustomDayManager.getTodayMonth(), CustomDayManager.getTodayDay()).size());
        }

        ((PastItemRecyclerViewAdapter) adapter).adapter = adapter;
        ((PastItemRecyclerViewAdapter) adapter).parentFragment = pastFragment;

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        button_left.setTextColor(CustomColor.color_accent);
        button_right.setTextColor(CustomColor.color_white);

        setBig(false);
    }
}
