package com.example.android.bluetoothlegatt;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class char_name_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("iBeacon Name");
        setContentView(R.layout.characteristic_name);
        Button btEnter = (Button) findViewById(R.id.name_enter);
        Button btCancel = (Button) findViewById(R.id.name_cancel);
        final EditText txName = (EditText) findViewById(R.id.name);

        btEnter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO: Add event to read and write Name of the device.
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View v)
           {
               finish();
           }
        });
    }



}
