package com.example.android.bluetoothlegatt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.ResultCallback;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class char_uuid_Activity extends Activity {
    private final static String TAG = char_uuid_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.ibeaconuuid);
        setContentView(R.layout.characteristic_uuid);
        Button btEnter = (Button) findViewById(R.id.uuid_enter);
        Button btCancel = (Button) findViewById(R.id.uuid_cancel);
        final EditText txUUID = (EditText) findViewById(R.id.uuid);
        byte[] uuidByte = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);
        // intent to send back infomation.
        final Intent intent = new Intent(char_uuid_Activity.this, DeviceControlActivity.class);

        String uuid = "UUID not read"; // String to display.
        if (uuidByte != null && uuidByte.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(uuidByte.length);
            for (byte byteChar : uuidByte)
                stringBuilder.append(String.format("%02X ", byteChar));
            /*for(int i=0;i<stringBuilder.length();++i){
                if(Character.isWhitespace(stringBuilder.charAt(i))){
                    stringBuilder.deleteCharAt(i);
                    i--;
                }
            }*/
            int j = 0;
            for (int i = 0; i < stringBuilder.length(); i++) {
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
//                                           new Thread(new Runnable() {
//                                               private final String TAG = char_uuid_Activity.class.getSimpleName();
//
//                                               @Override
//                                               public void run() {
                                           String rawUUID = txUUID.getText().toString();
                                           String[] uuidArray = new String[16]; // uuid have always 16 2bit HEX.
                                           int[] uuidInt = new int[16];
                                           byte[] uuidByteFinal = new byte[16];

                                           int in = 0;
                                           while (in < uuidArray.length) {
//                                                   for (int in = 0; in < uuidArray.length; in++) {
                                               uuidArray[in] = rawUUID.substring(in * 2, in * 2 + 2);
                                               uuidInt[in] = Integer.valueOf(uuidArray[in], 16);
                                               uuidByteFinal[in] = (byte) (uuidInt[in] & 0xFF); // cast 16 ints into bytes
                                               in++;
                                           }
                                           Intent intent = new Intent(char_uuid_Activity.this, DeviceControlActivity.class);
                                           //Log.d(TAG, "uuid_data");
                                           intent.putExtra("uuid_data", uuidByteFinal);
                                           setResult(Activity.RESULT_OK, intent);
                                           // TODO: Data length + correction check
                                           // TODO: and send the new Input back to BLE Service
                                           // TODO: do the actuall change.
//                                               }
//                                           }).start();
                                           finish();
                                       }
                                   }

        );

        btCancel.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }
                                    }

        );


    }

    // Divide a integer into two byte. for major/minor calculation.
    public static byte[] intToByteArray(int a) {
        byte[] ret = new byte[2];
        ret[1] = (byte) (a & 0xFF);
        ret[0] = (byte) ((a >> 8) & 0xFF);
        return ret;
    }
}
