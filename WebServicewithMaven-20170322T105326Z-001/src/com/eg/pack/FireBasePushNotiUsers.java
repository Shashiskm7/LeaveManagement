package com.eg.pack;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class FireBasePushNotiUsers {
	public final static  String AUTH_KEY_FCM = "AAAAeuI1GZU:APA91bHGWPhKPB3iac5pMvgyH3_Ahprko4EzxLF9xlGRtkqoeACJ-lc"
            + "3asU6__bXZ0CzNw7fvEamDUlx9V3P91mdds_tlz1l6Z6ShlO6WoEutJGPc1Sx89FCa"
            + "5XuaOg491ewCJ5eeUVZ";
public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

public void pushFCMNotification(String userDeviceIdKey)throws Exception
{
String authKey = AUTH_KEY_FCM;
String FMCurl = API_URL_FCM;

URL url = new URL(FMCurl);

HttpURLConnection conn = (HttpURLConnection) url.openConnection();

conn.setUseCaches(false);
conn.setDoInput(true);
conn.setDoOutput(true);

conn.setRequestMethod("POST");
conn.setRequestProperty("Authorization","key="+authKey);
conn.setRequestProperty("Content-Type","application/json");

JSONObject json = new JSONObject();
json.put("to",userDeviceIdKey.trim());
JSONObject info = new JSONObject();
info.put("title","New Request");
info.put("body","You Have Got New Request");
json.put("notification",info);


OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
wr.write(json.toString());
wr.flush();
conn.getInputStream();


}
	
	
	
}
