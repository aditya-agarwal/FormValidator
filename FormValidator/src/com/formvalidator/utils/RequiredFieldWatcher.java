package com.formvalidator.utils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.formvalidator.R;
import com.formvalidator.interfaces.SpinnerAndRadioButtonWatcher;
import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.RadioGroup.CustomRadioGroup;

/**
 * Created with IntelliJ IDEA. User: Aditya Agarwal Date: 9/17/12 Time: 12:21 PM
 *
 * Validates EditText, CustomRadioGroup or CustomRadioSpinner every time user makes a change. Calls a method
 * associated with each of the elements and based on which element was changed.
 */

public class RequiredFieldWatcher implements TextWatcher,
		SpinnerAndRadioButtonWatcher {

	private TextView mTextView = null;
	private Context mContext = null;
	private int mFormType = 0;
	private Drawable alertIcon = null;

	public RequiredFieldWatcher(View txtView, Context context) {
		mTextView = (TextView) txtView;
		mContext = context;
		setAlertIcon();
	}

    /**
     *
     * @param formType The int type of the form
     * @param txtView The text view associated with element that needs to show the required error tag
     * @param context
     */
	public RequiredFieldWatcher(int formType, View txtView, Context context) {
		mTextView = (TextView) txtView;
		mContext = context;
		mFormType = formType;
		setAlertIcon();
	}

	private void setAlertIcon() {
		alertIcon = mContext.getResources().getDrawable(R.drawable.alert_icon);
		alertIcon.setBounds(new Rect(0, 0, alertIcon.getIntrinsicWidth(),
				alertIcon.getIntrinsicHeight()));
	}

    /**
     * Checks whether the submit button has been clicked or not.
     * @return
     */
	private boolean isSubmitButtonClicked() {

		boolean submitButtonClicked = false;
		try {
			submitButtonClicked = SharedPrefUtils.getSubmitButtonStatus(
					mFormType, mContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitButtonClicked;
	}

	@Override
	public void afterTextChanged(Editable s) {
		markRequiredField(s.toString().length());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// Nothing to be done here

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// Nothing to be done here

	}

	private void markRequiredField(int strLen) {

		if (strLen > 0) {
			mTextView.setError(null, null);
		} else if (strLen <= 0) {

			if (isSubmitButtonClicked()) {
				String msg = mContext.getString(R.string.empty_string);
				if (mTextView instanceof EditText) {
					msg = mContext.getString(R.string.gen_required_msg);
				}
				mTextView.setError(msg, alertIcon);
			}
		}
	}

    /**
     * Validates whether or not an item has been selected from the spinner and marks the associated text view
     * accordingly.
     * @param customSpinner
     */
    @Override
	public void markRequiredFieldForSpinner(CustomSpinner customSpinner) {

		int position = customSpinner.getSelectedItemPosition();

		if (position == 0 && isSubmitButtonClicked()) {
			mTextView.setError("Required", alertIcon);
		} else if (position > 0) {
			mTextView.setError(null, null);
		}
	}

    /**
     * Validates whether or not a radio button has been selected and marks the associated text view
     * accordingly. Also validates the edit text associated with the radio button based on the tag set in the Edit text.
     * @param radioGroup
     */
	@Override
	public void markRequiredFieldForRadioButton(CustomRadioGroup radioGroup) {
		int id = radioGroup.getCheckedRadioButtonId();
        View commentView = null;

        //Get comment edit text view
        View view = (View)radioGroup.getParent();
        int radioGroupTableRowIndex = ((ViewGroup)view.getParent()).indexOfChild(view);
        if(radioGroupTableRowIndex >= 0){
            TableRow rowEditText = (TableRow)((TableLayout)view.getParent()).getChildAt(radioGroupTableRowIndex + 1);
            if(null != rowEditText){
                commentView = rowEditText.getChildAt(0);
            }
        }

        //Validation for the radio button
		if (id == -1 && isSubmitButtonClicked()) {
			mTextView.setError("Required", alertIcon);
		} else {
			mTextView.setError(null, null);
		}

        //Validation for Comment Edit text
        if (null != commentView && commentView instanceof EditText) {
            String tag = commentView.getTag().toString();

            if (id > 0 && null != tag) {
                int childIndex = Integer.parseInt(tag);
                if (id == radioGroup.getChildAt(childIndex).getId()) {
                    int len = ((EditText)commentView).length();
                    if (len == 0) {
                        ((EditText)commentView).setError(
                                "Required", alertIcon);
                    }
                }else {
                    ((EditText)commentView).setError(
                            null, null);
                }
            }
        }
	}
}
