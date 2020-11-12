package com.futurumgame.base.ui.popups;

import android.view.LayoutInflater;

import com.futurumgame.base.enums.UpgradeResult;

public class UpgradeResultPopup  extends TextPopup {

    public UpgradeResultPopup(LayoutInflater inflater, UpgradeResult result){
        super(result.getMessage(), inflater);
    }


}
