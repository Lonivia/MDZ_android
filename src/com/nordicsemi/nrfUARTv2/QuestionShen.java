package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionShen extends Activity {


	private Button btnshensubmit;
	
	private CheckBox chbshenre;
	private CheckBox chbshenhan;
	private CheckBox chbshenlen;
	private CheckBox chbshenfa;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listshen);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnshensubmit=(Button) findViewById(R.id.buttonshensubmit);
	        chbshenre=(CheckBox)findViewById(R.id.checkBoxshenre);
	        chbshenhan=(CheckBox)findViewById(R.id.checkBoxshenhan);
	        chbshenlen=(CheckBox)findViewById(R.id.checkBoxshenlen);
	        chbshenfa=(CheckBox)findViewById(R.id.checkBoxshenfa);

	        
	        if(QuestionActivity.quest[11][0]==1){
	        	chbshenre.setChecked(true);
	        }else{
	        	chbshenre.setChecked(false);
	        }
	        if(QuestionActivity.quest[11][1]==1){
	        	chbshenhan.setChecked(true);
	        }else{
	        	chbshenhan.setChecked(false);
	        }
	        if(QuestionActivity.quest[11][2]==1){
	        	chbshenlen.setChecked(true);
	        }else{
	        	chbshenlen.setChecked(false);
	        }
	        if(QuestionActivity.quest[11][3]==1){
	        	chbshenfa.setChecked(true);
	        }else{
	        	chbshenfa.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnshensubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbshenre.isChecked()){
	            		QuestionActivity.quest[11][0]=1;
	            	}else{
	            		QuestionActivity.quest[11][0]=0;
	            	}
	            	if(chbshenhan.isChecked()){
	            		QuestionActivity.quest[11][1]=1;
	            	}else{
	            		QuestionActivity.quest[11][1]=0;
	            	}
	            	if(chbshenlen.isChecked()){
	            		QuestionActivity.quest[11][2]=1;
	            	}else{
	            		QuestionActivity.quest[11][2]=0;
	            	}
	            	if(chbshenfa.isChecked()){
	            		QuestionActivity.quest[11][3]=1;
	            	}else{
	            		QuestionActivity.quest[11][3]=0;
	            	}

	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
