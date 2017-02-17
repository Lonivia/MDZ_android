package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionYao extends Activity {

	

	private Button btnyaosubmit;
	
	private CheckBox chbyaotong;
	private CheckBox chbyaosuan;
	private CheckBox chbyaoruan;


	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listyao);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnyaosubmit=(Button) findViewById(R.id.buttonyaosubmit);
	        chbyaosuan=(CheckBox)findViewById(R.id.checkBoxyaosuan);
	        chbyaotong=(CheckBox)findViewById(R.id.checkBoxyaotong);
	        chbyaoruan=(CheckBox)findViewById(R.id.checkBoxyaoruan);


	        
	        if(QuestionActivity.quest[10][0]==1){
	        	chbyaotong.setChecked(true);
	        }else{
	        	chbyaotong.setChecked(false);
	        }
	        if(QuestionActivity.quest[10][1]==1){
	        	chbyaosuan.setChecked(true);
	        }else{
	        	chbyaosuan.setChecked(false);
	        }
	        if(QuestionActivity.quest[10][2]==1){
	        	chbyaoruan.setChecked(true);
	        }else{
	        	chbyaoruan.setChecked(false);
	        }


	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnyaosubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbyaotong.isChecked()){
	            		QuestionActivity.quest[10][0]=1;
	            	}else{
	            		QuestionActivity.quest[10][0]=0;
	            	}
	            	if(chbyaosuan.isChecked()){
	            		QuestionActivity.quest[10][1]=1;
	            	}else{
	            		QuestionActivity.quest[10][1]=0;
	            	}
	            	if(chbyaoruan.isChecked()){
	            		QuestionActivity.quest[10][2]=1;
	            	}else{
	            		QuestionActivity.quest[10][2]=0;
	            	}

	            	finish();
	            }
	            
	    	});
	        
	 }
}
