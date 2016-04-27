package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.ByteBuffer;

public class char_mepower_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.measurepower);
        setContentView(R.layout.characteristic_measurepower);
        Button btEnter = (Button) findViewById(R.id.measurepower_enter);
        Button btCancel = (Button) findViewById(R.id.measurepower_cancel);
        final EditText etMesurepower = (EditText) findViewById(R.id.meaurepower_text);
        byte[] measurePower;
        final Intent intent = new Intent(char_mepower_Activity.this, DeviceControlActivity.class);

        measurePower = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        Byte b = new Byte(measurePower[0]);
        int i = b.intValue();
        String iString = String.valueOf(i);
        etMesurepower.setText(iString);

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] callbackbyte = new byte[1];
                // TODO: read data from TextView.
                String mePower = etMesurepower.getText().toString();
                byte mePowerByte;
                if (!mePower.isEmpty())
                    mePowerByte = Byte.parseByte(mePower);
                else
                    mePowerByte = 0;
                callbackbyte[0] = mePowerByte;
                intent.putExtra("measurepower_data", callbackbyte);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
