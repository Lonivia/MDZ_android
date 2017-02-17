package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionTou extends Activity {

	private Button btntousubmit;
	
	private CheckBox chbtoutong;
	private CheckBox chbtouyun;
	private CheckBox chbtouhun;
	private CheckBox chbtouzhang;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listtou);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btntousubmit=(Button) findViewById(R.id.buttontousubmit);
	        chbtoutong=(CheckBox)findViewById(R.id.checkBoxtoutong);
	        chbtouhun=(CheckBox)findViewById(R.id.checkBoxtouhun);
	        chbtouzhang=(CheckBox)findViewById(R.id.checkBoxtouzhang);
	        chbtouyun=(CheckBox)findViewById(R.id.checkBoxtouyun);

	        
	        if(QuestionActivity.quest[3][0]==1){
	        	chbtoutong.setChecked(true);
	        }else{
	        	chbtoutong.setChecked(false);
	        }
	        if(QuestionActivity.quest[3][1]==1){
	        	chbtouyun.setChecked(true);
	        }else{
	        	chbtouyun.setChecked(false);
	        }
	        if(QuestionActivity.quest[3][2]==1){
	        	chbtouhun.setChecked(true);
	        }else{
	        	chbtouhun.setChecked(false);
	        }
	        if(QuestionActivity.quest[3][3]==1){
	        	chbtouzhang.setChecked(true);
	        }else{
	        	chbtouzhang.setChecked(false);
	        }

	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btntousubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbtoutong.isChecked()){
	            		QuestionActivity.quest[3][0]=1;
	            	}else{
	            		QuestionActivity.quest[3][0]=0;
	            	}
	            	if(chbtouyun.isChecked()){
	            		QuestionActivity.quest[3][1]=1;
	            	}else{
	            		QuestionActivity.quest[3][1]=0;
	            	}
	            	if(chbtouhun.isChecked()){
	            		QuestionActivity.quest[3][2]=1;
	            	}else{
	            		QuestionActivity.quest[3][2]=0;
	            	}
	            	if(chbtouzhang.isChecked()){
	            		QuestionActivity.quest[3][3]=1;
	            	}else{
	            		QuestionActivity.quest[3][3]=0;
	            	}

	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
