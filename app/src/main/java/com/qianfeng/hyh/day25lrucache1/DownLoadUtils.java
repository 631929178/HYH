package com.qianfeng.hyh.day25lrucache1;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadUtils {
    
	public static String getJsonString(String url) {
		String jString="";
		OkHttpClient client=new OkHttpClient();
		Request request=new Request.Builder().url(url).build();
		try {
			Response response=client.newCall(request).execute();
			jString=response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jString;
	}	
	public static byte[] getImageByte(String url) {
		byte[] b=null;
		OkHttpClient client=new OkHttpClient();
		Request request=new Request.Builder().url(url).build();
		try {
			Response response=client.newCall(request).execute();
			b=response.body().bytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}	
}
