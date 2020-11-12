package com.futurumgame.base.ui.listeners.ontouchlistener;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

public class OnTouchDismissListener implements View.OnTouchListener {

    private final PopupWindow popup;

    public OnTouchDismissListener(PopupWindow popup){
        this.popup = popup;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        popup.dismiss();
        return true;
    }
}
