package com.sist.client;
import java.awt.*;
import javax.swing.*;

import com.sist.client.GameLayout.TimeLimit;

import java.awt.event.*;
import java.net.*; 
import java.io.*;
public class ClientMainForm extends JFrame
implements ActionListener
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	GameInfo gi=new GameInfo();
	ImageIcon mainIcon;//Ÿ��Ʋâ ������
	static Thread t1=new TimeLimit();//�ð����ѹ� ������
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
		else if(e.getSource()==login.b3) // ȸ������ ��ư
		{
			SignUp su = new SignUp();
			su.setVisible(true);
		}
		
		if(e.getSource()==wr.b5) // �������� ��ư
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
			new GameProcess();
			card.show(getContentPane(), "ChoiceNation");
			System.out.println(GameProcess.numOfBingo1[0]);
		}
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
			CoinFlip cf=new CoinFlip();
			cf.coinEnd=true; 
			cf.setVisible(true);
			if(cf.coinEnd==false) // ���÷��̼��� �������� �����尡 ����Ǹ�,,, �ð����ѹ� �����带 ������
			{
				card.show(getContentPane(), "GAME");
				t1.start();
				game.requestFocus();
			}
		}
		else if(e.getSource()==game.exit)
		{
			card.show(getContentPane(), "WR");
		}
		
	}
}







