package com.herojeff.sulchedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;

import com.herojeff.sulchedule.data.CustomColor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoDialog extends Dialog {

    @BindView(R.id.text_memo) EditText text_memo;
    @BindView(R.id.text_expense) EditText text_expense;

    @BindView(R.id.button_dismiss) TextView button_dismiss;
    @BindView(R.id.button_save) TextView button_save;

    @BindView(R.id.switch_custom) Switch switch_custom;

    @BindView(R.id.text_memo_click_helper) CardView text_memo_click_helper;
    @BindView(R.id.text_expense_container) LinearLayout text_expense_container;

    boolean is_edit_mode = false;

    String memo;
    int expense;

    public MemoDialog(Context context, boolean is_edit_mode) {
        super(context);
        this.is_edit_mode = is_edit_mode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addmemo);
        this.setCanceledOnTouchOutside(false);
        ButterKnife.bind(this);

        if (is_edit_mode) {
            //cancel to delete
            button_dismiss.setTextColor(CustomColor.color_traffic_red);
            button_dismiss.setText("삭제");
            button_dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo delete
                }
            });

        }


        button_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
