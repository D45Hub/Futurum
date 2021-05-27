package com.futurumgame.base.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.HardResetListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.SoundListener;

public class OptionsViewActivity extends UpdatableViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameRoutine.setNewCurrent(this);
        allowUpdates();

        setContentView(R.layout.options_view);

        ImageButton soundButton = findViewById(R.id.soundButton);
        updateSoundButton(soundButton);

        ImageButton returnButton = findViewById(R.id.optionsReturnButton);
        returnButton.setOnClickListener(GoBackListener.newListener());

        Button hardResetButton = findViewById(R.id.HardReset);
        hardResetButton.setOnClickListener(HardResetListener.newListener(this));

        soundButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SoundListener.toggleSoundMute();
                updateSoundButton(soundButton);
            }
        });
    }

    private void updateSoundButton(ImageButton soundButton) {

        if (SoundListener.muted)
        {
            soundButton.setBackgroundResource(R.drawable.sound_off_button);
        }
        else
        {
            soundButton.setBackgroundResource(R.drawable.sound_on_button);
        }
    }

    @Override
    public void updateUi(WareHouse wareHouse) {

    }
}