package com.sist.client;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
public class GameProcess extends JPanel{
   static int[][] p1Board= new int[3][25];   //�÷��̾�1 ������ ����
   static int[][] p2Board= new int[3][25];   //�÷��̾�2 ������
   
   static int[] numArr1=new int[75]; //�������� ���� ���� ����
   static int[] numArr2=new int[75]; //�÷��̾�1,2
   
   static int chosenBingoNumber; //���õ� �����ȣ
   static boolean[][] bingo1=new boolean[3][25]; //����ó��
   static boolean[][] bingo2=new boolean[3][25];
   static int[] numOfBingo1=new int[3]; //�� �� ���� ī��Ʈó��
   static int[] numOfBingo2=new int[3];

   //��ų,�ʻ��� 3�� �Ǹ� ���
   static int attackSkill1 = 0;
   static int attackFinish1 = 0;
   static int defenseSkill1 = 0;
   static int defenseFinish1 = 0;
   static int strategySkill1 = 0;
   static int strategyFinish1 = 0;
   
   static int usingAttackSkill1 = 0; //��ų�� ����ϸ� -1 
   static int usingAttackFinish1 = 0;//�ʻ��� ���� -3
   static int usingDefenseSkill1 = 0;
   static int usingDefenseFinish1 = 0;
   static int usingStrategySkill1 = 0;
   static int usingStrategyFinish1 = 0;
   
   static boolean bAttackSkill1 = false; //�������� Ŭ�������� Ʈ�簡 �ǰ� ���� false 
   static boolean bAttackFinish1 = false;
   static boolean bDefenseSkill1 = false;
   static boolean bDefenseFinish1 = false;
   static boolean bStrategySkill1 = false;
   static boolean bStrategyFinish1 = false;
   
   //��ų
   static int attackSkill2 = 0;
   static int attackFinish2 = 0; //�ʻ��� 3�� �Ǹ� ���, ���� -3
   static int defenseSkill2 = 0;
   static int defenseFinish2 = 0;
   static int strategySkill2 = 0;
   static int strategyFinish2 = 0;
      
   static boolean bAttackSkill2 = false; //�������� Ŭ�������� Ʈ�簡 �ǰ� ���� false 
   static boolean bAttackFinish2 = false;
   static boolean bDefenseSkill2 = false;
   static boolean bDefenseFinish2 = false;
   static boolean bStrategySkill2 = false;
   static boolean bStrategyFinish2 = false;
   
   static boolean playerTurn=false; //true=player1, false=player2 

   static int[] gaugeScore={0,33,66,100};//��������
   
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

   static void CheckBingo1(JButton btn,ImageIcon icon)//����üũ�ϰ� ��� ���� �������ֱ�
   {
      for(int i=0; i<3; i++)
      {
         for(int j=0; j<25; j++)
         {
            if(p1Board[i][j]==chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
            {
               btn.setIcon(icon); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
               bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
            }
         }
      }
   }
   
   static void lineCount() //���� ī��Ʈ
   {
      int numOfLine1=0; //�÷��̾�1 ī��Ʈ
      int numOfLine2=0;
      int numOfLine3=0;
      
      int numOfLine4=0; //�÷��̾�2 ī��Ʈ
      int numOfLine5=0;
      int numOfLine6=0;
      
      //���� �� ����
      //ù��°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo1[0][i*5]==true&&bingo1[0][(i*5)+1]==true
               &&bingo1[0][(i*5)+2]==true&&bingo1[0][(i*5)+3]==true
               &&bingo1[0][(i*5)+4]==true)
         {
            numOfLine1++;
         }
         //����
         if(bingo1[0][i]==true&&bingo1[0][i+5]==true
               &&bingo1[0][i+10]==true&&bingo1[0][i+15]==true
               &&bingo1[0][i+20]==true)
         {
            numOfLine1++;
         }
      }
      //�밢��
      if(bingo1[0][0]==true&&bingo1[0][6]==true
            &&bingo1[0][12]==true&&bingo1[0][18]==true
            &&bingo1[0][24]==true)
      {
         numOfLine1++;
      }
      if(bingo1[0][4]==true&&bingo1[0][8]==true
            &&bingo1[0][12]==true&&bingo1[0][16]==true
            &&bingo1[0][20]==true)
      {
         numOfLine1++;
      }
      numOfBingo1[0]=numOfLine1;
      if(numOfLine1>3)
    	  GameLayout.gauge[1][0].setValue(gaugeScore[3]);
      else
    	  GameLayout.gauge[1][0].setValue(gaugeScore[numOfLine1]);
      attackSkill1=numOfLine1;
      attackFinish1=numOfLine1;
      
      //�ι�°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo1[1][i*5]==true&&bingo1[1][(i*5)+1]==true
               &&bingo1[1][(i*5)+2]==true&&bingo1[1][(i*5)+3]==true
               &&bingo1[1][(i*5)+4]==true)
         {
            numOfLine2++;
         }
         //����
         if(bingo1[1][i]==true&&bingo1[1][i+5]==true
               &&bingo1[1][i+10]==true&&bingo1[1][i+15]==true
               &&bingo1[1][i+20]==true)
         {
            numOfLine2++;
         }
         
      }
      //�밢��
      if(bingo1[1][0]==true&&bingo1[1][6]==true
            &&bingo1[1][12]==true&&bingo1[1][18]==true
            &&bingo1[1][24]==true)
      {
         numOfLine2++;
      }
      if(bingo1[1][4]==true&&bingo1[1][8]==true
            &&bingo1[1][12]==true&&bingo1[1][16]==true
            &&bingo1[1][20]==true)
      {
         numOfLine2++;
      }
      numOfBingo1[1]=numOfLine2;
      if(numOfLine2>3)
    	  GameLayout.gauge[1][1].setValue(gaugeScore[3]);
      else
    	  GameLayout.gauge[1][1].setValue(gaugeScore[numOfLine2]);
      attackSkill1=numOfLine2;
      attackFinish1=numOfLine2;
      
      //����°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo1[2][i*5]==true&&bingo1[2][(i*5)+1]==true
               &&bingo1[2][(i*5)+2]==true&&bingo1[2][(i*5)+3]==true
               &&bingo1[2][(i*5)+4]==true)
         {
            numOfLine3++;
         }
         //����
         if(bingo1[2][i]==true&&bingo1[2][i+5]==true
               &&bingo1[2][i+10]==true&&bingo1[2][i+15]==true
               &&bingo1[2][i+20]==true)
         {
            numOfLine3++;
         }
         
      }
      //�밢��
      if(bingo1[2][0]==true&&bingo1[2][6]==true
            &&bingo1[2][12]==true&&bingo1[2][18]==true
            &&bingo1[2][24]==true)
      {
         numOfLine3++;
      }
      if(bingo1[2][4]==true&&bingo1[2][8]==true
            &&bingo1[2][12]==true&&bingo1[2][16]==true
            &&bingo1[2][20]==true)
      {
         numOfLine3++;
      }
      numOfBingo1[2]=numOfLine3;
      if(numOfLine3>3)
    	  GameLayout.gauge[1][2].setValue(gaugeScore[3]);
      else
    	  GameLayout.gauge[1][2].setValue(gaugeScore[numOfLine3]);
      strategySkill1=numOfLine3;
      strategyFinish1=numOfLine3;
      
      //����� üũ
      //ù��°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo2[0][i*5]==true&&bingo2[0][(i*5)+1]==true
               &&bingo2[0][(i*5)+2]==true&&bingo2[0][(i*5)+3]==true
               &&bingo2[0][(i*5)+4]==true)
         {
            numOfLine4++;
         }
         //����
         if(bingo2[0][i]==true&&bingo2[0][i+5]==true
               &&bingo2[0][i+10]==true&&bingo2[0][i+15]==true
               &&bingo2[0][i+20]==true)
         {
            numOfLine4++;
         }
      }
      //�밢��
      if(bingo2[0][0]==true&&bingo2[0][6]==true
            &&bingo2[0][12]==true&&bingo2[0][18]==true
            &&bingo2[0][24]==true)
      {
         numOfLine4++;
      }
      if(bingo2[0][4]==true&&bingo2[0][8]==true
            &&bingo2[0][12]==true&&bingo2[0][16]==true
            &&bingo2[0][20]==true)
      {
         numOfLine4++;
      }
      numOfBingo2[0]=numOfLine4;
      if(numOfLine4>3)
    	  GameLayout.gauge[0][0].setValue(gaugeScore[3]);  
      else
    	  GameLayout.gauge[0][0].setValue(gaugeScore[numOfLine4]);
      attackSkill2=numOfLine4;
      attackFinish2=numOfLine4;
      
      //�ι�°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo2[1][i*5]==true&&bingo2[1][(i*5)+1]==true
               &&bingo2[1][(i*5)+2]==true&&bingo2[1][(i*5)+3]==true
               &&bingo2[1][(i*5)+4]==true)
         {
            numOfLine5++;
         }
         //����
         if(bingo2[1][i]==true&&bingo2[1][i+5]==true
               &&bingo2[1][i+10]==true&&bingo2[1][i+15]==true
               &&bingo2[1][i+20]==true)
         {
            numOfLine5++;
         }
         
      }
      //�밢��
      if(bingo2[1][0]==true&&bingo2[1][6]==true
            &&bingo2[1][12]==true&&bingo2[1][18]==true
            &&bingo2[1][24]==true)
      {
         numOfLine5++;
      }
      if(bingo2[1][4]==true&&bingo2[1][8]==true
            &&bingo2[1][12]==true&&bingo2[1][16]==true
            &&bingo2[1][20]==true)
      {
         numOfLine5++;
      }
      numOfBingo2[1]=numOfLine5;
      if(numOfLine5>3)
    	  GameLayout.gauge[0][1].setValue(gaugeScore[3]);
      else
    	  GameLayout.gauge[0][1].setValue(gaugeScore[numOfLine5]);
      attackSkill2=numOfLine5;
      attackFinish2=numOfLine5;
      
      //����°��
      for(int i=0; i<5; i++)
      {
         //����
         if(bingo2[2][i*5]==true&&bingo2[2][(i*5)+1]==true
               &&bingo2[2][(i*5)+2]==true&&bingo2[2][(i*5)+3]==true
               &&bingo2[2][(i*5)+4]==true)
         {
            numOfLine6++;
         }
         //����
         if(bingo2[2][i]==true&&bingo2[2][i+5]==true
               &&bingo2[2][i+10]==true&&bingo2[2][i+15]==true
               &&bingo2[2][i+20]==true)
         {
            numOfLine6++;
         }
         
      }
      //�밢��
      if(bingo2[2][0]==true&&bingo2[2][6]==true
            &&bingo2[2][12]==true&&bingo2[2][18]==true
            &&bingo2[2][24]==true)
      {
         numOfLine6++;
      }
      if(bingo2[2][4]==true&&bingo2[2][8]==true
            &&bingo2[2][12]==true&&bingo2[2][16]==true
            &&bingo2[2][20]==true)
      {
         numOfLine6++;
      }
      numOfBingo2[2]=numOfLine6;
      if(numOfLine5>3)
    	  GameLayout.gauge[0][2].setValue(gaugeScore[3]);
      else
    	  GameLayout.gauge[0][2].setValue(gaugeScore[numOfLine6]);
      strategySkill2=numOfLine6;
      strategyFinish2=numOfLine6;
            
      if(numOfBingo1[0]>=5||numOfBingo1[1]>=5||numOfBingo1[2]>=5)
      {
         // â�� �ϳ� ����� �����ư�� ������ ��
         JOptionPane.showMessageDialog(new GameLayout(), "��������");
      }
      if(numOfBingo2[0]>=5||numOfBingo2[1]>=5||numOfBingo2[2]>=5)
      {
    	  JOptionPane.showMessageDialog(new GameLayout(), "��������");
      }
   }

   static void getSkill() //��ų ���� �߰�
   {
      if(attackSkill1>0)
      {
         //�̹��� �߰�
      }
      if(defenseSkill1>0)
      {
         
      }
      if(strategySkill1>0)
      {
         
      }
      if(attackFinish1>3)
      {
         
      }
      if(defenseFinish1>3)
      {
         
      }
      if(strategyFinish1>3)
      {
         
      }
   }

   static void clickSkill_Icon() //��ų Ŭ��
   {
      //��ų ���� �޽��� ����
   }
   
   static void useSkill1() //��ų���
   {
      //����ǿ��� ��� ����
      
      attackSkill1--;
      defenseSkill1--;
      strategySkill1--;
      attackFinish1-=3;
      defenseFinish1-=3;
      strategyFinish1-=3;
   }
   
   static void process()
   {
      rand();
      insertBingoNumber1();
      insertBingoNumber2();
   }

   
   

}