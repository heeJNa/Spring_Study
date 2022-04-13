package com.sist.music.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// name은 table명
@Entity(name="music")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MusicEntity {
	@Id
	private int no;
	private int cno,idcrement;
	private String title,singer,album,state,poster,mkey;
}
