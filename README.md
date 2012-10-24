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
- Call method validateForm() on the view for validation which returns boolean.

CustomViewFlipper
================
 View Flipper to handle validation of a group of views and show Required error tag in parent view.

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
Interface definitions for callbacks for RadioGroup and Spinner to be invoked when item is selected/checked.

BackKeyHandler
=============
Interface definition for callback to be invoked when user is done handling back button
