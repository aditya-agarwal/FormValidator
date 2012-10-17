package com.formvalidator;

import android.app.Activity;
import android.os.Bundle;

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
