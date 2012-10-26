package com.formvalidator.forms;

/**
 * Created with IntelliJ IDEA. User: Aditya Agarwal Date: 9/17/12 Time: 12:21 PM
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.formvalidator.R;
import com.formvalidator.interfaces.BackKeyHandler;
import com.formvalidator.utils.GroupInfo;
import com.formvalidator.utils.QuestionGroupAdapter;
import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.CustomTableLayout.CustomTableLayout;
import com.formvalidator.widget.CustomRadioGroup.CustomRadioGroup;
import com.formvalidator.widget.CustomViewFlipper.CustomViewFlipper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DuringDayForm extends CustomViewFlipper implements OnItemClickListener, OnClickListener,
        OnCheckedChangeListener, OnItemSelectedListener {

    private ListView mGroupLV = null;
    private ArrayList<GroupInfo> mGroupList = null;

    private LayoutInflater mLayoutInflater = null;

    private QuestionGroupAdapter mGroupListAdapter = null;

    // ArrayList of Forms to validate
	private static List<Integer> mListOfFormsToValidate = Arrays.asList(
			R.id.during_day_voting_process_container,
			R.id.during_day_polling_place_observations_container,
			R.id.during_day_observations_container,
			R.id.during_day_provisional_ballots_container);


    public DuringDayForm(Context context) {
        super(context, 0, 205);
    }

    public DuringDayForm(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public String getTag() {
        return "DuringDayForm";
    }

    @Override
    public void onBackPressed(BackKeyHandler callBack) {
        if (null != callBack && getDisplayedChild() == 0) {
            callBack.callSuper();
        } else {
            setDisplayedChild(0);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initViewFlipper();
        initGroupList();
    }

    /**
     * Initalize the layout inflater and
     * Add all the layouts of groups to the view flipper
     */
    private void initViewFlipper() {
        mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addChildToViewFlipper(R.layout.during_day_container);
        addChildToViewFlipper(R.layout.during_day_voting_process);
        addChildToViewFlipper(R.layout.during_day_polling_place_observations);
        addChildToViewFlipper(R.layout.during_day_observations);
        addChildToViewFlipper(R.layout.during_day_provisional_ballots);
    }

    /**
     * -Initialize the List view of the Groups
     * -Set the List adapter of the Group
     * -Set the click listener of the submit button
     */
    private void initGroupList() {
        String[] groupNameArr = getResources().getStringArray(R.array.during_day_group_list);
        mGroupList = GroupInfo.initListOfGroups(groupNameArr);
        mGroupListAdapter = new QuestionGroupAdapter(getContext(), mGroupList);
        mGroupLV = (ListView) findViewById(R.id.question_group_listview);
        if (null != mGroupLV) {
            mGroupLV.setAdapter(mGroupListAdapter);
            mGroupLV.setOnItemClickListener(this);
        }

        Button submitBtn = (Button) findViewById(R.id.question_group_submit_btn);
        submitBtn.setOnClickListener(this);
    }

    /**
     * Method to add layouts to the view flipper
     *
     * @param layoutId
     */
    private void addChildToViewFlipper(int layoutId) {
        View v = mLayoutInflater.inflate(layoutId, null);
        addView(v);
        initListeners(v);
    }

    /**
     * Method to set listeners for radio group and spinner inside the custom table layout
     *
     * @param layout
     */
    private void initListeners(View layout) {
        CustomTableLayout tableLayout = (CustomTableLayout) layout.findViewById(R.id.custom_table_layout);
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
            } else if (row.getChildCount() == 1) {
                View view = row.getChildAt(0);
                if (view instanceof Button) {
                    ((Button) view).setOnClickListener(this);
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // question group list view
        if (R.id.question_group_listview == parent.getId()) {
            setDisplayedChild(position + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.during_day_polling_place_observations_next_btn:
            case R.id.during_day_observations_next_btn:
            case R.id.during_day_voting_process_next_btn:
            case R.id.during_day_provisional_ballots_done_btn:
                setDisplayedChild(0);
                break;
            case R.id.question_group_submit_btn:
                setSubmitButtonClickedFromForm(mFormType);
                processDuringDayForm();
                break;
            default:
                break;
        }
    }

    /**
     * Called when Submit button is clicked
     * - Validates the form and handles submission process
     */
    private void processDuringDayForm() {
        if (validateForms(mListOfFormsToValidate, R.id.custom_table_layout, mGroupLV)) {
            //DO SOMETHING OVER HERE LIKE SUBMIT DATA ETC ..
        } else {
            showAlert();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //If the radio group used in the xml is of type Custom then calling the method will validate the fields
        // and show the error tags
        if (group instanceof CustomRadioGroup) {
            ((CustomRadioGroup) group).afterRadioButtonItemSelected();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //If the spinner used in the xml is of type Custom then calling the method will validate the fields
        // and show the error tags
        if (parent instanceof CustomSpinner) {
            ((CustomSpinner) parent).afterSpinnerItemSelected();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // Nothing todo here
    }
}
