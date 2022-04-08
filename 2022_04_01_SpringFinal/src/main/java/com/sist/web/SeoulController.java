package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class SeoulController {
  @Autowired
  private SeoulDAO dao; 
  
  @RequestMapping("seoul/seoul_make.do")
  public String seoul_make(String fd,Model model){
	  if(fd==null)
		  fd="강남";
	  SeoulVO svo=dao.seoulMyLocationData(fd);
	  FoodVO fvo1= dao.seoulMyFoodData1(fd);
	  FoodVO fvo2= dao.seoulMyFoodData2(fd);
	  SeoulVO nvo = dao.seoulMyNatureData(fd);
	  model.addAttribute("svo", svo);
	  model.addAttribute("nvo", nvo);
	  model.addAttribute("fvo1", fvo1);
	  model.addAttribute("fvo2", fvo2);
	  model.addAttribute("fd", fd);
	  return "seoul/seoul_make";
  }
  
  @GetMapping("seoul/location.do")
  public String seoul_location(String page, Model model){
	  // DAO
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  Map map=new HashMap();
	  int rowSize=12;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=rowSize*curpage;
	  
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> rList=dao.seoulLocationData(map);
	  int totalpage=dao.seoulLocationTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  //                (10-1)/10*10 => 0+1  ==> 1
	  // 1(curpage=1,10) ,  11(curpage=11~20) , 21 , 31
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; //10,20....
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // list.jsp에서 출력에 필요한 데이터 전송
	  model.addAttribute("curpage", curpage);
	  model.addAttribute("totalpage", totalpage);
	  model.addAttribute("rList", rList);
	  model.addAttribute("startPage", startPage);
	  model.addAttribute("endPage", endPage);
	  return "seoul/location";
  }
  @GetMapping("seoul/nature.do")
  public String seoul_nature(String page,Model model){
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  Map map=new HashMap();
	  int rowSize=12;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=rowSize*curpage;
	  
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> rList=dao.seoulNatureData(map);
	  int totalpage=dao.seoulNatureTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  //                (10-1)/10*10 => 0+1  ==> 1
	  // 1(curpage=1,10) ,  11(curpage=11~20) , 21 , 31
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; //10,20....
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // list.jsp에서 출력에 필요한 데이터 전송
	  model.addAttribute("curpage", curpage);
	  model.addAttribute("totalpage", totalpage);
	  model.addAttribute("rList", rList);
	  model.addAttribute("startPage", startPage);
	  model.addAttribute("endPage", endPage);
	  return "seoul/nature";
  }
  
  @GetMapping("seoul/hotel.do")
  public String seoul_hotel(String page,Model model){
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  Map map=new HashMap();
	  int rowSize=12;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=rowSize*curpage;
	  
	  map.put("start", start);
	  map.put("end", end);
	  
	  List<SeoulVO> rList=dao.seoulHotelData(map);
	  int totalpage=dao.seoulHotelTotalPage();
	  
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	  //                (10-1)/10*10 => 0+1  ==> 1
	  // 1(curpage=1,10) ,  11(curpage=11~20) , 21 , 31
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; //10,20....
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // list.jsp에서 출력에 필요한 데이터 전송
	  model.addAttribute("curpage", curpage);
	  model.addAttribute("totalpage", totalpage);
	  model.addAttribute("rList", rList);
	  model.addAttribute("startPage", startPage);
	  model.addAttribute("endPage", endPage);
	  return "seoul/hotel";
  }
  /*
   *   제어 
   *   ---
   *    클라이언트가 보내준 요청 정보 제어 : Front Controller (DispatcherServlet) 
   *    요청 처리 제어 (@Controller => 사용자 정의)
   *   
   */
  // 요청 처리를 제어하는 클래스 => Controller
  @GetMapping("seoul/location_detail.do")
  public String seoul_location_detail(int no,Model model){
	 
	  SeoulVO vo=dao.seoulLocationDetailData(no);
	  String address=vo.getAddress();
	  address=address.substring(address.indexOf(" "));
	  address=address.trim();
	  vo.setAddress(address);
	  String addr1=address.substring(address.indexOf(" ")+1);
	  String addr2=addr1.trim().substring(0,addr1.indexOf(" "));
	  List<FoodVO> fList=dao.seoulLocationFoodHouse(addr2);
	  
	  model.addAttribute("vo", vo);
	  model.addAttribute("fList", fList);
	  return "seoul/location_detail";
  }
  @GetMapping("seoul/nature_detail.do")
  public String seoul_nature_detail(int no,Model model) {
	  
	  SeoulVO vo=dao.seoulNatureDetailData(no);
	  String address=vo.getAddress();
	  address=address.substring(address.indexOf(" "));
	  address=address.trim();
	  vo.setAddress(address);
	  String addr1=address.substring(address.indexOf(" ")+1);
	  String addr2=addr1.trim().substring(0,addr1.indexOf(" "));
	  List<FoodVO> fList=dao.seoulLocationFoodHouse(addr2);
	  
	  model.addAttribute("vo", vo);
	  model.addAttribute("fList", fList);
	  return "seoul/nature_detail";
  }
  @GetMapping("seoul/hotel_detail.do")
  public String hotel_detail(int no,Model model) {
	  
	  SeoulVO vo=dao.seoulHotelDetailData(no);
	  String address=vo.getAddress();
	  address=address.substring(address.indexOf(" "));
	  address=address.trim();
	  vo.setAddress(address);
	  String addr1=address.substring(address.indexOf(" ")+1);
	  String addr2=addr1.trim().substring(0,addr1.indexOf(" "));
	  List<FoodVO> fList=dao.seoulLocationFoodHouse(addr2);
	  
	  model.addAttribute("vo", vo);
	  model.addAttribute("fList", fList);
	  
	  return "seoul/hotel_detail";
  }
}
