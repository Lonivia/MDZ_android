package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionHan extends Activity {

	private Button btnhansubmit;
	
	private CheckBox chbhanduo;
	private CheckBox chbhanshao;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listhan);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnhansubmit=(Button) findViewById(R.id.buttonhansubmit);
	        chbhanduo=(CheckBox)findViewById(R.id.checkBoxhanduo);
	        chbhanshao=(CheckBox)findViewById(R.id.checkBoxhanshao);

	        
	        if(QuestionActivity.quest[17][0]==1){
	        	chbhanduo.setChecked(true);
	        }else{
	        	chbhanduo.setChecked(false);
	        }
	        if(QuestionActivity.quest[17][1]==1){
	        	chbhanshao.setChecked(true);
	        }else{
	        	chbhanshao.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnhansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbhanduo.isChecked()){
	            		QuestionActivity.quest[17][0]=1;
	            	}else{
	            		QuestionActivity.quest[17][0]=0;
	            	}
	            	if(chbhanshao.isChecked()){
	            		QuestionActivity.quest[17][1]=1;
	            	}else{
	            		QuestionActivity.quest[17][1]=0;
	            	}

	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
