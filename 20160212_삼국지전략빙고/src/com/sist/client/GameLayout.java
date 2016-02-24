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
	/* >>>>>>>>>>>>>>>>>>>>>>>>>>��������<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	// �ð����� Ÿ�̸�
	static JProgressBar timer = new JProgressBar(); // �ð����ѹ�
	// static boolean timeRun=true;
	static int colorInt = 0;

	// ������ ��ư
	static JButton timeOut = new JButton();
	static ImageIcon timeImg = new ImageIcon("img\\������_�⺻.png");
	static ImageIcon timeImg2 = new ImageIcon("img\\������_Ŀ��.png");
	// �׺� ��ư
	static JButton exit = new JButton();
	static ImageIcon exitImg = new ImageIcon("img\\�׺�.png");
	static ImageIcon exitImg2 = new ImageIcon("img\\�׺�_Ŀ��.png");
	// ���ȭ��
	Image bg; // �߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image vs; // ��� vs �ؽ�Ʈ
	Image pan1; // ����Ʋ �÷��̾�1
	Image pan2; // ����Ʋ �÷��̾�2
	Image stateImage; // ����â �̹���
	// ��ų�����̹�����
	Image attackSkillNotice, attackFinishNotice;
	Image defenseSkillNotice, defenseFinishNotice;
	Image strategySkillNotice, strategyFinishNotice;
	// ��ų�̹�����
	Image attackSkillImage, attackFinishImage, attackFPlus;
	Image defenseSkillImage, defenseFinishImage;
	Image strategySkillImage, strategyFinishImage;
	// �����̹���
	Image won, lose;
	// �̹��� ��ǥ����
	static int aNoticeX = 1200, aFNoticeX = 1200;
	static int dNoticeX = 1200, dFNoticeX = 1200;
	static int sNoticeX = 1200, sFNoticeX = 1200;
	static int aImageX = 1200, aFImageX = 1200, plusY = 1000;
	static int dImageX = 1200, dFImageX = 1200;
	static int sImageX = 1200, sFImageX = 1200;
	static int imageX = 0, wonX = 1200, loseX = 1200;

	ChatInGame cig = new ChatInGame();
	// ���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	static Cursor cur = new Cursor(Cursor.HAND_CURSOR);

	// ������
	static JButton[][] a1 = new JButton[3][25]; // �� ��
	static JButton[][] a2 = new JButton[3][25]; // �����

	// �����ۼ��ù�ư, �ƹ�Ÿ
	static JButton btnAtt; // ���ݽ�ų��ư
	static JButton btnDef; // ����ų��ư
	static JButton btnTrick; // å����ų��ư
	static JButton btnAvatar; // �ƹ�Ÿ �̹��� �־��� ��ư

	static JButton youBtnAtt; // ����� ��ư�� ��ǻ� �̹��� ���Կ�
	static JButton youBtnDef;
	static JButton youBtnTrick;
	static JButton youBtnAvatar;

	static int useAtt, useDef, useTrick;

	// ������ ���� Ȯ�� �� ���̵�
	static JLabel laAtt; // ���ݽ�ų ���� Ȯ��
	static JLabel laDef; // ����ų ���� Ȯ��
	static JLabel laTrick; // å����ų ���� Ȯ��
	static JLabel laNickname; // �г��� �Է��ϴ� ��
	static JLabel laTactic, laCommand; // �������, ���ֱ�

	static JLabel youLaAtt; // ����.
	static JLabel youLaDef;
	static JLabel youLaTrick;
	static JLabel youLaNickname;
	static JLabel youLaTactic, youLaCommand;

	// �÷��̾� 1,2 ����Ǻ� ������
	static JProgressBar[][] gauge = new JProgressBar[2][3];
	// �÷��̾� 1,2 ����Ǻ� �ñر� Ȱ�� �ȳ���ư
	static JButton[][] fury = new JButton[2][3]; // [0][0]~[0][2]���� ����� �ʻ���ư,
													// [1][0]~[1][2]���� ���� �ʻ���ư

	// �÷��̾� 1,2 ����Ǻ� ����ȹ������ Ȯ���г�
	static JButton[][] bingoScore = new JButton[6][5];
	static JPanel[] bingoScorePan = new JPanel[6];
	static ImageIcon bingo1 = new ImageIcon("img\\����-����.png");
	static ImageIcon bingo2 = new ImageIcon("img\\����-���ٿϼ�.png");
	static ImageIcon attIcon = new ImageIcon("img\\��ų������-����.png");
	static ImageIcon defIcon = new ImageIcon("img\\��ų������-���.png");
	static ImageIcon trickIcon = new ImageIcon("img\\��ų������-å��.png");
	static ImageIcon avatarIcon = new ImageIcon("img\\m1.jpg");
	static ImageIcon youAvatarIcon = new ImageIcon("img\\m2.jpg");
	static ImageIcon defPGIcon = new ImageIcon("img\\�����ı��̹���.jpg");// ����ʻ�������
	static JButton defPGchoice1, defPGchoice2;
	static JPanel defPan1 = new JPanel();
	static JPanel defPan2 = new JPanel();
	static ImageIcon jypgLine = new ImageIcon("img\\�����ı��̹���.jpg");// å���ʻ�� ��ư��
																	// ������ ������
																	// �����ı���ư
	static ImageIcon jjgs = new ImageIcon("img\\��������̹���.jpg");// ����ʻ�� ��ư�� ������
																// ������ ���������ư
	static JButton[] jypgChoice = new JButton[6]; // 0,1,2 ��������ı� �� ���ù�ư(����
													// ������), �� �����ı� �� ���ù�ư(�ϰ�
													// ������)
	static JPanel jypgPan1 = new JPanel();
	static JPanel jypgPan2 = new JPanel();
	static ImageIcon furyEndIcon = new ImageIcon("img\\�ñر����������.png");
	static JButton[][] furyEndBtn = new JButton[2][3];
	static ImageIcon gameEndIcon = new ImageIcon("img\\���ӳ�-������.jpg");

	// �÷��̾� 2 ������ ���̾ƿ� (�����)
	static JPanel p = new JPanel(); // ����� ������ ���� �г�(p1,p2,p3)
	static JPanel p1 = new JPanel(); // ����� ������� ��
	static JPanel p2 = new JPanel(); // ����� ������ ��
	static JPanel p3 = new JPanel(); // ����� ������� ��

	// �÷��̾� 1 ������ ���̾ƿ� (����)
	static JPanel pp = new JPanel(); // ���� ������ ���� �г�(pp1,pp2,pp3)
	static JPanel pp1 = new JPanel(); // ���� ������� ��
	static JPanel pp2 = new JPanel(); // ���� ������ ��
	static JPanel pp3 = new JPanel(); // ���� ������� ��

	// ��� ĳ���� â
	static JPanel j1 = new JPanel(); // �÷��̾�2 ����� �����г�
	static JPanel j2 = new JPanel(); // �÷��̾�1 ����� �����г�

	// ���˿� ����üũ �̹���
	static ImageIcon bcIcon0 = new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon1 = new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon2 = new ImageIcon("img\\����üũ-��.png");
	static ImageIcon enemyIcon = new ImageIcon("img\\������-���.png");

	// ��ų ��밡�ɿ���(true=��ų��밡��, false=���Ұ�)
	static boolean bAttCheck1 = false; // ���� ���ݽ�ų
	static boolean bDefCheck1 = false; // ���� ��ų
	static boolean bTrickCheck1 = false; // ���� ������ų
	static boolean bDefFCheck1 = false; // ���� ����ʻ��
	static boolean bTrickFCheck1 = false; // ���� �����ʻ��

	static boolean bAttCheck2 = false; // ���� ���ݽ�ų
	static boolean bDefCheck2 = false; // ���� ��ų
	static boolean bTrickCheck2 = false; // ���� ������ų
	static boolean bDefFCheck2 = false; // ���� ����ʻ��
	static boolean bTrickFCheck2 = false; // ���� �����ʻ��

	// �ñر�(�ʻ��) ��밡�ɿ��� (0���迭:�����ʻ��, 1���迭:����ʻ��, 2���迭:�����ʻ��)
	static boolean goongUsable1[] = new boolean[3]; // ���� �ñر�(�ʻ��)
	static boolean goongUsable2[] = new boolean[3]; // ���� �ñر�(�ʻ��)

	// ������ ���� ��ư(���� ���� ��ư)�� Ȯ���ϱ� ���� ����
	static boolean[][] panCheck1 = new boolean[3][25]; // ���� ===> ��� �ǿ� üũ�Ѱ�
	static boolean[][] panCheck2 = new boolean[3][25]; // ��밡 ===> �� �ǿ� üũ�Ѱ�

	// ������ǥ��
	static JButton bingoTurnIcon1 = new JButton(new ImageIcon("img\\��ǥ��.png"));
	static JButton bingoTurnIcon2 = new JButton(new ImageIcon("img\\��ǥ��.png"));
	// ��������
	Image endBack, boom; // ����̹���
	static JButton endBtn;
	ImageIcon endBtnIcon = new ImageIcon("img\\������-��ư.png");
	static int endBackX = 1200;
	static int boomX = 1200;
	static boolean bingoEnd = false;
	// ���� ������ ��ư
	static JButton gameEnd;

	/* >>>>>>>>>>>>>>>>>>>>>>>>>>�޼ҵ�<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

	// �����ư����1 (�����ư�� �̹��� �ִ� �޼ҵ�)
	public static void RanButton(int a, int b, int c, JPanel d, JPanel e) {
		// ���� �����ư����
		ImageIcon m = new ImageIcon("img\\" + GameProcess.numArr1[a] + ".png");
		a1[b][a - c] = new JButton(m);
		d.add(a1[b][a - c]);
		// ��ư�� ������ ������ ���߱�
		a1[b][a - c].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
		a1[b][a - c].setBorderPainted(false); // ��ư ��輱 ����
		a1[b][a - c].setContentAreaFilled(false); // �����ߴ� ��ư ǥ�� ����
		a1[b][a - c].setFocusPainted(false); // ��ư���� ��� ����
		a1[b][a - c].setCursor(cur); // ��ư�� ���콺�� �ø��� Ŀ�� ����� ������ �ٲ�

		// ����� �����ư����
		ImageIcon m1 = new ImageIcon("img\\������-���.png");
		a2[b][a - c] = new JButton(m1);
		e.add(a2[b][a - c]);
		// ��ư�� ������ ������ ���߱�
		a2[b][a - c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a - c].setBorderPainted(false);
		a2[b][a - c].setContentAreaFilled(false);
		a2[b][a - c].setFocusPainted(false);
		a2[b][a - c].setCursor(cur);
	}

	// �����ư����2 (���� Ȱ�� �޼ҵ�� ��ܿ� ���ǵ� RanButton();�����ư����1)
	public static void Rand() {
		// 1���� 75������ �������� ��� ==> GameProcess Ŭ������ numArr1[75], numArr2[75]�� ����.
		GameProcess.rand(); // GameProcess Ŭ���� �������� ����~ �����޼ҵ� ȣ��

		for (int i = 0; i < 75; i++) {
			if (i < 25) {
				// pp1(���� ������ �г�), p1(���� ������ �г�)
				RanButton(i, 0, 0, pp1, p1);
			} else if ((i >= 25) && (i < 50)) {
				// pp2(���� ����� �г�), p2(���� ����� �г�)
				RanButton(i, 1, 25, pp2, p2);
			} else { // pp3(���� ������ �г�), p3(���� ������ �г�)
				RanButton(i, 2, 50, pp3, p3);
			}
		}
	}

	// ���� ����â ���� �޼ҵ�
	public void youStateWindow() {
		youBtnAtt = new JButton(attIcon); // ���� ���ݽ�ų ��ư
		youBtnDef = new JButton(defIcon); // ���� ��ų ��ư
		youBtnTrick = new JButton(trickIcon); // ���� ������ų ��ư
		youBtnAvatar = new JButton(youAvatarIcon); // ���� �ƹ�Ÿ
		youLaAtt = new JLabel("x0"); // ���� ���ݾ����� ���� ��
		youLaDef = new JLabel("x0"); // ���� �������� ���� ��
		youLaTrick = new JLabel("x0"); // ���� ���������� ���� ��
		youLaTactic = new JLabel("�������x" + GameProcess.skillChance2); // ����
																		// ��ų��밡��
																		// Ƚ�� ��
		youLaCommand = new JLabel("���ֱ�x" + GameProcess.bingoCheckChance2); // ����
																			// ����üũ����
																			// Ƚ��
																			// ��
		youLaNickname = new JLabel("your���̵�"); // ���� ���̵�
		youLaNickname.setFont(new Font("�ü�ü", Font.PLAIN, 35));

		// ���� ���̵� ���� �гο� �߰�
		JPanel p = new JPanel();
		p.add(youLaNickname);
		p.setBounds(920, 50, 240, 58);
		p.setOpaque(false);

		// ���� ����â ������� ��ġ
		bingoTurnIcon2.setBounds(905, 30, 55, 55);// ������
		bingoTurnIcon2.setVisible(false);
		imageSetting(bingoTurnIcon2);
		// ����
		youBtnAtt.setBounds(903, 104, 60, 60); // ���ݽ�ų��ư
		youLaAtt.setBounds(963, 109, 100, 60); // ���ݾ����� ���� ��
		youLaAtt.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ���
		youBtnDef.setBounds(903, 164, 60, 60); // ��ų��ư
		youLaDef.setBounds(963, 169, 100, 60); // �������� ���� ��
		youLaDef.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ����
		youBtnTrick.setBounds(903, 224, 60, 60); // ������ų��ư
		youLaTrick.setBounds(963, 229, 100, 60); // ���������� ���� ��
		youLaTrick.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ��ų��밡�� Ƚ�� ��
		youLaTactic.setBounds(903, 307, 160, 40);
		youLaTactic.setFont(new Font("�ü�ü", Font.BOLD, 20));
		// ����üũ���� Ƚ�� ��
		youLaCommand.setBounds(903, 347, 160, 40);
		youLaCommand.setFont(new Font("�ü�ü", Font.BOLD, 20));
		// �ƹ�Ÿ
		youBtnAvatar.setBounds(1024, 102, 157, 190);
		youBtnAvatar.setPreferredSize(new Dimension(youAvatarIcon.getIconWidth(), youAvatarIcon.getIconHeight()));
		imageSetting(youBtnAvatar);

		// ��ų��ư �̹��� ������ ����

		// ����
		youBtnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(youBtnAtt);
		// ���
		youBtnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(youBtnDef);
		// ����
		youBtnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(youBtnTrick);

		// ���� ����â ������� �߰�(���̵� ����)
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

	// ���� ����â ���� �޼ҵ�
	public void stateWindow() {
		btnAtt = new JButton(attIcon); // ���� ���ݽ�ų ��ư
		btnDef = new JButton(defIcon); // ���� ��ų ��ư
		btnTrick = new JButton(trickIcon); // ���� ������ų ��ư
		btnAvatar = new JButton(avatarIcon); // ���� �ƹ�Ÿ
		laAtt = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1)))); // ����
																													// ���ݾ�����
																													// ����
																													// ��
		laDef = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1)))); // ����
																													// ��������
																													// ����
																													// ��
		laTrick = new JLabel("x" + (String.valueOf((GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1)))); // ����
																														// ����������
																														// ����
																														// ��
		laTactic = new JLabel("�������x" + String.valueOf(GameProcess.skillChance1)); // ����
																					// ��ų��밡��
																					// Ƚ��
																					// ��
		laCommand = new JLabel("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1)); // ����
																						// ����üũ����
																						// Ƚ��
																						// ��
		laNickname = new JLabel("���̵�"); // ���� ���̵�
		laNickname.setFont(new Font("�ü�ü", Font.PLAIN, 35));

		// ���� ���̵� ���� �гο� �߰�
		JPanel p = new JPanel();
		p.add(laNickname);
		p.setBounds(920, 539, 240, 58);
		p.setOpaque(false);

		// ���� ����â ������� ��ġ

		bingoTurnIcon1.setBounds(905, 535, 55, 55);// ������
		bingoTurnIcon1.setVisible(false);
		imageSetting(bingoTurnIcon1);
		// ����
		btnAtt.setBounds(903, 613, 60, 60); // ���ݽ�ų��ư
		laAtt.setBounds(963, 618, 100, 60); // ���ݾ����� ���� ��
		laAtt.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ���
		btnDef.setBounds(903, 673, 60, 60); // ��ų��ư
		laDef.setBounds(963, 678, 100, 60); // �������� ���� ��
		laDef.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ����
		btnTrick.setBounds(903, 733, 60, 60); // ������ų��ư
		laTrick.setBounds(963, 738, 100, 60); // ���������� ���� ��
		laTrick.setFont(new Font("�ü�ü", Font.BOLD, 35));
		// ��ų��밡�� Ƚ�� ��
		laTactic.setBounds(903, 815, 160, 40);
		laTactic.setFont(new Font("�ü�ü", Font.BOLD, 20));
		// ����üũ���� Ƚ�� ��
		laCommand.setBounds(903, 855, 160, 40);
		laCommand.setFont(new Font("�ü�ü", Font.BOLD, 20));

		// �ƹ�Ÿ
		btnAvatar.setBounds(1024, 607, 157, 190);
		btnAvatar.setPreferredSize(new Dimension(avatarIcon.getIconWidth(), avatarIcon.getIconHeight()));
		imageSetting(btnAvatar);

		// ��ų��ư �̹��� ������ ����

		// ����
		btnAtt.setPreferredSize(new Dimension(attIcon.getIconWidth(), attIcon.getIconHeight()));
		imageSetting(btnAtt);
		// ���
		btnDef.setPreferredSize(new Dimension(defIcon.getIconWidth(), defIcon.getIconHeight()));
		imageSetting(btnDef);
		// ����
		btnTrick.setPreferredSize(new Dimension(trickIcon.getIconWidth(), trickIcon.getIconHeight()));
		imageSetting(btnTrick);

		// ���� ����â ������� �߰�(���̵� ����)
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
		btn.setBorderPainted(false); // ��ư ��輱 ����
		btn.setContentAreaFilled(false); // �����ߴ� ��ư ǥ�� ����
		btn.setFocusPainted(false); // ��ư���� ��� ����
		btn.setOpaque(false);
	}

	// ����� ������ ��� �̹��� �����
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
		for (int i = 0; i < 3; i++) // ��� �� ��ư �׼Ǹ�����
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

	// �ٽ� �̹��� ���̱�
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
		for (int i = 0; i < 3; i++) // ��� �� ��ư �׼Ǹ�����
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
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>������<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 */
	GameLayout() {
		bingoTurnIcon1 = new JButton(new ImageIcon("img\\��ǥ��.png"));
		bingoTurnIcon2 = new JButton(new ImageIcon("img\\��ǥ��.png"));
		// �ð����� �� �߰� �� ��ġ
		add(timer);
		timer.setBackground(Color.WHITE);
		timer.setMinimum(0);
		timer.setMaximum(100);
		timer.setStringPainted(true);
		timer.setFont(new Font("�������", Font.BOLD, 16));
		timer.setBounds(888, 422, 210, 25);

		// �� �����ư �߰� �� ��ġ
		add(timeOut);
		timeOut.setCursor(cur); // ���콺 ������ ���콺Ŀ�� ��� ����(�ո��)
		timeOut.setBounds(886, 449, 212, 55);
		timeOut.setIcon(timeImg); // ������ �⺻ �������̹���
		timeOut.setRolloverIcon(timeImg2); // ���콺������ �������̹���
		timeOut.setPressedIcon(timeImg2); // ���콺 Ŭ���� ������ �̹���
		imageSetting(timeOut);

		// �׺�(������) ��ư �߰� �� ��ġ
		add(exit);
		exit.setCursor(cur); // ���콺 ������ ���콺Ŀ�� ��� ����(�ո��)
		exit.setBounds(1106, 420, exitImg.getIconWidth(), exitImg.getIconHeight());
		exit.setIcon(exitImg); // ������ �⺻ �������̹���
		exit.setRolloverIcon(exitImg2); // ���콺������ �������̹���
		exit.setPressedIcon(exitImg2); // ���콺 Ŭ���� ������ �̹���
		imageSetting(exit);

		// �ñر� ��밡�ɿ��� �ʱ�ȭ
		for (int i = 0; i < 3; i++) {
			// �ʱ�ȭ ���� true�̳�, �ñر� ��ư�� setVisble(false)���� ������
			// �ñر� ��ư�� setVisble(true)�� ���� ����ϰ� ���� false�� �����
			goongUsable1[i] = true;
			goongUsable2[i] = true;
		}

		/*
		 * �����ʻ���ư ���� (�����ı� ��ư ����)
		 ************************************************/
		for (int i = 0; i < 6; i++) {
			jypgChoice[i] = new JButton(jypgLine); // ��ų��ư �̹��� ����
			jypgChoice[i].setContentAreaFilled(false); // �����ߴ� ��ư ǥ�� ����
			jypgChoice[i].setBorderPainted(false); // ��ư ��輱 ����
			jypgChoice[i].setFocusPainted(false); // ��ư���� ��� ����
			jypgChoice[i].setCursor(cur); // ���콺 ������ ���콺Ŀ�� ��� ����(�ո��)
			add(jypgChoice[i]);
			jypgChoice[i].setVisible(false);
			if (i < 3) {
				jypgChoice[i].setBounds(40 + 279 * i, 27, jypgLine.getIconWidth(), jypgLine.getIconHeight());
			} else {
				jypgChoice[i].setBounds(40 + 279 * (i % 3), 530, jypgLine.getIconWidth(), jypgLine.getIconHeight());
			}
		}
		// ���̾ƿ����� ��� ��ġ
		// FlowLayout jypg=new FlowLayout(FlowLayout.LEFT,0,0); //�����ʻ�� ���̾ƿ�
		// (�����ı�)
		// ���� �ǿ� ��ġ
		/*
		 * jypgPan1.setLayout(jypg); jypgPan1.setBackground(null);
		 * jypgPan1.setOpaque(false); jypgPan1.setBounds(32, 528, 895, 110);
		 * add(jypgPan1); jypgPan1.setVisible(false); //���� �ǿ� ��ġ
		 * jypgPan2.setLayout(jypg); jypgPan2.setBackground(null);
		 * jypgPan2.setOpaque(false); jypgPan2.setBounds(32, 23, 895, 110);
		 * add(jypgPan2); jypgPan2.setVisible(false);
		 */

		/*
		 * ����ʻ���ư ���� (������� ��ư ����)
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

		defPan1.setLayout(new FlowLayout(FlowLayout.CENTER));// ����ʻ�� ��ġ
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
		 * �� �ʻ�� ������ ���� ����
		 *********************************************************/

		Color RED = new Color(255, 0, 0);
		Color GREEN = new Color(0, 147, 0);
		Color PURPLE = new Color(95, 0, 255);
		Color[] color = { RED, GREEN, PURPLE };

		/*
		 * ��������Ȯ�� ��ư x��ǥ�� �迭ȭ
		 *****************************************************/

		int[] xVal = { 130, 413, 688 };
		int[] xVal2 = { 245, 528, 803 };

		/*
		 * ��������Ȯ�� ��ư�� �̹��������� ��ġ
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

		// �ʻ�� ��ư �̹��� �ּ� �迭ȭ
		String[] goong = { "img\\��ų������-�����ʻ��.png", "img\\��ų������-����ʻ��.png", "img\\��ų������-å���ʻ��.png" };

		// �ʻ�� ��ư ���� (�ʱ�: setVisible(false), 100�ۼ�Ʈ ����: setVisible(true)
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				// ����/���/������ �ñر��ư �̹��� ��ġ
				fury[i][j] = new JButton(new ImageIcon(goong[j]));
				// ����/���/���� �ñر������ ��ư �̹��� ��ġ
				furyEndBtn[i][j] = new JButton(furyEndIcon);
				add(fury[i][j]);
				add(furyEndBtn[i][j]);
				fury[i][j].setBounds(xVal2[j] + imageX, i * 508 + 74, 60, 60);
				fury[i][j].setBorderPainted(false); // ��ư ��輱 ����
				fury[i][j].setContentAreaFilled(false); // �����ߴ� ��ư ǥ�� ����
				fury[i][j].setFocusPainted(false); // ��ư���� ��� ����
				fury[i][j].setEnabled(false); // �ñر� ��ư �ʱ⿡�� ��Ȱ��!!
				fury[i][j].setCursor(cur); // ���콺 ������ Ŀ�� ��� ����(�ո��)
				fury[i][j].setVisible(true);
				furyEndBtn[i][j].setBounds(xVal2[j] + imageX, i * 508 + 74, 60, 60);
				furyEndBtn[i][j].setBorderPainted(false); // ��ư ��輱 ����
				furyEndBtn[i][j].setContentAreaFilled(false); // �����ߴ� ��ư ǥ�� ����
				furyEndBtn[i][j].setFocusPainted(false); // ��ư���� ��� ����
				furyEndBtn[i][j].setVisible(false); // �ʱ⿡ �ñر� ���� ��ư�� �Ⱥ��̰� ó��
			}
		}

		// �ʻ�� ������ ����
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gauge[i][j] = new JProgressBar();
				add(gauge[i][j]);
				gauge[i][j].setMinimum(0); // ����� �ּҰ� ����
				gauge[i][j].setMaximum(100); // ����� �ִ밪 ����
				gauge[i][j].setStringPainted(true); // ������� �ۼ�Ƽ���� �����ֱ� ����
				gauge[i][j].setForeground(color[j]); // ����� ������
				gauge[i][j].setBounds(xVal[j], i * 507 + 85, 115, 40);
			}
		}

		// ������ ����
		endBtn = new JButton(endBtnIcon);
		endBtn.setBounds(305, 385, 75, 75);
		imageSetting(endBtn);
		endBtn.setCursor(cur);
		add(endBtn);
		endBtn.setVisible(false);

		// ��������� ������ ��ư
		gameEnd = new JButton(gameEndIcon);// ������
		imageSetting(gameEnd);
		add(gameEnd);
		gameEnd.setVisible(false);

		// �ΰ��� �̹���
		pan2 = Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		pan1 = Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		bg = Toolkit.getDefaultToolkit().getImage("img\\�ΰ��ӹ��.jpg");
		vs = Toolkit.getDefaultToolkit().getImage("img\\vs.png");
		stateImage = Toolkit.getDefaultToolkit().getImage("img\\����â.png");
		attackSkillNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-����.jpg");
		defenseSkillNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-���.jpg");
		strategySkillNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-å��.jpg");
		attackFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-�����ʻ��.jpg");
		defenseFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-����ʻ��.jpg");
		strategyFinishNotice = Toolkit.getDefaultToolkit().getImage("img\\��ų����-å���ʻ��.jpg");
		attackSkillImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����.png");
		attackFinishImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-�����ʻ��.png");
		attackFPlus = Toolkit.getDefaultToolkit().getImage("img\\��ų-�����ʻ��-�÷���.png");
		defenseSkillImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-���.png");
		defenseFinishImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����ʻ��.png");
		strategySkillImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å��.png");
		strategyFinishImage = Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å���ʻ��.png");
		endBack = Toolkit.getDefaultToolkit().getImage("img\\������-��ư���.png");
		boom = Toolkit.getDefaultToolkit().getImage("img\\������.jpg");
		won = Toolkit.getDefaultToolkit().getImage("img\\����-�¸�.png");
		lose = Toolkit.getDefaultToolkit().getImage("img\\����-�й�.png");

		// ������
		a1 = new JButton[3][25]; // �� ��
		a2 = new JButton[3][25]; // �����

		// �÷��̾� 2 ������ ���̾ƿ� (�����)
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		// �÷��̾� 1 ������ ���̾ƿ� (����)
		pp = new JPanel();
		pp1 = new JPanel();
		pp2 = new JPanel();
		pp3 = new JPanel();

		// �̹���,��ư �� ��ġ
		setLayout(null);
		// ������ ��ư�� �� ��ư�����г� ��ġ
		FlowLayout k = new FlowLayout(0); // 0: ��������, 1:�������, 2:����������
		k.setHgap(20);
		p.setLayout(k);
		pp.setLayout(k);
		add(p); // �����
		p.add(p1);
		p.add(p2);
		p.add(p3);
		add(pp);// ����
		pp.add(pp1);
		pp.add(pp2);
		pp.add(pp3);

		// ����� ��ư �� ��ư�����г� ��ġ
		FlowLayout j0 = new FlowLayout(FlowLayout.RIGHT, 185, 0);
		add(j1);// �����
		add(j2);// ������
		j1.setLayout(j0);
		j2.setLayout(j0);

		// ��ġ
		p.setBounds(20, 130, 900, 275); // �����
		pp.setBounds(20, 635, 900, 275); // ����
		p1.setLayout(new GridLayout(5, 5, 0, 0)); // 5by5��ĸ��, ��,�참�� 0
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

		// ��ȣ ����
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

		// ����â
		stateWindow(); // ���� ����
		youStateWindow(); // ���� ����

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
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>������
	 * ����<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 */

	@Override
	public synchronized void addMouseListener(MouseListener l) {
		// TODO Auto-generated method stub
		super.addMouseListener(l);
	}

	// �̹��� ����
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343 + imageX, 423, vs.getWidth(this), 80, this);
		// ����Ʋ
		g.drawImage(pan1, 4 + imageX, 492, 895, 452, this);
		g.drawImage(pan2, 4 + imageX, -10, 895, 446, this);
		// ����â
		g.drawImage(stateImage, 874 + imageX, 0, stateImage.getWidth(this) - 90, stateImage.getHeight(this) - 90, this);
		g.drawImage(stateImage, 874 + imageX, 505, stateImage.getWidth(this) - 90, stateImage.getHeight(this) - 90,
				this);
		// ��ų����
		g.drawImage(attackSkillNotice, aNoticeX, 398, 582, 130, this);
		g.drawImage(defenseSkillNotice, dNoticeX, 398, 582, 130, this);
		g.drawImage(strategySkillNotice, sNoticeX, 398, 582, 130, this);
		g.drawImage(attackFinishNotice, aFNoticeX, 398, 582, 130, this);
		g.drawImage(defenseFinishNotice, dFNoticeX, 398, 582, 130, this);
		g.drawImage(strategyFinishNotice, sFNoticeX, 398, 582, 130, this);
		// ��ų�̹���
		g.drawImage(attackSkillImage, aImageX, 280, getWidth(), attackSkillImage.getHeight(this), this);
		g.drawImage(attackFinishImage, aFImageX, 280, getWidth(), attackFinishImage.getHeight(this), this);
		g.drawImage(attackFPlus, 1010, plusY, attackFPlus.getWidth(this) / 2, attackFPlus.getHeight(this) / 2, this);
		g.drawImage(defenseSkillImage, dImageX, 280, getWidth(), defenseSkillImage.getHeight(this), this);
		g.drawImage(defenseFinishImage, dFImageX, 280, getWidth(), defenseFinishImage.getHeight(this), this);
		g.drawImage(strategySkillImage, sImageX, 280, getWidth(), strategySkillImage.getHeight(this), this);
		g.drawImage(strategyFinishImage, sFImageX, 280, getWidth(), strategyFinishImage.getHeight(this), this);
		// ������
		g.drawImage(endBack, endBackX, 235, 750, 510, this);
		g.drawImage(boom, boomX, 240, 800, 400, this);
		// ����
		g.drawImage(won, wonX, 200, getWidth(), won.getHeight(this), this);
		g.drawImage(lose, loseX, 200, getWidth(), lose.getHeight(this), this);
	}

	// ���ֱ� ��밡�� Ƚ�� �� �ܿ������� ���� ǥ�� �޼ҵ� (������� ��ư Ŭ���� ����)
	public void laSetting(JLabel command, JLabel att, JLabel def, JLabel trick) {
		command.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
		att.setText("x" + String.valueOf(GameProcess.numOfBingo1[0] + GameProcess.usingAttackSkill1));
		def.setText("x" + String.valueOf(GameProcess.numOfBingo1[1] + GameProcess.usingDefenseSkill1));
		trick.setText("x" + String.valueOf(GameProcess.numOfBingo1[2] + GameProcess.usingStrategySkill1));
	}

	public void bingoEndProcess() {
		imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
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

	class AImageThread extends Thread // ���ݽ�ų �̹��� ������
	{
		public void run() {
			GameLayout.aNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.aImageX >= 0) {
				try {
					GameLayout.aImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {}
			GameLayout.aImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
		}
	}

	class DImageThread extends Thread // ��ų �̹��� ������
	{
		public void run() {
			GameLayout.dNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.dImageX >= 0) {
				try {
					GameLayout.dImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {}
			GameLayout.dImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
		}
	}

	class SImageThread extends Thread // ������ų �̹��� ������
	{
		public void run() {
			GameLayout.sNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.sImageX >= 0) {
				try {
					GameLayout.sImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {}
			GameLayout.sImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
		}
	}

	class AFImageThread extends Thread // �����ʻ�� �̹��� ������
	{
		public void run() {
			GameLayout.aFNoticeX -= 1190; // ���Ӽ��� ��������
			try {
				Thread.sleep(1500); // 1.5�� ��
			} catch (Exception ex) {}
			GameLayout.aFNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.aFImageX >= 0) {
				try {
					GameLayout.aFImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {
			}
			GameLayout.aFImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
			laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));
			laCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
			GameLayout.plusY -= 170;
			while (GameLayout.plusY > 810) // �������,���ֱ� ���� �÷���ǥ��
			{
				try {
					GameLayout.plusY--;
					Thread.sleep(50);
				} catch (Exception ex) {}
			}
			GameLayout.plusY = 1000;
		}
	}

	class DFImageThread extends Thread // ����ʻ�� �̹��� ������
	{
		public void run() {
			GameLayout.dFNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.dFImageX >= 0) {
				try {
					GameLayout.dFImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {}
			GameLayout.dFImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
		}
	}

	class SFImageThread extends Thread // �����ʻ�� �̹��� ������
	{
		public void run() {
			GameLayout.sFNoticeX += 1190; // ���Ӽ����� ��������
			imageVisibleFalse(); // ����� ������ ��� ������Ʈ�� ����� ��
			while (GameLayout.sFImageX >= 0) {
				try {
					GameLayout.sFImageX -= 3; // ��ų�̹����� ���ƿ�
					Thread.sleep(1);
					bingoTurnIcon1.setVisible(false); //�� ǥ�ð� �ٲ�
					bingoTurnIcon2.setVisible(false); //��ų �̹����� ���ư��� ������ �Ⱥ���
				} catch (Exception ex) {
				}
			}
			try {
				Thread.sleep(1000); // 1�ʰ� �����̹��� �����ֱ�
			} catch (Exception ex) {
			}
			GameLayout.sFImageX = 1200; // �����̹��� ���ֱ�
			imageVisibleTrue();
		}
	}

	public static class TimeLimit extends Thread {
		int cnt = 0;
		int[] rgb = new int[3];
		int percent = 0; // �ð����ѹٸ� ä��� �ۼ�Ʈ (20��:100�ۼ�Ʈ ��, 0.2��: 1�ۼ�Ʈ)
		double residueTime = 20; // �����ð�ǥ�� (�ʱⰪ:20��)

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
				while (true)// timeRun)//timeRun�� false�϶� ���� (������,�׺�,������)
				{
					cnt++;
					percent++;
					if (percent > 100)// 100�ۼ�Ʈ�� �Ǹ� �ٽ� 0���� �ʱ�ȭ
					{
						if (GameProcess.playerTurn)// ���� �ٲ�
						{
							GameProcess.playerTurn = false;
							GameProcess.skillChance2=1;
							GameProcess.bingoCheckChance2=1;
							youLaTactic.setText("�������x" + String.valueOf(GameProcess.skillChance2));
							youLaCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance2));
						}
						else if (!GameProcess.playerTurn)// ���� �ٲ�
						{
							GameProcess.playerTurn = true;
							GameProcess.skillChance1=1;
							GameProcess.bingoCheckChance1=1;
							laTactic.setText("�������x" + String.valueOf(GameProcess.skillChance1));
							laCommand.setText("���ֱ�x" + String.valueOf(GameProcess.bingoCheckChance1));
						}
						percent = 0;
						residueTime = 20;
						colorInt = 0;
						rgb[0] = colorInt;
						rgb[1] = 255;
						IFNoticeVisible(); // �ð��� ������ ���Ӽ����� ���� ��ų�����false�� ��
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
						timer.setString("�������:" + rr);
					} else {
						String rr = rt;
						timer.setString("�������:" + rr);
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

	class paintThread extends Thread // �ð������� ������ ��ų������ ���ֱ� ���� repaint()���ִ� ������
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

	class endThread extends Thread // �����ʻ�� �̹��� ������
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