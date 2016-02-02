package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChoiceNation extends JPanel implements ActionListener{
	static public int chosenNation1=0; //0=위, 1=촉, 2=오
	static public int chosenNation2=0;
	static public JButton[][] jangSu=new JButton[2][3];
	ImageIcon icon0=new ImageIcon("img\\나라선택아이콘-위.png"); // 위촉오 선택버튼 이미지
	ImageIcon icon1=new ImageIcon("img\\나라선택아이콘-촉.png");
	ImageIcon icon2=new ImageIcon("img\\나라선택아이콘-오.png");
	JButton nation0 = new JButton(icon0); //위촉오 선택버튼
	JButton nation1 = new JButton(icon1);
	JButton nation2 = new JButton(icon2);
	Image bg, notice;//배경 이미지
	
	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	public void jangsu()
	{
		for(int i=0;i<6;i++)
		{
			if(i<3)
			{
				jangSu[1][i]=new JButton(new ImageIcon("img\\장수"+chosenNation1+i+".jpg"));
				//a1.setEnabled(false);
				GameLayout.j1.add(jangSu[1][i]);
				jangSu[1][i].setContentAreaFilled(false);
				jangSu[1][i].setBorderPainted(false); //버튼 경계선 제거
			}
			else
			{
				jangSu[0][i%3]=new JButton(new ImageIcon("img\\장수"+chosenNation2+(i%3)+".jpg"));
				//a1.setEnabled(false);
				GameLayout.j2.add(jangSu[0][i%3]);
				jangSu[0][i%3].setContentAreaFilled(false);
				jangSu[0][i%3].setBorderPainted(false);
			}
		}
	}
	
	
	public ChoiceNation()
	{
		bg=Toolkit.getDefaultToolkit().getImage("img\\나라선택-배경.jpg"); //그림가져오기
		notice=Toolkit.getDefaultToolkit().getImage("img\\나라선택-설명.jpg");
		
		setLayout(null);
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
		
		//버튼을 누르면 위 촉 오 (0 1 2) 값이 들어감 => 인게임에서 빙고체크 시 각 나라아이콘 사용
		nation0.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=0;
				jangsu();
			}
			
		});

		nation1.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=1;
				jangsu();
			}
		});

		nation2.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=2;
				jangsu();
			}
		});
		
		//장수얼굴 버튼 이벤트 처리
	
		
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
