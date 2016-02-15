package com.sist.client;

import javax.swing.*;
import javax.swing.text.*;

import sun.net.www.content.image.jpeg;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatInGame extends JFrame implements ActionListener{
	
	JTextPane pane; // JTextArea  두개다 여러줄 문자줄 떄 사용
	JTextField tf;
	JComboBox box;
	JButton btn, btn2;
	JPanel p;
	Boolean btnCheck = false;
	Emoticon emo = new Emoticon();
	Style[] style = new Style[9];
	SimpleDateFormat simp = new SimpleDateFormat("a hh:mm:ss");
	boolean bCheck = false;
	int a;
	public ChatInGame(){
		pane = new JTextPane();
		pane.setEditable(false);
		JScrollPane js = new JScrollPane(pane);
		tf = new JTextField(21);
		btn = new JButton(new ImageIcon("img\\tjdrb.gif"));
		btn2 = new JButton();
		setUndecorated(true); // 타이틀바 사라지게
		setOpacity(0.7f);
		
		box = new JComboBox();
//		box.addItem("black");
		box.addItem("white");
		box.addItem("green");
		box.addItem("cyan");
		box.addItem("magenta");
		box.setBackground(Color.black);
		box.setForeground(Color.white);
		pane.setBackground(Color.black);
		tf.setBackground(Color.black);
		tf.setForeground(Color.white);
		

		
		btn2.setIcon(new ImageIcon("img\\tjdrb.gif"));
		p = new JPanel();
		p.add("Center", btn2);
		p.setVisible(false);
		setLayout(null);
		js.setBounds(0, 0, 415, 225);
		tf.setBounds(5, 233, 270, 25);
		box.setBounds(280, 233, 80, 25);
		btn.setBounds(365, 228, 40, 35);
		p.setBounds(360, 180, 50, 50);
		add(js);
		add(tf);
		add(box);
		add(btn);
		add(p);
		
		
		setSize(420,300);
		Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit 객체 생성
		Dimension ds = tk.getScreenSize(); // 현재 스크린 사이즈를 저장
		setLocation(ds.width/2+130, ds.height/2+75);
		
		setVisible(false);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tf.addActionListener(this);
		box.addActionListener(this);
		btn.addActionListener(this);
		
		for(int i=0;i<9;i++){
			emo.btnEmoticon[i].addActionListener(this);
		}
		
	}
	// 색상 변경
	public void initStyle(){
		// default 색상
		
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		for(int i=0;i<9;i++){
			style[i] = pane.addStyle("emo["+i+"]", null);
			StyleConstants.setIcon(style[i], emo.emoticon[i]);
		}
		
		Style white = pane.addStyle("white", def);
		StyleConstants.setForeground(white, Color.white);
		
		Style green = pane.addStyle("green", def);
		StyleConstants.setForeground(green, Color.green);
		
		Style cyan = pane.addStyle("cyan", def);
		StyleConstants.setForeground(cyan, Color.cyan);
		
		Style magenta = pane.addStyle("magenta", def);
		StyleConstants.setForeground(magenta, Color.magenta);
	}
	
	// 문자열 결합
	public void append(String msg, String color){
		
		Document doc = pane.getDocument();
		try {
			doc.insertString(doc.getLength(), msg+"\n", pane.getStyle(color));
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tf){
			Date date = new Date();
			String data = tf.getText();
			if(data.length()<1 && bCheck==false){
				setVisible(false);
				return;
			}
			initStyle();
			String color = box.getSelectedItem().toString();
			append(simp.format(date)+" : "+data, color);
			
			tf.setText("");
			if(bCheck == true){
				Document doc = pane.getDocument();
				
				try {
					doc.insertString(doc.getLength(), "\n", style[a]);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				emo.setVisible(false);
				btnCheck = false;
				tf.requestFocus();
			}
			bCheck = false;
			p.setVisible(false);
		}
		if(e.getSource()==btn){
			if(btnCheck){
				emo.setVisible(false);
				btnCheck = false;
			}else{
				emo.setVisible(true);
				btnCheck = true;
			}
		}
		if(e.getSource()==box){
			tf.requestFocus();
		}
		for(int i=0;i<9;i++){
			if(e.getSource()==emo.btnEmoticon[i]){
				emo.setVisible(false);
				a=i;
				bCheck = true;
				tf.requestFocus();
				btn2.setIcon(emo.emoticon[i]);
				p.setVisible(true);
			}
		}
		
	}
}
