FormValidator
=============

CustomTableLayout 
=================
Use this table layout for a form which requires validation

Usage
=====	
See single_form.xml in layout folder for usage

Adding CustomTableLayout in xml:

	<com.formvalidator.widget.CustomTableLayout.CustomTableLayout
                xmlns:mrp="http://schemas.android.com/apk/res/com.formvalidator"
                android:id="@+id/form_1_table_layout"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:collapseColumns="true"
                mrp:formType="203">

- In xml file add attribute integer formType for table layout and define formType for each type of forms.
- For each table row add tag android:tag="is_required" or android:tag="not_required" based on 
whether the elements in the table row need to be validated or not.
- Call method validateForm() for the table layout that needs to be validated
which returns boolean:

	CustomTableLayout tableLayout = (CustomTableLayout) findViewById(R.id.form_1_table_layout);
        tableLayout.validateForm();
        
CustomViewFlipper
================
View Flipper to handle validation of a group of views and show Required error tag in parent view.

Usage
=====
See group_form.xml in layout folder and DuringDayForm.java for usage
- Call setSubmitButtonClickedFromForm(int formType) from Form class to save the status of submit button
in shared preference. This is done so that when user moves away from the form after clicking submit button
and comes back again to the form it will see the required error tags.
- Call validateForms(List<Integer> listOfFormsToValidate,int tableLayoutId, ListView groupListView)
passing List of Resource id of the forms to validate along with the custom table layout id and the list view
which holds all the forms.
NOTE: In this case all custom table layouts should have the same id
 

CustomSpinner
============
 Spinner which calls afterSpinnerItemSelected() in SpinnerAndRadioButtonWatcher after an item is selected. Use this spinner in xml for it be validated and update its associated text view.
 For usage use this spinner in you layout .xml file and for Validation, in onItemSelected do:
 	
 	@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView instanceof CustomSpinner) {
            ((CustomSpinner) adapterView).afterSpinnerItemSelected();
        }
    }


CustomRadioGroup
===============
 RadioGroup which calls afterRadioButtonItemSelected() SpinnerAndRadioButtonWatcher after an item is selected. Use this radio group in xml for it be validated and update its associated text view.
 For usage use this radiogroup in your layout and for Validation, in onCheckChanged of RadioButton do:
 
 	@Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup instanceof CustomRadioGroup) {
            ((CustomRadioGroup) radioGroup).afterRadioButtonItemSelected();
        }
    }
 
RequiredFieldWatcher
====================
Validates EditText, CustomRadioGroup or CustomRadioSpinner every time user makes a change. Calls a method associated with 
each of the elements and based on which element was changed.

SpinnerAndRadioButtonWatcher
===========================
Interface definitions for callbacks for RadioGroup and Spinner to be invoked after item is selected/checked.

BackKeyHandler
=============
Interface definition for callback to be invoked when forms in view flipper group is done handling back button
Implement this interface in activity creating the view flipper
Usage
====
See onBackPresses() in GroupFormActivity.java
