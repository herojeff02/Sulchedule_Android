package com.herojeff.sulchedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PillSelector extends RelativeLayout {
    public static final int whom = 0;
    public static final int where = 1;
    public static final int expense = 2;
    int mode;
    boolean should_show_price;

    RelativeLayout pill_click;
    TextView pill_selector_string;
    EditText edittext_more_info;
    Button edittext_clear_button;

    public PillSelector(Context context, int mode, boolean should_show_price) {
        super(context);
        this.mode = mode;
        this.should_show_price = should_show_price;
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.listview_more_info_item, this, false);
        addView(v);

        pill_click = v.findViewById(R.id.pill_click);
        pill_selector_string = v.findViewById(R.id.pill_selector_string);
        edittext_more_info = v.findViewById(R.id.edittext_more_info);
        edittext_clear_button = v.findViewById(R.id.edittext_clear_button);

        pill_click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(v, should_show_price);
            }
        });
        edittext_clear_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_more_info.setText("");
            }
        });
        edittext_more_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    edittext_clear_button.setClickable(false);
                    edittext_clear_button.setVisibility(GONE);
                } else {
                    edittext_clear_button.setClickable(true);
                    edittext_clear_button.setVisibility(VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edittext_more_info.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //수정 끝나고 저장하는 코드
                }
            }
        });

        pill_selector_string.setText(getModeString(mode));
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        setPillString(mode);
    }

    public String getModeString(int mode) {
        switch (mode) {
            case 0:
                return "누구와";
            case 1:
                return "어디서";
            case 2:
                return "얼마";
            default:
                return "오류";
        }
    }

    private void createDialog(View view, boolean should_show_price) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(view.getContext(), R.style.TodaySettingDialog));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.goal_dialog_item);
        if (should_show_price) {
            adapter.addAll("누구와", "어디서", "얼마");
        } else {
            adapter.addAll("누구와", "어디서");
        }
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mode = i;
                setPillString(mode);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void setPillString(int mode) {
        pill_selector_string.setText(getModeString(mode));
    }
}
