package com.sist.client;
import java.awt.*;
import javax.swing.*;
public class Dialog_Skill_Image extends JDialog{
	static int imageX=0;
	public Dialog_Skill_Image()
	{
		Dialog_View dv=new Dialog_View();
		add("Center",dv);
		setSize(850,400);
		setLocation(imageX, 200);
		setModal(true);
		this.setUndecorated(true);
		setVisible(false);
	}
}
class Dialog_View extends JPanel
{
	static Image attackSkillImage;//스킬이미지들
	static Image attackFinishImage;
	static Image defenseSkillImage;
	static Image defenseFinishImage;
	static Image strategySkillImage;
	static Image strategyFinishImage;
	public Dialog_View()
	{
		attackSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격.png");
		attackFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격필살기.png");
		defenseSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어.png");
		defenseFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어필살기.png");
		strategySkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략.png");
		strategyFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략필살기.png");
//		if(GameLayout.bAttCheck1||GameLayout.bAttCheck2)
//			new AttackDialog.start();
//		if(GameLayout.goongUsable1[0]||GameLayout.goongUsable2[0])
//			new AttackFDialog().start();
//		else if(GameLayout.bDefCheck1||GameLayout.bDefCheck2)
//		else if(GameLayout.goongUsable1[1]||GameLayout.goongUsable2[1])
//		else if(GameLayout.bTrcikCheck1||GameLayout.bTrcikCheck2)
//		else if(GameLayout.goongUsable1[2]||GameLayout.goongUsable2[2])
	}
	@Override
	public void paint(Graphics g) {
			g.drawImage(attackSkillImage, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(attackFinishImage, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(defenseSkillImage, getWidth(), getHeight(), this);
			g.drawImage(defenseFinishImage, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(strategySkillImage, 0, 0, getWidth(), getHeight(), this);
			g.drawImage(strategyFinishImage, 0, 0, getWidth(), getHeight(), this);
	}
	class AttackDialog extends Thread
	{
		public void run(){
			
		}
	}
	class AttackFDialog extends Thread
	{
		
		public void run(){
			setVisible(true);
			GameLayout.aFNoticeX-=1190;
			   repaint();
			   try
			   {	
				   Thread.sleep(2000);
			   }catch(Exception ex){}
			   GameLayout.aFNoticeX+=1190;
			   repaint();
			   while(Dialog_Skill_Image.imageX>=0)
			   {
				   try
				   {
					   Dialog_Skill_Image.imageX-=3;
					   Thread.sleep(1);
				   }catch(Exception ex){}
			   }
			   try
			   {
				   Thread.sleep(2000);
			   }catch(Exception ex){}
			   Dialog_Skill_Image.imageX+=1200;
			   setVisible(false);
		}
	}
	class DefenseDialog extends Thread
	{
		
	}
	class DefenseFDialog extends Thread
	{
		
	}
	class strategyDialog extends Thread
	{
		
	}
	class StrategyFDialog extends Thread
	{
		
	}
}
