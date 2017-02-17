/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nordicsemi.nrfUARTv2;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.util.Date;

//import org.apache.axis.client.Service;
import org.xmlpull.v1.XmlPullParser;

//import com.soap.mysaop.SoapTestControllerBindingStub;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	


	//蓝牙状态指示变量，可不管
    private static final int REQUEST_SELECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int UART_PROFILE_READY = 10;
    public static final String TAG = "nRFUART";
    private static final int UART_PROFILE_CONNECTED = 20;
    private static final int UART_PROFILE_DISCONNECTED = 21;
    private static final int STATE_OFF = 10;

    //找脉设定值的两个参数，ZMi的值0为沉，1为中，2为沉，找脉时按012的顺序，设置、存储参数时按210的顺序
    public static int[] ZM =new int[3];
    public static int ZMi=0;
    //int cancel=0;
    
    //AD采集的相关变量
    int[][] ADbuff=new int[10][5]; //找脉时的10个缓冲池
    int ADi=0;
    int ADbase=0;
    long ADall;
    
    int startFind=0;
    int timeout=0;	//10秒之后找不到脉自动使能timeout，开始采集
    

    public static int[][][] ADco=new int[2400][5][6];
    int ADcoi=0;	//接受时的2400个计数器
    int Ptime=0;	//找脉的顺序计数，0-5
    //int[] PA={3000,2000,1000};
    String[] PString={"开始诊断-左手浮","开始诊断-左手中","开始诊断-左手沉","开始诊断-右手浮","开始诊断-右手中","开始诊断-右手沉"};
    String[] PStrings={"左手浮","左手中","左手沉","右手浮","右手中","右手沉"};
    
    TextView mRemoteRssiVal;
    RadioGroup mRg;
    private int mState = UART_PROFILE_DISCONNECTED;
    private UartService mService = null;
    private BluetoothDevice mDevice = null;
    private BluetoothAdapter mBtAdapter = null;
    private ListView messageListView;
    private ArrayAdapter<String> listAdapter;
    
    //四个按钮，连接、进入诊脉页面、取消、高级设置
    private Button btnConnectDisconnect,btnSend,buttoncancel;
    //private EditText edtMessage;
    private Button btnadv;
    
    //6个进度圈和6个脉象标识
    private TextView tvzuoshouchen,tvzuoshouzhong,tvzuoshoufu,tvyoushouchen,tvyoushouzhong,tvyoushoufu;
    private ProgressBar pg1,pg2,pg3,pg4,pg5,pg6;
    
    int sendflag;
    
    //4、5、6三个线程，找脉成功时调用，发送0157取消找脉，当找脉超时时做相关处理
    Handler hd1=new Handler();
    Handler hd2=new Handler();
    Handler hd3=new Handler();
    Handler hd4=new Handler();
    Handler hd5=new Handler();
    Handler hd6=new Handler();
    Runnable rn4=new Runnable(){
	    public void run() {
    		if(sendflag==1 && ZMi==0){
		    	timeout=1;
		    	Log.e("time", "4000");
		    	//byte[] value={0x01,0x57};
		    	try{
		    	//mService.writeRXCharacteristic(value);
		    		}
		    	catch(Exception e){
		    		Log.e("MyLog", "----------"+"send find stop 01 57 ERROR!!!");
		    	}
		    	Log.e("MyLog", "----------"+"send Over!!!");
		    	//buttoncancel.setEnabled(false);
    		}
	    }    
 	};
 	Runnable rn5=new Runnable(){
 		public void run() {  
 			if(sendflag==1 && ZMi==1){
		    	timeout=1;
		    	Log.e("time", "4500");
 			}

	    }  
 	};
 	Runnable rn6=new Runnable(){
 		public void run() {  
 			if(sendflag==1 && ZMi==2){
		    	timeout=1;
		    	Log.e("time", "5000");
 			}
    		if(sendflag==1){
		    	//mDevice=null;
		    	//btnConnectDisconnect.setEnabled(true);
		    	//startFind=0;
		    	//cancel=1;
	    		//ADi=0;
	    	    //ADbase=0;
	    	    //ADall=0;
	    	    //startFind=1;
	    	    //ADcoi=0;
	    	    //String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	     	 	//listAdapter.add("["+currentDateTimeString+"] 找脉失败！ ");
	     	 	//messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	     	 	
	     	 	//pg1.setVisibility(255);
	    		//pg2.setVisibility(255);
	    		//pg3.setVisibility(255);
	    		//pg4.setVisibility(255);
	    		//pg5.setVisibility(255);
	    		//pg6.setVisibility(255);
	 			//timeout=1;
	 			//buttoncancel.setEnabled(true);
    		}
	    } 
 	};
 	
 	
 	//采集脉象时的11个线程
 	Handler hdf1=new Handler();
 	Handler hdf2=new Handler();
 	Handler hdf3=new Handler();
 	Handler hdf4=new Handler();
 	Handler hdf5=new Handler();
 	Handler hdf6=new Handler();
 	Handler hdf7=new Handler();
 	Handler hdf8=new Handler();
 	Handler hdf9=new Handler();
 	Handler hdf10=new Handler();
 	Handler hdf11=new Handler();
 	
 	//1-10个线程是指示进度10%-100%，第11个线程用于发送0143收集信号
 	Runnable rnf1=new Runnable(){
 		 public void run() {    
	      	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	 	 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"10%");
	 	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	 	 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度10%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度10%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度10%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度10%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度10%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度10%");;
	 	 	}
	    } 
 	};
 	Runnable rnf2=new Runnable(){
 		 public void run() {    
	      	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	 	 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"20%");
	 	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	 	 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度20%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度20%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度20%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度20%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度20%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度20%");;
	 	 	}
	    }
	};
	Runnable rnf3=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"30%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度30%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度30%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度30%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度30%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度30%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度30%");;
	 	 	}
		} 
	};
	Runnable rnf4=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"40%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度40%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度40%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度40%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度40%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度40%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度40%");;
	 	 	}
		} 
	};
	Runnable rnf5=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"50%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度50%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度50%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度50%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度50%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度50%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度50%");;
	 	 	}
		}
	};
	Runnable rnf6=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"60%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度60%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度60%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度60%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度60%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度60%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度60%");;
	 	 	}
		} 
	};
	Runnable rnf7=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"70%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度70%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度70%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度70%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度70%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度70%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度70%");;
	 	 	}
		}
	};
	Runnable rnf8=new Runnable(){
		public void run() {
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"80%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度80%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度80%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度80%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度80%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度80%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度80%");;
	 	 	}
		}
	};
	Runnable rnf9=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"90%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度90%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度90%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度90%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度90%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度90%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度90%");;
	 	 	}
		}
	};
	Runnable rnf10=new Runnable(){
		public void run() {    
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 采集中...进度: "+"100%");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	buttoncancel.setEnabled(false);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-进度100%");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-进度100%");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-进度100%");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-进度100%");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-进度100%");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-进度100%");;
	 	 	}
	 	 	
	 	 	
	 	 	//采完一分钟就放气
	 	 	//if(Ptime==2){
	 	 	byte[] value={0x01,0x09};
		 	try{
		    	while(mService.writeRXCharacteristic(value)==0);
		 	}catch(Exception e){
		 		Log.e("MyLog", "no send 01 09 rec error!");
		 	}
	 	 	//}
		}
	};
	Runnable rnf11=new Runnable(){
		public void run() {    
			
			/*
	     	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
		 	listAdapter.add("["+currentDateTimeString+"] 接受中"+"...");
		 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
		 	if(Ptime==0){
	 	 		tvzuoshouchen.setText("左手浮-接受中");;
	 	 	}
	 	 	if(Ptime==1){
	 	 		tvzuoshouzhong.setText("左手中-接受中");;
	 	 	}
	 	 	if(Ptime==2){
	 	 		tvzuoshoufu.setText("左手沉-接受中");;
	 	 	}
	 	 	if(Ptime==3){
	 	 		tvyoushouchen.setText("右手浮-接受中");;
	 	 	}
	 	 	if(Ptime==4){
	 	 		tvyoushouzhong.setText("右手中-接受中");;
	 	 	}
	 	 	if(Ptime==5){
	 	 		tvyoushoufu.setText("右手沉-接受中");;
	 	 	}
		 	
		 	
		 	byte[] value={0x01,0x43};
		 	try{
		    	mService.writeRXCharacteristic(value);
		 	}catch(Exception e){
		 		Log.e("MyLog", "no send 01 43 rec error!");
		 	}
		    Log.e("MyLog", "----------"+"Send 0143 start receive");
		    */
			
			
	    	btnConnectDisconnect.setEnabled(true);
	    	
	    	Ptime++;
	    	if(Ptime>=6){
	    		Ptime=0;
	    		btnConnectDisconnect.setText(PString[Ptime]);
	    		btnConnectDisconnect.setEnabled(false);
	    		String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
			 	listAdapter.add("["+currentDateTimeString+"] 接受中"+"...");
			 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
			 	byte[] value={0x01,0x43};
			 	try{
			    	while(mService.writeRXCharacteristic(value)==0){}
			 	}catch(Exception e){
			 		Log.e("MyLog", "no send 01 43 rec error!");
			 	}
			 	Log.e("MyLog", "----------"+"Send 0143 start receive");

	    	}else{
	    		btnConnectDisconnect.setText(PString[Ptime]);
	    		if(Ptime!=3)
	    		btnConnectDisconnect.performClick();
	    		//一次采集后自动调用click执行下一次找脉
	    	}
	    	
	    	if(ZMi>=3){
	    		//ZMi=0;
	    	}
		}
	};
    
	
	//读写文件操作，writeFile写入文件，会覆盖原文件
	final static String FOLDER = "/MDZfile/";   
	//final static String FILENAME = "config";    
	final static String SUFFIX = ".mdz";
	private void writeFile(String sb ,String FILENAME) {
	    String foldername = Environment.getExternalStorageDirectory().getPath()   
	                             + FOLDER;   
	    File folder = new File(foldername);   
	    if (folder != null && !folder.exists()) {   
	        if (!folder.mkdir() && !folder.isDirectory())   
	        {   
	            Log.d("SelectLog", "Error: make dir failed!");   
	            return;   
	        }   
	    }   
	    
	    String stringToWrite = sb.toString();   
	    String targetPath = foldername + FILENAME + SUFFIX;   
	    File targetFile = new File(targetPath);   
	    if (targetFile != null) {   
	        if (targetFile.exists()) {   
	            targetFile.delete();   
	        }    
	    
	        OutputStreamWriter osw;   
	        try{   
	            osw = new OutputStreamWriter(   
	                        new FileOutputStream(targetFile),"utf-8");   
	            try {   
	                osw.write(stringToWrite);   
	                osw.flush();   
	                osw.close();   
	            } catch (IOException e) {     
	                e.printStackTrace();   
	            }   
	        } catch (UnsupportedEncodingException e1) {     
	            e1.printStackTrace();   
	        } catch (FileNotFoundException e1) {     
	            e1.printStackTrace();   
	        }   
	    }   
	}
	private String readFile(String filename) {  
		String foldername = Environment.getExternalStorageDirectory().getPath()   
                + FOLDER;   
		String targetPath = foldername + filename + SUFFIX;    
	 
	    String filecontent = null;   
	    File f = new File(targetPath);   
	    if (f != null && f.exists())   
	    {
	        FileInputStream fis = null;   
	        try {   
	            fis = new FileInputStream(f);   
	        } catch (FileNotFoundException e1) {   
	               
	            e1.printStackTrace();   
	            Log.d("SelectLog", "Error: Input File not find!");   
	            return null;   
	        }   
	  
	        CharBuffer cb;   
	        try {   
	            cb = CharBuffer.allocate(fis.available());   
	        } catch (IOException e1) {   
	              
	            e1.printStackTrace();   
	            Log.d("SelectLog", "Error: CharBuffer initial failed!");   
	            return null;   
	        }   
	    
	        InputStreamReader isr;   
	        try {   
	            isr = new InputStreamReader(fis, "utf-8");   
	            try {   
	                if (cb != null) {   
	                   isr.read(cb);   
	                }   
	                filecontent = new String(cb.array());   
	                isr.close();   
	            } catch (IOException e) {   
	                e.printStackTrace();   
	            }   
	        } catch (UnsupportedEncodingException e) {   
	               
	            e.printStackTrace();           
	        }   
	    }   
	    Log.d("SelectLog", "readFile filecontent = " + filecontent);   
	    return filecontent;   
	} 
	
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        messageListView = (ListView) findViewById(R.id.listMessage4);
        listAdapter = new ArrayAdapter<String>(this, R.layout.message_detail);
        messageListView.setAdapter(listAdapter);
        messageListView.setDivider(null);
        btnConnectDisconnect=(Button) findViewById(R.id.btn_select);
        btnSend=(Button) findViewById(R.id.sendButton);

        //6个诊脉步骤指示
        tvzuoshouchen=(TextView)findViewById(R.id.textViewzuoshouchen);
        tvzuoshouzhong=(TextView)findViewById(R.id.textViewzuoshouzhong);
        tvzuoshoufu=(TextView)findViewById(R.id.textViewzuoshoufu);
        tvyoushouchen=(TextView)findViewById(R.id.textViewyoushouchen);
        tvyoushouzhong=(TextView)findViewById(R.id.textViewyoushouzhong);
        tvyoushoufu=(TextView)findViewById(R.id.textViewyoushoufu);
        //6个进度圈指示
        pg1=(ProgressBar)findViewById(R.id.progressBar1);
        pg2=(ProgressBar)findViewById(R.id.progressBar2);
        pg3=(ProgressBar)findViewById(R.id.progressBar3);
        pg4=(ProgressBar)findViewById(R.id.progressBar4);
        pg5=(ProgressBar)findViewById(R.id.progressBar5);
        pg6=(ProgressBar)findViewById(R.id.progressBar6);
        //进度圈初始化时全部隐藏
        pg1.setVisibility(255);
        pg2.setVisibility(255);
        pg3.setVisibility(255);
        pg4.setVisibility(255);
        pg5.setVisibility(255);
        pg6.setVisibility(255);
        
        
        btnSend.setEnabled(true);
        //edtMessage.setEnabled(true);
        
        buttoncancel=(Button) findViewById(R.id.buttoncancel);
        buttoncancel.setEnabled(false);
        
        btnadv=(Button) findViewById(R.id.buttonadv);

        service_init();

        
        //初始化时读取找脉浮中沉参数，没有则设定为预设值
        if(readFile("MDZFUSET")!=null){
        	ZM[0]= Integer.parseInt(readFile("MDZFUSET"));
        }else{
        	ZM[0]=100;
        }
        if(readFile("MDZZHONGSET")!=null){
        	ZM[1]= Integer.parseInt(readFile("MDZZHONGSET"));
        }else{
        	ZM[1]=200;
        }
        if(readFile("MDZCHENSET")!=null){
        	ZM[2]= Integer.parseInt(readFile("MDZCHENSET"));
        	Log.e("!!!!!!!!!!!!", ""+ZM[2]);
        }else{
        	ZM[2]=300;
        }
        
        
        //进入高级设置页面
        btnadv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent();
                intent.setClass(MainActivity.this, com.nordicsemi.nrfUARTv2.SetupActivity.class);
                startActivity(intent);	
            }

        });
       
        //TODO Handler Disconnect & Connect button
        //连接找脉主控按钮！
        btnConnectDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBtAdapter.isEnabled()) {
                    Log.i(TAG, "onClick - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                }
                else {
                	
                	//每当采集一个进度，显示一个进度的圆圈
                	if(btnConnectDisconnect.getText().equals(PString[0]) ){
                		ZMi=0;
                		pg1.setVisibility(0);
                		pg2.setVisibility(255);
                		pg3.setVisibility(255);
                		pg4.setVisibility(255);
                		pg5.setVisibility(255);
                		pg6.setVisibility(255);
                		
                		tvzuoshouchen.setText("左手浮");
                		tvzuoshouzhong.setText("左手中");
                		tvzuoshoufu.setText("左手沉");
                		tvyoushouchen.setText("右手浮");
                		tvyoushouzhong.setText("右手中");
                		tvyoushoufu.setText("右手沉");
                	}
                	if(btnConnectDisconnect.getText().equals(PString[1]) ){
                		ZMi=1;
                		pg1.setVisibility(255);
                		pg2.setVisibility(0);
                		pg3.setVisibility(255);
                		pg4.setVisibility(255);
                		pg5.setVisibility(255);
                		pg6.setVisibility(255);
                	}
                	if(btnConnectDisconnect.getText().equals(PString[2]) ){
                		ZMi=2;
                		pg1.setVisibility(255);
                		pg2.setVisibility(255);
                		pg3.setVisibility(0);
                		pg4.setVisibility(255);
                		pg5.setVisibility(255);
                		pg6.setVisibility(255);
                	}
                	if(btnConnectDisconnect.getText().equals(PString[3])){
                		ZMi=0;
                		pg1.setVisibility(255);
                		pg2.setVisibility(255);
                		pg3.setVisibility(255);
                		pg4.setVisibility(0);
                		pg5.setVisibility(255);
                		pg6.setVisibility(255);
                	}
                	if(btnConnectDisconnect.getText().equals(PString[4])){
                		ZMi=1;
                		pg1.setVisibility(255);
                		pg2.setVisibility(255);
                		pg3.setVisibility(255);
                		pg4.setVisibility(255);
                		pg5.setVisibility(0);
                		pg6.setVisibility(255);
                	}
                	if(btnConnectDisconnect.getText().equals(PString[5])){
                		ZMi=2;
                		pg1.setVisibility(255);
                		pg2.setVisibility(255);
                		pg3.setVisibility(255);
                		pg4.setVisibility(255);
                		pg5.setVisibility(255);
                		pg6.setVisibility(0);
                	}
                	
                	timeout=0;
            		btnConnectDisconnect.setEnabled(false);
            		//mDevice =BluetoothAdapter.getDefaultAdapter().getRemoteDevice(SelectActivity.addr1);
            		Log.e("444", SelectActivity.addr1);
            		//cancel=0;
            		ADi=0;
            	    ADbase=0;
            	    ADall=0;
            	    startFind=1;
            	    ADcoi=0;
            	    //已连接时的情况，即第2次及以后的找脉情况
            		if (mDevice!=null){
            			//mService.disconnect();
            			//Log.e("MyLog", "----------"+"DisConnect!!!");
            			//mService.onConnectionStateChangeDisconnected();
            			

            			hd3.postDelayed(new Runnable(){    
        		    	    public void run() {    
        		    	    	byte[] value={0x01,0x54,0x00,0x00,0x00};
        		    	    	if(ZMi==0){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	if(ZMi==1){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	if(ZMi==2){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	
        		    	    	Log.e("MyLog", "----------"+"ready to send!!!");
        		    	    	try{
        		    	    		while(mService.writeRXCharacteristic(value)==0);
        		    	    	}catch(Exception e){
        		    	    		Log.e("MyLog", "send 01 54 error!!");
        		    	    	}
                 		    	//value[1]=0x46;
                 		    	//mService.writeRXCharacteristic(value);
        		    	    	//已连接时的，只有第一次找脉才可以取消（已连接不可能为第一次找脉，所以无用）
        		    	    	if(Ptime==0)
                 		    	buttoncancel.setEnabled(true);
        		    	    	
                 		    	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                 		    	listAdapter.add("["+currentDateTimeString+"] Connected--"+"正在找脉中....");
                        	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        	 	Log.e("time", "3000end");
        		    	    }    
        		    	 }, 3000);
            		}
            		//未连接的情况，即第一次找脉的情况
            		else{
            			mDevice=null;
            			hd1.postDelayed(new Runnable(){    
        		    	    public void run() {    
        		    	    	mDevice =BluetoothAdapter.getDefaultAdapter().getRemoteDevice(SelectActivity.addr1);
        		    	    	Log.e("MyLog", "----------"+"GetAddapter!!!"+mDevice.getName());
        		    	    }    
        		    	 }, 500);
            			hd2.postDelayed(new Runnable(){    
        		    	    public void run() {    
        		    	    	try{
        		    	    		//mDevice =BluetoothAdapter.getDefaultAdapter().getRemoteDevice(SelectActivity.addr1);
        		    	    		boolean connflag=mService.connect(SelectActivity.addr1);
                        			//mService.onConnectionStateChangeConnected();
                        			if(connflag){
	                        			//String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	                               	 	//listAdapter.add("["+currentDateTimeString+"] Connected to: "+ mDevice.getName()+"正在连接设备");
	                            	 	//messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        			}else{
                        				String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	                               	 	listAdapter.add("["+currentDateTimeString+"] 连接失败！请重试或重启设备与软件");
	                            	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	                            	 	mService.disconnect();
	                            		mDevice=null;
	                            		btnConnectDisconnect.setEnabled(true);
	                            		
	                            		timeout=0;
	                            		ADi=0;
	                            	    ADbase=0;
	                            	    ADall=0;
	                            	    ADcoi=0;
	                            	    ZMi=0;
	                            	    pg1.setVisibility(255);
    	                        		pg2.setVisibility(255);
    	                        		pg3.setVisibility(255);
    	                        		pg4.setVisibility(255);
    	                        		pg5.setVisibility(255);
    	                        		pg6.setVisibility(255);

                        			}
                        			
                        			
                        			
                        			Log.e("MyLog", "----------"+"Connect!!!"+SelectActivity.addr1);

                        			//mState = UART_PROFILE_CONNECTED;
                        		}catch(Exception ee){
                            		Log.e("MyLog", "----------"+"no MDZ found!!!");
                            		Log.e("MyLog", "----------"+"no MDZ found!!!");
                            		String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                            		//((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready"+"找脉中...");
                                    listAdapter.add("["+currentDateTimeString+"] 连接失败！请重试或重启设备与软件");
                               	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                               	 	mService.disconnect();
	                        		mDevice=null;
	                        		btnConnectDisconnect.setEnabled(true);
	                        		
	                        		timeout=0;
	                        		ADi=0;
	                        	    ADbase=0;
	                        	    ADall=0;
	                        	    ADcoi=0;
	                        	    ZMi=0;
	                        	    pg1.setVisibility(255);
	                        		pg2.setVisibility(255);
	                        		pg3.setVisibility(255);
	                        		pg4.setVisibility(255);
	                        		pg5.setVisibility(255);
	                        		pg6.setVisibility(255);

                            	}
                        		//mService.enableTXNotification();
                        		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        		String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        		//((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready"+"找脉中...");
                                //listAdapter.add("["+currentDateTimeString+"] Connected to: "+ mDevice.getName());
                           	 	//messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                           	 //((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready"+"找脉中...");
                             listAdapter.add("["+currentDateTimeString+"] Connected--"+"找脉初始化中...");
                        	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);

        		    	    }    
        		    	 }, 1000);
            			hd3.postDelayed(new Runnable(){    
        		    	    public void run() {    
        		    	    	byte[] value={0x01,0x54,0x00,0x00,0x00};
        		    	    	if(ZMi==0){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	if(ZMi==1){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	if(ZMi==2){
        		    	    		value[4]=0x00;
        		    	    	}
        		    	    	Log.e("MyLog", "----------"+"ready to send!!!");
        		    	    	try{
        		    	    		sendflag=
        		    	    				mService.writeRXCharacteristic(value);
        		    	    		if(sendflag==1){
        		    	    			//未连接时的，只有第一次找脉才可以取消
                		    	    	if(Ptime==0)
                         		    	buttoncancel.setEnabled(true);
                         		    	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                         		    	listAdapter.add("["+currentDateTimeString+"] Connected--"+"正在找脉中...");
                                	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                	 	
        		    	    		}else{
        		    	    			String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                                		//((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready"+"找脉中...");
                                        listAdapter.add("["+currentDateTimeString+"] 连接失败！请重试或重启设备与软件");
                                   	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                   	 	mService.disconnect();
    	                        		mDevice=null;
    	                        		btnConnectDisconnect.setEnabled(true);
    	                        		
    	                        		timeout=0;
    	                        		ADi=0;
    	                        	    ADbase=0;
    	                        	    ADall=0;
    	                        	    ADcoi=0;
    	                        	    ZMi=0;
    	                        	    pg1.setVisibility(255);
    	                        		pg2.setVisibility(255);
    	                        		pg3.setVisibility(255);
    	                        		pg4.setVisibility(255);
    	                        		pg5.setVisibility(255);
    	                        		pg6.setVisibility(255);
        		    	    		}
        		    	    	}catch(Exception e){
        		    	    		Log.e("MyLog", "send 01 54 error!!");
        		    	    		String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                            		//((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready"+"找脉中...");
                                    listAdapter.add("["+currentDateTimeString+"] 连接失败！请重试或重启设备与软件");
                               	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                               	 	mService.disconnect();
	                        		mDevice=null;
	                        		btnConnectDisconnect.setEnabled(true);
	                        		
	                        		timeout=0;
	                        		ADi=0;
	                        	    ADbase=0;
	                        	    ADall=0;
	                        	    ADcoi=0;
	                        	    ZMi=0;
	                        	    pg1.setVisibility(255);
	                        		pg2.setVisibility(255);
	                        		pg3.setVisibility(255);
	                        		pg4.setVisibility(255);
	                        		pg5.setVisibility(255);
	                        		pg6.setVisibility(255);
        		    	    	}
                 		    	//value[1]=0x46;
                 		    	//mService.writeRXCharacteristic(value);
        		    	    	Log.e("time", "3000end");
                 		    	
        		    	    }    
        		    	 }, 3000);
            		}


	     		    	//Handler hd4=new Handler();
	     		    	hd4.postDelayed(rn4, 4000);
	     		    	
	     		    	//Handler hd5=new Handler();
	     		    	hd5.postDelayed(rn5, 5000);
	     		    	
	     		    	//Handler hd6=new Handler();
	     		    	hd6.postDelayed(rn6, 6000);

                }
            }
        });
        // Handler Send button  
        
        
        
        
        //TODO with send!!!!!!!!!!!!
        //进入问诊页面
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	//EditText editText = (EditText) findViewById(R.id.sendText);
            	//String message = edtMessage.getText().toString();
            	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
        	 	listAdapter.add("["+currentDateTimeString+"] 进入问诊页面");
        	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
            	
        	 	//wb=new webServiceTest();
        	 	
                try {  

                	Intent intent = new Intent();
                    //intent.setClass(WelcomeActivity.this, MainActivity.class);
                    intent.setClass(MainActivity.this, com.nordicsemi.nrfUARTv2.QuestionActivity.class);
                    startActivity(intent);	
                } catch (Exception e) {  
                    Log.e(TAG, e.toString());  
                }
            	
            
                
            }
        });
     
        // Set initial UI state
        
        
        //TODO buttonCancel!!!!
        //取消按钮功能，取消所有正在运行的非主线程
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	buttoncancel.setEnabled(false);
            	//cancel=1;
            	startFind=0;
            	hd4.removeCallbacks(rn4);
            	hd5.removeCallbacks(rn5);
            	hd6.removeCallbacks(rn6);
            	
            	hdf1.removeCallbacks(rnf1);
            	hdf2.removeCallbacks(rnf2);
            	hdf3.removeCallbacks(rnf3);
            	hdf4.removeCallbacks(rnf4);
            	hdf5.removeCallbacks(rnf5);
            	hdf6.removeCallbacks(rnf6);
            	hdf7.removeCallbacks(rnf7);
            	hdf8.removeCallbacks(rnf8);
            	hdf9.removeCallbacks(rnf9);
            	hdf10.removeCallbacks(rnf10);
            	hdf11.removeCallbacks(rnf11);
            	
            	byte[] value={0x01,0x57};
            	try{
		    	mService.writeRXCharacteristic(value);  }
            	catch(Exception e){Log.e("myLog", "no send watch 01 57");}
            	
 
		    	
		    	new Handler().postDelayed(new Runnable(){    
		    	    public void run() {    
		    	    	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
			    		//((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready");
			            listAdapter.add("["+currentDateTimeString+"] 取消成功！");
			       	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
			       	 	if(Ptime==0){
				 	 		tvzuoshouchen.setText("左手浮");
				 	 	}
				 	 	if(Ptime==1){
				 	 		tvzuoshouchen.setText("左手浮-已完成");
				 	 		tvzuoshouzhong.setText("左手中");;
				 	 	}
				 	 	if(Ptime==2){
				 	 		tvzuoshouchen.setText("左手浮-已完成");
				 	 		tvzuoshouzhong.setText("左手中-已完成");;
				 	 		tvzuoshoufu.setText("左手沉");;
				 	 	}
				 	 	if(Ptime==3){
				 	 		tvzuoshouchen.setText("左手浮-已完成");
				 	 		tvzuoshouzhong.setText("左手中-已完成");;
				 	 		tvzuoshoufu.setText("左手沉-已完成");;
				 	 		tvyoushouchen.setText("右手浮");;
				 	 	}
				 	 	if(Ptime==4){
				 	 		tvzuoshouchen.setText("左手浮-已完成");
				 	 		tvzuoshouzhong.setText("左手中-已完成");;
				 	 		tvzuoshoufu.setText("左手沉-已完成");;
				 	 		tvyoushouchen.setText("右手浮-已完成");;
				 	 		tvyoushouzhong.setText("右手中");;
				 	 	}
				 	 	if(Ptime==5){
				 	 		tvzuoshouchen.setText("左手浮-已完成");
				 	 		tvzuoshouzhong.setText("左手中-已完成");;
				 	 		tvzuoshoufu.setText("左手沉-已完成");;
				 	 		tvyoushouchen.setText("右手浮-已完成");;
				 	 		tvyoushouzhong.setText("右手中-已完成");;
				 	 		tvyoushoufu.setText("右手沉");;
				 	 	}
			       	 	//if (mDevice!=null)
			       	 	//	mService.disconnect();  
			       	 btnConnectDisconnect.setEnabled(true);
			       	 
			       	pg1.setVisibility(255);
            		pg2.setVisibility(255);
            		pg3.setVisibility(255);
            		pg4.setVisibility(255);
            		pg5.setVisibility(255);
            		pg6.setVisibility(255);
		    	    }    
		    	 }, 1000); 

            }
        });
     
        // Set initial UI state
        
    }

    //UART service connected/disconnected
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
        		mService = ((UartService.LocalBinder) rawBinder).getService();
        		Log.d(TAG, "onServiceConnected mService= " + mService);
        		if (!mService.initialize()) {
                    Log.e(TAG, "Unable to initialize Bluetooth");
                    finish();
                }

        }

        public void onServiceDisconnected(ComponentName classname) {
       ////     mService.disconnect(mDevice);
        		mService = null;
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        
        //Handler events that received from UART service 
        public void handleMessage(Message msg) {
  
        }
    };

    private final BroadcastReceiver UARTStatusChangeReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            final Intent mIntent = intent;
           //*********************//
            if (action.equals(UartService.ACTION_GATT_CONNECTED)) {
            	 runOnUiThread(new Runnable() {
                     public void run() {
                             mState = UART_PROFILE_CONNECTED;
                     }
            	 });
            }
           
          //*********************//
            if (action.equals(UartService.ACTION_GATT_DISCONNECTED)) {
            	 runOnUiThread(new Runnable() {
                     public void run() {
                             mState = UART_PROFILE_DISCONNECTED;
                             mService.close();
                            //setUiState();
                         
                     }
                 });
            }
            
          
          //*********************//
            if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED)) {
             	 mService.enableTXNotification();
            }
          //*********************//

          //TODO receive!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //接受数据处理，包括找脉与采集脉
            if (action.equals(UartService.ACTION_DATA_AVAILABLE)) {
              
            	
                 final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);
                 if((txValue[0]==0x01 && txValue[1]==0x55 && startFind==1) 
                		 || (timeout==1)
                		 ){

                	 
                	 
                	 
                	 try{
	                	 ADbuff[ADi][0]=((int)(txValue[4]<<8)&0xFF00 | (int)txValue[5]&0xFF);
	                	 ADbuff[ADi][1]=((int)(txValue[6]<<8)&0xFF00 | (int)txValue[7]&0xFF);
	                	 ADbuff[ADi][2]=((int)(txValue[8]<<8)&0xFF00 | (int)txValue[9]&0xFF);
	                	 ADbuff[ADi][3]=((int)(txValue[10]<<8)&0xFF00 | (int)txValue[11]&0xFF);
	                	 ADbuff[ADi][4]=((int)(txValue[12]<<8)&0xFF00 | (int)txValue[13]&0xFF);
	                	 ADi++;
                	 
	                	 if(ADi>=10){
	                		 ADi=0;
	                		 ADbase=1;
	                	 }
                	 }catch(Exception ed){
                		 
                	 }
	                if(ADbase==1){
	                		 try{
	                		 ADall=0;
	                		 for(int i=0;i<10;i++){
	                			 for(int j=0;j<5;j++){
	                				 ADall+=ADbuff[i][j];
	                			 }
	                		 }
	                		 }catch(Exception md){
	                			 
	                		 }

                		 if( 
                				 (timeout==1) ||
                				 (startFind==1&&((((int)(txValue[4]<<8)&0xFF00 | (int)txValue[5]&0xFF)+((int)(txValue[6]<<8)&0xFF00 | (int)txValue[7]&0xFF)+((int)(txValue[8]<<8)&0xFF00 | (int)txValue[9]&0xFF)+((int)(txValue[10]<<8)&0xFF00 | (int)txValue[11]&0xFF)+((int)(txValue[12]<<8)&0xFF00 | (int)txValue[13]&0xFF))-(Math.abs(ADall)/10))>=ZM[ZMi]  
                				 //充气式找脉
                				// || 
                				 //放气式找脉
                				 //((((int)(txValue[4]<<8)&0xFF00 | (int)txValue[5]&0xFF)+((int)(txValue[6]<<8)&0xFF00 | (int)txValue[7]&0xFF)+((int)(txValue[8]<<8)&0xFF00 | (int)txValue[9]&0xFF)+((int)(txValue[10]<<8)&0xFF00 | (int)txValue[11]&0xFF)+((int)(txValue[12]<<8)&0xFF00 | (int)txValue[13]&0xFF))-(Math.abs(ADall)/10))<=-ZM[ZMi]  
                				 )
                				 
                				 ){
                			 
                			 //Log.e("!!!!!!!!!!!!", ""+ZM[ZMi]+"==="+ADall+"===="+(int)(((int)(txValue[4]<<8)&0xFF00 | (int)txValue[5]&0xFF)+((int)(txValue[6]<<8)&0xFF00 | (int)txValue[7]&0xFF)+((int)(txValue[8]<<8)&0xFF00 | (int)txValue[9]&0xFF)+((int)(txValue[10]<<8)&0xFF00 | (int)txValue[11]&0xFF)+((int)(txValue[12]<<8)&0xFF00 | (int)txValue[13]&0xFF)));
                			 timeout=0;
                			 buttoncancel.setEnabled(false);
                			 try{
                				 hd4.removeCallbacks(rn4);
                				 hd5.removeCallbacks(rn5);
                				 hd6.removeCallbacks(rn6);
                			 }catch(Exception ed){
                				 timeout=0;
                			 }
                			 startFind=0;
                			 //cancel=1;
                			 ADi=0;
                			 ADbase=0;
                			 //ADall=0;
                			 //buttoncancel.setEnabled(false);
                				 byte[] value={0x01,0x57};
                				 while(mService.writeRXCharacteristic(value)==0);

             		    	//btnConnectDisconnect.setEnabled(true);
               		    	//((TextView) findViewById(R.id.deviceName)).setText("找脉成功！=="+ADall+"="+(((int)(txValue[4]<<8)&0xFF00 | (int)txValue[5]&0xFF)+((int)(txValue[6]<<8)&0xFF00 | (int)txValue[7]&0xFF)+((int)(txValue[8]<<8)&0xFF00 | (int)txValue[9]&0xFF)+((int)(txValue[10]<<8)&0xFF00 | (int)txValue[11]&0xFF)+((int)(txValue[12]<<8)&0xFF00 | (int)txValue[13]&0xFF)));
               		    	new Handler().postDelayed(new Runnable(){    
            		    	    public void run() {  
    	        		    	    //mService.disconnect();
            		    	    	if(Ptime==0){
            		    	    		byte[] value={0x01,0x36,0x01};
            		    	    		while(mService.writeRXCharacteristic(value)==0);
            		    	    	}else{
            		    	    		byte[] value={0x01,0x36,0x00};
            		    	    		while(mService.writeRXCharacteristic(value)==0);
            		    	    	}
    	        		    	    Log.e("MyLog", "----------"+"Send 0136 start collection");
    	        		    	    String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	                        	 	listAdapter.add("["+currentDateTimeString+"] 开始采集...... ");
	                        	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	                        	 	buttoncancel.setEnabled(false);
	                        	 	//mService.disconnect();
            		    	    }    
            		    	 }, 1000);
             		    	
               		    	
             		    	hdf1.postDelayed(rnf1, 9000);
             		    	hdf2.postDelayed(rnf2, 15000);
             		    	hdf3.postDelayed(rnf3, 21000);
             		    	hdf4.postDelayed(rnf4, 27000);
             		    	hdf5.postDelayed(rnf5, 33000);
             		    	hdf6.postDelayed(rnf6, 39000);
             		    	hdf7.postDelayed(rnf7, 45000);
             		    	hdf8.postDelayed(rnf8, 51000);
             		    	hdf9.postDelayed(rnf9, 58000);
             		    	hdf10.postDelayed(rnf10, 66000);
             		    	hdf11.postDelayed(rnf11, 72000);

                		 }
                		
                		 
                	 }
	                	//TODO delete here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!双向通信找脉
                    	 //byte[] value2={0x01,0x13};
          		    	//mService.writeRXCharacteristic(value2);
                	 
                 }else
                 
                 if(txValue[0]==0x02){
                	 ADco[ADcoi][0][Ptime]=((int)(txValue[3]<<8)&0xFF00 | (int)txValue[4]&0xFF);
                	 ADco[ADcoi][1][Ptime]=((int)(txValue[5]<<8)&0xFF00 | (int)txValue[6]&0xFF);
                	 ADco[ADcoi][2][Ptime]=((int)(txValue[7]<<8)&0xFF00 | (int)txValue[8]&0xFF);
                	 ADco[ADcoi][3][Ptime]=((int)(txValue[9]<<8)&0xFF00 | (int)txValue[10]&0xFF);
                	 ADco[ADcoi][4][Ptime]=((int)(txValue[11]<<8)&0xFF00 | (int)txValue[12]&0xFF);
                	 ADcoi++;
                	 
                	 
                	 //TODO delete here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!双向通信接受
                	 if(ADcoi<2400){
                		 //byte[] value3={0x01,0x14};
                		 //mService.writeRXCharacteristic(value3);
                	 }
      		    	
                	 if(ADcoi%240==0){
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                  	 	listAdapter.add("["+currentDateTimeString+"] "+PStrings[Ptime]+"接受中..."+(int)ADcoi/240+"0%");
                  	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                	 }
                	 
                	 
                	 if(ADcoi>=2400){
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                 	 	listAdapter.add("["+currentDateTimeString+"] "+PStrings[Ptime]+"接受完毕！"+"");
                 	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                 	 	
                 	 	byte[] value2={0x01,0x51};
                 	 	while(mService.writeRXCharacteristic(value2)==0);
                 	 	//bat!
          		    	
          		    	ADcoi=0;
          		    	
                 	 	if(Ptime==0){
            	 	 		tvzuoshouchen.setText("左手浮-已完成");;
            	 	 	}
            	 	 	if(Ptime==1){
            	 	 		tvzuoshouzhong.setText("左手中-已完成");;
            	 	 	}
            	 	 	if(Ptime==2){
            	 	 		tvzuoshoufu.setText("左手沉-已完成");;
            	 	 	}
            	 	 	if(Ptime==3){
            	 	 		tvyoushouchen.setText("右手浮-已完成");;
            	 	 	}
            	 	 	if(Ptime==4){
            	 	 		tvyoushouzhong.setText("右手中-已完成");;
            	 	 	}
            	 	 	if(Ptime==5){
            	 	 		tvyoushoufu.setText("右手沉-已完成");;
            	 	 	}
                 	 	
                 	 	pg1.setVisibility(255);
                		pg2.setVisibility(255);
                		pg3.setVisibility(255);
                		pg4.setVisibility(255);
                		pg5.setVisibility(255);
                		pg6.setVisibility(255);
                 	 	
                 	 	new Handler().postDelayed(new Runnable(){    
        		    	    public void run() {  
        		    	    	//mService.disconnect();
        		    	    	//mService.onConnectionStateChangeDisconnected();
        		    	    	//Log.e("MyLog", "----------"+"After receive-DisConnect!!!");
        		    	    }    
        		    	 }, 500);
         		    	
         		    	new Handler().postDelayed(new Runnable(){    
        		    	    public void run() {   
        		    	    	Ptime++;
        		    	    	
        		    	    	if(Ptime>=6){
        		    	    		Ptime=0;
        		    	    		btnConnectDisconnect.setText(PString[Ptime]);
        		    	    		btnConnectDisconnect.setEnabled(true);

        		    	    	}else{
        		    	    		
        		    	    		
        		    	    		
        		    	    		byte[] value={0x01,0x43};
            					 	try{
            					 		while(mService.writeRXCharacteristic(value)==0);
            					 	}catch(Exception e){
            					 		Log.e("MyLog", "no send 01 43 rec error!");
            					 	}
            					 	
        		    	    	}

        		    	    	/*
	        		    	    	btnConnectDisconnect.setEnabled(true);
	        		    	    	
	        		    	    	Ptime++;
	        		    	    	if(Ptime>=6){
	        		    	    		Ptime=0;
	        		    	    		btnConnectDisconnect.setText(PString[Ptime]);

	        		    	    	}else{
	        		    	    		btnConnectDisconnect.setText(PString[Ptime]);
	        		    	    		if(Ptime!=3)
	        		    	    		btnConnectDisconnect.performClick();
	        		    	    		//一次采集后自动调用click执行下一次找脉
	        		    	    	}
	        		    	    	
	        		    	    	if(ZMi>=3){
	        		    	    		//ZMi=0;
	        		    	    	}
	        		    	    	
	        		    	    	*/
        		    	    	
	        		    	    	
	        		    	    	
	        		    	    	
        		    	    }    
        		    	 }, 1000);
                	 }
                	 /*
                	 else{
                		 
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                  	 	listAdapter.add("["+currentDateTimeString+"] 接受失败！"+"");
                  	 	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                  	 	
                  	 	new Handler().postDelayed(new Runnable(){    
         		    	    public void run() {  
         		    	    	mService.disconnect();
         		    	    	//mService.onConnectionStateChangeDisconnected();
         		    	    	Log.e("MyLog", "----------"+"After receive-DisConnect!!!");
         		    	    }    
         		    	 }, 1000);
          		    	
          		    	new Handler().postDelayed(new Runnable(){    
         		    	    public void run() {    
 	        		    	    	mDevice=null;
 	        		    	    	//mService.disableTXNotification();
 	        		    	    	//mState = UART_PROFILE_DISCONNECTED;
 	        		    	    	btnConnectDisconnect.setEnabled(true);
         		    	    }    
         		    	 }, 2000);
                	 }
                	 */
                 }
                 else if(txValue[0]==0x01 && txValue[1]==0x52){
                	 if(txValue[4]<=10){
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                     	listAdapter.add("["+currentDateTimeString+"] 电池电量不足，请充电！");
                     	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                	 }else if(txValue[4]>=70){
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                      	listAdapter.add("["+currentDateTimeString+"] 电池电量充足！");
                      	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                	 }else{
                		 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                       	listAdapter.add("["+currentDateTimeString+"] 电池电量中等");
                       	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                	 }
                	 
                	 
                	 
                 }
                 
                 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                 //else
                 {
                 
                 
	                 runOnUiThread(new Runnable() {
	                     public void run() {
	                         try {
	                        	 String hex=new String();
	                        	 /*
	                        	 for(reci=0;txValue[reci]!=0;reci++){
	                        		 String hex = Integer.toHexString(txValue[reci] & 0xFF);
	                        	 }
	                        	 */
	                        	 for (int i = 0; i < txValue.length; i++) {    
	                        	     hex += Integer.toHexString(txValue[i] & 0xFF);    
	                        	     if (hex.length() == 1) {    
	                        	       hex = '0' + hex;    
	                        	     }    
	                        	     //System.out.print(hex.toUpperCase() );    
	                        	 }   
	                        	 
	                         	//TODO no show detail RX message!!!
	                         	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	                        	listAdapter.add("["+currentDateTimeString+"] RX: "+hex);
	                        	messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
	                        	
	                         } catch (Exception e) {
	                             Log.e(TAG, e.toString());
	                         }
	                     }
	                 });
                 }
                 
             }
           //*********************//
            if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART)){
            	showMessage("Device doesn't support UART. Disconnecting");
            	mService.disconnect();
            }
            
            
        }
    };

    private void service_init() {
        Intent bindIntent = new Intent(this, UartService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
  
        LocalBroadcastManager.getInstance(this).registerReceiver(UARTStatusChangeReceiver, makeGattUpdateIntentFilter());
    }
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UartService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(UartService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(UartService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
    	 super.onDestroy();
        Log.d(TAG, "onDestroy()");
        
        try {
        	LocalBroadcastManager.getInstance(this).unregisterReceiver(UARTStatusChangeReceiver);
        } catch (Exception ignore) {
            Log.e(TAG, ignore.toString());
        } 
        unbindService(mServiceConnection);
        mService.stopSelf();
        mService= null;
       
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onResume - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
 
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

        case REQUEST_SELECT_DEVICE:
        	//When the DeviceListActivity return, with the selected device address
            if (resultCode == Activity.RESULT_OK && data != null) {
                String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
               
                Log.d(TAG, "... onActivityResultdevice.address==" + mDevice + "mserviceValue" + mService);
                //((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - connecting");
                mService.connect(deviceAddress);
                            

            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
        default:
            Log.e(TAG, "wrong request code");
            break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       
    }

    
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  
    }

    //返回按钮处理
    @Override
    public void onBackPressed() {
    	/*
        if (mState == UART_PROFILE_CONNECTED) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            showMessage("nRFUART's running in background.\n             Disconnect to exit");
        }
        else 
        */
        {
            new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("提示")
            .setMessage("确认退出？")
            .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
   	                finish();
                }
            })
            .setNegativeButton("取消", null)
            .show();
        }
    }
}
