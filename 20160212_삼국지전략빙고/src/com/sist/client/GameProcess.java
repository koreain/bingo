package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
public class GameProcess{
	static int[][] p1Board= new int[3][25];   //�÷��̾�1 ������ ���� ����
	static int[][] p2Board= new int[3][25];   //�÷��̾�2 ������ ���� ����
	
	//�������� ���� ���� ����
	static int[] numArr1=new int[75]; //�÷��̾�1  
	static int[] numArr2=new int[75]; //�÷��̾�2
	
	static int chosenBingoNumber; //���õ� �����ȣ
	static boolean[][] bingo1=new boolean[3][25]; //����ó��
	static boolean[][] bingo2=new boolean[3][25];
	static int[] numOfBingo1=new int[3]; //�� �� ���� ī��Ʈó��
	static int[] numOfBingo2=new int[3];
	
	//numOfBing+usingAttackSkill�� �׻� ���, ��ų�� ����ϸ� -1, �ʻ��� ���� -3
	static int usingAttackSkill1=0;
	static int usingDefenseSkill1=0;
	static int usingStrategySkill1=0;
	//�÷��̾�2��ų
	static int usingAttackSkill2=0;
	static int usingDefenseSkill2=0;
	static int usingStrategySkill2=0;
	
	static boolean playerTurn=true; //true=player1, false=player2
   
	static int bingoCheckChance1=1, bingoCheckChance2=1;//�� �Ͽ� ���� üũ�� �� �� �ִ� Ƚ��
	static int skillChance1=1, skillChance2=1;
	
	static int[] gaugeScore={0,33,66,100};//��������
	
	static int coinA=0;
	static int coinB=0;
	
	static boolean playerWon=false;
	static void coinRand() //�÷��̾����� ���ϴ� ���� �߻�
	{
		coinB=(int)(Math.random()*10)+5;
		if(coinA%2==0)
			playerTurn=true;
		else
			playerTurn=true;//false
	}
	static void rand()//�ߺ����� �ʴ� ���� ���ڹ迭 �ΰ� �����
	{      
		int su=0; //���� �߻��� ������ ����
		boolean bDash=false; //�ߺ����� Ȯ��
		for(int i=0; i<75; i++)
		{
			bDash=true;
			while(bDash) // �����߻�, �ߺ� ����
			{
				su=(int)(Math.random()*75)+1;
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
		insertBingoNumber1();//�ǿ� ���� ��ġ
		insertBingoNumber2();
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

   //����üũ
   static void bingoCheck(int i, int j, int[][] board1, int[][]board2,
		   boolean[][] b1, boolean[][] b2, JButton[][] btn1, JButton[][] btn2,
		   ImageIcon icon1, ImageIcon icon2)
   {
	   chosenBingoNumber=board1[i][j];
	   if(board1[i][j]==chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
	   {
          btn1[i][j].setIcon(icon1); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
          b1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
       }
	   for(int k=0; k<3; k++)
	   {
		   for(int l=0; l<25; l++)
		   {
			   //���͵� ����üũ
			   if(board2[k][l]==chosenBingoNumber)
			   {
				   if(GameLayout.panCheck1[k][l]==false)
				   {
					   btn2[k][l].setIcon(icon2);
					   b2[k][l]=true;
				   }
			   }
		   }
	   }
	   lineCount();
   }
   
   static void gaugeCtrl(int lineNum, int a, int b, String str) //������ ��� �� �ñر� ��ư Ȱ��ȭ �޼ҵ�
   {
     if(lineNum>=3)
     {
        if(GameLayout.goongUsable1[b]==true&&a==1)
         {   GameLayout.gauge[a][b].setValue(gaugeScore[3]);
               GameLayout.gauge[a][b].setString(str);
               GameLayout.gauge[a][b].setFont(new Font("�ü�ü",Font.BOLD,20));
               GameLayout.gauge[a][b].setVisible(true);
         }
         else if(GameLayout.goongUsable2[b]==true&&a==0)
         {
            GameLayout.gauge[a][b].setValue(gaugeScore[3]);
               GameLayout.gauge[a][b].setString(str);
               GameLayout.gauge[a][b].setFont(new Font("�ü�ü",Font.BOLD,20));
               GameLayout.gauge[a][b].setVisible(true);
         }
     }
     else
        GameLayout.gauge[a][b].setValue(gaugeScore[lineNum]);
   }
 
   static void goongCtrl(int lineNum, int a, int b)   //���ٿϼ�=>�ʻ�� ��� ���� 
   {
	   if(lineNum>=3)  
		   //if()�ѹ�������쿡�� ��Ȱ��ȭ
		   //GameLayout.fury[a][b].setEnabled(false);
		   //else() <-- �ѹ��� �Ƚ������
		   GameLayout.fury[a][b].setEnabled(true);
   }
	  
   static void bingoIcon(int lineNo,int a) //�ϼ��� �� ī��Ʈ �ټ���,int a�� �Ǽ��� (1~6);
   {
	  for(int i=0;i<lineNo;i++)
	   {
		  if(lineNo>0&&lineNo<6)
			  GameLayout.bingoScore[a-1][i].setIcon(GameLayout.bingo2);//�ϳ� �ϼ��Ǹ� �����ܸ�� �����ϴ� Į�� �ٲ�
	   }
   }
   
   //�����ı�(JinYoung PaGoe)
   static void jypg(int panRow, int panCol)
   {
	   ImageIcon[] dft= {GameLayout.bcIcon0,GameLayout.bcIcon1,GameLayout.bcIcon2}; //���˿� �����̹���
	   ImageIcon myBingo=dft[ChoiceNation.chosenNation1];    //���� ������ ���� �´� ����üũ �̹��� ������
	   ImageIcon yourBingo=dft[ChoiceNation.chosenNation2];	 //��밡 ������ ���� �´� ����üũ �̹��� ������
	   if(panRow==1)//���� ���ϰ�� (panRow�� 0:�����, panRow�� 1:����)
	   {
		  for(int i=panCol*25; i<24+(panCol*25); i++) //panCol���� 0�̸� ������, 1�̸� �����, 2�̸� ������
		  {
			ImageIcon originNum=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png"); //���� ���� �̹���
			int newArr=(int)(Math.random()*25+(panCol*25));
			ImageIcon newImgNum=new ImageIcon("img\\"+GameProcess.numArr1[newArr]+".png"); //�������� ����� ���� �̹��� 
			//��Ȯ�� �����ϸ� �������� ���� �迭��ȣ�� ���� �ش��ϴ� �̹����� �����ϴ� ��
			
			//���� ������ڰ� ��ȯ
			int temp=numArr1[i]; //�ӽ������� ���� �迭��ȣ�� �������� temp������ ����
			numArr1[i]=numArr1[newArr]; //�������� ���� �迭��ȣ�� �������� ���� �迭��ȣ�� �����ϰ�
			numArr1[newArr]=temp; //�ӽ������� ������ ���� �迭��ȣ�� �������� �������� ���� �迭��ȣ�� ���������� ����
			p1Board[panCol][i-(25*panCol)]=numArr1[i]; //�ٲ���� ���� �迭��ȣ�� �������� p1Board(�������� ������ 5by5 �迭�� �����ִ�)�� �����迭��ġ�� �������ش� >> �������� ������ ����
			p1Board[panCol][newArr-(25*panCol)]=numArr1[newArr]; //���� �迭��ȣ�� �������� p1Board(�������� ������ 5by5 �迭�� �����ִ�)�� �����迭��ġ�� �������ش� >> �������� ������ ���� 
			
			if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&				  //���� �迭��ȣ�� ��ư�� ����üũ ���� �ʾҰ�,
			   GameProcess.bingo1[panCol][newArr-(25*panCol)]==false) 			  //���ο� �迭��ȣ�� ��ư�� ����üũ ���� �ʾ�����..
			{
				GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); 	  //���ο� �迭��ȣ ��ư�� �̹����� ���� �迭��ȣ ��ư�� �̹����� �����ϰ�
				GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);		  //���� �迭��ȣ ��ư�� �̹����� ���ο� �迭��ȣ ��ư�� �̹����� �������ش�.
			}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==true&&             //���� �迭��ȣ�� ��ư�� ����üũ �Ǿ���,                
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==false)        //���ο� �迭��ȣ�� ��ư�� ����üũ�� ���� �ʾ�����..              
				{                                                                                                           
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(myBingo);   //���ο� �迭��ȣ ��ư�� �̹����� ���� ������ ���� �´� ����üũ �̹����� �����ϰ� 
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);	  //���� �迭��ȣ ��ư�� �̹����� ���ο� �迭��ȣ ��ư�� �̹����� �����ϰ�
					GameProcess.bingo1[panCol][i-(25*panCol)]=false;              //���� �迭��ȣ�� ��ư�� ����üũ "����"���ְ�
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=true;		  //���ο� �迭��ȣ�� ��ư�� ����üũ���ش�.
				}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&            //���� �迭��ȣ�� ��ư�� ����üũ ���� �ʾҰ�,                
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==true)         //���ο� �迭��ȣ�� ��ư�� ����üũ �Ǿ�����..               
				{                                                                                                             
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); //���ο� �迭��ȣ ��ư�� �̹����� ���� �迭��ȣ ��ư�� �̹����� �����ϰ�    
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(myBingo);		  //���� �迭��ȣ ��ư�� �̹����� ���� ������ ���� �´� ����üũ �̹����� �����ϰ�
					GameProcess.bingo1[panCol][i-(25*panCol)]=true;				  //���� �迭��ȣ�� ��ư�� ����üũ���ְ�,
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=false;		  //���ο� �迭��ȣ�� ��ư�� ����üũ "����" ���ش�.
				}
		  }  
	   }
	   else //panRow==0; <���� �����ϳ�, ������� ����̴�> �����̹����� �ʿ����...
	   {
			  for(int i=panCol*25; i<24+(panCol*25); i++)
			  {
				ImageIcon noBingo=new ImageIcon("img\\������-���.png");
				int newArr=(int)(Math.random()*25+(panCol*25));
				int temp=numArr2[i];
				numArr2[i]=numArr2[newArr];
				numArr2[newArr]=temp;
				p2Board[panCol][i-(25*panCol)]=numArr2[i];
				p2Board[panCol][newArr-(25*panCol)]=numArr2[newArr];
				
				if(GameProcess.bingo2[panCol][i-(25*panCol)]==false&&
				   GameProcess.bingo2[panCol][newArr-(25*panCol)]==false)
				{
					GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(noBingo); 
					GameLayout.a2[panCol][i-(25*panCol)].setIcon(noBingo);
				}
				else if(GameProcess.bingo2[panCol][i-(25*panCol)]==true&&
					    GameProcess.bingo2[panCol][newArr-(25*panCol)]==false)
					{
						GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(yourBingo); 
						GameProcess.bingo2[panCol][i-(25*panCol)]=false;
						GameLayout.a2[panCol][i-(25*panCol)].setIcon(noBingo);
						GameProcess.bingo2[panCol][newArr-(25*panCol)]=true;
					}
				else if(GameProcess.bingo2[panCol][i-(25*panCol)]==false&&
					    GameProcess.bingo2[panCol][newArr-(25*panCol)]==true)
					{
						GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(noBingo); 
						GameProcess.bingo2[panCol][i-(25*panCol)]=true;
						GameLayout.a2[panCol][i-(25*panCol)].setIcon(yourBingo);
						GameProcess.bingo2[panCol][newArr-(25*panCol)]=false;
					}
			  }  
	   }
	   lineCount();
   }
   
   //�ϼ��� ���� üũ �˰���
   static int lineCountMethod(boolean[][] bingo, int pan)
   {
	   int line=0;
	   for(int i=0; i<5; i++)
	   {
		   //����
		   if(bingo[pan][i*5]==true&&bingo[pan][(i*5)+1]==true
				   &&bingo[pan][(i*5)+2]==true&&bingo[pan][(i*5)+3]==true
				   &&bingo[pan][(i*5)+4]==true)
		   {
			   line++;
		   }
		   //����
		   if(bingo[pan][i]==true&&bingo[pan][i+5]==true
				   &&bingo[pan][i+10]==true&&bingo[pan][i+15]==true
	               &&bingo[pan][i+20]==true)
		   {
	            line++;
		   }
	   }
	   //�밢��
	   if(bingo[pan][0]==true&&bingo[pan][6]==true
			   &&bingo[pan][12]==true&&bingo[pan][18]==true
			   &&bingo[pan][24]==true)
	   {
		   line++;
	   }
	   if(bingo[pan][4]==true&&bingo[pan][8]==true
			   &&bingo[pan][12]==true&&bingo[pan][16]==true
			   &&bingo[pan][20]==true)
	   {
		   line++;	
	   }
	   return line;
   }
   
   static void lineCount() //���� ī��Ʈ
   {
      //���� �� ����
      //ù��°��
      numOfBingo1[0]=lineCountMethod(bingo1,0);
      gaugeCtrl(numOfBingo1[0], 1, 0, "�� ��");//ī��Ʈ �Ǵ� ��ŭ �ʻ��
      bingoIcon(numOfBingo1[0], 4);
      goongCtrl(numOfBingo1[0],1,0);
      
      //�ι�°��
      numOfBingo1[1]=lineCountMethod(bingo1,1);
      gaugeCtrl(numOfBingo1[1], 1, 1, "�������");
      bingoIcon(numOfBingo1[1], 5);
      goongCtrl(numOfBingo1[1],1,1);
      
      //����°��
      numOfBingo1[2]=lineCountMethod(bingo1,2);
      gaugeCtrl(numOfBingo1[2], 1, 2, "�����ı�");
      bingoIcon(numOfBingo1[2], 6);
      goongCtrl(numOfBingo1[2],1,2);
      
      //����� üũ
      //ù��°��
      numOfBingo2[0]=lineCountMethod(bingo2,0);
      gaugeCtrl(numOfBingo2[0], 0, 0, "�� ��");
      bingoIcon(numOfBingo2[0], 1);
      goongCtrl(numOfBingo2[0],0,0);
      
      //�ι�°��
      numOfBingo2[1]=lineCountMethod(bingo2,1);
      gaugeCtrl(numOfBingo2[1], 0, 1, "�������");
      bingoIcon(numOfBingo2[1], 2);
      goongCtrl(numOfBingo2[1],0,1);
      
      //����°��
      numOfBingo2[2]=lineCountMethod(bingo2,2);
      gaugeCtrl(numOfBingo2[2], 0, 2, "�����ı�");
      bingoIcon(numOfBingo2[2], 3);
      goongCtrl(numOfBingo2[2],0,2);
      
      if(numOfBingo1[0]>=5||numOfBingo1[1]>=5||numOfBingo1[2]>=5)
      {
    	  GameLayout.bingoEnd=true;
      }
      if(numOfBingo2[0]>=5||numOfBingo2[1]>=5||numOfBingo2[2]>=5)
      {
    	  GameLayout.bingoEnd=true;
      }
   }
   
   //���� �����鼭(�׺� �Ǵ� �����) GameProcess Ŭ���� �� static ������ �ʱ�ȭ �޼ҵ�
   static public void gameReset()
   {
		GameProcess.numOfBingo1=new int[3];
		GameProcess.numOfBingo2=new int[3];
		GameProcess.bingo1=new boolean[3][25];
		GameProcess.bingo2=new boolean[3][25];
		GameProcess.usingAttackSkill1=0;
		GameProcess.usingDefenseSkill1=0;
		GameProcess.usingStrategySkill1=0;
		GameProcess.usingAttackSkill2=0;
		GameProcess.usingDefenseSkill2=0;
		GameProcess.usingStrategySkill2=0;
		GameProcess.bingoCheckChance1=1;
		GameProcess.bingoCheckChance2=1;
		GameProcess.skillChance1=1;
		GameProcess.skillChance2=1;
		GameProcess.coinA=0;
		GameProcess.coinB=0;
		GameProcess.skillChance1=1;
		GameProcess.skillChance2=1;
		GameProcess.bingoCheckChance1=1;
		GameProcess.bingoCheckChance2=1;
   }   
   
}