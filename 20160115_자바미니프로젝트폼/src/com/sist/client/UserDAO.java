package com.sist.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserDAO {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USER = "scott"; // DB ID
	private static final String PASS = "tiger"; // DB ID
	
	ArrayList<UserDTO> arDTO = new ArrayList<>();
	// UserDTO�� ������ ���� ��ü : ���̺� ���� / select ��ü ����
	
	// DB ���� �޼ҵ�
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<UserDTO> getList(){
		Connection conn = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���
		
		ArrayList<UserDTO> list = new ArrayList<>();
		try{
			String sql = "SELECT * FROM USER_INFO"; // ����
			conn = getConn();
			ps = conn.prepareStatement(sql); // 
			rs = ps.executeQuery();
			while(rs.next()){
				UserDTO dto = new UserDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_pw(rs.getString("user_pw"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				dto.setUser_sex(rs.getString("user_sex"));
				dto.setUser_win(rs.getInt("user_win"));
				dto.setUser_lose(rs.getInt("user_lose"));
				dto.setUser_avatar(rs.getString("user_avatar"));
				list.add(dto);
			}
		}catch(Exception e){
		}finally{
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex){
				
			}
		}
		return list;
	}
	
	// �ѻ���� ȸ�� ������ ��� �޼ҵ�
	public UserDTO getUserDTO(String user_id){
		UserDTO dto = new UserDTO();
		Connection conn = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���
		try{
			conn = getConn();
			String sql = "select * from user_info where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()){
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_pw(rs.getString("user_pw"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				dto.setUser_sex(rs.getString("user_sex"));
				dto.setUser_win(rs.getInt("user_win"));
				dto.setUser_lose(rs.getInt("user_lose"));
				dto.setUser_avatar(rs.getString("user_avatar"));
			}
		}catch(Exception e){
			System.out.println(e+"getUserDTO���� ����");
		}finally{
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex){
				
			}
		}
		return dto;
	}
	
	// ȸ�� ���
	public boolean insertUser(UserDTO dto){
		boolean check = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConn();
			String sql = "insert into user_info(user_id,user_pw,user_name,user_nickname,user_sex,"
							+ "user_win,user_lose,user_avatar) values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_id());
			ps.setString(2, dto.getUser_pw());
			ps.setString(3, dto.getUser_name());
			ps.setString(4, dto.getUser_nickname());
			ps.setString(5, dto.getUser_sex());
			ps.setInt(6, dto.getUser_win());
			ps.setInt(7, dto.getUser_lose());
			ps.setString(8, dto.getUser_avatar());
			int result = ps.executeUpdate();
			
			if(result>0) // ���Լ���
				check = true;
			else
				System.out.println("insert ����");
			
		}catch(Exception e){
			System.out.println(e+"insertUser���� ����");
		}finally{
			try{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex){
				
			}
		}
		return check;
	}
}
