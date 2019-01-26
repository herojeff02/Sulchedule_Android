package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


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
        adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right);
        ((PastItemRecyclerViewAdapter) adapter).adapter = adapter;
        ((PastItemRecyclerViewAdapter) adapter).parentFragment = pastFragment;
        recyclerView.setAdapter(adapter);
    }

    public static PastFragment newInstance(String param1, String param2) {
        PastFragment fragment = new PastFragment();
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
        View view = inflater.inflate(R.layout.fragment_past, null);

        button_left = view.findViewById(R.id.button_left);
        button_right = view.findViewById(R.id.button_right);

        recyclerView = view.findViewById(R.id.recyclerview_past);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PastItemRecyclerViewAdapter(big, button_left, button_right);
        ((PastItemRecyclerViewAdapter) adapter).adapter = adapter;
        ((PastItemRecyclerViewAdapter) adapter).parentFragment = pastFragment;

        recyclerView.setAdapter(adapter);

        return view;
    }
}
