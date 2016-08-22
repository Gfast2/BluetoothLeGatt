package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class char_name_Activity extends Activity {

    private final static String TAG = char_name_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("iBeacon Name");
        setContentView(R.layout.characteristic_name);
        Button btEnter = (Button) findViewById(R.id.name_enter);
        Button btCancel = (Button) findViewById(R.id.name_cancel);
        final EditText txName = (EditText) findViewById(R.id.name);

        byte[] deviceID = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        // Intent to send back the info.
        final Intent intent = new Intent(char_name_Activity.this, DeviceControlActivity.class);

        String deviceName = "Device name";

        if (deviceID != null && deviceID.length > 0)
        {
            final StringBuilder stringBuilder = new StringBuilder(deviceID.length);
            for (byte chars : deviceID)
            {
                // TODO: Here should parse the info to display with other format...
                // The format now is Hex, to display is ASCII
//                stringBuilder.append(String.format("%02X", chars));
                stringBuilder.append(Character.toString((char) chars));
            }

            // filter out white spaces. (Optional)
            int j = 0;
            for (int i = 0; i < stringBuilder.length(); i++) {
                if (!Character.isWhitespace(stringBuilder.charAt(i)))
                    stringBuilder.setCharAt(j++, stringBuilder.charAt(i));
            }
            stringBuilder.delete(j, stringBuilder.length());

            deviceName = stringBuilder.toString();
        }

        // Till here display the name of the thing.
        txName.setText(deviceName, TextView.BufferType.EDITABLE);



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
