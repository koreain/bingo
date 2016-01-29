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
	
	//������ 
	static JButton[][] a1=new JButton[3][25]; //�� ��
	static JButton[][] a2=new JButton[3][25]; //�����
	
	//�÷��̾� 1,2  ����Ǻ� ������ 
	static JProgressBar[][] gauge=new JProgressBar[2][3]; 
	
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
	
	static JPanel j1=new JPanel();
	static JPanel j2=new JPanel();
	
	
	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	static ImageIcon bcIcon0=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon1=new ImageIcon("img\\����üũ-��.png");
	static ImageIcon bcIcon2=new ImageIcon("img\\����üũ-��.png");
	
	

	public void Rand()
	{
		GameProcess.rand(); // GameProcess Ŭ���� �������� ����~ �����޼ҵ� ȣ��
		
		for(int i=0; i<75;i++)
		{
		  if(i<25)	
		  {
			// �������� ������ ����numArr1�� ���� ���ھ����� ��ư�� �߰� 
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a1[0][i]= new JButton(m);
			pp1.add(a1[0][i]);
			// ��ư�� ������ ������ ���߱� 
			a1[0][i].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[0][i].setBorderPainted(false); //��ư ��輱 ����
			a1[0][i].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
			a1[0][i].setFocusPainted(false); //��ư���� ��� ����
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a2[0][i]= new JButton(m1);
			p1.add(a2[0][i]);
			// ��ư�� ������ ������ ���߱� 
			a2[0][i].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a2[0][i].setBorderPainted(false); //��ư ��輱 ����
			a2[0][i].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
			a2[0][i].setFocusPainted(false); //��ư���� ��� ����
		  }
		  else if((i>=25)&&(i<50))
		  {
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a1[1][i-25]= new JButton(m);
			pp2.add(a1[1][i-25]);
			a1[1][i-25].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[1][i-25].setBorderPainted(false);
			a1[1][i-25].setContentAreaFilled(false); 
			a1[1][i-25].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a2[1][i-25]= new JButton(m1);
			p2.add(a2[1][i-25]);
			a2[1][i-25].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a2[1][i-25].setBorderPainted(false);
			a2[1][i-25].setContentAreaFilled(false); 
			a2[1][i-25].setFocusPainted(false);
		  }
		  else//(i>=50)
		  {
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a1[2][i-50]= new JButton(m);
			pp3.add(a1[2][i-50]);  
			a1[2][i-50].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[2][i-50].setBorderPainted(false);
			a1[2][i-50].setContentAreaFilled(false); 
			a1[2][i-50].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a2[2][i-50]= new JButton(m1);
			p3.add(a2[2][i-50]);  
			a2[2][i-50].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a2[2][i-50].setBorderPainted(false);
			a2[2][i-50].setContentAreaFilled(false); 
			a2[2][i-50].setFocusPainted(false);
		  }
		}
	}

	GameLayout()
	{	
		Color RED=new Color(255,0,0);
		Color GREEN=new Color(0,147,0);
		Color BLUE=new Color(95,0,255);
		Color[] color={RED,GREEN,BLUE};
		int[] xVal={115,398,673};
		
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
				gauge[i][j].setBounds(xVal[j], i*465+75, 165, 50);
			}
			
		}
		
		// ���ȿ�� �� ����
		JLabel eft1 = new JLabel();
		JLabel eft2 = new JLabel();
		JLabel eft3 = new JLabel();
		JLabel eft4 = new JLabel();
		JLabel eft5 = new JLabel();
		JLabel eft6 = new JLabel();
		add(eft1); add(eft2); add(eft3);
		add(eft4); add(eft5); add(eft6);
		Font f1 = new Font("�ü�",Font.BOLD,18);
		eft1.setText("�屺ȿ��: ��  ��");
		eft2.setText("�屺ȿ��: �������");
		eft3.setText("�屺ȿ��: �����ı�");
		eft4.setText("�屺ȿ��: ��  ��");
		eft5.setText("�屺ȿ��: �������");
		eft6.setText("�屺ȿ��: �����ı�");
		eft1.setFont(f1);
		eft2.setFont(f1);
		eft3.setFont(f1);
		eft4.setFont(f1);
		eft5.setFont(f1);
		eft6.setFont(f1);
		eft1.setBounds(120, 30, 188, 60); eft2.setBounds(398, 30, 188, 60); eft3.setBounds(673, 30, 188, 60);
		eft4.setBounds(120, 495, 188, 60); eft5.setBounds(398, 495, 188, 60); eft6.setBounds(673, 495, 188, 60);
		
		// ���ȭ��
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
		//j1.setOpaque(false);
		j1.setLayout(j0);
		add(j2);
		j2.setLayout(j0);
		//j2.setOpaque(false);
		j2.setBackground(null);
		j1.setBounds(20, 30, 836, 100);
		j2.setBounds(20, 495, 836, 100);
		p.setBounds(5,130,900,275);
		pp.setBounds(5, 595, 900, 275);
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
		g.drawImage(vs, 500, 400, vs.getWidth(this),vs.getHeight(this),this);
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
