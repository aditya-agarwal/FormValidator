package com.formvalidator.forms;

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
import com.formvalidator.utils.GroupInfo;
import com.formvalidator.utils.QuestionGroupAdapter;
import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.CustomTableLayout.CustomTableLayout;
import com.formvalidator.widget.RadioGroup.CustomRadioGroup;
import com.formvalidator.widget.ViewFlipper.CustomViewFlipper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO : Remove all hard coded strings
//TODO : Remove all instances of MRP
//TODO : Make it look good

class DuringDayForm extends CustomViewFlipper implements OnItemClickListener,
		OnClickListener, OnCheckedChangeListener, OnItemSelectedListener {

	private ListView mGroupLV = null;
	private ArrayList<GroupInfo> mGroupList = null;

	private LayoutInflater mLayoutInflater = null;

	private QuestionGroupAdapter mGroupListAdapter = null;

	// Volunteer info
	private ArrayAdapter<String> mPrecinctAdapter = null;
	private CustomSpinner mPrecinctAssignedSP = null;
	private ArrayList<String> mPrecinctNameList = null;

	// ArrayList of Forms to validate
	private static List<Integer> mListOfFormsToValidate = Arrays.asList(
			R.id.during_day_voting_process_container,
			R.id.during_day_polling_place_observations_container,
			R.id.during_day_observations_container,
			R.id.during_day_provisional_ballots_container);

	public DuringDayForm(Context context) {
		super(context, 0,205);
	}

	public DuringDayForm(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void initViews() {
		String[] questionArr = getResources().getStringArray(
				R.array.during_day_group_list);
		mGroupList = GroupInfo.initList(questionArr);
		mGroupListAdapter = new QuestionGroupAdapter(getContext(), mGroupList);
		mGroupLV = (ListView) findViewById(R.id.question_group_listview);
		if (null != mGroupLV) {
			mGroupLV.setAdapter(mGroupListAdapter);
			mGroupLV.setOnItemClickListener(this);
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		initFlipper();
		initViews();
	}

	public void initFlipper() {
		mLayoutInflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		populateViewFlipper();
	}

	private CustomRadioGroup initRadioGroupWidget(int widgetId) {
		CustomRadioGroup rg = (CustomRadioGroup) findViewById(widgetId);
		if (null != rg) {
			rg.setOnCheckedChangeListener(this);
		}
		return rg;
	}


	private void populateViewFlipper() {
        addChildToViewFlipper(R.layout.during_day_container);
		addChildToViewFlipper(R.layout.during_day_voting_process);
		addChildToViewFlipper(R.layout.during_day_polling_place_observations);
		addChildToViewFlipper(R.layout.during_day_observations);
		addChildToViewFlipper(R.layout.during_day_provisional_ballots);
	}

	private void addChildToViewFlipper(int layoutId) {
		View v = mLayoutInflater.inflate(layoutId, null);
		addView(v);
        initListeners(v);
	}

    private void initListeners(View v){
        CustomTableLayout tableLayout = (CustomTableLayout)v.findViewById(R.id.custom_table_layout);
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
            }
        }
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// question group list view
		if (R.id.question_group_listview == parent.getId()) {
			setDisplayedChild(position + 2);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.during_day_polling_place_observations_next_btn:
		case R.id.during_day_observations_next_btn:
		case R.id.during_day_voting_process_next_btn:
		case R.id.during_day_provisional_ballots_done_btn:
			setDisplayedChild(1);
			break;
		case R.id.question_group_submit_btn:
			setSubmitButtonClickedFromForm(mFormType);
			processDuringDayForm();
			break;
		}
	}

	private void initPrecinctSpinner() {
		if (null == mPrecinctAdapter) {
			mPrecinctAdapter = new ArrayAdapter<String>(getContext(),
					android.R.layout.simple_spinner_item, mPrecinctNameList);
		}
		mPrecinctAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mPrecinctAssignedSP.setAdapter(mPrecinctAdapter);
		mPrecinctAssignedSP.setOnItemSelectedListener(this);
	}

	private void processDuringDayForm() {
		if (validateForms(mListOfFormsToValidate,
				R.id.custom_table_layout, mGroupLV)){
		} else {
			showAlert();
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group instanceof CustomRadioGroup) {
			((CustomRadioGroup) group).afterRadioButtonItemSelected();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent instanceof CustomSpinner) {
			((CustomSpinner) parent).afterSpinnerItemSelected();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// Nothing todo here
	}
}
