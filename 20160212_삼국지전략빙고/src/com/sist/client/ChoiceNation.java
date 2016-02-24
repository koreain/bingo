package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ChoiceNation extends JPanel{
	static public int chosenNation1=0; //0=��, 1=��, 2=��
	static public int chosenNation2=0;
	ImageIcon icon0=new ImageIcon("img\\�����þ�����-��.png"); // ���˿� ���ù�ư �̹���
	ImageIcon icon1=new ImageIcon("img\\�����þ�����-��.png");
	ImageIcon icon2=new ImageIcon("img\\�����þ�����-��.png");
	ImageIcon icon3 = new ImageIcon("img\\�����þ�����.jpg");
	JButton nation0 = new JButton(icon0); //���˿� ���ù�ư
	JButton nation1 = new JButton(icon1);
	JButton nation2 = new JButton(icon2);
	boolean choiceComplete;
	JPanel jp = new JPanel() {
	      public void paintComponent(Graphics g) {
	         g.drawImage(icon3.getImage(), 0, 0, this);
	      }
	   };
	JLabel la1 = new JLabel("���");	
	JLabel la2 = new JLabel("���");
	JLabel la3 = new JLabel("���� �ð�");
	JButton bu1 = new JButton(new ImageIcon("img\\������-���.png"));
	JButton bu2 = new JButton(new ImageIcon("img\\������-���.png"));
	static int cntime = 7;
	static JLabel la6 = new JLabel(String.valueOf(cntime));
	Image bg, notice;//��� �̹���
	static JButton[] jangSu1 = new JButton[3]; 
	static JButton[] jangSu2 = new JButton[3];

	//���콺 Ŀ���� ��ư�� �ö����� �ո������ �ٲ��
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	public void jangsu()
	{
		for(int i=0;i<6;i++)
		{
			if(i<3)
			{
				jangSu1[i].setIcon(new ImageIcon("img\\���"+chosenNation1+i+".jpg")); 
				GameLayout.j1.add(jangSu1[i]); 
				jangSu1[i].setContentAreaFilled(false); 
				jangSu1[i].setBorderPainted(false); //��ư ��輱 ���� 
			}
			else
			{
				jangSu2[i%3].setIcon(new ImageIcon("img\\���"+chosenNation2+(i%3)+".jpg"));
				GameLayout.j2.add(jangSu2[i%3]); 
				jangSu2[i%3].setContentAreaFilled(false); 
				jangSu2[i%3].setBorderPainted(false); 
			}
		}
	}
	
	public ChoiceNation()
	{
		for(int i=0;i<3;i++)
		{ 
			jangSu1[i] = new JButton();  
			jangSu2[i] = new JButton(); 
		}
		bg=Toolkit.getDefaultToolkit().getImage("img\\������-���.jpg"); //�׸���������
		notice=Toolkit.getDefaultToolkit().getImage("img\\������-����.jpg");
	
		setLayout(null);
		jp.setLayout(null);
		jp.setBounds(760, 110, 360, 130);

		la1.setBounds(50, 20, 50, 20);
		la2.setBounds(150, 20, 50, 20);
		la3.setBounds(230, -20, 100, 100);
		la6.setBounds(255, 30, 100, 100);
		bu1.setBounds(45, 55, 52, 52);
		bu1.setBorderPainted(false); //��ư ��輱 ����
		bu1.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		bu1.setFocusPainted(false); //��ư���� ��� ����
		bu2.setBounds(145, 55, 52, 52);
		bu2.setBorderPainted(false); //��ư ��輱 ����
		bu2.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
		bu2.setFocusPainted(false); //��ư���� ��� ����
		

		la1.setFont(new Font("�ü�", Font.BOLD, 18));
		la2.setFont(new Font("�ü�", Font.BOLD, 18));
		la3.setFont(new Font("�ü�", Font.BOLD, 18));
		la6.setFont(new Font("�ü�", Font.BOLD, 60));

		jp.add(la1);
		jp.add(la2);
		jp.add(la3);
		jp.add(bu1);
      	jp.add(bu2);
      	jp.add(la6);
      	add(jp);

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
		
		add(nation0); // ȭ�鿡 ���� ��ư ������ 3�� �߰�
		add(nation1);
		add(nation2);
		
		nation0.setCursor(cur); //��ư ���� Ŀ���� �ø��� �ո������ ��ȭ�ǰ� ����
		nation1.setCursor(cur);
		nation2.setCursor(cur);
	}
	@Override
	public void paintComponent(Graphics g){
		  g.drawImage(bg, 0, 0, 1280, 970, this); //��� �׸���
		  g.drawImage(notice,80,110,360,130,this); //���� �׸���
	}
}