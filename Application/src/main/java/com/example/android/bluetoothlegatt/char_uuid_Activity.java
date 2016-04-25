package com.example.android.bluetoothlegatt;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class char_uuid_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_uuid);
        Button btEnter  = (Button) findViewById(R.id.measurepower_enter);
        Button btCancel = (Button) findViewById(R.id.measurepower_cancel);

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
