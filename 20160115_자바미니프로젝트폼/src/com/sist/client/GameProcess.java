package com.sist.client;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
public class GameProcess extends JPanel{
   static int[][] p1Board= new int[3][25];   //플레이어1 빙고판 숫자
   static int[][] p2Board= new int[3][25];   //플레이어2 빙고판
   
   static int[] numArr1=new int[75]; //랜덤으로 섞을 숫자 생성
   static int[] numArr2=new int[75]; //플레이어1,2
   
   static int chosenBingoNumber; //선택된 빙고번호
   static boolean[][] bingo1=new boolean[3][25]; //빙고처리
   static boolean[][] bingo2=new boolean[3][25];
   static int[] numOfBingo1=new int[3]; //각 판 별로 카운트처리
   static int[] numOfBingo2=new int[3];

   //스킬,필살기는 3이 되면 사용
   static int attackSkill1 = 0;
   static int attackFinish1 = 0;
   static int defenseSkill1 = 0;
   static int defenseFinish1 = 0;
   static int strategySkill1 = 0;
   static int strategyFinish1 = 0;
   
   static int usingAttackSkill1 = 0; //스킬을 사옹하면 -1 
   static int usingAttackFinish1 = 0;//필살기는 사용시 -3
   static int usingDefenseSkill1 = 0;
   static int usingDefenseFinish1 = 0;
   static int usingStrategySkill1 = 0;
   static int usingStrategyFinish1 = 0;
   
   static boolean bAttackSkill1 = false; //아이콘을 클릭했을때 트루가 되고 사용시 false 
   static boolean bAttackFinish1 = false;
   static boolean bDefenseSkill1 = false;
   static boolean bDefenseFinish1 = false;
   static boolean bStrategySkill1 = false;
   static boolean bStrategyFinish1 = false;
   
   //스킬
   static int attackSkill2 = 0;
   static int attackFinish2 = 0; //필살기는 3이 되면 사용, 사용시 -3
   static int defenseSkill2 = 0;
   static int defenseFinish2 = 0;
   static int strategySkill2 = 0;
   static int strategyFinish2 = 0;
      
   static boolean bAttackSkill2 = false; //아이콘을 클릭했을때 트루가 되고 사용시 false 
   static boolean bAttackFinish2 = false;
   static boolean bDefenseSkill2 = false;
   static boolean bDefenseFinish2 = false;
   static boolean bStrategySkill2 = false;
   static boolean bStrategyFinish2 = false;
   
   static boolean playerTurn=false; //true=player1, false=player2 

   static int[] gaugeScore={0,33,66,100};//게이지바
   
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
      insertBingoNumber1();//판에 숫자 배치
      insertBingoNumber2();
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

   static void CheckBingo1(JButton btn,ImageIcon icon)//빙고체크하고 상대 빙고도 뒤집어주기
   {
      for(int i=0; i<3; i++)
      {
         for(int j=0; j<25; j++)
         {
            if(p1Board[i][j]==chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
            {
               btn.setIcon(icon); //그 자리 버튼의 아이콘을 바꿔주고
               bingo1[i][j]=true; //빙고가 체크된걸로(true로)표시
            }
         }
      }
   }
   
   static void lineCount() //라인 카운트
   {
      int numOfLine1=0; //플레이어1 카운트
      int numOfLine2=0;
      int numOfLine3=0;
      
      int numOfLine4=0; //플레이어2 카운트
      int numOfLine5=0;
      int numOfLine6=0;
      
      //빙고 줄 세기
      //첫번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo1[0][i*5]==true&&bingo1[0][(i*5)+1]==true
               &&bingo1[0][(i*5)+2]==true&&bingo1[0][(i*5)+3]==true
               &&bingo1[0][(i*5)+4]==true)
         {
            numOfLine1++;
         }
         //세로
         if(bingo1[0][i]==true&&bingo1[0][i+5]==true
               &&bingo1[0][i+10]==true&&bingo1[0][i+15]==true
               &&bingo1[0][i+20]==true)
         {
            numOfLine1++;
         }
      }
      //대각선
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
      
      //두번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo1[1][i*5]==true&&bingo1[1][(i*5)+1]==true
               &&bingo1[1][(i*5)+2]==true&&bingo1[1][(i*5)+3]==true
               &&bingo1[1][(i*5)+4]==true)
         {
            numOfLine2++;
         }
         //세로
         if(bingo1[1][i]==true&&bingo1[1][i+5]==true
               &&bingo1[1][i+10]==true&&bingo1[1][i+15]==true
               &&bingo1[1][i+20]==true)
         {
            numOfLine2++;
         }
         
      }
      //대각선
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
      
      //세번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo1[2][i*5]==true&&bingo1[2][(i*5)+1]==true
               &&bingo1[2][(i*5)+2]==true&&bingo1[2][(i*5)+3]==true
               &&bingo1[2][(i*5)+4]==true)
         {
            numOfLine3++;
         }
         //세로
         if(bingo1[2][i]==true&&bingo1[2][i+5]==true
               &&bingo1[2][i+10]==true&&bingo1[2][i+15]==true
               &&bingo1[2][i+20]==true)
         {
            numOfLine3++;
         }
         
      }
      //대각선
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
      
      //상대판 체크
      //첫번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo2[0][i*5]==true&&bingo2[0][(i*5)+1]==true
               &&bingo2[0][(i*5)+2]==true&&bingo2[0][(i*5)+3]==true
               &&bingo2[0][(i*5)+4]==true)
         {
            numOfLine4++;
         }
         //세로
         if(bingo2[0][i]==true&&bingo2[0][i+5]==true
               &&bingo2[0][i+10]==true&&bingo2[0][i+15]==true
               &&bingo2[0][i+20]==true)
         {
            numOfLine4++;
         }
      }
      //대각선
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
      
      //두번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo2[1][i*5]==true&&bingo2[1][(i*5)+1]==true
               &&bingo2[1][(i*5)+2]==true&&bingo2[1][(i*5)+3]==true
               &&bingo2[1][(i*5)+4]==true)
         {
            numOfLine5++;
         }
         //세로
         if(bingo2[1][i]==true&&bingo2[1][i+5]==true
               &&bingo2[1][i+10]==true&&bingo2[1][i+15]==true
               &&bingo2[1][i+20]==true)
         {
            numOfLine5++;
         }
         
      }
      //대각선
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
      
      //세번째판
      for(int i=0; i<5; i++)
      {
         //가로
         if(bingo2[2][i*5]==true&&bingo2[2][(i*5)+1]==true
               &&bingo2[2][(i*5)+2]==true&&bingo2[2][(i*5)+3]==true
               &&bingo2[2][(i*5)+4]==true)
         {
            numOfLine6++;
         }
         //세로
         if(bingo2[2][i]==true&&bingo2[2][i+5]==true
               &&bingo2[2][i+10]==true&&bingo2[2][i+15]==true
               &&bingo2[2][i+20]==true)
         {
            numOfLine6++;
         }
         
      }
      //대각선
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
         // 창을 하나 띄워서 빙고버튼을 누르게 함
         JOptionPane.showMessageDialog(new GameLayout(), "게임종료");
      }
      if(numOfBingo2[0]>=5||numOfBingo2[1]>=5||numOfBingo2[2]>=5)
      {
    	  JOptionPane.showMessageDialog(new GameLayout(), "게임종료");
      }
   }

   static void getSkill() //스킬 갯수 추가
   {
      if(attackSkill1>0)
      {
         //이미지 추가
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

   static void clickSkill_Icon() //스킬 클릭
   {
      //스킬 사용버 메시지 띄우기
   }
   
   static void useSkill1() //스킬사용
   {
      //상대판에만 사용 가능
      
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