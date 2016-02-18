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
	
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	Thread t1=game.new TimeLimit();//�ð����ѹ� ������ 
	Socket s;
    BufferedReader in;
    OutputStream out;
 // ���� ����
    String myId,myRoom;
	ClientMainForm()
	{
		super("�ﱹ�� ��������");//Ÿ��Ʋ ����
		mainIcon=new ImageIcon("img\\Ÿ��Ʋ������.png");
		this.setIconImage(mainIcon.getImage());
		
		setLayout(card);//BorderLayout
		add("LOG",login);
		add("WR",wr);
		add("ChoiceNation",cn);//������ ȭ��
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		login.b1.addActionListener(this);//ī�巹�̾ƿ��� �� ��ư�� �߰�
		login.b2.addActionListener(this);
		login.b3.addActionListener(this);//ȸ������
		
		wr.b2.addActionListener(this); //test(���ӹ��)
		wr.b3.addActionListener(this); //�������� 
		wr.b4.addActionListener(this); //������
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
		GameLayout.exit.addActionListener(this); //�׺� ��ư 
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //������â ����
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
		if(e.getSource()==login.b1) //�α����� ������ ���Ƿ� �̵�
		{
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
		            connection(myId,myName,mySex,myAvatar);
		            myRoom="����";
					card.show(getContentPane(), "WR"); 
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
			GameLayout.endBtn.addActionListener(this);//����������ư
			GameLayout.gameEnd.addActionListener(this);//�ΰ��� ������ ��ư
		}
		else if(e.getSource()==GameLayout.exit) //�׺� ��ư
		{
			int exitValue=JOptionPane.showConfirmDialog(this, "�׺��Ͻðڽ��ϱ�?", "�׺�", JOptionPane.YES_NO_OPTION);
			if(exitValue==JOptionPane.YES_OPTION) //���� ������ ���� ������ ������
			{
				GameLayout.imageVisibleFalse();
				repaint();
				GameProcess.playerWon=false;
				game.new endThread().start();
			}
		}
		else if(e.getSource()==GameLayout.endBtn)//���� ������
		{
			GameLayout.endBtn.setVisible(false);
			GameLayout.endBackX+=975;
			GameProcess.playerWon=true;
			game.new endThread().start();
		}
		else if(e.getSource()==GameLayout.gameEnd) //������
		{
			if(GameProcess.playerWon==true) //���� �̹��� ���ֱ�
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