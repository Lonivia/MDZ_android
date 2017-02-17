package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionZhi extends Activity {

	private Button btnzhisubmit;
	
	private CheckBox chbzhilen;
	private CheckBox chbzhisuan;
	private CheckBox chbzhima;
	private CheckBox chbzhizhong;
	private CheckBox chbzhitong;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listzhi);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnzhisubmit=(Button) findViewById(R.id.buttonzhisubmit);
	        chbzhilen=(CheckBox)findViewById(R.id.checkBoxzhilen);
	        chbzhisuan=(CheckBox)findViewById(R.id.checkBoxzhisuan);
	        chbzhima=(CheckBox)findViewById(R.id.checkBoxzhima);
	        chbzhizhong=(CheckBox)findViewById(R.id.checkBoxzhizhong);
	        chbzhitong=(CheckBox)findViewById(R.id.checkBoxzhitong);
	        
	        if(QuestionActivity.quest[13][0]==1){
	        	chbzhilen.setChecked(true);
	        }else{
	        	chbzhilen.setChecked(false);
	        }
	        if(QuestionActivity.quest[13][1]==1){
	        	chbzhisuan.setChecked(true);
	        }else{
	        	chbzhisuan.setChecked(false);
	        }
	        if(QuestionActivity.quest[13][2]==1){
	        	chbzhima.setChecked(true);
	        }else{
	        	chbzhima.setChecked(false);
	        }
	        if(QuestionActivity.quest[13][3]==1){
	        	chbzhizhong.setChecked(true);
	        }else{
	        	chbzhizhong.setChecked(false);
	        }
	        if(QuestionActivity.quest[13][4]==1){
	        	chbzhitong.setChecked(true);
	        }else{
	        	chbzhitong.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnzhisubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbzhilen.isChecked()){
	            		QuestionActivity.quest[13][0]=1;
	            	}else{
	            		QuestionActivity.quest[13][0]=0;
	            	}
	            	if(chbzhisuan.isChecked()){
	            		QuestionActivity.quest[13][1]=1;
	            	}else{
	            		QuestionActivity.quest[13][1]=0;
	            	}
	            	if(chbzhima.isChecked()){
	            		QuestionActivity.quest[13][2]=1;
	            	}else{
	            		QuestionActivity.quest[13][2]=0;
	            	}
	            	if(chbzhizhong.isChecked()){
	            		QuestionActivity.quest[13][3]=1;
	            	}else{
	            		QuestionActivity.quest[13][3]=0;
	            	}
	            	if(chbzhitong.isChecked()){
	            		QuestionActivity.quest[13][4]=1;
	            	}else{
	            		QuestionActivity.quest[13][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
