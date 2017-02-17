package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

public class QuestionKou extends Activity {

	private Button btnkousubmit;
	
	private CheckBox chbkouku;
	private CheckBox chbkouke;
	private CheckBox chbkougan;
	private CheckBox chbkouwai;
	private CheckBox chbkouchou;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
	        setContentView(R.layout.listkou);
	        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
	        layoutParams.gravity=Gravity.TOP;
	        layoutParams.y = 200;
	        
	        btnkousubmit=(Button) findViewById(R.id.buttonkousubmit);
	        chbkouku=(CheckBox)findViewById(R.id.checkBoxkouku);
	        chbkouke=(CheckBox)findViewById(R.id.checkBoxkouke);
	        chbkougan=(CheckBox)findViewById(R.id.checkBoxkougan);
	        chbkouwai=(CheckBox)findViewById(R.id.checkBoxkouwai);
	        chbkouchou=(CheckBox)findViewById(R.id.checkBoxkouchou);
	        
	        if(QuestionActivity.quest[0][0]==1){
	        	chbkouku.setChecked(true);
	        }else{
	        	chbkouku.setChecked(false);
	        }
	        if(QuestionActivity.quest[0][1]==1){
	        	chbkouke.setChecked(true);
	        }else{
	        	chbkouke.setChecked(false);
	        }
	        if(QuestionActivity.quest[0][2]==1){
	        	chbkougan.setChecked(true);
	        }else{
	        	chbkougan.setChecked(false);
	        }
	        if(QuestionActivity.quest[0][3]==1){
	        	chbkouwai.setChecked(true);
	        }else{
	        	chbkouwai.setChecked(false);
	        }
	        if(QuestionActivity.quest[0][4]==1){
	        	chbkouchou.setChecked(true);
	        }else{
	        	chbkouchou.setChecked(false);
	        }
	        
	        
	        
	        onStart();
	 }
	 
	 public void onStart() {
	        super.onStart();
	        btnkousubmit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(chbkouku.isChecked()){
	            		QuestionActivity.quest[0][0]=1;
	            	}else{
	            		QuestionActivity.quest[0][0]=0;
	            	}
	            	if(chbkouke.isChecked()){
	            		QuestionActivity.quest[0][1]=1;
	            	}else{
	            		QuestionActivity.quest[0][1]=0;
	            	}
	            	if(chbkougan.isChecked()){
	            		QuestionActivity.quest[0][2]=1;
	            	}else{
	            		QuestionActivity.quest[0][2]=0;
	            	}
	            	if(chbkouwai.isChecked()){
	            		QuestionActivity.quest[0][3]=1;
	            	}else{
	            		QuestionActivity.quest[0][3]=0;
	            	}
	            	if(chbkouchou.isChecked()){
	            		QuestionActivity.quest[0][4]=1;
	            	}else{
	            		QuestionActivity.quest[0][4]=0;
	            	}
	            	
	            	
	            	
	            	
	            	finish();
	            }
	            
	    	});
	        
	 }
	
	
	
}
