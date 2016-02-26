package com.sist.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GameWay extends JFrame implements KeyListener{
//   JPanel pview;
   JLabel la;
   int next = 1;
   public GameWay() {
      la = new JLabel(new ImageIcon("img\\gameway"+next+".jpg"));
//      pview = new JPanel();
//      pview.add("Center",new JLabel(new ImageIcon(setImage("img\\"+next+".png",pview.getWidth(),pview.getHeight()))));
      
      add("Center",la);
      setSize(853,635);
      setVisible(false);
      addKeyListener(this);
      
      //화면 위치 조정 
       Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit 객체 생성 
       Dimension ds = tk.getScreenSize(); // 현재 스크린 사이즈를 저장 
       setLocation(ds.width/2-425, ds.height/2-315); 
   }
   
   
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      new GameWay();
   }
   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      switch (e.getKeyCode()) {
      case KeyEvent.VK_ENTER:
         if(next==6)
            dispose();
         if(next<6){
            next++;
            la.setIcon(new ImageIcon("img\\gameway"+next+".jpg"));
         }
         
         
      break;
      case KeyEvent.VK_BACK_SPACE:
         if(next>1){
         next--;
         la.setIcon(new ImageIcon("img\\gameway"+next+".jpg"));
         }
      break;
      }
   }
   @Override
   public void keyReleased(KeyEvent arg0) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyTyped(KeyEvent arg0) {
      // TODO Auto-generated method stub
      
   }
}