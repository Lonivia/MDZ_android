package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionKe extends Activity {
	private Button btnkesubmit;
	
	private CheckBox chbkeduo;
	private CheckBox chbkeshao;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listke);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnkesubmit=(Button) findViewById(R.id.buttonkesubmit);
	        chbkeduo=(CheckBox)findViewById(R.id.checkBoxkeduo);
	        chbkeshao=(CheckBox)findViewById(R.id.checkBoxkeshao);

	        
	        if(QuestionActivity.quest[15][0]==1){
	        	chbkeduo.setChecked(true);
	        }else{
	        	chbkeduo.setChecked(false);
	        }
	        if(QuestionActivity.quest[15][1]==1){
	        	chbkeshao.setChecked(true);
	        }else{
	        	chbkeshao.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnkesubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbkeduo.isChecked()){
	            		QuestionActivity.quest[15][0]=1;
	            	}else{
	            		QuestionActivity.quest[15][0]=0;
	            	}
	            	if(chbkeshao.isChecked()){
	            		QuestionActivity.quest[15][1]=1;
	            	}else{
	            		QuestionActivity.quest[15][1]=0;
	            	}

	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
