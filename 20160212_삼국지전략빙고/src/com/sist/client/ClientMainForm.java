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
import com.sist.server.Server;
import com.sun.security.ntlm.Client;
import sun.awt.WindowClosingListener;
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
	
	ImageIcon mainIcon;//타이틀창 아이콘
	static Thread t1=new TimeLimit();//시간제한바 스레드 
	Thread paintthread=game.new paintThread();
	Socket s;
    BufferedReader in;
    OutputStream out;
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
		
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		wr.b2.addActionListener(this); //test(게임방법)
		wr.b3.addActionListener(this); //게임정보 
		wr.b4.addActionListener(this); //나가기
		wr.tf.addActionListener(this); //대기실 채팅내용 받기
	    wr.table1.addMouseListener(this);
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //항복 버튼 
		
		mr.b1.addActionListener(this); //방만들기 창의 방만들기 버튼
		mr.b2.addActionListener(this); //방만들기 창의 방만들기 취소 버튼
		
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
	         String sex,String avata, int win, int lose)
	{
		try
		{
			s=new Socket("localhost", 3333); //211.238.142.39 localhost
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
					+"\n").getBytes());
		}catch(Exception ex){}
		new Thread(this).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1) //로그인을 누르면 대기실로 이동
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
		            connection(myId,myName,mySex,myAvatar,myWin,myLose);
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
			System.out.println("test_mr.b2");
		}
		else if(e.getSource()==mr.b1)
		{
			System.out.println("test_mr.b1");
			String rn=mr.tf.getText().trim();
			if(rn.length()<1)
			{
				JOptionPane.showMessageDialog(this,
						"방이름을 입력하세요");
				mr.tf.requestFocus();
				return;
			}
			System.out.println("test2");
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
			System.out.println("test3");
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
				System.out.println(
						   "방이름:"+rn+"\n"+
						   "방상태:"+state+"\n"+
						   "비번:"+pwd);
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
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
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
			GameLayout.endBtn.addActionListener(this);//빙고마무리버튼
			GameLayout.gameEnd.addActionListener(this);//인게임 나가기 버튼
			for(int i=0; i<2; i++)
			{
				for(int j=0; j<3; j++)
				{
					GameLayout.fury[i][j].setVisible(true);
				}
			}
		}
		else if(e.getSource()==GameLayout.exit) //항복 버튼
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "항복하시겠습니까?", "항복", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //예를 누르면 게임 끝내기 쓰레드
			{
				t1.interrupt();
				GameLayout.IFNoticeVisible();
				GameLayout.imageVisibleFalse();
				GameProcess.playerWon=false;
				game.new endThread().start();
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//빙고 마무리
		{
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			GameLayout.IFNoticeVisible();
			game.new endThread().start();
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
		else if(e.getSource()==cr.tf) //chatroom 채팅내용
		{
//			String msg=cr.tf.getText().trim();
//			  wr.initStyle();
//			  String color = wr.box.getSelectedItem().toString();
//				if(msg.length()<1)
//					return;
//				try
//				{
//					out.write((Function.WAITCHAT+"|"
//									+msg+"|"
//									+color+"\n").getBytes());
//				}catch(Exception ex){
//					System.out.println("wr.tf오류"+ex.getMessage());
//				}
//				wr.tf.setText("");
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
					String id=st.nextToken();	//id
					String name=st.nextToken(); //대화명
					String sex=st.nextToken();	//성별
					String avata=st.nextToken(); //아바타
					String rName=st.nextToken(); //방제목
					String rb=st.nextToken(); //방장아아디
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
					cr.setVisible(true);
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
							if(id.equals(rb))
							{
								cr.idtf[i].setForeground(Color.red);
							}
							cr.pan[i].validate();
							break;
						}
					}
					
					if(id.equals(rb))
					{
						cr.b1.setEnabled(true);
						cr.b2.setEnabled(true);
					}
					else
					{
						cr.b1.setEnabled(false);
						cr.b2.setEnabled(false);
					}

				}
				break;
				case Function.ROOMIN:
				{
					System.out.println("클라이언트 ROOMIN 수행시작");
					String id=st.nextToken();	//id
					String name=st.nextToken(); //대화명
					String sex=st.nextToken();	//성별
					String avata=st.nextToken(); //아바타
					String rName=st.nextToken(); //방제목
					String rb=st.nextToken(); //방장아아디
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
					cr.setVisible(true);
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
							if(id.equals(rb))
							{
								cr.idtf[i].setForeground(Color.red);
							}
							cr.pan[i].validate();
							break;
						}
					}
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
					/*
					 *  +room.roomName+"|"
    									+room.current+"|"
    									+room.inwon+"|"
    									+id+"|"
    									+pos
					 */
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
				case Function.BANGCHANGE:
				{
					String bj=st.nextToken();
					String name=st.nextToken();
					int size=Integer.parseInt(st.nextToken());
					for(int i=0; i<size;i++)
					{
						String n=cr.idtf[i].getText();
						if(n.equals(name))
						{
							cr.idtf[i].setForeground(Color.red);
						}
						else
						{
							cr.idtf[i].setForeground(Color.BLACK);
						}
					}
					if(bj.equals(myId))
					{
						cr.b1.setEnabled(true);
						cr.b2.setEnabled(true);
					}
					else
					{
						cr.b1.setEnabled(false);
						cr.b2.setEnabled(false);
					}
				}
				break;
				case Function.ROOMOUT: //cr의 아바타창과 유저정보 삭제
				{
					String id=st.nextToken();
					String name=st.nextToken();
					int size=Integer.valueOf(st.nextToken());
					//아바타 보이는 화면 처리
					for(int i=0;i<size;i++)
					{
						String temp=cr.idtf[i].getText();
						if(temp.equals(name))
						{
    						cr.sw[i]=false;
    						cr.idtf[i].setText("");
    						cr.pan[i].removeAll();
    						cr.pan[i].setLayout(new BorderLayout());
    						cr.pan[i].add("Center",new JLabel(
    								new ImageIcon("c:\\image\\def.png")));
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
				}
				break;
				case Function.MYROOMOUT:
				{
					int size=Integer.parseInt(st.nextToken());
					//아바타 창 초기화
					for(int i=0;i<size;i++)
					{		cr.sw[i]=false;
    						cr.idtf[i].setText("");
    						cr.pan[i].removeAll();
    						cr.pan[i].setLayout(new BorderLayout());
    						cr.pan[i].add("Center",new JLabel(
    								new ImageIcon("c:\\image\\def.png")));
    						cr.pan[i].validate();
    				}
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
	
}