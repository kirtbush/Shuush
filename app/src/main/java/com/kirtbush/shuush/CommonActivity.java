package com.kirtbush.shuush;

import android.content.Context;
import android.media.AudioManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.media.*;
/**
 * Created by kirt on 7/30/2017.
 */

public class CommonActivity extends AppCompatActivity {
    public void UpdateFullVolume()
    {
        // Code here executes on main thread after user presses button
        Snackbar.make(findViewById(R.id.main_layout), "TROLO", Snackbar.LENGTH_SHORT).show();

        Double defaultVolumeLevel = (double) getResources().getInteger(R.integer.full_volume_default_int);
        double volumeLevel = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).getInt(getString(R.string.full_volume), 0);

        UpdateVolume(volumeLevel, defaultVolumeLevel);
    }

    public void UpdateVolume(double volumeLevel, Double defaultVolumeLevel) {
        AudioManager audioManager =
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Log.d("DefaultVolumeLevel", defaultVolumeLevel.toString());
        Log.d("VolumeLevel", String.format("%f", volumeLevel));

        Log.d("VOICE STREAM_VOICE_CALL", Integer.toString(audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL)));
        Log.d("VOICE STREAM_SYSTEM", Integer.toString(audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM)));
        Log.d("VOICE STREAM_RING", Integer.toString(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)));
        Log.d("VOICE STREAM_ALARM", Integer.toString(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)));
        Log.d("VOICE STREAM_MUSIC", Integer.toString(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)));

        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                (int) ((volumeLevel / 100.0) * audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL)),
                AudioManager.FLAG_SHOW_UI);

        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,
                (int) ((volumeLevel / 100.0) * audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM)),
                AudioManager.FLAG_SHOW_UI);

        audioManager.setStreamVolume(AudioManager.STREAM_RING,
                (int) ((volumeLevel / 100.0) * audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)),
                AudioManager.FLAG_SHOW_UI);

        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                (int) ((volumeLevel / 100.0) * audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)),
                AudioManager.FLAG_SHOW_UI);

        //TODO: potentially replace with new preference
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                0,
                AudioManager.FLAG_SHOW_UI);

    }

    public void UpdateLowVolume() {
        // Code here executes on main thread after user presses button
        Snackbar.make(findViewById(R.id.main_layout), "SHOWLOLOLO", Snackbar.LENGTH_SHORT).show();
        AudioManager audioManager =
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        double defaultVolumeLevel = getResources().getInteger(R.integer.low_volume_default_int);
        Double volumeLevel = (double)getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE).getInt(getString(R.string.low_volume), 0);

        UpdateVolume(volumeLevel, defaultVolumeLevel);
    }

}
