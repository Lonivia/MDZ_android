package com.nordicsemi.nrfUARTv2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class SelectActivity extends Activity {
	
    private BluetoothAdapter mBtAdapter = null;

	public static String addr1 =null;
	public static String addr2 =null;
	public static String account =null;
	public static String account_pwd =null;

	public static String docid="0";
	
	public int choose=-1;
	
	private Button button1;
	private static Button button2;
	private Button button3;
	private CheckBox chb1;
	private CheckBox chb2;
	
	private EditText edt1;
	private EditText edt2;
	
	final static String FOLDER = "/MDZfile/";   
	//final static String FILENAME = "config";    
	final static String SUFFIX = ".mdz";
	//CSVWriter writer = null;

	
	webServiceTest wb0;
	Thread net1=new Thread();
	
	
	
	
	
	
	//TODO write file!
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
	
	//TODO read file!!!
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
	            // TODO Auto-generated catch block   
	            e1.printStackTrace();   
	            Log.d("SelectLog", "Error: Input File not find!");   
	            return null;   
	        }   
	  
	        CharBuffer cb;   
	        try {   
	            cb = CharBuffer.allocate(fis.available());   
	        } catch (IOException e1) {   
	            // TODO Auto-generated catch block   
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
	            // TODO Auto-generated catch block   
	            e.printStackTrace();           
	        }   
	    }   
	    Log.d("SelectLog", "readFile filecontent = " + filecontent);   
	    return filecontent;   
	} 
	

	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        chb1=(CheckBox) findViewById(R.id.checkBox1);
        chb2=(CheckBox) findViewById(R.id.checkBox2);
        chb1.setEnabled(false);
        chb2.setEnabled(false);
        //button2.setEnabled(false);
        edt1=(EditText) findViewById(R.id.editText1);
        edt2=(EditText) findViewById(R.id.editText2);
        
        if(readFile("MDZNconfig")!=null){
        	edt1.setText(readFile("MDZNconfig"));
        	//addr1=readFile("MDZWconfig");
        	//chb1.setEnabled(true);
        	//chb1.setChecked(true);
        }
        if(readFile("MDZPconfig")!=null){
        	edt2.setText(readFile("MDZPconfig"));
        	//addr1=readFile("MDZWconfig");
        	//chb1.setEnabled(true);
        	//chb1.setChecked(true);
        }

        if(readFile("MDZWconfig")!=null){
        	chb1.setText(readFile("MDZWconfig"));
        	addr1=readFile("MDZWconfig");
        	chb1.setEnabled(true);
        	chb1.setChecked(true);
        }
        if(readFile("MDZBconfig")!=null){
        	chb2.setText(readFile("MDZBconfig"));
        	addr2=readFile("MDZBconfig");
        	chb2.setEnabled(true);
        	chb2.setChecked(true);
        }
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        
        
        Start();
    }
  
	
    public void Start() {
    	button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (!mBtAdapter.isEnabled()) {
                    Log.i("SelectLog", "onClick - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, 2);
                }
                else {

            	choose=1;
                Intent newIntent = new Intent(SelectActivity.this, DeviceListActivity.class);
    			startActivityForResult(newIntent, 1);
            	
                }
            	//button2.setEnabled(true);
            }
            
    	});
    	
    	button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (!mBtAdapter.isEnabled()) {
                    Log.i("SelectLog", "onClick - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, 2);
                }
                else {

            	choose=2;
                Intent newIntent = new Intent(SelectActivity.this, DeviceListActivity.class);
    			startActivityForResult(newIntent, 1);
            	
                }
            	//button2.setEnabled(true);
            }
            
    	});
    	
    	button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//if(chb1.isChecked() && chb2.isChecked()){
            	button2.setEnabled(false);
            	if(chb1.isChecked()){
            		Log.e("111", "1111");
            		if(!edt1.getText().toString().equals("") && !edt2.getText().toString().equals("")){
            			account=edt1.getText().toString();
            			account_pwd=edt2.getText().toString();
		                writeFile(account,"MDZNconfig");
		                writeFile(account_pwd,"MDZPconfig");
		                button2.setText("正在登陆...");
		                Log.e("333", "3333");
		                
		                net1 = new Thread(new Runnable() {
	                	    @Override
	                	            public void run() {
	                	    	
	                	    	Looper.prepare();
	                	    
	        	    			new Handler().postDelayed(new Runnable(){    
	        	    			    public void run() {    
	        	    			    //execute the task  
	        	    			    	 wb0=new webServiceTest();
	        	 		                
	        	 		                try{
	        	 		                	
	        	 		                	docid=wb0.doctorLogin();
	        	 		                	}
	        	 		                catch(Exception ed){
	        	 		                    
	        	 		                	docid="ex";
	        	 		                }
	        	 		                
	        	 		                if(docid==null){
	        	 		                	docid="nu";
	        	 		                }
	        	 		                
	        	 		               Log.e("login", docid);
	        	 		               
	        	 		                
	        	 		                if(!docid.equals("0") && !docid.equals("nu")&& !docid.equals("ex")){
	        	 		                	
	        	 		                	Message msg = new Message();  
	 		                                  msg.what = 6;  
	 		                                 SelectActivity.handler.sendMessage(msg);
	        	 		                	AlertDialog.Builder builder = new Builder(SelectActivity.this);
	        	 		                    builder.setMessage("登陆成功！开始前请确保设备已打开且未连接（红色指示灯亮）");
	        	 		                    builder.setTitle("提示");
	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	        	 		                    builder.setPositiveButton("确定",
	        	 		                            new DialogInterface.OnClickListener() {
	        	 		                                public void onClick(DialogInterface dialog, int which) {
	        	 		                                    dialog.dismiss();
	        	 		                                    Intent intent = new Intent();
	        	 		                                    //intent.setClass(WelcomeActivity.this, MainActivity.class);
	        	 		                                    intent.setClass(SelectActivity.this, com.nordicsemi.nrfUARTv2.MainActivity.class);
	        	 		                                    startActivity(intent);
	        	 		                                    SelectActivity.this.finish();
	        	 		                                }
	        	 		                            });
	        	 		                    builder.create().show();
	        	 		                }
	        	 		                else{
	        	 		                
	        	 		                	Message msg = new Message();  
	 		                                  msg.what = 6;  
	 		                                 SelectActivity.handler.sendMessage(msg);
	        	 			                AlertDialog.Builder builder = new Builder(SelectActivity.this);
	        	 		                    builder.setMessage("登录失败，请检查网络与用户名密码是否正确。");
	        	 		                    builder.setTitle("提示");
	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	        	 		                    builder.setPositiveButton("确定",
	        	 		                            new DialogInterface.OnClickListener() {
	        	 		                                public void onClick(DialogInterface dialog, int which) {
	        	 		                                    dialog.dismiss();
	        	 		                                   Message msg = new Message();  
	        	 		                                  msg.what = 6;  
	        	 		                                 SelectActivity.handler.sendMessage(msg);
	        	 		                                }
	        	 		                            });
	        	 		                    builder.create().show();
	        	 		                }
	        
	        	    			    }    
	        	    			 },500); 
	        	    			Looper.loop();
	        	    			
	                	    }
	        	        });
	                	//Looper.loop();
	        	    net1.start(); 
	        	    
	        	    
	        	    
	        	    
		               
	                    
            		}else{
            			Message msg = new Message();  
                         msg.what = 6;  
                        SelectActivity.handler.sendMessage(msg);
            			Log.e("222", "2222");
            			AlertDialog.Builder builder = new Builder(SelectActivity.this);
                        builder.setMessage("用户名或密码不能为空");
                        builder.setTitle("提示");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.create().show();
            		}

            	}else{
            		Message msg = new Message();  
                     msg.what = 6;  
                    SelectActivity.handler.sendMessage(msg);
            		Log.e("Waring!", "Please Select Right Device!");
            		AlertDialog.Builder builder = new Builder(SelectActivity.this);
                    builder.setMessage("您未选择正确的设备");
                    builder.setTitle("提示");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setPositiveButton("返回",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
            	}
            	
            	
            	
            }
            
    	});
    }
    
   
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == Activity.RESULT_OK && data != null) {
            	
            	if(choose==1){
	                String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
	                if(data.getStringExtra(BluetoothDevice.EXTRA_NAME).equals("MDZW")){
	                	addr1=deviceAddress;
		                chb1.setText(deviceAddress);
		                chb1.setEnabled(true);
		                writeFile(deviceAddress,"MDZWconfig");
	                }else{
	                	AlertDialog.Builder builder = new Builder(SelectActivity.this);
	                    builder.setMessage("您未选择正确的手表设备");
	                    builder.setTitle("提示");
	                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	                    builder.setPositiveButton("返回",
	                            new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int which) {
	                                    dialog.dismiss();
	                                }
	                            });
	                    builder.create().show();
	                }
	                
            	}else if(choose==2){
            		String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
            		if(data.getStringExtra(BluetoothDevice.EXTRA_NAME).equals("MDZB")){
            			addr2=deviceAddress;
    	                chb2.setText(deviceAddress);
    	                chb2.setEnabled(true);
    	                writeFile(deviceAddress,"MDZBconfig");
	                }else{
	                	AlertDialog.Builder builder = new Builder(SelectActivity.this);
	                    builder.setMessage("您未选择正确的腰带设备");
	                    builder.setTitle("提示");
	                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	                    builder.setPositiveButton("返回",
	                            new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int which) {
	                                    dialog.dismiss();
	                                }
	                            });
	                    builder.create().show();
	                }
            	}
            }
    } 
    public static Handler handler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 6) {  
            	button2.setEnabled(true);
                 button2.setText("开始诊脉");
            }  

        }  
    };
    
}
