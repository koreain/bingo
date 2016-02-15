package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
public class WaitRoom extends JPanel implements ActionListener{
   JTable table1, table2; //������.
   DefaultTableModel model1,model2; //������ ���� ����
   JTextPane pane;
   JTextField tf;
   JComboBox box;
   JButton b1,b2,b3,b4,b5,b6;
   MakeRoom mr=new MakeRoom();
   
   private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
   WaitRoom()
   {
	  String[] col1={"���ȣ","���̸�","����/�����","�ο�"};
      String[][] row1=new String[0][4]; //�ʱ� 0��, but �����ʹ� 3����..
      model1=new DefaultTableModel(row1, col1);
      table1=new JTable(model1);
      JScrollPane js1=new JScrollPane(table1);
      
      String[] col2={"ID","��ȭ��","����","��ġ"};
      String[][] row2=new String[0][4]; //�ʱ� 0��, but �����ʹ� 3����..
      model2=new DefaultTableModel(row2, col2);
      table2=new JTable(model2);
      JScrollPane js2=new JScrollPane(table2);
      
      pane=new JTextPane();
      pane.setEditable(false);
      JScrollPane js3=new JScrollPane(pane);
      
      tf=new JTextField(20);
      box=new JComboBox();
      box.addItem("black");
      box.addItem("blue");
      box.addItem("green");
      box.addItem("cyan");
      box.addItem("pink");
      
      b1=new JButton("�游���");
      b2=new JButton("�����");
      b3=new JButton("1:1 ����");
      b4=new JButton("���ӹ��");
      b5=new JButton("��������");
      b6=new JButton("������");
      b1.setFont(new Font("���� ���",Font.BOLD,20));
      b2.setFont(new Font("���� ���",Font.BOLD,20));
      b3.setFont(new Font("���� ���",Font.BOLD,20));
      b4.setFont(new Font("���� ���",Font.BOLD,20));
      b5.setFont(new Font("���� ���",Font.BOLD,20));
      b6.setFont(new Font("���� ���",Font.BOLD,20));
      
      JPanel p=new JPanel();
      p.setLayout(new GridLayout(1,6,10,10)); 
      p.add(b1);      p.add(b2);
      p.add(b3);      p.add(b4);
      p.add(b5);      p.add(b6);

      setLayout(null);
      js1.setBounds(425, 15, 755, 590); //���� 
      js2.setBounds(10, 15, 400, 350); //������ 
      js3.setBounds(10, 370, 400, 500); //ä��â 
      tf.setBounds(10, 875, 295, 30); //ä��â�Է�â 
      box.setBounds(310, 875, 100, 30); //���ڻ��� 
      p.setBounds(425, 820, 755, 80); //��ư 

      add(js1);
      add(js2);
      add(js3);
      add(tf);
      add(box);
      add(p);
      tf.addActionListener(this);
      mr.b1.addActionListener(this);
      mr.b2.addActionListener(this);
      b1.addActionListener(this); 
      
      b1.setCursor(cur);
      b2.setCursor(cur);
      b3.setCursor(cur);
      b4.setCursor(cur);
      b5.setCursor(cur);
      b6.setCursor(cur);
   }
   public void initStyle() {
      // default ������ �����´�
      Style def=StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
      Style blue=pane.addStyle("blue", def);
      StyleConstants.setForeground(blue, Color.blue);
      
      Style green=pane.addStyle("green", def);
      StyleConstants.setForeground(green, Color.green);
      
      Style cyan=pane.addStyle("cyan", def);
      StyleConstants.setForeground(cyan, Color.cyan);
      
      Style pink=pane.addStyle("pink", def);
      StyleConstants.setForeground(pink, Color.pink);
   }
   public void append(String msg, String color) {
      Document doc=pane.getDocument();
      try {
         doc.insertString(doc.getLength(), msg+"\n",pane.getStyle(color));// ��ü ���� �����ͼ� msg����ĭ�� �߰�
      } catch (BadLocationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name="������";
		
		String data=name+">> "+tf.getText();
		if(e.getSource()==tf) {
			if(data.length()<1)
				return;
			initStyle();
			String color=box.getSelectedItem().toString();
			append(data,color);
			tf.setText("");	
		}
		 if(e.getSource()==b1) {
			 mr.setLocationRelativeTo(null);
			mr.setVisible(true);
		}else if(e.getSource()==mr.b2) {
			mr.setVisible(false);
		}
		 
		 if(e.getSource()==mr.b1) {
			  String data1=mr.tf.getText();
		 }
		 
	}
}

