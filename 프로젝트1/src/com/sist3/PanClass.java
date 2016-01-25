package com.sist3;
import java.awt.*;
import javax.swing.*;

import sun.java2d.pipe.DrawImage;
public class PanClass extends JFrame{
	JButton[][] a1=new JButton[5][5]; //첫번째 판 25칸
	JButton[][] a2=new JButton[5][5]; //두번째 판 25칸
	JButton[][] a3=new JButton[5][5]; //세번째 판 25칸
	JPanel p=new JPanel(); //패널 생성
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	ImageIcon m=new ImageIcon("c:\\bingo\\abc\\2.png");
	ImageIcon pan1=new ImageIcon("c:\\bingo\\abc\\빙고틀-공격.png");
	public void Rand()
	{
		int[] temp=new int[75]; //중복되지 않는 난수 발생
		boolean bDash=false;
		int su=0;
		for(int i=0; i<75;i++) 
		{
			bDash=true;
			while(bDash)
			{
				su=(int)(Math.random()*75+1);
				bDash=false;
				for(int j=0; j<i;j++)
				{
					if(su==temp[j])
					{
						bDash=true;
						break;
					}
				}
			}
			temp[i]=su;
			
		  if(i<25)	//배열 25전까지(숫자가 아닌 배열)는 첫번째판에 배치 
		  {
			//ImageIcon m=new ImageIcon("c:\\bingo\\abc\\"+(i+1)+".png");
			//ImageIcon m=new ImageIcon("c:\\bingo\\"+i+".png");
			a1[i/5][i%5]= new JButton(m);
			a1[i/5][i%5].setFont(new Font("맑은 고딕",Font.BOLD,9));	
			p1.add(a1[i/5][i%5]); 
			a1[i/5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[i/5][i%5].setBorderPainted(false);
			a1[i/5][i%5].setContentAreaFilled(false);
			a1[i/5][i%5].setFocusPainted(false);
		  }
		  else if((i>=25)&&(i<50)) //25~50까지 두번째 판에 배치
		  {
			//ImageIcon m=new ImageIcon("c:\\bingo\\abc\\"+(i+1)+".png");
			//  ImageIcon m=new ImageIcon("c:\\bingo\\1.png");
			
			a2[(i/5)-5][i%5]= new JButton(m);
			a2[(i/5)-5][i%5].setFont(new Font("맑은 고딕",Font.BOLD,9));
			p2.add(a2[(i/5)-5][i%5]);
			a2[(i/5)-5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a2[(i/5)-5][i%5].setBorderPainted(false);
			a2[(i/5)-5][i%5].setContentAreaFilled(false); 
			a2[(i/5)-5][i%5].setFocusPainted(false);      
		  }
		  else//(i>=50) 75까지 세번째 판에 배치
		  {
			//ImageIcon m=new ImageIcon("c:\\bingo\\abc\\"+(i+1)+".png");
			//  ImageIcon m=new ImageIcon("c:\\bingo\\1.png");
			a3[(i/5)-10][i%5]= new JButton(m);
			a3[(i/5)-10][i%5].setFont(new Font("맑은 고딕",Font.BOLD,9));
			p3.add(a3[(i/5)-10][i%5]);  
			a3[(i/5)-10][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a3[(i/5)-10][i%5].setBorderPainted(false);
			a3[(i/5)-10][i%5].setContentAreaFilled(false); 
			a3[(i/5)-10][i%5].setFocusPainted(false);      
		  }
		}
	}
	
	

	PanClass()
	{	
		p.setLayout(new FlowLayout());
		p1.setLayout(new GridLayout(5,5,0,0));
		p2.setLayout(new GridLayout(5,5,0,0));
		p3.setLayout(new GridLayout(5,5,0,0));
		p1.setSize(265,265);
		p2.setSize(265,265);
		p3.setSize(265,265);
		
		Rand();
		add(p);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		setLayout(null);
		p.setBounds(5,10,1200,275);
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //윈도우창 고정

	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanClass m=new PanClass();
	
	
	
	}

}
