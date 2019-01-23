package com.herojeff.sulchedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;


public class TodayFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageButton setting_button;
    LinearLayout more_sul_pill;
    LinearLayout more_blank_pill;


    public TodayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, null);

        //sul adapter
        ListView listview_sul = view.findViewById(R.id.listview_sul);
        SulListViewAdapter adapter_sul = new SulListViewAdapter();
        listview_sul.setAdapter(adapter_sul);
        listview_sul.setDividerHeight(0);

        //set listview height not to clip content
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_sul);

        //more_info adapter
        ListView listview_more_info = view.findViewById(R.id.listview_more_info);
        MoreInfoListViewAdapter adapter_more_info = new MoreInfoListViewAdapter();
        listview_more_info.setAdapter(adapter_more_info);
        listview_more_info.setDividerHeight(0);

        //set listview height not to clip content
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_info);

        setting_button = view.findViewById(R.id.setting_button);
        setting_button.setOnClickListener(this);
        more_sul_pill = view.findViewById(R.id.pill_more_sul);
        more_sul_pill.setOnClickListener(this);
        more_blank_pill = view.findViewById(R.id.pill_more_blank);
        more_blank_pill.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.setting_button:
                System.out.println("setting_button");
                break;
            case R.id.pill_more_sul:
                intent = new Intent(getContext(), MoreSulActivity.class);
                startActivity(intent);
                break;
            case R.id.pill_more_blank:
                System.out.println("pill_more_blank");
                break;
        }

    }
}

