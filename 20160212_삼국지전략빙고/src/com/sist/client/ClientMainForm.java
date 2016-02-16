package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.*;
import java.io.*;
import com.sist.client.GameLayout.TimeLimit;
public class ClientMainForm extends JFrame
implements ActionListener, Runnable
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	GameInfo gi=new GameInfo();
	//MakeRoom mr=new MakeRoom();
	
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	static Thread t1=new TimeLimit();//�ð����ѹ� ������ 
	
	Socket s;
    BufferedReader in;
    OutputStream out;
    
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
		
		wr.b3.addActionListener(this); //test
		wr.b5.addActionListener(this); //�������� 
		wr.b6.addActionListener(this); //������
		
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		game.exit.addActionListener(this); //�׺� ��ư 
		
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
	
	@Override
	public void actionPerformed(ActionEvent e){
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
						JOptionPane.showMessageDialog(login,"���������� �α��� �߽��ϴ�.");
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
				UserDTO sendData=userInfo;//�Ѱ��� �������� ������
				card.show(getContentPane(), "WR");
			}
			/*try
			{
				if(loginOk)
				{
					s=new Socket("211.238.142.40", 33333);
					in=new BufferedReader(
		    				new InputStreamReader(s.getInputStream()));
		    			out=s.getOutputStream();
		    	    //out.write((sendData).getBytes());
		    		//System.out.println(s.getInetAddress());
				}
							}catch(Exception ex){}*/
			/*new Thread(this).start();*/
			//card.show(getContentPane(), "WR");
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
		
		else if(e.getSource()==wr.b5) // �������� ��ư
		{
			gi.setVisible(true); 
		}
		else if(e.getSource()==wr.b6) //�����⸦ ������ ���α׷� ����
		{
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==wr.b3) //1:1������ ������ ��������â
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
		else if(e.getSource()==game.exit) //������ ������ �� ������ ��ư
		{
			ClientMainForm.t1.interrupt();
			card.show(getContentPane(), "WR");
			game.removeAll();
			GameProcess.gameReset();
			game=new GameLayout();
			//game.gameReset();
			add(game,"GAME");
			System.out.println("�÷��̾��� ����>> true:�÷��̾�1 /// false:�÷��̾�2");
			System.out.println("�÷��̾���: "+GameProcess.playerTurn);
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
				String[] data={st.nextToken(), st.nextToken()};
			    wr.model2.addRow(data);
			}
		}catch(Exception ex){}
	}
}