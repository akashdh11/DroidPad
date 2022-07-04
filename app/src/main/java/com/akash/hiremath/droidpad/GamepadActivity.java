package com.akash.hiremath.droidpad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.Range;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class GamepadActivity extends AppCompatActivity  {

    private static final String TAG = "GamepadActivity";
    private ImageButton[] buttons1;
    private ImageButton[] buttons2;
    private JoystickView leftStick,rightStick;
    static int reportIndex = 0;
    private Vibrator vibrator;
    public Sensor mySensor;
    private SensorManager mySensorManager;

    private float x;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepad);

//        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mySensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);


        vibrator = getSystemService(Vibrator.class);

        // initialise the joystick
        leftStick = findViewById(R.id.left_stick);
        leftStick.setTag(R.id.tag_x_pos, 0.0);
        leftStick.setTag(R.id.tag_y_pos, 0.0);
        leftStick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                double rad = Math.toRadians(angle);
                double dist = strength / 100.0;
                // vibrate if crossing an axis
//                double prev_x = (double) joystick.getTag(R.id.tag_x_pos);
//                double prev_y = (double) joystick.getTag(R.id.tag_y_pos);
//                double x = dist * Math.cos(rad);
//                double y = dist * Math.sin(rad);
//
//                if((prev_x < -0.15 && x >= -0.15) || (prev_x > 0.15 && x <= 0.15) ||
//                        (prev_y < -0.15 && y >= -0.15) || (prev_y > 0.15 && y <= 0.15)) {
//                    vibrator.vibrate(VibrationEffect.createOneShot(40, 50));
//                }

                leftStick.setTag(R.id.tag_x_pos, dist * Math.cos(rad));
                leftStick.setTag(R.id.tag_y_pos, dist * Math.sin(rad));
                sendReport();
            }
        });

        rightStick = findViewById(R.id.right_stick);
        rightStick.setTag(R.id.tag_x_pos, 0.0);
        rightStick.setTag(R.id.tag_y_pos, 0.0);
        rightStick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                double rad = Math.toRadians(angle);
                double dist = strength / 100.0;
                // vibrate if crossing an axis
//                double prev_x = (double) joystick.getTag(R.id.tag_x_pos);
//                double prev_y = (double) joystick.getTag(R.id.tag_y_pos);
//                double x = dist * Math.cos(rad);
//                double y = dist * Math.sin(rad);
//
//                if((prev_x < -0.15 && x >= -0.15) || (prev_x > 0.15 && x <= 0.15) ||
//                        (prev_y < -0.15 && y >= -0.15) || (prev_y > 0.15 && y <= 0.15)) {
//                    vibrator.vibrate(VibrationEffect.createOneShot(40, 50));
//                }

                rightStick.setTag(R.id.tag_x_pos, dist * Math.cos(rad));
                rightStick.setTag(R.id.tag_y_pos, dist * Math.sin(rad));
                sendReport();
            }
        });

        // get buttons ready
        buttons1 = new ImageButton[]{
                findViewById(R.id.A_Button),
                findViewById(R.id.B_Button),
                findViewById(R.id.X_Button),
                findViewById(R.id.Y_Button),
                findViewById(R.id.left_trigger),
                findViewById(R.id.right_trigger),
                findViewById(R.id.left_bumper),
                findViewById(R.id.right_bumper),
        };
        for (ImageButton button : buttons1) {
            button.setTag(R.id.tag_pressed, false);
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // Do something
                            if(!(boolean) v.getTag(R.id.tag_pressed)) {
                                vibrator.vibrate(VibrationEffect.createOneShot(40, 50));
                            }
                            v.setTag(R.id.tag_pressed, true);
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            // No longer down
                            v.setTag(R.id.tag_pressed, false);
                            break;
                        default:
                            return false;
                    }
                    sendReport();
                    return false;
                }
            });
        }

        buttons2 = new ImageButton[]{
                findViewById(R.id.guide_button),
                findViewById(R.id.start_button),
                findViewById(R.id.back_button),
        };
        for (ImageButton button : buttons2) {
            button.setTag(R.id.tag_pressed, false);
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // Do something
                            if(!(boolean) v.getTag(R.id.tag_pressed)) {
                                vibrator.vibrate(VibrationEffect.createOneShot(40, 50));
                            }
                            v.setTag(R.id.tag_pressed, true);
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            // No longer down
                            v.setTag(R.id.tag_pressed, false);
                            break;
                        default:
                            return false;
                    }
                    sendReport();
                    return false;
                }
            });
        }


    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        x = (event.values[1])/10;
////        Log.e("x", String.valueOf(x));
//        sendReport();
//        // The x value in the accelerometer is the 0th index in the array
//
//        // Now you may do what you wish with this x value, and use it to move your ball
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }


    private void sendReport() {
        byte defState = 0;
        // get button state
        byte state1 = 0;
        for (int i = 0; i < 8; ++i) {
            if ((boolean) buttons1[i].getTag(R.id.tag_pressed)) {
                state1 |= (1 << i);
            }
        }
        byte state2 = 0;
        for (int i = 0; i < 3; ++i) {
            if ((boolean) buttons2[i].getTag(R.id.tag_pressed)) {
                state2 |= (1 << i);
            }
        }
        // get joystick state
        Range<Integer> bounds = new Range<>(-127, 127);
//        int adjX = bounds.clamp((int) ((double) x * 127));
        int adjX = bounds.clamp((int) ((double) leftStick.getTag(R.id.tag_x_pos) * 127));
        int adjY = bounds.clamp((int) ((double) leftStick.getTag(R.id.tag_y_pos) * -127));


        int adjRx = bounds.clamp((int) ((double) rightStick.getTag(R.id.tag_x_pos) * 127));
        int adjRy = bounds.clamp((int) ((double) rightStick.getTag(R.id.tag_y_pos) * -127));

//        Log.d(TAG, "sendReport(): " + state1 + " " + adjX + " " + adjY);
//        TextView reportIndicator = findViewById(R.id.reportCount);
//        reportIndicator.setText("#" + reportIndex++);

        for (BluetoothDevice btDev : MainActivity.mBtHidDevice.getConnectedDevices()) {
            MainActivity.mBtHidDevice.sendReport(btDev, DescriptorCollection.GAMEPAD_ID, new byte[]{
                    state1,
                    state2,
                    (byte) adjX,
                    (byte) adjY,
                    (byte) defState,
                    (byte) adjRx,
                    (byte) adjRy,
                    (byte) defState,
            });
        }
    }
}