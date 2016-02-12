package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
public class GameProcess extends JPanel{
	GameLayout gl=new GameLayout();
	
   static int[][] p1Board= new int[3][25];   //플레이어1 빙고판 숫자
   static int[][] p2Board= new int[3][25];   //플레이어2 빙고판
   
   //랜덤으로 섞을 숫자 생성
   static int[] numArr1=new int[75]; //플레이어1  
   static int[] numArr2=new int[75]; //플레이어2
   
   static int chosenBingoNumber; //선택된 빙고번호
   static boolean[][] bingo1=new boolean[3][25]; //빙고처리
   static boolean[][] bingo2=new boolean[3][25];
   static int[] numOfBingo1=new int[3]; //각 판 별로 카운트처리
   static int[] numOfBingo2=new int[3];
   
   //numOfBing+usingAttackSkill로 항상 사용, 스킬을 사옹하면 -1, 필살기는 사용시 -3
   static int usingAttackSkill1=0;
   static int usingDefenseSkill1=0;
   static int usingStrategySkill1=0;
   
   //아이콘을 클릭했을때 트루가 되고 사용시 false 
   static boolean bAttackSkill1=false;
   static boolean bDefenseSkill1=false;
   static boolean bStrategySkill1=false;
   
   //플레이어2스킬
   static int usingAttackSkill2=0;
   static int usingDefenseSkill2=0;
   static int usingStrategySkill2=0;
   static boolean bAttackSkill2=false;
   static boolean bDefenseSkill2=false;
   static boolean bStrategySkill2=false;
   
   static boolean playerTurn=true; //true=player1, false=player2
   
   static int bingoCheckChance1=1, bingoCheckChance2=1;//한 턴에 빙고 체크를 할 수 있는 횟수
   static int skillChance1=1, skillChance2=1;

   static int[] gaugeScore={0,33,66,100};//게이지바
   
   static int coinA=0;
   static int coinB=0;
   static void coinRand() //플레이어턴을 정하는 난수 발생
   {
	   coinB=(int)(Math.random()*10)+20;
	   if(coinA%2==0)
		   playerTurn=true;
	   else
		   playerTurn=false;
   }
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

   //빙고체크
   static void bingoCheck(int i, int j, int[][] board1, int[][]board2,
		   boolean[][] b1, boolean[][] b2, JButton[][] btn1, JButton[][] btn2,
		   ImageIcon icon1, ImageIcon icon2)
   {
	   chosenBingoNumber=board1[i][j];
	   if(board1[i][j]==chosenBingoNumber) //선택한 숫자와 보드판의 숫자가 맞으면
	   {
          btn1[i][j].setIcon(icon1); //그 자리 버튼의 아이콘을 바꿔주고
          b1[i][j]=true; //빙고가 체크된걸로(true로)표시
       }
	   for(int k=0; k<3; k++)
	   {
		   for(int l=0; l<25; l++)
		   {
			   //상대것도 빙고체크
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
   
   static void gaugeCtrl(int lineNum, int a, int b, String str) //게이지 상승 및 궁극기 버튼 활성화 메소드
   {
     if(lineNum>=3)
     {
        if(GameLayout.goongUsable1[b]==true&&a==1)
         {   GameLayout.gauge[a][b].setValue(gaugeScore[3]);
               GameLayout.gauge[a][b].setString(str);
               GameLayout.gauge[a][b].setFont(new Font("궁서체",Font.BOLD,20));
               GameLayout.gauge[a][b].setVisible(true);
         }
         else if(GameLayout.goongUsable2[b]==true&&a==0)
         {
            GameLayout.gauge[a][b].setValue(gaugeScore[3]);
               GameLayout.gauge[a][b].setString(str);
               GameLayout.gauge[a][b].setFont(new Font("궁서체",Font.BOLD,20));
               GameLayout.gauge[a][b].setVisible(true);
         }
     }
     else
        GameLayout.gauge[a][b].setValue(gaugeScore[lineNum]);
   }
 
   static void goongCtrl(int lineNum, int a, int b)   //세줄완성=>필살기 사용 가능 
   {
	   if(lineNum>=3)  
		   //if()한번썼을경우에는 비활성화
		   //GameLayout.fury[a][b].setEnabled(false);
		   //else() <-- 한번도 안썼을경우
		   GameLayout.fury[a][b].setEnabled(true);
   }
	  
   static void bingoIcon(int lineNo,int a) //완성된 줄 카운트 다섯개,int a는 판숫자 (1~6);
   {
	  for(int i=0;i<lineNo;i++)
	   {
		  if(lineNo>0&&lineNo<6)
			  GameLayout.bingoScore[a-1][i].setIcon(GameLayout.bingo2);//하나 완성되면 아이콘모양 교차하는 칼로 바꿈
	   }
   }
   
   //진영파괴(JinYoung PaGoe)
   static void jypg(int panRow, int panCol)
   {
	   ImageIcon[] dft= {GameLayout.bcIcon0,GameLayout.bcIcon1,GameLayout.bcIcon2}; //위촉오 빙고키
	   ImageIcon myBingo=dft[ChoiceNation.chosenNation1];
	   ImageIcon yourBingo=dft[ChoiceNation.chosenNation2];
	   if(panRow==1)
	   {
		  for(int i=panCol*25; i<24+(panCol*25); i++)
		  {
			ImageIcon originNum=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			int newArr=(int)(Math.random()*25+(panCol*25));
			ImageIcon newImgNum=new ImageIcon("img\\"+GameProcess.numArr1[newArr]+".png");
			int temp=numArr1[i];
			numArr1[i]=numArr1[newArr];
			numArr1[newArr]=temp;
			if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&
			   GameProcess.bingo1[panCol][newArr-(25*panCol)]==false)
			{
				GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); 
				GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);
			}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==true&&
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==false)
				{
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(myBingo); 
					GameProcess.bingo1[panCol][i-(25*panCol)]=false;
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=true;
				}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==true)
				{
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); 
					GameProcess.bingo1[panCol][i-(25*panCol)]=true;
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(myBingo);
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=false;
				}
				
			p1Board[panCol][i-(25*panCol)]=numArr1[i];
			p1Board[panCol][newArr-(25*panCol)]=numArr1[newArr];
		  }  
	   }
	   else //panRow==0;
	   {
			  for(int i=panCol*25; i<24+(panCol*25); i++)
			  {
				ImageIcon originNum=new ImageIcon("img\\빙고판-상대.png");
//				ImageIcon originNum=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
				int newArr=(int)(Math.random()*25+(panCol*25));
				ImageIcon newImgNum=new ImageIcon("img\\빙고판-상대.png");
//				ImageIcon newImgNum=new ImageIcon("img\\"+GameProcess.numArr2[newArr]+".png");
				int temp=numArr2[i];
				numArr2[i]=numArr2[newArr];
				numArr2[newArr]=temp;
				if(GameProcess.bingo2[panCol][i-(25*panCol)]==false&&
				   GameProcess.bingo2[panCol][newArr-(25*panCol)]==false)
				{
					GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(originNum); 
					GameLayout.a2[panCol][i-(25*panCol)].setIcon(newImgNum);
				}
				else if(GameProcess.bingo2[panCol][i-(25*panCol)]==true&&
					    GameProcess.bingo2[panCol][newArr-(25*panCol)]==false)
					{
						GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(yourBingo); 
						GameProcess.bingo2[panCol][i-(25*panCol)]=false;
						GameLayout.a2[panCol][i-(25*panCol)].setIcon(newImgNum);
						GameProcess.bingo2[panCol][newArr-(25*panCol)]=true;
					}
				else if(GameProcess.bingo2[panCol][i-(25*panCol)]==false&&
					    GameProcess.bingo2[panCol][newArr-(25*panCol)]==true)
					{
						GameLayout.a2[panCol][newArr-(25*panCol)].setIcon(originNum); 
						GameProcess.bingo2[panCol][i-(25*panCol)]=true;
						GameLayout.a2[panCol][i-(25*panCol)].setIcon(yourBingo);
						GameProcess.bingo2[panCol][newArr-(25*panCol)]=false;
					}
					
				p2Board[panCol][i-(25*panCol)]=numArr2[i];
				p2Board[panCol][newArr-(25*panCol)]=numArr2[newArr];
			  }  
	   }
	   lineCount();
   }
   
   //완성된 라인 체크 알고리즘
   static int lineCountMethod(boolean[][] bingo, int pan)
   {
	   int line=0;
	   for(int i=0; i<5; i++)
	   {
		   //가로
		   if(bingo[pan][i*5]==true&&bingo[pan][(i*5)+1]==true
				   &&bingo[pan][(i*5)+2]==true&&bingo[pan][(i*5)+3]==true
				   &&bingo[pan][(i*5)+4]==true)
		   {
			   line++;
		   }
		   //세로
		   if(bingo[pan][i]==true&&bingo[pan][i+5]==true
				   &&bingo[pan][i+10]==true&&bingo[pan][i+15]==true
	               &&bingo[pan][i+20]==true)
		   {
	            line++;
		   }
	   }
	   //대각선
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
   
   static void lineCount() //라인 카운트
   {
      //빙고 줄 세기
      //첫번째판
      numOfBingo1[0]=lineCountMethod(bingo1,0);
      gaugeCtrl(numOfBingo1[0], 1, 0, "투 신");//카운트 되는 만큼 필살기
      bingoIcon(numOfBingo1[0], 4);
      goongCtrl(numOfBingo1[0],1,0);
      
      //두번째판
      numOfBingo1[1]=lineCountMethod(bingo1,1);
      gaugeCtrl(numOfBingo1[1], 1, 1, "적진기습");
      bingoIcon(numOfBingo1[1], 5);
      goongCtrl(numOfBingo1[1],1,1);
      
      //세번째판
      numOfBingo1[2]=lineCountMethod(bingo1,2);
      gaugeCtrl(numOfBingo1[2], 1, 2, "진영파괴");
      bingoIcon(numOfBingo1[2], 6);
      goongCtrl(numOfBingo1[2],1,2);
      
      //상대판 체크
      //첫번째판
      numOfBingo2[0]=lineCountMethod(bingo2,0);
      gaugeCtrl(numOfBingo2[0], 0, 0, "투 신");
      bingoIcon(numOfBingo2[0], 1);
      goongCtrl(numOfBingo2[0],0,0);
      
      //두번째판
      numOfBingo2[1]=lineCountMethod(bingo2,1);
      gaugeCtrl(numOfBingo2[1], 0, 1, "적진기습");
      bingoIcon(numOfBingo2[1], 2);
      goongCtrl(numOfBingo2[1],0,1);
      
      //세번째판
      numOfBingo2[2]=lineCountMethod(bingo2,2);
      gaugeCtrl(numOfBingo2[2], 0, 2, "진영파괴");
      bingoIcon(numOfBingo2[2], 3);
      goongCtrl(numOfBingo2[2],0,2);
      
      gameEnd();
   }
   
   static void gameEnd()
   {
	   if(numOfBingo1[0]>=5||numOfBingo1[1]>=5||numOfBingo1[2]>=5)
	   {
		   BingoEnd be=new BingoEnd();
		   be.setVisible(true);
	   }
	   if(numOfBingo2[0]>=5||numOfBingo2[1]>=5||numOfBingo2[2]>=5)
	   {
	   }
   }
   
   static void gameReset()
   {
	   //빙고버튼이미지,빙고판숫자(rand)   
	   //GameLayout.a1=new Jbutton[3][25];
	   //GameLayout.a2=new Jbutton[3][25];
	   GameProcess.rand();
	   for(int i=0; i<3; i++)
	   {
		   numOfBingo1[i]=0;
		   numOfBingo2[i]=0;
		   for(int j=0; j<25; j++)
		   {
			   GameLayout.a1[i][j].setIcon(new ImageIcon("img\\"+GameProcess.numArr1[(i*25)+j]+".png"));
			   GameLayout.a2[i][j].setIcon(new ImageIcon("img\\빙고판-상대.png"));
			   bingo1[i][j]=false;
			   bingo2[i][j]=false;
			   GameLayout.panCheck1[i][j]=false;
			   GameLayout.panCheck2[i][j]=false;
		   }
	   }
	   //빙고체크false,numOfBingo  // usingSkill,스킬갯수Label
	   usingAttackSkill1=0;
	   usingDefenseSkill1=0;
	   usingStrategySkill1=0;
	   usingAttackSkill2=0;
	   usingDefenseSkill2=0;
	   usingStrategySkill2=0;
	   bingoCheckChance1=1;
	   bingoCheckChance2=1;
	   skillChance1=1;
	   skillChance2=1;
	   //필살기프로그레스바,필살기버튼   
	   GameLayout.gauge=new JProgressBar[2][3];
	   GameLayout.fury=new JButton[2][3];
	   //전술명령,지휘권Label
   }
}