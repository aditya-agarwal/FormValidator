package com.formvalidator.interfaces;

import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.CustomRadioGroup.CustomRadioGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 2:37 PM
 *
 * Interface definitions for callbacks for RadioGroup and Spinner to be invoked when item is selected/checked
 */
public interface SpinnerAndRadioButtonWatcher {

    public void markRequiredFieldForSpinner(CustomSpinner customSpinner);

    public void markRequiredFieldForRadioButton(CustomRadioGroup radioGroup);

}
