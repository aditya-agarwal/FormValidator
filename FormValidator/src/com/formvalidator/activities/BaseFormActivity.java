package com.formvalidator.activities;

import android.app.Activity;
import android.os.Bundle;
import com.formvalidator.R;

public class BaseFormActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_type_2 );
    }
}
