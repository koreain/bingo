package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.*;
import sun.net.www.content.image.jpeg;
import com.sist.client.GameLayout.TimeLimit;

public class GameLayout extends JPanel implements KeyListener {
	/* >>>>>>>>>>>>>>>>>>>>>>>>>>변수선언<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	// 시간제한 타이머
	static JProgressBar timer = new JProgressBar(); // 시간제한바
	// static boolean timeRun=true;
	static int colorInt = 0;

	// 턴종료 버튼
	static JButton timeOut = new JButton();
	static ImageIcon timeImg = new ImageIcon("img\\턴종료_기본.png");
	static ImageIcon timeImg2 = new ImageIcon("img\\턴종료_커서.png");
	// 항복 버튼
	static JButton exit = new JButton();
	static ImageIcon exitImg = new ImageIcon("img\\항복.png");
	static ImageIcon exitImg2 = new ImageIcon("img\\항복_커서.png");
	// 배경화면
	Image bg; // 추상클래스 abstract!! 단독으로 메모리 할당을 못한다.
	Image vs; // 가운데 vs 텍스트
	Image pan1; // 빙고틀 플레이어1
	Image pan2; // 빙고틀 플레이어2
	Image stateImage; // 상태창 이미지
	// 스킬설명이미지들
	Image attackSkillNotice, attackFinishNotice;
	Image defenseSkillNotice, defenseFinishNotice;
	Image strategySkillNotice, strategyFinishNotice;
	// 스킬이미지들
	Image attackSkillImage, attackFinishImage, attackFPlus;
	Image defenseSkillImage, defenseFinishImage;
	Image strategySkillImage, strategyFinishImage;
	// 승패이미지
	Image won, lose;
	// 이미지 좌표값들
	static int aNoticeX = 1200, aFNoticeX = 1200;
	static int dNoticeX = 1200, dFNoticeX = 1200;
	static int sNoticeX = 1200, sFNoticeX = 1200;
	static int aImageX = 1200, aFImageX = 1200, plusY = 1000;
	static int dImageX = 1200, dFImageX = 1200;
	static int sImageX = 1200, sFImageX = 1200;
	static int imageX = 0, wonX = 1200, loseX = 1200;

	ChatInGame cig = new ChatInGame();
	// 마우스 커서가 버튼에 올라갔을때 손모양으로 바뀌게
	static Cursor cur = new Cursor(Cursor.HAND_CURSOR);

	// 빙고판
	static JButton[][] a1 = new JButton[3][25]; // 내 판
	static JButton[][] a2 = new JButton[3][25]; // 상대판

	// 아이템선택버튼, 아바타
	static JButton btnAtt; // 공격스킬버튼
	static JButton btnDef; // 수비스킬버튼
	static JButton btnTrick; // 책략스킬버튼
	static JButton btnAvatar; // 아바타 이미지 넣어줄 버튼

	static JButton youBtnAtt; // 상대편 버튼은 사실상 이미지 삽입용
	static JButton youBtnDef;
	static JButton youBtnTrick;
	static JButton youBtnAvatar;

	static int useAtt, useDef, useTrick;

	// 아이템 갯수 확인 및 아이디
	static JLabel laAtt; // 공격스킬 개수 확인
	static JLabel laDef; // 수비스킬 개수 확인
	static JLabel laTrick; // 책략스킬 개수 확인
	static JLabel laNickname; // 닉네임 입력하는 곳
	static JLabel laTactic, laCommand; // 전술명령, 지휘권

	static JLabel youLaAtt; // 상대방.
	static JLabel youLaDef;
	static JLabel youLaTrick;
	static JLabel youLaNickname;
	static JLabel youLaTactic, youLaCommand;

	// 플레이어 1,2 장기판별 게이지
	static JProgressBar[][] gauge = new JProgressBar[2][3];
	// 플레이어 1,2 장기판별 궁극기 활성 안내버튼
	static JButton[][] fury = new JButton[2][3]; // [0][0]~[0][2]까지 상대판 필살기버튼,
													// [1][0]~[1][2]까지 내판 필살기버튼

	// 플레이어 1,2 장기판별 빙고획득점수 확인패널
	static JButton[][] bingoScore = new JButton[6][5];
	static JPanel[] bingoScorePan = new JPanel[6];
	static ImageIcon bingo1 = new ImageIcon("img\\빙고-한줄.png");
	static ImageIcon bingo2 = new ImageIcon("img\\빙고-한줄완성.png");
	static ImageIcon attIcon = new ImageIcon("img\\스킬아이콘-공격.png");
	static ImageIcon defIcon = new ImageIcon("img\\스킬아이콘-방어.png");
	static ImageIcon trickIcon = new ImageIcon("img\\스킬아이콘-책략.png");
	static ImageIcon avatarIcon = new ImageIcon("img\\m1.jpg");
	static ImageIcon youAvatarIcon = new ImageIcon("img\\m2.jpg");
	static ImageIcon defPGIcon = new ImageIcon("img\\진영파괴이미지.jpg");// 방어필살기아이콘
	static JButton defPGchoice1, defPGchoice2;
	static JPanel defPan1 = new JPanel();
	static JPanel defPan2 = new JPanel();
	static ImageIcon jypgLine = new ImageIcon("img\\진영파괴이미지.jpg");// 책략필살기 버튼을
																	// 누르면 나오는
																	// 진영파괴버튼
	static ImageIcon jjgs = new ImageIcon("img\\적진기습이미지.jpg");// 방어필살기 버튼을 누르면
																// 나오는 적진기습버튼
	static JButton[] jypgChoice = new JButton[6]; // 0,1,2 상대진영파괴 판 선택버튼(내가
													// 누르는), 내 진영파괴 판 선택버튼(니가
													// 누르는)
	static JPanel jypgPan1 = new JPanel();
	static JPanel jypgPan2 = new JPanel();
	static ImageIcon furyEndIcon = new ImageIcon("img\\궁극기소진아이콘.png");
	static JButton[][] furyEndBtn = new JButton[2][3];
	static ImageIcon gameEndIcon = new ImageIcon("img\\게임끝-나가기.jpg");

	// 플레이어 2 빙고판 레이아웃 (상대판)
	static JPanel p = new JPanel(); // 상대의 공방전 묶는 패널(p1,p2,p3)
	static JPanel p1 = new JPanel(); // 상대의 공격장수 판
	static JPanel p2 = new JPanel(); // 상대의 방어장수 판
	static JPanel p3 = new JPanel(); // 상대의 전략장수 판

	// 플레이어 1 빙고판 레이아웃 (내판)
	static JPanel pp = new JPanel(); // 나의 공방전 묶는 패널(pp1,pp2,pp3)
	static JPanel pp1 = new JPanel(); // 나의 공격장수 판
	static JPanel pp2 = new JPanel(); // 나의 방어장수 판
	static JPanel pp3 = new JPanel(); // 나의 전략장수 판

	// 장수 캐릭터 창
	static JPanel j1 = new JPanel(); // 플레이어2 장수들 묶음패널
	static JPanel j2 = new JPanel(); // 플레이어1 장수들 묶음패널

	// 위촉오 빙고체크 이미지
	static ImageIcon bcIcon0 = new ImageIcon("img\\빙고체크-위.png");
	static ImageIcon bcIcon1 = new ImageIcon("img\\빙고체크-촉.png");
	static ImageIcon bcIcon2 = new ImageIcon("img\\빙고체크-오.png");
	static ImageIcon enemyIcon = new ImageIcon("img\\빙고판-상대.png");

	// 스킬 사용가능여부(true=스킬사용가능, false=사용불가)
	static boolean bAttCheck1 = false; // 나의 공격스킬
	static boolean bDefCheck1 = false; // 나의 방어스킬
	static boolean bTrickCheck1 = false; // 나의 전략스킬
	static boolean bDefFCheck1 = false; // 나의 방어필살기
	static boolean bTrickFCheck1 = false; // 나의 전략필살기

	static boolean bAttCheck2 = false; // 너의 공격스킬
	static boolean bDefCheck2 = false; // 너의 방어스킬
	static boolean bTrickCheck2 = false; // 너의 전략스킬
	static boolean bDefFCheck2 = false; // 너의 방어필살기
	static boolean bTrickFCheck2 = false; // 너의 전략필살기

	// 궁극기(필살기) 사용가능여부 (0번배열:공격필살기, 1번배열:방어필살기, 2번배열:전략필살기)
	static boolean goongUsable1[] = new boolean[3]; // 나의 궁극기(필살기)
	static boolean goongUsable2[] = new boolean[3]; // 너의 궁극기(필살기)

	// 공격을 당한 버튼(락을 당한 버튼)을 확인하기 위한 변수
	static boolean[][] panCheck1 = new boolean[3][25]; // 내가 ===> 상대 판에 체크한것
	static boolean[][] panCheck2 = new boolean[3][25]; // 상대가 ===> 내 판에 체크한것

	// 빙고턴표시
	static JButton bingoTurnIcon1 = new JButton(new ImageIcon("img\\턴표시.png"));
	static JButton bingoTurnIcon2 = new JButton(new ImageIcon("img\\턴표시.png"));
	// 빙고마무리
	Image endBack, boom; // 배경이미지
	static JButton endBtn;
	ImageIcon endBtnIcon = new ImageIcon("img\\마무리-버튼.png");
	static int endBackX = 1200;
	static int boomX = 1200;
	static boolean bingoEnd = false;
	// 게임 나가기 버튼
	static JButton gameEnd;

	/* >>>>>>>>>>>>>>>>>>>>>>>>>>메소드<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

	// 빙고버튼셋팅1 (빙고버튼에 이미지 넣는 메소드)
	public static void RanButton(int a, int b, int c, JPanel d, JPanel e) {
		// 내판 빙고버튼셋팅
		ImageIcon m = new ImageIcon("img\\" + GameProcess.numArr1[a] + ".png");
		a1[b][a - c] = new JButton(m);
		d.add(a1[b][a - c]);
		// 버튼에 아이콘 사이즈 맞추기
		a1[b][a - c].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
		a1[b][a - c].setBorderPainted(false); // 버튼 경계선 제거
		a1[b][a - c].setContentAreaFilled(false); // 선택했던 버튼 표시 제거
		a1[b][a - c].setFocusPainted(false); // 버튼영역 배경 제거
		a1[b][a - c].setCursor(cur); // 버튼에 마우스를 올리면 커서 모양이 손으로 바뀜

		// 상대판 빙고버튼셋팅
		ImageIcon m1 = new ImageIcon("img\\빙고판-상대.png");
		a2[b][a - c] = new JButton(m1);
		e.add(a2[b][a - c]);
		// 버튼에 아이콘 사이즈 맞추기
		a2[b][a - c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a - c].setBorderPainted(false);
		a2[b][a - c].setContentAreaFilled(false);
		a2[b][a - c].setFocusPainted(false);
		a2[b][a - c].setCursor(cur);
	}

	// 빙고버튼셋팅2 (내부 활용 메소드는 상단에 정의된 RanButton();빙고버튼셋팅1)
	public static void Rand() {
		// 1에서 75까지의 랜덤변수 담기 ==> GameProcess 클래스의 numArr1[75], numArr2[75]에 담긴다.
		GameProcess.rand(); // GameProcess 클래스 안쪽으로 들어가서~ 랜덤메소드 호출

		for (int i = 0; i < 75; i++) {
			if (i < 25) {
				// pp1(나의 공격판 패널), p1(너의 공격판 패널)
				RanButton(i, 0, 0, pp1, p1);
			} else if ((i >= 25) && (i < 50)) {
				// pp2(나의 방어판 패널), p2(너의 방어판 패널)
				RanButton(i, 1, 25, pp2, p2);
			} else { // pp3(나의 전략판 패널), p3(너의 전략판 패널)
				RanButton(i, 2, 50, pp3, p3);
			}
		}
	}

	// 너의 상태창 셋팅 메소드
	public void youStateWindow() {
		youBtnAtt = new JButton(attIcon); // 너의 공격스킬 버튼
		youBtnDef = new JButton(defIcon); // 너의 방어스킬 버튼
		youBtnTrick = new JButton(trickIcon); // 너의 전략스킬 버튼
		youBtnAvatar = new JButton(youAvatarIcon); // 너의 아바타
		youLaAtt = new JLabel("x0"); // 너의 공격아이템 개수 라벨
		youLaDef = new JLabel("x0"); // 너의 방어아이템 개수 라벨
		youLaTrick = new JLabel("x0"); // 너의 전략아이템 개수 라벨
		youLaTactic = new JLabel("전술명령x" + GameProcess.skillChance2); // 너의
																		// 스킬사용가능
																		// 횟수 라벨
		youLaCommand = new JLabel("지휘권x" + GameProcess.bingoCheckChance2); // 너의
																			// 빙고체크가능
																			// 횟수
																			// 라벨
		youLaNickname = new JLabel("your아이디"); // 너의 아이디
		youLaNickname.setFont(new Font("궁서체", Font.PLAIN, 35));

		// 너의 아이디 라벨을 패널에 추가
		JPanel p = new JPanel();
		p.add(youLaNickname);
		p.setBounds(920, 50, 240, 58);
		p.setOpaque(false);

		// 너의 상태창 구성요소 배치
		bingoTurnIcon2.setBounds(905, 30, 55, 55);// 빙고턴
		bingoTurnIcon2.setVisible(false);
		imageSetting(bingoTurnIcon2);
		// 공격
		youBtnAtt.setBounds(903, 104, 60, 60); // 공격스킬버튼
		youLaAtt.setBounds(963, 109, 100, 60); // 공격아이템 개수 라벨
		youLaAtt.setFont(new Font("궁서체", Font.BOLD, 35));
		// 방어
		youBtnDef.setBounds(903, 164, 60, 60); // 방어스킬버튼
		youLaDef.setBounds(963, 169, 100, 60); // 방어아이템 개수 라벨
		youLaDef.setFont(new Font("궁서체", Font.BOLD, 35));
		// 전략
		youBtnTrick.setBounds(903, 224, 60, 60); // 전략스킬버튼
		youLaTrick.setBounds(963, 229, 100, 60); // 전략아이템 개수 라벨
		youLaTrick.setFont(new Font("궁서체", Font.BOLD, 35));
		// 스킬사용가능 횟수 라벨
		youLaTactic.setBounds(903, 307, 160, 40);
		youLaTactic.setFont(new Font("궁서체", Font.BOLD, 20));
		// 빙고체크가능 횟수 라벨
		youLaCommand.setBounds(903, 347, 160, 40);
		youLaCommand.setFont(new Font("궁서체", Font.BOLD, 20));
		// 아바타
		youBtnAvatar.setBounds(1024, 102, 157, 190);
		youBtnAvatar.setPreferredSize(new Dimension(youAvatarIcon.getIconWidth(), youAvatarIcon.getIconHeight()));
		imageSetting(youBtnAvatar);

		// 스킬버튼 이미지 사이즈 조정

		// 공격
		youBtnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(youBtnAtt);
		// 방어
		youBtnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(youBtnDef);
		// 전략
		youBtnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(youBtnTrick);

		// 너의 상태창 구성요소 추가(아이디 제외)
		add(bingoTurnIcon2);
		add(youBtnAtt);
		add(youBtnDef);
		add(youBtnTrick);
		add(youBtnAvatar);
		add(youLaAtt);
		add(youLaDef);
		add(youLaTrick);
		add(youLaCommand);
		add(youLaTactic);
		add(p);
	}

	// 나의 상태창 셋팅 메소드
	public void stateWindow() {
		btnAtt = new JButton(attIcon); // 나의 공격스킬 버튼
		btnDef = new JButton(defIcon); // 나의 방어스킬 버튼
		btnTrick = new JButton(trickIcon); // 나의 전략스킬 버튼
		btnAvatar = new JButton(avatarIcon); // 나의 아바타
		laAtt = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1)))); // 나의
																													// 공격아이템
																													// 개수
																													// 라벨
		laDef = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1)))); // 나의
																													// 방어아이템
																													// 개수
																													// 라벨
		laTrick = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1)))); // 나의
																														// 전략아이템
																														// 개수
																														// 라벨
		laTactic = new JLabel("전술명령x" + String.valueOf(GameProcess.skillChance1)); // 나의
																					// 스킬사용가능
																					// 횟수
																					// 라벨
		laCommand = new JLabel("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1)); // 나의
																						// 빙고체크가능
																						// 횟수
																						// 라벨
		laNickname = new JLabel("아이디"); // 나의 아이디
		laNickname.setFont(new Font("궁서체", Font.PLAIN, 35));

		// 나의 아이디 라벨을 패널에 추가
		JPanel p = new JPanel();
		p.add(laNickname);
		p.setBounds(920, 539, 240, 58);
		p.setOpaque(false);

		// 나의 상태창 구성요소 배치

		bingoTurnIcon1.setBounds(905, 535, 55, 55);// 빙고턴
		bingoTurnIcon1.setVisible(false);
		imageSetting(bingoTurnIcon1);
		// 공격
		btnAtt.setBounds(903, 613, 60, 60); // 공격스킬버튼
		laAtt.setBounds(963, 618, 100, 60); // 공격아이템 개수 라벨
		laAtt.setFont(new Font("궁서체", Font.BOLD, 35));
		// 방어
		btnDef.setBounds(903, 673, 60, 60); // 방어스킬버튼
		laDef.setBounds(963, 678, 100, 60); // 방어아이템 개수 라벨
		laDef.setFont(new Font("궁서체", Font.BOLD, 35));
		// 전략
		btnTrick.setBounds(903, 733, 60, 60); // 전략스킬버튼
		laTrick.setBounds(963, 738, 100, 60); // 전략아이템 개수 라벨
		laTrick.setFont(new Font("궁서체", Font.BOLD, 35));
		// 스킬사용가능 횟수 라벨
		laTactic.setBounds(903, 815, 160, 40);
		laTactic.setFont(new Font("궁서체", Font.BOLD, 20));
		// 빙고체크가능 횟수 라벨
		laCommand.setBounds(903, 855, 160, 40);
		laCommand.setFont(new Font("궁서체", Font.BOLD, 20));

		// 아바타
		btnAvatar.setBounds(1024, 607, 157, 190);
		btnAvatar.setPreferredSize(new Dimension(avatarIcon.getIconWidth(), avatarIcon.getIconHeight()));
		imageSetting(btnAvatar);

		// 스킬버튼 이미지 사이즈 조정

		// 공격
		btnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(btnAtt);
		// 방어
		btnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(btnDef);
		// 전략
		btnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(btnTrick);

		// 너의 상태창 구성요소 추가(아이디 제외)
		add(bingoTurnIcon1);
		add(btnAtt);
		add(btnDef);
		add(btnTrick);
		add(btnAvatar);
		add(laAtt);
		add(laDef);
		add(laTrick);
		add(laTactic);
		add(laCommand);
		add(p);
	}

	public void imageSetting(JButton btn) {
		btn.setBorderPainted(false); // 버튼 경계선 제거
		btn.setContentAreaFilled(false); // 선택했던 버튼 표시 제거
		btn.setFocusPainted(false); // 버튼영역 배경 제거
		btn.setOpaque(false);
	}

	// 배경을 제외한 모든 이미지 숨기기
	public static void imageVisibleFalse() {
		bingoTurnIcon1.setVisible(false);
		bingoTurnIcon2.setVisible(false);
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
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gauge[i][j].setVisible(false);
				furyEndBtn[i][j].setVisible(false);
				fury[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				bingoScore[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < 3; i++) // 장수 얼굴 버튼 액션리스너
		{
			ChoiceNation.jangSu1[i].setVisible(false);
			ChoiceNation.jangSu2[i].setVisible(false);
		}
		p.setVisible(false);
		pp.setVisible(false);
		timer.setVisible(false);
		timeOut.setVisible(false);
		exit.setVisible(false);
		imageX += 1200;
	}

	// 다시 이미지 보이기
	public static void imageVisibleTrue() {
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
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gauge[i][j].setVisible(true);
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				bingoScore[i][j].setVisible(true);
			}
		}
		for (int i = 0; i < 3; i++) // 장수 얼굴 버튼 액션리스너
		{
			ChoiceNation.jangSu1[i].setVisible(true);
			ChoiceNation.jangSu2[i].setVisible(true);
		}
		if (!goongUsable1[0]) {
			furyEndBtn[1][0].setVisible(true);
			fury[1][0].setVisible(false);
		} else if (goongUsable1[0]) {
			furyEndBtn[1][0].setVisible(false);
			fury[1][0].setVisible(true);
		}
		if (!goongUsable1[1]) {
			furyEndBtn[1][1].setVisible(true);
			fury[1][1].setVisible(false);
		} else if (goongUsable1[1]) {
			furyEndBtn[1][1].setVisible(false);
			fury[1][1].setVisible(true);
		}
		if (!goongUsable1[2]) {
			furyEndBtn[1][2].setVisible(true);
			fury[1][2].setVisible(false);
		} else if (goongUsable1[2]) {
			furyEndBtn[1][2].setVisible(false);
			fury[1][2].setVisible(true);
		}
		if (!goongUsable2[0]) {
			furyEndBtn[0][0].setVisible(true);
			fury[0][0].setVisible(false);
		} else if (goongUsable2[0]) {
			furyEndBtn[0][0].setVisible(false);
			fury[0][0].setVisible(true);
		}
		if (!goongUsable2[1]) {
			furyEndBtn[0][1].setVisible(true);
			fury[0][1].setVisible(false);
		} else if (goongUsable2[1]) {
			furyEndBtn[0][1].setVisible(false);
			fury[0][1].setVisible(true);
		}
		if (!goongUsable2[2]) {
			furyEndBtn[0][2].setVisible(true);
			fury[0][2].setVisible(false);
		} else if (goongUsable2[2]) {
			furyEndBtn[0][2].setVisible(false);
			fury[0][2].setVisible(true);
		}
		p.setVisible(true);
		pp.setVisible(true);
		timer.setVisible(true);
		timeOut.setVisible(true);
		exit.setVisible(true);
		imageX -= 1200;
		if (GameProcess.playerTurn) {
			bingoTurnIcon1.setVisible(true);
			bingoTurnIcon2.setVisible(false);
		} else if (!GameProcess.playerTurn) {
			bingoTurnIcon1.setVisible(false);
			bingoTurnIcon2.setVisible(true);
		}
	}

	/*
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>생성자<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 */
	GameLayout() {
		bingoTurnIcon1 = new JButton(new ImageIcon("img\\턴표시.png"));
		bingoTurnIcon2 = new JButton(new ImageIcon("img\\턴표시.png"));
		// 시간제한 바 추가 및 배치
		add(timer);
		timer.setBackground(Color.WHITE);
		timer.setMinimum(0);
		timer.setMaximum(100);
		timer.setStringPainted(true);
		timer.setFont(new Font("맑은고딕", Font.BOLD, 16));
		timer.setBounds(888, 422, 210, 25);

		// 턴 종료버튼 추가 및 배치
		add(timeOut);
		timeOut.setCursor(cur); // 마우스 오버시 마우스커서 모양 변경(손모양)
		timeOut.setBounds(886, 449, 212, 55);
		timeOut.setIcon(timeImg); // 턴종료 기본 아이콘이미지
		timeOut.setRolloverIcon(timeImg2); // 마우스오버시 아이콘이미지
		timeOut.setPressedIcon(timeImg2); // 마우스 클릭시 아이콘 이미지
		imageSetting(timeOut);

		// 항복(나가기) 버튼 추가 및 배치
		add(exit);
		exit.setCursor(cur); // 마우스 오버시 마우스커서 모양 변경(손모양)
		exit.setBounds(1106, 420, exitImg.getIconWidth(), exitImg.getIconHeight());
		exit.setIcon(exitImg); // 나가기 기본 아이콘이미지
		exit.setRolloverIcon(exitImg2); // 마우스오버시 아이콘이미지
		exit.setPressedIcon(exitImg2); // 마우스 클릭시 아이콘 이미지
		imageSetting(exit);

		// 궁극기 사용가능여부 초기화
		for (int i = 0; i < 3; i++) {
			// 초기화 값이 true이나, 궁극기 버튼이 setVisble(false)여서 사용못함
			// 궁극기 버튼이 setVisble(true)된 이후 사용하고 나면 false로 변경됨
			goongUsable1[i] = true;
			goongUsable2[i] = true;
		}

		/*
		 * 전략필살기버튼 세팅 (진영파괴 버튼 설정)
		 ************************************************/
		for (int i = 0; i < 6; i++) {
			jypgChoice[i] = new JButton(jypgLine); // 스킬버튼 이미지 셋팅
			jypgChoice[i].setContentAreaFilled(false); // 선택했던 버튼 표시 제거
			jypgChoice[i].setBorderPainted(false); // 버튼 경계선 제거
			jypgChoice[i].setFocusPainted(false); // 버튼영역 배경 제거
			jypgChoice[i].setCursor(cur); // 마우스 오버시 마우스커서 모양 변경(손모양)
			add(jypgChoice[i]);
			jypgChoice[i].setVisible(false);
			if (i < 3) {
				jypgChoice[i].setBounds(40 + 279 * i, 27, jypgLine.getIconWidth(), jypgLine.getIconHeight());
			} else {
				jypgChoice[i].setBounds(40 + 279 * (i % 3), 530, jypgLine.getIconWidth(), jypgLine.getIconHeight());
			}
		}
		// 레이아웃으로 묶어서 배치
		// FlowLayout jypg=new FlowLayout(FlowLayout.LEFT,0,0); //전략필살기 레이아웃
		// (진영파괴)
		// 너의 판에 배치
		/*
		 * jypgPan1.setLayout(jypg); jypgPan1.setBackground(null);
		 * jypgPan1.setOpaque(false); jypgPan1.setBounds(32, 528, 895, 110);
		 * add(jypgPan1); jypgPan1.setVisible(false); //나의 판에 배치
		 * jypgPan2.setLayout(jypg); jypgPan2.setBackground(null);
		 * jypgPan2.setOpaque(false); jypgPan2.setBounds(32, 23, 895, 110);
		 * add(jypgPan2); jypgPan2.setVisible(false);
		 */

		/*
		 * 방어필살기버튼 세팅 (진영기습 버튼 설정)
		 ************************************************/

		defPGchoice2 = new JButton(jjgs);
		defPGchoice2.setContentAreaFilled(false);
		defPGchoice2.setCursor(cur);
		defPGchoice2.setBorderPainted(false);
		defPan2.add(defPGchoice2);

		defPGchoice1 = new JButton(jjgs);
		defPGchoice1.setContentAreaFilled(false);
		defPGchoice1.setCursor(cur);
		defPGchoice1.setBorderPainted(false);
		defPan1.add(defPGchoice1);

		defPan1.setLayout(new FlowLayout(FlowLayout.CENTER));// 방어필살기 배치
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

		/*
		 * 각 필살기 게이지 색상 셋팅
		 *********************************************************/

		Color RED = new Color(255, 0, 0);
		Color GREEN = new Color(0, 147, 0);
		Color PURPLE = new Color(95, 0, 255);
		Color[] color = { RED, GREEN, PURPLE };

		/*
		 * 빙고점수확인 버튼 x좌표값 배열화
		 *****************************************************/

		int[] xVal = { 130, 413, 688 };
		int[] xVal2 = { 245, 528, 803 };

		/*
		 * 빙고점수확인 버튼에 이미지아이콘 배치
		 **************************************************/

		for (int i = 0; i < 6; i++) {
			bingoScorePan[i] = new JPanel();
			add(bingoScorePan[i]);
			bingoScorePan[i].setOpaque(false);
			for (int j = 0; j < 5; j++) {
				bingoScore[i][j] = new JButton(bingo1);
				bingoScorePan[i].add(bingoScore[i][j]);
				bingoScore[i][j].setPreferredSize(new Dimension(bingo1.getIconWidth(), bingo1.getIconHeight()));
				bingoScorePan[i].setBounds(xVal[i % 3] - 8, (i / 3) * 505 + 40, 180, 60);
				bingoScore[i][j].setBorderPainted(false);
				bingoScore[i][j].setContentAreaFilled(false);
				bingoScore[i][j].setFocusPainted(false);
			}
		}

		// 필살기 버튼 이미지 주소 배열화
		String[] goong = { "img\\스킬아이콘-공격필살기.png", "img\\스킬아이콘-방어필살기.png", "img\\스킬아이콘-책략필살기.png" };

		// 필살기 버튼 생성 (초기: setVisible(false), 100퍼센트 이후: setVisible(true)
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				// 공격/방어/전략별 궁극기버튼 이미지 배치
				fury[i][j] = new JButton(new ImageIcon(goong[j]));
				// 공격/방어/전략 궁극기소진시 버튼 이미지 배치
				furyEndBtn[i][j] = new JButton(furyEndIcon);
				add(fury[i][j]);
				add(furyEndBtn[i][j]);
				fury[i][j].setBounds(xVal2[j] + imageX, i * 508 + 74, 60, 60);
				fury[i][j].setBorderPainted(false); // 버튼 경계선 제거
				fury[i][j].setContentAreaFilled(false); // 선택했던 버튼 표시 제거
				fury[i][j].setFocusPainted(false); // 버튼영역 배경 제거
				fury[i][j].setEnabled(false); // 궁극기 버튼 초기에는 비활성!!
				fury[i][j].setCursor(cur); // 마우스 오버시 커서 모양 변경(손모양)
				fury[i][j].setVisible(true);
				furyEndBtn[i][j].setBounds(xVal2[j] + imageX, i * 508 + 74, 60, 60);
				furyEndBtn[i][j].setBorderPainted(false); // 버튼 경계선 제거
				furyEndBtn[i][j].setContentAreaFilled(false); // 선택했던 버튼 표시 제거
				furyEndBtn[i][j].setFocusPainted(false); // 버튼영역 배경 제거
				furyEndBtn[i][j].setVisible(false); // 초기에 궁극기 소진 버튼은 안보이게 처리
			}
		}

		// 필살기 게이지 셋팅
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gauge[i][j] = new JProgressBar();
				add(gauge[i][j]);
				gauge[i][j].setMinimum(0); // 진행바 최소값 설정
				gauge[i][j].setMaximum(100); // 진행바 최대값 설정
				gauge[i][j].setStringPainted(true); // 진행사항 퍼센티지로 보여주기 설정
				gauge[i][j].setForeground(color[j]); // 진행바 색설정
				gauge[i][j].setBounds(xVal[j], i * 507 + 85, 115, 40);
			}
		}

		// 마무리 빙고
		endBtn = new JButton(endBtnIcon);
		endBtn.setBounds(305, 385, 75, 75);
		imageSetting(endBtn);
		endBtn.setCursor(cur);
		add(endBtn);
		endBtn.setVisible(false);

		// 게임종료시 나오는 버튼
		gameEnd = new JButton(gameEndIcon);// 나가기
		imageSetting(gameEnd);
		add(gameEnd);
		gameEnd.setVisible(false);

		// 인게임 이미지
		pan2 = Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		pan1 = Toolkit.getDefaultToolkit().getImage("img\\빙고틀.png");
		bg = Toolkit.getDefaultToolkit().getImage("img\\인게임배경.jpg");
		vs = Toolkit.getDefaultToolkit().getImage("img\\vs.png");
		stateImage = Toolkit.getDefaultToolkit().getImage("img\\상태창.png");
		attackSkillNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격.jpg");
		defenseSkillNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어.jpg");
		strategySkillNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략.jpg");
		attackFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-공격필살기.jpg");
		defenseFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-방어필살기.jpg");
		strategyFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\스킬설명-책략필살기.jpg");
		attackSkillImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격.png");
		attackFinishImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-공격필살기.png");
		attackFPlus = Toolkit.getDefaultToolkit().getImage("img\\스킬-공격필살기-플러스.png");
		defenseSkillImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어.png");
		defenseFinishImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-방어필살기.png");
		strategySkillImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략.png");
		strategyFinishImage = Toolkit.getDefaultToolkit().getImage("img\\스킬이미지-책략필살기.png");
		endBack = Toolkit.getDefaultToolkit().getImage("img\\마무리-버튼배경.png");
		boom = Toolkit.getDefaultToolkit().getImage("img\\마무리.jpg");
		won = Toolkit.getDefaultToolkit().getImage("img\\승패-승리.png");
		lose = Toolkit.getDefaultToolkit().getImage("img\\승패-패배.png");

		// 빙고판
		a1 = new JButton[3][25]; // 내 판
		a2 = new JButton[3][25]; // 상대판

		// 플레이어 2 빙고판 레이아웃 (상대판)
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		// 플레이어 1 빙고판 레이아웃 (내판)
		pp = new JPanel();
		pp1 = new JPanel();
		pp2 = new JPanel();
		pp3 = new JPanel();

		// 이미지,버튼 등 배치
		setLayout(null);
		// 빙고판 버튼들 및 버튼묶음패널 배치
		FlowLayout k = new FlowLayout(0); // 0: 왼쪽정렬, 1:가운데정렬, 2:오른쪽정렬
		k.setHgap(20);
		p.setLayout(k);
		pp.setLayout(k);
		add(p); // 상대판
		p.add(p1);
		p.add(p2);
		p.add(p3);
		add(pp);// 내판
		pp.add(pp1);
		pp.add(pp2);
		pp.add(pp3);

		// 장수얼굴 버튼 및 버튼묶음패널 배치
		FlowLayout j0 = new FlowLayout(FlowLayout.RIGHT, 185, 0);
		add(j1);// 내장수
		add(j2);// 상대장수
		j1.setLayout(j0);
		j2.setLayout(j0);

		// 배치
		p.setBounds(20, 130, 900, 275); // 상대판
		pp.setBounds(20, 635, 900, 275); // 내판
		p1.setLayout(new GridLayout(5, 5, 0, 0)); // 5by5행렬모양, 좌,우갭은 0
		p2.setLayout(new GridLayout(5, 5, 0, 0));
		p3.setLayout(new GridLayout(5, 5, 0, 0));
		pp1.setLayout(new GridLayout(5, 5, 0, 0));
		pp2.setLayout(new GridLayout(5, 5, 0, 0));
		pp3.setLayout(new GridLayout(5, 5, 0, 0));
		p1.setSize(265, 265);
		p2.setSize(265, 265);
		p3.setSize(265, 265);
		pp1.setSize(265, 265);
		pp2.setSize(265, 265);
		pp3.setSize(265, 265);
		j2.setBounds(35, 30, 836, 100);
		j1.setBounds(35, 535, 836, 100);

		gameEnd.setBounds(485, 540, 230, 84);

		// 번호 섞기
		Rand();

		p.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		j1.setOpaque(false);
		pp.setOpaque(false);
		pp1.setOpaque(false);
		pp2.setOpaque(false);
		pp3.setOpaque(false);
		j2.setOpaque(false);

		// 상태창
		stateWindow(); // 나의 상태
		youStateWindow(); // 너의 상태

		setSize(1200, 970);
		setVisible(true);

		btnAtt.setCursor(cur);
		btnDef.setCursor(cur);
		btnTrick.setCursor(cur);

		gameEnd.setCursor(cur);

		addKeyListener(this);
		setFocusable(true);
	}

	/*
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>생성자
	 * 종료<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 */

	@Override
	public synchronized void addMouseListener(MouseListener l) {
		// TODO Auto-generated method stub
		super.addMouseListener(l);
	}

	// 이미지 정리
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343 + imageX, 423, vs.getWidth(this), 80, this);
		// 빙고틀
		g.drawImage(pan1, 4 + imageX, 492, 895, 452, this);
		g.drawImage(pan2, 4 + imageX, -10, 895, 446, this);
		// 상태창
		g.drawImage(stateImage, 874 + imageX, 0, stateImage.getWidth(this) - 90, stateImage.getHeight(this) - 90, this);
		g.drawImage(stateImage, 874 + imageX, 505, stateImage.getWidth(this) - 90, stateImage.getHeight(this) - 90,
				this);
		// 스킬설명
		g.drawImage(attackSkillNotice, aNoticeX, 398, 582, 130, this);
		g.drawImage(defenseSkillNotice, dNoticeX, 398, 582, 130, this);
		g.drawImage(strategySkillNotice, sNoticeX, 398, 582, 130, this);
		g.drawImage(attackFinishNotice, aFNoticeX, 398, 582, 130, this);
		g.drawImage(defenseFinishNotice, dFNoticeX, 398, 582, 130, this);
		g.drawImage(strategyFinishNotice, sFNoticeX, 398, 582, 130, this);
		// 스킬이미지
		g.drawImage(attackSkillImage, aImageX, 280, getWidth(), attackSkillImage.getHeight(this), this);
		g.drawImage(attackFinishImage, aFImageX, 280, getWidth(), attackFinishImage.getHeight(this), this);
		g.drawImage(attackFPlus, 1010, plusY, attackFPlus.getWidth(this) / 2, attackFPlus.getHeight(this) / 2, this);
		g.drawImage(defenseSkillImage, dImageX, 280, getWidth(), defenseSkillImage.getHeight(this), this);
		g.drawImage(defenseFinishImage, dFImageX, 280, getWidth(), defenseFinishImage.getHeight(this), this);
		g.drawImage(strategySkillImage, sImageX, 280, getWidth(), strategySkillImage.getHeight(this), this);
		g.drawImage(strategyFinishImage, sFImageX, 280, getWidth(), strategyFinishImage.getHeight(this), this);
		// 마무리
		g.drawImage(endBack, endBackX, 235, 750, 510, this);
		g.drawImage(boom, boomX, 240, 800, 400, this);
		// 승패
		g.drawImage(won, wonX, 200, getWidth(), won.getHeight(this), this);
		g.drawImage(lose, loseX, 200, getWidth(), lose.getHeight(this), this);
	}

	// 지휘권 사용가능 횟수 및 잔여아이템 개수 표시 메소드 (빙고숫자 버튼 클릭에 따라)
	public void laSetting(JLabel command, JLabel att, JLabel def, JLabel trick) {
		command.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
		att.setText("x" + String.valueOf(GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1));
		def.setText("x" + String.valueOf(GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1));
		trick.setText("x" + String.valueOf(GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1));
	}

	public void bingoEndProcess() {
		imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
		GameLayout.endBackX -= 975;
		GameLayout.endBtn.setVisible(true);
	}

	public static void IFNoticeVisible() {
		if (bAttCheck1 || bAttCheck2) {
			aNoticeX += 1190;
			bAttCheck1 = false;
			bAttCheck2 = false;
		} else if (bDefCheck1 || bDefCheck2) {
			dNoticeX += 1190;
			bDefCheck1 = false;
			bDefCheck2 = false;
		} else if (bTrickCheck1 || bTrickCheck2) {
			sNoticeX += 1190;
			bTrickCheck1 = false;
			bTrickCheck2 = false;
		} else if (bDefFCheck1 || bDefFCheck2) {
			dFNoticeX += 1190;
			bDefFCheck1 = false;
			bDefFCheck2 = false;
			defPGchoice2.setVisible(false);
		} else if (bTrickFCheck1 || bTrickFCheck2) {
			sFNoticeX += 1190;
			bTrickFCheck1 = false;
			bTrickFCheck2 = false;
			for (int i = 0; i < 6; i++) {
				jypgChoice[i].setVisible(false);
			}
		}
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

	class AImageThread extends Thread // 공격스킬 이미지 쓰레드
	{
		public void run() {
			GameLayout.aNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.aImageX >= 0) {
				try {
					GameLayout.aImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {}
			GameLayout.aImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
		}
	}

	class DImageThread extends Thread // 방어스킬 이미지 쓰레드
	{
		public void run() {
			GameLayout.dNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.dImageX >= 0) {
				try {
					GameLayout.dImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {}
			GameLayout.dImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
		}
	}

	class SImageThread extends Thread // 전략스킬 이미지 쓰레드
	{
		public void run() {
			GameLayout.sNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.sImageX >= 0) {
				try {
					GameLayout.sImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {}
			GameLayout.sImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
		}
	}

	class AFImageThread extends Thread // 공격필살기 이미지 쓰레드
	{
		public void run() {
			GameLayout.aFNoticeX -= 1190; // 게임설명 가져오기
			try {
				Thread.sleep(1500); // 1.5초 후
			} catch (Exception ex) {}
			GameLayout.aFNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.aFImageX >= 0) {
				try {
					GameLayout.aFImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {
			}
			GameLayout.aFImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
			laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));
			laCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
			GameLayout.plusY -= 170;
			while (GameLayout.plusY > 810) // 전술명령,지휘권 옆에 플러스표시
			{
				try {
					GameLayout.plusY--;
					Thread.sleep(50);
				} catch (Exception ex) {}
			}
			GameLayout.plusY = 1000;
		}
	}

	class DFImageThread extends Thread // 방어필살기 이미지 쓰레드
	{
		public void run() {
			GameLayout.dFNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.dFImageX >= 0) {
				try {
					GameLayout.dFImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {}
			GameLayout.dFImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
		}
	}

	class SFImageThread extends Thread // 전략필살기 이미지 쓰레드
	{
		public void run() {
			GameLayout.sFNoticeX += 1190; // 게임설명은 없어지고
			imageVisibleFalse(); // 배경을 제외한 모든 오브젝트가 사라진 뒤
			while (GameLayout.sFImageX >= 0) {
				try {
					GameLayout.sFImageX -= 3; // 스킬이미지가 날아옴
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //턴 표시가 바뀌어도
					bingoTurnIcon2.setVisible(false); //스킬 이미지가 돌아가는 동안은 안보임
				} catch (Exception ex) {
				}
			}
			try {
				Thread.sleep(1000); // 1초간 게임이미지 멈춰있기
			} catch (Exception ex) {
			}
			GameLayout.sFImageX = 1200; // 게임이미지 없애기
			imageVisibleTrue();
		}
	}

	public static class TimeLimit extends Thread {
		int cnt = 0;
		int[] rgb = new int[3];
		int percent = 0; // 시간제한바를 채우는 퍼센트 (20초:100퍼센트 즉, 0.2초: 1퍼센트)
		double residueTime = 20; // 남은시간표시 (초기값:20초)

		public void run() {
			// timeRun=true;
			try {
				if (GameProcess.playerTurn) {
					bingoTurnIcon1.setVisible(true);
					bingoTurnIcon2.setVisible(false);
				} else if (!GameProcess.playerTurn) {
					bingoTurnIcon1.setVisible(false);
					bingoTurnIcon2.setVisible(true);
				}
				while (true)// timeRun)//timeRun이 false일때 멈춤 (나가기,항복,턴종료)
				{
					cnt++;
					percent++;
					if (percent > 100)// 100퍼센트가 되면 다시 0으로 초기화
					{
						if (GameProcess.playerTurn)// 턴이 바뀜
						{
							GameProcess.playerTurn = false;
							GameProcess.skillChance2=1;
							GameProcess.bingoCheckChance2=1;
							youLaTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance2));
							youLaCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance2));
						}
						else if (!GameProcess.playerTurn)// 턴이 바뀜
						{
							GameProcess.playerTurn = true;
							GameProcess.skillChance1=1;
							GameProcess.bingoCheckChance1=1;
							laTactic.setText("전술명령x" + String.valueOf(GameProcess.skillChance1));
							laCommand.setText("지휘권x" + String.valueOf(GameProcess.bingoCheckChance1));
						}
						percent = 0;
						residueTime = 20;
						colorInt = 0;
						rgb[0] = colorInt;
						rgb[1] = 255;
						IFNoticeVisible(); // 시간이 끝나면 게임설명은 들어가고 스킬사용이false가 됨
						if (GameProcess.playerTurn) {
							bingoTurnIcon1.setVisible(true);
							bingoTurnIcon2.setVisible(false);
						} else if (!GameProcess.playerTurn) {
							bingoTurnIcon1.setVisible(false);
							bingoTurnIcon2.setVisible(true);
						}
					}
					Thread.sleep(200);
					colorInt = (int) (Math.ceil(2.55 * (percent)));
					residueTime -= 0.2;
					if (residueTime < 0.2)
						residueTime = 0;
					String rt = String.valueOf(residueTime);
					if (rt.length() >= 4) {
						String rr = rt.substring(0, 4);
						timer.setString("制限時間:" + rr);
					} else {
						String rr = rt;
						timer.setString("制限時間:" + rr);
					}
					rgb[0] = colorInt;
					rgb[1] = 255 - colorInt;
					timer.setValue(percent);
					timer.setForeground(new Color(rgb[0], rgb[1], rgb[2]));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
	}

	class paintThread extends Thread // 시간제한이 지나면 스킬설명을 없애기 위해 repaint()해주는 쓰레드
	{
		public void run() {
			try {
				while (true) {
					repaint();
				}
			} catch (Exception e) {
			}
		}
	}

	class endThread extends Thread // 전략필살기 이미지 쓰레드
	{
		public void run() {
			GameLayout.boomX -= 1000;
			int i = 0;
			while (i < 18) {
				try {
					if (i % 2 == 0)
						GameLayout.boomX += 10;
					else
						GameLayout.boomX -= 10;
					i++;
					Thread.sleep(100);
				} catch (Exception ex) {
				}
			}
			GameLayout.boomX += 1000;
			if (GameProcess.playerWon == true)
				GameLayout.wonX -= 1200;
			else if (GameProcess.playerWon == false)
				GameLayout.loseX -= 1200;
			gameEnd.setVisible(true);
		}
	}
}