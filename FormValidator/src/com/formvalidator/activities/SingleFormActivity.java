package com.formvalidator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.formvalidator.R;
import com.formvalidator.constants.AppConstants;
import com.formvalidator.utils.SharedPrefUtils;
import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.CustomTableLayout.CustomTableLayout;
import com.formvalidator.widget.CustomRadioGroup.CustomRadioGroup;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 10/18/12
 * Time: 10:59 PM
 */
public class SingleFormActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private String LOG_TAG = "SingleFormActivity";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_type_1 );
        initListeners();
        initSpinner();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.validate_btn:
                validateForm();
                try {
                    SharedPrefUtils.saveSubmitButtonStatus(203,true,this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Method to set listeners for radio group and spinner inside the custom table layout
     */
    private void initListeners(){
        CustomTableLayout tableLayout = (CustomTableLayout)findViewById(R.id.form_1_table_layout);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);

            if (row.getChildCount() > 1) {
                View view = row.getChildAt(1);

                if (view instanceof CustomSpinner) {
                    ((CustomSpinner) view).setOnItemSelectedListener(this);
                }
                if (view instanceof CustomRadioGroup) {
                    ((CustomRadioGroup) view).setOnCheckedChangeListener(this);
                }
            } else if(row.getChildCount() == 1){
                View view = row.getChildAt(0);
                if(view instanceof Button) {
                    ((Button)view).setOnClickListener(this);
                }
            }
        }
    }

    private void initSpinner() {

        ArrayList spinnerList = new ArrayList<Integer>();

        for (int i = 0; i <= 10; i++) {
            spinnerList.add(i);
        }

        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item,
                spinnerList);
        spinnerArrayAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CustomSpinner spinner = (CustomSpinner) findViewById(R.id.form_1_answer_3_spinner);

        if (null != spinner) {
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }

    @Override
    public String getTag() {
        return LOG_TAG;
    }

    @Override
    public int getFormType() {
        return AppConstants.FORM_TYPE_ONE;
    }

    private void validateForm(){
        CustomTableLayout tableLayout = (CustomTableLayout)findViewById(R.id.form_1_table_layout);
        tableLayout.validateForm();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(radioGroup instanceof CustomRadioGroup){
            ((CustomRadioGroup) radioGroup).afterRadioButtonItemSelected();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //If the spinner used in the xml is of type Custom then calling the method will validate the fields
        // and show the error tags
        if (adapterView instanceof CustomSpinner) {
            ((CustomSpinner) adapterView).afterSpinnerItemSelected();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
