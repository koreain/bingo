package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChoiceNation extends JPanel{
	static public int chosenNation1=0; //0=위, 1=촉, 2=오
	static public int chosenNation2=0;
	ImageIcon icon0=new ImageIcon("img\\나라선택아이콘-위.png"); // 위촉오 선택버튼 이미지
	ImageIcon icon1=new ImageIcon("img\\나라선택아이콘-촉.png");
	ImageIcon icon2=new ImageIcon("img\\나라선택아이콘-오.png");
	ImageIcon icon3 = new ImageIcon("img\\나라선택쓰레드.jpg");
	JButton nation0 = new JButton(icon0); //위촉오 선택버튼
	JButton nation1 = new JButton(icon1);
	JButton nation2 = new JButton(icon2);
	boolean choiceComplete;
	JPanel jp = new JPanel() {
	      public void paintComponent(Graphics g) {
	         g.drawImage(icon3.getImage(), 0, 0, this);
	      }
	   };
	JLabel la1 = new JLabel("당신");	
	JLabel la2 = new JLabel("상대");
	JLabel la3 = new JLabel("남은 시간");
	JButton bu1 = new JButton(new ImageIcon("img\\빙고판-상대.png"));
	JButton bu2 = new JButton(new ImageIcon("img\\빙고판-상대.png"));
	static int cntime = 7;
	static JLabel la6 = new JLabel(String.valueOf(cntime));
	Image bg, notice;//배경 이미지
	static JButton[] jangSu1 = new JButton[3]; 
	static JButton[] jangSu2 = new JButton[3];

	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	public void jangsu()
	{
		for(int i=0;i<6;i++)
		{
			if(i<3)
			{
				jangSu1[i].setIcon(new ImageIcon("img\\장수"+chosenNation1+i+".jpg")); 
				GameLayout.j1.add(jangSu1[i]); 
				jangSu1[i].setContentAreaFilled(false); 
				jangSu1[i].setBorderPainted(false); //버튼 경계선 제거 
			}
			else
			{
				jangSu2[i%3].setIcon(new ImageIcon("img\\장수"+chosenNation2+(i%3)+".jpg"));
				GameLayout.j2.add(jangSu2[i%3]); 
				jangSu2[i%3].setContentAreaFilled(false); 
				jangSu2[i%3].setBorderPainted(false); 
			}
		}
	}
	
	public ChoiceNation()
	{
		for(int i=0;i<3;i++)
		{ 
			jangSu1[i] = new JButton();  
			jangSu2[i] = new JButton(); 
		}
		bg=Toolkit.getDefaultToolkit().getImage("img\\나라선택-배경.jpg"); //그림가져오기
		notice=Toolkit.getDefaultToolkit().getImage("img\\나라선택-설명.jpg");
	
		setLayout(null);
		jp.setLayout(null);
		jp.setBounds(760, 110, 360, 130);

		la1.setBounds(50, 20, 50, 20);
		la2.setBounds(150, 20, 50, 20);
		la3.setBounds(230, -20, 100, 100);
		la6.setBounds(255, 30, 100, 100);
		bu1.setBounds(45, 55, 52, 52);
		bu1.setBorderPainted(false); //버튼 경계선 제거
		bu1.setContentAreaFilled(false); //선택했던 버튼 표시 제거
		bu1.setFocusPainted(false); //버튼영역 배경 제거
		bu2.setBounds(145, 55, 52, 52);
		bu2.setBorderPainted(false); //버튼 경계선 제거
		bu2.setContentAreaFilled(false); //선택했던 버튼 표시 제거
		bu2.setFocusPainted(false); //버튼영역 배경 제거
		

		la1.setFont(new Font("궁서", Font.BOLD, 18));
		la2.setFont(new Font("궁서", Font.BOLD, 18));
		la3.setFont(new Font("궁서", Font.BOLD, 18));
		la6.setFont(new Font("궁서", Font.BOLD, 60));

		jp.add(la1);
		jp.add(la2);
		jp.add(la3);
		jp.add(bu1);
      	jp.add(bu2);
      	jp.add(la6);
      	add(jp);

		// 버튼에 아이콘 사이즈 맞추기 
		nation0.setPreferredSize(new Dimension(icon0.getIconWidth(), icon0.getIconHeight()));
		nation0.setBorderPainted(false); //버튼 경계선 제거
		nation0.setContentAreaFilled(false); //선택했던 버튼 표시 제거
		nation0.setFocusPainted(false); //버튼영역 배경 제거
		
		nation1.setPreferredSize(new Dimension(icon1.getIconWidth(), icon1.getIconHeight()));
		nation1.setBorderPainted(false);
		nation1.setContentAreaFilled(false); 
		nation1.setFocusPainted(false);
		
		nation2.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
		nation2.setBorderPainted(false);
		nation2.setContentAreaFilled(false); 
		nation2.setFocusPainted(false);
		
		nation0.setBounds(550, 350, 140, 140);
		nation1.setBounds(400, 600, 140, 140);
		nation2.setBounds(700, 600, 140, 140);
		
		add(nation0); // 화면에 나라 버튼 아이콘 3개 추가
		add(nation1);
		add(nation2);
		
		nation0.setCursor(cur); //버튼 위에 커서를 올리면 손모양으로 변화되게 설정
		nation1.setCursor(cur);
		nation2.setCursor(cur);
	}
	@Override
	public void paintComponent(Graphics g){
		  g.drawImage(bg, 0, 0, 1280, 970, this); //배경 그리기
		  g.drawImage(notice,80,110,360,130,this); //설명 그리기
	}
}