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
	Image back, boom; //����̹���
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
	Image back, boom; //����̹���
	JButton endBtn;
	public BingoEndImage()
	{
		setBorder(null);
		back=Toolkit.getDefaultToolkit().getImage("img\\������-��ư���.png");
		ImageIcon m=new ImageIcon("img\\������-��ư.png");
		endBtn= new JButton(m);
		endBtn.setBorderPainted(false); //��ư ��輱 ����
		endBtn.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		endBtn.setFocusPainted(false); //��ư���� ��� ����
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