FormValidator
=============

CustomTableLayout 
=================
Use this table layout for a form which requires validation

Usage
=====	
See form_type_1 for usage
- In xml file add attribute integer formType for table layout and define formType for each type of forms.
- For each table row add tag android:tag="is_required" or android:tag="not_required" based on 
whether the elements in the table row need to be validated or not.
- Call method validateForm() for the table layout that needs to be validated
which returns boolean.

CustomViewFlipper
================
View Flipper to handle validation of a group of views and show Required error tag in parent view.

Usage
=====
See form_type_2 and DuringDayForm.java for usage
- Call setSubmitButtonClickedFromForm(int formType) from Form class to save the status of submit button
in shared preference. This is done so that when moves away from the form after clicking submit button
and comes back again to the form it will see the required error tags.
- Call validateForms(List<Integer> listOfFormsToValidate,int tableLayoutId, ListView groupListView)
passing List of Resource id of the forms to validate along with the custom table layout id and the list view
which holds all the forms.
NOTE: In this case all custom table layouts should have the same id
 

CustomSpinner
============
 Spinner which calls SpinnerAndRadioButtonWatcher after an item is selected. Use this spinner in xml for it be validated and update its associated text view

CustomRadioGroup
===============
 RadioGroup which calls SpinnerAndRadioButtonWatcher after an item is selected. Use this radio group in xml for it be validated and update its associated text view
 
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
