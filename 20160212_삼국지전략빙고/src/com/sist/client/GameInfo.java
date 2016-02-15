package com.sist.client;
import java.awt.*;
import javax.swing.*;

public class GameInfo extends JDialog{
	public GameInfo(){
		GameView gv=new GameView();
		add("Center", gv);
		this.setModal(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(false);	
	}
}
class GameView extends JPanel{
	Image back;
	public GameView(){
		back=Toolkit.getDefaultToolkit().getImage("img\\게임정보.jpg");
	}
	public void paint(Graphics g){
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}
}