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
	//�ð����� Ÿ�̸� 
	static JProgressBar timer=new JProgressBar();  
	static boolean timeRun=true;
	static int colorInt=0;
	//������ ��ư  
	static JButton timeOut=new JButton();  
	static ImageIcon timeImg=new ImageIcon("img\\������_�⺻.png");  
	static ImageIcon timeImg2=new ImageIcon("img\\������_Ŀ��.png");  
	//�׺� ��ư  
	static JButton exit=new JButton();  
	static ImageIcon exitImg=new ImageIcon("img\\�׺�.png");  
	static ImageIcon exitImg2=new ImageIcon("img\\�׺�_Ŀ��.png");  
	//���ȭ��
	Image bg; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image vs; //���  vs �ؽ�Ʈ
	Image pan1; //����Ʋ �÷��̾�1
	Image pan2; //����Ʋ �÷��̾�2
	Image stateImage; // ����â �̹���
	//��ų�����̹����� 
	Image attackSkillNotice, attackFinishNotice;              
	Image defenseSkillNotice, defenseFinishNotice;             
	Image strategySkillNotice, strategyFinishNotice;
	//��ų�̹�����
	Image attackSkillImage, attackFinishImage;
	Image defenseSkillImage, defenseFinishImage;
	Image strategySkillImage, strategyFinishImage;
	//�����̹���
	Image won, lose;
	//�̹��� ��ǥ����
	static int aNoticeX=1200, aFNoticeX=1200;
	static int dNoticeX=1200, dFNoticeX=1200;
	static int sNoticeX=1200, sFNoticeX=1200;
	static int aImageX=1200, aFImageX=1200;
	static int dImageX=1200, dFImageX=1200;
	static int sImageX=1200, sFImageX=1200;
	static int imageX=0, wonX=1200, loseX=1200;
	ChatInGame cig = new ChatInGame();

	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	//������ 
	static JButton[][] a1=new JButton[3][25]; //�� ��
	static JButton[][] a2=new JButton[3][25]; //�����
	
	//�����ۼ��ù�ư, �ƹ�Ÿ
	static JButton btnAtt;  // ���ݽ�ų��ư
	static JButton btnDef;  // ����ų��ư
	static JButton btnTrick;  // å����ų��ư
	static JButton btnAvatar; // �ƹ�Ÿ �̹��� �־��� ��ư
	
	static JButton youBtnAtt; // ����� ��ư�� ��ǻ� �̹��� ���Կ�
	static JButton youBtnDef;
	static JButton youBtnTrick;
	static JButton youBtnAvatar;
	
	static int useAtt,useDef,useTrick;
	
	//������ ���� Ȯ�� �� ���̵�
	static JLabel laAtt;  // ���ݽ�ų ���� Ȯ��
	static JLabel laDef;  // ����ų ���� Ȯ��
	static JLabel laTrick;  // å����ų ���� Ȯ��
	static JLabel laNickname;  // �г��� �Է��ϴ� ��
	static JLabel laTactic, laCommand; // �������, ���ֱ�
	
	static JLabel youLaAtt;   // ����.
	static JLabel youLaDef;
	static JLabel youLaTrick;
	static JLabel youLaNickname;
	static JLabel youLaTactic, youLaCommand;
		
	//�÷��̾� 1,2  ����Ǻ� ������ 
	static JProgressBar[][] gauge=new JProgressBar[2][3];
	//�÷��̾� 1,2 ����Ǻ� �ñر� Ȱ�� �ȳ���ư
	static JButton[][] fury=new JButton[2][3];
	
	//�÷��̾� 1,2 ����Ǻ� ����ȹ������ Ȯ���г�
	static JButton[][] bingoScore=new JButton[6][5];
	static JPanel[] bingoScorePan=new JPanel[6];
	static ImageIcon bingo1=new ImageIcon("img\\����-����.png");
	static ImageIcon bingo2=new ImageIcon("img\\����-���ٿϼ�.png");
	static ImageIcon attIcon=new ImageIcon("img\\��ų������-����.png");
	static ImageIcon defIcon=new ImageIcon("img\\��ų������-���.png");
	static ImageIcon trickIcon=new ImageIcon("img\\��ų������-å��.png");
	static ImageIcon avatarIcon=new ImageIcon("img\\m1.jpg");
	static ImageIcon youAvatarIcon=new ImageIcon("img\\m2.jpg");
	static ImageIcon defPGIcon=new ImageIcon("img\\�����ı��̹���.jpg");//����ʻ������� 
	static JButton defPGchoice1, defPGchoice2;
	static JPanel defPan1=new JPanel();
	static JPanel defPan2=new JPanel();
	static ImageIcon jypgLine=new ImageIcon("img\\�����ı��̹���.jpg");//å���ʻ�� ��ư�� ������ ������ �����ı���ư
	static ImageIcon jjgs=new ImageIcon("img\\��������̹���.jpg");//����ʻ�� ��ư�� ������ ������ ���������ư 
	static JButton[] jypgChoice=new JButton[6]; 
	static JPanel jypgPan1=new JPanel();
	static JPanel jypgPan2=new JPanel();
	static ImageIcon furyEndIcon=new ImageIcon("img\\�ñر����������.png");  
	static JButton[][] furyEndBtn=new JButton[2][3];
	static ImageIcon regameIcon=new ImageIcon("img\\���ӳ�-�絵��.jpg");
	static ImageIcon gameEndIcon=new ImageIcon("img\\���ӳ�-������.jpg");
	
	//�÷��̾� 2 ������ ���̾ƿ� (�����)
	static JPanel p=new JPanel();
	static JPanel p1=new JPanel();
	static JPanel p2=new JPanel();
	static JPanel p3=new JPanel();
	
	// �÷��̾� 1 ������ ���̾ƿ� (����)
	static JPanel pp=new JPanel();
	static JPanel pp1=new JPanel();
	static JPanel pp2=new JPanel();
	static JPanel pp3=new JPanel();
	
	// ��� ĳ���� â
	static JPanel j1=new JPanel(); //�÷��̾�2
	static JPanel j2=new JPanel(); //�÷��̾�1
		
	static ImageIcon bcIcon0=new ImageIcon("img\\����üũ-��.png"); //���˿� ����üũ �̹���
	static ImageIcon bcIcon1=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon2=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon enemyIcon=new ImageIcon("img\\������-���.png");
	
	static boolean bAttCheck1 = false; //true=��ų��밡��, false=���Ұ�
	static boolean bDefCheck1 = false;
	static boolean bTrcikCheck1 = false; 
	
	static boolean bAttCheck2 = false;
	static boolean bDefCheck2 = false;
	static boolean bTrcikCheck2 = false;
	
	static boolean goongUsable1[]=new boolean[3]; //�ñر� ��� �Ҹ�
	static boolean goongUsable2[]=new boolean[3];
	
	static boolean[][] panCheck1 = new boolean[3][25]; // ���ݱ��(��)�� �� �����ư(��)�� Ȯ���ϱ����� ����
	static boolean[][] panCheck2 = new boolean[3][25]; //panCheck1�� ���� ����ǿ� üũ�Ѱ�, 2�� ��밡 ������ üũ�� ��
	
	static JButton regame;
	static JButton gameEnd;
	/////////�����ư����
	public void Rand()
	{
		GameProcess.rand(); // GameProcess Ŭ���� �������� ����~ �����޼ҵ� ȣ��
		
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
		// ��ư�� ������ ������ ���߱� 
		a1[b][a-c].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
		a1[b][a-c].setBorderPainted(false); //��ư ��輱 ����
		a1[b][a-c].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		a1[b][a-c].setFocusPainted(false); //��ư���� ��� ����
		ImageIcon m1=new ImageIcon("img\\������-���.png");
		a2[b][a-c]= new JButton(m1);
		e.add(a2[b][a-c]);
		// ��ư�� ������ ������ ���߱� 
		a2[b][a-c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a-c].setBorderPainted(false);
		a2[b][a-c].setContentAreaFilled(false);
		a2[b][a-c].setFocusPainted(false);
	}
	///////////����â
	public void youStateWindow(){	
		youBtnAtt = new JButton(attIcon);
		youBtnDef = new JButton(defIcon);
		youBtnTrick = new JButton(trickIcon);
		youBtnAvatar = new JButton(youAvatarIcon);
		youLaAtt = new JLabel("x0");
		youLaDef = new JLabel("x0");
		youLaTrick = new JLabel("x0");
		youLaTactic = new JLabel("�������x"+GameProcess.skillChance2);
		youLaCommand = new JLabel("���ֱ�x"+GameProcess.bingoCheckChance2);
		youLaNickname = new JLabel("your���̵�");
		youLaNickname.setFont(new Font("�ü�ü",Font.PLAIN,35));

		JPanel p = new JPanel();
		p.add(youLaNickname);
		p.setBounds(920, 30, 240, 58);
		p.setOpaque(false);
		
		youBtnAtt.setBounds(903, 104, 60, 60);
		youLaAtt.setBounds(963, 109, 100, 60);
		youLaAtt.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		youBtnDef.setBounds(903, 164, 60, 60);
		youLaDef.setBounds(963, 169, 100, 60);
		youLaDef.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		youBtnTrick.setBounds(903, 224, 60, 60);
		youLaTrick.setBounds(963, 229, 100, 60);
		youLaTrick.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		youLaTactic.setBounds(903, 307, 160, 40);
		youLaTactic.setFont(new Font("�ü�ü", Font.BOLD,20));
		
		youLaCommand.setBounds(903, 347, 160, 40);
		youLaCommand.setFont(new Font("�ü�ü", Font.BOLD,20));
		
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
		laTactic = new JLabel("�������x"+GameProcess.skillChance1);
		laCommand = new JLabel("���ֱ�x"+GameProcess.bingoCheckChance1);
		laNickname = new JLabel("���̵�");
		laNickname.setFont(new Font("�ü�ü",Font.PLAIN,35));
		
		JPanel p = new JPanel();
		p.add(laNickname);
		p.setBounds(920, 539, 240, 58);
		p.setOpaque(false);
		
		btnAvatar.setBounds(1024,607,157,190);
		btnAvatar.setPreferredSize(new Dimension(avatarIcon.getIconWidth(), avatarIcon.getIconHeight()));
		imageSetting(btnAvatar);
		
		btnAtt.setBounds(903, 613, 60, 60);
		laAtt.setBounds(963, 618, 100, 60);
		laAtt.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		btnDef.setBounds(903, 673, 60, 60);
		laDef.setBounds(963, 678, 100, 60);
		laDef.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		btnTrick.setBounds(903, 733, 60, 60);
		laTrick.setBounds(963, 738, 100, 60);
		laTrick.setFont(new Font("�ü�ü",Font.BOLD,35));
		
		laTactic.setBounds(903, 815, 160, 40);
		laTactic.setFont(new Font("�ü�ü", Font.BOLD,20));
		
		laCommand.setBounds(903, 855, 160, 40);
		laCommand.setFont(new Font("�ü�ü", Font.BOLD,20));
		
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
	///////////////////////������
	GameLayout()
	{	
		//�ð����� �� �߰�  
		timer=new JProgressBar();  
		add(timer);
		timer.setBackground(Color.WHITE);
		timer.setMinimum(0);
		timer.setMaximum(100);
		timer.setStringPainted(true);
		timer.setFont(new Font("�������",Font.BOLD,16));
		timer.setBounds(888, 422, 210, 25);
		//�� �����ư �߰�  
		add(timeOut);  
		timeOut.setCursor(cur);  
		timeOut.setBounds(886, 449, 212, 55);  
		timeOut.setIcon(timeImg);  
		timeOut.setRolloverIcon(timeImg2);  
		timeOut.setBorderPainted(false);//��ư ��輱 ����  
		timeOut.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����  
		timeOut.setFocusPainted(false); //��ư���� ��� ����  
		timeOut.setPressedIcon(timeImg2);  
		  
		//�׺�(������) ��ư �߰�  
		add(exit);  
		exit.setCursor(cur);  
		exit.setBounds(1106, 420, exitImg.getIconWidth(), exitImg.getIconHeight());  
		exit.setIcon(exitImg);  
		exit.setRolloverIcon(exitImg2);  
		exit.setBorderPainted(false);//��ư ��輱 ����  
		exit.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����  
		exit.setFocusPainted(false); //��ư���� ��� ����  
		exit.setPressedIcon(exitImg2);  

		for(int i=0;i<3;i++) //�ñر� �ʱ�ȭ
		{
			goongUsable1[i]=true;
			goongUsable2[i]=true;
		}
		//�����ı� ��ư ����
		for(int i=0; i<6;i++) 
		{
			if(i<3)
			{
				jypgChoice[i]=new JButton(jypgLine);
				jypgChoice[i].setContentAreaFilled(false);
				jypgChoice[i].addActionListener(this);
				jypgChoice[i].setBorderPainted(false); //��ư ��輱 ����
				jypgPan2.add(jypgChoice[i]); //�÷��̾�2 �ǿ� ��ġ (choice 0,1,2)
				jypgChoice[i].setCursor(cur);
			}
			if(i>=3)
			{
				jypgChoice[i]=new JButton(jypgLine);
				jypgChoice[i].setContentAreaFilled(false);
				jypgChoice[i].addActionListener(this);
				jypgChoice[i].setBorderPainted(false); //��ư ��輱 ����
				jypgPan1.add(jypgChoice[i]);//�÷��̾�1 �ǿ� ��ġ (choice 3,4,5)
				jypgChoice[i].setCursor(cur);
			}
		}
		
		defPGchoice2=new JButton(jjgs); //����ʻ���ư ����
		defPGchoice2.setContentAreaFilled(false);
		defPGchoice2.addActionListener(this);
		defPGchoice2.setBorderPainted(false);
		defPan2.add(defPGchoice2);
		
		defPGchoice1=new JButton(jjgs);
		defPGchoice1.setContentAreaFilled(false);
		defPGchoice1.addActionListener(this);
		defPGchoice1.setBorderPainted(false);
		defPan1.add(defPGchoice1);
		
		defPan1.setLayout(new FlowLayout(FlowLayout.CENTER));//����ʻ�� ��ġ
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
		
		FlowLayout jypg=new FlowLayout(FlowLayout.LEFT,0,0);//�����ı� å���ʻ�� ���̾ƿ�
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
		Color PURPLE=new Color(95,0,255); //BLUE ��Ī�� ����� PURPLE�� ����-HJ
		Color[] color={RED,GREEN,PURPLE}; //BLUE ��Ī�� ����� PURPLE�� ����-HJ
		int[] xVal={130,413,688};
		int[] xVal2={245,528,803};
		
		//�����ھ� ������ ��ġ
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
		
		// �ʻ�� ��ư ���� (�ʱ�: setVisible(false), 100�ۼ�Ʈ ����: setVisible(true)
		String[] goong={"img\\��ų������-�����ʻ��.png",
				"img\\��ų������-����ʻ��.png",
				"img\\��ų������-å���ʻ��.png"};
		
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				fury[i][j]=new JButton(new ImageIcon(goong[j]));
				furyEndBtn[i][j]=new JButton(furyEndIcon);
				add(fury[i][j]);
				add(furyEndBtn[i][j]);
				fury[i][j].setBounds(xVal2[j]+imageX, i*508+74, 60, 60);
				fury[i][j].setBorderPainted(false); //��ư ��輱 ����
				fury[i][j].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
				fury[i][j].setFocusPainted(false); //��ư���� ��� ����
				fury[i][j].setEnabled(false);
				furyEndBtn[i][j].setBounds(xVal2[j]+imageX, i*508+74, 60, 60);
				furyEndBtn[i][j].setBorderPainted(false); //��ư ��輱 ����
				furyEndBtn[i][j].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
				furyEndBtn[i][j].setFocusPainted(false); //��ư���� ��� ����
				furyEndBtn[i][j].setVisible(false);
				fury[i][j].setCursor(cur);
			}
		}
		for(int i=0; i<2;i++) //�ʻ�� ���α׷�����
		{
			for(int j=0; j<3; j++)
			{
				gauge[i][j]=new JProgressBar();
				add(gauge[i][j]);
				gauge[i][j].setMinimum(0); //����� �ּҰ� ����
				gauge[i][j].setMaximum(100); //����� �ִ밪 ����
				gauge[i][j].setStringPainted(true); //������� �ۼ�Ƽ���� �����ֱ� ����
				gauge[i][j].setForeground(color[j]); //����� ������
				gauge[i][j].setBounds(xVal[j], i*507+85, 115, 40);
			}
		}
		//��������� ������ ��ư
		regame=new JButton(regameIcon);
		gameEnd=new JButton(gameEndIcon);
		imageSetting(regame);imageSetting(gameEnd);
		add(regame); add(gameEnd);
		regame.setVisible(false);gameEnd.setVisible(false);
		// �ΰ��� �̹���
		pan2=Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		pan1=Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		bg=Toolkit.getDefaultToolkit().getImage("img\\�ΰ��ӹ��.jpg");
		vs=Toolkit.getDefaultToolkit().getImage("img\\vs.png");
		stateImage=Toolkit.getDefaultToolkit().getImage("img\\����â.png");
		attackSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-����.jpg");
		defenseSkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-���.jpg");
		strategySkillNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-å��.jpg");
		attackFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-�����ʻ��.jpg");
		defenseFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-����ʻ��.jpg");
		strategyFinishNotice=Toolkit.getDefaultToolkit().getImage("img\\��ų����-å���ʻ��.jpg");
		attackSkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����.png");
		attackFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-�����ʻ��.png");
		defenseSkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-���.png");
		defenseFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-����ʻ��.png");
		strategySkillImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å��.png");
		strategyFinishImage=Toolkit.getDefaultToolkit().getImage("img\\��ų�̹���-å���ʻ��.png");
		won=Toolkit.getDefaultToolkit().getImage("img\\����-�¸�.png");
		lose=Toolkit.getDefaultToolkit().getImage("img\\����-�й�.png");
		//�̹���,��ư �� ��ġ
		setLayout(null);
		FlowLayout k=new FlowLayout(0); // 0: ��������, 1:�������, 2:����������
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
		p1.setLayout(new GridLayout(5,5,0,0)); //5by5��ĸ��, ��,�참�� 0
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
		Rand();//��ȣ ����
		
		p.setOpaque(false);p1.setOpaque(false);p2.setOpaque(false);p3.setOpaque(false);
		pp.setOpaque(false);pp1.setOpaque(false);pp2.setOpaque(false);pp3.setOpaque(false);
		
		stateWindow();//����â
		youStateWindow();
		
		setSize(1200,970);
		setVisible(true);
		
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<25; j++)
			{
				a1[i][j].setCursor(cur); //��ư�� ���콺�� �ø��� Ŀ�� ����� ������ �ٲ�
				a2[i][j].setCursor(cur);
				
				a1[i][j].addActionListener(this); //��ư �׼� �߰�
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
		for(int i=0; i<3;i++) //��� �� ��ư �׼Ǹ�����
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
		
		regame.addActionListener(this);//�絵��
		gameEnd.addActionListener(this);//������
		regame.setCursor(cur);
		gameEnd.setCursor(cur);
		
		timeOut.addActionListener(this); //������ ��ư 
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
		btn.setBorderPainted(false); //��ư ��輱 ����
		btn.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		btn.setFocusPainted(false); //��ư���� ��� ����
		btn.setOpaque(false);
	}
	//����� ������ ��� �̹��� �����/���̱�
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
		 for(int i=0; i<3;i++) //��� �� ��ư �׼Ǹ�����
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
		 for(int i=0; i<3;i++) //��� �� ��ư �׼Ǹ�����
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
		//����Ʋ
		g.drawImage(pan1, 4+imageX, 492, 895, 452, this);
		g.drawImage(pan2, 4+imageX, -10, 895, 446, this);
		//����â
		g.drawImage(stateImage, 874+imageX, 0, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
		g.drawImage(stateImage, 874+imageX, 505, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
		//��ų����
		g.drawImage(attackSkillNotice, aNoticeX, 398, 582,130,this);
		g.drawImage(defenseSkillNotice, dNoticeX, 398, 582,130,this);
		g.drawImage(strategySkillNotice, sNoticeX, 398, 582,130,this);
		g.drawImage(attackFinishNotice, aFNoticeX, 398, 582,130,this);
		g.drawImage(defenseFinishNotice, dFNoticeX, 398, 582,130,this);
		g.drawImage(strategyFinishNotice, sFNoticeX, 398, 582,130,this);
		//��ų�̹���
		g.drawImage(attackSkillImage, aImageX, 280, getWidth(), attackSkillImage.getHeight(this), this);
		g.drawImage(attackFinishImage, aFImageX, 280, getWidth(), attackFinishImage.getHeight(this), this);
		g.drawImage(defenseSkillImage, dImageX, 280, getWidth(), defenseSkillImage.getHeight(this), this);
		g.drawImage(defenseFinishImage, dFImageX, 280, getWidth(), defenseFinishImage.getHeight(this), this);
		g.drawImage(strategySkillImage, sImageX, 280, getWidth(), strategySkillImage.getHeight(this), this);
		g.drawImage(strategyFinishImage, sFImageX, 280, getWidth(), strategyFinishImage.getHeight(this), this);
		//����
		g.drawImage(won, wonX, 390, getWidth(), won.getHeight(this), this);
		g.drawImage(lose, loseX, 390, getWidth(), lose.getHeight(this), this);
	}
	//�̹��� ����

	
	public void laSetting(JLabel command, JLabel att, JLabel def, JLabel trick)//��ư�� Ŭ���� �� �ؽ�Ʈ�� �ٲ��ִ� �޼ҵ�
	{
		command.setText("���ֱ�x"+String.valueOf(GameProcess.bingoCheckChance1));
		att.setText("x"+String.valueOf(GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1));
		def.setText("x"+String.valueOf(GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1));
		trick.setText("x"+String.valueOf(GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//////////���� üũ(üũ�� ���� �ƴ� ��+��ų�������� Ŭ������ �ʾ��� ��)
		if(bAttCheck1==false&&bDefCheck1==false&&bTrcikCheck1==false
			&&bAttCheck2==false&&bDefCheck2==false&&bTrcikCheck2==false)
		{
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����,���� ������ �� üũ ����
						if(e.getSource()==a1[i][j]&&GameProcess.bingo1[i][j]==false&&GameProcess.playerTurn==true&&panCheck2[i][j]==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
									,GameProcess.bingo1,GameProcess.bingo2,a1,a2
									,bcIcon0,bcIcon0);
							GameProcess.bingoCheckChance1--;
							laCommand.setText("���ֱ�x"+String.valueOf(GameProcess.bingoCheckChance1));
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
			else if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
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
			else if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
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
				else if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
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
			
		////////////��ų��ư
		if(GameProcess.playerTurn==true)
		{
			if(e.getSource()==btnAtt && GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1>0)//�÷��̾�1 ���� ��ų ��ư
			{
				bAttCheck1 = true; //��ų��� �����ϰ� true
				aNoticeX-=1190; //��ų���� ��������(1200���� ȭ�� �ۿ� �ִ� ���� -1190 �ؼ� ȭ�鿡 ����)
				repaint(); //�ٽ� ȭ�鿡 �׷���
			}
			else if(e.getSource()==btnDef && GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1>0)//��ų
			{
				int k=0;
				for(int i=0;i<3;i++){
					for(int j=0;j<25;j++){
						if(panCheck1[i][j])
							k++;   
					}
				}
				if(k==0){ //���� �� �ɸ� �������� ������, ������ ��ư�� ������� ����
					JOptionPane.showMessageDialog(this, "���ع��� ������ �����ϴ�.");
					return;
				}
				bDefCheck1 = true;
				dNoticeX-=1190;
				repaint();
			}
			else if(e.getSource()==btnTrick && GameProcess.numOfBingo1[2]+GameProcess.usingStrategySkill1>0)//å����ų
			{
				bTrcikCheck1=true;
				sNoticeX-=1190;
				repaint();
			}
			else if(e.getSource()==fury[1][0]) //�����ʻ�� ��ư
			{
				new AFImageThread().start();
				GameProcess.bingoCheckChance1++;//���ݱ�ȸ+1,�����ۻ���ȸ+1
				GameProcess.skillChance1++;
				goongUsable1[0]=false;
				fury[1][0].setEnabled(false);
				gauge[1][0].setBackground(Color.DARK_GRAY);
				gauge[1][0].setString("�ñر����");
				laTactic.setText("�������x"+String.valueOf(GameProcess.skillChance1));
				laCommand.setText("���ֱ�x"+String.valueOf(GameProcess.bingoCheckChance1));
			}
			else if(e.getSource()==fury[1][1]) //����ʻ�� ��ư
			{
				int k=0;
				if(GameProcess.numOfBingo2[0]+GameProcess.usingAttackSkill2<=0
						&&GameProcess.numOfBingo2[1]+GameProcess.usingDefenseSkill2<=0
						&&GameProcess.numOfBingo2[2]+GameProcess.usingStrategySkill2<=0)
				{ //���� ��� �������� ������, �ʻ�� ��ư�� ������� ����
					JOptionPane.showMessageDialog(this, "�ı��� �������Ⱑ �����ϴ�.");
					return;
				}
				dFNoticeX-=1190;
				if(goongUsable1[1])
				{  
					defPan2.setVisible(true);  
				}  
				repaint();
			}
			else if(e.getSource()==fury[1][2]) //å���ʻ�� ��ư
			{
				if(goongUsable1[2])
				{
					sFNoticeX-=1190;
					jypgPan2.setVisible(true);
					repaint();
				}
				
			}
		}
		
		//�÷��̾�2
		if(GameProcess.playerTurn==false)
		{
			if(e.getSource()==youBtnAtt)//�÷��̾�2 ���� ��ų ��ư
			{
				
			}
			else if(e.getSource()==youBtnDef)//��ų
			{
				
			}
			else if(e.getSource()==youBtnTrick)//å����ų
			{
				
			}
			else if(e.getSource()==fury[0][0]) //�÷��̾�2 �����ʻ�� ��ư
			{
				
			}
			else if(e.getSource()==fury[0][1]) //����ʻ�� ��ư
			{
				
			}
			else if(e.getSource()==fury[0][2]) //å���ʻ�� ��ư
			{
					
			}
		}
		
		//����,���,å����ų
		for(int i=0;i<3;i++){
			for(int j=0;j<25;j++){
				if(e.getSource()==a2[i][j]&&bAttCheck1&&!GameProcess.bingo2[i][j]&&!panCheck1[i][j]){//���ݽ�ų
					new AImageThread().start();
					a2[i][j].setIcon(new ImageIcon("img\\����üũ-��.png"));
					GameProcess.usingAttackSkill1--;
					useAtt--;
					laAtt.setText("x"+String.valueOf(GameProcess.numOfBingo1[0]+GameProcess.usingAttackSkill1));
					bAttCheck1 = false;
					GameProcess.skillChance1--;
					laTactic.setText("�������x"+String.valueOf(GameProcess.skillChance1));
					panCheck1[i][j] = true; // ������ �� Ȯ��.
					repaint();
				}else if(e.getSource()==a2[i][j]&&bDefCheck1&&panCheck1[i][j]){//��ų
					new DImageThread().start();
					GameProcess.usingDefenseSkill1--;
					useDef--;
					laDef.setText("x"+String.valueOf(GameProcess.numOfBingo1[1]+GameProcess.usingDefenseSkill1));
					if(GameProcess.bingo2[i][j]==false){
						a2[i][j].setIcon(new ImageIcon("img\\"+(GameProcess.p2Board[i][j])+".png"));
					}else if(GameProcess.bingo2[i][j]){
						if(ChoiceNation.chosenNation2==0)
							a2[i][j].setIcon(new ImageIcon("img\\����üũ-��.png"));
						else if(ChoiceNation.chosenNation2==1)
							a2[i][j].setIcon(new ImageIcon("img\\����üũ-��.png"));
						else if(ChoiceNation.chosenNation2==2)
							a2[i][j].setIcon(new ImageIcon("img\\����üũ-��.png"));
					}
					
					bDefCheck1 = false;
					GameProcess.skillChance1--;
					laTactic.setText("�������x"+String.valueOf(GameProcess.skillChance1));
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
		//�����ʻ�� Ŭ�� ��, ����� ��ư Ŭ��
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
			gauge[1][1].setString("�ñر����");
			defPan2.setVisible(false);
			furyEndBtn[1][1].setVisible(true);
		}
		//å���ʻ�� Ŭ�� ��, ��� �� �����ı� ��ư Ŭ��
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
				gauge[1][2].setString("�ñر����");
				jypgPan2.setVisible(false);
				furyEndBtn[1][2].setVisible(true);
				GameProcess.skillChance1--;
				laTactic.setText("�������x"+String.valueOf(GameProcess.skillChance1));
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
				gauge[0][2].setString("�ñر����");
				jypgPan1.setVisible(false);
				furyEndBtn[0][2].setVisible(true);
				GameProcess.skillChance2--;
				laTactic.setText("�������x"+String.valueOf(GameProcess.skillChance2));
				repaint();
			}
		}
		if(e.getSource()==timeOut)//������  
		{  
			ClientMainForm.t1.interrupt();  
			ClientMainForm.t1=new TimeLimit();  
			ClientMainForm.t1.start();  
		}  
		//�絵��
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
			GameLayout.aNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.aImageX>=0)
			{
				try
				{
					GameLayout.aImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.aImageX=1200; //�����̹��� ���ֱ�
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
			GameLayout.dNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.dImageX>=0)
			{
				try
				{
					GameLayout.dImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.dImageX=1200; //�����̹��� ���ֱ�
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
			GameLayout.sNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.sImageX>=0)
			{
				try
				{
					GameLayout.sImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.sImageX=1200; //�����̹��� ���ֱ�
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
			GameLayout.aFNoticeX-=1190; //���Ӽ��� ��������
			repaint();
			try
			{	
				Thread.sleep(1500); //2�� ��
			}catch(Exception ex){}
			GameLayout.aFNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.aFImageX>=0)
			{
				try
				{
					GameLayout.aFImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.aFImageX=1200; //�����̹��� ���ֱ�
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
			GameLayout.dFNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.dFImageX>=0)
			{
				try
				{
					GameLayout.dFImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.dFImageX=1200; //�����̹��� ���ֱ�
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
			GameLayout.sFNoticeX+=1190; //���Ӽ����� ��������
			repaint();
			imageVisibleFalse(); //����� ������ ��� ������Ʈ�� ����� ��
			while(GameLayout.sFImageX>=0)
			{
				try
				{
					GameLayout.sFImageX-=3; //��ų�̹����� ���ƿ�
					Thread.sleep(1);
					repaint();
				}catch(Exception ex){}
			}
			try
			{
				Thread.sleep(1000); //2�ʰ� �����̹��� �����ֱ�
			}catch(Exception ex){}
			GameLayout.sFImageX=1200; //�����̹��� ���ֱ�
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
		int percent=0; //�ð����ѹٸ� ä��� �ۼ�Ʈ (20��:100�ۼ�Ʈ ��, 0.2��: 1�ۼ�Ʈ) 
		double residueTime=20; //�����ð�ǥ�� (�ʱⰪ:20��) 
		public void run() 
		{
			timeRun=true; 
			try  
			{ 	 
				while(timeRun)//timeRun�� false�϶� ���� (������,�׺�,������) 
				{	   
				  cnt++; 
				  percent++; 
				  if(percent>100)//100�ۼ�Ʈ�� �Ǹ� �ٽ� 0���� �ʱ�ȭ 
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
					  timer.setString("�������:"+rr); 
				  } 
				  else 
				  { 
					  String rr=rt; 
					  timer.setString("�������:"+rr); 
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