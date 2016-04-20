package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.UUID;

/**
 * Created by sugao on 16/4/19.
 */

public class char_majorminor_Activity extends Activity{

    private final static String TAG = char_majorminor_Activity.class.getSimpleName();
    String majorMinor = null; // the old value passed from the last View.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_majorminor); // setup the view.

        majorMinor = getIntent().getStringExtra(BluetoothLeService.EXTRA_DATA); // That's how I can get info from the activity that call this activiy. Correctely!!
        final Button btEnter = (Button) findViewById(R.id.majorminor_enter);
        final Button btCancel = (Button) findViewById(R.id.majorminor_cancel);
        final EditText etMajor = (EditText) findViewById(R.id.majorInput);
        final EditText etMinor = (EditText) findViewById(R.id.minorInput);
        final String EXTRA_DATA = "com.example.majorminoractivity.EXTRA_DATA";
        // intent to send back infomation.
        final Intent intent = new Intent(char_majorminor_Activity.this, DeviceControlActivity.class);

        Log.d(TAG, "receive Intent data (majorMinor): " + majorMinor);
        // TODO: Parse the gotten value.
        // TODO: Display the parsed value in the input field
        // TODO: build button functions, don't change anything, jump back to the last activity.
        btEnter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                String ma = etMajor.getText().toString();
                String mi = etMinor.getText().toString();
                final int major,minor;
                if(!ma.isEmpty()){major = Integer.parseInt(ma);}
                else major = 0;
                if(!mi.isEmpty()) minor = Integer.parseInt(mi);
                else minor = 0;
//                final int majorminor = (major * 65536) + minor; // default java int is 4 bytes.
                byte[] majorminor = null;
                byte[] tmp1 = intToByteArray(major);
                byte[] tmp2 = intToByteArray(minor);
                majorminor = new byte[tmp1.length + tmp2.length];
                for(int i=0; i<majorminor.length; i++){
                    majorminor[i] = i<tmp1.length ?  tmp1[i] : tmp2[i-tmp1.length];
                }

                Log.d(TAG, "majorminor value:" + majorminor);
                intent.putExtra("majorminor_data", majorminor);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }

    // Divide a integer into two byte. for major/minor calculation.
    public static byte[] intToByteArray(int a){
        byte [] ret = new byte[2];
        ret[1] = (byte)(a & 0xFF);
        ret[0] = (byte)((a >> 8) & 0xFF);
        return ret;
    }
}
