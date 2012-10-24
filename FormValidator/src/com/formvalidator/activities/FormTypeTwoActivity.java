package com.formvalidator.activities;

import android.os.Bundle;
import com.formvalidator.R;
import com.formvalidator.interfaces.BackKeyHandler;
import com.formvalidator.widget.ViewFlipper.CustomViewFlipper;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 10/18/12
 * Time: 10:58 PM
 */
public class FormTypeTwoActivity extends BaseActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_type_2 );
    }

    @Override
    public void onBackPressed(){
        CustomViewFlipper view = (CustomViewFlipper)findViewById(R.id.ViewFlipper01);
        view.onBackPressed(new BackKeyHandler(){

            public void callSuper(){
                FormTypeTwoActivity.super.onBackPressed();
            }
        });
    }
    @Override
    public String getTag() {
        return "FormTypeTwoActivity";
    }
}
