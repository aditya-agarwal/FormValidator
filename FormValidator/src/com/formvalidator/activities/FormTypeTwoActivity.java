package com.formvalidator.activities;

import android.os.Bundle;
import com.formvalidator.R;
import com.formvalidator.constants.AppConstants;
import com.formvalidator.interfaces.BackKeyHandler;
import com.formvalidator.widget.CustomViewFlipper.CustomViewFlipper;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 10/18/12
 * Time: 10:58 PM
 */
public class FormTypeTwoActivity extends BaseActivity {
    private String LOG_TAG = "FormTypeTwoActivity";

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
        return LOG_TAG;
    }

    @Override
    public int getFormType() {
        return AppConstants.FORM_TYPE_TWO;
    }
}
