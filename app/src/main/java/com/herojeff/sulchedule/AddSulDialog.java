package com.herojeff.sulchedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.herojeff.sulchedule.data.SharedResources;

public class AddSulDialog extends Dialog {

    EditText text_name;
    EditText text_unit;
    EditText text_kcal;
    EditText text_price;
    TextView button_dismiss;
    TextView button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addsul);
        this.setCanceledOnTouchOutside(false);

        text_kcal = findViewById(R.id.text_kcal);
        text_name = findViewById(R.id.text_name);
        text_unit = findViewById(R.id.text_unit);
        text_price = findViewById(R.id.text_price);
        button_dismiss = findViewById(R.id.button_dismiss);
        button_save = findViewById(R.id.button_save);

        button_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(text_kcal.getText().length()!=0||text_name.getText().length()!=0||text_unit.getText().length()!=0||text_price.getText().length()!=0){
//                }
//                else{
                    dismiss();
//                }
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                if(text_kcal.getText().length()==0){
                    flag = false;
                    text_kcal.setError("비워 둘 수 없습니다.");
                }
                if(text_name.getText().length()==0){
                    flag = false;
                    text_name.setError("비워 둘 수 없습니다.");
                }
                if(text_unit.getText().length()==0){
                    flag = false;
                    text_unit.setError("비워 둘 수 없습니다.");
                }
                if(text_price.getText().length()==0){
                    flag = false;
                    text_price.setError("비워 둘 수 없습니다.");
                }

                if(flag) {
                    try {
                        SharedResources.addSul(text_name.getText().toString(), Integer.valueOf(text_kcal.getText().toString()), Integer.valueOf(text_price.getText().toString()), text_unit.getText().toString());
                        Toast.makeText(getContext(), "추가됐습니다.", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }catch (Exception e){
                        Toast.makeText(getContext(), "문제가 발생했습니다. 개발자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public AddSulDialog(Context context) {
        super(context);
    }
}