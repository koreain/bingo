package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.*;


// ���߿� ���̵�ã��, ��й�ȣã�� ��� ����� ������ ���ؼ�.

public class SignUp extends JFrame implements ActionListener, FocusListener{
	JLabel la_id,la_idCheck,la_name,la_pw,la_checkPw,
			la_p,la_sex,la_email,la_question,la_intro;
	JTextField tf_id,tf_name,tf_answer,tf_email;
	JPasswordField pf, pf2;
	JTextArea ta_intro;
	JRadioButton rbMan, rbWoman;
	JButton btn_idCheck, btn_OK, btn_NO;
	JScrollPane js;
	boolean bId;
	SignUp() {
		// TODO Auto-generated constructor stub
		
	
		la_id = new JLabel("���̵�",JLabel.RIGHT);
		la_idCheck = new JLabel();
		la_pw = new JLabel("��й�ȣ",JLabel.RIGHT);
		la_checkPw = new JLabel("��й�ȣ Ȯ��",JLabel.RIGHT);
		la_p = new JLabel();
		la_name = new JLabel("�̸�",JLabel.RIGHT);
		la_sex = new JLabel("����",JLabel.RIGHT);
		la_email = new JLabel("�̸���",JLabel.RIGHT);
		la_intro = new JLabel("�ڱ�Ұ�",JLabel.RIGHT);
		
		tf_name = new JTextField();
		tf_id = new JTextField();
		pf = new JPasswordField();
		pf2 = new JPasswordField();
		tf_email = new JTextField();
		ta_intro = new JTextArea();
		js = new JScrollPane(ta_intro);
		btn_idCheck = new JButton("�ߺ�Ȯ��");
		btn_OK = new JButton("����");
		btn_NO = new JButton("���");
		rbMan = new JRadioButton("����");
		rbWoman = new JRadioButton("����");
		
		//���� ��ư�� �׷����� ����. => �ϳ��� ������ �� �ְ� �ϱ����ؼ�
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbMan);
		btnGroup.add(rbWoman);
		
		la_idCheck.setFont(new Font("Batang",Font.ITALIC,10)); // ����ü �� ũ�⺯��
		la_p.setFont(new Font("Batang",Font.ITALIC,10));
		
		// ��ġ �밡��.
		setLayout(null);
		la_id.setBounds(15, 20, 80, 20);
		tf_id.setBounds(105, 20, 150, 20);
		btn_idCheck.setBounds(265, 20, 80, 20);
		la_idCheck.setBounds(105, 40, 150, 20);
		la_pw.setBounds(15, 60, 80, 20);
		pf.setBounds(105, 60, 150, 20);
		la_checkPw.setBounds(15, 100, 80, 20);
		pf2.setBounds(105, 100, 150, 20);
		la_p.setBounds(105, 120, 150, 20);
		la_name.setBounds(15, 140, 80, 20);
		tf_name.setBounds(105, 140, 150, 20);
		la_sex.setBounds(15, 180, 80, 20);
		rbMan.setBounds(120, 180, 60, 20);
		rbWoman.setBounds(185, 180, 60, 20);
		la_email.setBounds(15, 220, 80, 20);
		tf_email.setBounds(105, 220, 150, 20);
		la_intro.setBounds(15, 260, 80, 20);
		ta_intro.setLineWrap(true);
		js.setBounds(105, 260, 200, 150);
		btn_OK.setBounds(95, 430, 70, 25);
		btn_NO.setBounds(180, 430, 70, 25);
		
		
		//�׼�!
		pf2.addFocusListener(this);
		btn_OK.addActionListener(this);
		btn_NO.addActionListener(this);
		btn_idCheck.addActionListener(this);
		
		//�߰�
		add(la_id);add(tf_id);
		add(btn_idCheck);add(la_idCheck);
		add(la_pw);add(pf);
		add(la_checkPw);add(pf2);add(la_p);
		add(la_name);add(tf_name);
		add(la_sex);add(rbMan);add(rbWoman);
		add(la_email);add(tf_email);
		add(la_intro);add(js);
		add(btn_OK);add(btn_NO);
		
		
		//ȭ�� ��ġ ����
		Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit ��ü ����
		Dimension ds = tk.getScreenSize(); // ���� ��ũ�� ����� ����
		setLocation(ds.width/2+130, ds.height/2-350);
		
		/*setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		setSize(370, 510);
		setVisible(false);
	}
	
	// ��й�ȣ ��
	public boolean pwCompare(){
		if(pf.getText().equals(pf2.getText()))
			return false;
		return true;
	}
	
	public boolean pwCompare2(){
		if(pf2.getText().equals(pf.getText()))
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
	
	public boolean idCheck(){
		UserDAO dao = new UserDAO();
		List<UserDTO> list = dao.getList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUser_id().equals(tf_id.getText()))
				return false;
		}
		return true;
	}
	
	public UserDTO getViewData(){
	       
        //ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
        UserDTO dto = new UserDTO();
        String user_id = tf_id.getText();
        String user_pw = pf.getText();
        String user_name = tf_name.getText();
        String user_sex = "";
        
        if(rbMan.isSelected()){
        	user_sex = "����";
        }else if(rbWoman.isSelected()){
        	user_sex = "����";
        }
       
        String user_email = tf_email.getText();
        String user_intro = ta_intro.getText();
       
        //dto�� ��´�.    
        dto.setUser_id(user_id);
        dto.setUser_pw(user_pw);
        dto.setUser_name(user_name);
        dto.setUser_sex(user_sex);
        dto.setUser_email(user_email);
        dto.setUser_intro(user_intro);
       
        return dto;
    }
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==btn_idCheck){
			if(idCheck()){
				bId = true;
				la_idCheck.setText("��밡���� ���̵��Դϴ�.");
				la_idCheck.setForeground(Color.green);
			}else{
				la_idCheck.setText("������� ���̵��Դϴ�.");
				la_idCheck.setForeground(Color.red);
			}
		}
		if(e.getSource()==btn_NO){
			dispose(); // ȸ������ â �ݱ�.
		}else if(e.getSource()==btn_OK){
			if(!bId){
				JOptionPane.showMessageDialog(null, "���̵� Ȯ���غ�����~");
				tf_id.requestFocus();
			}else if(pwCompare()){
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ���غ�����~");
				pf.requestFocus();
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
}
