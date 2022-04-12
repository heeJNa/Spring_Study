package com.sist.temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class MusicManager {
	public static void main(String[] args) {
		MusicManager m = new MusicManager();
		m.genieMusic();
	}
	public void genieMusic() {
		MusicDAO dao=new MusicDAO();
		try {
			int k=1;
			for(int i=1;i<=2;i++) {
				// HTML을 읽어 와서 메모리에 저장
				// top200 https://www.genie.co.kr/chart/top200?ditc=D&ymd=20220412&hh=10&rtm=Y&pg=1
				// 장르별 일간 -가요  : https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0100&pg=1
				// 장르별 일간 - pop : https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0200&pg=1
				// 장르별 일간 - OST : https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0300&pg=1
				// 장르별 일간 - 트롯 : https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0107&pg=1
				// 장르별 일간 - JAZZ : https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0500&pg=1
				// 시대별 차트- 2021-국내 : https://www.genie.co.kr/chart/musicHistory?year=2021&category=0&pg=1 : 달라서 안됨
				Document doc = Jsoup.connect("https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220410&genrecode=M0500&pg="+i).get();
				Elements title=doc.select("td.info a.title");
				Elements singer=doc.select("td.info a.artist");
				Elements album=doc.select("td.info a.albumtitle");
				Elements poster=doc.select("a.cover img");
				Elements etc=doc.select("td.number span.rank");
				for(int j=0;j<title.size();j++) {
					System.out.println("곡명: "+title.get(j).text());
					System.out.println("가수명: "+singer.get(j).text());
					System.out.println("앨범명: "+album.get(j).text());
					System.out.println("포스터: "+poster.get(j).attr("src"));
					String tmp = etc.get(j).text();
					// 10하강
					String state=tmp.replaceAll("[^가-힣]", "").trim();
					int idcrement=0;
					if(state.equals("유지")) {
						idcrement=0;
					}else {
						if(state.equals("상승") || state.equals("하강")) {
							idcrement=Integer.parseInt(tmp.replaceAll("[^0-9]", "").trim());
						}else if(!(state.equals("유지")||state.equals("상승") || state.equals("하강"))){
							state="new";
							idcrement=0;
						}
					}
					System.out.println("상태: "+state);
					System.out.println("등폭: "+idcrement);
//					System.out.println("key: "+getMkeyData(title.get(j).text()));
					System.out.println("==================================");
					MusicVO vo = new MusicVO();
					vo.setCno(6);
					vo.setTitle(title.get(j).text());
					vo.setSinger(singer.get(j).text());
					vo.setPoster(poster.get(j).attr("src"));
					vo.setState(state);
					vo.setIdcrement(idcrement);
					vo.setAlbum(album.get(j).text());
					vo.setMkey(getMkeyData(title.get(j).text()));
					dao.musicInsert(vo);
					System.out.println("k= "+k);
					k++;
				}
				System.out.println("데이터 수집 완료!!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getMkeyData(String title) {
		String key="";
		try {
		
			Document doc = Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
			String data=doc.toString();
			// ? 는 정규식에서 이미 사용하는 기호 =>  ?를 출력하기 위해서는 \\?
			// ^ + * . => \\. \\+
			Pattern p = Pattern.compile("/watch\\?v=[^가-힣]+");
			Matcher m=p.matcher(data);
			/*
			 *		(aaa)(bbb)(ccc) 
			 */
			while(m.find()) {
				String s = m.group(); // 실제값을 읽어 온다
				key=s.substring(s.indexOf("=")+1,s.indexOf("\""));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
}
