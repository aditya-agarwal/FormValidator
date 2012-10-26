package com.formvalidator.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtils {

    private static String pref_string = "com_formvalidator_preferences";

    public static void saveSubmitButtonStatus(int formType, boolean state, Context context) throws Exception {
        SharedPreferences.Editor prefEdit = context.getSharedPreferences(pref_string, 0).edit();
        prefEdit.putBoolean(Integer.toString(formType), state);
        prefEdit.commit();
    }

    public static Boolean getSubmitButtonStatus(int formType, Context context) throws Exception {
        SharedPreferences pref = context.getSharedPreferences(pref_string, 0);
        return pref.getBoolean(Integer.toString(formType), false);
    }

}
