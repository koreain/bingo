package com.sist.client;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BackGround extends JPanel{
	Image image;
   
	public BackGround(){
		image = new ImageIcon("c:\\bingo\\�ΰ��ӹ��.jpg").getImage();
      
	}
	// �������̵�..,
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);//�����ϰ�
	}
}
