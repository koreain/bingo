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
	
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	static Thread t1=new TimeLimit();//�ð����ѹ� ������ 
	Thread paintthread=game.new paintThread();
	Socket s;
    BufferedReader in;
    OutputStream out;
 // ���� ����
    String myId,pos;
    int myRoom;
	ClientMainForm()
	{
		super("�ﱹ�� ��������");//Ÿ��Ʋ ����
		mainIcon=new ImageIcon("img\\Ÿ��Ʋ������.png");
		this.setIconImage(mainIcon.getImage());
		
		setLayout(card);//BorderLayout
		add("LOG",login);
		add("WR",wr);
		//add("CR",cr);
		add("ChoiceNation",cn);//������ ȭ��
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		login.b1.addActionListener(this);//ī�巹�̾ƿ��� �� ��ư�� �߰�
		login.b2.addActionListener(this);
		login.b3.addActionListener(this);//ȸ������
		
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		wr.b2.addActionListener(this); //test(���ӹ��)
		wr.b3.addActionListener(this); //�������� 
		wr.b4.addActionListener(this); //������
		wr.tf.addActionListener(this); //���� ä�ó��� �ޱ�
	    wr.table1.addMouseListener(this);
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //�׺� ��ư 
		
		mr.b1.addActionListener(this); //�游��� â�� �游��� ��ư
		mr.b2.addActionListener(this); //�游��� â�� �游��� ��� ��ư
		
		cr.b3.addActionListener(this);
		cr.tf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //������â ����
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) { 
				try 
				{   //�����̵� �����ְ� waitVc ��Ͽ��� �����ֱ� ��û�Ѵ�..
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
		if(e.getSource()==login.b1) //�α����� ������ ���Ƿ� �̵�
		{   System.out.println("testlogin");
			boolean loginOk=false; 
			boolean idOk=false; 
			String id=login.tf.getText(); 
			String pw=String.valueOf(login.pf.getPassword()); 
			UserDAO user=new UserDAO(); 
			UserDTO userInfo=new UserDTO(); // id�� ������ �ѻ���� ����  
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
						JOptionPane.showMessageDialog(login,"��й�ȣ�� Ȯ���� �ּ���"); 
						login.pf.setText(""); 
							login.pf.requestFocus(); 
						} 
					} 
				} 
				if(!idOk) 
				{ 
					JOptionPane.showMessageDialog(login,"���̵� �������� �ʽ��ϴ�."); 
					login.tf.setText(""); 
					login.pf.setText(""); 
					login.tf.requestFocus(); 
				} 
				if(loginOk) 
				{ 
//					UserDTO sendData=userInfo;//�Ѱ��� �������� ������ 
		            myId=userInfo.getUser_id();
		            String myName=userInfo.getUser_nickname();
		            String mySex=userInfo.getUser_sex();
		            String myAvatar=userInfo.getUser_avatar();
		            int myWin=userInfo.getUser_win();
		            int myLose=userInfo.getUser_lose();
		            connection(myId,myName,mySex,myAvatar,myWin,myLose);
		            pos="����";
		            System.out.println(myAvatar);
//					card.show(getContentPane(), "WR"); 
				} 
		}
		else if(e.getSource()==login.b2) //��Ҹ� ������ ���α׷� ����
		{
			System.exit(0);
		}
		else if(e.getSource()==login.b3) // ȸ������ ��ư
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
		else if(e.getSource()==wr.b2) //1:1������ ������ ��������â
		{
			card.show(getContentPane(), "ChoiceNation");
		}
		else if(e.getSource()==wr.b3) // �������� ��ư
		{
			gi.setVisible(true); 
		}
		else if(e.getSource()==wr.b4) //�����⸦ ������ ���α׷� ����
		{
			try 
	         {   //�����̵� �����ְ� waitVc ��Ͽ��� �����ֱ� ��û�Ѵ�..
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
						"���̸��� �Է��ϼ���");
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
							"�̹� �����ϴ� ���Դϴ�\n�ٸ� �̸��� �Է��ϼ���");
					mr.tf.setText("");
					mr.tf.requestFocus();
					return;
				}
			}*/
			String state="",pwd="";
			System.out.println("test3");
			if(mr.open.isSelected())
			{
				state="����";
				pwd=" ";
			}
			else
			{
				state="�����";
				pwd=String.valueOf(mr.pf.getPassword());
			}
			int inwon=2;
			// ������ ���� 
				System.out.println(
						   "���̸�:"+rn+"\n"+
						   "�����:"+state+"\n"+
						   "���:"+pwd);
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
			GameLayout.endBtn.addActionListener(this);//����������ư
			GameLayout.gameEnd.addActionListener(this);//�ΰ��� ������ ��ư
			for(int i=0; i<2; i++)
			{
				for(int j=0; j<3; j++)
				{
					GameLayout.fury[i][j].setVisible(true);
				}
			}
		}
		else if(e.getSource()==GameLayout.exit) //�׺� ��ư
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "�׺��Ͻðڽ��ϱ�?", "�׺�", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //���� ������ ���� ������ ������
			{
				t1.interrupt();
				GameLayout.IFNoticeVisible();
				GameLayout.imageVisibleFalse();
				GameProcess.playerWon=false;
				game.new endThread().start();
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//���� ������
		{
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			GameLayout.IFNoticeVisible();
			game.new endThread().start();
		}
		else if(e.getSource()==GameLayout.gameEnd) //������
		{
			GameLayout.imageVisibleTrue();
			if(GameProcess.playerWon==true) //���� �̹��� ���ֱ�
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
		else if(e.getSource()==wr.tf) //���� ä�ó���
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
					System.out.println("wr.tf����"+ex.getMessage());
				}
				wr.tf.setText("");
		}
		else if(e.getSource()==cr.tf) //chatroom ä�ó���
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
				System.out.println("cr.tf����"+ex.getMessage());
			}
			cr.tf.setText("");
		}
		else if(e.getSource()==cr.tf) //chatroom ä�ó���
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
//					System.out.println("wr.tf����"+ex.getMessage());
//				}
//				wr.tf.setText("");
		}
		else if(e.getSource()==cr.b3)
		{
			try 
			{
				out.write((Function.ROOMOUT+"|"
						+myRoom+"|"//���ȣ
						+myId+"\n").getBytes());//���ξ��̵�	
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("cr.b3����"+e2.getMessage());
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
				// Ŭ���̾�Ʈ => ��û���� �޴´�
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
	            			st.nextToken(),   //id (Server���� ���� ��)
	            			st.nextToken(),   //nickname (Server���� ���� ��)
	            			st.nextToken(),   //sex (Server���� ���� ��)
	            			st.nextToken()   //pos (Server���� ���� ��)
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
	                  wr.laId.setText(id+" ��");
	                  wr.laScore.setText("���� "+win+"�� "+lose+"��");
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
	            	JOptionPane.showMessageDialog(login, "�̹� �������Դϴ�.");
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
					System.out.println("Ŭ���̾�Ʈ MYROOMIN �������");
					String id=st.nextToken();	//id
					String name=st.nextToken(); //��ȭ��
					String sex=st.nextToken();	//����
					String avata=st.nextToken(); //�ƹ�Ÿ
					String rName=st.nextToken(); //������
					String rb=st.nextToken(); //����ƾƵ�
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
					System.out.println("Ŭ���̾�Ʈ ROOMIN �������");
					String id=st.nextToken();	//id
					String name=st.nextToken(); //��ȭ��
					String sex=st.nextToken();	//����
					String avata=st.nextToken(); //�ƹ�Ÿ
					String rName=st.nextToken(); //������
					String rb=st.nextToken(); //����ƾƵ�
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
					int rNum=Integer.parseInt(st.nextToken()); //���ȣ
					String rc=st.nextToken(); //�����ο�
					System.out.println("Ŭ���̾�Ʈ WAITUPDATE ���̽� �����ο�:"+rc);
					String ri=st.nextToken(); //��ü�ο�
					String id=st.nextToken(); //���� ��� ���̵�
					String pos=st.nextToken(); //���� ��� ��ġ
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
				case Function.ROOMOUT: //cr�� �ƹ�Ÿâ�� �������� ����
				{
					String id=st.nextToken();
					String name=st.nextToken();
					int size=Integer.valueOf(st.nextToken());
					//�ƹ�Ÿ ���̴� ȭ�� ó��
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
					//������ �������� ȭ�� ó��
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
					//�ƹ�Ÿ â �ʱ�ȭ
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
							"���̻� ������ �Ұ��� �մϴ�");
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