package com.nordicsemi.nrfUARTv2;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends Activity {
	static webServiceTest wb;
	 Handler net1=new Handler();
	 Handler net2=new Handler();
	 Handler net3=new Handler();
	 Handler net4=new Handler();
	 Handler net5=new Handler();
	 
	 Handler net6=new Handler();
	 Handler net7=new Handler();
	 Handler net8=new Handler();
	 Handler net9=new Handler();

	
	public static Context sContext;
	
	public static String checkPatientNamereturn="999";
	public static String checkPatientPasswordreturn="999";
	public static String modifyPatientOfDiagreturn="999";
	public static String AddPatientreturn="999";
	
	public static String huanzheshouji="999";
	public static String huanzhexingbie="无";
	public static String huanzhenianling="999";

	
	
	
	
	
	public static Button btnhuanzhebaocun;
	public static Button btnhuanzhezhuce;
	public static Button btnbubaocuntuichu;
	
	private static EditText ethuanzheyonghuming;
	private static EditText ethuanzhemima;
	private static EditText ethuanzheshoujihao;
	//private static EditText ethuanzhexingbie;
	private static EditText ethuanzhenianling;
	
	private static CheckBox chbnan;
	private static CheckBox chbnv;
	
	int conti=1;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sContext = getApplicationContext();
        
        setContentView(R.layout.save);
        
        btnhuanzhebaocun=(Button) findViewById(R.id.buttonhuanzhebaocun);
        btnhuanzhezhuce=(Button) findViewById(R.id.buttonhuanzhezhuce);
        btnbubaocuntuichu=(Button) findViewById(R.id.buttonbubaocuntuichu);
        
        ethuanzheyonghuming=(EditText) findViewById(R.id.editTexthuanzhexingming);
        ethuanzhemima=(EditText) findViewById(R.id.editTexthuanzhemima);
        ethuanzheshoujihao=(EditText) findViewById(R.id.editTexthuanzheshouji);
        //ethuanzhexingbie=(EditText) findViewById(R.id.editTexthuanzhexingbie);
        ethuanzhenianling=(EditText) findViewById(R.id.editTexthuanzhenianling);
        chbnan=(CheckBox) findViewById(R.id.checkBoxnan);
        chbnv=(CheckBox) findViewById(R.id.checkBoxnv);

        
        Start();
        
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
	public void Start() {
		
		chbnan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					chbnv.setChecked(false);
				} else {
					
				}
			}
		});
		chbnv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					chbnan.setChecked(false);
				} else {
					
				}
			}
		});
		
		
		
		
		
		btnhuanzhebaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.e("Save", "huanzhebaocun");
            	conti=1;
            	checkPatientNamereturn="999";
            	checkPatientPasswordreturn="999";
            	modifyPatientOfDiagreturn="999";
            	AddPatientreturn="999";
            	
            	
            	btnhuanzhebaocun.setEnabled(false);
            	if(ethuanzheyonghuming.getText().toString().length()<=8 && ethuanzheyonghuming.getText().toString().length()>=1){
            		if(ethuanzhemima.getText().toString().length()<=16 && ethuanzhemima.getText().toString().length()>=1){
            			if( ethuanzheshoujihao.getText().toString().equals("")  ||(ethuanzheshoujihao.getText().toString().length()==11 && isNumeric(ethuanzheshoujihao.getText().toString()))){
            				
            				if(true){
            					if(chbnan.isChecked()){
            						huanzhexingbie="男";
            					}else if(chbnv.isChecked()){
            						huanzhexingbie="女";
            					}else{
            						huanzhexingbie="无";
            					}
            					
            					if(  ethuanzhenianling.getText().toString().equals("") || (isNumeric(ethuanzhenianling.getText().toString()) && ethuanzhenianling.getText().toString().length()<=3 && ethuanzhenianling.getText().toString().length()>=1)){
            						Log.e("Save", "baocun_ready!!");
            						if(ethuanzhenianling.getText().toString().equals("")){
            							huanzhenianling="998";
            						}else{
        		    	    			huanzhenianling=ethuanzhenianling.getText().toString();
        		    	    		}
            					
            						net1.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	wb=new webServiceTest();
    		    	    	            	checkPatientNamereturn=wb.checkPatientName(ethuanzheyonghuming.getText().toString());
    		    	    			   
                    		    	    }    
                    		    	 }, 500);
            						
            						net2.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && checkPatientNamereturn.substring(0,1).equals("1")){
    		    	    			    		checkPatientPasswordreturn=wb.checkPatientPassword(SaveActivity.ethuanzheyonghuming.getText().toString(), SaveActivity.ethuanzhemima.getText().toString());
    		    	    			    		Log.e("save-checkPatientPasswordreturn", checkPatientPasswordreturn);
    		    	    			    	}else{
    		    	    			    		conti=0;
    		                    				//Toast.makeText(SaveActivity.sContext, "用户名不存在，请先注册", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("用户名不存在，请先注册");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    		    	    			    		btnhuanzhebaocun.setEnabled(true);
    		                    				
    		    	    			    	}
                    		    	    }    
                    		    	 }, 1000);
            						
            						net3.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && checkPatientPasswordreturn.equals("0")){
                    		    	    		if(ethuanzheshoujihao.getText().toString().equals("")){
                    		    	    			huanzheshouji="999";
                    		    	    		}else{
                    		    	    			huanzheshouji=ethuanzheshoujihao.getText().toString();
                    		    	    		}
    		    	    		    			AddPatientreturn=wb.AddPatient(ethuanzheyonghuming.getText().toString(), huanzheshouji, ethuanzhemima.getText().toString(), Integer.parseInt(huanzhenianling),huanzhexingbie);
    		    	    		    			Log.e("save-addpatient", AddPatientreturn);
    		    	    			    	}else if (conti==1){
    		    	    			    		conti=0;
    		                    				//Toast.makeText(SaveActivity.sContext, "密码错误", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("密码错误");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    		    	    			    		btnhuanzhebaocun.setEnabled(true);
    		    	    			    	}
                    		    	    }    
                    		    	 }, 1500);
            						net4.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && !AddPatientreturn.equals("0") && !AddPatientreturn.equals("999")){
                    		    	    		modifyPatientOfDiagreturn=wb.modifyPatientOfDiag();
    		    	    		    			Log.e("save-modifyPatient", modifyPatientOfDiagreturn);
    		    	    			    	}else if (conti==1){
    		    	    			    		conti=0;
    		                    				//Toast.makeText(SaveActivity.sContext, "密码错误", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("保存失败");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    		    	    			    		btnhuanzhebaocun.setEnabled(true);
    		    	    			    	}
                    		    	    }    
                    		    	 }, 2000);
            						net5.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && modifyPatientOfDiagreturn.equals("1")){
                    		    	    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("保存成功");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                   btnhuanzhebaocun.setEnabled(true);
    	        	 		                                   finish();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    		    	    			    		
    		    	    			    	}else if (conti==1){
    		                    				//Toast.makeText(SaveActivity.sContext, "密码错误", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("保存失败!!");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    		    	    			    		btnhuanzhebaocun.setEnabled(true);
    		    	    			    	}
                    		    	    }    
                    		    	 }, 2500);
            						

            		    	
            						
            					}else{
                    				Toast.makeText(SaveActivity.this, "年龄输入有误", Toast.LENGTH_SHORT).show();
                    				btnhuanzhebaocun.setEnabled(true);
            					}
            				}else{
                				Toast.makeText(SaveActivity.this, "性别输入有误", Toast.LENGTH_SHORT).show();
                				btnhuanzhebaocun.setEnabled(true);
            				}
            			}else{
            				Toast.makeText(SaveActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
            				btnhuanzhebaocun.setEnabled(true);
            			}
            		}else{
            			Toast.makeText(SaveActivity.this, "密码不能超过16位且不能为空", Toast.LENGTH_SHORT).show();
            			btnhuanzhebaocun.setEnabled(true);
            		}
            	}else{
            		Toast.makeText(SaveActivity.this, "用户名不能超过8位且不能为空", Toast.LENGTH_SHORT).show();
            		btnhuanzhebaocun.setEnabled(true);
            	}
            }
            
    	});
		btnhuanzhezhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.e("Save", "huanzhezhuce");
            	
            	conti=1;
            	checkPatientNamereturn="999";
            	checkPatientPasswordreturn="999";
            	modifyPatientOfDiagreturn="999";
            	AddPatientreturn="999";
            	btnhuanzhezhuce.setEnabled(false);
            	if(ethuanzheyonghuming.getText().toString().length()<=8 && ethuanzheyonghuming.getText().toString().length()>=1){
            		if(ethuanzhemima.getText().toString().length()<=16 && ethuanzhemima.getText().toString().length()>=1){
            			if( ethuanzheshoujihao.getText().toString().equals("")  || (ethuanzheshoujihao.getText().toString().length()==11 && isNumeric(ethuanzheshoujihao.getText().toString()))){
            				
            				if(true){
            					if(chbnan.isChecked()){
            						huanzhexingbie="男";
            					}else if(chbnv.isChecked()){
            						huanzhexingbie="女";
            					}else{
            						huanzhexingbie="无";
            					}
            					
            					if(ethuanzhenianling.getText().toString().equals("") || (isNumeric(ethuanzhenianling.getText().toString()) && ethuanzhenianling.getText().toString().length()<=3 && ethuanzhenianling.getText().toString().length()>=1)){
            						Log.e("Save", "zhuce_ready!!");
            						if(ethuanzhenianling.getText().toString().equals("")){
            							huanzhenianling="998";
            						}else{
        		    	    			huanzhenianling=ethuanzhenianling.getText().toString();
        		    	    		}
            						
            						
            						net6.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	wb=new webServiceTest();
    		    	    	            	checkPatientNamereturn=wb.checkPatientName(ethuanzheyonghuming.getText().toString());
    		    	    			   
                    		    	    }    
                    		    	 }, 500);
            						
            						net7.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && checkPatientNamereturn.substring(0,1).equals("0")){
                    		    	    		if(ethuanzheshoujihao.getText().toString().equals("")){
                    		    	    			huanzheshouji="999";
                    		    	    		}else{
                    		    	    			huanzheshouji=ethuanzheshoujihao.getText().toString();
                    		    	    		}
                    		    	    		AddPatientreturn=wb.AddPatient(ethuanzheyonghuming.getText().toString(), huanzheshouji, ethuanzhemima.getText().toString(), Integer.parseInt(huanzhenianling), huanzhexingbie);
    		    	    		    			Log.e("save-addpatient", AddPatientreturn);
    		    	    			    	}else{
    		    	    			    		conti=0;
    		    	    			    		if(checkPatientNamereturn.substring(2,5).equals("999")){
    		    	    			    			AlertDialog.Builder builder = new Builder(SaveActivity.this);
	    	        	 		                    builder.setMessage("用户名已被占用，手机号为空，请换个用户名，或直接登陆");
	    	        	 		                    builder.setTitle("提示");
	    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	    	        	 		                    builder.setPositiveButton("确定",
	    	        	 		                            new DialogInterface.OnClickListener() {
	    	        	 		                                public void onClick(DialogInterface dialog, int which) {
	    	        	 		                                    dialog.dismiss();
	    	        	 		                                }
	    	        	 		                            });
	    	        	 		                    builder.create().show();
	    		    	    			    		btnhuanzhezhuce.setEnabled(true);
    		    	    			    		}else{
    		                    				//Toast.makeText(SaveActivity.sContext, "用户名不存在，请先注册", Toast.LENGTH_SHORT).show();
	    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
	    	        	 		                    builder.setMessage("用户名已被占用，手机号为"+checkPatientNamereturn.substring(2,11)+"**，请换个用户名，或直接登陆");
	    	        	 		                    builder.setTitle("提示");
	    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
	    	        	 		                    builder.setPositiveButton("确定",
	    	        	 		                            new DialogInterface.OnClickListener() {
	    	        	 		                                public void onClick(DialogInterface dialog, int which) {
	    	        	 		                                    dialog.dismiss();
	    	        	 		                                }
	    	        	 		                            });
	    	        	 		                    builder.create().show();
	    		    	    			    		btnhuanzhezhuce.setEnabled(true);
    		    	    			    		}
    		                    				
    		    	    			    	}
                    		    	    }    
                    		    	 }, 1000);
            						
            						
            						net8.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && !"0".equals(AddPatientreturn) && !AddPatientreturn.equals("999")){
                    		    	    		modifyPatientOfDiagreturn=wb.modifyPatientOfDiag();
    		    	    		    			Log.e("save-modifyPatient", modifyPatientOfDiagreturn);
    		    	    			    	}else if (conti==1){
    		    	    			    		conti=0;
    		                    				//Toast.makeText(SaveActivity.sContext, "密码错误", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("注册失败");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    	        	 		                   btnhuanzhezhuce.setEnabled(true);
    		    	    			    	}
                    		    	    }    
                    		    	 }, 1500);
            						net9.postDelayed(new Runnable(){    
                    		    	    public void run() {    
                    		    	    	if(conti==1 && "1".equals(modifyPatientOfDiagreturn)){
                    		    	    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("注册成功，保存成功");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                   btnhuanzhezhuce.setEnabled(true);
    	        	 		                                  finish();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    	        	 		                   
    		    	    			    		//finish();
    		    	    			    	}else if (conti==1){
    		                    				//Toast.makeText(SaveActivity.sContext, "密码错误", Toast.LENGTH_SHORT).show();
    		    	    			    		AlertDialog.Builder builder = new Builder(SaveActivity.this);
    	        	 		                    builder.setMessage("注册成功，保存失败!");
    	        	 		                    builder.setTitle("提示");
    	        	 		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    	        	 		                    builder.setPositiveButton("确定",
    	        	 		                            new DialogInterface.OnClickListener() {
    	        	 		                                public void onClick(DialogInterface dialog, int which) {
    	        	 		                                    dialog.dismiss();
    	        	 		                                }
    	        	 		                            });
    	        	 		                    builder.create().show();
    	        	 		                   btnhuanzhezhuce.setEnabled(true);
    		    	    			    	}
                    		    	    }    
                    		    	 }, 2000);
            						

            		    	
            						
            					}else{
                    				Toast.makeText(SaveActivity.this, "年龄输入有误", Toast.LENGTH_SHORT).show();
                    				btnhuanzhezhuce.setEnabled(true);
            					}
            				}else{
                				Toast.makeText(SaveActivity.this, "性别输入有误", Toast.LENGTH_SHORT).show();
                				btnhuanzhezhuce.setEnabled(true);
            				}
            			}else{
            				Toast.makeText(SaveActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
            				btnhuanzhezhuce.setEnabled(true);
            			}
            		}else{
            			Toast.makeText(SaveActivity.this, "密码不能超过16位且不能为空", Toast.LENGTH_SHORT).show();
            			btnhuanzhezhuce.setEnabled(true);
            		}
            	}else{
            		Toast.makeText(SaveActivity.this, "用户名不能超过8位且不能为空", Toast.LENGTH_SHORT).show();
            		btnhuanzhezhuce.setEnabled(true);
            	}
            	
            	
            }
            
    	});
		btnbubaocuntuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.e("Save", "bubaocuntuichu");
            	finish();
            	
            }
            
    	});
	}
	
	
	
	


}
