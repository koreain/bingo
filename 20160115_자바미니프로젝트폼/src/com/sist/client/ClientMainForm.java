package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ClientMainForm extends JFrame
implements ActionListener
{
	Login login=new Login();
	WaitRoom wr=new WaitRoom();
	CardLayout card=new CardLayout();
	GameLayout game=new GameLayout();
	
	ClientMainForm() 
	{
		setLayout(card);//BorderLayout
		add("LOG",login);
		add("WR",wr);
		add("GAME",game);
		setSize(1200,970);
		setVisible(true);
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		wr.b6.addActionListener(this);
		wr.b3.addActionListener(this);
		setResizable(false); //윈도우창 고정
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		}catch(Exception ex){}
		new ClientMainForm();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1)
		{
			card.show(getContentPane(), "WR");
		}
		else if(e.getSource()==login.b2)
		{
			System.exit(0);
		}
		else if(e.getSource()==wr.b6)
		{
			System.exit(0);
		}
		else if(e.getSource()==wr.b3)
		{
			card.show(getContentPane(), "GAME");
		}
	}
}
