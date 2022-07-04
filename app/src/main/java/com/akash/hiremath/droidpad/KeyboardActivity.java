package com.akash.hiremath.droidpad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.akash.hiremath.droidpad.keyboardUtils.KeyboardReport;
import com.akash.hiremath.droidpad.keyboardUtils.KeyboardSender;

public class KeyboardActivity extends AppCompatActivity {

    KeyboardSender keyboardSender;
    Button ctrl,alt,winCmd,mute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        keyboardSender=new KeyboardSender(MainActivity.mBtHidDevice,MainActivity.mBtDevice);

        ctrl=findViewById(R.id.ctrl);
        ctrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardSender.setLeftCtrlOn();
            }
        });

        alt=findViewById(R.id.alt);
        alt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardSender.setLeftAltOn();
            }
        });

        winCmd=findViewById(R.id.win_cmd);
        winCmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardSender.setLeftGuiOn();
            }
        });

        mute=findViewById(R.id.mute);
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardSender.sendKeyOn(0x3d);
                keyboardSender.sendKeyOff();
            }
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        keyboardSender.sendKeyboard(keyCode,event,0);
        return super.onKeyUp(keyCode, event);
    }
}