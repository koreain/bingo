package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.xml.internal.ws.assembler.dev.ServerTubelineAssemblyContext;

public class CoinFlip extends JDialog{
	Image cfBack; //����̹���
	Image fCoin, bCoin; //���İ� �̹���
	int type=0;
	int x=250;
	int y=140;
	public CoinFlip(){
		setModal(true);
		setSize(600,380);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(false);
		cfBack=Toolkit.getDefaultToolkit().getImage("img\\���İ����.jpg");
		fCoin=Toolkit.getDefaultToolkit().getImage("img\\���İ�0.png");
		bCoin=Toolkit.getDefaultToolkit().getImage("img\\���İ�1.png");
		new FrontThread().start();
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(cfBack, 0, 0, getWidth(),getHeight() ,this);
		if(type==0){
			g.drawImage(fCoin, x, y,100,100,this);
		}
		else{
			g.drawImage(bCoin, x, y, 100,100 ,this);
		}
	}
	class FrontThread extends Thread{
		int a=0;
		int b=(int)(Math.random()*10)+20;
		public void run(){
			try{
				while(a<b){
					if(a%2==0)
						type=0;
					else
						type=1;
					a++;
					Thread.sleep(200);
					repaint();
				}
				Thread.sleep(3000);
				setVisible(false);
			}catch(Exception ex){}
		}
	}
}