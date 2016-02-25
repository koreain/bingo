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
	
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	static Thread t1=new TimeLimit();//�ð����ѹ� ������ 
	Thread paintthread=game.new paintThread();
	Socket s;
    BufferedReader in;
    OutputStream out;
    Vector<Room> roomVc = new Vector<Room>();
    
	static int colorInt = 0;
	static int percent = 0;
    
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
		login.tf.addActionListener(this);
		login.pf.addActionListener(this);
		
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this); //test(���ӹ��)
		wr.b3.addActionListener(this); //�������� 
		wr.b4.addActionListener(this); //������
		wr.tf.addActionListener(this); //���� ä�ó��� �ޱ�
	    wr.table1.addMouseListener(this);
	    wr.userinfoBtn.addActionListener(this);
	    uif.btn_NO.addActionListener(this);
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //�׺� ��ư 
		GameLayout.endBtn.addActionListener(this);//����������ư
		
		mr.b1.addActionListener(this); //�游��� â�� �游��� ��ư
		mr.b2.addActionListener(this); //�游��� â�� �游��� ��� ��ư
		
		cr.b1.addActionListener(this);
		cr.b2.addActionListener(this);
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
		
		/////////// GameLayout action ���� ///////////////  
		for(int i=0;i<6;i++){  
			GameLayout.jypgChoice[i].addActionListener(this); // �̺�Ʈ ���  
		}  
	  
		// ���� ���� ������ ���� ��� ��ư actionListener �߰� �޼ҵ�  
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
		for (int i = 0; i < 3; i++) // ��� �� ��ư �׼Ǹ�����  
		{  
			ChoiceNation.jangSu1[i].addActionListener(this);  
			ChoiceNation.jangSu2[i].addActionListener(this);  
		}  
		GameLayout.btnAtt.addActionListener(this);  
		GameLayout.btnDef.addActionListener(this);  
		GameLayout.btnTrick.addActionListener(this);  
		GameLayout.timeOut.addActionListener(this); // ������ ��ư  

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
		if(e.getSource()==login.b1||e.getSource()==login.tf||e.getSource()==login.pf) //�α����� ������ ���Ƿ� �̵�
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
		            String nick=userInfo.getUser_name();
		            String date = userInfo.getUser_date().toString();
		            connection(myId,myName,mySex,myAvatar,myWin,myLose,nick,date);
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
			mr.setVisible(false);
		}
		else if(e.getSource()==mr.b1)
		{
			String rn=mr.tf.getText().trim();
			if(rn.length()<1)
			{
				JOptionPane.showMessageDialog(this,
						"���̸��� �Է��ϼ���");
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
							"�̹� �����ϴ� ���Դϴ�\n�ٸ� �̸��� �Է��ϼ���");
					mr.tf.setText("");
					mr.tf.requestFocus();
					return;
				}
			}*/
			String state="",pwd="";
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
	      else if(e.getSource()==cr.b1) 
	      {
	         if(cr.b1.getText().equals("�غ����"))
	         {
	            cr.b1.setText("�غ�");
	            try 
	            {
	               out.write((Function.GAMENO+"|"
	                        +myRoom+"|"
	                        +myId+"|"
	                        +"\n").getBytes());
	            } catch (Exception e2) {
	               // TODO: handle exception
	               System.out.println("cr.b1 �غ���� ����: "+e2.getMessage());
	            }
	         }
	         else
	         {
	            cr.b1.setText("�غ����");
	            try 
	            {
	               out.write((Function.GAMEYES+"|"
	                     +myRoom+"|"
	                     +myId+"|"
	                     +"\n").getBytes());
	            } catch (Exception e2) {
	               // TODO: handle exception
	               System.out.println("cr.b1 �غ� ����: "+e2.getMessage());
	            }
	         }
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
		else if(e.getSource()==cr.b2)//ê���� ���۹�ư
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
///////////////////�ΰ���
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
			//��ư�� ������ �� �� �� (0 1 2) ���� �� => �ΰ��ӿ��� ����üũ �� �� ��������� ���
			if(e.getSource()==cn.nation0){
				cn.chosenNation1=0;
				cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
			}
			if(e.getSource()==cn.nation1){
				cn.chosenNation1=1;
				cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
			}
			if(e.getSource()==cn.nation2){
				cn.chosenNation1=2;
				cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
			}
			cn.choiceComplete=true;//���� ���ÿϷ�, ������ �ȵǸ� �ڵ�����
			cn.jangsu();
			try
			{
				out.write((Function.CHOICENATION+"|"+myRoom+"|"+ChoiceNation.chosenNation1+"\n").getBytes());
			}catch(Exception ex){}
		}
/////////////////////////////////////////////////////////////////////////////////////////  
//////////���� üũ(üũ�� ���� �ƴ� ��+��ų�������� Ŭ������ �ʾ��� ��)
		if (GameLayout.bAttCheck1 == false && GameLayout.bDefCheck1 == false && GameLayout.bTrickCheck1 == false 
				&& GameLayout.bDefFCheck1 == false && GameLayout.bTrickFCheck1 == false) {  
			for (int k = 0; k < 3; k++) {  
				for (int l = 0; l < 3; l++) {  
					// ���� ������ ���˿��� ���� ��ư �̹��������� �ٲ��ֱ�  
					ImageIcon nationIcon1 = null, nationIcon2 = null;  
					if(k==0)nationIcon1=GameLayout.bcIcon0;if(k==1)nationIcon1=GameLayout.bcIcon1;if(k==2)nationIcon1=GameLayout.bcIcon2;  
					if(l==0)nationIcon2=GameLayout.bcIcon0;if(l==1)nationIcon2=GameLayout.bcIcon1;if(l==2)nationIcon2=GameLayout.bcIcon2;  
					if (ChoiceNation.chosenNation1 == k && ChoiceNation.chosenNation2 == l)// ����������  
																							// �Ǹ�  
					{
						for (int i = 0; i < 3; i++) {  
							for (int j = 0; j < 25; j++) {  
								// bingo[][]�� üũ �ȵȰ͸� üũ ����,���� ������ �� üũ ����  
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
  
		//////////// ��ų��ư ������  
		if (GameProcess.playerTurn == true && GameLayout.bAttCheck1 == false && GameLayout.bDefCheck1 == false 
				&& GameLayout.bTrickCheck1 == false && GameLayout.bDefFCheck1 == false  
				&& GameLayout.bTrickFCheck1 == false && GameProcess.skillChance1>0) {  
			if (e.getSource() == GameLayout.btnAtt && GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1 > 0)// �÷��̾�1  
																											// ����  
																											// ��ų  
																											// ��ư  
			{  
				GameLayout.bAttCheck1 = true; // ��ų��� �����ϰ� true  
				GameLayout.aNoticeX -= 1190; // ��ų���� ��������(1200���� ȭ�� �ۿ� �ִ� ���� -1190 �ؼ� ȭ�鿡  
									// ����)  
			} else if (e.getSource() == GameLayout.btnDef && GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1 > 0)// ��ų  
			{  
				int k = 0;  
				for (int i = 0; i < 3; i++) {  
					for (int j = 0; j < 25; j++) {  
						if (GameLayout.panCheck1[i][j])  
							k++;  
					}  
				}  
				if (k == 0) { // ���� �� �ɸ� �������� ������, ������ ��ư�� ������� ����  
					JOptionPane.showMessageDialog(this, "���ع��� ������ �����ϴ�.");  
					return;  
				}  
				GameLayout.bDefCheck1 = true;  
				GameLayout.dNoticeX -= 1190;  
			} else if (e.getSource() == GameLayout.btnTrick && GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1 > 0)// å����ų  
			{  
				GameLayout.bTrickCheck1 = true;  
				GameLayout.sNoticeX -= 1190;  
			} else if (e.getSource() == GameLayout.fury[1][0]) // �����ʻ�� ��ư  
			{  
				game.new AFNoticeThread().start();
				try
				{
					out.write((Function.ATTFURY+"|"+myRoom+"|"+myId+"\n").getBytes());
				}catch(Exception ex){}
			} else if (e.getSource() == GameLayout.fury[1][1]) // ����ʻ�� ��ư  
			{  
				if (GameProcess.numOfBingo2[0] + GameProcess.usingAttackSkill2 <= 0  
						&& GameProcess.numOfBingo2[1] + GameProcess.usingDefenseSkill2 <= 0  
						&& GameProcess.numOfBingo2[2] + GameProcess.usingStrategySkill2 <= 0) { // ����  
																								// ���  
																								// ��������  
																								// ������,  
																								// �ʻ��  
																								// ��ư��  
																								// �������  
																								// ����  
					JOptionPane.showMessageDialog(this, "�ı��� �������Ⱑ �����ϴ�.");  
					return;  
				}  
				GameLayout.dFNoticeX -= 1190;  
				if (GameLayout.goongUsable1[1]) {  
					GameLayout.defPan2.setVisible(true);  
				}  
				GameLayout.bDefFCheck1 = true;  
			}  
			// !@#$%  
			else if (e.getSource() == GameLayout.fury[1][2]) // å���ʻ�� ��ư  
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
  
		// ����,���,å����ų ���  
	      for (int i = 0; i < 3; i++) {  
	         for (int j = 0; j < 25; j++) {  
	            if (e.getSource() == GameLayout.a2[i][j] && GameLayout.bAttCheck1 && !GameProcess.bingo2[i][j] && !GameLayout.panCheck1[i][j]) {// ���ݽ�ų
	               GameLayout.aNoticeX += 1190; // ���Ӽ����� ���ֱ�
	               try
	               {
	                  out.write((Function.ATTSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            } else if (e.getSource() == GameLayout.a1[i][j] && GameLayout.bDefCheck1 && GameLayout.panCheck2[i][j]) {// ��ų  
	               GameLayout.dNoticeX += 1190; // ���Ӽ����� ���ֱ�
	               try
	               {
	                  out.write((Function.DEFSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            } else if (e.getSource() == GameLayout.a2[i][j] && GameLayout.bTrickCheck1 && !GameProcess.bingo2[i][j] && !GameLayout.panCheck1[i][j]) {
	               GameLayout.sNoticeX += 1190; // ���Ӽ����� ���ֱ�
	               try
	               {
	                  out.write((Function.TRICKSKILL+"|"+myRoom+"|"+i+"|"+j+"\n").getBytes());
	               }catch(Exception ex){}
	            }  
	         }  
	      }  
		// �����ʻ�� Ŭ�� ��, ����� ��ư Ŭ��  
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
			GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
  
			GameLayout.goongUsable1[1] = false;  
			GameLayout.fury[1][1].setEnabled(false);  
			GameLayout.fury[1][1].setVisible(false);  
			GameLayout.gauge[1][1].setBackground(Color.DARK_GRAY);
			GameLayout.gauge[1][1].setString("�ñر����");
			GameLayout.defPan2.setVisible(false);
			GameLayout.furyEndBtn[1][1].setVisible(true);  
			GameLayout.bDefFCheck1 = false;
			try
			{
//				out.write((Function.DEFFURY+"|"+myRoom+"|"+i+j+"\n").getBytes());
			}catch(Exception ex){}
		}  
		// å���ʻ�� Ŭ�� ��, ��� �� �����ı� ��ư Ŭ��  
		for (int i = 0; i < 3; i++) {  
			if (e.getSource() == GameLayout.jypgChoice[i] && GameLayout.goongUsable1[2] == true) {  
				game.new SFImageThread().start();  
				GameProcess.jypg(0, i);  
				GameLayout.goongUsable1[2] = false;  
				GameLayout.fury[1][2].setEnabled(false);  
				GameLayout.fury[1][2].setVisible(false);  
				GameLayout.gauge[1][2].setBackground(Color.DARK_GRAY);  
				GameLayout.gauge[1][2].setString("�ñر����");  
				for (int j = 0; j < 3; j++) {  
					GameLayout.jypgChoice[j].setVisible(false);//  
				}  
				GameLayout.furyEndBtn[1][2].setVisible(true);  
				GameProcess.skillChance1--;  
				GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
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
				GameLayout.gauge[0][2].setString("�ñر����");  
				for (int j = 0; j < 3; j++) {  
					GameLayout.jypgChoice[j + 3].setVisible(false);//  
				}  
				GameLayout.furyEndBtn[0][2].setVisible(true);  
				GameProcess.skillChance2--;  
				GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));  
				for (int j = 0; j < 6; j++) {  
					GameLayout.jypgChoice[j].setOpaque(false);  
					GameLayout.jypgChoice[j].setVisible(false);//  
				}  
			}  
		}  
  
		if (e.getSource() == GameLayout.timeOut && GameProcess.playerTurn)// ������ 
		{  
			GameLayout.IFNoticeVisible();  
			// ��ų ��� ���� �ʱ�ȭ  
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
		
		if(e.getSource()==GameLayout.exit) //�׺� ��ư
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "�׺��Ͻðڽ��ϱ�?", "�׺�", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //���� ������ ���� ������ ������
			{
				GameLayout.gameEnd.addActionListener(this);//�ΰ��� ������ ��ư
				GameProcess.playerWon=false;
				GameLayout.imageVisibleFalse();
				try
	            {
	               out.write((Function.BINGOEND+"|"+myRoom+"|"+myId+"|"+GameProcess.playerWon+"\n").getBytes());
	            }catch(Exception ex){}
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//���� ������
		{
			GameLayout.gameEnd.addActionListener(this);//�ΰ��� ������ ��ư
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			try
            {
               out.write((Function.BINGOEND+"|"+myRoom+"|"+myId+"|"+GameProcess.playerWon+"\n").getBytes());
            }catch(Exception ex){}
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
                     String name=st.nextToken();
                     String nick=st.nextToken();
                     String date=st.nextToken();
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
                     
                     uif.la_id.setText("���̵�   "+id);
                     uif.la_nickname.setText("�г���   "+name);
                     uif.la_name.setText("�̸�     "+nick);
                     uif.la_day.setText("���Գ�¥ "+date);
                     double Rate=(double)win/(win+lose);
                    if(win+lose==0)
                    {
                       Rate=0;
                    }
                    String winRate=String.format("%.2f", (double)Rate*100.0);
                    uif.avata.setIcon(new ImageIcon("img\\"+ava));
                     uif.la_rate.setText("�·�     "+winRate+"%");
                     uif.la_score.setText("����     "+win+"�� "+lose+"��");
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
	               String id=st.nextToken();   //id
	               String name=st.nextToken(); //��ȭ��
	               String sex=st.nextToken();   //����
	               String avata=st.nextToken(); //�ƹ�Ÿ
	               String rName=st.nextToken(); //������
	               String rb=st.nextToken(); //����ƾƵ�
	               System.out.println("������̵�:"+rb);
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
	                     if(id.equals(rb))//���� ����
	                     {
	                        cr.idtf[i].setForeground(Color.red);
	                     }
	                     cr.pan[i].validate();
	                     break;
	                  }
	               }
	               if(myId.equals(rb))//�����ΰ��
	               {
	                  cr.b1.setEnabled(false); //�غ�
	                  cr.b2.setEnabled(false); //����
//	                  cr.idtf[0].setForeground(Color.red);
	               }
	               else//�� �����ο�
	               {
	                  cr.b1.setEnabled(true); //�غ�
	                  cr.b2.setEnabled(false);//����
//	                  cr.idtf[1].setForeground(Color.red);
	               }
	               cr.setVisible(true);
	            }
	            break;
	            case Function.ROOMIN:
	            {
	               System.out.println("Ŭ���̾�Ʈ ROOMIN �������");
	               String id=st.nextToken();   //id
	               String name=st.nextToken(); //��ȭ��
	               String sex=st.nextToken();   //����
	               String avata=st.nextToken(); //�ƹ�Ÿ
	               String rName=st.nextToken(); //������
	               String rb=st.nextToken(); //����ƾƵ�
	               System.out.println("������̵�:"+rb);
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
	                     if(id.equals(rb))//���� ����
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
	            case Function.BANGCHANGE://ä�÷뿡 ���� �ο��� ���ؼ� ó�� (���� ���� or �ٲ����)
	            {   System.out.println("Ŭ���̾�Ʈ CASE�� BANGCHAGE: ������ ������ ������ �ٲ��� ȣ���Ѵ�");
	               String bj=st.nextToken();//���� �ٲ���� (���� ���̵� �ǰ���..)
	               String name=st.nextToken();
	               int size=Integer.parseInt(st.nextToken());
	                    cr.idtf[0].setForeground(Color.red);
	                    cr.idtf[1].setForeground(Color.BLACK);
	                    cr.b1.setEnabled(false);
	               cr.b2.setEnabled(false);
	            }
	            break;
	            case Function.ROOMOUT: //cr�� �ƹ�Ÿâ�� �������� ����
	            {
	               String id=st.nextToken();
	               String name=st.nextToken();
	               String clientName=st.nextToken();
	               //�ƹ�Ÿ ���̴� ȭ�� ó��cch
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
	               //������ �������� ȭ�� ó��
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
	               cr.b1.setText("��   ��");
	            }
	            break;
	            case Function.MYROOMOUT://?!?!?!?
	            {
	               int size=Integer.parseInt(st.nextToken());
	               //�ƹ�Ÿ â �ʱ�ȭ
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
	               case Function.GAMEYES://������ �Խ�Ʈ�� ����
	               {   System.out.println("client case�� GAMEYES ����");
	                  String yesId=st.nextToken(); //�غ� ���� ���� ���̵� (���� ������̵�)
	                  System.out.println("�غ� ���� ���� ���̵� (���� ������̵�):"+yesId);
	                  String bjId=st.nextToken(); //������̵�
	                  System.out.println("���� ������̵�:"+bjId);
	                  if(myId.equals(yesId))//���� �Խ�Ʈ���
	                  {
	                   cr.idtf[0].setBackground(Color.green);
	                   cr.idtf[0].setText("�غ�Ϸ�");
	                   cr.b3.setEnabled(false);
	                  }
	                  else//���� �����̶��
	                  {
	                   cr.idtf[1].setBackground(Color.green);
	                   cr.idtf[1].setText("�غ�Ϸ�");
	                   cr.b2.setEnabled(true);
	                   cr.b3.setEnabled(true);
	                  }
	               }
	               break;
	               case Function.GAMENO://������ �Խ�Ʈ�� ����
	               {   System.out.println("client case�� GAMENO ����");
	                  String noId=st.nextToken(); //�غ���Ҹ� ���� ���� ���̵�(�Խ�Ʈ ���̵�)
	                  System.out.println("�غ���Ҹ� ���� ���� ���̵�:"+noId);
	                  String noName=st.nextToken(); //�غ���Ҹ� ���� ���� ��ȭ�� (�Խ�Ʈ ��ȭ��)
	                  System.out.println("�غ���Ҹ� ���� ���� ��ȭ��:"+noName);
	                  String bjId=st.nextToken(); //���� ���̵�
	                  System.out.println("���������̵�:"+bjId);
	                  if(myId.equals(noId))//���� �Խ�Ʈ��
	                  {
	                     //System.out.println("myId.equals(bjId):"+myId.equals(noId));
	                   cr.idtf[0].setBackground(Color.WHITE);
	                   cr.idtf[0].setText(noName);
	                   cr.b3.setEnabled(true); //������ ��ư Ȱ��ȭ
	                  }
	                  else//���� ������
	                  {
	                   cr.idtf[1].setBackground(Color.WHITE);
	                   cr.idtf[1].setText(noName);
	                   cr.b2.setEnabled(false);
	                  }
	                  
	               }
	               break;
	                  case Function.GAMESTART: 
	                  {   
	                     //playTurn 0:��, 1:��
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
	                     if(myId.equals(rId))//���� �����̸�
	                     {
	                        GameProcess.numArr1[seq]=Integer.parseInt(st.nextToken());
	                        GameProcess.numArr2[seq]=Integer.parseInt(st.nextToken());
	                     }
	                     else//���� �Խ�Ʈ��
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
	                        if(GameProcess.playerTurn)//�� ���̿��µ� �޼����� ������� (����  �ѱ���)
	                        {
	                           GameProcess.playerTurn=false; //������ �ƴ� ó��
	                           GameLayout.bingoTurnIcon1.setVisible(false); //���� �������� �ƴ�
	                           GameLayout.bingoTurnIcon2.setVisible(true); //���� ��������
	                         //�������, ���ֱ� ����  
	                           GameProcess.skillChance2=1; //���� ������ɱ��� 1��
	                           GameProcess.bingoCheckChance2=1; //���� ���ֱ��� 1��
	                           GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));  
	                           GameLayout.youLaCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance2));
	                        }
	                        else if(!GameProcess.playerTurn)//�� ���̿��µ� �޼����� ������� (����  �������)
	                        {
	                           GameProcess.playerTurn=true;
	                           GameLayout.bingoTurnIcon1.setVisible(true); //���� ��������
	                           GameLayout.bingoTurnIcon2.setVisible(false); //�ʴ� �������� �ƴ�
	                           GameProcess.skillChance1=1; //���� ������ɱ��� 1��
	                           GameProcess.bingoCheckChance1=1; //���� ���ֱ��� 1��
	                           GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
	                           GameLayout.laCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
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
	                        GameLayout.laScore.setText("���� "+win+"�� "+lose+"��");
	                         if((win+lose)==0){
	                            GameLayout.pbScore.setValue(0);
	                       }else{
	                          double rate = (double)win/(win+lose)*100;
	                          GameLayout.pbScore.setValue((int)Math.ceil(rate));
	                       }
	                     }
	                     else//���� �ƴϸ�
	                     {
	                        ChoiceNation.chosenNation2=chosenNation;
	                        if(ChoiceNation.chosenNation2==0)
	                           cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
	                        else if(ChoiceNation.chosenNation2==1)
	                           cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
	                        else
	                           cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
	                        
	                        GameLayout.youLaNickname.setText(name);
	                        GameLayout.youBtnAvatar.setIcon(new ImageIcon("img\\"+avata));
	                        GameLayout.youLaScore.setText("���� "+win+"�� "+lose+"��");
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
							GameLayout.laCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
//							if(GameLayout.panCheck2[panNumber][bingoCheckNumber]==true)//���ɸ��� �ȹٲ�
//								GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	            	   }
	            	   else
	            	   {
	            		   GameProcess.bingoCheck(panNumber, bingoCheckNumber, 
 				   				  GameProcess.p2Board, GameProcess.p1Board,  
 				   				  GameProcess.bingo2, GameProcess.bingo1, 
 				   				  GameLayout.a2, GameLayout.a1, 
 				   				  nationIcon2, nationIcon1); 
	            		   GameProcess.bingoCheckChance2=0;
	            		   GameLayout.youLaCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance2));
							if(GameLayout.panCheck2[panNumber][bingoCheckNumber]==true)//���ɸ��� �ȹٲ�
								GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));
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
	                        GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                        GameProcess.usingAttackSkill1--;
	                        GameLayout.useAtt--;
	                        GameLayout.laAtt.setText("x" + String.valueOf(GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1));  
	                        GameLayout.bAttCheck1 = false;  
	                        GameProcess.skillChance1--;  
	                        GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
	                        GameLayout.panCheck1[panNumber][bingoCheckNumber] = true; // ������ �� Ȯ��.
	                     }
	                     else
	                     {
	                        game.new AImageThread().start();
	                      GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));
	                        GameProcess.usingAttackSkill2--;
	                        GameLayout.youUseAtt--;
	                        GameLayout.youLaAtt.setText("x" + String.valueOf(GameProcess.numOfBingo2[0] + GameProcess.usingAttackSkill2));  
	                        GameProcess.skillChance2--;  
	                        GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));  
	                        GameLayout.panCheck2[panNumber][bingoCheckNumber] = true; // ������ �� Ȯ��.
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
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                        else if (ChoiceNation.chosenNation1 == 1)  
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                        else if (ChoiceNation.chosenNation1 == 2)  
	                           GameLayout.a1[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                     }  
	        
	                     GameLayout.bDefCheck1 = false;  
	                     GameProcess.skillChance1--;  
	                     GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
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
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                        else if (ChoiceNation.chosenNation2 == 1)  
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                        else if (ChoiceNation.chosenNation2 == 2)  
	                           GameLayout.a2[panNumber][bingoCheckNumber].setIcon(new ImageIcon("img\\����üũ-��.png"));  
	                     }  
	                     GameProcess.skillChance2--;
	                     GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));  
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
	                     GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
	                     GameLayout.bTrickCheck1 = false;
	                     }
	                     else
	                     {
	                        game.new SImageThread().start();
	                        GameProcess.skillChance2--;
	                        GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));
	                     }
	                  }
	                  break;
	                  case Function.ATTFURY:
	                  {
	                	  String furyId=st.nextToken();
	                	  if(furyId.equals(myId))
	                	  {
		                	  game.new AFImageThread().start();  
		                	  GameProcess.bingoCheckChance1++;// ���ݱ�ȸ+1,�����ۻ���ȸ+1  
		                	  GameProcess.skillChance1++;  
		                	  GameLayout.goongUsable1[0] = false;  
		                	  GameLayout.fury[1][0].setEnabled(false);  
		                	  GameLayout.gauge[1][0].setBackground(Color.DARK_GRAY);  
		                	  GameLayout.gauge[1][0].setString("�ñر����");
	                	  }
	                	  else
	                	  {
	                		  game.new AFImageThread().start();
	                		  GameProcess.bingoCheckChance2++;// ���ݱ�ȸ+1,�����ۻ���ȸ+1  
		                	  GameProcess.skillChance2++;  
		                	  GameLayout.goongUsable2[0] = false;  
		                	  GameLayout.fury[2][0].setEnabled(false);  
		                	  GameLayout.gauge[2][0].setBackground(Color.DARK_GRAY);  
		                	  GameLayout.gauge[2][0].setString("�ñر����");
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
	            	if(!cn.choiceComplete) //���� ������ �ȵǸ� �ڵ�����
	            	{
	            		ChoiceNation.chosenNation1=a;
	            		if(ChoiceNation.chosenNation1==0)
	            			cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
	            		else if(ChoiceNation.chosenNation1==1)
	            			cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
	            		else
	            			cn.bu1.setIcon(new ImageIcon("img\\����üũ-��.png"));
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
                   	 cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
                    else if(ChoiceNation.chosenNation2==1)
                   	 cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
                    else
                   	 cn.bu2.setIcon(new ImageIcon("img\\����üũ-��.png"));
	            }
			} catch (Exception ex) {
			}
		}
	}
	public static class TimeLimit extends Thread {
		int[] rgb = new int[3];
		 // �ð����ѹٸ� ä��� �ۼ�Ʈ (20��:100�ۼ�Ʈ ��, 0.2��: 1�ۼ�Ʈ)
		double residueTime = 20; // �����ð�ǥ�� (�ʱⰪ:20��)
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
					if (percent > 100)// 100�ۼ�Ʈ�� �Ǹ� �ٽ� 0���� �ʱ�ȭ
					{
						if(GameProcess.playerTurn)//�� ���̿��µ� �޼����� ������� (����  �ѱ���)
                        {
                           GameProcess.playerTurn=false; //������ �ƴ� ó��
                           GameLayout.bingoTurnIcon1.setVisible(false); //���� �������� �ƴ�
                           GameLayout.bingoTurnIcon2.setVisible(true); //���� ��������
                         //�������, ���ֱ� ����  
                           GameProcess.skillChance2=1; //���� ������ɱ��� 1��
                           GameProcess.bingoCheckChance2=1; //���� ���ֱ��� 1��
                           GameLayout.youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));  
                           GameLayout.youLaCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance2));
                        }
                        else if(!GameProcess.playerTurn)//�� ���̿��µ� �޼����� ������� (����  �������)
                        {
                           GameProcess.playerTurn=true;
                           GameLayout.bingoTurnIcon1.setVisible(true); //���� ��������
                           GameLayout.bingoTurnIcon2.setVisible(false); //�ʴ� �������� �ƴ�
                           GameProcess.skillChance1=1; //���� ������ɱ��� 1��
                           GameProcess.bingoCheckChance1=1; //���� ���ֱ��� 1��
                           GameLayout.laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));  
                           GameLayout.laCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
                        }
						percent=0;
						residueTime=20;
						colorInt = 0;
						rgb[0] = colorInt;
						rgb[1] = 255;
						GameLayout.IFNoticeVisible(); // �ð��� ������ ���Ӽ����� ���� ��ų�����false�� ��
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
						GameLayout.timer.setString("�������:" + rr);
					} else {
						String rr = rt;
						GameLayout.timer.setString("�������:" + rr);
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
