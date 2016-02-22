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

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import java.io.IOException;  
import java.net.URI;  
import java.net.URISyntaxException;  

public class WaitRoom extends JPanel implements ActionListener,MouseListener { 
   JTable table1, table2; // ������. 
   DefaultTableModel model1, model2; // ������ ���� ���� 
   JTextPane pane; 
   JScrollBar bar;
   JTextField tf;
   JComboBox box; 
   JButton b1, b2, b3, b4; //���� ��ư(�游���, ���ӹ��, ��������, ������
   TableColumn column1, column2; 
   JLabel la1, la2, la3; //����
   
   //// ���� �ڱ����� /////
   JProgressBar pb;
   JButton avaBtn;
   JLabel laId,laScore,laChode,laRate;
   Checkbox cb;
   ImageIcon stateicon;
   //////////////////////
   
   private Cursor cur = new Cursor(Cursor.HAND_CURSOR); 
   
   WaitRoom() { 
      la1=new JLabel(new ImageIcon("img\\����1.jpg"));  
      la2=new JLabel(new ImageIcon("img\\s����1.jpg"));  
      la3=new JLabel(new ImageIcon("img\\s����2.jpg"));  

      String[] col1 = { "���ȣ", "���̸�", "����/�����", "�ο�" }; 
      String[][] row1 = new String[0][4]; // �ʱ� 0��, but �����ʹ� 3����.. 
      model1 = new DefaultTableModel(row1, col1){
         @Override
         public boolean isCellEditable(int row, int column) { // ���� �ȵǰ� ����.
            // TODO Auto-generated method stub
            return false;
         }
      };
      table1 = new JTable(model1); 
      JScrollPane js1 = new JScrollPane(table1); 
      table1.getTableHeader().setReorderingAllowed(false); 
      table1.getTableHeader().setResizingAllowed(false); 
   
      String[] col2 = { "ID", "��ȭ��", "����", "��ġ" }; 
      String[][] row2 = new String[0][4]; // �ʱ� 0��, but �����ʹ� 3����.. 
      model2 = new DefaultTableModel(row2, col2){
         @Override
         public boolean isCellEditable(int row, int column) { // ���� �ȵǰ� ����.
            // TODO Auto-generated method stub
            return false;
         }
      }; 
      table2 = new JTable(model2); 
      JScrollPane js2 = new JScrollPane(table2); 
      table2.getTableHeader().setReorderingAllowed(false); 
      table2.getTableHeader().setResizingAllowed(false); 
   
      pane = new JTextPane(); 
      pane.setEditable(false); 
      JScrollPane js3 = new JScrollPane(pane); 
      bar = js3.getVerticalScrollBar();
      tf = new JTextField(20); 
      box = new JComboBox(); 
      box.addItem("black"); 
      box.addItem("blue"); 
      box.addItem("green"); 
      box.addItem("cyan"); 
      box.addItem("pink"); 
   
      b1 = new JButton("�游���"); 
      b2 = new JButton("���ӹ��"); 
      b3 = new JButton("��������"); 
      b4 = new JButton("������"); 
      b1.setFont(new Font("���� ���", Font.BOLD, 20)); 
      b2.setFont(new Font("���� ���", Font.BOLD, 20)); 
      b3.setFont(new Font("���� ���", Font.BOLD, 20)); 
      b4.setFont(new Font("���� ���", Font.BOLD, 20)); 
   
      JPanel p = new JPanel(){
         protected void paintComponent(Graphics g) {
            if (g instanceof Graphics2D) {
               final int R = 240;
               final int G = 240;
               final int B = 240;

               Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(),
                     new Color(R, G, B, 255), true);
               Graphics2D g2d = (Graphics2D) g;
               g2d.setPaint(p);
               g2d.fillRect(0, 0, getWidth(), getHeight());
            }
         }
      }; 
      p.setLayout(new GridLayout(4,0,20,20));
      p.add(b1); 
      p.add(b2); 
      p.add(b3); 
      p.add(b4);
      
      ////////////////////
      
      stateicon = new ImageIcon("img\\wrstate.png");
      
      JPanel p2 = new JPanel(){
         @Override
         protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(stateicon.getImage(), 0, 0, getWidth(),getHeight(), this);
         }
      };
      avaBtn = new JButton();
      avaBtn.setBorderPainted(false); //��ư ��輱 ����
      avaBtn.setContentAreaFilled(false); //�����ߴ� ��ư ǥ�� ����
      avaBtn.setFocusPainted(false); //��ư���� ��� ����
      avaBtn.setOpaque(false);
      
      pb = new JProgressBar(0, 100);
      pb.setForeground(Color.cyan);
      pb.setBorderPainted(false);
      pb.setBackground(Color.white);
      pb.setStringPainted(true);
      laId = new JLabel("FreshQ ��");
      laScore = new JLabel("���� 27�� 15��");
      laChode = new JLabel("�ʴ� �ź�");
      laRate = new JLabel("�·�");
      cb = new Checkbox();
      
      laId.setFont(new Font("����",Font.BOLD,25));
      laScore.setFont(new Font("����",Font.BOLD,18));
      laRate.setFont(new Font("����",Font.BOLD,18));
      laChode.setFont(new Font("����",Font.BOLD,18));
      ////////////////////
      
      setLayout(null);
       la1.setBounds(425, 635, 635, 100);//����1
       la2.setBounds(425, 755, 300, 150);//����2
       la3.setBounds(760, 755, 300, 150);
       js1.setBounds(425, 15, 755, 590); //���� 
       js2.setBounds(10, 15, 400, 300); //������ 
       js3.setBounds(10, 320, 400, 340); //ä��â 
       tf.setBounds(10, 665, 295, 30); //ä��â�Է�â 
       box.setBounds(310, 665, 100, 30); //���ڻ��� 
       p.setBounds(1080, 635, 100, 270); //��ư
       p2.setBounds(10, 700, 400, 220);
       avaBtn.setBounds(25, 710, 157 ,200);
       laId.setBounds(220, 725, 170, 30);
       laScore.setBounds(220, 770, 170, 30);
       laRate.setBounds(220, 810, 60, 30);
       pb.setBounds(270, 815, 105, 23);
       cb.setBounds(220, 852, 30, 30);
       laChode.setBounds(245, 857, 120, 20);
       
       p2.add(avaBtn);
       p2.add(laId);
       
       // 870
   
      add(js1);add(js2);add(js3); 
      add(tf);add(box); 
      add(p);
      add(la1);add(la2);add(la3);
      add(avaBtn);add(laId);
      add(laScore);add(pb);
      add(laChode);add(laRate);
      add(cb);
      add(p2);

//      mr.b1.addActionListener(this);
//      mr.b2.addActionListener(this);
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
         if(i==0)//���ȣ  
         {  
            column1.setPreferredWidth(50);  
            rnd.setHorizontalAlignment(JLabel.CENTER);  
         }  
         else if(i==1)//���̸�  
         {  
            column1.setPreferredWidth(400);  
         }  
         else if(i==2)//����/�����  
         {  
            //column.setPreferredWidth(100);  
            column1.setPreferredWidth(60);  
            rnd.setHorizontalAlignment(JLabel.CENTER);  
         }  
         else if(i==3)//�ο�  
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
         else if(i==1)//��ȭ��  
         {  
            column2.setPreferredWidth(100);  
            rnd.setHorizontalAlignment(JLabel.CENTER);  
         }  
         else if(i==2)//����  
         {  
            //column.setPreferredWidth(100);  
            column2.setPreferredWidth(50);  
            rnd.setHorizontalAlignment(JLabel.CENTER);  
         }  
         else if(i==3)//��ġ  
         {  
            //column.setPreferredWidth(150);  
            column2.setPreferredWidth(50);  
            rnd.setHorizontalAlignment(JLabel.CENTER);  
         }  
         column2.setCellRenderer(rnd);   
      }  

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

   @Override 
   public void actionPerformed(ActionEvent e) { 
      // TODO Auto-generated method stub 
      if (e.getSource() == b1) {
//         mr.setLocationRelativeTo(null);
//         mr.setVisible(true);
      }/* else if (e.getSource() == mr.b2) {
         mr.setVisible(false);
         mr.la3.setVisible(false);
         mr.pf.setText("");
         mr.tf.setText("");
         mr.open.setSelected(true);
         mr.pf.setVisible(false);
      }*/
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