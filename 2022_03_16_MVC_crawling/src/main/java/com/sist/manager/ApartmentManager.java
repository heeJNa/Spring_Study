package com.sist.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApartmentManager {
	public static void main(String[] args) {
		try {
			StringBuilder urlStr = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?");
			urlStr.append("LAWD_CD=11215&");
			urlStr.append("DEAL_YMD=202110&");
			urlStr.append("serviceKey=a54FQpISj0Z05rlq%2FkHE4FTD%2FH4YpwJ59MyVG%2BJE5lIxJlD3DRXmryCuvapMfrmbvgmEUC9zv7o3s6WsNiTDaQ%3D%3D");
			
			URL url = new URL(urlStr.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/xml");
			System.out.println("Response code: " + conn.getResponseCode());
			
			BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        System.out.println(sb.toString());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
