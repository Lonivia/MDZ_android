package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionGuanjie extends Activity {

	private Button btnguanjiesubmit;
	
	private CheckBox chbguanjiezhong;
	private CheckBox chbguanjietong;
	private CheckBox chbguanjieruan;
	private CheckBox chbguanjielen;
	private CheckBox chbguanjiesuan;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listguanjie);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnguanjiesubmit=(Button) findViewById(R.id.buttonguanjiesubmit);
	        chbguanjiezhong=(CheckBox)findViewById(R.id.checkBoxguanjiezhong);
	        chbguanjietong=(CheckBox)findViewById(R.id.checkBoxguanjietong);
	        chbguanjieruan=(CheckBox)findViewById(R.id.checkBoxguanjieruan);
	        chbguanjielen=(CheckBox)findViewById(R.id.checkBoxguanjielen);
	        chbguanjiesuan=(CheckBox)findViewById(R.id.checkBoxguanjiesuan);
	        
	        if(QuestionActivity.quest[14][0]==1){
	        	chbguanjiezhong.setChecked(true);
	        }else{
	        	chbguanjiezhong.setChecked(false);
	        }
	        if(QuestionActivity.quest[14][1]==1){
	        	chbguanjietong.setChecked(true);
	        }else{
	        	chbguanjietong.setChecked(false);
	        }
	        if(QuestionActivity.quest[14][2]==1){
	        	chbguanjieruan.setChecked(true);
	        }else{
	        	chbguanjieruan.setChecked(false);
	        }
	        if(QuestionActivity.quest[14][3]==1){
	        	chbguanjielen.setChecked(true);
	        }else{
	        	chbguanjielen.setChecked(false);
	        }
	        if(QuestionActivity.quest[14][4]==1){
	        	chbguanjiesuan.setChecked(true);
	        }else{
	        	chbguanjiesuan.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnguanjiesubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbguanjiezhong.isChecked()){
	            		QuestionActivity.quest[14][0]=1;
	            	}else{
	            		QuestionActivity.quest[14][0]=0;
	            	}
	            	if(chbguanjietong.isChecked()){
	            		QuestionActivity.quest[14][1]=1;
	            	}else{
	            		QuestionActivity.quest[14][1]=0;
	            	}
	            	if(chbguanjieruan.isChecked()){
	            		QuestionActivity.quest[14][2]=1;
	            	}else{
	            		QuestionActivity.quest[14][2]=0;
	            	}
	            	if(chbguanjielen.isChecked()){
	            		QuestionActivity.quest[14][3]=1;
	            	}else{
	            		QuestionActivity.quest[14][3]=0;
	            	}
	            	if(chbguanjiesuan.isChecked()){
	            		QuestionActivity.quest[14][4]=1;
	            	}else{
	            		QuestionActivity.quest[14][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
