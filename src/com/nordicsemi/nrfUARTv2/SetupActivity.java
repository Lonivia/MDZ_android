package com.nordicsemi.nrfUARTv2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SetupActivity extends Activity {

	private Button btnsetupsubmit;
	private EditText tbfu;
	private EditText tbzhong;
	private EditText tbchen;
	private EditText tbchi;
	private EditText tbhuan;
	private EditText tbping;
	private EditText tbshu;
	
	private EditText tbhuan2;
	private EditText tbping2;
	
	
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
	
	
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        setContentView(R.layout.setup);
        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
        layoutParams.gravity=Gravity.TOP;
        layoutParams.y = 200;
        
        btnsetupsubmit=(Button) findViewById(R.id.buttonsetupsubmit);
        tbfu=(EditText) findViewById(R.id.editTextfu);
        tbzhong=(EditText) findViewById(R.id.editTextzhong);
        tbchen=(EditText) findViewById(R.id.editTextchen);
        
        tbchi=(EditText) findViewById(R.id.editTextchi);
        tbhuan=(EditText) findViewById(R.id.editTexthuan);
        tbping=(EditText) findViewById(R.id.editTextping);
        tbshu=(EditText) findViewById(R.id.editTextshu);
        
        tbhuan2=(EditText) findViewById(R.id.editTexthuan2);
        tbping2=(EditText) findViewById(R.id.editTextping2);
        
        tbhuan.setEnabled(false);
        tbping.setEnabled(false);
        tbshu.setEnabled(false);
        
        
        if(readFile("MDZFUSET")!=null){
        	tbfu.setText(readFile("MDZFUSET"));
        }else{
        	tbfu.setText("200");
        }
        if(readFile("MDZZHONGSET")!=null){
        	tbzhong.setText(readFile("MDZZHONGSET"));
        }else{
        	tbzhong.setText("500");
        }
        if(readFile("MDZCHENSET")!=null){
        	tbchen.setText(readFile("MDZCHENSET"));
        }else{
        	tbchen.setText("800");
        }
        
        if(readFile("MDZCHISET")!=null){
        	tbchi.setText(readFile("MDZCHISET"));
        }else{
        	tbchi.setText("50");
        }
        if(readFile("MDZHUANSET")!=null){
        	tbhuan2.setText(readFile("MDZHUANSET"));
        	tbhuan.setText(readFile("MDZCHISET"));
        }else{
        	tbhuan2.setText("60");
        	tbhuan.setText("50");
        }
        if(readFile("MDZPINGSET")!=null){
        	tbping2.setText(readFile("MDZPINGSET"));
        	tbping.setText(readFile("MDZHUANSET"));
        	tbshu.setText(readFile("MDZPINGSET"));
        }else{
        	tbping2.setText("80");
        	tbping.setText("60");
        	tbshu.setText("80");
        }

        tbchi.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				tbhuan.setText(tbchi.getText().toString());
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(isNumeric(tbchi.getText().toString())){
					
				}
			} 
        });
        tbhuan2.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				tbping.setText(tbhuan2.getText().toString());
			}
			@Override
			public void afterTextChanged(Editable s) {
			} 
        });
        tbping2.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				tbshu.setText(tbping2.getText().toString());
			}
			@Override
			public void afterTextChanged(Editable s) {
			} 
        });
        
        btnsetupsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(isNumeric(tbfu.getText().toString()) && isNumeric(tbzhong.getText().toString())
            	&& isNumeric(tbchen.getText().toString()) && isNumeric(tbchi.getText().toString())
            	&& isNumeric(tbhuan2.getText().toString()) && isNumeric(tbping2.getText().toString())){
            		if(Integer.parseInt(tbhuan2.getText().toString())>Integer.parseInt(tbchi.getText().toString())
            		&& Integer.parseInt(tbping2.getText().toString())>Integer.parseInt(tbhuan2.getText().toString())){
            			
            			writeFile(tbfu.getText().toString() ,"MDZFUSET");
            			writeFile(tbzhong.getText().toString() ,"MDZZHONGSET");
            			writeFile(tbchen.getText().toString() ,"MDZCHENSET");
            			
            			MainActivity.ZM[2]=Integer.parseInt(tbchen.getText().toString());
            			MainActivity.ZM[1]=Integer.parseInt(tbzhong.getText().toString());
            			MainActivity.ZM[0]=Integer.parseInt(tbfu.getText().toString());
            			
            			writeFile(tbchi.getText().toString() ,"MDZCHISET");
            			writeFile(tbhuan2.getText().toString() ,"MDZHUANSET");
            			writeFile(tbping2.getText().toString() ,"MDZPINGSET");
            			
            			finish();
            		}else{
            			AlertDialog.Builder builder = new Builder(SetupActivity.this);
                        builder.setMessage("请输入正确的参数区间");
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
            		AlertDialog.Builder builder = new Builder(SetupActivity.this);
                    builder.setMessage("请确保输入的参数为数字");
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
	
            	
            }
            
    	});
        
	}
	
}
