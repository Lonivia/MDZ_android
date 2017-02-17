package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

    private Button btnresultsubmit;
	
	private TextView tvmaixiang;

	//private TextView[] tvzhengzhuang=new TextView[6];

	//private TextView[] tvyaopin=new TextView[6];

	String[] strs=new String[3];
	String titleData[][] = new String[6][6];
	
	EditText suggest;
	
	CheckBox[] checkbox = new CheckBox[6];
	
	private ScrollView sc;
	
	webServiceTest wb2;
	
	Thread net1=new Thread();
	
	String finalreturn;
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.result);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        sc=(ScrollView)findViewById(R.id.sc);
	        
	        TableLayout layout = (TableLayout)findViewById(R.id.TableLayout01);  
	        //全部列自动填充空白处  
	        //layout.setStretchAllColumns(true);  
	        layout.setColumnShrinkable(3, true); //设置长句的一列为伸展

	        
	        btnresultsubmit=(Button) findViewById(R.id.buttonresultsubmit);
	        tvmaixiang=(TextView)findViewById(R.id.textViewmaixiangshow);
	        
	        suggest=(EditText)findViewById(R.id.editTextsuggest);
	        
	        
	        //tvzhengzhuang[0]=(TextView)findViewById(R.id.textViewzhengzhuangshow);
	        //tvzhengzhuang[1]=(TextView)findViewById(R.id.textViewzhengzhuangshow1);
	        //tvzhengzhuang[2]=(TextView)findViewById(R.id.textViewzhengzhuangshow2);
	        //tvzhengzhuang[3]=(TextView)findViewById(R.id.textViewzhengzhuangshow3);
	        //tvzhengzhuang[4]=(TextView)findViewById(R.id.textViewzhengzhuangshow4);
	        //tvzhengzhuang[5]=(TextView)findViewById(R.id.textViewzhengzhuangshow5);
	        
	        //tvyaopin[0]=(TextView)findViewById(R.id.textViewyaopinshow);
	        //tvyaopin[1]=(TextView)findViewById(R.id.textViewyaopinshow1);
	        //tvyaopin[2]=(TextView)findViewById(R.id.textViewyaopinshow2);
	        //tvyaopin[3]=(TextView)findViewById(R.id.textViewyaopinshow3);
	        //tvyaopin[4]=(TextView)findViewById(R.id.textViewyaopinshow4);
	        //tvyaopin[5]=(TextView)findViewById(R.id.textViewyaopinshow5);
	        
	        for(int ini=0;ini<6;ini++){
	        	for(int inj=0;inj<6;inj++){
	        		titleData[ini][inj]="";
	        	}
	        }
	        
	        
	        
	        try{
	        	strs=QuestionActivity.resF.split("%");
	        }catch(Exception e){
	        	/*
	        	AlertDialog.Builder builder = new Builder(ResultActivity.this);
                builder.setMessage("网络连接失败，请重试");
                builder.setTitle("提示");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            	Message msg = new Message();  
                                msg.what = 8;  
                                QuestionActivity.handler.sendMessage(msg);
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
                */
	        	Message msg = new Message();  
	            msg.what = 7;  
	            QuestionActivity.handler.sendMessage(msg);
	        	finish();
	        }
	        
	        if(strs.length>=1){
	        	tvmaixiang.setText(strs[0]);
	        }
	        
	        
	        if(strs.length>=2){
	        	//tvzhengzhuang[0].setText(strs[1]);
	        	try{
		        	strs[1] = strs[1].replace("\r\n", "\n");
		        	String[] symptoms = strs[1].split("\n");
		        	for(int ij=0;ij<symptoms.length && ij<6;ij++){
		        		String[] str1=symptoms[ij].split("A|B|C|D");
		        		//tvzhengzhuang[ij].setText("["+(ij+1)+"]"+str1[0]+"1\n"+str1[1]+"2\n"+str1[2]+"3\n"+str1[3]);
		        		//titleData[ij][0]=str1[0]+"0|0";
		        		titleData[ij][1]=str1[1]+"        "+str1[3]+" ";;		//第一列 诊断属性（四字）+第三列症状
		        		titleData[ij][2]=str1[2]+" ";		//第二列 诊断术语（长句）
		        		titleData[ij][4]=" "+str1[3]+" "; //第三列 症状!  不显示bug！！！
		        		titleData[ij][3]=str1[0]+" ";  //无显示！
		        		
		        	}
		        	Log.e("RES!!", "str2-symptoms!!"+symptoms.length);
	        	}catch(Exception estr2){
	        		Log.e("strs2", estr2.toString());
	        	}
	        }
	        if(strs.length>=3){
	        	//tvyaopin.setText(strs[2]);
	        	try{
		        	String[] drugsOfResult = new String[strs.length - 2];
	                for (int i1 = 2; i1 < strs.length; i1++)
	                {
	                    drugsOfResult[i1 - 2] = strs[i1];
	                    
	                }
	                Log.e("RES!!", "str3-strslength!!"+strs.length);
	                
	                for(int jj=0;jj<drugsOfResult.length && jj<6;jj++){
	                	//tvyaopin[jj].setText("["+(jj+1)+"]"+drugsOfResult[jj]); 
	                	titleData[jj][5]=" "+drugsOfResult[jj]; //药品！
	                	
	                }
	                Log.e("RES!!", "str3-drugofres!!"+drugsOfResult.length);
	        	}catch(Exception estr3){
	        		Log.e("str3", estr3.toString());
	        	}
	        	
	        }
	        
	        
	        
	        
	        for (int x =-1; x < this.strs.length - 2; x++) { // 循环设置表格行
	        	TableRow row = new TableRow(this); // 定义表格行
	        	if(x==-1){
	        		TextView text1 = new TextView(this);
                	text1.setMaxLines(10);
                	text1.setText("辩证-医学教育");
                	//add textview click and underline
                	text1.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
                	text1.setOnClickListener(new OnClickListener() {
                		@Override
                		public void onClick(View arg0) {
                			Uri uri = Uri.parse("http://121.40.53.156/mDoctor/");
                			Intent it = new Intent(Intent.ACTION_VIEW, uri);
                			startActivity(it);
                			Log.e("resultAct", "textview1");
                		}
                	});
                	row.addView(text1, 0); // 加入一个编号
                	
                	TextView text2 = new TextView(this);
                	text2.setMaxLines(10);
                	text2.setText("病症-营养保健");
                	text2.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
                	text2.setOnClickListener(new OnClickListener() {
                		@Override
                		public void onClick(View arg0) {
                			Uri uri = Uri.parse("http://121.40.53.156/mDoctor/");
                			Intent it = new Intent(Intent.ACTION_VIEW, uri);
                			startActivity(it);
                			Log.e("resultAct", "textview2");
                		}
                	});
                	row.addView(text2, 1); // 加入一个编号
                	
                	TextView text3 = new TextView(this);
                	text3.setMaxLines(10);
                	text3.setText("");
                	row.addView(text3, 2); // 加入一个编号
                	
                	TextView text4 = new TextView(this);
                	text4.setMaxLines(10);
                	text4.setText("");
                	row.addView(text4, 3); // 加入一个编号
                	
                	TextView text5 = new TextView(this);
                	text5.setMaxLines(10);
                	text5.setText("药品-辩证医学 ");
                	text5.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
                	text5.setOnClickListener(new OnClickListener() {
                		@Override
                		public void onClick(View arg0) {
                			Uri uri = Uri.parse("http://121.40.53.156/mDoctor/");
                			Intent it = new Intent(Intent.ACTION_VIEW, uri);
                			startActivity(it);
                			Log.e("resultAct", "textview5");
                		}
                	});
                	row.addView(text5, 4); // 加入一个编号
                	
                	TextView text6 = new TextView(this);
                	text6.setMaxLines(10);
                	text6.setText("选择");
                	row.addView(text6, 5); // 加入一个编号
                
                	
	        		layout.addView(row); // 向表格之中增加若干个表格行
	        		Log.e("RES!!", "add a -1 row!!");
	        	}else{
		            
		            for (int y = 1; y < 7; y++) {
		                
		                
		                if(y==6){
		                	checkbox[x] = new CheckBox(ResultActivity.this);
		                	row.addView(checkbox[x]);
		                	Log.e("RES!!", "add a checkbox!!");
		                }else{
		                	TextView text = new TextView(this);
		                	text.setMaxLines(10);
			                text.setText(this.titleData[x][y]); // 设置文本内容
			                row.addView(text, y-1); // 加入一个编号
		                }
		                //checkbox[x] = new CheckBox(ResultActivity.this);
			            //layout.addView(checkbox[x]);
		            }
		            
		            layout.addView(row); // 向表格之中增加若干个表格行
		            Log.e("RES!!", "add a row!!"+this.titleData.length);
	            
	        	}
	            

	        }
	        
	        for(int q=0;q<20;q++){
            	for(int p=0;p<5;p++){
            		QuestionActivity.quest[q][p]=0;
            	}
            }
	        
	        onStart();
	 }
	 
	 
	 //按下确定和返回时，都会改变前页问诊页面的灰色正在提交属性变为可提交
	 //TODO when see over the result , ex.send to the net?
	 public void onStart() {
	        super.onStart();
	        //sc.fullScroll(ScrollView.FOCUS_UP);//back to the top! BUT USELESS ?
	        
	        btnresultsubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	//Message msg = new Message();  
                    //msg.what = 8;  
                    //QuestionActivity.handler.sendMessage(msg);
	            	String select="";
	            	for (int x = 0; x < strs.length - 2; x++) {
	            		if(checkbox[x].isChecked()){
	            			select+=x+",";
	            		}
	            	}
	            	if(!select.equals("")){
	            		select.substring(0,select.length()-1);
	            	}
	            	
	            	String opinion="";
	            	opinion=suggest.getText().toString();
	            	
	            	final String finalopinion=opinion;
	            	final String finalselect=select;
	            	
	            	btnresultsubmit.setEnabled(false);
	            	btnresultsubmit.setText("正在保存...");

	            	
	            	//TODO wb2.function
	            	
	            	net1 = new Thread(new Runnable() {
	            	    @Override
	            	            public void run() {
	            	    	
	            	    	Looper.prepare();
	            	    
	    	    			new Handler().postDelayed(new Runnable(){    
	    	    			    public void run() {    
	    	    			    //execute the task  
	    	    	            	wb2=new webServiceTest();
	    	    	            	finalreturn=wb2.uploadDoctorDiag( finalselect,finalopinion);
	    	    			    }    
	    	    			 },500); 
	    	    			
	    	    			new Handler().postDelayed(new Runnable(){    
	    	    			    public void run() {    
	    	    			    //execute the task  
	    	    			    	if(finalreturn.equals("1")){
	    	    			    		showsuccess();
	    	    			    		
	    	    			    		
	    	    			    		Intent intent = new Intent();
		                                //intent.setClass(WelcomeActivity.this, MainActivity.class);
		                                intent.setClass(ResultActivity.this, SaveActivity.class);
		                                startActivity(intent);
		                                ResultActivity.this.finish();
	    	    			    		
	    	    			    		
	    	    			    		//finish();
	    	    			    	}else{
	    	    			    		btnresultsubmit.setEnabled(true);
	    	    		            	btnresultsubmit.setText("上传失败，请重试");
	    	    			    	}
	    	    			    	
	    	    			    }    
	    	    			 },3000); 
	    	    			Looper.loop();
	            	    }
	    	        });
	            	//Looper.loop();
	    	    net1.start(); 
	    	    
	    	    
	    	    
	            	
	            }
	            
	    	});
	        
	 }
	 
	 @Override
	 public void onBackPressed() {
		 Message msg = new Message();  
         msg.what = 8;  
         QuestionActivity.handler.sendMessage(msg);
         finish();
	 }

	    
	    public void showsuccess(){
	    	Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
	    }
	    

	
}
