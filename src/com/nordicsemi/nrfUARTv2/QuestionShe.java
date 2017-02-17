package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionShe extends Activity {

	private Button btnshesubmit;
	
	private CheckBox chbshedan;
	private CheckBox chbshezi;
	private CheckBox chbshehong;
	private CheckBox chbshetai;
	private CheckBox chbshebai;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listshe);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnshesubmit=(Button) findViewById(R.id.buttonshesubmit);
	        chbshedan=(CheckBox)findViewById(R.id.checkBoxshedan);
	        chbshezi=(CheckBox)findViewById(R.id.checkBoxshezi);
	        chbshehong=(CheckBox)findViewById(R.id.checkBoxshehong);
	        chbshetai=(CheckBox)findViewById(R.id.checkBoxshetai);
	        chbshebai=(CheckBox)findViewById(R.id.checkBoxshebai);
	        
	        if(QuestionActivity.quest[4][0]==1){
	        	chbshedan.setChecked(true);
	        }else{
	        	chbshedan.setChecked(false);
	        }
	        if(QuestionActivity.quest[4][1]==1){
	        	chbshezi.setChecked(true);
	        }else{
	        	chbshezi.setChecked(false);
	        }
	        if(QuestionActivity.quest[4][2]==1){
	        	chbshehong.setChecked(true);
	        }else{
	        	chbshehong.setChecked(false);
	        }
	        if(QuestionActivity.quest[4][3]==1){
	        	chbshetai.setChecked(true);
	        }else{
	        	chbshetai.setChecked(false);
	        }
	        if(QuestionActivity.quest[4][4]==1){
	        	chbshebai.setChecked(true);
	        }else{
	        	chbshebai.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnshesubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbshedan.isChecked()){
	            		QuestionActivity.quest[4][0]=1;
	            	}else{
	            		QuestionActivity.quest[4][0]=0;
	            	}
	            	if(chbshezi.isChecked()){
	            		QuestionActivity.quest[4][1]=1;
	            	}else{
	            		QuestionActivity.quest[4][1]=0;
	            	}
	            	if(chbshehong.isChecked()){
	            		QuestionActivity.quest[4][2]=1;
	            	}else{
	            		QuestionActivity.quest[4][2]=0;
	            	}
	            	if(chbshetai.isChecked()){
	            		QuestionActivity.quest[4][3]=1;
	            	}else{
	            		QuestionActivity.quest[4][3]=0;
	            	}
	            	if(chbshebai.isChecked()){
	            		QuestionActivity.quest[4][4]=1;
	            	}else{
	            		QuestionActivity.quest[4][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
