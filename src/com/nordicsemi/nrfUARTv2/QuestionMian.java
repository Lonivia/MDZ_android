package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionMian extends Activity {

	private Button btnmiansubmit;
	
	private CheckBox chbmianhuang;
	private CheckBox chbmianhong;
	private CheckBox chbmianbai;
	private CheckBox chbmianan;
	private CheckBox chbmianzhong;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listmian);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnmiansubmit=(Button) findViewById(R.id.buttonmiansubmit);
	        chbmianhuang=(CheckBox)findViewById(R.id.checkBoxmianhuang);
	        chbmianhong=(CheckBox)findViewById(R.id.checkBoxmianhong);
	        chbmianbai=(CheckBox)findViewById(R.id.checkBoxmianbai);
	        chbmianan=(CheckBox)findViewById(R.id.checkBoxmianan);
	        chbmianzhong=(CheckBox)findViewById(R.id.checkBoxmianzhong);
	        
	        if(QuestionActivity.quest[6][0]==1){
	        	chbmianhuang.setChecked(true);
	        }else{
	        	chbmianhuang.setChecked(false);
	        }
	        if(QuestionActivity.quest[6][1]==1){
	        	chbmianhong.setChecked(true);
	        }else{
	        	chbmianhong.setChecked(false);
	        }
	        if(QuestionActivity.quest[6][2]==1){
	        	chbmianbai.setChecked(true);
	        }else{
	        	chbmianbai.setChecked(false);
	        }
	        if(QuestionActivity.quest[6][3]==1){
	        	chbmianan.setChecked(true);
	        }else{
	        	chbmianan.setChecked(false);
	        }
	        if(QuestionActivity.quest[6][4]==1){
	        	chbmianzhong.setChecked(true);
	        }else{
	        	chbmianzhong.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnmiansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbmianhuang.isChecked()){
	            		QuestionActivity.quest[6][0]=1;
	            	}else{
	            		QuestionActivity.quest[6][0]=0;
	            	}
	            	if(chbmianhong.isChecked()){
	            		QuestionActivity.quest[6][1]=1;
	            	}else{
	            		QuestionActivity.quest[6][1]=0;
	            	}
	            	if(chbmianbai.isChecked()){
	            		QuestionActivity.quest[6][2]=1;
	            	}else{
	            		QuestionActivity.quest[6][2]=0;
	            	}
	            	if(chbmianan.isChecked()){
	            		QuestionActivity.quest[6][3]=1;
	            	}else{
	            		QuestionActivity.quest[6][3]=0;
	            	}
	            	if(chbmianzhong.isChecked()){
	            		QuestionActivity.quest[6][4]=1;
	            	}else{
	            		QuestionActivity.quest[6][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
