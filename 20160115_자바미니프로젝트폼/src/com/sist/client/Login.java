package com.sist.client;
import java.awt.*;
import javax.swing.*;
public class Login extends JPanel{ //��� extends
	JLabel	la1, la2; //�ʵ�� �ֱ�
	JTextField tf; //�Է��ʵ�
	JPasswordField pf;//��й�ȣ �Է� �ʵ�
	JButton b1,b2,b3; // ��ư 2��.
	Image img; //�߻�Ŭ���� abstract!! �ܵ����� �޸� �Ҵ��� ���Ѵ�.
	Image loginTxt; //�����̸�
	
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	Login() //�������� ��Ī�� Ŭ������� ���� !! �������� ����!!
	{
		img=Toolkit.getDefaultToolkit().getImage("img\\�α��ι��.jpg");
		loginTxt=Toolkit.getDefaultToolkit().getImage("img\\�α����ؽ�Ʈ.png");
		setLayout(null);//FlowLayout
		// �� ���̾ƿ��Ӽ� �Ҵ�
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("login");
		b2=new JButton("cancel");
		b3=new JButton("ȸ������");
		//��ġ
		la1.setBounds(290, 350, 40, 30); //x,y,w,h -300
		la1.setForeground(Color.white); // �ʵ�� ���� ���� -300
		tf.setBounds(335, 350, 150,30);
		la2.setBounds(290, 385, 40, 30);
		la2.setForeground(Color.white); // �ʵ�� ���� ���� -300
		pf.setBounds(335, 385, 150,30);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.setBounds(310, 420, 195, 35);
		p.setOpaque(false);//�޺κ�(�������� �κ�) ����(����ȭ)
		add(la1);
		add(tf);
		add(la2);
		add(pf);
		add(p);
		
		b1.setCursor(cur);
		b2.setCursor(cur);
		b3.setCursor(cur);
	}
	@Override
	   protected void paintComponent(Graphics g) {
	      // TODO Auto-generated method stub
	      //getWidth(),getHeight(): �׸� ũ�⸦ â �ȿ� �� �°� �������
	      g.drawImage(img, 0, 0, getWidth(),getHeight(),this);//this�� �׷���
	   }
	
}
