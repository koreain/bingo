package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChoiceNation extends JPanel{
	int chosenNation=0; //0=위, 1=촉, 2=오
	ImageIcon icon0=new ImageIcon("c:\\bingo\\나라선택아이콘-위.png"); // 위촉오 선택버튼 이미지
	ImageIcon icon1=new ImageIcon("c:\\bingo\\나라선택아이콘-촉.png");
	ImageIcon icon2=new ImageIcon("c:\\bingo\\나라선택아이콘-오.png");
	JButton nation0 = new JButton(icon0); //위촉오 선택버튼
	JButton nation1 = new JButton(icon1);
	JButton nation2 = new JButton(icon2);
	Image bg, notice;//배경 이미지
	
	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	public ChoiceNation()
	{
		bg=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\나라선택-배경.jpg");
		notice=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\나라선택-설명.jpg");
		setLayout(null);
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
		
		//버튼을 누르면 위 촉 오 (0 1 2) 값이 들어감
		nation0.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation=0;
			}
		});

		nation1.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation=0;
			}
		});

		nation2.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation=0;
			}
		});
		
		add(nation0);
		add(nation1);
		add(nation2);
		
		nation0.setCursor(cur);
		nation1.setCursor(cur);
		nation2.setCursor(cur);
		
		setSize(1200,970);
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		  g.drawImage(bg, 0, 0, 1200, 970, this);
		  g.drawImage(notice,50,120,402,120,this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame(); 
		f.setPreferredSize(new Dimension(1200, 970)); 
		f.setLocation(0, 0); 
		Container con = f.getContentPane(); 
		ChoiceNation M = new ChoiceNation(); 
		con.add(M); 
		f.pack(); 
		f.setVisible(true); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
