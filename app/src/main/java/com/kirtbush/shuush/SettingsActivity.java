package com.kirtbush.shuush;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ResetSeekBarValues();

        SeekBar fullVolumeSlider = (SeekBar)findViewById(R.id.full_volume_percent_seekbar);
        fullVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Toast myToast = new Toast(getApplicationContext());
//                myToast.makeText(getApplicationContext(), "meep", Toast.LENGTH_LONG);
                SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
                editor.putInt(getString(R.string.full_volume), progress);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();

            }
        });

        SeekBar lowVolumeSlider = (SeekBar)findViewById(R.id.low_volume_percent_seekbar);
        lowVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
                editor.putInt(getString(R.string.low_volume), progress);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();

            }
        });

    }

    private void ResetSeekBarValues()
    {
        SeekBar fullVolumeSlider = (SeekBar)findViewById(R.id.full_volume_percent_seekbar);
        if(!getPreferences(Context.MODE_PRIVATE).contains(getString(R.string.full_volume))) {
            int progress = getPreferences(Context.MODE_PRIVATE).getInt(
                    getString(R.string.full_volume),
                    getResources().getInteger(R.integer.full_volume_default_int));
            fullVolumeSlider.setProgress(progress);
        }

           SeekBar lowVolumeSlider = (SeekBar)findViewById(R.id.low_volume_percent_seekbar);

        if(!getPreferences(Context.MODE_PRIVATE).contains(getString(R.string.low_volume))) {
            int progress = getPreferences(Context.MODE_PRIVATE).getInt(
                    getString(R.string.low_volume),
                    getResources().getInteger(R.integer.low_volume_default_int));
            lowVolumeSlider.setProgress(progress);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast myToast = new Toast(getApplicationContext());
        myToast.makeText(getApplicationContext(), "settings activity resumed", Toast.LENGTH_LONG);

        ResetSeekBarValues();
    }
}
