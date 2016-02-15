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
				dto.setUser_sex(rs.getString("user_sex"));
				dto.setUser_email(rs.getString("user_email"));
				dto.setUser_intro(rs.getString("user_intro"));
				list.add(dto);
			}
		}catch(Exception e){
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
				dto.setUser_sex(rs.getString("user_sex"));
				dto.setUser_email(rs.getString("user_email"));
				dto.setUser_intro(rs.getString("user_intro"));
			}
		}catch(Exception e){
			System.out.println(e+"getUserDTO���� ����");
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
			String sql = "insert into user_info(user_id,user_pw,user_name,user_sex,"
							+ "user_email,user_intro) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_id());
			ps.setString(2, dto.getUser_pw());
			ps.setString(3, dto.getUser_name());
			ps.setString(4, dto.getUser_sex());
			ps.setString(5, dto.getUser_email());
			ps.setString(6, dto.getUser_intro());
			int result = ps.executeUpdate();
			
			if(result>0) // ���Լ���
				check = true;
			else
				System.out.println("insert ����");
			
		}catch(Exception e){
			System.out.println(e+"insertUser���� ����");
		}
		return check;
	}
	
	
	
	// �����ͺ��̽� ���� �ٽ� �ҷ�����
//	public void userSelectAll(DefaultTableModel model){
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		try{
//			conn = getConn();
//            String sql = "select * from user_info order by user_name asc";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//           
//            // DefaultTableModel�� �ִ� ������ �����
//            for(int i=0; i<model.getRowCount();) {
//                model.removeRow(0);
//            }
// 
//            while (rs.next()){
//                Object data[] = {rs.getString(1), rs.getString(2),rs.getString(3), 
//                				rs.getString(4), rs.getString(5), rs.getString(6)};
//                model.addRow(data);                
//            }
//		}catch(Exception e){
//			System.out.println(e +"userSelectAll���� ����");
//		}finally{
//			if(rs != null){
//				try{
//					rs.close();
//				}catch(SQLException e){
//				}
//			}
//			if(ps != null){
//				try{
//					ps.close();
//				}catch(SQLException e){
//				}
//			}
//			if(conn != null){
//				try{
//					conn.close();
//				}catch(SQLException e){
//				}
//			}
//		}
//	}
}
