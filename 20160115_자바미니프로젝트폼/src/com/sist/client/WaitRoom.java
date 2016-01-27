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
   JTable table1, table2; //껍데기.
   DefaultTableModel model1,model2; //껍데기 안의 내용
   JTextPane pane;
   JTextField tf;
   JComboBox box;
   JButton b1,b2,b3,b4,b5,b6;
   
   WaitRoom() 
   {
      String[] col1={"방이름","공개/비공개","인원"};
      String[][] row1=new String[0][3]; //초기 0값, but 데이터는 3개씩..
      model1=new DefaultTableModel(row1, col1);
      table1=new JTable(model1);
      table1.getTableHeader().setReorderingAllowed(false);//게시판 타이틀 위치변경 안되게 하는것  
      table1.getTableHeader().setResizingAllowed(false);  
      table1.getTableHeader().setBackground(Color.pink);//테이블바 컬러지정  
      table1.setRowHeight(35);//타이틀바 밑에 제목들의 높이 조정  
      table1.setShowVerticalLines(false);//내용들 정렬될때 세로줄 없애는것  

      JScrollPane js1=new JScrollPane(table1);
      
      String[] col2={"ID","대화명","성별","위치"};
      String[][] row2=new String[0][4]; //초기 0값, but 데이터는 3개씩..
      model2=new DefaultTableModel(row2, col2);
      table2=new JTable(model2);
      table2.getTableHeader().setReorderingAllowed(false);//게이판 타이틀 위치변경 안되게 하는것  
      table2.getTableHeader().setResizingAllowed(false);  
      table2.getTableHeader().setBackground(Color.pink);//테이블바 컬러지정  
      table2.setRowHeight(35);//타이틀바 밑에 제목들의 높이 조정  
      table2.setShowVerticalLines(false);//내용들 정렬될때 세로줄 없애는것  

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
      
      b1=new JButton("방만들기");
      b2=new JButton("방들어가기");
      b3=new JButton("1:1 게임");
      b4=new JButton("쪽지보내기");
      b5=new JButton("정보보기");
      b6=new JButton("나가기");
      
      JPanel p=new JPanel();
      p.setLayout(new GridLayout(1,6,10,10)); 
      p.add(b1);      p.add(b2);
      p.add(b3);      p.add(b4);
      p.add(b5);      p.add(b6);

      setLayout(null);
      js1.setBounds(425, 15, 755, 590); //대기실  
      js2.setBounds(10, 15, 400, 350); //접속자  
      js3.setBounds(10, 370, 400, 350); //채팅창  
      tf.setBounds(10, 725, 295, 30); //채팅창입력창  
      box.setBounds(310, 725, 100, 30); //글자색깔  
      p.setBounds(425, 725, 755, 30); //버튼  

      add(js1);
      add(js2);
      add(js3);
      add(tf);
      add(box);
      add(p);
      tf.addActionListener(this);
   }
   public void initStyle() {
      // default 색상을 가져온다
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
         doc.insertString(doc.getLength(), msg+"\n",pane.getStyle(color));// 전체 문자 가져와서 msg다음칸에 추가
      } catch (BadLocationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String name="김진현";
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







































