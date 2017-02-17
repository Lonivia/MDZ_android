package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionXiong extends Activity {
	private Button btnxiongsubmit;
	
	private CheckBox chbxiongtong;
	private CheckBox chbxiongmen;
	private CheckBox chbxiongman;
	private CheckBox chbxiongzhang;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listxiong);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnxiongsubmit=(Button) findViewById(R.id.buttonxiongsubmit);
	        chbxiongtong=(CheckBox)findViewById(R.id.checkBoxxiongtong);
	        chbxiongmen=(CheckBox)findViewById(R.id.checkBoxxiongmen);
	        chbxiongman=(CheckBox)findViewById(R.id.checkBoxxiongman);
	        chbxiongzhang=(CheckBox)findViewById(R.id.checkBoxxiongzhang);

	        
	        if(QuestionActivity.quest[7][0]==1){
	        	chbxiongtong.setChecked(true);
	        }else{
	        	chbxiongtong.setChecked(false);
	        }
	        if(QuestionActivity.quest[7][1]==1){
	        	chbxiongmen.setChecked(true);
	        }else{
	        	chbxiongmen.setChecked(false);
	        }
	        if(QuestionActivity.quest[7][2]==1){
	        	chbxiongman.setChecked(true);
	        }else{
	        	chbxiongman.setChecked(false);
	        }
	        if(QuestionActivity.quest[7][3]==1){
	        	chbxiongzhang.setChecked(true);
	        }else{
	        	chbxiongzhang.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnxiongsubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbxiongtong.isChecked()){
	            		QuestionActivity.quest[7][0]=1;
	            	}else{
	            		QuestionActivity.quest[7][0]=0;
	            	}
	            	if(chbxiongmen.isChecked()){
	            		QuestionActivity.quest[7][1]=1;
	            	}else{
	            		QuestionActivity.quest[7][1]=0;
	            	}
	            	if(chbxiongman.isChecked()){
	            		QuestionActivity.quest[7][2]=1;
	            	}else{
	            		QuestionActivity.quest[7][2]=0;
	            	}
	            	if(chbxiongzhang.isChecked()){
	            		QuestionActivity.quest[7][3]=1;
	            	}else{
	            		QuestionActivity.quest[7][3]=0;
	            	}

	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
