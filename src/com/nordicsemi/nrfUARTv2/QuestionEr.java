package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionEr extends Activity {

	private Button btnersubmit;
	
	private CheckBox chberming;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.lister);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnersubmit=(Button) findViewById(R.id.buttonersubmit);
	        chberming=(CheckBox)findViewById(R.id.checkBoxerming);

	        
	        if(QuestionActivity.quest[1][0]==1){
	        	chberming.setChecked(true);
	        }else{
	        	chberming.setChecked(false);
	        }
	        
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnersubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chberming.isChecked()){
	            		QuestionActivity.quest[1][0]=1;
	            	}else{
	            		QuestionActivity.quest[1][0]=0;
	            	}
	            	
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
	
}
