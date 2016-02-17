package com.sist.client;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;

import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ChatRoom extends JDialog implements ActionListener {
	JPanel pan1, pan2 = new JPanel();
	JTextField idtf1, idtf2 = new JTextField();
	JTextArea ta;
	JTextField tf;
	JTable table;
	DefaultTableModel model;
	JButton b1, b2, b3, b4;
	
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);

	public ChatRoom() {
		setTitle("¼­¹ö¿¡¼­ ¹Þ¾Æ¿Ã ¹æÀÌ¸§");
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JTextField idtf1 = new JTextField();
		JTextField idtf2 = new JTextField();
		pan1.setBackground(Color.black);
		pan2.setBackground(Color.black);

		ta = new JTextArea();
		ta.setEditable(false);
		JScrollPane js1 = new JScrollPane(ta);
		tf = new JTextField(20);
		b1 = new JButton("ÃÊ   ´ë");
		b2 = new JButton("°­   Åð");
		b3 = new JButton("³ª°¡±â");
		b4 = new JButton("ÁØ   ºñ");
		b1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		b2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		b3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		b4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		b1.setCursor(cur);
		b2.setCursor(cur);
		b3.setCursor(cur);
		b4.setCursor(cur);

		String[] col = { "ID", "´ëÈ­¸í", "¼ºº°" };
		String[][] row = new String[0][3];
		model = new DefaultTableModel(row, col);
		table = new JTable(model);
		JScrollPane js2 = new JScrollPane(table);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// ¹èÄ¡

		setLayout(null);
		pan1.setBounds(10, 15, 180, 150);
		pan2.setBounds(205, 15, 180, 150);
		add(pan1);
		add(pan2);
		idtf1.setBounds(10, 170, 180, 30);
		idtf2.setBounds(205, 170, 180, 30);
		add(idtf1);
		add(idtf2);

		js1.setBounds(10, 215, 375, 120);
		tf.setBounds(10, 340, 270, 30);
		b4.setBounds(285, 340, 100, 30);
		js2.setBounds(400, 15, 190, 185);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1, 5, 5));
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.setBounds(400, 215, 190, 155);

		add(js1);
		add(js2);
		add(tf);
		add(b4);
		add("Center", p);
		setSize(620, 420);
		setVisible(false);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		tf.addActionListener(this);

		setModal(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b1) {

		} else if (e.getSource() == b2) {

		} else if (e.getSource() == b3) {
			ta.setText("");
			dispose();
			setVisible(false);
		} else if (e.getSource() == b4) {
			
		} else if (e.getSource() == tf) {
			String text = tf.getText();
			if (text.length() < 1) {
				return;
			}
			ta.append(text + "\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			tf.setText("");
		}
	}
}
