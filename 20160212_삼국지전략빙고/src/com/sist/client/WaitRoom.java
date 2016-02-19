package com.sist.client; 

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*; 
import javax.swing.table.*; 
import javax.swing.text.BadLocationException; 
import javax.swing.text.Document; 
import javax.swing.text.Style; 
import javax.swing.text.StyleConstants; 
import javax.swing.text.StyleContext; 

import java.io.IOException;  
import java.net.URI;  
import java.net.URISyntaxException;  
import java.awt.Desktop;  

public class WaitRoom extends JPanel implements ActionListener,MouseListener { 
	JTable table1, table2; // 껍데기. 
	DefaultTableModel model1, model2; // 껍데기 안의 내용 
	JTextPane pane;
	JTextArea ta;
	JTextField tf;
	JComboBox box;
	JButton b1, b2, b3, b4; //대기실 버튼(방만들기, 게임방법, 게임정보, 나가기
	MakeRoom mr = new MakeRoom(); 
	TableColumn column1, column2; 
	JLabel la1, la2, la3; //광고
	JScrollBar bar;
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR); 
	
	WaitRoom() { 
		la1=new JLabel(new ImageIcon("img\\광고1.jpg"));  
		la2=new JLabel(new ImageIcon("img\\s광고1.jpg"));  
		la3=new JLabel(new ImageIcon("img\\s광고2.jpg"));  

		String[] col1 = { "방번호", "방이름", "공개/비공개", "인원" }; 
		String[][] row1 = new String[0][4]; // 초기 0값, but 데이터는 3개씩.. 
		model1 = new DefaultTableModel(row1, col1); 
		table1 = new JTable(model1); 
		JScrollPane js1 = new JScrollPane(table1); 
		table1.getTableHeader().setReorderingAllowed(false); 
		table1.getTableHeader().setResizingAllowed(false); 
	
		String[] col2 = { "ID", "대화명", "성별", "위치" }; 
		String[][] row2 = new String[0][4]; // 초기 0값, but 데이터는 3개씩.. 
		model2 = new DefaultTableModel(row2, col2); 
		table2 = new JTable(model2); 
		JScrollPane js2 = new JScrollPane(table2); 
		table2.getTableHeader().setReorderingAllowed(false); 
		table2.getTableHeader().setResizingAllowed(false); 
	
		pane = new JTextPane(); 
		pane.setEditable(false); 
		ta=new JTextArea();
		JScrollPane js3 = new JScrollPane(ta); 
		bar=js3.getVerticalScrollBar();
		tf = new JTextField(20); 
		box = new JComboBox(); 
		box.addItem("black"); 
		box.addItem("blue"); 
		box.addItem("green"); 
		box.addItem("cyan"); 
		box.addItem("pink"); 
	
		b1 = new JButton("방만들기"); 
		b2 = new JButton("게임방법"); 
		b3 = new JButton("게임정보"); 
		b4 = new JButton("나가기"); 
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 20)); 
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 20)); 
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 20)); 
		b4.setFont(new Font("맑은 고딕", Font.BOLD, 20)); 
	
		JPanel p = new JPanel(); 
		p.setLayout(new GridLayout(4,0,20,20));
		p.add(b1); 
		p.add(b2); 
		p.add(b3); 
		p.add(b4); 
	
		setLayout(null); 
		setLayout(null);
	    la1.setBounds(425, 635, 635, 100);//광고1
	    la2.setBounds(425, 755, 300, 150);//광고2
	    la3.setBounds(760, 755, 300, 150);
	    js1.setBounds(425, 15, 755, 590); //대기실 
	    js2.setBounds(10, 15, 400, 350); //접속자 
	    js3.setBounds(10, 370, 400, 500); //채팅창 
	    tf.setBounds(10, 875, 295, 30); //채팅창입력창 
	    box.setBounds(310, 875, 100, 30); //글자색깔 
	    p.setBounds(1080, 635, 100, 270); //버튼 

		add(js1); 
		add(js2); 
		add(js3); 
		add(tf); 
		add(box); 
		add(p);
		add(la1);
		add(la2);  
		add(la3);  

		mr.b1.addActionListener(this); 
		mr.b2.addActionListener(this); 
		b1.addActionListener(this); 
		la1.addMouseListener(this);  
		la2.addMouseListener(this);  
		la3.addMouseListener(this);  

		la1.setCursor(cur);  
		la2.setCursor(cur);  
		la3.setCursor(cur);  

		b1.setCursor(cur); 
		b2.setCursor(cur); 
		b3.setCursor(cur); 
		b4.setCursor(cur); 
		for(int i=0;i<col1.length;i++)  
		{  
			column1=table1.getColumnModel().getColumn(i);  
			DefaultTableCellRenderer rnd=new DefaultTableCellRenderer();  
			if(i==0)//방번호  
			{  
				column1.setPreferredWidth(50);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			else if(i==1)//방이름  
			{  
				column1.setPreferredWidth(400);  
			}  
			else if(i==2)//공개/비공개  
			{  
				//column.setPreferredWidth(100);  
				column1.setPreferredWidth(60);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			else if(i==3)//인원  
			{  
				//column.setPreferredWidth(150);  
				column1.setPreferredWidth(60);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			column1.setCellRenderer(rnd);   
		}  
		for(int i=0;i<col2.length;i++)  
		{  
			column2=table2.getColumnModel().getColumn(i);  
			DefaultTableCellRenderer rnd=new DefaultTableCellRenderer();  
			if(i==0)//ID  
			{  
				column2.setPreferredWidth(100);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			else if(i==1)//대화명  
			{  
				column2.setPreferredWidth(100);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			else if(i==2)//성별  
			{  
				//column.setPreferredWidth(100);  
				column2.setPreferredWidth(50);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			else if(i==3)//위치  
			{  
				//column.setPreferredWidth(150);  
				column2.setPreferredWidth(50);  
				rnd.setHorizontalAlignment(JLabel.CENTER);  
			}  
			column2.setCellRenderer(rnd);   
		}  

	} 
	
	public void initStyle() { 
		// default 색상을 가져온다 
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE); 
		Style blue = pane.addStyle("blue", def); 
		StyleConstants.setForeground(blue, Color.blue); 


		Style green = pane.addStyle("green", def); 
		StyleConstants.setForeground(green, Color.green); 


		Style cyan = pane.addStyle("cyan", def); 
		StyleConstants.setForeground(cyan, Color.cyan); 


		Style pink = pane.addStyle("pink", def); 
		StyleConstants.setForeground(pink, Color.pink); 
	} 

	public void append(String msg, String color) { 
		Document doc = pane.getDocument(); 
		try { 
			doc.insertString(doc.getLength(), msg + "\n", pane.getStyle(color));// 전체 
																				// 문자 
																				// 가져와서 
																				// msg다음칸에 
																				// 추가 
		} catch (BadLocationException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 

	@Override 
	public void actionPerformed(ActionEvent e) { 
		// TODO Auto-generated method stub 
		if (e.getSource() == b1) {
			mr.setLocationRelativeTo(null);
			mr.setVisible(true);
		} else if (e.getSource() == mr.b2) {
			mr.setVisible(false);
			mr.la3.setVisible(false);
			mr.pf.setText("");
			mr.tf.setText("");
			mr.open.setSelected(true);
			mr.pf.setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==la1){  
			try {  
				Desktop.getDesktop().browse(new URI("http://booking.naver.com/"));  
			} catch (IOException ex) {  
				ex.printStackTrace();  
			} catch (URISyntaxException ex) {  
				ex.printStackTrace();  
			}  
		}  
		if(e.getSource()==la2){  
			try {  
				Desktop.getDesktop().browse(new URI("https://kr.burberry.com/"));  
			} catch (IOException ex) {  
				ex.printStackTrace();  
			} catch (URISyntaxException ex) {  
				ex.printStackTrace();  
			}  
		}  
		if(e.getSource()==la3){  
			try {  
				Desktop.getDesktop().browse(new URI("http://www.mini.co.kr/mini/main/index.jsp"));  
			} catch (IOException ex) {  
				ex.printStackTrace();  
			} catch (URISyntaxException ex) {  
				ex.printStackTrace();  
			}  
		}  

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
