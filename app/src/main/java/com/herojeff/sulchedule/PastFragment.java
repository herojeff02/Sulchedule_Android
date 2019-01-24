package com.herojeff.sulchedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PastFragment extends Fragment implements View.OnClickListener {

    TextView button_left;
    TextView button_right;

    public PastFragment() {
        // Required empty public constructor
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

        button_left.setOnClickListener(this);
        button_right.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_left:
                button_right.setTextColor(SharedResources.color_white);
                button_left.setTextColor(SharedResources.color_accent);
                break;
            case R.id.button_right:
                button_right.setTextColor(SharedResources.color_accent);
                button_left.setTextColor(SharedResources.color_white);
                break;
        }

    }
}
