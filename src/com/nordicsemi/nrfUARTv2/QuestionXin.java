package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionXin extends Activity {

	private Button btnxinsubmit;
	
	private CheckBox chbxinfan;
	private CheckBox chbxinzao;
	private CheckBox chbxinre;
	private CheckBox chbxintong;

	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listxin);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnxinsubmit=(Button) findViewById(R.id.buttonxinsubmit);
	        chbxinfan=(CheckBox)findViewById(R.id.checkBoxxinfan);
	        chbxinzao=(CheckBox)findViewById(R.id.checkBoxxinzao);
	        chbxinre=(CheckBox)findViewById(R.id.checkBoxxinre);
	        chbxintong=(CheckBox)findViewById(R.id.checkBoxxintong);

	        
	        if(QuestionActivity.quest[8][0]==1){
	        	chbxinfan.setChecked(true);
	        }else{
	        	chbxinfan.setChecked(false);
	        }
	        if(QuestionActivity.quest[8][1]==1){
	        	chbxinzao.setChecked(true);
	        }else{
	        	chbxinzao.setChecked(false);
	        }
	        if(QuestionActivity.quest[8][2]==1){
	        	chbxinre.setChecked(true);
	        }else{
	        	chbxinre.setChecked(false);
	        }
	        if(QuestionActivity.quest[8][3]==1){
	        	chbxintong.setChecked(true);
	        }else{
	        	chbxintong.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnxinsubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbxinfan.isChecked()){
	            		QuestionActivity.quest[8][0]=1;
	            	}else{
	            		QuestionActivity.quest[8][0]=0;
	            	}
	            	if(chbxinzao.isChecked()){
	            		QuestionActivity.quest[8][1]=1;
	            	}else{
	            		QuestionActivity.quest[8][1]=0;
	            	}
	            	if(chbxinre.isChecked()){
	            		QuestionActivity.quest[8][2]=1;
	            	}else{
	            		QuestionActivity.quest[8][2]=0;
	            	}
	            	if(chbxintong.isChecked()){
	            		QuestionActivity.quest[8][3]=1;
	            	}else{
	            		QuestionActivity.quest[8][3]=0;
	            	}

	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
