package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class GameView extends JPanel{
	Image back;
	
	public GameView(){
		back=Toolkit.getDefaultToolkit().getImage("img\\게임정보.jpg");
	}
	public void paint(Graphics g){
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}

}
public class GameInfo extends JDialog{
	GameView gv=new GameView();
	public GameInfo(){
		add("Center", gv);
		this.setModal(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(false);	
	}
}
