package com.android.tvnet.key;

public class ConsKey {

    //key for save
    private static final String packageName ="com.android.tvnet" ;
    public static final String PREFS_NAME = packageName + "_DATA";
    public static final String USER_TOKEN = packageName + "USER_TOKEN";

//key for send Data Between Activity
public static final String TASK_DATA = packageName + "TASK_DATA";

    //key for bind cards
    public static final int TASK_CARD = 0;
    public static final int NO_INTERNET_CARD = 1;
}
