package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionYan extends Activity {

	
	private Button btnyansubmit;
	
	private CheckBox chbyantong;
	private CheckBox chbyanzao;
	private CheckBox chbyanhong;
	private CheckBox chbyanzhong;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listyan);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnyansubmit=(Button) findViewById(R.id.buttonyansubmit);
	        chbyantong=(CheckBox)findViewById(R.id.checkBoxyantong);
	        chbyanzao=(CheckBox)findViewById(R.id.checkBoxyanzao);
	        chbyanhong=(CheckBox)findViewById(R.id.checkBoxyanhong);
	        chbyanzhong=(CheckBox)findViewById(R.id.checkBoxyanzhong);

	        
	        if(QuestionActivity.quest[5][0]==1){
	        	chbyantong.setChecked(true);
	        }else{
	        	chbyantong.setChecked(false);
	        }
	        if(QuestionActivity.quest[5][1]==1){
	        	chbyanzao.setChecked(true);
	        }else{
	        	chbyanzao.setChecked(false);
	        }
	        if(QuestionActivity.quest[5][2]==1){
	        	chbyanhong.setChecked(true);
	        }else{
	        	chbyanhong.setChecked(false);
	        }
	        if(QuestionActivity.quest[5][3]==1){
	        	chbyanzhong.setChecked(true);
	        }else{
	        	chbyanzhong.setChecked(false);
	        }

	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnyansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbyantong.isChecked()){
	            		QuestionActivity.quest[5][0]=1;
	            	}else{
	            		QuestionActivity.quest[5][0]=0;
	            	}
	            	if(chbyanzao.isChecked()){
	            		QuestionActivity.quest[5][1]=1;
	            	}else{
	            		QuestionActivity.quest[5][1]=0;
	            	}
	            	if(chbyanhong.isChecked()){
	            		QuestionActivity.quest[5][2]=1;
	            	}else{
	            		QuestionActivity.quest[5][2]=0;
	            	}
	            	if(chbyanzhong.isChecked()){
	            		QuestionActivity.quest[5][3]=1;
	            	}else{
	            		QuestionActivity.quest[5][3]=0;
	            	}

	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
