package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import com.futurumgame.base.enums.MetaData;

import java.io.Serializable;
import java.util.HashMap;

public class GoToViewListener implements View.OnClickListener {

    private final boolean needsToBeClosed;
    private final Class<? extends Activity> clazz;
    private final Activity current;
    private final HashMap<String, Object> map;

    public GoToViewListener(Class<? extends Activity> clazz, Activity current){
        this(clazz, current, false);
    }

    public GoToViewListener(Class<? extends Activity> clazz, Activity current, HashMap<String, Object> map) {
        this(clazz, current, false, map);
    }

    public GoToViewListener(Class<? extends Activity> clazz, Activity current, boolean needsToBeClosed){
        this(clazz, current, needsToBeClosed, null);
    }

    public GoToViewListener(Class<? extends Activity> clazz, Activity current, boolean needsToBeClosed, HashMap<String, Object> map) {
        this.needsToBeClosed = needsToBeClosed;
        this.clazz = clazz;
        this.current = current;
        this.map = map;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(current.getApplicationContext(), clazz);
        if(map != null) {
            MetaData.setAllMeta(map);
        }
        if(needsToBeClosed){
            current.finish();
        }
        current.startActivity(intent);
    }
}
