package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel implements ActionListener{
	
	//���ȭ��
	Image bg; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image vs; //���  vs �ؽ�Ʈ
	Image pan1; //����Ʋ �÷��̾�1
	Image pan2; //����Ʋ �÷��̾�2
	
	
	//������ 
	static JButton[][] a1=new JButton[3][25]; //�� ��
	static JButton[][] a2=new JButton[3][25]; //�����
	
	//�÷��̾� 1,2  ����Ǻ� ������ 
	static JProgressBar[][] gauge=new JProgressBar[2][3];
	//�÷��̾� 1,2 ����Ǻ� �ñر� Ȱ�� �ȳ���ư
	static JButton[][] fury=new JButton[2][3];
	
	//�÷��̾� 1,2 ����Ǻ� ����ȹ������ Ȯ���г�
	static JButton[][] bingoScore=new JButton[6][5];
	static JPanel[] bingoScorePan=new JPanel[6];
	static ImageIcon bingo1=new ImageIcon("img\\����-����.png");
	static ImageIcon bingo2=new ImageIcon("img\\����-���ٿϼ�.png");
	
	
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
	
	
	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
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
		a2[b][a-c].setBorderPainted(false); //��ư ��輱 ����
		a2[b][a-c].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		a2[b][a-c].setFocusPainted(false); //��ư���� ��� ����
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
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(vs, 343, 423, vs.getWidth(this),80,this);
		g.drawImage(pan1, 4, 492, 895, 452, this);
		g.drawImage(pan2, 4, -10, 895, 446, this);
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
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon0); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon0); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==0&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon0); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon1); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon1); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==1&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon1); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==0)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon2); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon0);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==1)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon2); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon1);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
			if(ChoiceNation.chosenNation1==2&&ChoiceNation.chosenNation2==2)//�������� : ��vs��
			{
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<25; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[i][j];
							if(GameProcess.p1Board[i][j]==GameProcess.chosenBingoNumber) //������ ���ڿ� �������� ���ڰ� ������
				            {
				               a1[i][j].setIcon(bcIcon2); //�� �ڸ� ��ư�� �������� �ٲ��ְ�
				               GameProcess.bingo1[i][j]=true; //���� üũ�Ȱɷ�(true��)ǥ��
				            }
							for(int k=0; k<3; k++)
							{
								for(int l=0; l<25; l++)
								{
									if(GameProcess.p2Board[k][l]==GameProcess.chosenBingoNumber)
									{
										a2[k][l].setIcon(bcIcon2);
										GameProcess.bingo2[k][l]=true;
									}
								}
							}
							GameProcess.lineCount();
						}
					}
				}
			}
		}
	}
}
