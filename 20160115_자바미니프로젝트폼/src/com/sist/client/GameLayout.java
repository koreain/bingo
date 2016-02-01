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
	Image pan1; //빙고틀 플레이어1
	Image pan2; //빙고틀 플레이어2
	
	
	//빙고판 
	static JButton[][] a1=new JButton[3][25]; //내 판
	static JButton[][] a2=new JButton[3][25]; //상대판
	
	//플레이어 1,2  장기판별 게이지 
	static JProgressBar[][] gauge=new JProgressBar[2][3];
	//플레이어 1,2 장기판별 궁극기 활성 안내버튼
	static JButton[][] fury=new JButton[2][3];
	
	//플레이어 1,2 장기판별 빙고획득점수 확인패널
	static JButton[][] bingoScore=new JButton[6][5];
	static JPanel[] bingoScorePan=new JPanel[6];
	static ImageIcon bingo1=new ImageIcon("img\\빙고-한줄.png");
	static ImageIcon bingo2=new ImageIcon("img\\빙고-한줄완성.png");
	
	
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
	
	static JPanel j1=new JPanel(); //플레이어2
	static JPanel j2=new JPanel(); //플레이어1
	
	
	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	static ImageIcon bcIcon0=new ImageIcon("img\\빙고체크-위.png");
	static ImageIcon bcIcon1=new ImageIcon("img\\빙고체크-촉.png");
	static ImageIcon bcIcon2=new ImageIcon("img\\빙고체크-오.png");
	
	
	public void RanButton(int a, int b, int c, JPanel d, JPanel e)
	{
		ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[a]+".png");
		a1[b][a-c]= new JButton(m);
		d.add(a1[b][a-c]);
		// 버튼에 아이콘 사이즈 맞추기 
		a1[b][a-c].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
		a1[b][a-c].setBorderPainted(false); //버튼 경계선 제거
		a1[b][a-c].setContentAreaFilled(false); //선택했던 버튼 표시 제거
		a1[b][a-c].setFocusPainted(false); //버튼영역 배경 제거
		ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[a]+".png");
		a2[b][a-c]= new JButton(m1);
		e.add(a2[b][a-c]);
		// 버튼에 아이콘 사이즈 맞추기 
		a2[b][a-c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a-c].setBorderPainted(false); //버튼 경계선 제거
		a2[b][a-c].setContentAreaFilled(false); //선택했던 버튼 표시 제거
		a2[b][a-c].setFocusPainted(false); //버튼영역 배경 제거
	}
	
	
	public void Rand()
	{
		GameProcess.rand(); // GameProcess 클래스 안쪽으로 들어가서~ 랜덤메소드 호출
		
		for(int i=0; i<75;i++)
		{
		  if(i<25)	
		  {
			  RanButton(i, 0, 0, pp1, p1);
		  }
		  else if((i>=25)&&(i<50))
		  {
			  RanButton(i, 1, 25, pp2, p2);
		  }
		  else//(i>=50)
		  {
			RanButton(i, 2, 50, pp3, p3);
		  }
		}
	}

	GameLayout()
	{	
		Color RED=new Color(255,0,0);
		Color GREEN=new Color(0,147,0);
		Color PURPLE=new Color(95,0,255); //BLUE 명칭을 보라색 PURPLE로 변경-HJ
		Color[] color={RED,GREEN,PURPLE}; //BLUE 명칭을 보라색 PURPLE로 변경-HJ
		int[] xVal={130,413,688};
		int[] xVal2={245,528,803};
		
		String[] goong={"img\\스킬아이콘-공격필살기.png",
						"img\\스킬아이콘-방어필살기.png",
						"img\\스킬아이콘-책략필살기.png"};
		
		for(int i=0; i<6; i++)
		{	bingoScorePan[i]=new JPanel();
			add(bingoScorePan[i]);
			bingoScorePan[i].setOpaque(false);
			for(int j=0; j<5; j++)
			{
				bingoScore[i][j]=new JButton(bingo1);
				bingoScorePan[i].add(bingoScore[i][j]);
				bingoScore[i][j].setPreferredSize(new Dimension(bingo1.getIconWidth(), bingo1.getIconHeight()));
				bingoScorePan[i].setBounds(xVal[i%3]-8, (i/3)*505+40, 180, 60);
				bingoScore[i][j].setBorderPainted(false);
				bingoScore[i][j].setContentAreaFilled(false); 
				bingoScore[i][j].setFocusPainted(false);
			}
		}
		
		
		// 필살기 버튼 생성 (초기: setVisible(false), 100퍼센트 이후: setVisible(true)
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				fury[i][j]=new JButton(new ImageIcon(goong[j]));
				add(fury[i][j]);
				fury[i][j].setBounds(xVal2[j], i*508+74, 60, 60);
				fury[i][j].setBorderPainted(false); //버튼 경계선 제거
				fury[i][j].setContentAreaFilled(false); //선택했던 버튼 표시 제거
				fury[i][j].setFocusPainted(false); //버튼영역 배경 제거
				fury[i][j].setEnabled(false);
			}
		}
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
				gauge[i][j].setBounds(xVal[j], i*507+85, 115, 40);
			}
			
		}
		
		// 배경화면
		pan2=Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		pan1=Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		bg=Toolkit.getDefaultToolkit().getImage("img\\인게임배경.jpg");
		vs=Toolkit.getDefaultToolkit().getImage("img\\vs.png");
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
		j2.setBounds(35, 30, 836, 100);
		j1.setBounds(35, 535, 836, 100);
		p.setBounds(20,130,900,275);
		pp.setBounds(20, 635, 900, 275);
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
		
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				a1[i][j].setCursor(cur); //버튼에 마우스를 올리면 커서 모양이 손으로 바뀜
				a2[i][j].setCursor(cur);
				
				a1[i][j].addActionListener(this); //버튼 액션 추가
				a2[i][j].addActionListener(this);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343, 423, vs.getWidth(this),80,this);
		g.drawImage(pan1, 4, 492, 895, 452, this);
		g.drawImage(pan2, 4, -10, 895, 446, this);
	}
	
	//버튼 누르기 빙고체크!!!!!!!!
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//빙고 체크(체크된 빙고가 아닐 때+스킬아이템을 클릭하지 않았을 때)
		if(GameProcess.bAttackSkill1==false&&GameProcess.bAttackFinish1==false&&GameProcess.bDefenseSkill1==false
		&&GameProcess.bDefenseFinish1==false&&GameProcess.bStrategySkill1==false&&GameProcess.bStrategyFinish1==false)
		{
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==0)//진영선택 : 위vs위
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon0); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==1)//진영선택 : 위vs촉
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon0); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==2)//진영선택 : 위vs오
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon0); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==0)//진영선택 : 촉vs위
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon1); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==1)//진영선택 : 촉vs촉
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon1); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==2)//진영선택 : 촉vs오
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon1); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==0)//진영선택 : 오vs위
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon2); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==1)//진영선택 : 오vs촉
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon2); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==2)//진영선택 : 오vs오
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
				            {
				               a1[i][j].setIcon(bcIcon2); //그 자리 버튼의 아이콘을 바꿔주고
				               GameProcess.bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
		}
	}
}
