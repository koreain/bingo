package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.*;


// ���߿� ���̵�ã��, ��й�ȣã�� ��� ����� ������ ���ؼ�.

public class SignUp extends JDialog implements ActionListener, FocusListener, ItemListener{
	JLabel la_id,la_idCheck,la_name,la_nickname,la_pw,la_checkPw,
			la_p,la_sex,la_nickCheck, la_avatar;
	JTextField tf_id,tf_name,tf_nickname;
	JPasswordField pf, pf2;
	JRadioButton rbMan, rbWoman;
	JRadioButton rb1, rb2, rb3;
	JButton btn_idCheck, btn_nicknameCheck, btn_OK, btn_NO;
	JLabel la_av1,la_av2,la_av3;
	boolean sexCheck; // ������ �� false, ������ �� true
	SignUp() {
		// TODO Auto-generated constructor stub
		this.setModal(true);
	
		la_id = new JLabel("���̵�",JLabel.RIGHT);
		la_idCheck = new JLabel();
		la_pw = new JLabel("��й�ȣ",JLabel.RIGHT);
		la_checkPw = new JLabel("��й�ȣ Ȯ��",JLabel.RIGHT);
		la_p = new JLabel();
		la_name = new JLabel("�̸�",JLabel.RIGHT);
		la_nickname = new JLabel("�г���",JLabel.RIGHT);
		la_nickCheck = new JLabel();
		la_sex = new JLabel("����",JLabel.RIGHT);
		la_avatar = new JLabel("�ƹ�Ÿ ����", JLabel.CENTER);
		
		la_av1 = new JLabel(new ImageIcon("img\\sum1.jpg"));
		la_av2 = new JLabel(new ImageIcon("img\\sum2.jpg"));
		la_av3 = new JLabel(new ImageIcon("img\\sum3.jpg"));
		
		tf_name = new JTextField();
		tf_nickname = new JTextField();
		tf_id = new JTextField();
		pf = new JPasswordField();
		pf2 = new JPasswordField();
		btn_idCheck = new JButton("�ߺ�Ȯ��");
		btn_nicknameCheck = new JButton("�ߺ�Ȯ��");
		btn_OK = new JButton("����");
		btn_NO = new JButton("���");
		rbMan = new JRadioButton("����");
		rbMan.setSelected(true);
		rbWoman = new JRadioButton("����");
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		
		//���� ��ư�� �׷����� ����. => �ϳ��� ������ �� �ְ� �ϱ����ؼ�
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbMan);
		btnGroup.add(rbWoman);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		rb1.setSelected(true);
		
		la_idCheck.setFont(new Font("Batang",Font.ITALIC,10)); // ����ü �� ũ�⺯��
		la_p.setFont(new Font("Batang",Font.ITALIC,10));
		la_nickCheck.setFont(new Font("Batang",Font.ITALIC,10));
		la_avatar.setFont(new Font("�ü�ü",Font.ITALIC, 18));
		la_id.setFont(new Font("�ü�ü",Font.BOLD, 14));
		tf_id.setFont(new Font("�ü�ü",Font.BOLD, 14));
		btn_idCheck.setFont(new Font("�ü�ü",Font.BOLD, 14));
		la_pw.setFont(new Font("�ü�ü",Font.BOLD, 14));
		la_checkPw.setFont(new Font("�ü�ü",Font.BOLD, 14));
		la_name.setFont(new Font("�ü�ü",Font.BOLD, 14));
		tf_name.setFont(new Font("�ü�ü",Font.BOLD, 14));
		la_nickname.setFont(new Font("�ü�ü",Font.BOLD, 14));
		tf_nickname.setFont(new Font("�ü�ü",Font.BOLD, 14));
		btn_nicknameCheck.setFont(new Font("�ü�ü",Font.BOLD, 14));
		la_sex.setFont(new Font("�ü�ü",Font.BOLD, 14));
		rbMan.setFont(new Font("�ü�ü",Font.BOLD, 14));
		rbWoman.setFont(new Font("�ü�ü",Font.BOLD, 14));
		btn_OK.setFont(new Font("�ü�ü",Font.BOLD, 14));
		btn_NO.setFont(new Font("�ü�ü",Font.BOLD, 14));
		
		// ��ġ �밡��.
		setLayout(null);
		la_id.setBounds(15, 20, 90, 20);
		tf_id.setBounds(115, 20, 150, 20);
		btn_idCheck.setBounds(285, 20, 80, 20);
		la_idCheck.setBounds(115, 40, 150, 20);
		la_pw.setBounds(15, 60, 90, 20);
		pf.setBounds(115, 60, 150, 20);
		la_checkPw.setBounds(5, 100, 100, 20);
		pf2.setBounds(115, 100, 150, 20);
		la_p.setBounds(115, 120, 150, 20);
		la_name.setBounds(15, 140, 90, 20);
		tf_name.setBounds(115, 140, 150, 20);
		la_nickname.setBounds(15, 180, 90, 20);
		tf_nickname.setBounds(115, 180, 150, 20);
		btn_nicknameCheck.setBounds(285, 180, 80, 20);
		la_nickCheck.setBounds(115, 200, 150, 20);
		la_sex.setBounds(15, 220, 90, 20);
		rbMan.setBounds(120, 220, 70, 20);
		rbWoman.setBounds(190, 220, 70, 20);
		la_avatar.setBounds(15, 260, 335, 20);
		la_av1.setBounds(20, 290, 105, 140);
		la_av2.setBounds(135, 290, 105, 140);
		la_av3.setBounds(250, 290, 105, 140);
		rb1.setBounds(62, 440, 30, 20);
		rb2.setBounds(177, 440, 30, 20);
		rb3.setBounds(292, 440, 30, 20);
		btn_OK.setBounds(115, 480, 70, 25);
		btn_NO.setBounds(200, 480, 70, 25);
		
		
		//�׼�!
		pf.addFocusListener(this);
		pf2.addFocusListener(this);
		btn_OK.addActionListener(this);
		btn_NO.addActionListener(this);
		btn_idCheck.addActionListener(this);
		btn_nicknameCheck.addActionListener(this);
		rbMan.addItemListener(this);
		rbWoman.addItemListener(this);
		
		//�߰�
		add(la_id);add(tf_id);
		add(btn_idCheck);add(la_idCheck);
		add(la_pw);add(pf);
		add(la_checkPw);add(pf2);add(la_p);
		add(la_name);add(tf_name);
		add(la_nickname);add(tf_nickname);add(btn_nicknameCheck);add(la_nickCheck);
		add(la_sex);add(rbMan);add(rbWoman);
		add(la_avatar);add(la_av1);add(la_av2);add(la_av3);
		add(rb1);add(rb2);add(rb3);
		add(btn_OK);add(btn_NO);
		
		
		//ȭ�� ��ġ ����
		Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit ��ü ����
		Dimension ds = tk.getScreenSize(); // ���� ��ũ�� ����� ����
		setLocation(ds.width/2+130, ds.height/2-350);
		
		/*setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		setSize(390, 560);
		setVisible(false);
	}
	
	// ��й�ȣ ��
	public boolean pwCompare(){
		String a = String.valueOf(pf.getPassword());
		String b = String.valueOf(pf2.getPassword());
		if(a.equals(b))
			return false;
		return true;
	}
	
	
	public void insertUser(){
		UserDTO dto = getViewData();
		UserDAO dao = new UserDAO();
		boolean bCheck = dao.insertUser(dto);
		if(bCheck){
			JOptionPane.showMessageDialog(this, "���ԿϷ�");
		}else
			JOptionPane.showMessageDialog(this, "���Խ���");
	}
	
	// ���̵� �ߺ�Ȯ��
	public boolean idCheck(){
		UserDAO dao = new UserDAO();
		List<UserDTO> list = dao.getList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUser_id().equals(tf_id.getText()))
				return false;
		}
		return true;
	}
	
	// �г��� �ߺ�Ȯ��
	public boolean nickCheck(){
		UserDAO dao = new UserDAO();
		List<UserDTO> list = dao.getList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUser_nickname().equals(tf_nickname.getText()))
				return false;
		}
		return true;
	}
	
	public UserDTO getViewData(){
	       
        //ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
        UserDTO dto = new UserDTO();
        String user_id = tf_id.getText();
        String user_pw = String.valueOf(pf.getPassword());
        String user_name = tf_name.getText();
        String user_nickname = tf_nickname.getText();
        String user_sex = "";
        String user_avatar = "";
        
        if(rbMan.isSelected()){
        	user_sex = "����";
        }else if(rbWoman.isSelected()){
        	user_sex = "����";
        }
       
        if(!sexCheck){
        	if(rb1.isSelected()){
        		user_avatar = "img\\m1.jpg";
        	}else if(rb2.isSelected()){
        		user_avatar = "img\\m2.jpg";
        	}else if(rb3.isSelected()){
        		user_avatar = "img\\m3.jpg";
        	}
        }else{
        	if(rb1.isSelected()){
        		user_avatar = "img\\w1.jpg";
        	}else if(rb2.isSelected()){
        		user_avatar = "img\\w2.jpg";
        	}else if(rb3.isSelected()){
        		user_avatar = "img\\w3.jpg";
        	}
        }
        dto.setUser_id(user_id);
        dto.setUser_pw(user_pw);
        dto.setUser_name(user_name);
        dto.setUser_nickname(user_nickname);
        dto.setUser_sex(user_sex);
        dto.setUser_avatar(user_avatar);
        dto.setUser_win(0);
        dto.setUser_lose(0);
       
        return dto;
    }
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==btn_idCheck){
			if(idCheck()){
				la_idCheck.setText("��밡���� ���̵��Դϴ�.");
				la_idCheck.setForeground(Color.green);
			}else{
				la_idCheck.setText("������� ���̵��Դϴ�.");
				la_idCheck.setForeground(Color.red);
				tf_id.requestFocus();
			}
		}else if(e.getSource()==btn_nicknameCheck){
			if(nickCheck()){
				la_nickCheck.setText("��밡���� �г����Դϴ�.");
				la_nickCheck.setForeground(Color.green);
			}else{
				la_nickCheck.setText("������� �г����Դϴ�.");
				la_nickCheck.setForeground(Color.red);
				tf_nickname.requestFocus();
			}
		}
		if(e.getSource()==btn_NO){
			dispose(); // ȸ������ â �ݱ�.
		}else if(e.getSource()==btn_OK){
			if(tf_id.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���");
				tf_id.requestFocus();
			}else if(String.valueOf(pf.getPassword()).equals("")){
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���");
				pf.requestFocus();
			}else if(tf_name.getText().equals("")){
				JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���");
				tf_name.requestFocus();
			}else if(tf_nickname.getText().equals("")){
				JOptionPane.showMessageDialog(null, "�г����� �Է��ϼ���");
				tf_nickname.requestFocus();
			}else if(!idCheck()){
				JOptionPane.showMessageDialog(null, "���̵� Ȯ���غ�����~");
				tf_id.requestFocus();
			}else if(pwCompare()){
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ���غ�����~");
				pf.requestFocus();
			}else if(!nickCheck()){
				JOptionPane.showMessageDialog(null, "�г����� Ȯ���غ�����~");
				tf_nickname.requestFocus();
			}else{
				insertUser();
			}
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==pf && !String.valueOf(pf2.getPassword()).equals("")){
			if(pwCompare()){
				la_p.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				la_p.setForeground(Color.red);
			}else{
				la_p.setText("Ȯ��");
				la_p.setForeground(Color.green);
			}
		}
		if(e.getSource()==pf2){
			if(pwCompare()){
				la_p.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				la_p.setForeground(Color.red);
			}else{
				la_p.setText("Ȯ��");
				la_p.setForeground(Color.green);
			}
		}
	}
	
	public static void main(String[] args){
		SignUp su = new SignUp();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==rbMan){
			sexCheck = false;
			la_av1.setIcon(new ImageIcon("img\\sum1.jpg"));
			la_av2.setIcon(new ImageIcon("img\\sum2.jpg"));
			la_av3.setIcon(new ImageIcon("img\\sum3.jpg"));
		}else if(e.getSource()==rbWoman){
			sexCheck = true;
			la_av1.setIcon(new ImageIcon("img\\suw1.jpg"));
			la_av2.setIcon(new ImageIcon("img\\suw2.jpg"));
			la_av3.setIcon(new ImageIcon("img\\suw3.jpg"));
		}
	}
}
