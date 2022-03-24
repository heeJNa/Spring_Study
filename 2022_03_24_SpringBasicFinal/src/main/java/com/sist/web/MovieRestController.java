package com.sist.web;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.SawonVO;

@RestController
public class MovieRestController {
	@GetMapping(value="sawon/list.do",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public SawonVO sawonData() {
		SawonVO s=new SawonVO(1,"홍길동","개발부","사원");
		return s;
	}
}
