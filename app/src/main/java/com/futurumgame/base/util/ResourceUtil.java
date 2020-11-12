package com.futurumgame.base.util;

import android.content.Context;

import androidx.annotation.StringRes;

public class ResourceUtil {

    private ResourceUtil(){
    }

    public static String getText(Context context, @StringRes int id){
        return context.getString(id);
    }
}
