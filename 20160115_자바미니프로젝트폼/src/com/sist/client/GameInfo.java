package com.sist.client;
import java.awt.*;
import javax.swing.*;

public class GameInfo extends JDialog{
	GameImage gimage=new GameImage();
	public GameInfo(){
		add("Center", gimage);
		this.setModal(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(false);	
	}
}