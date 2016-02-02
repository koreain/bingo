package com.sist.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;;

class GameImage extends JPanel{ //게임정보, 스킬이미지를 불러오는 클래스
	static Image back;
	static Image attackSkillNotice;//스킬설명이미지들
	static Image attackFinishNotice;
	static Image defenseSkillNotice;
	static Image defenseFinishNotice;
	static Image strategySkillNotice;
	static Image strategyFinishNotice;
	static Image attackSkillImage;//스킬이미지들
	static Image attackFinishImage;
	static Image defenseSkillImage;
	static Image defenseFinishImage;
	static Image strategySkillImage;
	static Image strategyFinishImage;
	
	int noticeX=1200,noticeY=400;//스킬설명 초기위치
	int skillImageX=1200, skillImageY=400;
	
	public GameImage(){
		back=Toolkit.getDefaultToolkit().getImage("img\\게임정보.jpg");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격.jpg");
		attackFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격필살기.jpg");
		defenseSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어.jpg");
		defenseFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어필살기.jpg");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략.jpg");
		strategyFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략필살기.jpg");
		
		attackSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격.png");
		attackFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격필살기.png"); 
		defenseSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어.png");   
		defenseFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어필살기.png");   
		strategySkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략.png");   
		strategyFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략필살기.png");
	}
	public void paint(Graphics g){
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
		
		g.drawImage(attackFinishNotice, noticeX, noticeY, attackFinishNotice.getWidth(this), 140, this);
		g.drawImage(attackFinishImage, skillImageX, skillImageY, attackFinishImage.getWidth(this), attackFinishImage.getHeight(this), this);

	}

}