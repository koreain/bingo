package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ClientMainForm extends JFrame
implements ActionListener
{
	Login login=new Login();
//	Loading2 ld2=new Loading2();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	
	ClientMainForm() 
	{
		super("�ﱹ�� ��������");//Ÿ��Ʋ ����
		mainIcon=new ImageIcon("img\\Ÿ��Ʋ������.png");
		this.setIconImage(mainIcon.getImage());
		
		
		setLayout(card);//BorderLayout
		add("LOG",login);
//		add("LOADING2",ld2);
		add("WR",wr);
		add("ChoiceNation",cn);//������ ȭ��
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		
		login.b1.addActionListener(this);//ī�巹�̾ƿ��� �� ��ư�� �߰�
		login.b2.addActionListener(this);
		login.b3.addActionListener(this);//ȸ������
		wr.b6.addActionListener(this);
		wr.b3.addActionListener(this);
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1) //�α����� ������ ���Ƿ� �̵�
		{
			card.show(getContentPane(), "WR");
		}
		else if(e.getSource()==login.b2) //��Ҹ� ������ ���α׷� ����
		{
			System.exit(0);
		}
		else if(e.getSource()==login.b3) // ȸ������ ��ư.
		{
			SignUp su = new SignUp();
			su.setVisible(true);
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
			JOptionPane.showMessageDialog(cn, "choice1Num="+ChoiceNation.chosenNation1+
			"\n choice2Num="+ChoiceNation.chosenNation2);
			card.show(getContentPane(), "GAME");
			game.requestFocus(); 
		}
		
	}
}
