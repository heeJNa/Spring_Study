package com.sist.music.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="movie")
@Getter
@Setter
public class MovieEntity {
	@Id
	private int mno;
	private int cno;
	private String title,time,grade,reserve,genre,regdate,
					director,actor,showUser,poster,story,mkey;
}
