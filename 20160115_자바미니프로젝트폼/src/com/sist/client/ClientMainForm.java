package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ClientMainForm extends JFrame
implements ActionListener
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	ChoiceNation cn=new ChoiceNation();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	
	ClientMainForm() 
	{
		setLayout(card);//BorderLayout
		add("LOG",login);
		add("WR",wr);
		add("ChoiceNation",cn);//
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		
		login.b1.addActionListener(this);//카드레이아웃이 될 버튼들 추가
		login.b2.addActionListener(this);
		wr.b6.addActionListener(this);
		wr.b3.addActionListener(this);
		cn.nation0.addActionListener(this);
		cn.nation1.addActionListener(this);
		cn.nation2.addActionListener(this);
		
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
			card.show(getContentPane(), "WR");
		}
		else if(e.getSource()==login.b2) //취소를 누르면 프로그램 종료
		{
			System.exit(0);
		}
		else if(e.getSource()==wr.b6) //나가기를 누르면 프로그램 종료
		{
			System.exit(0);
		}
		else if(e.getSource()==wr.b3) //1:1게임을 누르면 진영선택창
		{
			card.show(getContentPane(), "ChoiceNation");
		}
		else if(e.getSource()==cn.nation0||e.getSource()==cn.nation1
				||e.getSource()==cn.nation2)
		{
			card.show(getContentPane(), "GAME");
		}
	}
}
