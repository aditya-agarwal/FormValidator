package com.formvalidator.widget.RadioGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.formvalidator.utils.RequiredFieldWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 3:08 PM
 */
public class CustomRadioGroup extends RadioGroup {

    private RequiredFieldWatcher mWatcher;

    public CustomRadioGroup(Context context) {
        super(context);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRequiredFieldWatcher(RequiredFieldWatcher watcher){
        mWatcher = watcher;
    }

    public void afterRadioButtonItemSelected(){
        mWatcher.markRequiredFieldForRadioButton(this);
    }
}
