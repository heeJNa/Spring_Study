package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="movie")
@Setter
@Getter
public class MovieEntity {
	@Id
	private int mno;
	private int cno;
	private String title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey;
}
