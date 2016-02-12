package com.sist.client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import com.sun.xml.internal.ws.assembler.dev.ServerTubelineAssemblyContext;

import sun.net.www.content.image.jpeg;
import sun.nio.cs.UnicodeEncoder;
public class BingoEnd extends JDialog{
	BingoEndImage bei=new BingoEndImage();
	Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	Image back, boom; //배경이미지
	public BingoEnd(){
		setModal(true);
		setSize(600,380);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		add(bei);
	}
}
class BingoEndImage extends JPanel
{
	GameLayout gl=new GameLayout();
	Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	Image back, boom; //배경이미지
	JButton endBtn;
	public BingoEndImage()
	{
		setBorder(null);
		back=Toolkit.getDefaultToolkit().getImage("img\\마무리-버튼배경.png");
		ImageIcon m=new ImageIcon("img\\마무리-버튼.png");
		endBtn= new JButton(m);
		endBtn.setBorderPainted(false); //버튼 경계선 제거
		endBtn.setContentAreaFilled(false); //선택했던 버튼 표시 제거
		endBtn.setFocusPainted(false); //버튼영역 배경 제거
		endBtn.setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
		endBtn.setCursor(cur);
		JPanel p=new JPanel();
		p.add(endBtn);
		p.setOpaque(true);
		p.setBounds(10,10,75,75); 
		add(endBtn);
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(back, 0, 0, getWidth(),getHeight() ,this);
	}
}