package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;
import sun.net.www.content.image.jpeg;
import com.sist.client.GameLayout.TimeLimit; 
public class GameLayout extends JPanel implements ActionListener, KeyListener{
	//시간제한 타이머 
	static JProgressBar timer=new JProgressBar();  
	static boolean timeRun=true;
	static int colorInt=0;
	//턴종료 버튼  
	static JButton timeOut=new JButton();  
	static ImageIcon timeImg=new ImageIcon("img\\턴종료_기본.png");  
	static ImageIcon timeImg2=new ImageIcon("img\\턴종료_커서.png");  
	//항복 버튼  
	static JButton exit=new JButton();  
	static ImageIcon exitImg=new ImageIcon("img\\항복.png");  
	static ImageIcon exitImg2=new ImageIcon("img\\항복_커서.png");  
	//배경화면
	Image bg; //추상클래스 abstract!! 단독으로 메모리 할당을 못한다.
	Image vs; //가운데  vs 텍스트
	Image pan1; //빙고틀 플레이어1
	Image pan2; //빙고틀 플레이어2
	Image stateImage; // 상태창 이미지
	//스킬설명이미지들 
	Image attackSkillNotice, attackFinishNotice;              
	Image defenseSkillNotice, defenseFinishNotice;             
	Image strategySkillNotice, strategyFinishNotice;
	//스킬이미지들
	Image attackSkillImage, attackFinishImage;
	Image defenseSkillImage, defenseFinishImage;
	Image strategySkillImage, strategyFinishImage;
	//승패이미지
	Image won, lose;
	//이미지 좌표값들
	static int aNoticeX=1200, aFNoticeX=1200;
	static int dNoticeX=1200, dFNoticeX=1200;
	static int sNoticeX=1200, sFNoticeX=1200;
	static int aImageX=1200, aFImageX=1200;
	static int dImageX=1200, dFImageX=1200;
	static int sImageX=1200, sFImageX=1200;
	static int imageX=0, wonX=1200, loseX=1200;
	ChatInGame cig = new ChatInGame();

	//마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	//빙고판 
	static JButton[][] a1=new JButton[3][25]; //내 판
	static JButton[][] a2=new JButton[3][25]; //상대판
	
	//아이템선택버튼, 아바타
	static JButton btnAtt;  // 공격스킬버튼
	static JButton btnDef;  // 수비스킬버튼
	static JButton btnTrick;  // 책략스킬버튼
	static JButton btnAvatar; // 아바타 이미지 넣어줄 버튼
	
	static JButton youBtnAtt; // 상대편 버튼은 사실상 이미지 삽입용
	static JButton youBtnDef;
	static JButton youBtnTrick;
	static JButton youBtnAvatar;
	
	static int useAtt,useDef,useTrick;
	
	//아이템 갯수 확인 및 아이디
	static JLabel laAtt;  // 공격스킬 개수 확인
	static JLabel laDef;  // 수비스킬 개수 확인
	static JLabel laTrick;  // 책략스킬 개수 확인
	static JLabel laNickname;  // 닉네임 입력하는 곳
	static JLabel laTactic, laCommand; // 전술명령, 지휘권
	
	static JLabel youLaAtt;   // 상대방.
	static JLabel youLaDef;
	static JLabel youLaTrick;
	static JLabel youLaNickname;
	static JLabel youLaTactic, youLaCommand;
		
	//플레이어 1,2  장기판별 게이지 
	static JProgressBar[][] gauge=new JProgressBar[2][3];
	//플레이어 1,2 장기판별 궁극기 활성 안내버튼
	static JButton[][] fury=new JButton[2][3];
	
	//플레이어 1,2 장기판별 빙고획득점수 확인패널
	static JButton[][] bingoScore=new JButton[6][5];
	static JPanel[] bingoScorePan=new JPanel[6];
	static ImageIcon bingo1=new ImageIcon("img\\빙고-한줄.png");
	static ImageIcon bingo2=new ImageIcon("img\\빙고-한줄완성.png");
	static ImageIcon attIcon=new ImageIcon("img\\스킬아이콘-공격.png");
	static ImageIcon defIcon=new ImageIcon("img\\스킬아이콘-방어.png");
	static ImageIcon trickIcon=new ImageIcon("img\\스킬아이콘-책략.png");
	static ImageIcon avatarIcon=new ImageIcon("img\\m1.jpg");
	static ImageIcon youAvatarIcon=new ImageIcon("img\\m2.jpg");
	static ImageIcon defPGIcon=new ImageIcon("img\\진영파괴이미지.jpg");//방어필살기아이콘 
	static JButton defPGchoice1, defPGchoice2;
	static JPanel defPan1=new JPanel();
	static JPanel defPan2=new JPanel();
	static ImageIcon jypgLine=new ImageIcon("img\\진영파괴이미지.jpg");//책략필살기 버튼을 누르면 나오는 진영파괴버튼
	static ImageIcon jjgs=new ImageIcon("img\\적진기습이미지.jpg");//방어필살기 버튼을 누르면 나오는 적진기습버튼 
	static JButton[] jypgChoice=new JButton[6]; 
	static JPanel jypgPan1=new JPanel();
	static JPanel jypgPan2=new JPanel();
	static ImageIcon furyEndIcon=new ImageIcon("img\\궁극기소진아이콘.png");  
	static JButton[][] furyEndBtn=new JButton[2][3];
	static ImageIcon regameIcon=new ImageIcon("img\\게임끝-재도전.jpg");
	static ImageIcon gameEndIcon=new ImageIcon("img\\게임끝-나가기.jpg");
	
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
		
	static ImageIcon bcIcon0=new ImageIcon("img\\빙고체크-위.png"); //위촉오 빙고체크 이미지
	static ImageIcon bcIcon1=new ImageIcon("img\\빙고체크-촉.png");
	static ImageIcon bcIcon2=new ImageIcon("img\\빙고체크-오.png");
	static ImageIcon enemyIcon=new ImageIcon("img\\빙고판-상대.png");
	
	static boolean bAttCheck1 = false; //true=스킬사용가능, false=사용불가
	static boolean bDefCheck1 = false;
	static boolean bTrcikCheck1 = false; 
	
	static boolean bAttCheck2 = false;
	static boolean bDefCheck2 = false;
	static boolean bTrcikCheck2 = false;
	
	static boolean goongUsable1[]=new boolean[3]; //궁극기 사용 불린
	static boolean goongUsable2[]=new boolean[3];
	
	static boolean[][] panCheck1 = new boolean[3][25]; // 공격기술(락)을 쓴 빙고버튼(판)을 확인하기위한 변수
	static boolean[][] panCheck2 = new boolean[3][25]; //panCheck1이 내가 상대판에 체크한것, 2가 상대가 나한테 체크한 것
	
	static JButton regame;
	static JButton gameEnd;
	/////////빙고버튼설정
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
		ImageIcon m1=new ImageIcon("img\\빙고판-상대.png");
		a2[b][a-c]= new JButton(m1);
		e.add(a2[b][a-c]);
		// 버튼에 아이콘 사이즈 맞추기 
		a2[b][a-c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a-c].setBorderPainted(false);
		a2[b][a-c].setContentAreaFilled(false);
		a2[b][a-c].setFocusPainted(false);
	}
	///////////상태창
	public void youStateWindow(){	
		youBtnAtt = new JButton(attIcon);
		youBtnDef = new JButton(defIcon);
		youBtnTrick = new JButton(trickIcon);
		youBtnAvatar = new JButton(youAvatarIcon);
		youLaAtt = new JLabel("x0");
		youLaDef = new JLabel("x0");
		youLaTrick = new JLabel("x0");
		youLaTactic = new JLabel("전술명령x"+GameProcess.skillChance2);
		youLaCommand = new JLabel("지휘권x"+GameProcess.bingoCheckChance2);
		youLaNickname = new JLabel("your아이디");
		youLaNickname.setFont(new Font("궁서체",Font.PLAIN,35));

		JPanel p = new JPanel();
		p.add(youLaNickname);
		p.setBounds(920, 30, 240, 58);
		p.setOpaque(false);
		
		youBtnAtt.setBounds(903, 104, 60, 60);
		youLaAtt.setBounds(963, 109, 100, 60);
		youLaAtt.setFont(new Font("궁서체",Font.BOLD,35));
		
		youBtnDef.setBounds(903, 164, 60, 60);
		youLaDef.setBounds(963, 169, 100, 60);
		youLaDef.setFont(new Font("궁서체",Font.BOLD,35));
		
		youBtnTrick.setBounds(903, 224, 60, 60);
		youLaTrick.setBounds(963, 229, 100, 60);
		youLaTrick.setFont(new Font("궁서체",Font.BOLD,35));
		
		youLaTactic.setBounds(903, 307, 160, 40);
		youLaTactic.setFont(new Font("궁서체", Font.BOLD,20));
		
		youLaCommand.setBounds(903, 347, 160, 40);
		youLaCommand.setFont(new Font("궁서체", Font.BOLD,20));
		
		youBtnAvatar.setBounds(1024,102,157,190);
		youBtnAvatar.setPreferredSize(new Dimension(youAvatarIcon.getIconWidth(), youAvatarIcon.getIconHeight()));
		imageSetting(youBtnAvatar);
		
		youBtnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(youBtnAtt);
		
		youBtnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(youBtnDef);
		
		youBtnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(youBtnTrick);

		add(youBtnAtt);add(youBtnDef);add(youBtnTrick);add(youBtnAvatar);
		add(youLaAtt);add(youLaDef);add(youLaTrick);
		add(youLaCommand);add(youLaTactic);add(p);
	}
	public void stateWindow(){
		btnAtt = new JButton(attIcon);
		btnDef = new JButton(defIcon);
		btnTrick = new JButton(trickIcon);
		btnAvatar = new JButton(avatarIcon);
		
		laAtt = new JLabel("x0");
		laDef = new JLabel("x0");
		laTrick = new JLabel("x0");
		laTactic = new JLabel("전술명령x"+GameProcess.skillChance1);
		laCommand = new JLabel("지휘권x"+GameProcess.bingoCheckChance1);
		laNickname = new JLabel("아이디");
		laNickname.setFont(new Font("궁서체",Font.PLAIN,35));
		
		JPanel p = new JPanel();
		p.add(laNickname);
		p.setBounds(920, 539, 240, 58);
		p.setOpaque(false);
		
		btnAvatar.setBounds(1024,607,157,190);
		btnAvatar.setPreferredSize(new Dimension(avatarIcon.getIconWidth(), avatarIcon.getIconHeight()));
		imageSetting(btnAvatar);
		
		btnAtt.setBounds(903, 613, 60, 60);
		laAtt.setBounds(963, 618, 100, 60);
		laAtt.setFont(new Font("궁서체",Font.BOLD,35));
		
		btnDef.setBounds(903, 673, 60, 60);
		laDef.setBounds(963, 678, 100, 60);
		laDef.setFont(new Font("궁서체",Font.BOLD,35));
		
		btnTrick.setBounds(903, 733, 60, 60);
		laTrick.setBounds(963, 738, 100, 60);
		laTrick.setFont(new Font("궁서체",Font.BOLD,35));
		
		laTactic.setBounds(903, 815, 160, 40);
		laTactic.setFont(new Font("궁서체", Font.BOLD,20));
		
		laCommand.setBounds(903, 855, 160, 40);
		laCommand.setFont(new Font("궁서체", Font.BOLD,20));
		
		btnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(btnAtt);
		
		btnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(btnDef);
		
		btnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(btnTrick);

		add(btnAtt);add(btnDef);add(btnTrick);add(btnAvatar);
		add(laAtt);add(laDef);add(laTrick);
		add(laTactic);add(laCommand);add(p);
	}
	///////////////////////생성자
	GameLayout()
	{	
		//시간제한 바 추가  
		timer=new JProgressBar();  
		add(timer);
		timer.setBackground(Color.WHITE);
		timer.setMinimum(0);
		timer.setMaximum(100);
		timer.setStringPainted(true);
		timer.setFont(new Font("맑은고딕",Font.BOLD,16));
		timer.setBounds(888, 422, 210, 25);
		//턴 종료버튼 추가  
		add(timeOut);  
		timeOut.setCursor(cur);  
		timeOut.setBounds(886, 449, 212, 55);  
		timeOut.setIcon(timeImg);  
		timeOut.setRolloverIcon(timeImg2);  
		timeOut.setBorderPainted(false);//버튼 경계선 제거  
		timeOut.setContentAreaFilled(false); //선택했던 버튼 표시 제거  
		timeOut.setFocusPainted(false); //버튼영역 배경 제거  
		timeOut.setPressedIcon(timeImg2);  
		  
		//항복(나가기) 버튼 추가  
		add(exit);  
		exit.setCursor(cur);  
		exit.setBounds(1106, 420, exitImg.getIconWidth(), exitImg.getIconHeight());  
		exit.setIcon(exitImg);  
		exit.setRolloverIcon(exitImg2);  
		exit.setBorderPainted(false);//버튼 경계선 제거  
		exit.setContentAreaFilled(false); //선택했던 버튼 표시 제거  
		exit.setFocusPainted(false); //버튼영역 배경 제거  
		exit.setPressedIcon(exitImg2);  

		for(int i=0;i<3;i++) //궁극기 초기화
		{
			goongUsable1[i]=true;
			goongUsable2[i]=true;
		}
		//진영파괴 버튼 설정
		for(int i=0; i<6;i++) 
		{
			if(i<3)
			{
				jypgChoice[i]=new JButton(jypgLine);
				jypgChoice[i].setContentAreaFilled(false);
				jypgChoice[i].addActionListener(this);
				jypgChoice[i].setBorderPainted(false); //버튼 경계선 제거
				jypgPan2.add(jypgChoice[i]); //플레이어2 판에 배치 (choice 0,1,2)
				jypgChoice[i].setCursor(cur);
			}
			if(i>=3)
			{
				jypgChoice[i]=new JButton(jypgLine);
				jypgChoice[i].setContentAreaFilled(false);
				jypgChoice[i].addActionListener(this);
				jypgChoice[i].setBorderPainted(false); //버튼 경계선 제거
				jypgPan1.add(jypgChoice[i]);//플레이어1 판에 배치 (choice 3,4,5)
				jypgChoice[i].setCursor(cur);
			}
		}
		
		defPGchoice2=new JButton(jjgs); //방어필살기버튼 세팅
		defPGchoice2.setContentAreaFilled(false);
		defPGchoice2.addActionListener(this);
		defPGchoice2.setBorderPainted(false);
		defPan2.add(defPGchoice2);
		
		defPGchoice1=new JButton(jjgs);
		defPGchoice1.setContentAreaFilled(false);
		defPGchoice1.addActionListener(this);
		defPGchoice1.setBorderPainted(false);
		defPan1.add(defPGchoice1);
		
		defPan1.setLayout(new FlowLayout(FlowLayout.CENTER));//방어필살기 배치
		defPan1.setBackground(Color.black);
		defPan1.setOpaque(false);
		defPan1.setBounds(900, 600, 113, 190);
		add(defPan1);
		defPan1.setVisible(false);
		
		defPan2.setLayout(new FlowLayout(FlowLayout.CENTER));
		defPan2.setBackground(Color.black);
		defPan2.setOpaque(false);
		defPan2.setBounds(900, 97, 113, 190);
		add(defPan2);
		defPan2.setVisible(false);
		
		FlowLayout jypg=new FlowLayout(FlowLayout.LEFT,0,0);//진영파괴 책략필살기 레이아웃
		jypgPan1.setLayout(jypg);
		jypgPan1.setBackground(null);
		jypgPan1.setOpaque(false);
		jypgPan1.setVisible(true);
		jypgPan1.setBounds(32, 528, 895, 110);
		add(jypgPan1);
		jypgPan1.setVisible(false);
		
		jypgPan2.setLayout(jypg);
		jypgPan2.setBackground(null);
		jypgPan2.setOpaque(false);
		jypgPan2.setVisible(true);
		jypgPan2.setBounds(32, 23, 895, 110);
		add(jypgPan2);
		jypgPan2.setVisible(false);
		
		Color RED=new Color(255,0,0);
		Color GREEN=new Color(0,147,0);
		Color PURPLE=new Color(95,0,255); //BLUE 명칭을 보라색 PURPLE로 변경-HJ
		Color[] color={RED,GREEN,PURPLE}; //BLUE 명칭을 보라색 PURPLE로 변경-HJ
		int[] xVal={130,413,688};
		int[] xVal2={245,528,803};
		
		//빙고스코어 아이콘 배치
		for(int i=0; i<6; i++)
		{	
			bingoScorePan[i]=new JPanel();
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
		String[] goong={"img\\스킬아이콘-공격필살기.png",
				"img\\스킬아이콘-방어필살기.png",
				"img\\스킬아이콘-책략필살기.png"};
		
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				fury[i][j]=new JButton(new ImageIcon(goong[j]));
				furyEndBtn[i][j]=new JButton(furyEndIcon);
				add(fury[i][j]);
				add(furyEndBtn[i][j]);
				fury[i][j].setBounds(xVal2[j]+imageX, i*508+74, 60, 60);
				fury[i][j].setBorderPainted(false); //버튼 경계선 제거
				fury[i][j].setContentAreaFilled(false); //선택했던 버튼 표시 제거
				fury[i][j].setFocusPainted(false); //버튼영역 배경 제거
				fury[i][j].setEnabled(false);
				furyEndBtn[i][j].setBounds(xVal2[j]+imageX, i*508+74, 60, 60);
				furyEndBtn[i][j].setBorderPainted(false); //버튼 경계선 제거
				furyEndBtn[i][j].setContentAreaFilled(false); //선택했던 버튼 표시 제거
				furyEndBtn[i][j].setFocusPainted(false); //버튼영역 배경 제거
				furyEndBtn[i][j].setVisible(false);
				fury[i][j].setCursor(cur);
			}
		}
		for(int i=0; i<2;i++) //필살기 프로그레스바
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
		//게임종료시 나오는 버튼
		regame=new JButton(regameIcon);
		gameEnd=new JButton(gameEndIcon);
		imageSetting(regame);imageSetting(gameEnd);
		add(regame); add(gameEnd);
		regame.setVisible(false);gameEnd.setVisible(false);
		// 인게임 이미지
		pan2=Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		pan1=Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		bg=Toolkit.getDefaultToolkit().getImage("img\\인게임배경.jpg");
		vs=Toolkit.getDefaultToolkit().getImage("img\\vs.png");
		stateImage=Toolkit.getDefaultToolkit().getImage("img\\상태창.png");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격.jpg");
		defenseSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어.jpg");
		strategySkillNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략.jpg");
		attackFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격필살기.jpg");
		defenseFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어필살기.jpg");
		strategyFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략필살기.jpg");
		attackSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격.png");
		attackFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격필살기.png");
		defenseSkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어.png");
		defenseFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어필살기.png");
		strategySkillImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략.png");
		strategyFinishImage=Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략필살기.png");
		won=Toolkit.getDefaultToolkit().getImage("img\\승패-승리.png");
		lose=Toolkit.getDefaultToolkit().getImage("img\\승패-패배.png");
		//이미지,버튼 등 배치
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
		regame.setBounds(485,520,230,84);
		gameEnd.setBounds(485,620,230,84);
		Rand();//번호 섞기
		
		p.setOpaque(false);p1.setOpaque(false);p2.setOpaque(false);p3.setOpaque(false);
		pp.setOpaque(false);pp1.setOpaque(false);pp2.setOpaque(false);pp3.setOpaque(false);
		
		stateWindow();//상태창
		youStateWindow();
		
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
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				fury[i][j].setCursor(cur);
				fury[i][j].addActionListener(this);
			}
		}
		for(int i=0; i<3;i++) //장수 얼굴 버튼 액션리스너
		{
			ChoiceNation.jangSu1[i].addActionListener(this);
			ChoiceNation.jangSu2[i].addActionListener(this);
		}
		btnAtt.addActionListener(this);
		btnDef.addActionListener(this);
		btnTrick.addActionListener(this);
		btnAtt.setCursor(cur);
		btnDef.setCursor(cur);
		btnTrick.setCursor(cur);
		
		regame.addActionListener(this);//재도전
		gameEnd.addActionListener(this);//나가기
		regame.setCursor(cur);
		gameEnd.setCursor(cur);
		
		timeOut.addActionListener(this); //턴종료 버튼 
		addKeyListener(this);
		setFocusable(true);
	}
	@Override  
	public synchronized void addMouseListener(MouseListener l) {  
		// TODO Auto-generated method stub  
		super.addMouseListener(l);  
	}  
	public void imageSetting(JButton btn)
	{
		btn.setBorderPainted(false); //버튼 경계선 제거
		btn.setContentAreaFilled(false); //선택했던 버튼 표시 제거
		btn.setFocusPainted(false); //버튼영역 배경 제거
		btn.setOpaque(false);
	}
	//배경을 제외한 모드 이미지 숨기기/보이기
	public void imageVisibleFalse()
	{
		 btnAtt.setVisible(false);  
		 btnDef.setVisible(false);         
		 btnTrick.setVisible(false);     
		 btnAvatar.setVisible(false);
		 youBtnAtt.setVisible(false);
		 youBtnDef.setVisible(false);                  
		 youBtnTrick.setVisible(false);                
		 youBtnAvatar.setVisible(false);               
		 laAtt.setVisible(false);  
		 laDef.setVisible(false);      
		 laTrick.setVisible(false);  
		 laNickname.setVisible(false);
		 laTactic.setVisible(false);
		 laCommand.setVisible(false);
		 youLaAtt.setVisible(false);         
		 youLaDef.setVisible(false);                    
		 youLaTrick.setVisible(false);                  
		 youLaNickname.setVisible(false);               
		 youLaTactic.setVisible(false);
		 youLaCommand.setVisible(false);
		 for(int i=0; i<2; i++)
		 {
			 for(int j=0; j<3; j++)
			 {
				 gauge[i][j].setVisible(false);
				 fury[i][j].setVisible(false);
				 furyEndBtn[i][j].setVisible(false);
			 }
		 }
		 for(int i=0; i<6; i++)
		 {
			 jypgChoice[i].setVisible(false);
			 for(int j=0; j<5; j++)
			 {
				 bingoScore[i][j].setVisible(false);
			 }
		 }
		 defPGchoice1.setVisible(false);
		 defPGchoice2.setVisible(false);
		 for(int i=0; i<3;i++) //장수 얼굴 버튼 액션리스너
		 {
			 ChoiceNation.jangSu1[i].setVisible(false);
			 ChoiceNation.jangSu2[i].setVisible(false);
		 }
		 p.setVisible(false);
		 pp.setVisible(false);
		 imageX+=1200;
		 repaint();
	}
	public void imageVisibleTrue()
	{
		 btnAtt.setVisible(true);
		 btnDef.setVisible(true);         
		 btnTrick.setVisible(true);     
		 btnAvatar.setVisible(true);
		 youBtnAtt.setVisible(true);
		 youBtnDef.setVisible(true);                  
		 youBtnTrick.setVisible(true);                
		 youBtnAvatar.setVisible(true);               
		 laAtt.setVisible(true);  
		 laDef.setVisible(true);      
		 laTrick.setVisible(true);  
		 laNickname.setVisible(true);
		 laTactic.setVisible(true);
		 laCommand.setVisible(true);
		 youLaAtt.setVisible(true);         
		 youLaDef.setVisible(true);                    
		 youLaTrick.setVisible(true);                  
		 youLaNickname.setVisible(true);               
		 youLaTactic.setVisible(true);
		 youLaCommand.setVisible(true);
		 for(int i=0; i<2; i++)
		 {
			 for(int j=0; j<3; j++)
			 {
				 gauge[i][j].setVisible(true);
				 fury[i][j].setVisible(true);
			 }
		 }
		 for(int i=0; i<6; i++)
		 {
			 jypgChoice[i].setVisible(true);
			 for(int j=0; j<5; j++)
			 {
				 bingoScore[i][j].setVisible(true);
			 }
		 }
		 defPGchoice1.setVisible(true);
		 defPGchoice2.setVisible(true);
		 for(int i=0; i<3;i++) //장수 얼굴 버튼 액션리스너
		 {
			 ChoiceNation.jangSu1[i].setVisible(true);
			 ChoiceNation.jangSu2[i].setVisible(true);
		 }
		 p.setVisible(true);
		 pp.setVisible(true);
		 imageX-=1200;
		 repaint();
	}	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343+imageX, 423, vs.getWidth(this),80,this);
		//빙고틀
		g.drawImage(pan1, 4+imageX, 492, 895, 452, this);
		g.drawImage(pan2, 4+imageX, -10, 895, 446, this);
		//상태창
		g.drawImage(stateImage, 874+imageX, 0, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
		g.drawImage(stateImage, 874+imageX, 505, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
		//스킬설명
		g.drawImage(attackSkillNotice, aNoticeX, 398, 582,130,this);
		g.drawImage(defenseSkillNotice, dNoticeX, 398, 582,130,this);
		g.drawImage(strategySkillNotice, sNoticeX, 398, 582,130,this);
		g.drawImage(attackFinishNotice, aFNoticeX, 398, 582,130,this);
		g.drawImage(defenseFinishNotice, dFNoticeX, 398, 582,130,this);
		g.drawImage(strategyFinishNotice, sFNoticeX, 398, 582,130,this);
		//스킬이미지
		g.drawImage(attackSkillImage, aImageX, 280, getWidth(), attackSkillImage.getHeight(this), this);
		g.drawImage(attackFinishImage, aFImageX, 280, getWidth(), attackFinishImage.getHeight(this), this);
		g.drawImage(defenseSkillImage, dImageX, 280, getWidth(), defenseSkillImage.getHeight(this), this);
		g.drawImage(defenseFinishImage, dFImageX, 280, getWidth(), defenseFinishImage.getHeight(this), this);
		g.drawImage(strategySkillImage, sImageX, 280, getWidth(), strategySkillImage.getHeight(this), this);
		g.drawImage(strategyFinishImage, sFImageX, 280, getWidth(), strategyFinishImage.getHeight(this), this);
		//승패
		g.drawImage(won, wonX, 390, getWidth(), won.getHeight(this), this);
		g.drawImage(lose, loseX, 390, getWidth(), lose.getHeight(this), this);
	}
	//이미지 정리

	
	public void laSetting(JLabel command, JLabel att, JLabel def, JLabel trick)//버튼을 클릭할 때 텍스트를 바꿔주는 메소드
	{
		command.setText("지휘권x"+String.valueOf(GameProcess.bingoCheckChance1));
		att.setText("x"+String.valueOf(GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1));
		def.setText("x"+String.valueOf(GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1));
		trick.setText("x"+String.valueOf(GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//////////빙고 체크(체크된 빙고가 아닐 때+스킬아이템을 클릭하지 않았을 때)
		if(bAttCheck1==false&&bDefCheck1==false&&bTrcikCheck1==false
			&&bAttCheck2==false&&bDefCheck2==false&&bTrcikCheck2==false)
		{
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==0)//진영선택 : 위vs위
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]가 체크 안된것만 체크 가능,본인 차례일 때 체크 가능
						if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&&panCheck2[i][j]==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
									,GameProcess.bingo1,GameProcess.bingo2,a1,a2
									,bcIcon0,bcIcon0);
							GameProcess.bingoCheckChance1--;
							laCommand.setText("지휘권x"+String.valueOf(GameProcess.bingoCheckChance1));
							laSetting(laCommand,laAtt,laDef,laTrick);
						}
						else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
									,GameProcess.bingo2,GameProcess.bingo1,a2,a1
									,bcIcon0,bcIcon0);
						}
					}
				}
			}
			else if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==1)//진영선택 : 위vs촉
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
									,GameProcess.bingo1,GameProcess.bingo2,a1,a2
									,bcIcon0,bcIcon1);
							GameProcess.bingoCheckChance1--;
							laSetting(laCommand,laAtt,laDef,laTrick);
						}
						else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
									,GameProcess.bingo2,GameProcess.bingo1,a2,a1
									,bcIcon1,bcIcon0);
						}
					}
				}
			}
			else if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==2)//진영선택 : 위vs오
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon0,bcIcon2);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon0);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==0)//진영선택 : 촉vs위
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon0);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon0,bcIcon1);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==1)//진영선택 : 촉vs촉
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon1);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon1,bcIcon1);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==2)//진영선택 : 촉vs오
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon2);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon1);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==0)//진영선택 : 오vs위
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon0);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo2[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon0,bcIcon2);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==1)//진영선택 : 오vs촉
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon1);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon1,bcIcon2);
							}
						}
					}
				}
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==2)//진영선택 : 오vs오
				{
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<25; j++)
						{
							if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&panCheck2[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon2);
								GameProcess.bingoCheckChance1--;
								laSetting(laCommand,laAtt,laDef,laTrick);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==false&panCheck1[i][j]==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon2);
							}
						}
					}
				}
		}
			
		////////////스킬버튼
		if(GameProcess.playerTurn==true)
		{
			if(e.getSource()==btnAtt && GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1>0)//플레이어1 공격 스킬 버튼
			{
				bAttCheck1 = true; //스킬사용 가능하게 true
				aNoticeX-=1190; //스킬설명 가져오기(1200으로 화면 밖에 있던 것이 -1190 해서 화면에 나옴)
				repaint(); //다시 화면에 그려줌
			}
			else if(e.getSource()==btnDef && GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1>0)//방어스킬
			{
				int k=0;
				for(int i=0;i<3;i++){
					for(int j=0;j<25;j++){
						if(panCheck1[i][j])
							k++;   
					}
				}
				if(k==0){ //만약 락 걸린 빙고판이 없으면, 아이템 버튼이 실행되지 않음
					JOptionPane.showMessageDialog(this, "방해받은 지역이 없습니다.");
					return;
				}
				bDefCheck1 = true;
				dNoticeX-=1190;
				repaint();
			}
			else if(e.getSource()==btnTrick && GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1>0)//책략스킬
			{
				bTrcikCheck1=true;
				sNoticeX-=1190;
				repaint();
			}
			else if(e.getSource()==fury[1][0]) //공격필살기 버튼
			{
				new AFImageThread().start();
				GameProcess.bingoCheckChance1++;//공격기회+1,아이템사용기회+1
				GameProcess.skillChance1++;
				goongUsable1[0]=false;
				fury[1][0].setEnabled(false);
				gauge[1][0].setBackground(Color.DARK_GRAY);
				gauge[1][0].setString("궁극기소진");
				laTactic.setText("전술명령x"+String.valueOf(GameProcess.skillChance1));
				laCommand.setText("지휘권x"+String.valueOf(GameProcess.bingoCheckChance1));
			}
			else if(e.getSource()==fury[1][1]) //방어필살기 버튼
			{
				int k=0;
				if(GameProcess.numOfBingo2[0]+GameProcess.usingAttackSkill2<=0
						&&GameProcess.numOfBingo2[1]+GameProcess.usingDefenseSkill2<=0
						&&GameProcess.numOfBingo2[2]+GameProcess.usingStrategySkill2<=0)
				{ //만약 상대 아이템이 없으면, 필살기 버튼이 실행되지 않음
					JOptionPane.showMessageDialog(this, "파괴할 전술병기가 없습니다.");
					return;
				}
				dFNoticeX-=1190;
				if(goongUsable1[1])
				{  
					defPan2.setVisible(true);  
				}  
				repaint();
			}
			else if(e.getSource()==fury[1][2]) //책략필살기 버튼
			{
				if(goongUsable1[2])
				{
					sFNoticeX-=1190;
					jypgPan2.setVisible(true);
					repaint();
				}
				
			}
		}
		
		//플레이어2
		if(GameProcess.playerTurn==false)
		{
			if(e.getSource()==youBtnAtt)//플레이어2 공격 스킬 버튼
			{
				
			}
			else if(e.getSource()==youBtnDef)//방어스킬
			{
				
			}
			else if(e.getSource()==youBtnTrick)//책략스킬
			{
				
			}
			else if(e.getSource()==fury[0][0]) //플레이어2 공격필살기 버튼
			{
				
			}
			else if(e.getSource()==fury[0][1]) //방어필살기 버튼
			{
				
			}
			else if(e.getSource()==fury[0][2]) //책략필살기 버튼
			{
					
			}
		}
		
		//공격,방어,책략스킬
		for(int i=0;i<3;i++){
			for(int j=0;j<25;j++){
				if(e.getSource()==a2[i][j]&&bAttCheck1&&!GameProcess.bingo2[i][j]&&!panCheck1[i][j]){//공격스킬
					new AImageThread().start();
					a2[i][j].setIcon(new ImageIcon("img\\빙고체크-락.png"));
					GameProcess.usingAttackSkill1--;
					useAtt--;
					laAtt.setText("x"+String.valueOf(GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1));
					bAttCheck1 = false;
					GameProcess.skillChance1--;
					laTactic.setText("전술명령x"+String.valueOf(GameProcess.skillChance1));
					panCheck1[i][j] = true; // 공격한 곳 확인.
					repaint();
				}else if(e.getSource()==a2[i][j]&&bDefCheck1&&panCheck1[i][j]){//방어스킬
					new DImageThread().start();
					GameProcess.usingDefenseSkill1--;
					useDef--;
					laDef.setText("x"+String.valueOf(GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1));
					if(GameProcess.bingo2[i][j]==false){
						a2[i][j].setIcon(new ImageIcon("img\\"+(GameProcess.p2Board[i][j])+".png"));
					}else if(GameProcess.bingo2[i][j]){
						if(ChoiceNation.chosenNation2==0)
							a2[i][j].setIcon(new ImageIcon("img\\빙고체크-위.png"));
						else if(ChoiceNation.chosenNation2==1)
							a2[i][j].setIcon(new ImageIcon("img\\빙고체크-촉.png"));
						else if(ChoiceNation.chosenNation2==2)
							a2[i][j].setIcon(new ImageIcon("img\\빙고체크-오.png"));
					}
					
					bDefCheck1 = false;
					GameProcess.skillChance1--;
					laTactic.setText("전술명령x"+String.valueOf(GameProcess.skillChance1));
					panCheck1[i][j]=false;
					repaint();
				}
				else if(e.getSource()==a2[i][j]&&bTrcikCheck1&&!GameProcess.bingo2[i][j]&&!panCheck1[i][j]) 
				{
					new SImageThread().start();
					a2[i][j].setIcon(new ImageIcon("img\\"+GameProcess.numArr2[25*i+j]+".png"));
					GameProcess.usingStrategySkill1--;
					useTrick--;
					laTrick.setText("x"+String.valueOf(GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1));
					bTrcikCheck1=false;
					repaint();
				}
			}
		}
		//수비필살기 클릭 후, 상대판 버튼 클릭
		if(e.getSource()==defPGchoice2){
			new DFImageThread().start();
				
			GameProcess.usingAttackSkill1-=GameProcess.numOfBingo1[0];
			GameProcess.usingDefenseSkill1-=GameProcess.numOfBingo1[1];
			GameProcess.usingStrategySkill1-=GameProcess.numOfBingo1[2];
			GameProcess.usingAttackSkill1-=useAtt;
			GameProcess.usingDefenseSkill1-=useDef;
			
			laAtt.setText("x"+String.valueOf(GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1));
			laDef.setText("x"+String.valueOf(GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1));
			laTrick.setText("x"+String.valueOf(GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1));
			
			goongUsable1[1]=false;
			fury[1][1].setEnabled(false);
			fury[1][1].setVisible(false);
			gauge[1][1].setBackground(Color.DARK_GRAY);
			gauge[1][1].setString("궁극기소진");
			defPan2.setVisible(false);
			furyEndBtn[1][1].setVisible(true);
		}
		//책략필살기 클릭 후, 상대 판 진영파괴 버튼 클릭
		for(int i=0; i<3; i++)
		{
			if(e.getSource()==jypgChoice[i])
			{
				new SFImageThread().start();
				GameProcess.jypg(0,i);
				goongUsable1[2]=false;
				fury[1][2].setEnabled(false);
				fury[1][2].setVisible(false);
				gauge[1][2].setBackground(Color.DARK_GRAY);
				gauge[1][2].setString("궁극기소진");
				jypgPan2.setVisible(false);
				furyEndBtn[1][2].setVisible(true);
				GameProcess.skillChance1--;
				laTactic.setText("전술명령x"+String.valueOf(GameProcess.skillChance1));
				repaint();
			}
		}
		for(int i=0; i<3; i++)
		{
			if(e.getSource()==jypgChoice[i+3])
			{
				GameProcess.jypg(1,i);
				goongUsable2[2]=false;
				fury[0][2].setEnabled(false);
				fury[0][2].setVisible(false);
				gauge[0][2].setBackground(Color.DARK_GRAY);
				gauge[0][2].setString("궁극기소진");
				jypgPan1.setVisible(false);
				furyEndBtn[0][2].setVisible(true);
				GameProcess.skillChance2--;
				laTactic.setText("전술명령x"+String.valueOf(GameProcess.skillChance2));
				repaint();
			}
		}
		if(e.getSource()==timeOut)//턴턴턴  
		{  
			ClientMainForm.t1.interrupt();  
			ClientMainForm.t1=new TimeLimit();  
			ClientMainForm.t1.start();  
		}  
		//재도전
		else if(e.getSource()==regame)
		{
			
		}
		
		requestFocus();
	}	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			cig.setVisible(true);
			cig.tf.requestFocus();
			break;

		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class AImageThread extends Thread
	{
		public void run(){
			GameLayout.aNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.aImageX>=0)
			{
				try
				{
					GameLayout.aImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.aImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	class DImageThread extends Thread
	{
		public void run(){
			GameLayout.dNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.dImageX>=0)
			{
				try
				{
					GameLayout.dImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.dImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	class SImageThread extends Thread
	{
		public void run(){
			GameLayout.sNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.sImageX>=0)
			{
				try
				{
					GameLayout.sImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.sImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	class AFImageThread extends Thread
	{
		public void run(){
			GameLayout.aFNoticeX-=1190; //게임설명 가져오기
			repaint();
			try
			{	
				Thread.sleep(1500); //2초 후
			}catch(Exception ex){}
			GameLayout.aFNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.aFImageX>=0)
			{
				try
				{
					GameLayout.aFImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.aFImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	class DFImageThread extends Thread
	{
		public void run(){
			GameLayout.dFNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.dFImageX>=0)
			{
				try
				{
					GameLayout.dFImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.dFImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	class SFImageThread extends Thread
	{
		public void run(){
			GameLayout.sFNoticeX+=1190; //게임설명은 없어지고
			repaint();
			imageVisibleFalse(); //배경을 제외한 모든 오브젝트가 사라진 뒤
			while(GameLayout.sFImageX>=0)
			{
				try
				{
					GameLayout.sFImageX-=3; //스킬이미지가 날아옴
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2초간 게임이미지 멈춰있기
			}catch(Exception ex){}
			GameLayout.sFImageX=1200; //게임이미지 없애기
			imageVisibleTrue();
			if(!goongUsable1[0])
			{
				furyEndBtn[1][0].setVisible(true);
				fury[1][0].setVisible(false);
			}
			if(!goongUsable1[1])
			{
				furyEndBtn[1][1].setVisible(true);
				fury[1][1].setVisible(false);
			}
			if(!goongUsable1[2])
			{
				furyEndBtn[1][2].setVisible(true);
				fury[1][2].setVisible(false);
			}
			repaint();
		}
	}
	public static class TimeLimit extends Thread 
	{	 
		boolean restart=true; 
		Random ran=new Random(); 
		int cnt=0; 
		int[] rgb=new int[3]; 
		int percent=0; //시간제한바를 채우는 퍼센트 (20초:100퍼센트 즉, 0.2초: 1퍼센트) 
		double residueTime=20; //남은시간표시 (초기값:20초) 
		public void run() 
		{
			timeRun=true; 
			try  
			{ 	 
				while(timeRun)//timeRun이 false일때 멈춤 (나가기,항복,턴종료) 
				{	   
				  cnt++; 
				  percent++; 
				  if(percent>100)//100퍼센트가 되면 다시 0으로 초기화 
				  { 
					  percent=0; 
					  residueTime=20; 
					  colorInt=0; 
					  rgb[0]=colorInt; 
					  rgb[1]=255; 
				  }	 
				  Thread.sleep(200); 
				  colorInt=(int)(Math.ceil(2.55*(percent))); 
				  residueTime-=0.2; 
				  if(residueTime<0.2) 
					  residueTime=0; 
				  String rt =String.valueOf(residueTime); 
				  if(rt.length()>=4) 
				  { 
					  String rr=rt.substring(0,4); 
					  timer.setString("制限時間:"+rr); 
				  } 
				  else 
				  { 
					  String rr=rt; 
					  timer.setString("制限時間:"+rr); 
				  } 
				  rgb[0]=colorInt; 
				  rgb[1]=255-colorInt; 
				  timer.setValue(percent); 
				  timer.setForeground(new Color(rgb[0],rgb[1],rgb[2])); 
				}	 
			} catch (Exception e) { 
				// TODO: handle exception 
				e.getMessage(); 
			} 

		} 
	} 

}