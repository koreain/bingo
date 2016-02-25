package com.sist.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static UserDAO dao;
   private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
   
   ArrayList<UserDTO> arDTO = new ArrayList<>();
   // UserDTO를 여러개 담을 객체 : 테이블 내용 / select 전체 내용
   
   
   public UserDAO(){
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
      }catch(Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   // DB 연결 메소드
   public static UserDAO newInstance(){
      if(dao==null)
         dao = new UserDAO();
      return dao;
   }
   
   public void getConnection(){
      try{
         conn = DriverManager.getConnection(URL, "scott", "tiger");
         // conn scott/tiger
      }catch(Exception ex){
         
      }
   }
   
   public void disConnection(){
      try{
         if(ps!=null) ps.close();
         // ps에 ==> InputStream, OutputStream
         if(conn!=null) conn.close();
         // socket.close();
         // exit
      }catch(Exception ex){}
   }
   
   public List<UserDTO> getList(){
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과
      
      ArrayList<UserDTO> list = new ArrayList<>();
      try{
         String sql = "SELECT * FROM USER_INFO"; // 쿼리
         getConnection();
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
            dto.setUser_date(rs.getDate("user_date"));
            list.add(dto);
         }
      }catch(Exception e){
      }finally{
         try{
            disConnection();
            if(rs!=null){
               rs.close();
            }
         }catch(Exception ex){
            
         }
      }
      return list;
   }
   
   // 한사람의 회원 정보를 얻는 메소드
   public UserDTO getUserDTO(String user_id){
      UserDTO dto = new UserDTO();
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과
      try{
         getConnection();
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
            dto.setUser_date(rs.getDate("user_date"));
         }
      }catch(Exception e){
         System.out.println(e+"getUserDTO에서 에러");
      }finally{
         try{
            disConnection();
            if(rs!=null){
               rs.close();
            }
         }catch(Exception ex){
            
         }
      }
      return dto;
   }
   
   // 회원 등록
   public boolean insertUser(UserDTO dto){
      boolean check = false;
      PreparedStatement ps = null;
      try{
         getConnection();
         String sql = "insert into user_info(user_id,user_pw,user_name,user_nickname,user_sex,"
                     + "user_win,user_lose,user_avatar,user_date) values(?,?,?,?,?,?,?,?,sysdate)";
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
         
         if(result>0) // 가입성공
            check = true;
         else
            System.out.println("insert 실패");
         
      }catch(Exception e){
         System.out.println(e+"insertUser에서 에러");
      }finally{
         try{
            disConnection();
         }catch(Exception ex){
            
         }
      }
      return check;
   }
   
   public void userUpdate(String user_id, int user_win, int user_lose){
      try{
         getConnection();
         String sql = "UPDATE user_info SET user_win=?,user_lose=? where user_id=?";
         ps = conn.prepareStatement(sql);
         ps.setInt(1, user_win);
         ps.setInt(2, user_lose);
         ps.setString(3, user_id);
         ps.executeUpdate(); // ==> 데이터 수정은 Update();
      }catch(Exception ex){
         System.out.println(ex.getMessage());
      }finally{
         disConnection();
      }
   }
}