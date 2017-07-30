package com.kirtbush.shuush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.VolumeProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        if(!sharedPref.contains(getString(R.string.full_volume)))
        {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.full_volume), getResources().getInteger(R.integer.full_volume_default_int));
            editor.commit();
        }

        if(!sharedPref.contains(getString(R.string.low_volume)))
        {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.low_volume), getResources().getInteger(R.integer.low_volume_default_int));
            editor.commit();
        }

        final Button fullButton = (Button)findViewById(R.id.full_volume_button);
        fullButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Snackbar.make(findViewById(R.id.main_layout), "TROLO", Snackbar.LENGTH_SHORT).show();
                AudioManager audioManager =
                        (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        getPreferences(Context.MODE_PRIVATE).getInt(getString(R.string.full_volume), 0),
                        0);

            }
        });

        final Button lowButton = (Button)findViewById(R.id.low_volume_button);
        lowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Snackbar.make(findViewById(R.id.main_layout), "SHOWLOLOLO", Snackbar.LENGTH_SHORT).show();
                AudioManager audioManager =
                        (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        getPreferences(Context.MODE_PRIVATE).getInt(getString(R.string.low_volume), 0),
                        0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(findViewById(R.id.main_layout), "STARTING SETTINGS", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
