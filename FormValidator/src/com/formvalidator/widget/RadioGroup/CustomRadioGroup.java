package com.formvalidator.widget.RadioGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import com.formvalidator.interfaces.SpinnerAndRadioButtonWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 3:08 PM
 */
public class CustomRadioGroup extends RadioGroup {

    private SpinnerAndRadioButtonWatcher mWatcher;

    public CustomRadioGroup(Context context) {
        super(context);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Register the Required Field watcher that will be called when an item is selected
     * @param watcher SpinnerAndRadioButtonWatcher
     */
    public void setRequiredFieldWatcher(SpinnerAndRadioButtonWatcher watcher){
        mWatcher = watcher;
    }

    /**
     * Called after a radio button item is selected from onCheckedChanged()
     */
    public void afterRadioButtonItemSelected(){
        mWatcher.markRequiredFieldForRadioButton(this);
    }
}
