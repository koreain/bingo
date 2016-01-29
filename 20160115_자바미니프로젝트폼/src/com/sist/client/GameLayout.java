package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel implements ActionListener{
	
	//배경화면
	Image bg; //추상클래스 abstract!! 단독으로 메모리 할당을 못한다.
	Image vs; //가운데  vs 텍스트
	
	//빙고판 
	static JButton[][] a4=new JButton[5][5]; //상대 판
	static JButton[][] a5=new JButton[5][5];
	static JButton[][] a6=new JButton[5][5];
	
	static JButton[][] a1=new JButton[5][5]; //내 판
	static JButton[][] a2=new JButton[5][5];
	static JButton[][] a3=new JButton[5][5];
		
	//플레이어 1,2  장기판별 아이디
	//static JLabel eft1,eft2,eft3,eft4,eft5,eft6;
	//플레이어 1,2  장기판별 게이지
	static JProgressBar[][] gauge=new JProgressBar[2][3];;
	//플레이어 2 빙고판 레이아웃 (상대판)
	static JPanel p=new JPanel();
	static JPanel p1=new JPanel();
	static JPanel p2=new JPanel();
	static JPanel p3=new JPanel();
	
	// 플레이어 1 빙고판 레이아웃 (내판)
	static JPanel pp=new JPanel();
	static JPanel pp1=new JPanel();
	static JPanel pp2=new JPanel();
	static JPanel pp3=new JPanel();
	
	// 장수 캐릭터 창
	
	static JPanel j1=new JPanel();
	static JPanel j2=new JPanel();
	
	
	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	ImageIcon bcIcon0=new ImageIcon("c:\\bingo\\빙고체크-위.png");
	ImageIcon bcIcon1=new ImageIcon("c:\\bingo\\빙고체크-촉.png");
	ImageIcon bcIcon2=new ImageIcon("c:\\bingo\\빙고체크-오.png");
	
	public void Rand()
	{
		GameProcess.rand(); // GameProcess 클래스 안쪽으로 들어가서~ 랜덤메소드 호출
		
		for(int i=0; i<75;i++)
		{
		  if(i<25)	
		  {
			// 랜덤으로 생성된 숫자numArr1에 따른 숫자아이콘 버튼에 추가 
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a1[i/5][i%5]= new JButton(m);
			pp1.add(a1[i/5][i%5]);
			// 버튼에 아이콘 사이즈 맞추기 
			a1[i/5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[i/5][i%5].setBorderPainted(false); //버튼 경계선 제거
			a1[i/5][i%5].setContentAreaFilled(false); //선택했던 버튼 표시 제거
			a1[i/5][i%5].setFocusPainted(false); //버튼영역 배경 제거
			ImageIcon m1=new ImageIcon("c:\\bingo\\"+GameProcess.numArr2[i]+".png");
			a4[i/5][i%5]= new JButton(m1);
			p1.add(a4[i/5][i%5]);
			// 버튼에 아이콘 사이즈 맞추기 
			a4[i/5][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a4[i/5][i%5].setBorderPainted(false); //버튼 경계선 제거
			a4[i/5][i%5].setContentAreaFilled(false); //선택했던 버튼 표시 제거
			a4[i/5][i%5].setFocusPainted(false); //버튼영역 배경 제거
		  }
		  else if((i>=25)&&(i<50))
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a2[(i/5)-5][i%5]= new JButton(m);
			pp2.add(a2[(i/5)-5][i%5]);
			a2[(i/5)-5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a2[(i/5)-5][i%5].setBorderPainted(false);
			a2[(i/5)-5][i%5].setContentAreaFilled(false); 
			a2[(i/5)-5][i%5].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("c:\\bingo\\"+GameProcess.numArr2[i]+".png");
			a5[(i/5)-5][i%5]= new JButton(m1);
			p2.add(a5[(i/5)-5][i%5]);
			a5[(i/5)-5][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a5[(i/5)-5][i%5].setBorderPainted(false);
			a5[(i/5)-5][i%5].setContentAreaFilled(false); 
			a5[(i/5)-5][i%5].setFocusPainted(false);
		  }
		  else//(i>=50)
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a3[(i/5)-10][i%5]= new JButton(m);
			pp3.add(a3[(i/5)-10][i%5]);  
			a3[(i/5)-10][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a3[(i/5)-10][i%5].setBorderPainted(false);
			a3[(i/5)-10][i%5].setContentAreaFilled(false); 
			a3[(i/5)-10][i%5].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("c:\\bingo\\"+GameProcess.numArr2[i]+".png");
			a6[(i/5)-10][i%5]= new JButton(m1);
			p3.add(a6[(i/5)-10][i%5]);  
			a6[(i/5)-10][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a6[(i/5)-10][i%5].setBorderPainted(false);
			a6[(i/5)-10][i%5].setContentAreaFilled(false); 
			a6[(i/5)-10][i%5].setFocusPainted(false);
		  }
		}
	}

	GameLayout()
	{	
		Color RED=new Color(255,0,0);
		Color GREEN=new Color(0,147,0);
		Color BLUE=new Color(95,0,255);
		Color[] color={RED,GREEN,BLUE};
		int[] xVal={115,398,673};
		
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				gauge[i][j]=new JProgressBar();
				add(gauge[i][j]);
				gauge[i][j].setMinimum(0); //진행바 최소값 설정
				gauge[i][j].setMaximum(100); //진행바 최대값 설정
				gauge[i][j].setStringPainted(true); //진행사항 퍼센티지로 보여주기 설정
				gauge[i][j].setForeground(color[j]); //진행바 색설정
				gauge[i][j].setBounds(xVal[j], i*465+75, 165, 50);
			}
			
		}
		
		// 장수효과 라벨 생성
		JLabel eft1 = new JLabel();
		JLabel eft2 = new JLabel();
		JLabel eft3 = new JLabel();
		JLabel eft4 = new JLabel();
		JLabel eft5 = new JLabel();
		JLabel eft6 = new JLabel();
		add(eft1); add(eft2); add(eft3);
		add(eft4); add(eft5); add(eft6);
		Font f1 = new Font("궁서",Font.BOLD,18);
		eft1.setText("장군효과: 투  신");
		eft2.setText("장군효과: 적진기습");
		eft3.setText("장군효과: 진영파괴");
		eft4.setText("장군효과: 투  신");
		eft5.setText("장군효과: 적진기습");
		eft6.setText("장군효과: 진영파괴");
		eft1.setFont(f1);
		eft2.setFont(f1);
		eft3.setFont(f1);
		eft4.setFont(f1);
		eft5.setFont(f1);
		eft6.setFont(f1);
		eft1.setBounds(120, 30, 188, 60); eft2.setBounds(398, 30, 188, 60); eft3.setBounds(673, 30, 188, 60);
		eft4.setBounds(120, 495, 188, 60); eft5.setBounds(398, 495, 188, 60); eft6.setBounds(673, 495, 188, 60);
		// 배경화면
		bg=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\인게임배경.jpg");
		vs=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\vs.png");
		setLayout(null);
		FlowLayout k=new FlowLayout(0); // 0: 왼쪽정렬, 1:가운데정렬, 2:오른쪽정렬
		k.setHgap(20);
		p.setLayout(k);
		pp.setLayout(k);
		add(p);
		add(pp);
		p.add(p1); 		p.add(p2); 		p.add(p3);
		pp.add(pp1);	pp.add(pp2);	pp.add(pp3);
		FlowLayout j0=new FlowLayout(FlowLayout.RIGHT,185,0);
		add(j1);
		j1.setOpaque(false);
		j1.setLayout(j0);
		add(j2);
		j2.setLayout(j0);
		j2.setOpaque(false);
		j2.setBackground(null);
		j1.setBounds(20, 30, 836, 100);
		j2.setBounds(20, 495, 836, 100);
		p.setBounds(5,130,900,275);
		pp.setBounds(5, 595, 900, 275);
		p1.setLayout(new GridLayout(5,5,0,0)); //5by5행렬모양, 좌,우갭은 0
		p2.setLayout(new GridLayout(5,5,0,0));
		p3.setLayout(new GridLayout(5,5,0,0));
		pp1.setLayout(new GridLayout(5,5,0,0));
		pp2.setLayout(new GridLayout(5,5,0,0));
		pp3.setLayout(new GridLayout(5,5,0,0));
		p1.setSize(265,265);
		p2.setSize(265,265);
		p3.setSize(265,265);
		pp1.setSize(265,265);
		pp2.setSize(265,265);
		pp3.setSize(265,265);
				
		Rand();
		
		p.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		pp.setOpaque(false);
		pp1.setOpaque(false);
		pp2.setOpaque(false);
		pp3.setOpaque(false);
		
		setSize(1200,970);
		setVisible(true);
		
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				a1[i][j].setCursor(cur); //버튼에 마우스를 올리면 커서 모양이 손으로 바뀜
				a2[i][j].setCursor(cur);
				a3[i][j].setCursor(cur);
				a4[i][j].setCursor(cur);
				a5[i][j].setCursor(cur);
				a6[i][j].setCursor(cur);
				
				a1[i][j].addActionListener(this); //버튼 액션 추가
				a2[i][j].addActionListener(this);
				a3[i][j].addActionListener(this);
				a4[i][j].addActionListener(this); 
				a5[i][j].addActionListener(this);
				a6[i][j].addActionListener(this);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 500, 400, vs.getWidth(this),vs.getHeight(this),this);
	}
	
	//버튼 누르기 빙고체크!!!!!!!!
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//빙고 체크(체크된 빙고가 아닐 때+스킬아이템을 클릭하지 않았을 때)
		if(GameProcess.bAttackSkill1==false&&GameProcess.bAttackFinish1==false&&GameProcess.bDefenseSkill1==false
		&&GameProcess.bDefenseFinish1==false&&GameProcess.bStrategySkill1==false&&GameProcess.bStrategyFinish1==false)
		{
			if(ChoiceNation.chosenNation1==0)//진영선택 : 위를 선택했을 때
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon0);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon0);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon0);
							GameProcess.lineCount();
						}
					}
				}
			}
			
			if(ChoiceNation.chosenNation1==1)//촉
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon1);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon1);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon1);
							GameProcess.lineCount();
						}
					}
				}
			}
			
			if(ChoiceNation.chosenNation1==2)//오
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon2);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon2);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon2);
							GameProcess.lineCount();
						}
					}
				}	
			}
		}
	}
}
