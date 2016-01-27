package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel implements ActionListener{
	
	//���ȭ��
	Image bg; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	
	//������ 
	static JButton[][] a1=new JButton[5][5]; //�� ��
	static JButton[][] a2=new JButton[5][5];
	static JButton[][] a3=new JButton[5][5];
	
	static JButton[][] a4=new JButton[5][5]; //��� ��
	static JButton[][] a5=new JButton[5][5];
	static JButton[][] a6=new JButton[5][5];
	
	//ĳ�����̹���
	ImageIcon c1;
	//�÷��̾� 1 ������ ���̾ƿ�
	static JPanel p=new JPanel();
	static JPanel p1=new JPanel();
	static JPanel p2=new JPanel();
	static JPanel p3=new JPanel();
	
	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	ImageIcon bcIcon0=new ImageIcon("c:\\bingo\\����üũ-��.png");
	ImageIcon bcIcon1=new ImageIcon("c:\\bingo\\����üũ-��.png");
	ImageIcon bcIcon2=new ImageIcon("c:\\bingo\\����üũ-��.png");
	
	public void Rand()
	{
		GameProcess.rand(); // GameProcess Ŭ���� �������� ����~ �����޼ҵ� ȣ��
		
		for(int i=0; i<75;i++)
		{
		  if(i<25)	
		  {
			// �������� ������ ����numArr1�� ���� ���ھ����� ��ư�� �߰� 
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a1[i/5][i%5]= new JButton(m);
			//a1[i/5][i%5].setFont(new Font("���� ���",Font.BOLD,9));	
			p1.add(a1[i/5][i%5]);
			// ��ư�� ������ ������ ���߱� 
			a1[i/5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[i/5][i%5].setBorderPainted(false); //��ư ��輱 ����
			a1[i/5][i%5].setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
			a1[i/5][i%5].setFocusPainted(false); //��ư���� ��� ����
		  }
		  else if((i>=25)&&(i<50))
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a2[(i/5)-5][i%5]= new JButton(m);
			//a2[(i/5)-5][i%5].setFont(new Font("���� ���",Font.BOLD,9));
			p2.add(a2[(i/5)-5][i%5]);
			a2[(i/5)-5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a2[(i/5)-5][i%5].setBorderPainted(false);
			a2[(i/5)-5][i%5].setContentAreaFilled(false); 
			a2[(i/5)-5][i%5].setFocusPainted(false);
		  }
		  else//(i>=50)
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+GameProcess.numArr1[i]+".png");
			a3[(i/5)-10][i%5]= new JButton(m);
			//a3[(i/5)-10][i%5].setFont(new Font("���� ���",Font.BOLD,9));
			p3.add(a3[(i/5)-10][i%5]);  
			a3[(i/5)-10][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a3[(i/5)-10][i%5].setBorderPainted(false);
			a3[(i/5)-10][i%5].setContentAreaFilled(false); 
			a3[(i/5)-10][i%5].setFocusPainted(false);
		  }
		}
	}

	GameLayout()
	{	
		bg=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\�ΰ��ӹ��.jpg");
		setLayout(null);
		FlowLayout k=new FlowLayout(0); // 0: ��������, 1:�������, 2:����������
		k.setHgap(20);
		p.setLayout(k);
		add(p);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.setBounds(5,10,900,275);
		p1.setLayout(new GridLayout(5,5,0,0)); //5by5��ĸ��, ��,�참�� 0
		p2.setLayout(new GridLayout(5,5,0,0));
		p3.setLayout(new GridLayout(5,5,0,0));
		p1.setSize(265,265);
		p2.setSize(265,265);
		p3.setSize(265,265);
		
		Rand();
		
		p.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		setSize(1200,970);
		setVisible(true);
		
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				a1[i][j].setCursor(cur); //��ư�� ���콺�� �ø��� Ŀ�� ����� ������ �ٲ�
				a2[i][j].setCursor(cur);
				a3[i][j].setCursor(cur);
				
				a1[i][j].addActionListener(this); //��ư �׼� �߰�
				a2[i][j].addActionListener(this);
				a3[i][j].addActionListener(this);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		//.setOpaque(false);//�����ϰ�
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
