package com.formvalidator.widget.CustomTableLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;
import com.formvalidator.R;
import com.formvalidator.utils.RequiredFieldWatcher;
import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.CustomRadioGroup.CustomRadioGroup;

/**
 * Created with IntelliJ IDEA. User: Aditya Agarwal Date: 9/17/12 Time: 12:21 PM
 * <p/>
 * Table layout which checks its fields/elements whether they have been filled/selected or not.
 * <p/>
 * -In xml file add attribute integer formType for the table layout and define formType for each type of forms.
 * -For each table row add tag android:tag="is_required" or android:tag="not_required" based on whether the elements
 * in the table row need to be validated or not.
 */

public class CustomTableLayout extends TableLayout {

    private int mFormType = 0;
    private Drawable alertIcon = null;

    public CustomTableLayout(Context context) {
        super(context);
    }

    public CustomTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormType);
        mFormType = Integer.parseInt(a.getString(R.styleable.FormType_formType));
        alertIcon = getResources().getDrawable(R.drawable.alert_icon);

        if (getTag() != null) {
            mFormType = Integer.parseInt(getTag().toString());
        }
    }

    /**
     * @return validity of the table layout - whether all fields tagged in the layout have been filled or not
     */
    public boolean validateForm() {

        boolean result = true;

        alertIcon.setBounds(new Rect(0, 0, alertIcon.getIntrinsicWidth(), alertIcon.getIntrinsicHeight()));

        TextView txtView = null;

        for (int i = 0; i < getChildCount(); i++) {

            TableRow row = (TableRow) getChildAt(i);

            if (null != row.getTag() && row.getTag().equals(getResources().getString(R.string.gen_tag_required))) {

                //Ignore this counter
                Object counterTag = row.getTag(0);
                int machineCount = 0;
                View answerView = row.getChildAt(1);

                if (row.getChildCount() == 1) {
                    answerView = row.getChildAt(0);
                }

                if (row.getChildAt(0) instanceof TextView) {
                    txtView = (TextView) row.getChildAt(0);
                }

                // validation for voting machine
                if (null != counterTag) {
                    machineCount = (Integer) counterTag;
                    if (machineCount <= 0) {
                        txtView.setError("Required", alertIcon);
                        result = false;
                    } else {
                        txtView.setError(null, null);
                    }
                    continue;
                }

                if (answerView instanceof EditText) {

                    if (null == answerView.getTag()) {
                        int len = ((EditText) answerView).getText().length();
                        if (len == 0) {
                            txtView.setError("Required", alertIcon);
                            result = false;
                            continue;
                        }
                    }
                    // Check which radio button has been selected and based on the tag set in the xml check if edit text
                    // is required or not
                    else {
                        View view = getChildAt(i - 1);

                        if (view instanceof TableRow) {
                            View previousAnswerView = ((TableRow) view).getChildAt(1);

                            if (previousAnswerView instanceof RadioGroup) {
                                String tag = answerView.getTag().toString();
                                int id = ((RadioGroup) previousAnswerView).getCheckedRadioButtonId();

                                if (id > 0 && null != tag) {
                                    int childIndex = Integer.parseInt(tag);
                                    if (id == ((RadioGroup) previousAnswerView).getChildAt(childIndex).getId()) {
                                        int len = ((EditText) answerView).getText().length();
                                        if (len == 0) {
                                            ((EditText) answerView).setError("Required", alertIcon);
                                            result = false;
                                            continue;
                                        }
                                    }
                                }
                            } else if (previousAnswerView instanceof EditText) {
                                int lenOfPreviousEditText = ((EditText) previousAnswerView).getText().length();

                                if (lenOfPreviousEditText > 0) {
                                    int len = ((EditText) answerView).getText().length();
                                    if (len == 0) {
                                        ((EditText) answerView).setError("Required", alertIcon);
                                        result = false;
                                        continue;
                                    }
                                }
                            }
                        }
                    }

                }
                if (answerView instanceof RadioGroup) {
                    int id = ((RadioGroup) answerView).getCheckedRadioButtonId();
                    if (id == -1) {
                        txtView.setError("Required", alertIcon);
                        result = false;
                        continue;
                    }
                }
                if (answerView instanceof Spinner) {
                    int position = ((Spinner) answerView).getSelectedItemPosition();
                    if (position == 0) {
                        txtView.setError("Reqiured", alertIcon);
                        result = false;
                        continue;
                    }
                }
                if (answerView instanceof CheckBox) {
                    boolean isChecked = ((CheckBox) answerView).isChecked();

                    if (!isChecked) {
                        ((CheckBox) answerView).setError("Required", alertIcon);
                        result = false;
                        continue;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void onFinishInflate() {
        setRequiredTextWatcher();
    }

    /**
     * Set the required text watcher listener for
     * - Simple Edit text
     * - Comment Edit text associated with a radio button
     * - Custom Spinner
     * - Custom Radio Group
     */
    private void setRequiredTextWatcher() {

        for (int i = 0; i < getChildCount(); i++) {
            TableRow row = (TableRow) getChildAt(i);

            if (row.getChildCount() > 1) {
                View view = row.getChildAt(1);

                if (view instanceof EditText) {
                    ((EditText) view).addTextChangedListener(new RequiredFieldWatcher(mFormType, row.getChildAt(0),
                            getContext()));
                }
                if (view instanceof CustomSpinner) {
                    ((CustomSpinner) view).setRequiredFieldWatcher(new RequiredFieldWatcher(mFormType,
                            row.getChildAt(0), getContext()));
                }
                if (view instanceof CustomRadioGroup) {
                    ((CustomRadioGroup) view).setRequiredFieldWatcher(new RequiredFieldWatcher(mFormType,
                            row.getChildAt(0), getContext()));
                }
            }

            //Setting Listener for Comment Edit text in Table Layout
            else if (row.getChildCount() == 1) {
                View view = row.getChildAt(0);
                if (view instanceof EditText) {
                    ((EditText) view).addTextChangedListener(new RequiredFieldWatcher(mFormType, row.getChildAt(0), getContext()));
                }
            }
        }
    }
}