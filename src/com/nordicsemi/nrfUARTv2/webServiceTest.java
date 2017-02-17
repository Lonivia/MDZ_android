package com.nordicsemi.nrfUARTv2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.StrictMode;
import android.util.Log;

public class webServiceTest {


	public static String diag_id=null;
	public static String new_patient_id=null;
	//public String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
	//Date date=new Date();
	//public long date_long=date.getTime();
	Random rd=new Random();
	
	
	Date date1 = new Date();     
	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");    
	 String date_int = sdf1.format(date1)+String.valueOf(((int)(rd.nextDouble()*10000)));   
	//public int date_int=new Long(date_long).intValue()+(rd.nextInt()*1000);
	//public int date_int=(int)date_long+(rd.nextInt()*1000);
	
	
	 public  String getProvince(){
		 
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
	                .detectDiskReads()  
	                .detectDiskWrites()  
	                .detectNetwork()  
	                .penaltyLog()  
	                .build());   
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
	                .detectLeakedSqlLiteObjects()  
	                .detectLeakedClosableObjects()  
	                .penaltyLog()  
	                .penaltyDeath()  
	                .build());  
		 
		 
		 
	        List<String> provinces=new ArrayList<String>();
	        //String str="";
	        String targetNameSpace = "urn:SoapTestControllerwsdl";
	        String getSupportProvince = "testSoap1";
	        String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
	        
//	        String targetNameSpace = "http://WebXml.com.cn/";
//	        String getSupportProvince = "getSupportProvince";
//	        String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
	        
	        
	        SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
	        //request.addProperty("参数", "参数值");调用的方法参数与参数值（根据具体需要可选可不选）
	        
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
	        
	        //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
	        HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
	        try {
	            
	            httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
	            SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
	            
	            //String result=(String)envelope.getResponse();
	            Log.i("aa", result.toString());
	            return result.toString();
	            //下面对结果进行解析，结构类似json对象
	            //str=(String) result.getProperty(6).toString();
	            
//	            int count=result.getPropertyCount();
//	            for(int index=0;index<count;index++){
//	                provinces.add(result.getProperty(index).toString());
//	            }
	            
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (XmlPullParserException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        //return provinces;
			return null;
	    }
	 
public  String checkVersion(){
		 
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
	                .detectDiskReads()  
	                .detectDiskWrites()  
	                .detectNetwork()  
	                .penaltyLog()  
	                .build());   
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
	                .detectLeakedSqlLiteObjects()  
	                .detectLeakedClosableObjects()  
	                .penaltyLog()  
	                .penaltyDeath()  
	                .build());  
		 
		 
		 
	        List<String> provinces=new ArrayList<String>();
	        //String str="";
	        String targetNameSpace = "urn:SoapTestControllerwsdl";
	        String getSupportProvince = "checkVersion";
	        String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
	        
//	        String targetNameSpace = "http://WebXml.com.cn/";
//	        String getSupportProvince = "getSupportProvince";
//	        String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
	        
	        
	        SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
	        soapObject.addProperty("ver", "123");
	        
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
	        
	        //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
	        HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
	        try {
	            
	            httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
	            SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
	            
	            //String result=(String)envelope.getResponse();
	            Log.e("aa", result.toString());
	            return result.toString();
	            //下面对结果进行解析，结构类似json对象
	            //str=(String) result.getProperty(6).toString();
	            
//	            int count=result.getPropertyCount();
//	            for(int index=0;index<count;index++){
//	                provinces.add(result.getProperty(index).toString());
//	            }
	            
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (XmlPullParserException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        //return provinces;
			return null;
	    }



public  void uploadData(int[][][] adsend,int step,int track){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
               .detectDiskReads()  
               .detectDiskWrites()  
               .detectNetwork()  
               .penaltyLog()  
               .build());   
   StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
               .detectLeakedSqlLiteObjects()  
               .detectLeakedClosableObjects()  
               .penaltyLog()  
               .penaltyDeath()  
               .build());  
	 
	 
	 
       List<String> provinces=new ArrayList<String>();
       //String str="";
       String targetNameSpace = "urn:SoapTestControllerwsdl";
       String getSupportProvince = "uploadSampleData";
       String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";


       
       
       SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
       soapObject.addProperty("account", SelectActivity.account);
       soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
       soapObject.addProperty("sn", date_int);
	   String data = "";
	   for(int datai=0;datai<=2399;datai++){
		   data+=adsend[datai][track-1][step];
		   if(datai!=2399)
		   data+=",";
	   }
	   soapObject.addProperty("step",step);
	   soapObject.addProperty("track",track);
	   soapObject.addProperty("sampleData", data);
	   //Log.e("aa", "---------set property");
       
       SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
       envelope.dotNet=true;
       envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
       
       //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
       HttpTransportSE httpTranstation=new HttpTransportSE(WSDL,15000);
       
       //MyAndroidHttpTransport transport = new MyAndroidHttpTransport(WSDL, 50000);
       Log.e("aa", "-----"+date_int);
       try {
           
    	   httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
           SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
           
           //String result=(String)envelope.getResponse();
           Log.e("aa", result.toString());
           //return result.toString();
           //return result.toString();
           //下面对结果进行解析，结构类似json对象
           //str=(String) result.getProperty(6).toString();
           
//           int count=result.getPropertyCount();
//           for(int index=0;index<count;index++){
//               provinces.add(result.getProperty(index).toString());
//               Log.e("aa", result.getProperty(index).toString());
//           }
           
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (XmlPullParserException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } 
       //return provinces;
		//return null;
   }


public  void setDiag3(){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
              .detectDiskReads()  
              .detectDiskWrites()  
              .detectNetwork()  
              .penaltyLog()  
              .build());   
  StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
              .detectLeakedSqlLiteObjects()  
              .detectLeakedClosableObjects()  
              .penaltyLog()  
              .penaltyDeath()  
              .build());  
	 
	 
	 
      List<String> provinces=new ArrayList<String>();
      //String str="";
      String targetNameSpace = "urn:SoapTestControllerwsdl";
      String getSupportProvince = "setDiag3";
      String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";


      
      SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
      
      soapObject.addProperty("account", SelectActivity.account);
      soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
      soapObject.addProperty("sn", date_int);
      soapObject.addProperty("user", "system_patient");
      soapObject.addProperty("phone", "111111");
      soapObject.addProperty("pwd", "system_FecX593");
      soapObject.addProperty("age", "100");
      soapObject.addProperty("sex", "男");
      soapObject.addProperty("doctor_id", SelectActivity.docid);
      
      String ques = "";
      for(int aa=0;aa<20;aa++){
    	  for(int bb=0;bb<5;bb++){
    		  if(aa==19 && bb==4){
    			  ques+= QuestionActivity.quest[aa][bb];
    		  }else{
    			  ques+= QuestionActivity.quest[aa][bb] + ",";
    		  }
    		  
    	  }
      }
      Log.e("!!!!!!!!!", ques);
      
      
      soapObject.addProperty("question", ques);
      
	   //Log.e("aa", "---------set property");
      
      SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
      envelope.dotNet=true;
      envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
      
      //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
      HttpTransportSE httpTranstation=new HttpTransportSE(WSDL,15000);
      
      //MyAndroidHttpTransport transport = new MyAndroidHttpTransport(WSDL, 50000);
      Log.e("aa", "---------HTTP trans"+date_int);
      try {
          
   	   httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
          SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
          
          //String result=(String)envelope.getResponse();
          Log.e("aa", result.toString());
          diag_id=result.toString();
          //return result.toString();
          //return result.toString();
          //下面对结果进行解析，结构类似json对象
          //str=(String) result.getProperty(6).toString();
          
//          int count=result.getPropertyCount();
//          for(int index=0;index<count;index++){
//              provinces.add(result.getProperty(index).toString());
//              Log.e("aa", result.getProperty(index).toString());
//          }
          
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (XmlPullParserException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } 
      //return provinces;
		//return null;
  }






public  String getNewestDiagResult(){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
               .detectDiskReads()  
               .detectDiskWrites()  
               .detectNetwork()  
               .penaltyLog()  
               .build());   
   StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
               .detectLeakedSqlLiteObjects()  
               .detectLeakedClosableObjects()  
               .penaltyLog()  
               .penaltyDeath()  
               .build());  
               
               
	 
	 
	 
       List<String> provinces=new ArrayList<String>();
       //String str="";
       String targetNameSpace = "urn:SoapTestControllerwsdl";
       String getSupportProvince = "getNewestDiagResult";
       String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
       
//       String targetNameSpace = "http://WebXml.com.cn/";
//       String getSupportProvince = "getSupportProvince";
//       String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
       
       
       SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
       soapObject.addProperty("account", SelectActivity.account);
       soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
       soapObject.addProperty("diag_id", diag_id);
       
       SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
       envelope.dotNet=true;
       envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
       
       //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
       HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
       try {
           
           httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
           SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
           
           //String result=(String)envelope.getResponse();
           Log.i("aa", result.toString());
           return result.toString();
           //下面对结果进行解析，结构类似json对象
           //str=(String) result.getProperty(6).toString();
           
//           int count=result.getPropertyCount();
//           for(int index=0;index<count;index++){
//               provinces.add(result.getProperty(index).toString());
//           }
           
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (XmlPullParserException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch(Exception e){
    	   
       }
       //return provinces;
		return null;
   }



public  String doctorLogin(){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
              .detectDiskReads()  
              .detectDiskWrites()  
              .detectNetwork()  
              .penaltyLog()  
              .build());   
  StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
              .detectLeakedSqlLiteObjects()  
              .detectLeakedClosableObjects()  
              .penaltyLog()  
              .penaltyDeath()  
              .build());  
              
              
	 
	 
	 
      List<String> provinces=new ArrayList<String>();
      //String str="";
      String targetNameSpace = "urn:SoapTestControllerwsdl";
      String getSupportProvince = "doctorLogin";
      String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
      
//      String targetNameSpace = "http://WebXml.com.cn/";
//      String getSupportProvince = "getSupportProvince";
//      String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
      
      
      SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
      soapObject.addProperty("account", SelectActivity.account);
      soapObject.addProperty("account_pwd", SelectActivity.account_pwd);

      
      SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
      envelope.dotNet=true;
      envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
      
      //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
      HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
      try {
          
          httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
          SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
          
          //String result=(String)envelope.getResponse();
          Log.i("aa", result.toString());
          return result.toString();
          //下面对结果进行解析，结构类似json对象
          //str=(String) result.getProperty(6).toString();
          
//          int count=result.getPropertyCount();
//          for(int index=0;index<count;index++){
//              provinces.add(result.getProperty(index).toString());
//          }
          
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (XmlPullParserException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch(Exception e){
   	   
      }
      //return provinces;
		return null;
  }

public  String uploadDoctorDiag(String selected_symptoms, String doctor_diag){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
             .detectDiskReads()  
             .detectDiskWrites()  
             .detectNetwork()  
             .penaltyLog()  
             .build());   
 StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
             .detectLeakedSqlLiteObjects()  
             .detectLeakedClosableObjects()  
             .penaltyLog()  
             .penaltyDeath()  
             .build());  
             
             
	 
	 
	 
     List<String> provinces=new ArrayList<String>();
     //String str="";
     String targetNameSpace = "urn:SoapTestControllerwsdl";
     String getSupportProvince = "uploadDoctorDiag";
     String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
     
//     String targetNameSpace = "http://WebXml.com.cn/";
//     String getSupportProvince = "getSupportProvince";
//     String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
     
     
     SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
     soapObject.addProperty("account", SelectActivity.account);
     soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
     soapObject.addProperty("selected_symptoms", selected_symptoms);
     Log.e("webupload_select", "--"+selected_symptoms);
     soapObject.addProperty("diag_id", diag_id);
     soapObject.addProperty("doctor_diag", doctor_diag);
     Log.e("webupload_diag", "--"+doctor_diag);

     
     SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
     envelope.dotNet=true;
     envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
     
     //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
     HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
     try {
         
         httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
         SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
         
         //String result=(String)envelope.getResponse();
         Log.i("aa", result.toString());
         return result.toString();
         //下面对结果进行解析，结构类似json对象
         //str=(String) result.getProperty(6).toString();
         
//         int count=result.getPropertyCount();
//         for(int index=0;index<count;index++){
//             provinces.add(result.getProperty(index).toString());
//         }
         
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch (XmlPullParserException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch(Exception e){
  	   
     }
     //return provinces;
		return null;
 }



public  String checkPatientName(String patientName){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
             .detectDiskReads()  
             .detectDiskWrites()  
             .detectNetwork()  
             .penaltyLog()  
             .build());   
 StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
             .detectLeakedSqlLiteObjects()  
             .detectLeakedClosableObjects()  
             .penaltyLog()  
             .penaltyDeath()  
             .build());  
             
             
	 
	 
	 
     List<String> provinces=new ArrayList<String>();
     //String str="";
     String targetNameSpace = "urn:SoapTestControllerwsdl";
     String getSupportProvince = "checkPatientName";
     String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
     
//     String targetNameSpace = "http://WebXml.com.cn/";
//     String getSupportProvince = "getSupportProvince";
//     String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
     
     
     SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
     soapObject.addProperty("patientName", patientName);
     //soapObject.addProperty("account_pwd", SelectActivity.account_pwd);

     
     SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
     envelope.dotNet=true;
     envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
     
     //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
     HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
     try {
         
         httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
         SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
         
         //String result=(String)envelope.getResponse();
         Log.i("checkPatientName", result.toString());
         return result.toString();
         //下面对结果进行解析，结构类似json对象
         //str=(String) result.getProperty(6).toString();
         
//         int count=result.getPropertyCount();
//         for(int index=0;index<count;index++){
//             provinces.add(result.getProperty(index).toString());
//         }
         
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch (XmlPullParserException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch(Exception e){
  	   
     }
     //return provinces;
		return null;
 }


public  String checkPatientPassword(String patientName,String pwd){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
            .detectDiskReads()  
            .detectDiskWrites()  
            .detectNetwork()  
            .penaltyLog()  
            .build());   
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
            .detectLeakedSqlLiteObjects()  
            .detectLeakedClosableObjects()  
            .penaltyLog()  
            .penaltyDeath()  
            .build());  
            
            
	 
	 
	 
    List<String> provinces=new ArrayList<String>();
    //String str="";
    String targetNameSpace = "urn:SoapTestControllerwsdl";
    String getSupportProvince = "checkPatientPassword";
    String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
    
//    String targetNameSpace = "http://WebXml.com.cn/";
//    String getSupportProvince = "getSupportProvince";
//    String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
    
    
    SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
    soapObject.addProperty("patientName", patientName);
    soapObject.addProperty("pwd", pwd);

    
    SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
    envelope.dotNet=true;
    envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
    
    //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
    HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
    try {
        
        httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
        SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
        
        //String result=(String)envelope.getResponse();
        Log.i("checkPatientPassword", result.toString());
        return result.toString();
        //下面对结果进行解析，结构类似json对象
        //str=(String) result.getProperty(6).toString();
        
//        int count=result.getPropertyCount();
//        for(int index=0;index<count;index++){
//            provinces.add(result.getProperty(index).toString());
//        }
        
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (XmlPullParserException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch(Exception e){
 	   
    }
    //return provinces;
		return null;
}


public  String modifyPatientOfDiag(){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
           .detectDiskReads()  
           .detectDiskWrites()  
           .detectNetwork()  
           .penaltyLog()  
           .build());   
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
           .detectLeakedSqlLiteObjects()  
           .detectLeakedClosableObjects()  
           .penaltyLog()  
           .penaltyDeath()  
           .build());  
           
           
	 
	 
	 
   List<String> provinces=new ArrayList<String>();
   //String str="";
   String targetNameSpace = "urn:SoapTestControllerwsdl";
   String getSupportProvince = "modifyPatientOfDiag";
   String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
   
//   String targetNameSpace = "http://WebXml.com.cn/";
//   String getSupportProvince = "getSupportProvince";
//   String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
   
   
   SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
   soapObject.addProperty("account", SelectActivity.account);
  // Log.e("webtest",SelectActivity.account);
   soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
   //Log.e("webtest",SelectActivity.account_pwd);
   
   soapObject.addProperty("diag_id", diag_id);
   try{
	   Log.i("webtestdiag","---"+diag_id);
   }catch(Exception egln){
	   
   }
   soapObject.addProperty("new_patient_id", new_patient_id);
   try{
	   Log.i("webtestpaintid","---"+new_patient_id);
	}catch(Exception egln2){
		   
	}


   
   SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
   envelope.dotNet=true;
   envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
   
   //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
   HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
   try {
       
       httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
       SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
       
       //String result=(String)envelope.getResponse();
       Log.i("modifyPatientOfDiag", result.toString());
       return result.toString();
       //下面对结果进行解析，结构类似json对象
       //str=(String) result.getProperty(6).toString();
       
//       int count=result.getPropertyCount();
//       for(int index=0;index<count;index++){
//           provinces.add(result.getProperty(index).toString());
//       }
       
   } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
   } catch (XmlPullParserException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
   } catch(Exception e){
	   
   }
   //return provinces;
		return null;
}


public  String AddPatient(String user, String phone, String pwd, int age, String sex){
	 
	
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
          .detectDiskReads()  
          .detectDiskWrites()  
          .detectNetwork()  
          .penaltyLog()  
          .build());   
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
          .detectLeakedSqlLiteObjects()  
          .detectLeakedClosableObjects()  
          .penaltyLog()  
          .penaltyDeath()  
          .build());  
          
          
	 
	 
	 
  List<String> provinces=new ArrayList<String>();
  //String str="";
  String targetNameSpace = "urn:SoapTestControllerwsdl";
  String getSupportProvince = "AddPatient";
  String WSDL="http://121.40.53.156/mDoctor/index.php?r=soapTest/patientInfo&ws=1";
  
//  String targetNameSpace = "http://WebXml.com.cn/";
//  String getSupportProvince = "getSupportProvince";
//  String WSDL="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
  
  
  SoapObject soapObject=new SoapObject(targetNameSpace, getSupportProvince);
  soapObject.addProperty("acount", SelectActivity.account);
  soapObject.addProperty("account_pwd", SelectActivity.account_pwd);
  soapObject.addProperty("user", user);
  soapObject.addProperty("phone", phone);
  soapObject.addProperty("pwd", pwd);
  soapObject.addProperty("age", age);
  soapObject.addProperty("sex", sex);
  soapObject.addProperty("doctor_id", SelectActivity.docid);

  
  SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
  envelope.dotNet=true;
  envelope.setOutputSoapObject(soapObject);//envelope.bodyOut=request;	        
  
  //HttpTransportSE  httpTranstation=new HttpTransportSE(WSDL);
  HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);
  try {
      
      httpTranstation.call(targetNameSpace+"#"+getSupportProvince, envelope);
      SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
      
      //String result=(String)envelope.getResponse();
      new_patient_id=result.toString();
      Log.i("AddPatient", result.toString());
      return result.toString();
      //下面对结果进行解析，结构类似json对象
      //str=(String) result.getProperty(6).toString();
      
//      int count=result.getPropertyCount();
//      for(int index=0;index<count;index++){
//          provinces.add(result.getProperty(index).toString());
//      }
      
  } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  } catch (XmlPullParserException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  } catch(Exception e){
	   
  }
  //return provinces;
		return null;
}








	
}
