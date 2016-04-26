package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class char_uuid_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_uuid);
        Button btEnter  = (Button) findViewById(R.id.uuid_enter);
        Button btCancel = (Button) findViewById(R.id.uuid_cancel);
        EditText txUUID = (EditText) findViewById(R.id.uuid);
        byte[] uuidByte= getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);
        // intent to send back infomation.
        final Intent intent = new Intent(char_uuid_Activity.this, DeviceControlActivity.class);

        String uuid = "UUID not read"; // String to display.
        if (uuidByte != null && uuidByte.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(uuidByte.length);
            for(byte byteChar : uuidByte)
                stringBuilder.append(String.format("%02X ", byteChar));
            /*for(int i=0;i<stringBuilder.length();++i){
                if(Character.isWhitespace(stringBuilder.charAt(i))){
                    stringBuilder.deleteCharAt(i);
                    i--;
                }
            }*/
            int j = 0;
            for(int i = 0; i < stringBuilder.length(); i++) {
                if (!Character.isWhitespace(stringBuilder.charAt(i)))
                    stringBuilder.setCharAt(j++, stringBuilder.charAt(i));
            }
            stringBuilder.delete(j, stringBuilder.length());
//            Log.d(TAG, "String from BluetoothLeService.java to sent now: " + new String(data) + "\n" + stringBuilder.toString());
            uuid = stringBuilder.toString();
        }
        txUUID.setText(uuid, TextView.BufferType.EDITABLE);

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
