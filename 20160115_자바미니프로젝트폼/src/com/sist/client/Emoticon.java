package com.sist.client;

import java.awt.*;
import javax.swing.*;
public class Emoticon extends JFrame{
	JButton[] btnEmoticon = new JButton[9];
	ImageIcon[] emoticon = new ImageIcon[9];
	public Emoticon(){
		setLayout(new GridLayout(3, 3));
		for(int i=0;i<btnEmoticon.length;i++){
			emoticon[i] = new ImageIcon("img\\emo"+i+".gif");
			btnEmoticon[i] = new JButton(emoticon[i]);
			btnEmoticon[i].setOpaque(true);
			add(btnEmoticon[i]);
			
		}
		setUndecorated(true);
		setOpacity(0.7f);
		setSize(140,140);
		setVisible(false);
		
		
		Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit 객체 생성
		Dimension ds = tk.getScreenSize(); // 현재 스크린 사이즈를 저장
		setLocation(ds.width/2+(300), ds.height/2+(200));
//		setVisible(true);
	}
//	public static void main(String[] args){
//		new Emoticon();
//	}
}
