package com.sist.client;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameLayout extends JPanel{
	
	//배경화면
	Image bg; //추상클래스 abstract!! 단독으로 메모리 할당을 못한다.
	
	//빙고판 
	JButton[][] a1=new JButton[5][5];
	JButton[][] a2=new JButton[5][5];
	JButton[][] a3=new JButton[5][5];
	
	//캐릭터이미지
	ImageIcon c1;
	//플레이어 1 빙고판 레이아웃
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	
	
	public void Rand()
	{
		int[] temp=new int[75];
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
			
		  if(i<25)	
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+su+".png");
			a1[i/5][i%5]= new JButton(m);
			a1[i/5][i%5].setFont(new Font("맑은 고딕",Font.BOLD,9));	
			p1.add(a1[i/5][i%5]); 
			a1[i/5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a1[i/5][i%5].setBorderPainted(false);
			a1[i/5][i%5].setContentAreaFilled(false);
			a1[i/5][i%5].setFocusPainted(false);
			
		  }
		  else if((i>=25)&&(i<50))
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+su+".png");
			a2[(i/5)-5][i%5]= new JButton(m);
			a2[(i/5)-5][i%5].setFont(new Font("맑은 고딕",Font.BOLD,9));
			p2.add(a2[(i/5)-5][i%5]);
			a2[(i/5)-5][i%5].setPreferredSize(new Dimension(m.getIconWidth(), m.getIconHeight()));
			a2[(i/5)-5][i%5].setBorderPainted(false);
			a2[(i/5)-5][i%5].setContentAreaFilled(false); 
			a2[(i/5)-5][i%5].setFocusPainted(false);      
		  }
		  else//(i>=50)
		  {
			ImageIcon m=new ImageIcon("c:\\bingo\\"+su+".png");
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
	
	
	GameLayout()
	{	
		bg=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\인게임배경.jpg");
		setLayout(null);
		FlowLayout k=new FlowLayout(0); // 0: 왼쪽정렬, 1:가운데정렬, 2:오른쪽정렬
		k.setHgap(20);
		p.setLayout(k);
		add(p);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.setBounds(5,10,900,275);
		p1.setLayout(new GridLayout(5,5,0,0));
		p2.setLayout(new GridLayout(5,5,0,0));
		p3.setLayout(new GridLayout(5,5,0,0));
		p1.setSize(265,265);
		p2.setSize(265,265);
		p3.setSize(265,265);
		Rand();
		
		//p.setOpaque(false);
		setSize(1200,800);
		setVisible(true);
		
	}
	@Override
	public void paint(Graphics g){
		  g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		  //.setOpaque(false);//투명하게
		  }
}
