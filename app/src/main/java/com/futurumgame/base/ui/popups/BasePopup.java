package com.futurumgame.base.ui.popups;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;

import com.futurumgame.base.ui.listeners.ontouchlistener.OnTouchDismissListener;

public abstract class BasePopup extends PopupWindow {

    protected BasePopup(LayoutInflater inflater, @LayoutRes int resource) {
        this(inflater.inflate(resource, null), LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
    }

    protected BasePopup(View view, int width, int height, boolean focusable) {
        setContentView(view);
        setWidth(width);
        setHeight(height);
        setFocusable(focusable);
        init();
    }

    private void init() {
        showAtLocation(getContentView(), Gravity.CENTER, 0, 0);
        getContentView().setOnTouchListener(new OnTouchDismissListener(this));
    }
}
