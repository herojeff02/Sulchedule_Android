package com.herojeff.sulchedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class AddSulDialog extends Dialog {

    TextView text_name;
    TextView text_unit;
    TextView text_kcal;
    TextView text_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_sul);

        text_kcal = findViewById(R.id.text_kcal);
        text_name = findViewById(R.id.text_name);
        text_unit = findViewById(R.id.text_unit);
        text_price = findViewById(R.id.text_price);

    }

    public AddSulDialog(Context context) {
        super(context);
    }
}
