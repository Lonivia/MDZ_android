package com.nordicsemi.nrfUARTv2;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;


import com.nordicsemi.nrfUARTv2.UartService;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends Activity{

	
	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,        
                WindowManager.LayoutParams. FLAG_FULLSCREEN); 
        setContentView(R.layout.welcome);
        Start();
    }
  
    public void Start() {
                new Thread() {
                        public void run() {
                                try {
                                        Thread.sleep(2500);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                                Intent intent = new Intent();
                                //intent.setClass(WelcomeActivity.this, MainActivity.class);
                                intent.setClass(WelcomeActivity.this, com.nordicsemi.nrfUARTv2.SelectActivity.class);
                                startActivity(intent);
                                WelcomeActivity.this.finish();
                        }
                }.start();
        }
}
