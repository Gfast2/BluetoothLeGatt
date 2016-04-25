package com.example.android.bluetoothlegatt;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class char_txpower_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_txpower);

        Button btEnter  = (Button) findViewById(R.id.txpower_enter);
        Button btCancel = (Button) findViewById(R.id.txpower_cancel);

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
