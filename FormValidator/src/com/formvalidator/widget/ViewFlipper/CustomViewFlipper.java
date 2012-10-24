package com.formvalidator.widget.ViewFlipper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.formvalidator.R;
import com.formvalidator.interfaces.BackKeyHandler;
import com.formvalidator.utils.GroupInfo;
import com.formvalidator.utils.QuestionGroupAdapter;
import com.formvalidator.utils.SharedPrefUtils;
import com.formvalidator.widget.CustomTableLayout.CustomTableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 10/18/12
 * Time: 9:58 PM
 *
 * View Flipper to handle validation of a group of views and show Required error tag in parent view
 */
public abstract class CustomViewFlipper extends ViewFlipper {

    public abstract String getTag();

    /**
     * Back button handler to be implemented by child views
     * @param callBack to call super
     */
    public abstract void onBackPressed(BackKeyHandler callBack);

    private static final String MARKER_START = ">>>>>>>>>>>>>>>>>>>>>>>";
    private static final String MARKER_END =   " <<<<<<<<<<<<<<<<<<<<<<";

    protected int mFormType = -1;
	protected Context mContext;


	// LIST OF INVALID VIEWS
	private List<Integer> mListOfInvalidViews = new ArrayList<Integer>();

	public CustomViewFlipper(Context context,
			int tabId, int formType) {
		super(context);
		mFormType = formType;
		mContext = context;
	}

	public CustomViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

    /**
     * Set the status of the submit button in the shared preferences
     * @param formType int id of the form type on which the submit button was clicked
     */
	public void setSubmitButtonClickedFromForm(int formType) {
		try {
			SharedPrefUtils.saveSubmitButtonStatus(formType, true, mContext);
		} catch (Exception ex) {
//			ErrorHandler.getInstance().reportError(ex,
//					ErrorHandler.UI_ERROR_CODE_SHARED_PREF_SAVE_FAIL);
		}
	}


    /**
     * Validate the groups or child views of the view flipper and set the error tags depending on the validity
     * @param listOfFormsToValidate
     * @param tableLayoutId
     * @param groupListView
     * @return whether or not all the fields in all the groups have been filled
     */
	protected boolean validateForms(List<Integer> listOfFormsToValidate,int tableLayoutId, ListView groupListView) {
		boolean result = true;
		mListOfInvalidViews.clear();
		for (int id : listOfFormsToValidate) {
			View view = findViewById(id);
			if (null != view) {
				CustomTableLayout tableLayout = (CustomTableLayout) view
						.findViewById(tableLayoutId);
				if (null != tableLayout) {
					boolean temp = tableLayout.validateForm();
					if (temp == false) {
						result = temp;
						mListOfInvalidViews.add(id);
					}
				}
			}
		}
		updateRequiredErrorTagInGroups(groupListView);
		return result;
	}

    /**
     * Show an alert dialog
     */
	public void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		String posBtn = getContext().getString(R.string.dialog_button_continue);
		builder.setMessage(R.string.group_list_required_field_alert);
		builder.setPositiveButton(posBtn,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

    /**
     * Toggle the "Required" tag for the groups based on the validity
     * @param listView View that holds the groups
     */
	protected void updateRequiredErrorTagInGroups(ListView listView) {
		if (listView.getAdapter() instanceof QuestionGroupAdapter) {
			QuestionGroupAdapter adapter = (QuestionGroupAdapter) listView
					.getAdapter();
			List<GroupInfo> groupList = adapter.getList();
			for (GroupInfo group : groupList) {
				int pos = groupList.indexOf(group);
				boolean validity = true;
				for (Integer id : mListOfInvalidViews) {
					View view = findViewById(id);
					if (null != view && null != view.getTag()) {
						int position = Integer.parseInt(view.getTag()
								.toString());
						if (pos == position) {
							validity = false;
							break;
						}
					}
				}
				groupList.get(pos).setValidity(validity);
			}
			adapter.notifyDataSetChanged();
		}
	}

    @Override
    public void setDisplayedChild(int position){
        Log.d(getTag(), MARKER_START + position + MARKER_END);
        super.setDisplayedChild(position);
    }

}
