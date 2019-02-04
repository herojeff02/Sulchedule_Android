package com.herojeff.sulchedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;

import java.util.ArrayList;


public class TodayFragment extends Fragment implements View.OnClickListener {

    ImageButton setting_button;
    LinearLayout more_sul_pill;
    LinearLayout more_blank_pill;

    TextView text_today;
    TextView text_tip;

    ListView listview_sul;
    ListView listview_more_info;
    SulListViewAdapter adapter_sul;
    MoreInfoListViewAdapter adapter_more_info;
    View pervert_area;

    ArrayList<Sul> showArray;

    boolean first = true;

    public TodayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
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
        pervert_area = view.findViewById(R.id.pervert_area);
        showArray = SharedResources.getMainSuls(SharedResources.getYear(), SharedResources.getMonth(), SharedResources.getDay());

        //sul adapter
        listview_sul = view.findViewById(R.id.listview_sul);
        adapter_sul = new SulListViewAdapter(SharedResources.getFavouriteSuls());
        listview_sul.setAdapter(adapter_sul);
        listview_sul.setDividerHeight(0);

        text_today = view.findViewById(R.id.text_today);
        text_tip = view.findViewById(R.id.text_tip);
        String temp = SharedResources.getMonth() + "월 " + SharedResources.getDay() + "일 (" + SharedResources.getWeekDayKorean() + ")";
        text_today.setText(temp);
        text_tip.setText("곧 Play Store에서 만나요!");

        //set listview height not to clip content
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_sul);

        if(SharedResources.getFavouriteSuls().size() == 0){
            pervert_area.setVisibility(View.INVISIBLE);
        }
        else{
            pervert_area.setVisibility(View.VISIBLE);
        }

        //more_info adapter
        listview_more_info = view.findViewById(R.id.listview_more_info);
        adapter_more_info = new MoreInfoListViewAdapter();
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
    public void onResume() {
        super.onResume();
        showArray = SharedResources.getMainSuls(SharedResources.getYear(), SharedResources.getMonth(), SharedResources.getDay());
        if (!first) {
            adapter_sul = new SulListViewAdapter(showArray);
            listview_sul.setAdapter(adapter_sul);
            ListViewResizeUtility.setListViewHeightBasedOnItems(listview_sul);
        }
        if(SharedResources.getFavouriteSuls().size() == 0){
            pervert_area.setVisibility(View.INVISIBLE);
        }
        else{
            pervert_area.setVisibility(View.VISIBLE);
        }
        first = false;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.setting_button:
                settingDialog();
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


    void settingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this.getContext(), R.style.TodaySettingDialog));
        builder.setTitle("스마트 팁 표시");
        builder.setMessage("스마트 팁은 열량, 지출액에 따라 유동적으로 팁을 표시합니다.\n해제하면 지출액과 열량만이 표시됩니다.");

        builder.setPositiveButton("켜기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setNegativeButton("끄기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }
}

