package com.akash.hiremath.droidpad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.hiremath.droidpad.keyboardUtils.KeyboardSender;
import com.akash.hiremath.droidpad.mouseUtils.MouseGestureDetectListener;
import com.akash.hiremath.droidpad.mouseUtils.MouseSender;
import com.akash.hiremath.droidpad.mouseUtils.MouseViewListener;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MouseActivity extends AppCompatActivity {

    Button leftClick;
    Button rightClick;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        MouseSender mouseSender=new MouseSender(MainActivity.mBtHidDevice,MainActivity.mBtDevice);
        leftClick=findViewById(R.id.left);
        rightClick=findViewById(R.id.right);

        leftClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseSender.sendLeftClickOn();
                mouseSender.sendLeftClickOff();
            }
        });

        rightClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseSender.sendRightClick();
            }
        });

        View.OnTouchListener viewTouchListener=new MouseViewListener(MainActivity.mBtHidDevice,MainActivity.mBtDevice,mouseSender);

        final CustomGestureDetector mDetector = new CustomGestureDetector(this, new MouseGestureDetectListener(mouseSender, MouseActivity.this));

        View.OnTouchListener gTouchListener = new View.OnTouchListener() {
            public final boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
            }
        };


        View.OnTouchListener onTouchListener=new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for(View.OnTouchListener listener: registeredListeners)
                {
                    listener.onTouch(v,event);
                }

                return true;
            }
        };



        registerListener(gTouchListener);
        registerListener(viewTouchListener);
        MouseActivity.this.findViewById(R.id.touchpad).setOnTouchListener(onTouchListener);
    }

    ArrayList<View.OnTouchListener> registeredListeners = new ArrayList<View.OnTouchListener>();

    public final void registerListener(@NotNull View.OnTouchListener listener) {
        registeredListeners.add(listener);
    }

    final class CustomGestureDetector extends GestureDetector {
        private MouseGestureDetectListener mListenerMouse;

        public boolean onTouchEvent(@Nullable MotionEvent ev) {
            boolean consume = this.mListenerMouse.onTouchEvent(ev);
            return consume || super.onTouchEvent(ev);
        }

        public CustomGestureDetector(@NotNull Context context, @NotNull MouseGestureDetectListener mListenerMouse) {
            super(context, (OnGestureListener)mListenerMouse);
            this.mListenerMouse = mListenerMouse;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_keyboard:
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        KeyboardSender keyboardSender=new KeyboardSender(MainActivity.mBtHidDevice,MainActivity.mBtDevice);
        keyboardSender.sendKeyboard(keyCode,event,0);
        return super.onKeyUp(keyCode, event);
    }

}

