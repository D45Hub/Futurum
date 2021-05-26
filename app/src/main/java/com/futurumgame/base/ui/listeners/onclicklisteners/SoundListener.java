package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.media.SoundPool;
import android.view.View;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.GameRoutine;

public abstract class SoundListener implements View.OnClickListener, SoundPool.OnLoadCompleteListener {

    private static final float MaxVolume = 1.0f;
    private static final float NormalPlayBackRate = 1.0f;

    private final SoundPool pool;

    protected SoundListener(){
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(20);
        pool = builder.build();
        pool.setOnLoadCompleteListener(this);
    }

    @Override
    public void onClick(View v) {
        pool.load(v.getContext(), R.raw.click, 1);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        if(status!= 0){
            return;
        }
        soundPool.play(sampleId, MaxVolume,MaxVolume, 1,0, NormalPlayBackRate);
    }
}
