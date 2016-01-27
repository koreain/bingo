package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChoiceNation extends JPanel{
	static int chosenNation1=0; //0=��, 1=��, 2=��
	static int chosenNation2=0;
	ImageIcon icon0=new ImageIcon("c:\\bingo\\�����þ�����-��.png"); // ���˿� ���ù�ư �̹���
	ImageIcon icon1=new ImageIcon("c:\\bingo\\�����þ�����-��.png");
	ImageIcon icon2=new ImageIcon("c:\\bingo\\�����þ�����-��.png");
	JButton nation0 = new JButton(icon0); //���˿� ���ù�ư
	JButton nation1 = new JButton(icon1);
	JButton nation2 = new JButton(icon2);
	Image bg, notice;//��� �̹���
	
	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	public void jangsu()
	{
		for(int i=0;i<6;i++)
		{
			if(i<3)
			{
				JButton a1=new JButton(new ImageIcon("c:\\bingo\\���"+chosenNation1+i+".jpg"));
				//a1.setEnabled(false);
				GameLayout.j1.add(a1);
			}
			else
			{
				JButton a2=new JButton(new ImageIcon("c:\\bingo\\���"+chosenNation2+(i%3)+".jpg"));
				//a1.setEnabled(false);
				GameLayout.j2.add(a2);
			}
		}
	}
	
	
	public ChoiceNation()
	{
		bg=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\������-���.jpg"); //�׸���������
		notice=Toolkit.getDefaultToolkit().getImage("c:\\bingo\\������-����.jpg");
		
		setLayout(null);
		// ��ư�� ������ ������ ���߱� 
		nation0.setPreferredSize(new Dimension(icon0.getIconWidth(), icon0.getIconHeight()));
		nation0.setBorderPainted(false); //��ư ��輱 ����
		nation0.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		nation0.setFocusPainted(false); //��ư���� ��� ����
		
		nation1.setPreferredSize(new Dimension(icon1.getIconWidth(), icon1.getIconHeight()));
		nation1.setBorderPainted(false);
		nation1.setContentAreaFilled(false); 
		nation1.setFocusPainted(false);
		
		nation2.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
		nation2.setBorderPainted(false);
		nation2.setContentAreaFilled(false); 
		nation2.setFocusPainted(false);
		
		nation0.setBounds(550, 350, 140, 140);
		nation1.setBounds(400, 600, 140, 140);
		nation2.setBounds(700, 600, 140, 140);
		
		//��ư�� ������ �� �� �� (0 1 2) ���� �� => �ΰ��ӿ��� ����üũ �� �� ��������� ���
		nation0.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=0;
				jangsu();
			}
		});

		nation1.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=1;
				jangsu();
			}
		});

		nation2.addActionListener(new ActionListener()
		{ 
			@Override 
			public void actionPerformed(ActionEvent e) 
			{
				chosenNation1=2;
				jangsu();
			}
		});
		
		add(nation0); // ȭ�鿡 ���� ��ư ������ 3�� �߰�
		add(nation1);
		add(nation2);
		
		nation0.setCursor(cur); //��ư ���� Ŀ���� �ø��� �ո������ ��ȭ�ǰ� ����
		nation1.setCursor(cur);
		nation2.setCursor(cur);
		
//		setSize(1200,970);
//		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		  g.drawImage(bg, 0, 0, 1200, 970, this); //��� �׸���
		  g.drawImage(notice,80,110,360,130,this); //���� �׸���
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		JFrame f = new JFrame(); 
//		f.setPreferredSize(new Dimension(1200, 970));
//		f.setLocation(0, 0); 
//		Container con = f.getContentPane(); 
//		ChoiceNation c = new ChoiceNation(); 
//		con.add(c); 
//		f.pack(); 
//		f.setVisible(true); 
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
