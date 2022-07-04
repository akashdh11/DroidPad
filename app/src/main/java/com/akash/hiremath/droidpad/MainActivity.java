package com.akash.hiremath.droidpad;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.bluetooth.BluetoothProfile;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.Range;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.hiremath.droidpad.Services.CBWatcherService;
import com.akash.hiremath.droidpad.keyboardUtils.KeyboardReport;
import com.akash.hiremath.droidpad.keyboardUtils.KeyboardSender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    public static BluetoothHidDevice mBtHidDevice;
    public static BluetoothDevice mBtDevice;

    private static final String TAG = "MainActivity";
    private final BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();
    TextView status;
    private boolean isConnected = false;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if ("android.intent.action.SEND".equals(action) && type != null && "text/plain".equals(type)) {
            Log.println(Log.ASSERT, "shareablTextExtra", intent.getStringExtra("android.intent.extra.TEXT"));
        }

        Button mouse = findViewById(R.id.open_mouse);
        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.getText().equals(getString(R.string.status_connected))) {
                    Intent mouseIntent = new Intent(MainActivity.this, MouseActivity.class);
                    MainActivity.this.startActivity(mouseIntent);
                } else {
                    info("Please connect");
                }
            }
        });

        Button gamepad = findViewById(R.id.open_gamepad);
        gamepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.getText().equals(getString(R.string.status_connected))) {
                    Intent mouseIntent = new Intent(MainActivity.this, GamepadActivity.class);
                    MainActivity.this.startActivity(mouseIntent);
                } else {
                    info("Please connect");
                }
            }
        });

        Button unlock = findViewById(R.id.unlock);
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.getText().equals(getString(R.string.status_connected))) {
                    KeyboardSender keyboardSender = new KeyboardSender(mBtHidDevice, mBtDevice);
                    keyboardSender.sendKeyOn(82);
                    keyboardSender.sendKeyOff();
                    keyboardSender.sendKeyOn(32);
                    keyboardSender.sendKeyOff();
                    keyboardSender.sendKeyOn(37);
                    keyboardSender.sendKeyOff();
                    keyboardSender.sendKeyOn(33);
                    keyboardSender.sendKeyOff();
                    keyboardSender.sendKeyOn(36);
                    keyboardSender.sendKeyOff();
                    keyboardSender.sendKeyOn(40);
                    keyboardSender.sendKeyOff();
                } else {
                    info("Please connect");
                }
            }
        });

        Button keyboard = findViewById(R.id.keyboard);
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.getText().equals(getString(R.string.status_connected))) {
                    KeyboardSender keyboardSender = new KeyboardSender(mBtHidDevice, mBtDevice);
                    Intent mouseIntent = new Intent(MainActivity.this, KeyboardActivity.class);
                    MainActivity.this.startActivity(mouseIntent);
                } else {
                    info("Please connect");
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get bluetooth enabled before continuing
        if (!mBtAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            final int REQUEST_ENABLE_BT = 99;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            if (!isConnected) {
                btListDevices();
            }
        }
    }


    private void info(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
        Log.d(TAG, msg);
    }

    private void getProxy() {
        mBtAdapter.getProfileProxy(this, new BluetoothProfile.ServiceListener() {
            @Override
            @SuppressLint("NewApi")
            public void onServiceConnected(int profile, BluetoothProfile proxy) {

                if (profile == BluetoothProfile.HID_DEVICE) {
//                    info(proxy.toString());
                    mBtHidDevice = (BluetoothHidDevice) proxy;

                    BluetoothHidDeviceAppSdpSettings sdp = new BluetoothHidDeviceAppSdpSettings(
                            "YBox",
                            "Android HID",
                            "Android",
                            BluetoothHidDevice.SUBCLASS2_GAMEPAD,
                            DescriptorCollection.MOUSE_KEYBOARD_GAMEPAD_COMBO
                    );

//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
                    mBtHidDevice.registerApp(sdp, null, null, Runnable::run, new BluetoothHidDevice.Callback() {
                        @Override
                        @BinderThread
                        public void onAppStatusChanged(BluetoothDevice pluggedDevice, boolean registered) {
                            super.onAppStatusChanged(pluggedDevice, registered);
                        }

                        @Override
                        @BinderThread
                        public void onConnectionStateChanged(BluetoothDevice device, int state) {
                            super.onConnectionStateChanged(device, state);
                            if (device.equals(mBtDevice)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (state == BluetoothProfile.STATE_DISCONNECTED) {
                                            status.setText(R.string.status_disconnected);
                                            mBtDevice = null;
                                        } else if (state == BluetoothProfile.STATE_CONNECTING) {
                                            status.setText(R.string.status_connecting);
                                        } else if (state == BluetoothProfile.STATE_CONNECTED) {
                                            status.setText(R.string.status_connected);
                                        } else if (state == BluetoothProfile.STATE_DISCONNECTING) {
                                            status.setText(R.string.status_disconnecting);
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        @BinderThread
                        public void onGetReport(BluetoothDevice device, byte type, byte id, int bufferSize) {
                            super.onGetReport(device, type, id, bufferSize);
                        }

                        @Override
                        @BinderThread
                        public void onSetReport(BluetoothDevice device, byte type, byte id, byte[] data) {
                            super.onSetReport(device, type, id, data);
                        }
                    });
                }
            }

            @Override
            public void onServiceDisconnected(int profile) {
                if (profile == BluetoothProfile.HID_DEVICE) {
                    info("Lost HID device");
                }
            }
        }, BluetoothProfile.HID_DEVICE);
    }

    private void btListDevices() {
        getProxy(); // need bluetooth to have been enabled first

        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Add devices to adapter
        List<String> names = new ArrayList<>();

        // add empty
        names.add("(disconnected)");
        mDevices.add(null);

        for (BluetoothDevice btDev : pairedDevices) {
            names.add(btDev.getName());
            mDevices.add(btDev);
        }

        Spinner btList = findViewById(R.id.devices);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btList.setAdapter(adapter);

        btList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice dev = mDevices.get(position);
                btConnect(dev);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO handle this
            }
        });
        
    }

    private void btConnect(BluetoothDevice device) {
        info("Bluetooth Connected:" + device);
        isConnected=true;
        // disconnect from everything else
        for (BluetoothDevice btDev : mBtHidDevice.getDevicesMatchingConnectionStates(new int[]{
                BluetoothProfile.STATE_CONNECTING,
                BluetoothProfile.STATE_CONNECTED
        })) {
            mBtHidDevice.disconnect(btDev);
        }

        if (device != null) {
            mBtDevice = device;
            mBtHidDevice.connect(device);
        }
    }

}