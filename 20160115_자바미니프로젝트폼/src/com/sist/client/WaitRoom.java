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
   
   WaitRoom() 
   {
      String[] col1={"���̸�","����/�����","�ο�"};
      String[][] row1=new String[0][3]; //�ʱ� 0��, but �����ʹ� 3����..
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
      b4=new JButton("����������");
      b5=new JButton("��������");
      b6=new JButton("������");
      
      JPanel p=new JPanel();
      p.setLayout(new GridLayout(3,2,5,5));
      p.add(b1);      p.add(b2);
      p.add(b3);      p.add(b4);
      p.add(b5);      p.add(b6);

      setLayout(null);
      js1.setBounds(10, 15, 500, 350);
      js2.setBounds(10, 370, 500, 180);
      js3.setBounds(515, 15, 265, 300);
      tf.setBounds(515, 320, 160, 30);
      box.setBounds(680, 320, 90, 30);
      p.setBounds(515, 470, 265, 80);
      add(js1);
      add(js2);
      add(js3);
      add(tf);
      add(box);
      add(p);
      tf.addActionListener(this);
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
   }
}







































