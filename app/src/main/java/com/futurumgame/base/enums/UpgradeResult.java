package com.futurumgame.base.enums;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.futurumgame.base.ui.popups.UpgradeResultPopup;

public enum UpgradeResult {

    Successful("Upgrade was successful"), Failure("Upgrade could not be completed, required resources are missing");

    private final String message;

    private UpgradeResult(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void createPopup(Context context) {
        new UpgradeResultPopup(LayoutInflater.from(context),this);
    }
}
