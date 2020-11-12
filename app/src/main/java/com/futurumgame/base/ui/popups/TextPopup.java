package com.futurumgame.base.ui.popups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.futurumgame.base.R;

public class TextPopup extends BasePopup {


    public TextPopup(String text, LayoutInflater inflater) {
        super(inflater, R.layout.popup_layout);
        init(text);
    }

    public TextPopup(String text, View view, int width, int height, boolean focusable) {
        super(view, width, height, focusable);
        init(text);
    }

    private void init(String text) {
        TextView view = getContentView().findViewById(R.id.Text);
        view.setText(text);
    }
}
