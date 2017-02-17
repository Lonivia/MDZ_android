package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionMei extends Activity {

	private Button btnmeisubmit;
	
	private CheckBox chbmeiduo;
	private CheckBox chbmeishao;
	private CheckBox chbmeiwu;


	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listmei);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnmeisubmit=(Button) findViewById(R.id.buttonmeisubmit);
	        chbmeiduo=(CheckBox)findViewById(R.id.checkBoxmeiduo);
	        chbmeishao=(CheckBox)findViewById(R.id.checkBoxmeishao);
	        chbmeiwu=(CheckBox)findViewById(R.id.checkBoxmeiwu);


	        
	        if(QuestionActivity.quest[12][0]==1){
	        	chbmeiduo.setChecked(true);
	        }else{
	        	chbmeiduo.setChecked(false);
	        }
	        if(QuestionActivity.quest[12][1]==1){
	        	chbmeishao.setChecked(true);
	        }else{
	        	chbmeishao.setChecked(false);
	        }
	        if(QuestionActivity.quest[12][2]==1){
	        	chbmeiwu.setChecked(true);
	        }else{
	        	chbmeiwu.setChecked(false);
	        }


	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnmeisubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbmeiduo.isChecked()){
	            		QuestionActivity.quest[12][0]=1;
	            	}else{
	            		QuestionActivity.quest[12][0]=0;
	            	}
	            	if(chbmeishao.isChecked()){
	            		QuestionActivity.quest[12][1]=1;
	            	}else{
	            		QuestionActivity.quest[12][1]=0;
	            	}
	            	if(chbmeiwu.isChecked()){
	            		QuestionActivity.quest[12][2]=1;
	            	}else{
	            		QuestionActivity.quest[12][2]=0;
	            	}


	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
