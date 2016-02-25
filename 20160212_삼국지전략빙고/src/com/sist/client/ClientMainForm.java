package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
//import com.sist.client.GameLayout.TimeLimit;
import com.sist.common.*;
import com.sist.server.*;

public class ClientMainForm extends JFrame
implements ActionListener, Runnable, MouseListener
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	GameInfo gi=new GameInfo();
	MakeRoom mr=new MakeRoom();
	ChatRoom cr=new ChatRoom();
	UserInfo uif=new UserInfo();
	
	ImageIcon mainIcon;//타이틀창 아이콘
	static Thread t1=new TimeLimit();//시간제한바 스레드 
	Thread paintthread=game.new paintThread();
	Socket s;
    BufferedReader in;
    OutputStream out;
    Vector<Room> roomVc = new Vector<Room>();
    
	static int colorInt = 0;
	static int percent = 0;
    
 // 개인 정보
    String myId,pos;
    int myRoom;
	ClientMainForm()
	{
		super("삼국지 전략빙고");//타이틀 제목
		mainIcon=new ImageIcon("img\\타이틀아이콘.png");
		this.setIconImage(mainIcon.getImage());
		
		setLayout(card);//BorderLayout
		add("LOG",login);
		add("WR",wr);
		//add("CR",cr);
		add("ChoiceNation",cn);//나라선택 화면
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		login.b1.addActionListener(this);//카드레이아웃이 될 버튼들 추가
		login.b2.addActionListener(this);
		login.b3.addActionListener(this);//회원가입
		login.tf.addActionListener(this);
		login.pf.addActionListener(this);
		
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this); //test(게임방법)
		wr.b3.addActionListener(this); //게임정보 
		wr.b4.addActionListener(this); //나가기
		wr.tf.addActionListener(this); //대기실 채팅내용 받기
	    wr.table1.addMouseListener(this);
	    wr.userinfoBtn.addActionListener(this);
	    uif.btn_NO.addActionListener(this);
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //항복 버튼 
		GameLayout.endBtn.addActionListener(this);//빙고마무리버튼
		
		mr.b1.addActionListener(this); //방만들기 창의 방만들기 버튼
		mr.b2.addActionListener(this); //방만들기 창의 방만들기 취소 버튼
		
		cr.b1.addActionListener(this);
		cr.b2.addActionListener(this);
		cr.b3.addActionListener(this);
		cr.tf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //윈도우창 고정
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) { 
				try 
				{   //내아이디를 보내주고 waitVc 목록에서 지워주길 요청한다..
					out.write((Function.EXIT+"\n").getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				System.exit(0);
			}
        });
		
		/////////// GameLayout action 모음 ///////////////  
		for(int i=0;i<6;i++){  
			GameLayout.jypgChoice[i].addActionListener(this); // 이벤트 대기  
		}  
	  
		// 나와 너의 빙고판 안의 모든 버튼 actionListener 추가 메소드  
/*		for (int i = 0; i < 3; i++) {  
			for (int j = 0; j < 25; j++) {  
				GameLayout.a1[i][j].addActionListener(this);  
				GameLayout.a2[i][j].addActionListener(this);  
			}  
		} */ 
		GameLayout.defPGchoice2.addActionListener(this);  
		GameLayout.defPGchoice1.addActionListener(this);
		for(int i=0;i<2;i++){  
			for(int j=0;j<3;j++){  
				GameLayout.fury[i][j].addActionListener(this);  
			}  
		}  
		for (int i = 0; i < 3; i++) // 장수 얼굴 버튼 액션리스너  
		{  
			ChoiceNation.jangSu1[i].addActionListener(this);  
			ChoiceNation.jangSu2[i].addActionListener(this);  
		}  
		GameLayout.btnAtt.addActionListener(this);  
		GameLayout.btnDef.addActionListener(this);  
		GameLayout.btnTrick.addActionListener(this);  
		GameLayout.timeOut.addActionListener(this); // 턴종료 버튼  

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
	         String sex,String avata, int win, int lose, String nick, String date)
	{
		try
		{
			s=new Socket("211.238.142.40", 33333); //211.238.142.39 localhost
			// s => server
			in=new BufferedReader(
					new InputStreamReader(
							s.getInputStream()));
			out=s.getOutputStream();
			out.write((Function.LOGIN+"|"+id+"|"+
					name+"|"
					+sex+"|"
					+avata+"|"
					+win+"|"
					+lose+"|"
					+nick+"|"
					+date+"|"
					+"\n").getBytes());
		}catch(Exception ex){}
		new Thread(this).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1||e.getSource()==login.tf||e.getSource()==login.pf) //로그인을 누르면 대기실로 이동
		{   System.out.println("testlogin");
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
		            int myWin=userInfo.getUser_win();
		            int myLose=userInfo.getUser_lose();
		            String nick=userInfo.getUser_name();
		            String date = userInfo.getUser_date().toString();
		            connection(myId,myName,mySex,myAvatar,myWin,myLose,nick,date);
		            pos="대기실";
		            System.out.println(myAvatar);
//					card.show(getContentPane(), "WR"); 
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
		else if(e.getSource()==wr.b1)
		{
			System.out.println("test_wr.b1");
			mr.tf.setText("");
			mr.open.setSelected(true);
			mr.la3.setVisible(false);
			mr.pf.setVisible(false);
			mr.setVisible(true);
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
			try 
	         {   //내아이디를 보내주고 waitVc 목록에서 지워주길 요청한다..
	            out.write((Function.EXIT+"\n").getBytes());
	         } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	         }
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==mr.b2)
		{
			mr.setVisible(false);
		}
		else if(e.getSource()==mr.b1)
		{
			String rn=mr.tf.getText().trim();
			if(rn.length()<1)
			{
				JOptionPane.showMessageDialog(this,
						"방이름을 입력하세요");
				mr.tf.requestFocus();
				return;
			}
			String temp="";
/*			for(int i=0;i<wr.model1.getRowCount();i++)
			{
				temp=wr.model1.getValueAt(i, 0).toString();
				if(rn.equals(temp))
				{
					JOptionPane.showMessageDialog(this,
							"이미 존재하는 방입니다\n다른 이름을 입력하세요");
					mr.tf.setText("");
					mr.tf.requestFocus();
					return;
				}
			}*/
			String state="",pwd="";
			if(mr.open.isSelected())
			{
				state="공개";
				pwd=" ";
			}
			else
			{
				state="비공개";
				pwd=String.valueOf(mr.pf.getPassword());
			}
			int inwon=2;
			// 서버로 전송 
			try
			{
				out.write((Function.MAKEROOM+"|"
			               +rn+"|"
			               +state+"|"
			               +pwd+"|"
			               +"\n").getBytes());
			}catch(Exception ex){}
			mr.setVisible(false);
		}
		else if(e.getSource()==wr.tf) //대기실 채팅내용
		{
			  String msg=wr.tf.getText().trim();
			  wr.initStyle();
			  String color = wr.box.getSelectedItem().toString();
				if(msg.length()<1)
					return;
				try
				{
					out.write((Function.WAITCHAT+"|"
									+msg+"|"
									+color+"\n").getBytes());
				}catch(Exception ex){
					System.out.println("wr.tf오류"+ex.getMessage());
				}
				wr.tf.setText("");
		}
		else if(e.getSource()==cr.tf) //chatroom 채팅내용
		{
			String msg=cr.tf.getText().trim();
			cr.initStyle();
			String color = cr.box.getSelectedItem().toString();
			if(msg.length()<1)
				return;
			try
			{
				out.write((Function.ROOMCHAT+"|"
						+msg+"|"+color+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){
				System.out.println("cr.tf오류"+ex.getMessage());
			}
			cr.tf.setText("");
		}
	      else if(e.getSource()==cr.b1) 
	      {
	         if(cr.b1.getText().equals("준비취소"))
	         {
	            cr.b1.setText("준비");
	            try 
	            {
	               out.write((Function.GAMENO+"|"
	                        +myRoom+"|"
	                        +myId+"|"
	                        +"\n").getBytes());
	            } catch (Exception e2) {
	               // TODO: handle exception
	               System.out.println("cr.b1 준비취소 오류: "+e2.getMessage());
	            }
	         }
	         else
	         {
	            cr.b1.setText("준비취소");
	            try 
	            {
	               out.write((Function.GAMEYES+"|"
	                     +myRoom+"|"
	                     +myId+"|"
	                     +"\n").getBytes());
	            } catch (Exception e2) {
	               // TODO: handle exception
	               System.out.println("cr.b1 준비 오류: "+e2.getMessage());
	            }
	         }
	      }
		else if(e.getSource()==cr.b3)
		{
			try 
			{
				out.write((Function.ROOMOUT+"|"
						+myRoom+"|"//방번호
						+myId+"\n").getBytes());//본인아이디	
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("cr.b3오류"+e2.getMessage());
			}
		}
		else if(e.getSource()==cr.b2)//챗룸의 시작버튼
		{
			try
			{
				out.write((Function.GAMESTART+"|"+myRoom+"\n").getBytes());
			}catch(Exception ex){}
		}
		else if(e.getSource()==wr.userinfoBtn)
		{
			uif.setVisible(true);
		}
		else if(e.getSource()==uif.btn_NO)
		{
			uif.setVisible(false);
		}
///////////////////인게임
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
			//버튼을 누르면 위 촉 오 (0 1 2) 값이 들어감 => 인게임에서 빙고체크 시 각 나라아이콘 사용
			if(e.getSource()==cn.nation0){
				cn.chosenNation1=0;
				cn.bu1.setIcon(new ImageIcon("img\\빙고체크-위.png"));
			}
			if(e.getSource()==cn.nation1){
				cn.chosenNation1=1;
				cn.bu1.setIcon(new ImageIcon("img\\빙고체크-촉.png"));
			}
			if(e.getSource()==cn.nation2){
				cn.chosenNation1=2;
				cn.bu1.setIcon(new ImageIcon("img\\빙고체크-오.png"));
			}
			cn.choiceComplete=true;//나라 선택완료, 선택이 안되면 자동선택
			cn.jangsu();
			try
			{
				out.write((Function.CHOICENATION+"|"+myRoom+"|"+ChoiceNation.chosenNation1+"\n").getBytes());
			}catch(Exception ex){}
		}
/////////////////////////////////////////////////////////////////////////////////////////  
//////////빙고 체크(체크된 빙고가 아닐 때+스킬아이템을 클릭하지 않았을 때)
		if (GameLayout.bAttCheck1 == false && GameLayout.bDefCheck1 == false && GameLayout.bTrickCheck1 == false 
				&& GameLayout.bDefFCheck1 == false && GameLayout.bTrickFCheck1 == false) {  
			for (int k = 0; k < 3; k++) {  
				for (int l = 0; l < 3; l++) {  
					// 각자 선택한 위촉오에 따라 버튼 이미지아이콘 바꿔주기  
					ImageIcon nationIcon1 = null, nationIcon2 = null;  
					if(k==0)nationIcon1=GameLayout.bcIcon0;if(k==1)nationIcon1=GameLayout.bcIcon1;if(k==2)nationIcon1=GameLayout.bcIcon2;  
					if(l==0)nationIcon2=GameLayout.bcIcon0;if(l==1)nationIcon2=GameLayout.bcIcon1;if(l==2)nationIcon2=GameLayout.bcIcon2;  
					if (ChoiceNation.chosenNation1 == k && ChoiceNation.chosenNation2 == l)// 진영선택이  
																							// 되면  
					{
						for (int i = 0; i < 3; i++) {  
							for (int j = 0; j < 25; j++) {  
								// bingo[][]가 체크 안된것만 체크 가능,본인 차례일 때 체크 가능  
								if (e.getSource() == GameLayout.a1[i][j] && GameProcess.bingo1[i][j] == false  
										&& GameProcess.playerTurn == true && GameLayout.panCheck2[i][j] == false  
										 && GameProcess.bingoCheckChance1>0) 
								{
									GameProcess.bingoCheckChance1=0; //--;
									if (GameLayout.bingoEnd)  
										game.bingoEndProcess();
									try
									{
										out.write((Function.BINGOCHECK+"|"+myRoom+"|"+myId+"|"+i+"|"+j+"\n").getBytes());
									}catch(Exception ex){}
								}
							}  
						}  
					}  
				}  
			}  
		}  
  
		//////////// 스킬버튼 누르기  
		if (GameProcess.playerTurn == true && GameLayout.bAttCheck1 == false && GameLayout.bDefCheck1 == false 
				&& GameLayout.bTrickCheck1 == false && GameLayout.bDefFCheck1 == false  
				&& GameLayout.bTrickFCheck1 == false && GameProcess.skillChance1>0) {  
			if (e.getSource() == GameLayout.btnAtt && GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1 > 0)// 플레이어1  
																											// 공격  
																											// 스킬  
																											// 버튼  
			{  
				GameLayout.bAttCheck1 = true; // 스킬사용 가능하게 true  
				GameLayout.aNoticeX -= 1190; // 스킬설명 가져오기(1200으로 화면 밖에 있던 것이 -1190 해서 화면에  
									// 나옴)  
			} else if (e.getSource() == GameLayout.btnDef && GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1 > 0)// 방어스킬  
			{  
				int k = 0;  
				for (int i = 0; i < 3; i++) {  
					for (int j = 0; j < 25; j++) {  
						if (GameLayout.panCheck1[i][j])  
							k++;  
					}  
				}  
				if (k == 0) { // 만약 락 걸린 빙고판이 없으면, 아이템 버튼이 실행되지 않음  
					JOptionPane.showMessageDialog(this, "방해받은 지역이 없습니다.");  
					return;  
				}  
				GameLayout.bDefCheck1 = true;  
				GameLayout.dNoticeX -= 1190;  
			} else if (e.getSource() == GameLayout.btnTrick && GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1 > 0)// 책략스킬  
			{  
				GameLayout.bTrickCheck1 = true;  
				GameLayout.sNoticeX -= 1190;  
			} else if (e.getSource() == GameLayout.fury[1][0]) // 공격필살기 버튼  
			{  
				game.new AFNoticeThread().start();
				try
				{
					out.write((Function.ATTFURY+"|"+myRoom+"|"+myId+"\n").getBytes());
				}catch(Exception ex){}
			} else if (e.getSource() == GameLayout.fury[1][1]) // 방어필살기 버튼  
			{  
				if (GameProcess.numOfBingo2[0] + GameProcess.usingAttackSkill2 <= 0  
						&& GameProcess.numOfBingo2[1] + GameProcess.usingDefenseSkill2 <= 0  
						&& GameProcess.numOfBingo2[2] + GameProcess.usingStrategySkill2 <= 0) { // 만약  
																								// 상대  
																								// 아이템이  
																								// 없으면,  
																								// 필살기  
																								// 버튼이  
																								// 실행되지  
																								// 않음  
					JOptionPane.showMessageDialog(this, "파괴할 전술병기가 없습니다.");  
					return;  
				}  
				GameLayout.dFNoticeX -= 1190;  
				if (GameLayout.goongUsable1[1]) {  
					GameLayout.defPan2.setVisible(true);  
				}  
				GameLayout.bDefFCheck1 = true;  
			}  
			// !@#$%  
			else if (e.getSource() == GameLayout.fury[1][2]) // 책략필살기 버튼  
			{  
				if (GameLayout.goongUsable1[2]) {  
					GameLayout.sFNoticeX -= 1190;  
					for (int i = 0; i < 3; i++) {  
						GameLayout.jypgChoice[i].setVisible(true);//  
					}  
					GameLayout.bTrickFCheck1 = true;  
				}  
			}  
		}  
  
		// 공격,방어,책략스킬 사용  
	      for (int i = 0; i < 3; i++) {  
	         for (int j = 0; j < 25; j++) {  
	            if (e.getSource() == GameLayout.a2[i][j] && GameLayout.bAttCheck1 && !GameProcess.bingo2[i][j] && !GameLayout.panCheck1[i][j]) {// 공격스킬
	               GameLayout.aNoticeX += 1190; // 게임설명은 없애기
	               try
	               {
	                  out.write((Function.ATTSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            } else if (e.getSource() == GameLayout.a1[i][j] && GameLayout.bDefCheck1 && GameLayout.panCheck2[i][j]) {// 방어스킬  
	               GameLayout.dNoticeX += 1190; // 게임설명은 없애기
	               try
	               {
	                  out.write((Function.DEFSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            } else if (e.getSource() == GameLayout.a2[i][j] && GameLayout.bTrickCheck1 && !GameProcess.bingo2[i][j] && !GameLayout.panCheck1[i][j]) {
	               GameLayout.sNoticeX += 1190; // 게임설명은 없애기
	               try
	               {
	                  out.write((Function.TRICKSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            }  
	         }  
	      }  
		// 수비필살기 클릭 후, 상대판 버튼 클릭  
		if (e.getSource() == GameLayout.defPGchoice2 && GameLayout.bDefFCheck1 == true) {  
			game.new DFImageThread().start();  
  
			GameProcess.usingAttackSkill2 -= GameProcess.numOfBingo1[0];  
			GameProcess.usingDefenseSkill2 -= GameProcess.numOfBingo1[1];  
			GameProcess.usingStrategySkill2 -= GameProcess.numOfBingo1[2];  
			GameProcess.usingAttackSkill2 -= GameLayout.useAtt;  
			GameProcess.usingDefenseSkill2 -= GameLayout.useDef;  
  
			GameLayout.laAtt.setText("x" + String.valueOf(GameProcess.numOfBingo2[0] + GameProcess.usingAttackSkill2));  
			GameLayout.laDef.setText("x" + String.valueOf(GameProcess.numOfBingo2[1] + GameProcess.usingDefenseSkill2));  
			GameLayout.laTrick.setText("x" + String.valueOf(GameProcess.numOfBingo2[2] + GameProcess.usingStrategySkill2));  
  
			GameProcess.skillChance1--;  
			GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
  
			GameLayout.goongUsable1[1] = false;  
			GameLayout.fury[1][1].setEnabled(false);  
			GameLayout.fury[1][1].setVisible(false);  
			GameLayout.gauge[1][1].setBackground(Color.DARK_GRAY);
			GameLayout.gauge[1][1].setString("궁극기소진");
			GameLayout.defPan2.setVisible(false);
			GameLayout.furyEndBtn[1][1].setVisible(true);  
			GameLayout.bDefFCheck1 = false;
			try
			{
//				out.write((Function.DEFFURY+"|"+myRoom+"|"+i+j+"\n").getBytes());
			}catch(Exception ex){}
		}  
		// 책략필살기 클릭 후, 상대 판 진영파괴 버튼 클릭  
		for (int i = 0; i < 3; i++) {  
			if (e.getSource() == GameLayout.jypgChoice[i] && GameLayout.goongUsable1[2] == true) {  
				game.new SFImageThread().start();  
				GameProcess.jypg(0, i);  
				GameLayout.goongUsable1[2] = false;  
				GameLayout.fury[1][2].setEnabled(false);  
				GameLayout.fury[1][2].setVisible(false);  
				GameLayout.gauge[1][2].setBackground(Color.DARK_GRAY);  
				GameLayout.gauge[1][2].setString("궁극기소진");  
				for (int j = 0; j < 3; j++) {  
					GameLayout.jypgChoice[j].setVisible(false);//  
				}  
				GameLayout.furyEndBtn[1][2].setVisible(true);  
				GameProcess.skillChance1--;  
				GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
				for (int j = 0; j < 6; j++) {  
					GameLayout.jypgChoice[j].setOpaque(false);  
					GameLayout.jypgChoice[j].setVisible(false);//  
				}  
				GameLayout.bTrickFCheck1 = false;  
			}  
  
			if (e.getSource() == GameLayout.jypgChoice[i + 3] && GameLayout.goongUsable2[2] == true) {  
				game.new SFImageThread().start();  
				GameProcess.jypg(1, i);  
				GameLayout.goongUsable2[2] = false;  
				GameLayout.fury[0][2].setEnabled(false);  
				GameLayout.fury[0][2].setVisible(false);  
				GameLayout.gauge[0][2].setBackground(Color.DARK_GRAY);  
				GameLayout.gauge[0][2].setString("궁극기소진");  
				for (int j = 0; j < 3; j++) {  
					GameLayout.jypgChoice[j + 3].setVisible(false);//  
				}  
				GameLayout.furyEndBtn[0][2].setVisible(true);  
				GameProcess.skillChance2--;  
				GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));  
				for (int j = 0; j < 6; j++) {  
					GameLayout.jypgChoice[j].setOpaque(false);  
					GameLayout.jypgChoice[j].setVisible(false);//  
				}  
			}  
		}  
  
		if (e.getSource() == GameLayout.timeOut && GameProcess.playerTurn)// 턴턴턴 
		{  
			GameLayout.IFNoticeVisible();  
			// 스킬 사용 가능 초기화  
			GameLayout.bAttCheck1 = false;
			GameLayout.bDefCheck1 = false;
			GameLayout.bDefFCheck1 = false;
			GameLayout.bTrickCheck1 = false;
			GameLayout.bTrickFCheck1 = false;
			try
	         {
	            out.write((Function.GAMETURN+"|"+myRoom+"\n").getBytes());
	         }catch(Exception ex){}
		}  
		game.requestFocus();  
		
		if(e.getSource()==GameLayout.exit) //항복 버튼
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "항복하시겠습니까?", "항복", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //예를 누르면 게임 끝내기 쓰레드
			{
				GameLayout.gameEnd.addActionListener(this);//인게임 나가기 버튼
				GameProcess.playerWon=false;
				GameLayout.imageVisibleFalse();
				try
	            {
	               out.write((Function.BINGOEND+"|"+myRoom+"|"+myId+"|"+GameProcess.playerWon+"\n").getBytes());
	            }catch(Exception ex){}
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//빙고 마무리
		{
			GameLayout.gameEnd.addActionListener(this);//인게임 나가기 버튼
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			try
            {
               out.write((Function.BINGOEND+"|"+myRoom+"|"+myId+"|"+GameProcess.playerWon+"\n").getBytes());
            }catch(Exception ex){}
		}
		else if(e.getSource()==GameLayout.gameEnd) //나가기
		{
			GameLayout.imageVisibleTrue();
			if(GameProcess.playerWon==true) //승패 이미지 없애기
				GameLayout.wonX+=1200;
			else if(GameProcess.playerWon==false)
				GameLayout.loseX+=1200;
			GameLayout.bingoEnd=false;
			t1.interrupt();
			paintthread.interrupt();
			card.show(getContentPane(), "WR");
			game.removeAll();
			GameProcess.gameReset();
			game=new GameLayout();
			add(game,"GAME");
			cr.setVisible(true);
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
                     int win=Integer.parseInt(st.nextToken());
                     int lose=Integer.parseInt(st.nextToken());
                     String ava=st.nextToken();
                     String name=st.nextToken();
                     String nick=st.nextToken();
                     String date=st.nextToken();
                     wr.laId.setText(id+" 님");
                     wr.laScore.setText("전적 "+win+"승 "+lose+"패");
                     if((win+lose)==0){
                        wr.pb.setValue(0);
                        
                     }else{
                        double rate = (double)win/(win+lose)*100;
                        wr.pb.setValue((int)Math.ceil(rate));
                     }
                     
                     wr.avaBtn.setIcon(new ImageIcon("img\\"+ava));
                     card.show(getContentPane(), "WR");
                     
                     uif.la_id.setText("아이디   "+id);
                     uif.la_nickname.setText("닉네임   "+name);
                     uif.la_name.setText("이름     "+nick);
                     uif.la_day.setText("가입날짜 "+date);
                     double Rate=(double)win/(win+lose);
                    if(win+lose==0)
                    {
                       Rate=0;
                    }
                    String winRate=String.format("%.2f", (double)Rate*100.0);
                    uif.avata.setIcon(new ImageIcon("img\\"+ava));
                     uif.la_rate.setText("승률     "+winRate+"%");
                     uif.la_score.setText("전적     "+win+"승 "+lose+"패");
                   }
                   break;
	               case Function.SAMELOGIN:
	               {
	                  JOptionPane.showMessageDialog(login, "이미 접속중입니다.");
	               }
	               break;
	               case Function.EXIT:
	               {
	                  String id=st.nextToken();
	                  for(int i=0; i<wr.model2.getRowCount();i++)
	                  {   
	                     String temp=wr.model2.getValueAt(i, 0).toString();
	                     if(id.equals(temp))
	                     {
	                        wr.model2.removeRow(i);
	                        break;
	                     }
	                  }
	               }
	               break;
	               case Function.MYEXIT:
	               {
	                  dispose();
	                  System.exit(0);
	               }
	               break;               
	            case Function.MAKEROOM:
	            {
	               String[] data={
	                  String.valueOf(st.nextToken()),
	                  st.nextToken(),
	                  st.nextToken(),   
	                  st.nextToken()
	               };

	               wr.model1.addRow(data);
	            }
	            break;
	               case Function.MYROOMIN:
	            {   
	               System.out.println("클라이언트 MYROOMIN 수행시작");
	               String id=st.nextToken();   //id
	               String name=st.nextToken(); //대화명
	               String sex=st.nextToken();   //성별
	               String avata=st.nextToken(); //아바타
	               String rName=st.nextToken(); //방제목
	               String rb=st.nextToken(); //방장아아디
	               System.out.println("방장아이디:"+rb);
	               int win=Integer.parseInt(st.nextToken());
	               int lose=Integer.parseInt(st.nextToken());
	               myRoom=Integer.parseInt(st.nextToken());
	               double Rate=(double)win/(win+lose);
	               if(win+lose==0)
	               {
	                  Rate=0;
	               }
	               System.out.println(Rate);
	               System.out.println(win+"\n"+lose);
	               String winRate=String.format("%.2f", (double)Rate*100.0);
	               System.out.println("id: "+id+
	                     "name: "+name+
	                     "sex: "+sex+
	                     "avata: "+avata+
	                     "rb: "+rb);
	               cr.setTitle(rName);
	               String[] data={id,name,winRate+"%"};
	               cr.model.addRow(data);
	               for(int i=0;i<2;i++)
	               {
	                  if(!cr.sw[i])
	                  {
	                     cr.sw[i]=true;
	                     cr.pan[i].setLayout(new BorderLayout());
	                     cr.pan[i].removeAll();
	                     cr.pan[i].add("Center",
	                           new JLabel(new ImageIcon("img\\"+avata)));
	                     cr.idtf[i].setText(name);
	                     if(id.equals(rb))//내가 방장
	                     {
	                        cr.idtf[i].setForeground(Color.red);
	                     }
	                     cr.pan[i].validate();
	                     break;
	                  }
	               }
	               if(myId.equals(rb))//방장인경우
	               {
	                  cr.b1.setEnabled(false); //준비
	                  cr.b2.setEnabled(false); //시작
//	                  cr.idtf[0].setForeground(Color.red);
	               }
	               else//걍 입장인원
	               {
	                  cr.b1.setEnabled(true); //준비
	                  cr.b2.setEnabled(false);//시작
//	                  cr.idtf[1].setForeground(Color.red);
	               }
	               cr.setVisible(true);
	            }
	            break;
	            case Function.ROOMIN:
	            {
	               System.out.println("클라이언트 ROOMIN 수행시작");
	               String id=st.nextToken();   //id
	               String name=st.nextToken(); //대화명
	               String sex=st.nextToken();   //성별
	               String avata=st.nextToken(); //아바타
	               String rName=st.nextToken(); //방제목
	               String rb=st.nextToken(); //방장아아디
	               System.out.println("방장아이디:"+rb);
	               int win=Integer.parseInt(st.nextToken());
	               int lose=Integer.parseInt(st.nextToken());
	               double Rate=(double)win/(win+lose);
	               System.out.println(Rate);
	               if(win+lose==0)
	               {
	                  Rate=0;
	               }
	               System.out.println(win+"\n"+lose);
	               String winRate=String.format("%.2f", (double)Rate*100.0);
	               System.out.println("id: "+id+
	                     "name: "+name+
	                     "sex: "+sex+
	                     "avata: "+avata+
	                     "rb: "+rb);
	               cr.setTitle(rName);
	               String[] data={id,name,winRate+"%"};
	               cr.model.addRow(data);
	               for(int i=0;i<2;i++)
	               {
	                  if(!cr.sw[i])
	                  {
	                     cr.sw[i]=true;
	                     cr.pan[i].setLayout(new BorderLayout());
	                     cr.pan[i].removeAll();
	                     cr.pan[i].add("Center",
	                           new JLabel(new ImageIcon("img\\"+avata)));
	                     cr.idtf[i].setText(name);
	                     if(id.equals(rb))//내가 방장
	                     {
	                        cr.idtf[i].setForeground(Color.red);
	                     }
	                     cr.pan[i].validate();
	                     break;
	                  }
	               }

	               cr.setVisible(true);
	            }
	            break;
	                case Function.WAITCHAT:
	                {  
	                   String data=st.nextToken();
	                   String color=st.nextToken();
	                   System.out.println("id:"+myId+"\n"+"color:"+color);
	                   wr.append(data,color);
	                   wr.bar.setValue(wr.bar.getMaximum());
	                   //wr.tf.setText("");
	                }
	                break;
	                case Function.ROOMCHAT:
	                {
	                   String data=st.nextToken();
	                   String color=st.nextToken();
	                    cr.append(data,color);
	                    cr.bar.setValue(cr.bar.getMaximum());
	                    //cr.tf.setText("");
	                }
	                break;
	            case Function.WAITUPDATE:
	            {
	               int rNum=Integer.parseInt(st.nextToken()); //방번호
	               String rc=st.nextToken(); //현재인원
	               System.out.println("클라이언트 WAITUPDATE 케이스 현재인원:"+rc);
	               String ri=st.nextToken(); //전체인원
	               String id=st.nextToken(); //나간 사람 아이디
	               String pos=st.nextToken(); //나간 사람 위치
	               for(int i=0;i<wr.model1.getRowCount();i++)
	               {
	                  String temp=wr.model1.getValueAt(i, 0).toString();
	                  if(rNum==Integer.parseInt(temp))
	                  {
	                     if(Integer.parseInt(rc)<1)
	                     {
	                        wr.model1.removeRow(i);
	                     }
	                     else
	                     {
	                        wr.model1.setValueAt(rc+"/"+ri, i, 3);
	                     }
	                     break;
	                  }
	               }
	               for(int i=0;i<wr.model2.getRowCount();i++)
	               {
	                  String temp=wr.model2.getValueAt(i,0).toString();
	                  if(id.equals(temp))
	                  {
	                     wr.model2.setValueAt(pos, i, 3);
	                     break;
	                  }
	               }
	            }
	            break;
	            case Function.BANGCHANGE://채팅룸에 남은 인원에 대해서 처리 (기존 방장 or 바뀐방장)
	            {   System.out.println("클라이언트 CASE문 BANGCHAGE: 방장이 나가서 방장이 바뀔경우 호출한다");
	               String bj=st.nextToken();//현재 바뀐방장 (나의 아이디가 되겠지..)
	               String name=st.nextToken();
	               int size=Integer.parseInt(st.nextToken());
	                    cr.idtf[0].setForeground(Color.red);
	                    cr.idtf[1].setForeground(Color.BLACK);
	                    cr.b1.setEnabled(false);
	               cr.b2.setEnabled(false);
	            }
	            break;
	            case Function.ROOMOUT: //cr의 아바타창과 유저정보 삭제
	            {
	               String id=st.nextToken();
	               String name=st.nextToken();
	               String clientName=st.nextToken();
	               //아바타 보이는 화면 처리cch
	               for(int i=0;i<2;i++)
	               {
	                  String temp=cr.idtf[i].getText();
	                  if(temp.equals(name))
	                  {
	                      cr.sw[i]=false;
	                      cr.idtf[i].setText("");
	                      cr.pan[i].removeAll();
	                      cr.pan[i].setLayout(new BorderLayout());
	                      cr.pan[i].add("Center",new JLabel(
	                            new ImageIcon("img\\def.png")));
	                      cr.pan[i].validate();
	                      break;
	                  }
	               }
	               //방참여 유저정보 화면 처리
	               for(int i=cr.model.getRowCount()-1;i>=0;i--)
	               {
	                  if(cr.model.getValueAt(i, 0).equals(id))
	                  {
	                     cr.model.removeRow(i);
	                  }
	               }
	               cr.idtf[0].setBackground(Color.WHITE);
	               cr.idtf[0].setText(clientName);
	               cr.b3.setEnabled(true);
	               cr.b1.setText("준   비");
	            }
	            break;
	            case Function.MYROOMOUT://?!?!?!?
	            {
	               int size=Integer.parseInt(st.nextToken());
	               //아바타 창 초기화
	               for(int i=0;i<size;i++)
	               {      cr.sw[i]=false;
	                      cr.idtf[i].setText("");
	                      cr.idtf[i].setBackground(Color.WHITE);
	                      
	                      cr.pan[i].removeAll();
	                      cr.pan[i].setLayout(new BorderLayout());
	                      cr.pan[i].add("Center",new JLabel(
	                            new ImageIcon("img\\def.png")));
	                      cr.pan[i].validate();
	                }
	               cr.idtf[1].setForeground(Color.red);
	               cr.idtf[0].setForeground(Color.black);
	               cr.pane.setText("");
	               cr.tf.setText("");
	               for(int i=cr.model.getRowCount()-1;i>=0;i--)
	               {
	                  cr.model.removeRow(i);
	               }
	               cr.setVisible(false);
	               card.show(getContentPane(), "WR");
	            }
	            break;
	            case Function.POSCHANGE:
	            {
	               String id=st.nextToken();
	               String pos=st.nextToken();
	               for(int i=0;i<wr.model2.getRowCount();i++)
	               {
	                  String temp=wr.model2.getValueAt(i,0).toString();
	                  if(id.equals(temp))
	                  {
	                     wr.model2.setValueAt(pos, i, 3);
	                     break;
	                  }
	               }
	            }
	            break;
	               case Function.GAMEYES://무조건 게스트만 존재
	               {   System.out.println("client case문 GAMEYES 시작");
	                  String yesId=st.nextToken(); //준비를 누른 놈의 아이디 (이전 방장아이디)
	                  System.out.println("준비를 누른 놈의 아이디 (이전 방장아이디):"+yesId);
	                  String bjId=st.nextToken(); //방장아이디
	                  System.out.println("현재 방장아이디:"+bjId);
	                  if(myId.equals(yesId))//내가 게스트라면
	                  {
	                   cr.idtf[0].setBackground(Color.green);
	                   cr.idtf[0].setText("준비완료");
	                   cr.b3.setEnabled(false);
	                  }
	                  else//내가 방장이라면
	                  {
	                   cr.idtf[1].setBackground(Color.green);
	                   cr.idtf[1].setText("준비완료");
	                   cr.b2.setEnabled(true);
	                   cr.b3.setEnabled(true);
	                  }
	               }
	               break;
	               case Function.GAMENO://무조건 게스트만 존재
	               {   System.out.println("client case문 GAMENO 시작");
	                  String noId=st.nextToken(); //준비취소를 누른 놈의 아이디(게스트 아이디)
	                  System.out.println("준비취소를 누른 놈의 아이디:"+noId);
	                  String noName=st.nextToken(); //준비취소를 누른 놈의 대화명 (게스트 대화명)
	                  System.out.println("준비취소를 누른 놈의 대화명:"+noName);
	                  String bjId=st.nextToken(); //방장 아이디
	                  System.out.println("현재방장아이디:"+bjId);
	                  if(myId.equals(noId))//내가 게스트임
	                  {
	                     //System.out.println("myId.equals(bjId):"+myId.equals(noId));
	                   cr.idtf[0].setBackground(Color.WHITE);
	                   cr.idtf[0].setText(noName);
	                   cr.b3.setEnabled(true); //나가기 버튼 활성화
	                  }
	                  else//내가 방장임
	                  {
	                   cr.idtf[1].setBackground(Color.WHITE);
	                   cr.idtf[1].setText(noName);
	                   cr.b2.setEnabled(false);
	                  }
	                  
	               }
	               break;
	                  case Function.GAMESTART: 
	                  {   
	                     //playTurn 0:선, 1:후
	                    String choiceId=st.nextToken();
	                    int playTurn=0;
	                    int playTurn1=Integer.parseInt(st.nextToken());
	                    int playTurn2=Integer.parseInt(st.nextToken());
	                     if(myId.equals(choiceId))
	                    {
	                        playTurn=playTurn1;
	                        if(playTurn==0)
	                            GameProcess.playerTurn=true;
	                         else
	                            GameProcess.playerTurn=false;
	                    }
	                     else
	                     {
	                        playTurn=playTurn2;
	                        if(playTurn==0)
	                            GameProcess.playerTurn=true;
	                         else
	                            GameProcess.playerTurn=false;
	                     }
	                     cr.setVisible(false);
	                     card.show(getContentPane(), "ChoiceNation");
	                     new ChoiceNationTimeLimit().start();
	                  }
	                  break;
	                  case Function.NUMBATCH:
	                  {
	                     String rId=st.nextToken();
	                     int seq=Integer.parseInt(st.nextToken());
	                     if(myId.equals(rId))//내가 방장이면
	                     {
	                        GameProcess.numArr1[seq]=Integer.parseInt(st.nextToken());
	                        GameProcess.numArr2[seq]=Integer.parseInt(st.nextToken());
	                     }
	                     else//내가 게스트면
	                     {
	                        GameProcess.numArr2[seq]=Integer.parseInt(st.nextToken());
	                        GameProcess.numArr1[seq]=Integer.parseInt(st.nextToken());
	                     }  
	                  }
	                  break;
	                  case Function.GAMELAYOUT://!!!!lay
	                  {
	                	  GameLayout.Rand();
	              		for (int i = 0; i < 3; i++) 
	              		{  
	            			for (int j = 0; j < 25; j++) 
	            			{  
	            				GameLayout.a1[i][j].addActionListener(this);  
	            				GameLayout.a2[i][j].addActionListener(this);  
	            			}  
	            		} 
	                  }
	                  break;
	                  case Function.GAMETURN:
	                     {    
	                    	percent=0;
	                        ClientMainForm.t1.interrupt();
	                        ClientMainForm.t1 = new TimeLimit();
	                        ClientMainForm.t1.start();
	                        if(GameProcess.playerTurn)//내 턴이였는데 메세지를 받은경우 (턴을  넘긴경우)
	                        {
	                           GameProcess.playerTurn=false; //내턴이 아님 처리
	                           GameLayout.bingoTurnIcon1.setVisible(false); //내가 공격턴이 아님
	                           GameLayout.bingoTurnIcon2.setVisible(true); //너의 공격턴임
	                         //전술명령, 지휘권 리셋  
	                           GameProcess.skillChance2=1; //너의 전술명령권은 1임
	                           GameProcess.bingoCheckChance2=1; //너의 지휘권은 1임
	                           GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));  
	                           GameLayout.youLaCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance2));
	                        }
	                        else if(!GameProcess.playerTurn)//니 턴이였는데 메세지를 받은경우 (턴을  받은경우)
	                        {
	                           GameProcess.playerTurn=true;
	                           GameLayout.bingoTurnIcon1.setVisible(true); //내가 공격턴임
	                           GameLayout.bingoTurnIcon2.setVisible(false); //너는 공격턴이 아님
	                           GameProcess.skillChance1=1; //나의 전술명령권은 1임
	                           GameProcess.bingoCheckChance1=1; //나의 지휘권은 1임
	                           GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
	                           GameLayout.laCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
	                        }
	                     }
	                     break;
	               case Function.CHOICENATION:
	                  {
	                     String id=st.nextToken();
	                     String name=st.nextToken();
	                     String avata=st.nextToken();
	                     int win = Integer.parseInt(st.nextToken());
	                     int lose = Integer.parseInt(st.nextToken());
	                     int chosenNation=Integer.parseInt(st.nextToken());
	                    
	                     if(myId.equals(id))
	                     {
	                        GameLayout.laNickname.setText(name);
	                        GameLayout.btnAvatar.setIcon(new ImageIcon("img\\"+avata));
	                        GameLayout.laScore.setText("전적 "+win+"승 "+lose+"패");
	                         if((win+lose)==0){
	                            GameLayout.pbScore.setValue(0);
	                       }else{
	                          double rate = (double)win/(win+lose)*100;
	                          GameLayout.pbScore.setValue((int)Math.ceil(rate));
	                       }
	                     }
	                     else//내가 아니면
	                     {
	                        ChoiceNation.chosenNation2=chosenNation;
	                        if(ChoiceNation.chosenNation2==0)
	                           cn.bu2.setIcon(new ImageIcon("img\\빙고체크-위.png"));
	                        else if(ChoiceNation.chosenNation2==1)
	                           cn.bu2.setIcon(new ImageIcon("img\\빙고체크-촉.png"));
	                        else
	                           cn.bu2.setIcon(new ImageIcon("img\\빙고체크-오.png"));
	                        
	                        GameLayout.youLaNickname.setText(name);
	                        GameLayout.youBtnAvatar.setIcon(new ImageIcon("img\\"+avata));
	                        GameLayout.youLaScore.setText("전적 "+win+"승 "+lose+"패");
	                        if((win+lose)==0){
	                            GameLayout.youPbScore.setValue(0);
	                       }else{
	                          double rate = (double)win/(win+lose)*100;
	                          GameLayout.youPbScore.setValue((int)Math.ceil(rate));
	                       }
	                     }
	                     cn.jangsu();
	                  }
	                  break;
	               case Function.BINGOCHECK:
	               {
	            	   String rId=st.nextToken();
	            	   int panNumber=Integer.parseInt(st.nextToken());
	            	   int bingoCheckNumber=Integer.parseInt(st.nextToken());
	            	   ImageIcon nationIcon1 = null, nationIcon2 = null;
	            	   if(ChoiceNation.chosenNation1==0)nationIcon1=GameLayout.bcIcon0;
	            	   if(ChoiceNation.chosenNation1==1)nationIcon1=GameLayout.bcIcon1;
	            	   if(ChoiceNation.chosenNation1==2)nationIcon1=GameLayout.bcIcon2;
	            	   if(ChoiceNation.chosenNation2==0)nationIcon2=GameLayout.bcIcon0;
	            	   if(ChoiceNation.chosenNation2==1)nationIcon2=GameLayout.bcIcon1;
	            	   if(ChoiceNation.chosenNation2==2)nationIcon2=GameLayout.bcIcon2;
	            	   if(myId.equals(rId))
	            	   {
	            		   GameProcess.bingoCheck(panNumber, bingoCheckNumber, 
	            				   				  GameProcess.p1Board, GameProcess.p2Board,  
	            				   				  GameProcess.bingo1, GameProcess.bingo2, 
	            				   				  GameLayout.a1, GameLayout.a2, 
	            				   				  nationIcon1, nationIcon2);  
							GameLayout.laCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
//							if(GameLayout.panCheck2[panNumber][bingoCheckNumber]==true)//락걸린건 안바뀜
//								GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-락.png"));  
	            	   }
	            	   else
	            	   {
	            		   GameProcess.bingoCheck(panNumber, bingoCheckNumber, 
 				   				  GameProcess.p2Board, GameProcess.p1Board,  
 				   				  GameProcess.bingo2, GameProcess.bingo1, 
 				   				  GameLayout.a2, GameLayout.a1, 
 				   				  nationIcon2, nationIcon1); 
	            		   GameProcess.bingoCheckChance2=0;
	            		   GameLayout.youLaCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance2));
							if(GameLayout.panCheck2[panNumber][bingoCheckNumber]==true)//락걸린건 안바뀜
								GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-락.png"));
	            	   }
						
						game.laSetting(GameLayout.laCommand, GameLayout.laAtt, GameLayout.laDef, GameLayout.laTrick);  
						if (GameLayout.bingoEnd)
							game.bingoEndProcess();
	               }
	               break;
	               case Function.ATTSKILL:
	                  {
	                     int panNumber=Integer.parseInt(st.nextToken());
	                     int bingoCheckNumber=Integer.parseInt(st.nextToken());
	                     if(GameLayout.bAttCheck1)
	                     {
	                        game.new AImageThread().start();
	                        GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-락.png"));  
	                        GameProcess.usingAttackSkill1--;
	                        GameLayout.useAtt--;
	                        GameLayout.laAtt.setText("x" + String.valueOf(GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1));  
	                        GameLayout.bAttCheck1 = false;  
	                        GameProcess.skillChance1--;  
	                        GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
	                        GameLayout.panCheck1[panNumber][bingoCheckNumber] = true; // 공격한 곳 확인.
	                     }
	                     else
	                     {
	                        game.new AImageThread().start();
	                      GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-락.png"));
	                        GameProcess.usingAttackSkill2--;
	                        GameLayout.youUseAtt--;
	                        GameLayout.youLaAtt.setText("x" + String.valueOf(GameProcess.numOfBingo2[0] + GameProcess.usingAttackSkill2));  
	                        GameProcess.skillChance2--;  
	                        GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));  
	                        GameLayout.panCheck2[panNumber][bingoCheckNumber] = true; // 공격한 곳 확인.
	                     }
	                  }
	                  break;
	                  case Function.DEFSKILL:
	                  {
	                     int panNumber=Integer.parseInt(st.nextToken());
	                     int bingoCheckNumber=Integer.parseInt(st.nextToken());
	                     if(GameLayout.bDefCheck1)
	                     {
	                        game.new DImageThread().start();  
	                        GameProcess.usingDefenseSkill1--;  
	                        GameLayout.useDef--;  
	                        GameLayout.laDef.setText("x" + String.valueOf(GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1));  
	                     if (GameProcess.bingo1[panNumber][bingoCheckNumber] == false) {  
	                        GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\" + (GameProcess.p1Board[panNumber][bingoCheckNumber]) + ".png"));  
	                     } else if (GameProcess.bingo1[panNumber][bingoCheckNumber]) {
	                        if (ChoiceNation.chosenNation1 == 0)
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-위.png"));  
	                        else if (ChoiceNation.chosenNation1 == 1)  
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-촉.png"));  
	                        else if (ChoiceNation.chosenNation1 == 2)  
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-오.png"));  
	                     }  
	        
	                     GameLayout.bDefCheck1 = false;  
	                     GameProcess.skillChance1--;  
	                     GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
	                     GameLayout.panCheck2[panNumber][bingoCheckNumber] = false;
	                     }
	                     else
	                     {
	                        game.new DImageThread().start();  
	                        GameProcess.usingDefenseSkill2--;  
	                        GameLayout.youUseDef--;  
	                        GameLayout.youLaDef.setText("x" + String.valueOf(GameProcess.numOfBingo2[1] + GameProcess.usingDefenseSkill2));  
	                     if (GameProcess.bingo2[panNumber][bingoCheckNumber] == false) {  
	                        GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\" + (GameProcess.p2Board[panNumber][bingoCheckNumber]) + ".png"));  
	                     } else if (GameProcess.bingo2[panNumber][bingoCheckNumber]) {
	                        if (ChoiceNation.chosenNation2 == 0)
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-위.png"));  
	                        else if (ChoiceNation.chosenNation2 == 1)  
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-촉.png"));  
	                        else if (ChoiceNation.chosenNation2 == 2)  
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\빙고체크-오.png"));  
	                     }  
	                     GameProcess.skillChance2--;
	                     GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));  
	                     GameLayout.panCheck1[panNumber][bingoCheckNumber] = false;
	                     }
	                  }
	                  break;
	                  case Function.TRICKSKILL:
	                  {
	                     int panNumber=Integer.parseInt(st.nextToken());
	                     int bingoCheckNumber=Integer.parseInt(st.nextToken());
	                     if(GameLayout.bTrickCheck1)
	                     {
	                        game.new SImageThread().start();  
	                        GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\" + GameProcess.numArr2[25 * panNumber + bingoCheckNumber] + ".png"));  
	                     GameProcess.usingStrategySkill1--;  
	                     GameLayout.useTrick--;  
	                     GameLayout.laTrick.setText("x" + String.valueOf(GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1));  
	                     GameProcess.skillChance1--;  
	                     GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
	                     GameLayout.bTrickCheck1 = false;
	                     }
	                     else
	                     {
	                        game.new SImageThread().start();
	                        GameProcess.skillChance2--;
	                        GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));
	                     }
	                  }
	                  break;
	                  case Function.ATTFURY:
	                  {
	                	  String furyId=st.nextToken();
	                	  if(furyId.equals(myId))
	                	  {
		                	  game.new AFImageThread().start();  
		                	  GameProcess.bingoCheckChance1++;// 공격기회+1,아이템사용기회+1  
		                	  GameProcess.skillChance1++;  
		                	  GameLayout.goongUsable1[0] = false;  
		                	  GameLayout.fury[1][0].setEnabled(false);  
		                	  GameLayout.gauge[1][0].setBackground(Color.DARK_GRAY);  
		                	  GameLayout.gauge[1][0].setString("궁극기소진");
	                	  }
	                	  else
	                	  {
	                		  game.new AFImageThread().start();
	                		  GameProcess.bingoCheckChance2++;// 공격기회+1,아이템사용기회+1  
		                	  GameProcess.skillChance2++;  
		                	  GameLayout.goongUsable2[0] = false;  
		                	  GameLayout.fury[2][0].setEnabled(false);  
		                	  GameLayout.gauge[2][0].setBackground(Color.DARK_GRAY);  
		                	  GameLayout.gauge[2][0].setString("궁극기소진");
	                	  }
	                  }
	                  break;
	                  case Function.DEFFURY:
	                  {
	                     
	                  }
	                  break;
	                  case Function.TRICKFURY:
	                  {
	                     
	                  }
	                  break;
	                  case Function.BINGOEND:
	                  {
	                	  String endId=st.nextToken();
	                	  int won=Integer.parseInt(st.nextToken());
	                	  if(endId.equals(myId))
	                		  game.new endThread().start();
	                	  else if(!endId.equals(myId)&&won==0)
	                	  {
	                		  GameProcess.playerWon=true;
	                		  game.new endThread().start();
	                	  
	                	  }
	                	  else if(!endId.equals(myId)&&won==1)
	                	  {
	                		  GameProcess.playerWon=false;
	                		  game.new endThread().start();
	                	  }
	                  }
	                  break;
	               }
	               
	         }
	      }catch(Exception ex){}
	   
	   }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==wr.table1)
		{
			if(e.getClickCount()==2)
			{
				int row=wr.table1.getSelectedRow();
				String rNum=wr.model1.getValueAt(row, 0).toString();
				myRoom=Integer.parseInt(rNum);
				String rn=wr.model1.getValueAt(row, 1).toString();
				String rs=wr.model1.getValueAt(row, 2).toString();
				String ri=wr.model1.getValueAt(row, 3).toString();
				StringTokenizer st=
						new StringTokenizer(ri, "/");
				// 1/6  => 6/6
				if(Integer.parseInt(st.nextToken())==2)
				{
					JOptionPane.showMessageDialog(this,
							"더이상 입장이 불가능 합니다");
					return;
				}
				try
				{
					out.write((Function.MYROOMIN+"|"+myRoom+"\n").getBytes());
				}catch(Exception ex){}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public class ChoiceNationTimeLimit extends Thread{
		public void run() {
			int a = (int) (Math.random() * 3);
			try {
				
				while (ChoiceNation.cntime>0) {
					ChoiceNation.cntime--;
					ChoiceNation.la6.setText(String.valueOf(ChoiceNation.cntime));
					repaint();
					Thread.sleep(1000);
				}
	            if(ChoiceNation.cntime==0) {
	            	if(!cn.choiceComplete) //나라 선택이 안되면 자동선택
	            	{
	            		ChoiceNation.chosenNation1=a;
	            		if(ChoiceNation.chosenNation1==0)
	            			cn.bu1.setIcon(new ImageIcon("img\\빙고체크-위.png"));
	            		else if(ChoiceNation.chosenNation1==1)
	            			cn.bu1.setIcon(new ImageIcon("img\\빙고체크-촉.png"));
	            		else
	            			cn.bu1.setIcon(new ImageIcon("img\\빙고체크-오.png"));
	            	}
	            	try
        			{
        				out.write((Function.CHOICENATION+"|"+myRoom+"|"+ChoiceNation.chosenNation1+"\n").getBytes());
        			}catch(Exception ex){}
	            	cn.jangsu();
	            	CoinFlip cf=new CoinFlip();
        			CoinFlip.coinEnd=true;
        			cf.setVisible(true);
        			if(CoinFlip.coinEnd==false)
        			{ 

        				card.show(getContentPane(), "GAME");
        				t1=new TimeLimit();
        				t1.start();
        				paintthread=game.new paintThread();
        				paintthread.start();
        				game.requestFocus();
        			}
        			ChoiceNation.cntime=7;
        			cn.choiceComplete=false;
        			if(ChoiceNation.chosenNation2==0)
                   	 cn.bu2.setIcon(new ImageIcon("img\\빙고체크-위.png"));
                    else if(ChoiceNation.chosenNation2==1)
                   	 cn.bu2.setIcon(new ImageIcon("img\\빙고체크-촉.png"));
                    else
                   	 cn.bu2.setIcon(new ImageIcon("img\\빙고체크-오.png"));
	            }
			} catch (Exception ex) {
			}
		}
	}
	public static class TimeLimit extends Thread {
		int[] rgb = new int[3];
		 // 시간제한바를 채우는 퍼센트 (20초:100퍼센트 즉, 0.2초: 1퍼센트)
		double residueTime = 20; // 남은시간표시 (초기값:20초)
		public void run() {
			// timeRun=true;
			try {
				if (GameProcess.playerTurn) {
					GameLayout.bingoTurnIcon1.setVisible(true);
					GameLayout.bingoTurnIcon2.setVisible(false);
				} else if (!GameProcess.playerTurn) {
					GameLayout.bingoTurnIcon1.setVisible(false);
					GameLayout.bingoTurnIcon2.setVisible(true);
				}
				while (true)
				{
					percent++;
					if (percent > 100)// 100퍼센트가 되면 다시 0으로 초기화
					{
						if(GameProcess.playerTurn)//내 턴이였는데 메세지를 받은경우 (턴을  넘긴경우)
                        {
                           GameProcess.playerTurn=false; //내턴이 아님 처리
                           GameLayout.bingoTurnIcon1.setVisible(false); //내가 공격턴이 아님
                           GameLayout.bingoTurnIcon2.setVisible(true); //너의 공격턴임
                         //전술명령, 지휘권 리셋  
                           GameProcess.skillChance2=1; //너의 전술명령권은 1임
                           GameProcess.bingoCheckChance2=1; //너의 지휘권은 1임
                           GameLayout.youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));  
                           GameLayout.youLaCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance2));
                        }
                        else if(!GameProcess.playerTurn)//니 턴이였는데 메세지를 받은경우 (턴을  받은경우)
                        {
                           GameProcess.playerTurn=true;
                           GameLayout.bingoTurnIcon1.setVisible(true); //내가 공격턴임
                           GameLayout.bingoTurnIcon2.setVisible(false); //너는 공격턴이 아님
                           GameProcess.skillChance1=1; //나의 전술명령권은 1임
                           GameProcess.bingoCheckChance1=1; //나의 지휘권은 1임
                           GameLayout.laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));  
                           GameLayout.laCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
                        }
						percent=0;
						residueTime=20;
						colorInt = 0;
						rgb[0] = colorInt;
						rgb[1] = 255;
						GameLayout.IFNoticeVisible(); // 시간이 끝나면 게임설명은 들어가고 스킬사용이false가 됨
						if (GameProcess.playerTurn) {
							GameLayout.bingoTurnIcon1.setVisible(true);
							GameLayout.bingoTurnIcon2.setVisible(false);
						} else if (!GameProcess.playerTurn) {
							GameLayout.bingoTurnIcon1.setVisible(false);
							GameLayout.bingoTurnIcon2.setVisible(true);
						}
						
					}
					Thread.sleep(200);
					colorInt = (int) (Math.ceil(2.55 * (percent)));
					residueTime -= 0.2;
					if (residueTime < 0.2)
						residueTime = 0;
					String rt = String.valueOf(residueTime);
					if (rt.length() >= 4) {
						String rr = rt.substring(0, 4);
						GameLayout.timer.setString("制限時間:" + rr);
					} else {
						String rr = rt;
						GameLayout.timer.setString("制限時間:" + rr);
					}
					rgb[0] = colorInt;
					rgb[1] = 255 - colorInt;
					GameLayout.timer.setValue(percent);
					GameLayout.timer.setForeground(new Color(rgb[0], rgb[1], rgb[2]));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
	}
}
