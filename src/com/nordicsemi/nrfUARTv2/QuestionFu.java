package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionFu extends Activity {

	private Button btnfusubmit;
	
	private CheckBox chbfuxie;
	private CheckBox chbfuman;
	private CheckBox chbfuzhang;
	private CheckBox chbfutong;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listfu);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnfusubmit=(Button) findViewById(R.id.buttonfusubmit);
	        chbfuxie=(CheckBox)findViewById(R.id.checkBoxfuxie);
	        chbfuman=(CheckBox)findViewById(R.id.checkBoxfuman);
	        chbfuzhang=(CheckBox)findViewById(R.id.checkBoxfuzhang);
	        chbfutong=(CheckBox)findViewById(R.id.checkBoxfutong);

	        
	        if(QuestionActivity.quest[9][0]==1){
	        	chbfuxie.setChecked(true);
	        }else{
	        	chbfuxie.setChecked(false);
	        }
	        if(QuestionActivity.quest[9][1]==1){
	        	chbfuman.setChecked(true);
	        }else{
	        	chbfuman.setChecked(false);
	        }
	        if(QuestionActivity.quest[9][2]==1){
	        	chbfuzhang.setChecked(true);
	        }else{
	        	chbfuzhang.setChecked(false);
	        }
	        if(QuestionActivity.quest[9][3]==1){
	        	chbfutong.setChecked(true);
	        }else{
	        	chbfutong.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnfusubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbfuxie.isChecked()){
	            		QuestionActivity.quest[9][0]=1;
	            	}else{
	            		QuestionActivity.quest[9][0]=0;
	            	}
	            	if(chbfuman.isChecked()){
	            		QuestionActivity.quest[9][1]=1;
	            	}else{
	            		QuestionActivity.quest[9][1]=0;
	            	}
	            	if(chbfuzhang.isChecked()){
	            		QuestionActivity.quest[9][2]=1;
	            	}else{
	            		QuestionActivity.quest[9][2]=0;
	            	}
	            	if(chbfutong.isChecked()){
	            		QuestionActivity.quest[9][3]=1;
	            	}else{
	            		QuestionActivity.quest[9][3]=0;
	            	}

	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
