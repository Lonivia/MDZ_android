package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionXiaobian extends Activity {

	private Button btnxiaobiansubmit;
	
	private CheckBox chbxiaobianshao;
	private CheckBox chbxiaobianxue;
	private CheckBox chbxiaobianhuang;
	private CheckBox chbxiaobianpin;
	private CheckBox chbxiaobiandi;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listxiaobian);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnxiaobiansubmit=(Button) findViewById(R.id.buttonxiaobiansubmit);
	        chbxiaobianshao=(CheckBox)findViewById(R.id.checkBoxxiaobianshao);
	        chbxiaobianxue=(CheckBox)findViewById(R.id.checkBoxxiaobianxue);
	        chbxiaobianhuang=(CheckBox)findViewById(R.id.checkBoxxiaobianhuang);
	        chbxiaobianpin=(CheckBox)findViewById(R.id.checkBoxxiaobianpin);
	        chbxiaobiandi=(CheckBox)findViewById(R.id.checkBoxxiaobiandi);
	        
	        if(QuestionActivity.quest[18][0]==1){
	        	chbxiaobianshao.setChecked(true);
	        }else{
	        	chbxiaobianshao.setChecked(false);
	        }
	        if(QuestionActivity.quest[18][1]==1){
	        	chbxiaobianxue.setChecked(true);
	        }else{
	        	chbxiaobianxue.setChecked(false);
	        }
	        if(QuestionActivity.quest[18][2]==1){
	        	chbxiaobianhuang.setChecked(true);
	        }else{
	        	chbxiaobianhuang.setChecked(false);
	        }
	        if(QuestionActivity.quest[18][3]==1){
	        	chbxiaobianpin.setChecked(true);
	        }else{
	        	chbxiaobianpin.setChecked(false);
	        }
	        if(QuestionActivity.quest[18][4]==1){
	        	chbxiaobiandi.setChecked(true);
	        }else{
	        	chbxiaobiandi.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnxiaobiansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbxiaobianshao.isChecked()){
	            		QuestionActivity.quest[18][0]=1;
	            	}else{
	            		QuestionActivity.quest[18][0]=0;
	            	}
	            	if(chbxiaobianxue.isChecked()){
	            		QuestionActivity.quest[18][1]=1;
	            	}else{
	            		QuestionActivity.quest[18][1]=0;
	            	}
	            	if(chbxiaobianhuang.isChecked()){
	            		QuestionActivity.quest[18][2]=1;
	            	}else{
	            		QuestionActivity.quest[18][2]=0;
	            	}
	            	if(chbxiaobianpin.isChecked()){
	            		QuestionActivity.quest[18][3]=1;
	            	}else{
	            		QuestionActivity.quest[18][3]=0;
	            	}
	            	if(chbxiaobiandi.isChecked()){
	            		QuestionActivity.quest[18][4]=1;
	            	}else{
	            		QuestionActivity.quest[18][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
