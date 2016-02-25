package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.*;
public class UserInfo extends JFrame implements ActionListener{
   JLabel la_id,la_name,la_nickname,la_day,la_score,la_rate;
   JButton btn_NO;
   JButton avata;
   public UserInfo() { 
      // TODO Auto-generated constructor stub 
   
      la_id = new JLabel("���̵�");
      la_name = new JLabel("�̸�"); 
      la_nickname = new JLabel("�г���");
      la_day = new JLabel("������");
      la_score = new JLabel("���� 27�� 76��");
      la_rate = new JLabel("�·� 68.55%");
      avata = new JButton();
      btn_NO = new JButton("���");
       
      ButtonGroup bg = new ButtonGroup(); 
      la_id.setFont(new Font("�ü�ü",Font.BOLD, 14)); 
      la_name.setFont(new Font("�ü�ü",Font.BOLD, 14)); 
      la_nickname.setFont(new Font("�ü�ü",Font.BOLD, 14)); 
      la_score.setFont(new Font("�ü�ü",Font.BOLD, 14));
      la_rate.setFont(new Font("�ü�ü",Font.BOLD, 14));
      la_day.setFont(new Font("�ü�ü",Font.BOLD, 14));
      btn_NO.setFont(new Font("�ü�ü",Font.BOLD, 14)); 
       
      // ��ġ �밡��. 
      setLayout(null);
      avata.setBounds(10, 10, 157, 195);
      la_id.setBounds(180, 15, 140, 20);
      la_name.setBounds(180, 48, 140, 20);
      la_nickname.setBounds(180, 81, 140, 20);
      la_score.setBounds(180, 114, 140, 20);
      la_rate.setBounds(180, 147, 140, 20);
      la_day.setBounds(180, 180, 160, 20);
      btn_NO.setBounds(135, 210, 65, 27);
      avata.setBackground(Color.black);
      
      //�߰� 
      add(la_id);
      add(la_name);add(la_score);
      add(la_nickname);add(la_day);add(la_rate);
      add(avata);add(btn_NO);
      //ȭ�� ��ġ ���� 
      Toolkit tk = Toolkit.getDefaultToolkit(); // getDefaultToolkit ��ü ���� 
      Dimension ds = tk.getScreenSize(); // ���� ��ũ�� ����� ���� 
      setLocation(ds.width/2-600, ds.height/2-50);
      /*setDefaultCloseOperation(EXIT_ON_CLOSE);*/ 
      setSize(350, 280); 
      setVisible(false); 
      btn_NO.addActionListener(this);
   } 
   
   public static void main(String[] args){
      new UserInfo();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource()==btn_NO){
         dispose();
      }
   }
}