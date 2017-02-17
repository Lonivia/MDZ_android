package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionDabian extends Activity {

	private Button btndabiansubmit;
	
	private CheckBox chbdabiangan;
	private CheckBox chbdabianxue;
	private CheckBox chbdabiannian;
	private CheckBox chbdabianxi;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listdabian);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btndabiansubmit=(Button) findViewById(R.id.buttondabiansubmit);
	        chbdabiangan=(CheckBox)findViewById(R.id.checkBoxdabiangan);
	        chbdabianxue=(CheckBox)findViewById(R.id.checkBoxdabianxue);
	        chbdabiannian=(CheckBox)findViewById(R.id.checkBoxdabiannian);
	        chbdabianxi=(CheckBox)findViewById(R.id.checkBoxdabianxi);

	        
	        if(QuestionActivity.quest[19][0]==1){
	        	chbdabiangan.setChecked(true);
	        }else{
	        	chbdabiangan.setChecked(false);
	        }
	        if(QuestionActivity.quest[19][1]==1){
	        	chbdabianxue.setChecked(true);
	        }else{
	        	chbdabianxue.setChecked(false);
	        }
	        if(QuestionActivity.quest[19][2]==1){
	        	chbdabiannian.setChecked(true);
	        }else{
	        	chbdabiannian.setChecked(false);
	        }
	        if(QuestionActivity.quest[19][3]==1){
	        	chbdabianxi.setChecked(true);
	        }else{
	        	chbdabianxi.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btndabiansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbdabiangan.isChecked()){
	            		QuestionActivity.quest[19][0]=1;
	            	}else{
	            		QuestionActivity.quest[19][0]=0;
	            	}
	            	if(chbdabianxue.isChecked()){
	            		QuestionActivity.quest[19][1]=1;
	            	}else{
	            		QuestionActivity.quest[19][1]=0;
	            	}
	            	if(chbdabiannian.isChecked()){
	            		QuestionActivity.quest[19][2]=1;
	            	}else{
	            		QuestionActivity.quest[19][2]=0;
	            	}
	            	if(chbdabianxi.isChecked()){
	            		QuestionActivity.quest[19][3]=1;
	            	}else{
	            		QuestionActivity.quest[19][3]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
