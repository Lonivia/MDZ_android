package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionBi extends Activity {

	private Button btnbisubmit;
	
	private CheckBox chbbisai;
	private CheckBox chbbiti;
	private CheckBox chbbigan;
	private CheckBox chbbihuang;
	private CheckBox chbbishan;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listbi);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnbisubmit=(Button) findViewById(R.id.buttonbisubmit);
	        chbbisai=(CheckBox)findViewById(R.id.checkBoxbisai);
	        chbbiti=(CheckBox)findViewById(R.id.checkBoxbiti);
	        chbbigan=(CheckBox)findViewById(R.id.checkBoxbigan);
	        chbbihuang=(CheckBox)findViewById(R.id.checkBoxbihuang);
	        chbbishan=(CheckBox)findViewById(R.id.checkBoxbishan);
	        
	        if(QuestionActivity.quest[2][0]==1){
	        	chbbisai.setChecked(true);
	        }else{
	        	chbbisai.setChecked(false);
	        }
	        if(QuestionActivity.quest[2][1]==1){
	        	chbbiti.setChecked(true);
	        }else{
	        	chbbiti.setChecked(false);
	        }
	        if(QuestionActivity.quest[2][2]==1){
	        	chbbigan.setChecked(true);
	        }else{
	        	chbbigan.setChecked(false);
	        }
	        if(QuestionActivity.quest[2][3]==1){
	        	chbbihuang.setChecked(true);
	        }else{
	        	chbbihuang.setChecked(false);
	        }
	        if(QuestionActivity.quest[2][4]==1){
	        	chbbishan.setChecked(true);
	        }else{
	        	chbbishan.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnbisubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbbisai.isChecked()){
	            		QuestionActivity.quest[2][0]=1;
	            	}else{
	            		QuestionActivity.quest[2][0]=0;
	            	}
	            	if(chbbiti.isChecked()){
	            		QuestionActivity.quest[2][1]=1;
	            	}else{
	            		QuestionActivity.quest[2][1]=0;
	            	}
	            	if(chbbigan.isChecked()){
	            		QuestionActivity.quest[2][2]=1;
	            	}else{
	            		QuestionActivity.quest[2][2]=0;
	            	}
	            	if(chbbihuang.isChecked()){
	            		QuestionActivity.quest[2][3]=1;
	            	}else{
	            		QuestionActivity.quest[2][3]=0;
	            	}
	            	if(chbbishan.isChecked()){
	            		QuestionActivity.quest[2][4]=1;
	            	}else{
	            		QuestionActivity.quest[2][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
