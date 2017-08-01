package com.kirtbush.shuush;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);

        ResetSeekBarValues();

        SeekBar fullVolumeSlider = (SeekBar) findViewById(R.id.full_volume_percent_seekbar);
        fullVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Toast myToast = new Toast(getApplicationContext());
//                myToast.makeText(getApplicationContext(), "meep", Toast.LENGTH_LONG);
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).edit();
                editor.putInt(getString(R.string.full_volume), progress);
                editor.apply();
                TextView displayText = (TextView)findViewById(R.id.full_volume_value_textview);
                displayText.setText(Integer.toString(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.d("fullvolumeprogress", Integer.toString(progress));

            }
        });

        SeekBar lowVolumeSlider = (SeekBar) findViewById(R.id.low_volume_percent_seekbar);
        lowVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).edit();
                editor.putInt(getString(R.string.low_volume), progress);
                editor.apply();
                TextView displayText = (TextView)findViewById(R.id.low_volume_value_textview);
                displayText.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.d("lowvolumeprogress", Integer.toString(progress));
            }
        });

    }

    private void ResetSeekBarValues() {
        int progress = 0;
        SeekBar fullVolumeSlider = (SeekBar) findViewById(R.id.full_volume_percent_seekbar);
        if (!getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).contains(getString(R.string.full_volume))) {
            progress = getResources().getInteger(R.integer.full_volume_default_int);
        } else {
            progress = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).getInt(
                    getString(R.string.full_volume),
                    getResources().getInteger(R.integer.full_volume_default_int));
        }

        fullVolumeSlider.setProgress(progress);
        TextView displayText = (TextView)findViewById(R.id.full_volume_value_textview);
        displayText.setText(Integer.toString(progress));

        SeekBar lowVolumeSlider = (SeekBar) findViewById(R.id.low_volume_percent_seekbar);

        if (!getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).contains(getString(R.string.low_volume))) {
            progress = getResources().getInteger(R.integer.low_volume_default_int);
        } else {
            progress = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).getInt(
                    getString(R.string.low_volume),
                    getResources().getInteger(R.integer.low_volume_default_int));
        }

        lowVolumeSlider.setProgress(progress);
        displayText = (TextView)findViewById(R.id.low_volume_value_textview);
        displayText.setText(Integer.toString(progress));

    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "settings activity resumed", Toast.LENGTH_LONG);

        ResetSeekBarValues();
    }
}
