package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
public class GameProcess{
	static int[][] p1Board= new int[3][25];   //플레이어1 빙고판 실제 숫자
	static int[][] p2Board= new int[3][25];   //플레이어2 빙고판 실제 숫자
	
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
	//플레이어2스킬
	static int usingAttackSkill2=0;
	static int usingDefenseSkill2=0;
	static int usingStrategySkill2=0;
	
	static boolean playerTurn=true; //true=player1, false=player2
   
	static int bingoCheckChance1=1, bingoCheckChance2=1;//한 턴에 빙고 체크를 할 수 있는 횟수
	static int skillChance1=1, skillChance2=1;
	
	static int[] gaugeScore={0,33,66,100};//게이지바
	
	static int coinA=0;
	static int coinB=0;
	
	static boolean playerWon=false;
	static void coinRand() //플레이어턴을 정하는 난수 발생
	{
		coinB=(int)(Math.random()*10)+5;
		if(coinA%2==0)
			playerTurn=true;
		else
			playerTurn=true;//false
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
				su=(int)(Math.random()*75)+1;
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
	   ImageIcon[] dft= {GameLayout.bcIcon0,GameLayout.bcIcon1,GameLayout.bcIcon2}; //위촉오 빙고이미지
	   ImageIcon myBingo=dft[ChoiceNation.chosenNation1];    //내가 선택한 나라에 맞는 빙고체크 이미지 가져옴
	   ImageIcon yourBingo=dft[ChoiceNation.chosenNation2];	 //상대가 선택한 나라에 맞는 빙고체크 이미지 가져옴
	   if(panRow==1)//나의 판일경우 (panRow가 0:상대판, panRow가 1:내판)
	   {
		  for(int i=panCol*25; i<24+(panCol*25); i++) //panCol값이 0이면 공격판, 1이면 방어판, 2이면 전략판
		  {
			ImageIcon originNum=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png"); //기존 숫자 이미지
			int newArr=(int)(Math.random()*25+(panCol*25));
			ImageIcon newImgNum=new ImageIcon("img\\"+GameProcess.numArr1[newArr]+".png"); //랜덤으로 변경된 숫자 이미지 
			//정확히 설명하면 랜덤으로 뽑은 배열번호의 값에 해당하는 이미지를 저장하는 것
			
			//실제 빙고숫자값 교환
			int temp=numArr1[i]; //임시적으로 기존 배열번호의 실제값을 temp변수에 저장
			numArr1[i]=numArr1[newArr]; //랜덤으로 뽑은 배열번호의 실제값을 기존 배열번호에 저장하고
			numArr1[newArr]=temp; //임시적으로 저장한 기존 배열번호의 실제값을 랜덤으로 뽑은 배열번호의 실제값으로 저장
			p1Board[panCol][i-(25*panCol)]=numArr1[i]; //바뀌어진 기존 배열번호의 실제값을 p1Board(실제값이 빙고판 5by5 배열로 잡혀있는)의 기존배열위치에 저장해준다 >> 빙고점수 적용을 위해
			p1Board[panCol][newArr-(25*panCol)]=numArr1[newArr]; //뽑힌 배열번호의 실제값을 p1Board(실제값이 빙고판 5by5 배열로 잡혀있는)의 뽑힌배열위치에 저장해준다 >> 빙고점수 적용을 위해 
			
			if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&				  //기존 배열번호의 버튼이 빙고체크 되지 않았고,
			   GameProcess.bingo1[panCol][newArr-(25*panCol)]==false) 			  //새로운 배열번호의 버튼이 빙고체크 되지 않았으면..
			{
				GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); 	  //새로운 배열번호 버튼의 이미지를 기존 배열번호 버튼의 이미지로 변경하고
				GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);		  //기존 배열번호 버튼의 이미지를 새로운 배열번호 버튼의 이미지로 변경해준다.
			}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==true&&             //기존 배열번호의 버튼이 빙고체크 되었고,                
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==false)        //새로운 배열번호의 버튼이 빙고체크가 되지 않았으면..              
				{                                                                                                           
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(myBingo);   //새로운 배열번호 버튼의 이미지를 내가 선택한 나라에 맞는 빙고체크 이미지로 변경하고 
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(newImgNum);	  //기존 배열번호 버튼의 이미지를 새로운 배열번호 버튼의 이미지로 변경하고
					GameProcess.bingo1[panCol][i-(25*panCol)]=false;              //기존 배열번호의 버튼을 빙고체크 "해제"해주고
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=true;		  //새로운 배열번호의 버튼을 빙고체크해준다.
				}
			else if(GameProcess.bingo1[panCol][i-(25*panCol)]==false&&            //기존 배열번호의 버튼이 빙고체크 되지 않았고,                
				    GameProcess.bingo1[panCol][newArr-(25*panCol)]==true)         //새로운 배열번호의 버튼이 빙고체크 되었으면..               
				{                                                                                                             
					GameLayout.a1[panCol][newArr-(25*panCol)].setIcon(originNum); //새로운 배열번호 버튼의 이미지를 기존 배열번호 버튼의 이미지로 변경하고    
					GameLayout.a1[panCol][i-(25*panCol)].setIcon(myBingo);		  //기존 배열번호 버튼의 이미지를 내가 선택한 나라에 맞는 빙고체크 이미지로 변경하고
					GameProcess.bingo1[panCol][i-(25*panCol)]=true;				  //기존 배열번호의 버튼을 빙고체크해주고,
					GameProcess.bingo1[panCol][newArr-(25*panCol)]=false;		  //새로운 배열번호의 버튼을 빙코체크 "해제" 해준다.
				}
		  }  
	   }
	   else //panRow==0; <위와 동일하나, 상대판의 경우이다> 숫자이미지가 필요없음...
	   {
			  for(int i=panCol*25; i<24+(panCol*25); i++)
			  {
				ImageIcon noBingo=new ImageIcon("img\\빙고판-상대.png");
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
      
      if(numOfBingo1[0]>=5||numOfBingo1[1]>=5||numOfBingo1[2]>=5)
      {
    	  GameLayout.bingoEnd=true;
      }
      if(numOfBingo2[0]>=5||numOfBingo2[1]>=5||numOfBingo2[2]>=5)
      {
    	  GameLayout.bingoEnd=true;
      }
   }
   
   //게임 나가면서(항복 또는 재시작) GameProcess 클래스 내 static 변수들 초기화 메소드
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