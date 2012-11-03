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
public class GroupFormActivity extends BaseActivity {
    private String LOG_TAG = "GroupFormActivity";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_form);
    }

    @Override
    public void onBackPressed() {
        CustomViewFlipper view = (CustomViewFlipper) findViewById(R.id.ViewFlipper01);
        view.onBackPressed(new BackKeyHandler() {

            public void callSuper() {
                GroupFormActivity.super.onBackPressed();
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
