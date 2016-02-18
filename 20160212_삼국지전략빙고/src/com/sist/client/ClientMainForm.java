package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.*;
import java.io.*;
import com.sist.client.GameLayout.TimeLimit;
import com.sist.client.GameLayout.endThread;
import com.sist.common.Function;
public class ClientMainForm extends JFrame
implements ActionListener, Runnable
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	GameInfo gi=new GameInfo();
	
	ImageIcon mainIcon;//타이틀창 아이콘
	Thread t1=game.new TimeLimit();//시간제한바 스레드 
	Socket s;
    BufferedReader in;
    OutputStream out;
 // 개인 정보
    String myId,myRoom;
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
		
		wr.b2.addActionListener(this); //test(게임방법)
		wr.b3.addActionListener(this); //게임정보 
		wr.b4.addActionListener(this); //나가기
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //항복 버튼 
		
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
	public void connection(String id,String name,
	         String sex,String avata)
	{
		try
		{
			s=new Socket("211.238.142.40", 33333);
			// s => server
			in=new BufferedReader(
					new InputStreamReader(
							s.getInputStream()));
			out=s.getOutputStream();
			out.write((Function.LOGIN+"|"+id+"|"+
					name+"|"+sex+"|"+
					avata+"\n").getBytes());
		}catch(Exception ex){}
		new Thread(this).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1) //로그인을 누르면 대기실로 이동
		{
			boolean loginOk=false; 
			boolean idOk=false; 
			String id=login.tf.getText(); 
			String pw=String.valueOf(login.pf.getPassword()); 
			UserDAO user=new UserDAO(); 
			UserDTO userInfo=new UserDTO(); // id로 접근한 한사람의 정보  
			userInfo=user.getUserDTO(id); 
			String[] arrayId=new String[user.getList().size()]; 
			for(int i=0; i<user.getList().size();i++) 
			{ 
				arrayId[i]=user.getList().get(i).getUser_id(); 
			} 
			for(int i=0; i<arrayId.length;i++) 
			{ 
				if(arrayId[i].equals(id)) 
				{ 
					idOk=true; 
				 
					if(userInfo.getUser_pw().equals(pw)) 
					{ 
						loginOk=true; 
					} 
					else if(!userInfo.getUser_pw().equals(pw)) 
					{ 
						loginOk=false; 
						JOptionPane.showMessageDialog(login,"비밀번호를 확인해 주세요"); 
						login.pf.setText(""); 
							login.pf.requestFocus(); 
						} 
					} 
					 
				} 
				if(!idOk) 
				{ 
					JOptionPane.showMessageDialog(login,"아이디가 존재하지 않습니다."); 
					login.tf.setText(""); 
					login.pf.setText(""); 
					login.tf.requestFocus(); 
				} 
	
				if(loginOk) 
				{ 
//					UserDTO sendData=userInfo;//넘겨줄 유저정보 재정의 
		            myId=userInfo.getUser_id();
		            String myName=userInfo.getUser_nickname();
		            String mySex=userInfo.getUser_sex();
		            String myAvatar=userInfo.getUser_avatar();
		            connection(myId,myName,mySex,myAvatar);
		            myRoom="대기실";
					card.show(getContentPane(), "WR"); 
				} 

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
		else if(e.getSource()==wr.b2) //1:1게임을 누르면 진영선택창
		{
			card.show(getContentPane(), "ChoiceNation");
		}
		else if(e.getSource()==wr.b3) // 게임정보 버튼
		{
			gi.setVisible(true); 
		}
		else if(e.getSource()==wr.b4) //나가기를 누르면 프로그램 종료
		{
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
			CoinFlip cf=new CoinFlip(); 
			CoinFlip.coinEnd=true;
			cf.setVisible(true); 
			if(CoinFlip.coinEnd==false) 
			{ 
				card.show(getContentPane(), "GAME"); 
				game.new TimeLimit();
				t1.start();
				game.requestFocus();  
			}
			GameLayout.endBtn.addActionListener(this);//빙고마무리버튼
			GameLayout.gameEnd.addActionListener(this);//인게임 나가기 버튼
		}
		else if(e.getSource()==GameLayout.exit) //항복 버튼
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "항복하시겠습니까?", "항복", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //예를 누르면 게임 끝내기 쓰레드
			{
				GameLayout.imageVisibleFalse();
				repaint();
				GameProcess.playerWon=false;
				game.new endThread().start();
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//빙고 마무리
		{
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			game.new endThread().start();
		}
		else if(e.getSource()==GameLayout.gameEnd) //나가기
		{
			if(GameProcess.playerWon==true) //승패 이미지 없애기
				GameLayout.wonX+=1200;
			else if(GameProcess.playerWon==false)
				GameLayout.loseX+=1200;
			GameLayout.bingoEnd=false;
			repaint();
			t1.interrupt();
			card.show(getContentPane(), "WR");
			game.removeAll();
			GameProcess.gameReset();
			game=new GameLayout();
			add(game,"GAME");
			GameLayout.imageVisibleTrue();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				// 클라이언트 => 요청값을 받는다
	            String msg=in.readLine();
	            StringTokenizer st=
	               new StringTokenizer(msg, "|");
	            // 100|id|sex|name|avata
	            int protocol=Integer.parseInt(st.nextToken());
	            switch(protocol)
	            {
	                case Function.LOGIN:
	                {
	                   String[] data={
	                      st.nextToken(),   //id (Server에서 보낸 값)
	                      st.nextToken(),   //nickname (Server에서 보낸 값)
	                      st.nextToken(),   //sex (Server에서 보낸 값)
	                      st.nextToken()   //pos (Server에서 보낸 값)
	                   };
	                   wr.model2.addRow(data);
	                }
	                break;
	                case Function.MYLOG:
	                {
	                   String id=st.nextToken();
	                   card.show(getContentPane(), "WR");
	                }
	                break;
	                /*case Function.WAITCHAT:
	                {
	                   wr.ta.append(st.nextToken()+"\n");
	                   wr.bar.setValue(wr.bar.getMaximum());
	                   wr.tf.setText("");
	                }*/

	            }
			}
		}catch(Exception ex){}
	}
}