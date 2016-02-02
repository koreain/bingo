package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel implements ActionListener, KeyListener{
	//���ȭ��
	Image bg; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image vs; //���  vs �ؽ�Ʈ
	Image pan1; //����Ʋ �÷��̾�1
	Image pan2; //����Ʋ �÷��̾�2
	Image stateImage; // ����â �̹���
	
	ChatInGame cig = new ChatInGame();
	ChoiceNation cn=new ChoiceNation();
	static int jypgUseCnt=0;
	

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
		
	static ImageIcon bcIcon0=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon1=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon2=new ImageIcon("img\\����üũ-��.png");
	
	
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
		ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[a]+".png");
		a2[b][a-c]= new JButton(m1);
		e.add(a2[b][a-c]);
		// ��ư�� ������ ������ ���߱� 
		a2[b][a-c].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
		a2[b][a-c].setBorderPainted(false);
		a2[b][a-c].setContentAreaFilled(false);
		a2[b][a-c].setFocusPainted(false);
	}
	
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

	GameLayout()
	{	
		Color RED=new Color(255,0,0);
		Color GREEN=new Color(0,147,0);
		Color PURPLE=new Color(95,0,255); //BLUE ��Ī�� ����� PURPLE�� ����-HJ
		Color[] color={RED,GREEN,PURPLE}; //BLUE ��Ī�� ����� PURPLE�� ����-HJ
		int[] xVal={130,413,688};
		int[] xVal2={245,528,803};
		
		String[] goong={"img\\��ų������-�����ʻ��.png",
						"img\\��ų������-����ʻ��.png",
						"img\\��ų������-å���ʻ��.png"};
		
		for(int i=0; i<6; i++)
		{	bingoScorePan[i]=new JPanel();
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
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<3; j++)
			{
				fury[i][j]=new JButton(new ImageIcon(goong[j]));
				add(fury[i][j]);
				fury[i][j].setBounds(xVal2[j], i*508+74, 60, 60);
				fury[i][j].setBorderPainted(false); //��ư ��輱 ����
				fury[i][j].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
				fury[i][j].setFocusPainted(false); //��ư���� ��� ����
				fury[i][j].setEnabled(false);
			}
		}
		for(int i=0; i<2;i++)
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
		
		// ���ȭ��
		pan2=Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		pan1=Toolkit.getDefaultToolkit().getImage("img\\����Ʋ.png");
		bg=Toolkit.getDefaultToolkit().getImage("img\\�ΰ��ӹ��.jpg");
		vs=Toolkit.getDefaultToolkit().getImage("img\\vs.png");
		stateImage=Toolkit.getDefaultToolkit().getImage("img\\����â.png");
		
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
				
		Rand();
		
		p.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		pp.setOpaque(false);
		pp1.setOpaque(false);
		pp2.setOpaque(false);
		pp3.setOpaque(false);
		
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
		addKeyListener(this);
		setFocusable(true);
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343, 423, vs.getWidth(this),80,this);
		g.drawImage(pan1, 4, 492, 895, 452, this);
		g.drawImage(pan2, 4, -10, 895, 446, this);
		g.drawImage(stateImage, 874, 0, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
		g.drawImage(stateImage, 874, 505, stateImage.getWidth(this)-90, stateImage.getHeight(this)-90,this);
	}
	//�̹��� ����
	public void imageSetting(JButton btn)
	{
		btn.setBorderPainted(false); //��ư ��輱 ����
		btn.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		btn.setFocusPainted(false); //��ư���� ��� ����
		btn.setOpaque(false);
	}
	

	//��ư ������ ����üũ!!!!!!!!
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//���� üũ(üũ�� ���� �ƴ� ��+��ų�������� Ŭ������ �ʾ��� ��)
		if(GameProcess.bAttackSkill1==false&&GameProcess.bAttackFinish1==false&&GameProcess.bDefenseSkill1==false
				&&GameProcess.bDefenseFinish1==false&&GameProcess.bStrategySkill1==false&&GameProcess.bStrategyFinish1==false)
		{
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����,���� ������ �� üũ ����
						if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
									,GameProcess.bingo1,GameProcess.bingo2,a1,a2
									,bcIcon0,bcIcon0,GameProcess.bingoCheckChance1);
						}
						else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
									,GameProcess.bingo2,GameProcess.bingo1,a2,a1
									,bcIcon0,bcIcon0,GameProcess.bingoCheckChance2);
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
						if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
									,GameProcess.bingo1,GameProcess.bingo2,a1,a2
									,bcIcon0,bcIcon1,GameProcess.bingoCheckChance1);
						}
						else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
						{
							GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
									,GameProcess.bingo2,GameProcess.bingo1,a2,a1
									,bcIcon1,bcIcon0,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon0,bcIcon2,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon0,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon0,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon0,bcIcon1,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon1,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon1,bcIcon1,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon1,bcIcon2,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon1,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon0,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon0,bcIcon2,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon1,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon1,bcIcon2,GameProcess.bingoCheckChance2);
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
							if(e.getSource()==a1[i][j]&&GameProcess.playerTurn==true)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p1Board,GameProcess.p2Board
										,GameProcess.bingo1,GameProcess.bingo2,a1,a2
										,bcIcon2,bcIcon2,GameProcess.bingoCheckChance1);
							}
							else if(e.getSource()==a2[i][j]&&GameProcess.playerTurn==false)
							{
								GameProcess.bingoCheck(i,j,GameProcess.p2Board,GameProcess.p1Board
										,GameProcess.bingo2,GameProcess.bingo1,a2,a1
										,bcIcon2,bcIcon2,GameProcess.bingoCheckChance2);
							}
						}
					}
				}
				
				if(GameProcess.playerTurn==true)
				{
					if(e.getSource()==btnAtt)//�÷��̾�1 ���� ��ų ��ư
					{
						GameProcess.bAttackSkill1=true;
					}
					else if(e.getSource()==btnDef)//��ų
					{
						
					}
					else if(e.getSource()==btnTrick)//å����ų
					{
						
					}
					else if(e.getSource()==fury[1][0]) //�����ʻ�� ��ư
					{
//			    	  new AttackFinishThread().start();
//			    	  GameProcess.checkTurn1++;//���ݱ�ȸ+1,�����ۻ���ȸ+1
//			    	  GameProcess.skillTurn1++;
//			    	  fury[1][0].setEnabled(false);//��ư ��� �Ұ�
//			    	  GameProcess.usingAttackFinish1+=3;
					}
					else if(e.getSource()==fury[1][1]) //����ʻ�� ��ư
					{
						
					}
					else if(e.getSource()==fury[1][2]) //å���ʻ�� ��ư
					{
						int[] arr={0,1,2,3,4,5};
						jypgUseCnt=1;
						String inputChoice;
						inputChoice=JOptionPane.showInputDialog(arr);
						Integer inputChoiceNum=Integer.parseInt(inputChoice);
						System.out.println(inputChoice);
						for(int k=0; k<6;k++)
						{
							  if(inputChoiceNum==k&&k<3)
							  {
								  GameProcess.jypg(0,k);
								  break;
							  }
							  if(inputChoiceNum==k&&k>=3)
							  {
								  GameProcess.jypg(1,k%3);
								  break;
							  }
						}
					}
				}

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
//			    	  GameProcess.checkTurn2++;
//			    	  GameProcess.skillTurn2++;
//			    	  fury[0][0].setEnabled(false);
//			    	  GameProcess.usingAttackFinish2+=3;
					}
					else if(e.getSource()==fury[0][1]) //����ʻ�� ��ư
					{
						
					}
					else if(e.getSource()==fury[0][2]) //å���ʻ�� ��ư
					{
						
					}
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
}
