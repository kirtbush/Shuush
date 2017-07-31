package com.kirtbush.shuush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.VolumeProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.widget.ImageButton;

/**
 * Created by kirt on 7/30/2017.
 */

public class ShuushFullActivity extends CommonActivity {
    public ShuushFullActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateFullVolume();
    }
}
