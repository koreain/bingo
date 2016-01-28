package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JProgressBar;
import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel implements ActionListener{
	
	//���ȭ��
	Image bg; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image vs;
	
	//������ 
	static JButton[][] a4=new JButton[5][5]; //��� ��
	static JButton[][] a5=new JButton[5][5];
	static JButton[][] a6=new JButton[5][5];
	
	static JButton[][] a1=new JButton[5][5]; //�� ��
	static JButton[][] a2=new JButton[5][5];
	static JButton[][] a3=new JButton[5][5];
		
	//�÷��̾� 1 ����Ǻ� ���̵�/������
	static JProgressBar jang11=new JProgressBar();
	static JProgressBar jang12=new JProgressBar();
	static JProgressBar jang13=new JProgressBar();
	
	//�÷��̾� 2 ����Ǻ� ���̵�/������
	static JProgressBar jang21=new JProgressBar();
	static JProgressBar jang22=new JProgressBar();
	static JProgressBar jang23=new JProgressBar();
	
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
	
	ImageIcon bcIcon0=new ImageIcon("img\\����üũ-��.png");
	ImageIcon bcIcon1=new ImageIcon("img\\����üũ-��.png");
	ImageIcon bcIcon2=new ImageIcon("img\\����üũ-��.png");
	
	public void Rand()
	{
		GameProcess.rand(); // GameProcess Ŭ���� �������� ����~ �����޼ҵ� ȣ��
		
		for(int i=0; i<75;i++)
		{
		  if(i<25)	
		  {
			// �������� ������ ����numArr1�� ���� ���ھ����� ��ư�� �߰� 
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a1[i/5][i%5]= new JButton(m);
			pp1.add(a1[i/5][i%5]);
			// ��ư�� ������ ������ ���߱� 
			a1[i/5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[i/5][i%5].setBorderPainted(false); //��ư ��輱 ����
			a1[i/5][i%5].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
			a1[i/5][i%5].setFocusPainted(false); //��ư���� ��� ����
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a4[i/5][i%5]= new JButton(m1);
			p1.add(a4[i/5][i%5]);
			// ��ư�� ������ ������ ���߱� 
			a4[i/5][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a4[i/5][i%5].setBorderPainted(false); //��ư ��輱 ����
			a4[i/5][i%5].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
			a4[i/5][i%5].setFocusPainted(false); //��ư���� ��� ����
		  }
		  else if((i>=25)&&(i<50))
		  {
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a2[(i/5)-5][i%5]= new JButton(m);
			pp2.add(a2[(i/5)-5][i%5]);
			a2[(i/5)-5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a2[(i/5)-5][i%5].setBorderPainted(false);
			a2[(i/5)-5][i%5].setContentAreaFilled(false); 
			a2[(i/5)-5][i%5].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a5[(i/5)-5][i%5]= new JButton(m1);
			p2.add(a5[(i/5)-5][i%5]);
			a5[(i/5)-5][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a5[(i/5)-5][i%5].setBorderPainted(false);
			a5[(i/5)-5][i%5].setContentAreaFilled(false); 
			a5[(i/5)-5][i%5].setFocusPainted(false);
		  }
		  else//(i>=50)
		  {
			ImageIcon m=new ImageIcon("img\\"+GameProcess.numArr1[i]+".png");
			a3[(i/5)-10][i%5]= new JButton(m);
			pp3.add(a3[(i/5)-10][i%5]);  
			a3[(i/5)-10][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a3[(i/5)-10][i%5].setBorderPainted(false);
			a3[(i/5)-10][i%5].setContentAreaFilled(false); 
			a3[(i/5)-10][i%5].setFocusPainted(false);
			ImageIcon m1=new ImageIcon("img\\"+GameProcess.numArr2[i]+".png");
			a6[(i/5)-10][i%5]= new JButton(m);
			p3.add(a6[(i/5)-10][i%5]);  
			a6[(i/5)-10][i%5].setPreferredSize(new Dimension(m1.getIconWidth(), m1.getIconHeight()));
			a6[(i/5)-10][i%5].setBorderPainted(false);
			a6[(i/5)-10][i%5].setContentAreaFilled(false); 
			a6[(i/5)-10][i%5].setFocusPainted(false);
		  }
		}
	}

	GameLayout()
	{	
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
		j1.setOpaque(false);
		j2.setOpaque(false);
		
		setSize(1200,970);
		setVisible(true);
		
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				a1[i][j].setCursor(cur); //��ư�� ���콺�� �ø��� Ŀ�� ����� ������ �ٲ�
				a2[i][j].setCursor(cur);
				a3[i][j].setCursor(cur);
				a4[i][j].setCursor(cur);
				a5[i][j].setCursor(cur);
				a6[i][j].setCursor(cur);
				
				a1[i][j].addActionListener(this); //��ư �׼� �߰�
				a2[i][j].addActionListener(this);
				a3[i][j].addActionListener(this);
				a4[i][j].addActionListener(this); 
				a5[i][j].addActionListener(this);
				a6[i][j].addActionListener(this);
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
			if(ChoiceNation.chosenNation1==0)//�������� : ���� �������� ��
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						//bingo[][]�� üũ �ȵȰ͸� üũ ����
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon0);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon0);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon0);
							GameProcess.lineCount();
						}
					}
				}
			}
			
			if(ChoiceNation.chosenNation1==1)//��
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon1);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon1);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon1);
							GameProcess.lineCount();
						}
					}
				}
			}
			
			if(ChoiceNation.chosenNation1==2)//��
			{
				for(int i=0; i<5; i++)
				{
					for(int j=0; j<5; j++)
					{
						if(e.getSource()==a1[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[0][(i*5)+j];
							GameProcess.CheckBingo(a1[i][j], bcIcon2);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a2[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[1][(i*5)+j];
							GameProcess.CheckBingo(a2[i][j], bcIcon2);
							GameProcess.lineCount();
						}
						else if(e.getSource()==a3[i][j])
						{
							GameProcess.chosenBingoNumber=GameProcess.p1Board[2][(i*5)+j];
							GameProcess.CheckBingo(a3[i][j], bcIcon2);
							GameProcess.lineCount();
						}
					}
				}	
			}
		}
	}
}
