package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import com.futurumgame.base.enums.MetaData;

import java.io.Serializable;
import java.util.HashMap;

public class GoToViewListener extends SoundListener {

    private final Class<? extends Activity> clazz;
    private final Activity current;
    private final HashMap<String, Object> map;

    public GoToViewListener(Class<? extends Activity> clazz, Activity current){
        this(clazz, current, null);
    }

    public GoToViewListener(Class<? extends Activity> clazz, Activity current, HashMap<String, Object> map) {
        this.clazz = clazz;
        this.current = current;
        this.map = map;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(current.getApplicationContext(), clazz);
        if(map != null) {
            MetaData.setAllMeta(map);
        }
        current.startActivity(intent);
    }
}
