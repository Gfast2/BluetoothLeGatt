package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class char_advert_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.advertInterval);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characteristic_advertinterval);
        Button btEnter = (Button) findViewById(R.id.advert_enter);
        Button btCancel = (Button) findViewById(R.id.advert_cancel);
        final RadioGroup rgAdvert = (RadioGroup) findViewById(R.id.advert_radioGroup);
        final Intent intent = new Intent(char_advert_Activity.this, DeviceControlActivity.class);

        byte[] advertByte = getIntent().getByteArrayExtra(DeviceControlActivity.EXTRA_READ);

        switch (advertByte[0]) {
            case 0x02:
                rgAdvert.check(R.id.advert_100);
                break;
            case 0x04:
                rgAdvert.check(R.id.advert_200);
                break;
            case 0x06:
                rgAdvert.check(R.id.advert_300);
                break;
            case 0x08:
                rgAdvert.check(R.id.advert_400);
                break;
            case 0x0A:
                rgAdvert.check(R.id.advert_500);
                break;
            case 0x0C:
                rgAdvert.check(R.id.advert_600);
                break;
            case 0x0E:
                rgAdvert.check(R.id.advert_700);
                break;
            case 0x10:
                rgAdvert.check(R.id.advert_800);
                break;
            case 0x12:
                rgAdvert.check(R.id.advert_900);
                break;
            default:
                // TODO: Check possible Errors.
                break;

        }


        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int advertSelected = rgAdvert.getCheckedRadioButtonId();
                View radioButton = rgAdvert.findViewById(advertSelected);
                int idx = rgAdvert.indexOfChild(radioButton); // Index: 0 - 8
                byte[] callbackByte = new byte[1];
                switch (idx) { //get byte for callback.
                    case 0:
                        callbackByte[0] = 0x02;
                        break;
                    case 1:
                        callbackByte[0] = 0x04;
                        break;
                    case 2:
                        callbackByte[0] = 0x06;
                        break;
                    case 3:
                        callbackByte[0] = 0x08;
                        break;
                    case 4:
                        callbackByte[0] = 0x0A;
                        break;
                    case 5:
                        callbackByte[0] = 0x0C;
                        break;
                    case 6:
                        callbackByte[0] = 0x0E;
                        break;
                    case 7:
                        callbackByte[0] = 0x10;
                        break;
                    case 8:
                        callbackByte[0] = 0x12;
                        break;
                }
                intent.putExtra("advert_data", callbackByte);
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
