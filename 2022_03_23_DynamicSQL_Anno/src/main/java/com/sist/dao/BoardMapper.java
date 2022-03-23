package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface BoardMapper {
	/*
	 	<select id="boardListData" resultType="BoardVO">
			SELECT no,subject,name,regdate,hit
			FROM project_freeboard
		</select>
	 */
	@Select("SELECT no,subject,name,regdate,hit "
			+ "FROM project_freeboard")
	public List<BoardVO> boardListData();
	/*
	 		<select id="boardFindData" resultType="BoardVO" parameterType="hashmap">
				SELECT no,subject,name,regdate,hit
				FROM project_freeboard
				WHERE
				<trim prefix="(" suffix=")" prefixOverrides="OR">
					<foreach collection="fsArr" item="fd">
						<trim prefix="OR">
						<choose>
							<when test="fd=='N'.toString()">
								name LIKE '%'||#{ss}||'%'
							</when>
							<when test="fd=='S'.toString()">
								subject LIKE '%'||#{ss}||'%'
							</when>
							<when test="fd=='C'.toString()">
								content LIKE '%'||#{ss}||'%'
							</when>
						</choose>
						</trim>
					</foreach>
				</trim>
			</select>
	 */
	@Select("<script>"
			+"SELECT no,subject,name,regdate,hit "
			+ "FROM project_freeboard "
			+ "WHERE "
			+ "<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"OR\">" + 
			"					<foreach collection=\"fsArr\" item=\"fd\">" + 
			"						<trim prefix=\"OR\">" + 
			"						<choose>" + 
			"							<when test=\"fd=='N'.toString()\">" + 
			"								name LIKE '%'||#{ss}||'%'" + 
			"							</when>\n" + 
			"							<when test=\"fd=='S'.toString()\">" + 
			"								subject LIKE '%'||#{ss}||'%'" + 
			"							</when>\n" + 
			"							<when test=\"fd=='C'.toString()\">" + 
			"								content LIKE '%'||#{ss}||'%'" + 
			"							</when>" + 
			"						</choose>" + 
			"						</trim>" + 
			"					</foreach>" + 
			"				</trim>"
			+ "</script>")
	public List<BoardVO> boardFindData(Map map);
}
