package com.sist.client;
import java.awt.*;
import javax.swing.*;
public class Login extends JPanel{ //상속 extends
	JLabel	la1, la2; //필드명 주기
	JTextField tf; //입력필드
	JPasswordField pf;//비밀번호 입력 필드
	JButton b1,b2,b3; // 버튼 2개.
	Image img; //추상클래스 abstract!! 단독으로 메모리 할당을 못한다.
	Image loginTxt; //게임이름
	
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	Login() //생성자의 명칭은 클래스명과 동일 !! 리턴형이 없다!!
	{
		img=Toolkit.getDefaultToolkit().getImage("img\\로그인배경.jpg");
		loginTxt=Toolkit.getDefaultToolkit().getImage("img\\로그인텍스트.png");
		setLayout(null);//FlowLayout
		// 각 레이아웃속성 할당
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("login");
		b2=new JButton("cancel");
		b3=new JButton("회원가입");
		//배치
		la1.setBounds(290, 350, 40, 30); //x,y,w,h -300
		la1.setForeground(Color.white); // 필드명 색깔 변경 -300
		tf.setBounds(335, 350, 150,30);
		la2.setBounds(290, 385, 40, 30);
		la2.setForeground(Color.white); // 필드명 색깔 변경 -300
		pf.setBounds(335, 385, 150,30);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.setBounds(310, 420, 195, 35);
		p.setOpaque(false);//뒷부분(쓸데없는 부분) 제거(투명화)
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
	      //getWidth(),getHeight(): 그림 크기를 창 안에 딱 맞게 집어넣음
	      g.drawImage(img, 0, 0, getWidth(),getHeight(),this);//this에 그려라
	   }
	
}
