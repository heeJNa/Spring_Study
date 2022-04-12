package com.sist.temp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:mysql://localhost:3306/mydb?serverTimeZone=UTC";
	
	// 드라이버 등록
	public MusicDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 연결
	public void getConnetion() {
		try {
			conn=DriverManager.getConnection(URL,"root","rlal!@1208");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 해제 
	public void disConnetion() {
			try {
				if(ps !=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public void musicInsert(MusicVO vo) {
		try {
			getConnetion();
			String sql="INSERT INTO music(cno,title,singer,album,state,idcrement,poster,mkey) "
					+ "VALUES(?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCno());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getSinger());
			ps.setString(4, vo.getAlbum());
			ps.setString(5, vo.getState());
			ps.setInt(6, vo.getIdcrement());
			ps.setString(7, vo.getPoster());
			ps.setString(8, vo.getMkey());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnetion();
		}
	}
}
