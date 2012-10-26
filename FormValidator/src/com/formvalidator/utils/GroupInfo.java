package com.formvalidator.utils;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Aditya Agarwal
 * Date: 9/18/12
 * Time: 10:19 PM
 */
public class GroupInfo {

    private String mGroupName;
    private boolean mIsValid = true;

    private GroupInfo(String name, boolean isValid) {
        mGroupName = name;
        mIsValid = isValid;
    }

    /**
     * @return group name
     */
    public String getGroupName() {
        return mGroupName;
    }

    /**
     * @return whether all the fields in the group have been filled or not
     */
    public boolean isValid() {
        return mIsValid;
    }

    /**
     * @param status Group status: whether all the fields in the group have been filled or not
     */
    public void setValidity(boolean status) {
        mIsValid = status;
    }

    /**
     * @param groupName
     * @return initalized list of group names
     */
    public static ArrayList<GroupInfo> initListOfGroups(String[] groupName) {
        ArrayList<GroupInfo> list = new ArrayList<GroupInfo>();

        for (String name : groupName) {
            GroupInfo groupInfo = new GroupInfo(name, true);
            list.add(groupInfo);
        }
        return list;
    }
}
