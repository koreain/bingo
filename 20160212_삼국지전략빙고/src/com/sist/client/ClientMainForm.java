package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.net.*;
import java.io.*;
import com.sist.client.GameLayout.TimeLimit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner; //테스트용
public class ClientMainForm extends JFrame
implements ActionListener, Runnable
{
   private String name;
   private Socket socket;
   private String serverIp = "211.238.142.40";
 
   Login login=new Login();
   WaitRoom wr=new WaitRoom();
   ChoiceNation cn=new ChoiceNation();
   CardLayout card=new CardLayout();
   GameLayout game=new GameLayout();
   GameInfo gi=new GameInfo();
   
   ImageIcon mainIcon;//타이틀창 아이콘
   static Thread t1=new TimeLimit();//시간제한바 스레드 
   
   Socket s;
    BufferedReader in;
    OutputStream out;
   ClientMainForm() 
   {
      super("삼국지 전략빙고");//타이틀 제목
      mainIcon=new ImageIcon("img\\타이틀아이콘.png");
      this.setIconImage(mainIcon.getImage());
      
      setLayout(card);//BorderLayout
      add("LOG",login);
      add("WR",wr);
      add("ChoiceNation",cn);//나라선택 화면
      add("GAME",game);
      setSize(1200,970);
      setVisible(true);
      login.b1.addActionListener(this);//카드레이아웃이 될 버튼들 추가
      login.b2.addActionListener(this);
      login.b3.addActionListener(this);//회원가입
      
      wr.b3.addActionListener(this); //test
      wr.b5.addActionListener(this); //게임정보 
      wr.b6.addActionListener(this); //나가기
      
      cn.nation0.addActionListener(this);
      cn.nation1.addActionListener(this);
      cn.nation2.addActionListener(this);
      game.exit.addActionListener(this); //항복 버튼 
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false); //윈도우창 고정
      
   }
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      try
      {
         UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
      }catch(Exception ex){}
      new ClientMainForm();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource()==login.b1) //로그인을 누르면 대기실로 이동
      {
         String id=login.tf.getText();
         String name=String.valueOf(login.pf.getPassword());
         String sex="a";
         String pos="a";
         String sendData=id+"|"+name+"|"+sex+"|"+pos+"\n";
         try
         {
            s=new Socket("211.238.142.40", 12345);
            in=new BufferedReader(
                   new InputStreamReader(s.getInputStream()));
                out=s.getOutputStream();
              out.write((sendData).getBytes());
                System.out.println(s.getInetAddress());
         }catch(Exception ex){}
         new Thread(this).start();
         card.show(getContentPane(), "WR");
      }
      else if(e.getSource()==login.b2) //취소를 누르면 프로그램 종료
      {
         System.exit(0);
      }
      else if(e.getSource()==login.b3) // 회원가입 버튼
      {
         SignUp su = new SignUp();
         su.setVisible(true);
      }
      
      else if(e.getSource()==wr.b5) // 게임정보 버튼
      {
         gi.setVisible(true); 
      }
      else if(e.getSource()==wr.b6) //나가기를 누르면 프로그램 종료
      {
         dispose();
         System.exit(0);
      }
      else if(e.getSource()==wr.b3) //1:1게임을 누르면 진영선택창
      {
         card.show(getContentPane(), "ChoiceNation");
      }
      else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
            ||e.getSource()==cn.nation2)
      {
    	 CoinFlip cf=new CoinFlip(); 
         cf.coinEnd=true;
         cf.setVisible(true); 
         if(cf.coinEnd==false) 
         { 
            card.show(getContentPane(), "GAME"); 
            ClientMainForm.t1=new TimeLimit();
            t1.start();
            game.requestFocus();  
         } 
         
      }
      else if(e.getSource()==game.exit) //게임이 끝났을 때 나가기 버튼
      {
         ClientMainForm.t1.interrupt();
         card.show(getContentPane(), "WR");
         game.removeAll();
         game=new GameLayout();
         GameProcess.gameReset();
         //game.gameReset();
         add(game,"GAME");
         System.out.println("플레이어1 첫번쨰 숫자: "+GameProcess.numArr1[0]);
         System.out.println("플에이어1 첫번쨰 숫자(T/F): "+GameProcess.bingo1[0][0]);
         System.out.println("플레이어턴 구분>> true:플레이어1 /// false:플레이어2");
         System.out.println("플레이어턴: "+GameProcess.playerTurn);
      }
   }
   @Override
   public void run() {
      // TODO Auto-generated method stub
      try
      {
         while(true)
         {
            String msg=in.readLine();
            StringTokenizer st=
               new StringTokenizer(msg, "|");
            String[] data={st.nextToken(),
                      st.nextToken(),
                      st.nextToken(),
                      st.nextToken()};
             wr.model2.addRow(data);
         }
      }catch(Exception ex){}
   }
}