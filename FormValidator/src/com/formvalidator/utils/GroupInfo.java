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

    private GroupInfo(String name, boolean isValid){
        mGroupName = name;
        mIsValid = isValid;
    }


    public String getGroupName() {
        return mGroupName;
    }

    public boolean isValid() {
        return mIsValid;
    }

    public void setValidity(boolean status){
        mIsValid = status;
    }


    public static ArrayList<GroupInfo> initList(String [] groupName){
        ArrayList<GroupInfo> list = new ArrayList<GroupInfo>();

        for(String name : groupName){
            GroupInfo groupInfo = new GroupInfo(name, true);
            list.add(groupInfo);
        }
        return list;
    }
}
