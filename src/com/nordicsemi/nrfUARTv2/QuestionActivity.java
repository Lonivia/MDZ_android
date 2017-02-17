package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuestionActivity extends Activity {

	
	public static int quest[][] =new int[20][5];
	
	public static String resF;
	public static Button btnsubmit;
	
	public Button btnkou;
	public Button btner;
	public Button btnbi;
	public Button btntou;
	public Button btnshe;
	public Button btnyan;
	public Button btnmian;
	public Button btnxiong;
	public Button btnxin;
	public Button btnfu;
	public Button btnyao;
	public Button btnshen;
	public Button btnmei;
	public Button btnzhi;
	public Button btnguanjie;
	public Button btnke;
	public Button btntan;
	public Button btnhan;
	public Button btnxiaobian;
	public Button btndabian;


	webServiceTest wb;
	Thread net1=new Thread();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        btnsubmit=(Button) findViewById(R.id.buttonsubmit);
        
        btnkou=(Button) findViewById(R.id.buttonkou);
        btner=(Button) findViewById(R.id.Buttoner);
        btnbi=(Button) findViewById(R.id.Buttonbi);
        btntou=(Button) findViewById(R.id.Buttontou);
        btnshe=(Button) findViewById(R.id.Buttonshe);
        btnyan=(Button) findViewById(R.id.Buttonyan);
        btnmian=(Button) findViewById(R.id.Buttonmian);
        btnxiong=(Button) findViewById(R.id.Buttonxiong);
        btnxin=(Button) findViewById(R.id.Buttonxin);
        btnfu=(Button) findViewById(R.id.Buttonfu);
        btnyao=(Button) findViewById(R.id.Buttonyao);
        btnshen=(Button) findViewById(R.id.Buttonshen);
        btnmei=(Button) findViewById(R.id.Buttonmei);
        btnzhi=(Button) findViewById(R.id.Buttonzhi);
        btnguanjie=(Button) findViewById(R.id.Buttonguanjie);
        btnke=(Button) findViewById(R.id.Buttonke);
        btntan=(Button) findViewById(R.id.Buttontan);
        btnhan=(Button) findViewById(R.id.Buttonhan);
        btnxiaobian=(Button) findViewById(R.id.Buttonxiaobian);
        btndabian=(Button) findViewById(R.id.Buttondabian);
        
        Log.e("ques", "start!");
        
        for(int q=0;q<20;q++){
        	for(int p=0;p<5;p++){
        		quest[q][p]=0;
        	}
        }
        
        Start();
        
	}
	public void Start() {
		btnkou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.e("ques", "start!kou!");
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionKou.class);
    			startActivity(newIntent);
            }
            
    	});
		btner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionEr.class);
    			startActivity(newIntent);
            }
            
    	});
		btnbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionBi.class);
    			startActivity(newIntent);
            }
            
    	});
		btntou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionTou.class);
    			startActivity(newIntent);
            }
            
    	});
		btnshe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionShe.class);
    			startActivity(newIntent);
            }
            
    	});
		btnyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionYan.class);
    			startActivity(newIntent);
            }
            
    	});
		btnmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionMian.class);
    			startActivity(newIntent);
            }
            
    	});
		btnxiong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionXiong.class);
    			startActivity(newIntent);
            }
            
    	});
		btnxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionXin.class);
    			startActivity(newIntent);
            }
            
    	});
		btnfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionFu.class);
    			startActivity(newIntent);
            }
            
    	});
		btnyao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionYao.class);
    			startActivity(newIntent);
            }
            
    	});
		btnshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionShen.class);
    			startActivity(newIntent);
            }
            
    	});
		btnmei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionMei.class);
    			startActivity(newIntent);
            }
            
    	});
		btnzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionZhi.class);
    			startActivity(newIntent);
            }
            
    	});
		btnguanjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionGuanjie.class);
    			startActivity(newIntent);
            }
            
    	});
		btnke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionKe.class);
    			startActivity(newIntent);
            }
            
    	});
		btntan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionTan.class);
    			startActivity(newIntent);
            }
            
    	});
		btnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionHan.class);
    			startActivity(newIntent);
            }
            
    	});
		btnxiaobian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionXiaobian.class);
    			startActivity(newIntent);
            }
            
    	});
		btndabian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent newIntent = new Intent(QuestionActivity.this, QuestionDabian.class);
    			startActivity(newIntent);
            }
            
    	});
		
		//处理网络连接部分
		//TODO net
		btnsubmit.setOnClickListener(new View.OnClickListener() {
			
            @Override
            public void onClick(View v) {
            	btnsubmit.setEnabled(false);
            	btnsubmit.setText("提交中，请稍后...");
            	
            	wb=new webServiceTest();
            	
            	net1 = new Thread(new Runnable() {
            	    @Override
            	            public void run() {
            	    	
            	    	Looper.prepare();
            	    
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,0,1);
    	    			    }    
    	    			 },300); 
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,0,2);
    	    			    }    
    	    			 },600); 
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,0,3);
    	    			    }    
    	    			 },900); 
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,0,4);
    	    			    }    
    	    			 },1200); 
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,0,5);
    	    			    }    
    	    			 },1500); 
    	    			
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,1,1);
    	    			    }    
    	    			 },1800);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,1,2);
    	    			    }    
    	    			 },2100);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,1,3);
    	    			    }    
    	    			 },2400);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,1,4);
    	    			    }    
    	    			 },2700);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,1,5);
    	    			    }    
    	    			 },3000);
    	    			 
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,2,1);
    	    			    }    
    	    			 },3300);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,2,2);
    	    			    }    
    	    			 },3600);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,2,3);
    	    			    }    
    	    			 },3900);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,2,4);
    	    			    }    
    	    			 },4200);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,2,5);
    	    			    }    
    	    			 },4500);

    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,3,1);
    	    			    }    
    	    			 },4800);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,3,2);
    	    			    }    
    	    			 },5100);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,3,3);
    	    			    }    
    	    			 },5400);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,3,4);
    	    			    }    
    	    			 },5700);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,3,5);
    	    			    }    
    	    			 },6000);
    	    			
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,4,1);
    	    			    }    
    	    			 },6300);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,4,2);
    	    			    }    
    	    			 },6600);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,4,3);
    	    			    }    
    	    			 },6900);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,4,4);
    	    			    }    
    	    			 },7200);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,4,5);
    	    			    }    
    	    			 },7500);
    	    			
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,5,1);
    	    			    }    
    	    			 },7800);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,5,2);
    	    			    }    
    	    			 },8100);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,5,3);
    	    			    }    
    	    			 },8400);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,5,4);
    	    			    }    
    	    			 },8700);
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	
    	    			    	wb.uploadData(MainActivity.ADco,5,5);
    	    			    }    
    	    			 },9000);
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	wb.setDiag3();
    	    			    }    
    	    			 },9500);
    	    			
    	    			new Handler().postDelayed(new Runnable(){    
    	    			    public void run() {    
    	    			    //execute the task  
    	    			    	resF=wb.getNewestDiagResult();
    	                        
    	    			    	
    	    			    	Intent intent = new Intent();
    	                        //intent.setClass(WelcomeActivity.this, MainActivity.class);
    	                        intent.setClass(QuestionActivity.this, ResultActivity.class);
    	                        startActivity(intent);	
    	                        
    	                        Message msg = new Message();  
    	                        msg.what = 8;  
    	                        QuestionActivity.handler.sendMessage(msg);
    	    			    	/*
    	    			    	AlertDialog.Builder builder = new Builder(QuestionActivity.this);
    		                    builder.setMessage(resF);
    		                    builder.setTitle("诊断结果");
    		                    builder.setIcon(android.R.drawable.ic_dialog_alert);
    		                    builder.setPositiveButton("确定",
    		                            new DialogInterface.OnClickListener() {
    		                                public void onClick(DialogInterface dialog, int which) {
    		                                	//QuestionActivity.btnsubmit.setEnabled(true);
    		                                	//QuestionActivity.btnsubmit.setText("提交");
    		                                	Message msg = new Message();  
    		                                    msg.what = 8;  
    		                                    handler.sendMessage(msg);
    		                                    dialog.dismiss();
    		                                }
    		                            });
    		                    builder.create().show();
    		                    */
    	    			    }  
    	    			    
    	    			    
    	    			 },10500);
    	    			
    	    			
    	    			Looper.loop();
    	    			
            	    }
    	        });
            	//Looper.loop();
    	    net1.start(); 
            }
            
    	});
		
	}
	public static Handler handler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 8) {  
            	QuestionActivity.btnsubmit.setEnabled(true);
            	QuestionActivity.btnsubmit.setText("提交");
            }  
            if (msg.what == 7) {  
            	QuestionActivity.btnsubmit.setEnabled(true);
            	QuestionActivity.btnsubmit.setText("网络连接失败，请重试");
            }
            
            
            
            
            
        }  
    };
    
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
            //TODO back button
        	if(btnsubmit.getText().equals("提交")){
        		super.onBackPressed();  
        	}else{
        		Toast.makeText(this, "请耐心等待诊断结果...", Toast.LENGTH_SHORT).show();
        	}
        }
    }
    
    
}
