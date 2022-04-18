package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="music")
@Setter
@Getter
// save (insert, update), delete, findAll, findOne
public class MusicEntity {
	
	@Id
	private int no;
	private int cno,idcrement;
	private String title,singer,album,state,poster,mkey;
}
