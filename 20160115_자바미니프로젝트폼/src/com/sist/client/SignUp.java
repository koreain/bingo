package com.sist.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.*;


// 나중에 아이디찾기, 비밀번호찾기 등등 만들것 질문을 통해서.

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
		
	
		la_id = new JLabel("아이디",JLabel.RIGHT);
		la_idCheck = new JLabel();
		la_pw = new JLabel("비밀번호",JLabel.RIGHT);
		la_checkPw = new JLabel("비밀번호 확인",JLabel.RIGHT);
		la_p = new JLabel();
		la_name = new JLabel("이름",JLabel.RIGHT);
		la_sex = new JLabel("성별",JLabel.RIGHT);
		la_email = new JLabel("이메일",JLabel.RIGHT);
		la_intro = new JLabel("자기소개",JLabel.RIGHT);
		
		tf_name = new JTextField();
		tf_id = new JTextField();
		pf = new JPasswordField();
		pf2 = new JPasswordField();
		tf_email = new JTextField();
		ta_intro = new JTextArea();
		js = new JScrollPane(ta_intro);
		btn_idCheck = new JButton("중복확인");
		btn_OK = new JButton("가입");
		btn_NO = new JButton("취소");
		rbMan = new JRadioButton("남자");
		rbWoman = new JRadioButton("여자");
		
		//라디오 버튼을 그룹으로 묶음. => 하나만 선택할 수 있게 하기위해서
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbMan);
		btnGroup.add(rbWoman);
		
		la_idCheck.setFont(new Font("Batang",Font.ITALIC,10)); // 글자체 및 크기변경
		la_p.setFont(new Font("Batang",Font.ITALIC,10));
		
		// 위치 노가다.
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
		
		
		//액션!
		pf2.addFocusListener(this);
		btn_OK.addActionListener(this);
		btn_NO.addActionListener(this);
		btn_idCheck.addActionListener(this);
		
		//추가
		add(la_id);add(tf_id);
		add(btn_idCheck);add(la_idCheck);
		add(la_pw);add(pf);
		add(la_checkPw);add(pf2);add(la_p);
		add(la_name);add(tf_name);
		add(la_sex);add(rbMan);add(rbWoman);
		add(la_email);add(tf_email);
		add(la_intro);add(js);
		add(btn_OK);add(btn_NO);
		
		
		//화면 위치 조정
		Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit 객체 생성
		Dimension ds = tk.getScreenSize(); // 현재 스크린 사이즈를 저장
		setLocation(ds.width/2+130, ds.height/2-350);
		
		/*setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		setSize(370, 510);
		setVisible(false);
	}
	
	// 비밀번호 비교
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
			JOptionPane.showMessageDialog(this, "가입완료");
		}else
			JOptionPane.showMessageDialog(this, "가입실패");
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
	       
        //화면에서 사용자가 입력한 내용을 얻는다.
        UserDTO dto = new UserDTO();
        String user_id = tf_id.getText();
        String user_pw = pf.getText();
        String user_name = tf_name.getText();
        String user_sex = "";
        
        if(rbMan.isSelected()){
        	user_sex = "남자";
        }else if(rbWoman.isSelected()){
        	user_sex = "여자";
        }
       
        String user_email = tf_email.getText();
        String user_intro = ta_intro.getText();
       
        //dto에 담는다.    
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
				la_idCheck.setText("사용가능한 아이디입니다.");
				la_idCheck.setForeground(Color.green);
			}else{
				la_idCheck.setText("사용중인 아이디입니다.");
				la_idCheck.setForeground(Color.red);
			}
		}
		if(e.getSource()==btn_NO){
			dispose(); // 회원가입 창 닫기.
		}else if(e.getSource()==btn_OK){
			if(!bId){
				JOptionPane.showMessageDialog(null, "아이디를 확인해보세요~");
				tf_id.requestFocus();
			}else if(pwCompare()){
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해보세요~");
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
				la_p.setText("비밀번호가 일치하지 않습니다.");
				la_p.setForeground(Color.red);
			}else{
				la_p.setText("확인");
				la_p.setForeground(Color.green);
			}
		}
	}
}
