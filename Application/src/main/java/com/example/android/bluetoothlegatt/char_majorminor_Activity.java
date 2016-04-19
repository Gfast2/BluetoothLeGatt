package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by sugao on 16/4/19.
 */

public class char_majorminor_Activity extends Activity{

    String majorMinor = null; // the old value passed from the last View.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_majorminor); // setup the view.
//        majorMinor = getIntent().getStringExtra(intent, MAJORMINOR_EXTRA); // That's how I can get info from the activity that call this activiy.
    }
}
