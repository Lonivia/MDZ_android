package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionTan extends Activity {
	private Button btntansubmit;
	
	private CheckBox chbtanduo;
	private CheckBox chbtanbai;
	private CheckBox chbtanhuang;
	private CheckBox chbtanxue;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listtan);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btntansubmit=(Button) findViewById(R.id.buttontansubmit);
	        chbtanduo=(CheckBox)findViewById(R.id.checkBoxtanduo);
	        chbtanbai=(CheckBox)findViewById(R.id.checkBoxtanbai);
	        chbtanhuang=(CheckBox)findViewById(R.id.checkBoxtanhuang);
	        chbtanxue=(CheckBox)findViewById(R.id.checkBoxtanxue);

	        
	        if(QuestionActivity.quest[16][0]==1){
	        	chbtanduo.setChecked(true);
	        }else{
	        	chbtanduo.setChecked(false);
	        }
	        if(QuestionActivity.quest[16][1]==1){
	        	chbtanbai.setChecked(true);
	        }else{
	        	chbtanbai.setChecked(false);
	        }
	        if(QuestionActivity.quest[16][2]==1){
	        	chbtanhuang.setChecked(true);
	        }else{
	        	chbtanhuang.setChecked(false);
	        }
	        if(QuestionActivity.quest[16][3]==1){
	        	chbtanxue.setChecked(true);
	        }else{
	        	chbtanxue.setChecked(false);
	        }

	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btntansubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbtanduo.isChecked()){
	            		QuestionActivity.quest[16][0]=1;
	            	}else{
	            		QuestionActivity.quest[16][0]=0;
	            	}
	            	if(chbtanbai.isChecked()){
	            		QuestionActivity.quest[16][1]=1;
	            	}else{
	            		QuestionActivity.quest[16][1]=0;
	            	}
	            	if(chbtanhuang.isChecked()){
	            		QuestionActivity.quest[16][2]=1;
	            	}else{
	            		QuestionActivity.quest[16][2]=0;
	            	}
	            	if(chbtanxue.isChecked()){
	            		QuestionActivity.quest[16][3]=1;
	            	}else{
	            		QuestionActivity.quest[16][3]=0;
	            	}

	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
}
