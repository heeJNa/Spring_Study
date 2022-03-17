package com.sist.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
// Model => 반드시 설정 (HandlerMapping => @Controller가 있는 곳에서 
// @GetMapping, @RequestMappin, @PostMapping
// @Controller가 없는 상태에서는 skip
// 사용자 요청 처리 => 결과값 전송 => 화면 이동
// --------------- 요청값 (매개변수), 결과값을 전송 (Model 전송객체 이용)
// 화면 이동 => forward, sendRedirect() => 재전송
// forward => return "경로/JSP명" sendRedirect => return "redirect:~.do"
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;
@Controller
@RequestMapping("databoard/") // 공통으로 사용되는 경로명을 제거할 목적
public class DataBoardController {
	// 1.DAO가 필요하다
	@Autowired
	private DataBoardDAO dao;
	// 2. 기능별 처리
	@GetMapping("list.do") //databoard/list.do 
	public String databoard_list(String page,Model model) {
		// String page,Model model => DispatcherServlet에서 채워준다
		// 1. request,response는 사용하지 않는다
		// 2. 결과값을 JSP로 전송 => 전송 클래스(Model)
		/*
		 	1. 메소드 : 리턴형: String : forward처리할 JSP
		 								 sendRedirect를 이용한 재전송
		 						void : 다운로드, JavaScript
		 				매개변수: 1) 일반 데이터 (String, int, double)
		 						  2) 커맨드 객체: VO단위 
		 						  3) 배열 (체크박스), List형으로 받을 수 있다
		 */
		// 사용자가 첫 페이지에서는 페이지를 보낼 수가 없다 => default 제작
		if(page==null)
			page="1";
		// 1. 현재 페이지 지정
		int curpage=Integer.parseInt(page);
		// 2. 현재 페이지에 대한 데이터 읽기
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end",end);
		List<DataBoardVO> list = dao.databoardListData(map);
		// 3. 총페이지
		int totalpage=dao.databoardTotalPage();
		
		// 4. JSP에서 출력할 데이터 전송
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		// 오늘 날짜 전송 => new
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		model.addAttribute("today", today);
		// 5. model에 등록된 데이터를 출력하는 JSP를 지정
		return "databoard/list"; // 확장자를 사용하면 안된다
		// ModelAndView => ViewResolver가 받아서 JSP를 찾은 다음 request로 변환해서 전송
	}
	
	// 2. 글쓰기 폼
	@GetMapping("insert.do")
	public String databoard_insert() {
		return "databoard/insert";
	}
	
	// 3. 실제 오라클에 저장
	@PostMapping("insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list = vo.getFiles();
		if(list==null) {
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
		}else { 
			// 업로드가 된 상태
			String tempName="";
			String tempSize="";
			
			for(MultipartFile mf:list) {
				String fn=mf.getOriginalFilename(); //사용자가 보낸 파일명 읽기
				File file = new File("/Users/kimheejun/Desktop/upload/"+fn);
				try {
					mf.transferTo(file); // 실제 업로드
				}catch (Exception e) {
					e.printStackTrace();
				}
				tempName+=fn+",";
				tempSize+=file.length()+",";
			}
			tempName=tempName.substring(0,tempName.lastIndexOf(","));
			tempSize=tempSize.substring(0,tempSize.lastIndexOf(","));
			vo.setFilename(tempName);
			vo.setFilesize(tempSize);
			vo.setFilecount(list.size());
		}
		dao.databoardInsert(vo);
		return "redirect:list.do";
	}
	
	@GetMapping("detail.do")
							      // 사용자가 요청한 값
	public String databoard_detail(int no,Model model){
		DataBoardVO vo = dao.databoardDetailData(no); // 요청처리 (DAO연동)
		if(vo.getFilecount()!=0) { // 파일이 업로드가 되었다면
			List<String> fList=new ArrayList<>(); // 파일명
			List<String> sList=new ArrayList<>(); // 파일크기
			
			StringTokenizer st = new StringTokenizer(vo.getFilename(),",");
			while(st.hasMoreTokens()) {
				fList.add(st.nextToken());
			}
			st= new StringTokenizer(vo.getFilesize(),",");
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			model.addAttribute("fList", fList);
			model.addAttribute("sList", sList);
		}
		model.addAttribute("vo", vo); // 요청 결과값을 보낸다
		return "databoard/detail"; // 결과값 화면을 보여준다
	}
	/*
	 	 1. 매개변수 => 사용자가 요청한 값을 받는다 (request.getParameter():스프링에서 대신 해준다)
	 	 2. DAO연결, Web크롤링, XML파싱, JSON...요청처리
	 	 3. 요청 처리 결과값을 JSP로 전송: model
	 	 4. 어떤 JSP를 보여줄것인지 설정 (return) => 새로운 JSP, 기존의 JSP(redirect)
	 */
	
	@GetMapping("download.do")
	public void databoard_download(String fn,HttpServletResponse response) throws Exception {
		// Cookie => request, 전송 (응답) => response
		// 내장객체가 아니다 => 어노테이션
		// 1. header 전송 => 다운로드창 열어준다  (파일 크기, 파일 명)
		response.setHeader("Content-Disposition", "attachment;filename="
											+URLEncoder.encode(fn,"UTF-8"));
		File file=new File("/Users/kimheejun/Desktop/upload/"+fn);
		response.setContentLength((int)file.length());
		// 2. 저장 버튼 => 실제 데이터 다운로드(파일 copy)
		BufferedInputStream bis =
				new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos=
				new BufferedOutputStream(response.getOutputStream());
		// response.getOutputStream() => 사용자 다운로드 위치
		int i=0;
		byte[] buffer=new byte[1024];
		while((i=bis.read(buffer,0,1024))!=-1) { // -1 = EOF
			// i는 문자한개X => byte갯수 읽기
			bos.write(buffer,0,i);
		}
		bis.close();
		bos.close();
	}
	
	@GetMapping("update.do")
	public String databoard_update(int no,Model model) {
		DataBoardVO vo = dao.databoardDetailData(no);
		model.addAttribute("vo", vo);
		return "databoard/update";
	}
	
	@PostMapping("update_ok.do")
	/*
	 	수정 => 비밀번호 검사 => true (detail.do)
	 							 false (history.back())
	 	Ajax로 처리						 
	 */
	public String databoard_update_ok(DataBoardVO vo,RedirectAttributes ra) {
		
		/*
		 	return "redirect:detail.do"
		 		=> sendRedirect() => GET => ?를 이용해서 데이터 전송
		 			** request를 사용하지 않는다 (request가 초기화)
		 		=> RedirectAttributes이용해서 데이터 전송 가능
		 	return "databoard/list"
		 		=> forward => request에 값을 담아서 전송
		 		=> Model을 이용해서 전송
		 */
		// DAO에 연결
		dao.databoardUpdate(vo);
		ra.addAttribute("no", vo.getNo());
		// detail.do?no=값
		return "redirect:detail.do";
	}
	
	// 찾기
	@PostMapping("find.do")
	public String databoard_find(String fs, String ss,Model model) {
		// DB검색 => 데이터 읽기
		Map map=new HashMap();
		map.put("fs",fs);
		map.put("ss",ss);
		List<DataBoardVO> list = dao.databoardFindData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("len", list.size());
		return "databoard/find";
	}
}	
	
	
	
	
	
	
	
	

