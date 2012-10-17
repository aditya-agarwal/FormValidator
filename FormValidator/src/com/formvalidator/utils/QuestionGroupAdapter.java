package com.formvalidator.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.formvalidator.R;

import java.util.ArrayList;

public class QuestionGroupAdapter extends ArrayAdapter<GroupInfo> {

	private ArrayList<GroupInfo> mQuestionList = null;
	private Context mContext = null;

	public QuestionGroupAdapter(Context context, ArrayList<GroupInfo> segList) {
		super(context, R.layout.list_view_group_layout, segList);
		mQuestionList = segList;
		mContext = context;
	}

	static class ViewHolder {
		public TextView questionTV;
		public TextView requiredTV;
		public int position;
	}

	@Override
	public int getCount() {
		return mQuestionList.size();
	}

	@Override
	public GroupInfo getItem(int position) {
		return mQuestionList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<GroupInfo> getList() {
		return mQuestionList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (null == rowView) {
			ViewHolder holder = new ViewHolder();
			rowView = ((LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.list_view_group_layout, null);
            holder.questionTV = (TextView) rowView
                    .findViewById(R.id.question_group_name);
            holder.requiredTV = (TextView) rowView
                    .findViewById(R.id.question_required_msg);
            rowView.setTag(holder);
			rowView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.position = position;

		holder.questionTV.setText(mQuestionList.get(position).getGroupName());

		if (!mQuestionList.get(position).isValid()) {
			holder.requiredTV.setVisibility(View.VISIBLE);
		} else {
			holder.requiredTV.setVisibility(View.INVISIBLE);
		}

		return rowView;
	}

}
