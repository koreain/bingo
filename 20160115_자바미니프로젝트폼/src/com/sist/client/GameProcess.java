package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GameProcess extends JPanel{
	static int[][] p1Board= new int[3][25];	//�÷��̾�1 ������
	static int[][] p2Board= new int[3][25];	//�÷��̾�2 ������
	
	static int[] numArr1=new int[75]; //�������� ���� ���� ����
	static int[] numArr2=new int[75]; //�÷��̾�1,2
	
	static int chosenBingoNumber; //���õ� �����ȣ
	static boolean[][] bingo=new boolean[3][25]; //����ó��
	static int[] numOfBingo=new int[3]; //�� �� ���� ī��Ʈó��

	//��ų
	static int attackSkill = 0;
	static int attackFinish = 0; //�ʻ��� 3�� �Ǹ� ���, ���� -3
	static int defenseSkill = 0;
	static int defenseFinish = 0;
	static int strategySkill = 0;
	static int strategyFinish = 0;
	
	static boolean bAttackSkill = false; //�������� Ŭ�������� Ʈ�簡 �ǰ� ���� false 
	static boolean bAttackFinish = false;
	static boolean bDefenseSkill = false;
	static boolean bDefenseFinish = false;
	static boolean bStrategySkill = false;
	static boolean bStrategyFinish = false;
	
	static boolean playerTurn=false; //true=player1, false=player2 

	static int chosenNation=0;//��:0,��:1,��:2
	static void rand()//�ߺ����� �ʴ� ���� ���ڹ迭 �ΰ� �����
	{		
		int su=0; //���� �߻��� ������ ����
		boolean bDash=false; //�ߺ����� Ȯ��
		for(int i=0; i<75; i++)
		{
			bDash=true;
			while(bDash) // �����߻�, �ߺ� ����
			{
				su=(int)(Math.random()*75)+1; //�� ũ��+10��(��밡 ������ ���� �ְ�)�� ���� �Է�
				bDash=false;
				for(int j=0; j<i; j++)
				{
					if(numArr1[j]==su)
					{
						bDash=true; //�ߺ��� ������ ���߰� while���� �ٽ� ����(�������� �ٽ� ��)
						break;
					}
				}
			}
			numArr1[i]=su;
		}
		for(int i=0; i<75; i++)
		{
			bDash=true;
			while(bDash) // �����߻�, �ߺ� ����
			{
				su=(int)(Math.random()*75)+1; //�� ũ��+10��(��밡 ������ ���� �ְ�)�� ���� �Է�
				bDash=false;
				for(int j=0; j<i; j++)
				{
					if(numArr2[j]==su)
					{
						bDash=true; //�ߺ��� ������ ���߰� while���� �ٽ� ����(�������� �ٽ� ��)
						break;
					}
				}
			}
			numArr2[i]=su;
		}
	}

	static void insertBingoNumber1()//�÷��̾�1 �ǿ� ���� ��ġ
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				p1Board[i][j]=numArr1[(i*25)+j];
			}
		}
	}

	static void insertBingoNumber2()//�÷��̾�2 �ǿ� ���� ��ġ
	{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				p2Board[i][j]=numArr2[(i*25)+j];
			}
		}
	}
	
	static void coinFlip() //����������
	{
		int coin=(int)(Math.random()*2);
		if(coin==0)
			playerTurn=true; 
		else
			playerTurn=false;
		
		//if(playerTurn==true)
			//�̹���=
		//else
			//�̹���=
	}

	static void choiceBingoNumber() //Ŭ������ ���� ����
	{
		//chosenBingoNumber
	}

	static void choiceNation()
	{
		//���� ���� ����üũ �̹��� �ҷ�����
		if(ChoiceNation.chosenNation==0)//��
		{
			ImageIcon m=new ImageIcon("c:\\bingo\\����üũ-��.png");
		}
		else if(ChoiceNation.chosenNation==1)//��
		{
			
		}
		else//��
		{
			
		}
	}
	
	static void bingoCount() //���� ī��Ʈ
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

	static void lineCount() //���� ī��Ʈ
	{
		//���� �� ����
		for(int i=0; i<5; i++)
		{
			//ù��°��
			//����
			if(bingo[0][i*5]==true&&bingo[0][(i*5)+1]==true
					&&bingo[0][(i*5)+2]==true&&bingo[0][(i*5)+3]==true
					&&bingo[0][(i*5)+4]==true)
			{
				numOfBingo[0]++;
				attackSkill++; //��ų �ϳ� �߰�
				attackFinish++;
			}
			//����
			if(bingo[0][i]==true&&bingo[0][i+5]==true
					&&bingo[0][i+10]==true&&bingo[0][i+15]==true
					&&bingo[0][i+20]==true)
			{
				numOfBingo[0]++;
				attackSkill++;
				attackFinish++;
			}
			//�밢��
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
			
			//�ι�°��
			//����
			if(bingo[1][i*5]==true&&bingo[1][(i*5)+1]==true
					&&bingo[1][(i*5)+2]==true&&bingo[1][(i*5)+3]==true
					&&bingo[1][(i*5)+4]==true)
			{
				numOfBingo[1]++;
				defenseSkill++;
				defenseFinish++;
			}
			//����
			if(bingo[1][i]==true&&bingo[1][i+5]==true
					&&bingo[1][i+10]==true&&bingo[1][i+15]==true
					&&bingo[1][i+20]==true)
			{
				numOfBingo[1]++;
				defenseSkill++;
				defenseFinish++;
			}
			//�밢��
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
			
			//����°��
			//����
			if(bingo[2][i*5]==true&&bingo[2][(i*5)+1]==true
					&&bingo[2][(i*5)+2]==true&&bingo[2][(i*5)+3]==true
					&&bingo[2][(i*5)+4]==true)
			{
				numOfBingo[2]++;
				strategySkill++;
				strategyFinish++;
			}
			//����
			if(bingo[2][i]==true&&bingo[2][i+5]==true
					&&bingo[2][i+10]==true&&bingo[2][i+15]==true
					&&bingo[2][i+20]==true)
			{
				numOfBingo[2]++;
				strategySkill++;
				strategyFinish++;
			}
			//�밢��
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
			// ���� ��
			// �絵��
		}
	}

	static void getSkill() //��ų ���� �߰�
	{
		if(attackSkill>0)
		{
			//�̹��� �߰�
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

	static void clickSkill_Icon() //��ų Ŭ��
	{
		//��ų ���� �޽��� ����
	}
	
	static void useSkill() //��ų���
	{
		//����ǿ��� ��� ����
		
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
