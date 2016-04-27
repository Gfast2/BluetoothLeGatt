package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RadioGroup;

public class char_deploy_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_deploymode);
        final Intent intent = new Intent(char_deploy_Activity.this, DeviceControlActivity.class);
        Button btEnter = (Button) findViewById(R.id.deploy_enter);
        Button btCancel = (Button) findViewById(R.id.deploy_cancel);
        final RadioGroup rgDeploy = (RadioGroup) findViewById(R.id.deploy_radioGroup);
        byte[] deployByte = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        if (deployByte[0] == 0x00) { // deploy mode deactivated
            rgDeploy.check(R.id.deploy_aus);
        } else {
            rgDeploy.check(R.id.deploy_an);
        }

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int advertSelected = rgDeploy.getCheckedRadioButtonId();
                View radioButton = rgDeploy.findViewById(advertSelected);
                int idx = rgDeploy.indexOfChild(radioButton); // Index: 0 or 1
                byte[] callbackByte = new byte[1];
                if(idx == 0)    callbackByte[0] = 0x01; // turn on deploy mode
                else            callbackByte[0] = 0x00; // turn off.
                intent.putExtra("deploy_data", callbackByte);
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
