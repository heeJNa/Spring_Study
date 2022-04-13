package com.sist.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// https://www.yna.co.kr/entertainment/movies?site=navi_entertainment_depth02
@SpringBootApplication
@ComponentScan(basePackages = {"com.sist.music.controller","com.sist.music.dao","com.sist.music.entity"})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
