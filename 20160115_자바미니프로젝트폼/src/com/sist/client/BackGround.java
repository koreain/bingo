package com.sist.client;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BackGround extends JPanel{
	Image image;
   
	public BackGround(){
		image = new ImageIcon("c:\\bingo\\인게임배경.jpg").getImage();
      
	}
	// 오버라이딩..,
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);//투명하게
	}
}
