package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GameProcess extends JPanel{
	static int[][] p1Board= new int[3][25];	//플레이어1 빙고판
	static int[][] p2Board= new int[3][25];	//플레이어2 빙고판
	
	static int[] numArr1=new int[75]; //랜덤으로 섞을 숫자 생성
	static int[] numArr2=new int[75]; //플레이어1,2
	
	static int chosenBingoNumber; //선택된 빙고번호
	static boolean[][] bingo=new boolean[3][25]; //빙고처리
	static int[] numOfBingo=new int[3]; //각 판 별로 카운트처리

	//스킬
	static int attackSkill = 0;
	static int attackFinish = 0; //필살기는 3이 되면 사용, 사용시 -3
	static int defenseSkill = 0;
	static int defenseFinish = 0;
	static int strategySkill = 0;
	static int strategyFinish = 0;
	
	static boolean bAttackSkill = false; //아이콘을 클릭했을때 트루가 되고 사용시 false 
	static boolean bAttackFinish = false;
	static boolean bDefenseSkill = false;
	static boolean bDefenseFinish = false;
	static boolean bStrategySkill = false;
	static boolean bStrategyFinish = false;
	
	static boolean playerTurn=false; //true=player1, false=player2 

	static int chosenNation=0;//위:0,촉:1,오:2
	static void rand()//중복되지 않는 랜덤 숫자배열 두개 만들기
	{		
		int su=0; //난수 발생시 저장할 변수
		boolean bDash=false; //중복여부 확인
		for(int i=0; i<75; i++)
		{
			bDash=true;
			while(bDash) // 난수발생, 중복 학인
			{
				su=(int)(Math.random()*75)+1; //판 크기+10개(상대가 못맞출 수도 있게)의 난수 입력
				bDash=false;
				for(int j=0; j<i; j++)
				{
					if(numArr1[j]==su)
					{
						bDash=true; //중복이 있으면 멈추고 while문을 다시 수행(랜덤값을 다시 줌)
						break;
					}
				}
			}
			numArr1[i]=su;
		}
		for(int i=0; i<75; i++)
		{
			bDash=true;
			while(bDash) // 난수발생, 중복 학인
			{
				su=(int)(Math.random()*75)+1; //판 크기+10개(상대가 못맞출 수도 있게)의 난수 입력
				bDash=false;
				for(int j=0; j<i; j++)
				{
					if(numArr2[j]==su)
					{
						bDash=true; //중복이 있으면 멈추고 while문을 다시 수행(랜덤값을 다시 줌)
						break;
					}
				}
			}
			numArr2[i]=su;
		}
	}

	static void insertBingoNumber1()//플레이어1 판에 숫자 배치
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				p1Board[i][j]=numArr1[(i*25)+j];
			}
		}
	}

	static void insertBingoNumber2()//플레이어2 판에 숫자 배치
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				p2Board[i][j]=numArr2[(i*25)+j];
			}
		}
	}
	
	static void coinFlip() //동전던지기
	{
		int coin=(int)(Math.random()*2);
		if(coin==0)
			playerTurn=true; 
		else
			playerTurn=false;
		
		//if(playerTurn==true)
			//이미지=
		//else
			//이미지=
	}

	static void choiceBingoNumber() //클릭으로 숫자 선택
	{
		//chosenBingoNumber
	}

	static void choiceNation()
	{
		//나라에 따른 빙고체크 이미지 불러오기
		if(ChoiceNation.chosenNation==0)//위
		{
			ImageIcon m=new ImageIcon("c:\\bingo\\빙고체크-위.png");
		}
		else if(ChoiceNation.chosenNation==1)//촉
		{
			
		}
		else//오
		{
			
		}
	}
	
	static void bingoCount() //빙고 카운트
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				if(p1Board[i][j]==chosenBingoNumber)
				{
					bingo[i][j]=true;
				}
				if(p2Board[i][j]==chosenBingoNumber)
				{
					bingo[i][j]=true;
				}
			}
		}
	}

	static void lineCount() //라인 카운트
	{
		//빙고 줄 세기
		for(int i=0; i<5; i++)
		{
			//첫번째판
			//가로
			if(bingo[0][i*5]==true&&bingo[0][(i*5)+1]==true
					&&bingo[0][(i*5)+2]==true&&bingo[0][(i*5)+3]==true
					&&bingo[0][(i*5)+4]==true)
			{
				numOfBingo[0]++;
				attackSkill++; //스킬 하나 추가
				attackFinish++;
			}
			//세로
			if(bingo[0][i]==true&&bingo[0][i+5]==true
					&&bingo[0][i+10]==true&&bingo[0][i+15]==true
					&&bingo[0][i+20]==true)
			{
				numOfBingo[0]++;
				attackSkill++;
				attackFinish++;
			}
			//대각선
			if(bingo[0][0]==true&&bingo[0][6]==true
					&&bingo[0][12]==true&&bingo[0][18]==true
					&&bingo[0][24]==true
					||bingo[0][4]==true&&bingo[0][8]==true
					&&bingo[0][12]==true&&bingo[0][16]==true
					&&bingo[0][20]==true)
			{
				numOfBingo[0]++;
				attackSkill++;
				attackFinish++;
			}
			
			//두번째판
			//가로
			if(bingo[1][i*5]==true&&bingo[1][(i*5)+1]==true
					&&bingo[1][(i*5)+2]==true&&bingo[1][(i*5)+3]==true
					&&bingo[1][(i*5)+4]==true)
			{
				numOfBingo[1]++;
				defenseSkill++;
				defenseFinish++;
			}
			//세로
			if(bingo[1][i]==true&&bingo[1][i+5]==true
					&&bingo[1][i+10]==true&&bingo[1][i+15]==true
					&&bingo[1][i+20]==true)
			{
				numOfBingo[1]++;
				defenseSkill++;
				defenseFinish++;
			}
			//대각선
			if(bingo[1][0]==true&&bingo[1][6]==true
					&&bingo[1][12]==true&&bingo[1][18]==true
					&&bingo[1][24]==true
					||bingo[1][4]==true&&bingo[1][8]==true
					&&bingo[1][12]==true&&bingo[1][16]==true
					&&bingo[1][20]==true)
			{
				numOfBingo[1]++;
				defenseSkill++;
				defenseFinish++;
			}
			
			//세번째판
			//가로
			if(bingo[2][i*5]==true&&bingo[2][(i*5)+1]==true
					&&bingo[2][(i*5)+2]==true&&bingo[2][(i*5)+3]==true
					&&bingo[2][(i*5)+4]==true)
			{
				numOfBingo[2]++;
				strategySkill++;
				strategyFinish++;
			}
			//세로
			if(bingo[2][i]==true&&bingo[2][i+5]==true
					&&bingo[2][i+10]==true&&bingo[2][i+15]==true
					&&bingo[2][i+20]==true)
			{
				numOfBingo[2]++;
				strategySkill++;
				strategyFinish++;
			}
			//대각선
			if(bingo[2][0]==true&&bingo[2][6]==true
					&&bingo[2][12]==true&&bingo[2][18]==true
					&&bingo[2][24]==true
					||bingo[2][4]==true&&bingo[2][8]==true
					&&bingo[2][12]==true&&bingo[2][16]==true
					&&bingo[2][20]==true)
			{
				numOfBingo[2]++;
				strategySkill++;
				strategyFinish++;
			}
		}
		
		if(numOfBingo[0]>=5||numOfBingo[1]>=5||numOfBingo[2]>=5)
		{
			// 게임 끝
			// 재도전
		}
	}

	static void getSkill() //스킬 갯수 추가
	{
		if(attackSkill>0)
		{
			//이미지 추가
		}
		if(defenseSkill>0)
		{
			
		}
		if(strategySkill>0)
		{
			
		}
		if(attackFinish>3)
		{
			
		}
		if(defenseFinish>3)
		{
			
		}
		if(strategyFinish>3)
		{
			
		}
	}

	static void clickSkill_Icon() //스킬 클릭
	{
		//스킬 사용버 메시지 띄우기
	}
	
	static void useSkill() //스킬사용
	{
		//상대판에만 사용 가능
		
		attackSkill--;
		defenseSkill--;
		strategySkill--;
		attackFinish-=3;
		defenseFinish-=3;
		strategyFinish-=3;
	}
	
	static void process()
	{
		rand();
		insertBingoNumber1();
		rand();
		insertBingoNumber2();
		////////////////////////
	}

	
	

}
