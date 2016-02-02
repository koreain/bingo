package com.sist.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;;

class GameImage extends JPanel{ //��������, ��ų�̹����� �ҷ����� Ŭ����
	static Image back;
	static Image attackSkillNotice;//��ų�����̹�����
	static Image attackFinishNotice;
	static Image defenseSkillNotice;
	static Image defenseFinishNotice;
	static Image strategySkillNotice;
	static Image strategyFinishNotice;
	static Image attackSkillImage;//��ų�̹�����
	static Image attackFinishImage;
	static Image defenseSkillImage;
	static Image defenseFinishImage;
	static Image strategySkillImage;
	static Image strategyFinishImage;
	
	int noticeX=1200,noticeY=400;//��ų���� �ʱ���ġ
	int skillImageX=1200, skillImageY=400;
	
	public GameImage(){
		back=Toolkit.getDefaultToolkit().getImage("img\\��������.jpg");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-����.jpg");
		attackFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-�����ʻ��.jpg");
		defenseSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-���.jpg");
		defenseFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-����ʻ��.jpg");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-å��.jpg");
		strategyFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-å���ʻ��.jpg");
		
		attackSkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����.png");
		attackFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-�����ʻ��.png"); 
		defenseSkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-���.png");   
		defenseFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����ʻ��.png");   
		strategySkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å��.png");   
		strategyFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å���ʻ��.png");
	}
	public void paint(Graphics g){
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
		
		g.drawImage(attackFinishNotice, noticeX, noticeY, attackFinishNotice.getWidth(this), 140, this);
		g.drawImage(attackFinishImage, skillImageX, skillImageY, attackFinishImage.getWidth(this), attackFinishImage.getHeight(this), this);

	}

}