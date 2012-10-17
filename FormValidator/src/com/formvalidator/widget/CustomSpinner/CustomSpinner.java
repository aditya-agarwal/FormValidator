package com.formvalidator.widget.CustomSpinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

import com.formvalidator.utils.RequiredFieldWatcher;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 2:26 PM
 */
public class CustomSpinner extends Spinner {

    private RequiredFieldWatcher mWatcher;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, int mode) {
        super(context, mode);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle, mode);
    }

    public void setRequiredFieldWatcher(RequiredFieldWatcher watcher){
        mWatcher = watcher;
    }

    public void afterSpinnerItemSelected(){
        mWatcher.markRequiredFieldForSpinner(this);
    }
}
