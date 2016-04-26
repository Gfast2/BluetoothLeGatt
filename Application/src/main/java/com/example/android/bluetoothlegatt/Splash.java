package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // setup the view.
        ImageView imgView = (ImageView) findViewById(R.id.Splash_imageView);
        imgView.setImageResource(R.drawable.splash);
    }
}
