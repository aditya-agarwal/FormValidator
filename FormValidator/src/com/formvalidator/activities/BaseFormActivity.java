package com.formvalidator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.formvalidator.R;
import com.formvalidator.constants.AppConstants;

public class BaseFormActivity extends BaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main );

        Button formOneBtn = (Button)findViewById(R.id.form_1_btn);
        formOneBtn.setOnClickListener(this);

        Button formTwoBtn = (Button)findViewById(R.id.form_2_btn);
        formTwoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.form_1_btn :
                 intent = new Intent(this, FormTypeOneActivity.class);
                startActivity(intent);
                break;
            case R.id.form_2_btn :
                intent = new Intent(this, FormTypeTwoActivity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    public String getTag() {
        return "BaseFormActivity";
    }

    @Override
    public int getFormType() {
        return AppConstants.BASE_FORM_TYPE;
    }
}
