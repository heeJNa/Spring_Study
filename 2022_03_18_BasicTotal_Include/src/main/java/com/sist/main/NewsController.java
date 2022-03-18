package com.sist.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.NewsVO;

@RestController
public class NewsController {
	@GetMapping(value="etc/news/news_data.do",produces="text/plain;charset=utf-8")
	public String news_data(String fd) {
		System.out.println("전송 받음: "+fd);
		String result="";
		try {
			// 네이버에서 주소 막아버림
			String strUrl="http://newssearch.naver.com/search.naver?where=rss&query="
					+ URLEncoder.encode(fd,"UTF-8");//query=%EC%98%81%ED%99%94
			URL url=new URL(strUrl);
			StringBuffer sb = new StringBuffer();
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn!=null) { 
				// web에 연결됐을때
				BufferedReader br=
						new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while(true) {
					String s=br.readLine();
					if(s==null) break;
					sb.append(s);
				}
			}
			System.out.println(sb.toString());
			
			Document doc=Jsoup.parse(sb.toString()); // Jsoup(HTML/XML)
			Elements title = doc.select("rss channel item title");
			Elements desc = doc.select("rss channel item description");
			Elements author = doc.select("rss channel item author");
			JSONArray arr=new JSONArray();
			for(int i=0;i<title.size();i++) {
				JSONObject obj=new JSONObject();
				obj.put("title", title.get(i).text());
				obj.put("description",desc.get(i).text());
				obj.put("author",author.get(i).text());
				arr.add(obj);
			}
			System.out.println("arr: "+arr);
			result=arr.toJSONString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
