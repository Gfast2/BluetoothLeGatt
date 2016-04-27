package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class char_txpower_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_txpower);

        Button btEnter = (Button) findViewById(R.id.txpower_enter);
        Button btCancel = (Button) findViewById(R.id.txpower_cancel);
        final RadioGroup rgTxpower = (RadioGroup) findViewById(R.id.txpower_radioGroup);
        final Intent intent = new Intent(char_txpower_Activity.this, DeviceControlActivity.class);
        byte[] txpwByte = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        switch (txpwByte[0]) {
            case 0x00:
                rgTxpower.check(R.id.txpower_low);
                break;
            case 0x01:
                rgTxpower.check(R.id.txpower_middle);
                break;
            case 0x02:
                rgTxpower.check(R.id.txpower_high);
                break;
        }

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int advertSelected = rgTxpower.getCheckedRadioButtonId();
                View radioButton = rgTxpower.findViewById(advertSelected);
                int idx = rgTxpower.indexOfChild(radioButton); // Index: 0 - 2
                byte[] callbackByte = new byte[1];
                switch (idx) {
                    case 0:
                        callbackByte[0] = 0;
                        break;
                    case 1:
                        callbackByte[0] = 1;
                        break;
                    case 2:
                        callbackByte[0] = 2;
                        break;
                }
                intent.putExtra("txpower_data", callbackByte);
                setResult(RESULT_OK, intent);
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
