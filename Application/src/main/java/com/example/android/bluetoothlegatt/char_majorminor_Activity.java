package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.ByteBuffer;

/**
 * Created by sugao on 16/4/19.
 */

public class char_majorminor_Activity extends Activity {

    private final static String TAG = char_majorminor_Activity.class.getSimpleName();
    byte[] majorMinorByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.majorMinor);
        setContentView(R.layout.characteristic_majorminor); // setup the view.

        // Uncomment to read String format data from Intent.
//        String majorMinor = getIntent().getStringExtra(DeviceControlActivity.EXTRA_READ);
        majorMinorByte = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        final Button btEnter = (Button) findViewById(R.id.measurepower_enter);
        final Button btCancel = (Button) findViewById(R.id.measurepower_cancel);
        final EditText etMajor = (EditText) findViewById(R.id.majorInput);
        final EditText etMinor = (EditText) findViewById(R.id.minorInput);
        final String EXTRA_DATA = "com.example.majorminoractivity.EXTRA_DATA";
        // intent to send back infomation.
        final Intent intent = new Intent(char_majorminor_Activity.this, DeviceControlActivity.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String major, minor;
                // Parse data from byte[] and show the integer format on screen
                ByteBuffer wrapped;
                short num;
                wrapped = ByteBuffer.wrap(majorMinorByte, 0, 2);
                num = wrapped.getShort();
                major = String.valueOf(num);
                wrapped = ByteBuffer.wrap(majorMinorByte, 2, 2);
                num = wrapped.getShort();
                minor = String.valueOf(num);

                // Bad practice, should prevented.
                etMajor.setText(major, TextView.BufferType.EDITABLE);
                etMinor.setText(minor, TextView.BufferType.EDITABLE);
            }
        }).start();

        btEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ma = etMajor.getText().toString();
                String mi = etMinor.getText().toString();
                final int major, minor;
                if (!ma.isEmpty()) {
                    major = Integer.parseInt(ma);
                } else major = 0;
                if (!mi.isEmpty()) minor = Integer.parseInt(mi);
                else minor = 0;
//                final int majorminor = (major * 65536) + minor; // default java int is 4 bytes.
                byte[] majorminor;
                byte[] tmp1 = intToByteArray(major);
                byte[] tmp2 = intToByteArray(minor);
                majorminor = new byte[tmp1.length + tmp2.length];
                for (int i = 0; i < majorminor.length; i++) {
                    majorminor[i] = i < tmp1.length ? tmp1[i] : tmp2[i - tmp1.length];
                }
                Log.d(TAG, "majorminor value:" + majorminor);
                intent.putExtra("majorminor_data", majorminor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    // Divide a integer into two byte. for major/minor calculation.
    public static byte[] intToByteArray(int a) {
        byte[] ret = new byte[2];
        ret[1] = (byte) (a & 0xFF);
        ret[0] = (byte) ((a >> 8) & 0xFF);
        return ret;
    }


}
