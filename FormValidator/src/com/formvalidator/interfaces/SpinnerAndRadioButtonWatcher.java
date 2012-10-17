package com.formvalidator.interfaces;

import com.formvalidator.widget.CustomSpinner.CustomSpinner;
import com.formvalidator.widget.RadioGroup.CustomRadioGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 2:37 PM
 */
public interface SpinnerAndRadioButtonWatcher {

    public void markRequiredFieldForSpinner(CustomSpinner customSpinner);

    public void markRequiredFieldForRadioButton(CustomRadioGroup radioGroup);

}
