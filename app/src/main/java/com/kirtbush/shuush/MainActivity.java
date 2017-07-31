package com.kirtbush.shuush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.widget.ImageButton;


public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_key), Context.MODE_PRIVATE);

        if (!sharedPref.contains(getString(R.string.full_volume))) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.full_volume), getResources().getInteger(R.integer.full_volume_default_int));
            editor.apply();
        }

        if (!sharedPref.contains(getString(R.string.low_volume))) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.low_volume), getResources().getInteger(R.integer.low_volume_default_int));
            editor.apply();
        }

        final ImageButton fullButton = (ImageButton) findViewById(R.id.full_volume_button);
        fullButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateFullVolume();
            }
        });

        final ImageButton lowButton = (ImageButton) findViewById(R.id.low_volume_button);
        lowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateLowVolume();
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
