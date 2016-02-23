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

import com.sist.server.Server; 


public class ChatRoom extends JFrame{//jdialog 
   JPanel[] pan = new JPanel[2]; 
   MakeRoom mr; 
   JTextField[] idtf = new JTextField[2];
   JTextPane pane; 
   JScrollBar bar;
   JTextField tf; 
   JComboBox box; 
   JTable table; 
   DefaultTableModel model; 
   JButton b1, b2, b3; 
   boolean[] sw = new boolean[2];
   private Cursor cur = new Cursor(Cursor.HAND_CURSOR); 


public ChatRoom() { 
   
   pan[0] = new JPanel(); 
   pan[1] = new JPanel(); 
   idtf[0] = new JTextField(); 
   idtf[1] = new JTextField(); 
   pan[0].setBackground(Color.black); 
   pan[1].setBackground(Color.black); 
   idtf[0].setEditable(false);
   idtf[1].setEditable(false);
   idtf[0].setHorizontalAlignment(JLabel.CENTER);
   idtf[1].setHorizontalAlignment(JLabel.CENTER);
   idtf[0].setFont(new Font("����",Font.BOLD,14));
   idtf[1].setFont(new Font("����",Font.BOLD,14));
   pane = new JTextPane(); 
   pane.setEditable(false); 
   JScrollPane js1 = new JScrollPane(pane);
   bar = js1.getVerticalScrollBar();
   tf = new JTextField(); 
   box = new JComboBox(); 
   box.addItem("black"); 
   box.addItem("blue"); 
   box.addItem("green"); 
   box.addItem("cyan"); 
   box.addItem("pink"); 
   b1 = new JButton("��   ��"); 
   b2 = new JButton("��   ��"); 
   b3 = new JButton("������"); 
   b1.setFont(new Font("���� ����", Font.BOLD, 20)); 
   b2.setFont(new Font("���� ����", Font.BOLD, 20)); 
   b3.setFont(new Font("���� ����", Font.BOLD, 20)); 
   b1.setCursor(cur); 
   b2.setCursor(cur); 
   b3.setCursor(cur);
   b2.setEnabled(false);


   String[] col = {"ID", "��ȭ��", "�·�"}; 
   String[][] row = new String[0][3]; 
   model = new DefaultTableModel(row, col){
       @Override
       public boolean isCellEditable(int row, int column) { // ���� �ȵǰ� ����.
          // TODO Auto-generated method stub
          return false;
       }
    }; 
   table = new JTable(model); 
   JScrollPane js2 = new JScrollPane(table); 
   table.getTableHeader().setResizingAllowed(false); 
   table.getTableHeader().setReorderingAllowed(false); 


   // ��ġ 


   setLayout(null);
   pan[0].setBounds(20, 15, 157, 150); 
   pan[1].setBounds(215, 15, 157, 150); 
   add(pan[0]); 
   add(pan[1]); 
   idtf[0].setBounds(10, 170, 180, 30); 
   idtf[1].setBounds(205, 170, 180, 30); 
   add(idtf[0]); 
   add(idtf[1]);

   js1.setBounds(10, 215, 375, 120); 
   tf.setBounds(10, 340, 270, 30); 
   box.setBounds(285, 340, 100, 30); 
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
   add(box); 
   add(p); 
   setSize(605, 420);
   setLocationRelativeTo(null); 
//   setModal(true);
   setResizable(false);
   } 

public void initStyle() { 
   // default ������ �����´� 
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
      doc.insertString(doc.getLength(), msg + "\n", pane.getStyle(color));// ��ü 
                                                         // ���� 
                                                         // �����ͼ� 
                                                         // msg����ĭ�� 
                                                         // �߰� 
   } catch (BadLocationException e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
   }
} 
} 