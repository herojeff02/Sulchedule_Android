package com.herojeff.sulchedule;

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

import com.herojeff.sulchedule.data.CustomColor;
import com.herojeff.sulchedule.data.CustomDayManager;
import com.herojeff.sulchedule.data.SharedResources;
import com.herojeff.sulchedule.data.Sul;
import com.herojeff.sulchedule.helper.ListViewResizeUtility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;


public class TodayFragment extends Fragment implements View.OnClickListener, SulListViewAdapter.EventListener, DatePickerDialog.OnDateSetListener {

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
        showArray = SharedResources.getMainSuls(CustomDayManager.getYear(), CustomDayManager.getMonth(), CustomDayManager.getDay());

        //캘린더 추가하려는 시도
//        Calendar now = Calendar.getInstance();
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//                this,
//                now.get(Calendar.YEAR), // Initial year selection
//                now.get(Calendar.MONTH), // Initial month selection
//                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
//        );
//        dpd.show(getFragmentManager(), "Datepickerdialog");

        //sul adapter
        listview_sul = view.findViewById(R.id.listview_sul);
        adapter_sul = new SulListViewAdapter(showArray, this);
        listview_sul.setAdapter(adapter_sul);
        listview_sul.setDividerHeight(0);

        text_today = view.findViewById(R.id.text_today);
        text_tip = view.findViewById(R.id.text_tip);
        String temp = CustomDayManager.getMonth() + "월 " + CustomDayManager.getDay() + "일 (" + CustomDayManager.getWeekDayKorean(CustomDayManager.getYear(), CustomDayManager.getMonth(), CustomDayManager.getDay()) + ")";
        text_today.setText(temp);

        //set listview height not to clip content
        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_sul);

        if (SharedResources.getFavouriteSuls().size() == 0) {
            pervert_area.setVisibility(View.INVISIBLE);
        } else {
            pervert_area.setVisibility(View.VISIBLE);
        }

        //more_info adapter
        /**개발 후 되살릴 영역**/
//        listview_more_info = view.findViewById(R.id.listview_more_info);
//        adapter_more_info = new MoreInfoListViewAdapter();
//        listview_more_info.setAdapter(adapter_more_info);
//        listview_more_info.setDividerHeight(0);

        //set listview height not to clip content
//        ListViewResizeUtility.setListViewHeightBasedOnItems(listview_more_info);

        setting_button = view.findViewById(R.id.setting_button);
        setting_button.setOnClickListener(this);
        more_sul_pill = view.findViewById(R.id.pill_more_sul);
        more_sul_pill.setOnClickListener(this);

        /**개발 후 되살릴 영역**/
//        more_blank_pill = view.findViewById(R.id.pill_more_blank);
//        more_blank_pill.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateTipString(false);
        showArray = SharedResources.getMainSuls(CustomDayManager.getYear(), CustomDayManager.getMonth(), CustomDayManager.getDay());
        if (!first) {
            adapter_sul = new SulListViewAdapter(showArray, this);
            listview_sul.setAdapter(adapter_sul);
            ListViewResizeUtility.setListViewHeightBasedOnItems(listview_sul);
        }
        if (SharedResources.getFavouriteSuls().size() == 0) {
            pervert_area.setVisibility(View.INVISIBLE);
        } else {
            pervert_area.setVisibility(View.VISIBLE);
        }
        first = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            /**개발 후 되살릴 영역**/
//            case R.id.pill_more_blank:
//                System.out.println("pill_more_blank");
//                break;
        }

    }


    void settingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this.getContext(), R.style.TodaySettingDialog));
        builder.setTitle("스마트 팁");
        builder.setMessage("켜면 열량, 지출액에 따라 유동적으로 팁을 표시합니다.\n끄면 지출액과 열량만이 표시됩니다.");

        AlertDialog dialog;

        builder.setPositiveButton("켜기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.enable_smart_tip_string = true;
                        updateTipString(false);
                    }
                }).create();
        dialog = builder.setNegativeButton("끄기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedResources.enable_smart_tip_string = false;
                        updateTipString(false);
                    }
                }).create();

        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(CustomColor.color_accent);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(CustomColor.color_accent);
    }

    @Override
    public void callUpdateTipString(boolean refresh_default) {
        updateTipString(refresh_default);
    }

    void updateTipString(boolean refresh_default) {
        text_tip.setText(SharedResources.getSmartTipString(CustomDayManager.getYear(), CustomDayManager.getMonth(), CustomDayManager.getDay(), refresh_default));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "You picked the following date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year, Toast.LENGTH_SHORT).show();
    }
}