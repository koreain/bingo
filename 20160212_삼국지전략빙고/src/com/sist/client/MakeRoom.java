package com.sist.client; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
public class MakeRoom extends JDialog implements ActionListener { 
JLabel la1, la2, la3; 
JTextField tf; 
	JPasswordField pf; 
	JRadioButton open, noopen; 
	JButton b1, b2;
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR); 

	public MakeRoom() {
		setModal(true);
		setResizable(false); 
		
		la1 = new JLabel("���̸�"); 
		la2 = new JLabel("����"); 
		la3 = new JLabel("��й�ȣ"); 
		tf = new JTextField(); 
		pf = new JPasswordField(); 

		open = new JRadioButton("����"); 
		noopen = new JRadioButton("�����"); 
		ButtonGroup bg = new ButtonGroup(); 
		bg.add(open); 
		bg.add(noopen); 
		open.setSelected(true);// ������ư�� �ϳ� �����ϸ� 
		la3.setVisible(false);// la3�� �Ⱥ��� 
		pf.setVisible(false); 

		b1 = new JButton("Ȯ��"); 
		b2 = new JButton("���"); 
		JPanel p = new JPanel(); 
		p.add(b1); 
		p.add(b2); 
		b1.setCursor(cur); 
		b2.setCursor(cur); 

		setLayout(null); 
		la1.setBounds(10, 15, 40, 30); 
		tf.setBounds(55, 15, 150, 30); 

		la2.setBounds(10, 50, 40, 30); 
		open.setBounds(55, 50, 70, 30); 
		noopen.setBounds(130, 50, 70, 30); 

		la3.setBounds(30, 85, 100, 30); 
		pf.setBounds(105, 85, 100, 30); 
		p.setBounds(10, 130, 195, 35); 

		add(la1); 
		add(tf); 
		add(la2); 
		add(open); 
		add(noopen); 
		add(la3); 
		add(pf); 
		add("Center",p); // 

		setSize(230, 220); 
		open.addActionListener(this); 
		noopen.addActionListener(this);
		setLocationRelativeTo(null); 
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MakeRoom();
	}
	
	
	@Override 
	public void actionPerformed(ActionEvent e) { 
		// TODO Auto-generated method stub 
		if (e.getSource() == open) { 
			pf.setText(""); 
			pf.setVisible(false); 
			la3.setVisible(false); 
		} else if (e.getSource() == noopen) { 
			pf.setText(""); 
			pf.setVisible(true); 
			la3.setVisible(true);
			pf.requestFocus(); 
		}
	}
}