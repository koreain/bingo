package com.sist.server;
import java.util.*;
import java.io.*;
import java.net.*;

import com.sist.client.ChoiceNation;
import com.sist.common.*;
import com.sist.server.Server.Client;
public class Server implements Runnable{
	ChoiceNation cn=new ChoiceNation();
    // ���� ���� 
   ServerSocket ss;
   // port
   final int PORT=33333;
   // ������ ������ ���� 
   Vector<Client> waitVc=new Vector<Client>();
   // �������� ���� 
   Vector<Room> roomVc=new Vector<Room>();
   // ����� ���ȣ ������ ���� �� ��ȯ
   Vector<Integer> rnumVc=new Vector<Integer>();
   public Server()
   {
      try
      {
         ss=new ServerSocket(PORT);
         /*
          *   bind()
          *   listen()
          */
         System.out.println("Server Start...");
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
   }
   // ���ӽ� ==> ������ ���� (��ſ���)
   public void run()
   {
      try
      {
         while(true)
         {
            Socket s=ss.accept();
            // s=> client�� ����(ip,port)
            // Thread�� ���� => ��� 
            Client client=new Client(s);
            client.start();
            // ��Ž��� 
         }
      }catch(Exception ex){}
   }
   public static void main(String[] args) {
      // TODO Auto-generated method stub
        Server server=new Server();
        new Thread(server).start();
   }
   // ��� => Server�� ��系���� ���� : ����Ŭ���� 
    class Client extends Thread
    {
       String id,name,sex,avata,pos;
       // ��� 
       int win,lose;
       Socket s;
       BufferedReader in;
       OutputStream out;
       public Client(Socket s)
       {
          try
          {
             this.s=s;
             in=new BufferedReader(
                   new InputStreamReader(
                         s.getInputStream()));
             out=s.getOutputStream();
          }catch(Exception ex){}
       }
       // ��� 
       public void run()
       {
          try
          {
             while(true)
             {
                int x=1;
                System.out.println(x+"���� WHILE��");
                x++;
                // Ŭ���̾�Ʈ => ��û���� �޴´�
                String msg=in.readLine();
                StringTokenizer st=
                   new StringTokenizer(msg, "|");
                // 100|id|sex|name|avata
                int protocol=Integer.parseInt(st.nextToken());
                System.out.println(x+"���� WHILE�� ��������:"+protocol);
                
                switch(protocol)
                {
                case Function.LOGIN:
                {
                   boolean sameId=false;
                   // ������ �����Ѵ�
                   id=st.nextToken();
                   name=st.nextToken();
                   sex=st.nextToken();
                   avata=st.nextToken();
                   win=Integer.parseInt(st.nextToken());
                   lose=Integer.parseInt(st.nextToken());
                   pos="����";
                   for(Client client:waitVc)
                   {
                      if(client.id.equals(id))
                         sameId=true;
                   }
                   if(sameId)
                   {
                      messageTo(Function.SAMELOGIN+"|"+id);
                      in.close();
                      out.close();
                   }
                   else
                   {
                      // ���ǿ� �ִ� ������� ���� ����
                      messageAll(Function.LOGIN+"|"+id+"|"
                            +name+"|"+sex+"|"+pos);//������ �α������ִ� �����鿡�� ������ ������ ������
                      // ����
                      waitVc.addElement(this); //������ 1�� ������ ���������� waitVc���Ϳ� ���� ��,
                      messageTo(Function.MYLOG+"|"
                              +id+"|"
                              +win+"|"
                              +lose+"|"
                              +avata); //�޼����� �ٽ� ���ο��� �����ش�.
                      for(Client client:waitVc) 
                      {
                         messageTo(Function.LOGIN+"|"
                                  +client.id+"|"
                               +client.name+"|"
                                  +client.sex+"|"
                               +client.pos);
                      }
                   }
                         // ������ �� ����
                     for(Room room:roomVc)
                     {
                         messageTo(Function.MAKEROOM+"|"
                            +room.roomNum+"|"
                             +room.roomName+"|"
                             +room.roomState+"|"
                             +room.current+"/"+room.inwon);
                     }
                   }
                   break;
                   case Function.WAITCHAT:
                   {
                      String data=st.nextToken();
                      String color=st.nextToken();
                      System.out.println("����:"+color);
                      messageAll(Function.WAITCHAT+"|["+name+"]"+data+"|"
                                  +color);
                   }
                   break;
                   case Function.ROOMCHAT:
                   {
                      String data=st.nextToken();
                      String color=st.nextToken();
                      int rNum=Integer.parseInt(st.nextToken());
                      for(Room room : roomVc){
                         if(rNum==room.roomNum)
                          {
                             for(Client client:room.userVc)
                             {
                                client.messageTo(Function.ROOMCHAT+"|["+name+"]"+data+"|"+color);
                                      
                             }
                         }
                      }
                   }
                   break;
                   case Function.EXIT:
                   {
                      messageAll(Function.EXIT+"|"+id);//�����ִ� ��� ó��
                      messageTo(Function.MYEXIT+"|");//������ ��� ó��
                       for(int i=0;i<waitVc.size();i++)
                       {
                          Client client=waitVc.elementAt(i);
                          if(id.equals(client.id))
                          {
                             waitVc.removeElementAt(i);
                             in.close(); //��Ŵݱ� �߿�!!!
                             out.close();//��Ŵݱ� �߿�!!!
                             s.close();
                          }
                       }
                   }
                   break;
                   case Function.MAKEROOM:
                  {
                  System.out.println("test_Server_MAKEROOM");
                  int tempNum=1;
                  System.out.println(tempNum);
                  if(rnumVc.size()!=0)
                  {
                        for(int i=0; i<rnumVc.size()-1;i++)
                        {
                           tempNum=Math.min(rnumVc.elementAt(i),rnumVc.elementAt(i+1));
                        }
                        for(int i=0; i<rnumVc.size();i++)
                        {
                           if(tempNum==rnumVc.elementAt(i))
                              rnumVc.removeElementAt(i);
                        }
                        System.out.println("test========1");
                  }
                  else
                  {
                     tempNum=roomVc.size()+1;
                     System.out.println("test========1-2");
                  }
                  System.out.println("test========2");
                  Room room=new Room(tempNum, //���ȣ
                                 st.nextToken(),//���̸�
                                 st.nextToken(),//����_�����
                                 st.nextToken() //����
                                 );
                  System.out.println("test========3");
                  System.out.println("���� MAKEROOM ���̽��� ���ȣ:"+room.roomNum+"\n"+
                                 "���� MAKEROOM ���̽��� ���̸�:"+room.roomName+"\n"+
                                 "���� MAKEROOM ���̽��� �����:"+room.roomState+"\n"+
                                 "���� MAKEROOM ���̽��� �ο�:"+room.current+"/"+room.inwon);
                  messageAll(Function.MAKEROOM+"|"
                           +room.roomNum+"|"
                           +room.roomName+"|"
                           +room.roomState+"|"
                           +room.current+"/"+room.inwon);
                  room.roomBang=id;
                  pos=room.roomNum+"����";
                  System.out.println("test1111");
                  roomVc.addElement(room);
                  System.out.println("test2222");
                  room.userVc.addElement(this);
                  System.out.println("test3333");
                  System.out.println("���� MAKEROOM ���̽��� ������̵�:"+room.roomBang+"\n"+
                        "���� MAKEROOM ���̽��� ���ȣ:"+pos+"\n"+
                           "������(������������) �߰� �Ϸ�");
               messageTo(Function.MYROOMIN+"|"
                          +id+"|"+name+"|"
                          +sex+"|"+avata+"|"
                          +room.roomName+"|"
                          +room.roomBang+"|"
                          +win+"|"
                          +lose+"|"
                          +room.roomNum);
                  System.out.println("���� MAKEROOM ���̽��� message to(MYROOMIN) ������̵�:"+id+"\n"+
                        "���� MAKEROOM ���̽��� message to(MYROOMIN) ��ȭ��:"+name+"\n"+
                        "���� MAKEROOM ���̽��� message to(MYROOMIN) ��ȭ��:"+sex+"\n"+
                        "���� MAKEROOM ���̽��� message to(MYROOMIN) ��ȭ��:"+avata+"\n"+
                        "messageTo(MYROOMIN) �Ϸ�");
               messageAll(Function.POSCHANGE+"|"+id+"|"+pos);

                  }
                  break;
                  case Function.MYROOMIN: //�游���ÿ� ȣ��(���忡��), ���̺�Ŭ�� ����� ȣ��
                  {
                     /*
                      *   1. ���� ã��
                      *   2. ��ġ����
                      *   3. �����ο� ���� 
                      *   4. �� �ִ� ���
                      *      = ���� ����� ����
                      *      = ����޼��� 
                      *   5. ���� ���
                      *      = ����=>ä�ù����� ����
                      *      = �� �ִ� ������� ���� 
                      *   6. ����
                      */
                     int rNum=Integer.parseInt(st.nextToken());
                     for(Room room:roomVc)
                     {
                        if(rNum==room.roomNum)
                        {
                           room.current++;
                           pos=rNum+"����";
                           for(Client client:room.userVc)
                           {
                              client.messageTo(Function.ROOMCHAT
                                    +"|[�˸� ��"+name+"���� �����ϼ̽��ϴ�]| ");
                              client.messageTo(Function.ROOMIN+"|"
                                         +id+"|"+name+"|"
                                         +sex+"|"+avata+"|"
                                         +room.roomName+"|"
                                         +room.roomBang+"|"
                                        +win+"|"
                                        +lose+"|");
                                    
                           }
                           // ���� ��� ó��
                           messageTo(Function.MYROOMIN+"|"
                                     +id+"|"+name+"|"
                                     +sex+"|"+avata+"|"
                                     +room.roomName+"|"
                                     +room.roomBang+"|"
                                     +win+"|"
                                     +lose+"|"
                                     +room.roomNum);
                           room.userVc.addElement(this);
                           for(Client client:room.userVc)
                           {
                              if(!id.equals(client.id))
                              {
                                 messageTo(Function.ROOMIN+"|"
                                           +client.id+"|"
                                           +client.name+"|"
                                           +client.sex+"|"
                                           +client.avata+"|"
                                           +room.roomName+"|"
                                             +room.roomBang+"|"
                                             +client.win+"|"
                                             +client.lose+"|"
                                             );
                              }
                           }
                           messageAll(Function.WAITUPDATE+"|"
                                 +room.roomNum+"|"
                                 +room.current+"|"
                                 +room.inwon+"|"
                                 +id+"|"
                                 +pos);
                        }
                     }
                  }
                  break;
                    case Function.ROOMOUT:
                    {
                  int rNum=Integer.parseInt(st.nextToken());
                  System.out.println("���� ROOMOUT���̽����� ������ ��� �ִ� ���ȣ:"+rNum);
                  String myId=st.nextToken();
                  System.out.println("���� ROOMOUT���̽����� �����³� ���̵�:"+myId);
                  int roomIdx=0;
                  String str=" ";
                  pos="����";
                  for(int i=0;i<roomVc.size();i++)
                  {
                     if(roomVc.elementAt(i).roomNum==rNum)
                     {
                        roomVc.elementAt(i).current--;
                        roomIdx=i;
//                        roomVc.elementAt(i).readyOrNot[1]=false;
                     }
                  }
                  int newCurrent=roomVc.elementAt(roomIdx).current;
                  System.out.println("���� roomout ���̽� �����ο�:"+newCurrent);
                  if(newCurrent<1) //���� ����� ���
                  {   
                     messageAll(Function.WAITUPDATE+"|"
                              +rNum+"|"
                              +roomVc.elementAt(roomIdx).current+"|"//1
                              +roomVc.elementAt(roomIdx).inwon+"|"  //2
                              +myId+"|"       //�����³�
                              +pos);          //����
                     rnumVc.add(rNum);
                     roomVc.removeElementAt(roomIdx);
                  }
                  else //���� �����ϰ� �� ���
                  {
                     messageAll(Function.WAITUPDATE+"|"
                           +rNum+"|"
                           +roomVc.elementAt(roomIdx).current+"|"//1
                           +roomVc.elementAt(roomIdx).inwon+"|"  //2
                           +myId+"|"       //�����³�
                           +pos);          //����
                     
                     if(roomVc.elementAt(roomIdx).roomBang.equals(myId))//������ �������
                     {
                        //���� �迭��ȣ�� ������̵� ������̵�� ����
                        System.out.println("��������ID:"+roomVc.elementAt(roomIdx).userVc.elementAt(1).id);
                        roomVc.elementAt(roomIdx).roomBang=roomVc.elementAt(roomIdx).userVc.elementAt(1).id;
                        //���� �迭��ȣ�� �����ȭ���� �޾Ƶΰ�
                        str=roomVc.elementAt(roomIdx).userVc.elementAt(1).name;
                     }
                     else//������ �ƴ� ����� ���
                     {
                        str=name;
                     }
                     for(Client client:roomVc.elementAt(roomIdx).userVc) //������ ������ ���� �������� ������ ���� �ݺ��� ����
                     {
                        if(!myId.equals(client.id))//�����ִ� ����� ó��
                        {
                              client.messageTo(Function.BANGCHANGE //�溯�� �������� ������ ���� ���� ����鿡�� �޼��� ����
                                              +"|"+roomVc.elementAt(roomIdx).roomBang //�������� ����
                                              +"|"+str //�����ȭ�� �������� ����޼��� ó��
                                              +"|"+"2"); //���ο� ����
                              //���� �˸�
                                    client.messageTo(Function.ROOMCHAT
                                             +"|[�˸� ��"+name+"���� �����ϼ̽��ϴ�]| ");
                                    client.messageTo(Function.ROOMOUT+"|"+id+"|"+name+"|"+client.name+"|");
                        }   
                     }
                     roomVc.elementAt(roomIdx).roomBang=str;
                     roomVc.elementAt(roomIdx).current=newCurrent;
                     for(int i=0;i<roomVc.elementAt(roomIdx).userVc.size();i++)
                     {
                        if(roomVc.elementAt(roomIdx).userVc.elementAt(i).id.equals(myId))
                              roomVc.elementAt(roomIdx).userVc.removeElementAt(i);
                     }
                  }
                  
                  messageTo(Function.MYROOMOUT+"|"+"2");
                             
                   }
                    break;
                    case Function.GAMEYES:
                    {
                       int rn=Integer.parseInt(st.nextToken());
                       String bjId=" ";
                       for(Room room:roomVc)
                       {
                          if(room.roomNum==rn)
                          {   bjId=room.roomBang;
//                             room.readyOrNot[1]=true;
                             for(Client client:room.userVc)
                             {
                                client.messageTo((Function.GAMEYES+"|"
                                               +id+"|"
                                               +bjId+"|"));
                             }
                          }   
                       }
                    }
                    break;
                    case Function.GAMENO:
                    {
                       int rn=Integer.parseInt(st.nextToken());
                       String bjId=" ";
                       for(Room room:roomVc)
                       {
                          if(room.roomNum==rn)
                          {
                             bjId=room.roomBang;
//                             room.readyOrNot[1]=false;
                               for(Client client:room.userVc)
                             {
                                client.messageTo((Function.GAMENO+"|"
                                               +id+"|"
                                               +name+"|"
                                               +bjId+"|"));
                             }
                          }
                             
                       }
                    }
                    break;
                    case Function.GAMESTART:
                    {
                       int rNum=Integer.parseInt(st.nextToken());
                       System.out.println("���� GAMESTART ��޾Ҿ�:"+rNum);
                       for(Room room:roomVc)
                       {
                          if(room.roomNum==rNum)
                          {   System.out.println("���� GAMESTART ���ȣ��:Ŭ���̾�Ʈ���� �������ȣ"+rNum);
                             System.out.println("���� GAMESTART ���ȣ��:������ ����� ���ȣ"+room.roomNum);
                             for(Client client:room.userVc)
                             {
                                client.messageTo(Function.GAMESTART+"|");
                             }
                          }
                       }
                    }
                    break;
                    case Function.CHOICENATION:
                    {
                    	 int rn=Integer.parseInt(st.nextToken());
                    	 int cnation=Integer.parseInt(st.nextToken());
                         for(Room room:roomVc)
                         {
                            if(room.roomNum==rn)
                            {   
                            	for(Client client:room.userVc)
                            	{
                                  client.messageTo((Function.CHOICENATION+"|"
                                                 +id+"|"+cnation+"|"));
                            	}
                            }   
                         }
                    }
                    break;
             }
                
            }
          }catch(Exception ex){}
       }
       // ������� ���������� ���� 
       public synchronized void messageTo(String msg)
       {
          try
          {
             out.write((msg+"\n").getBytes());
          }catch(Exception ex)
          {
             for(int i=0;i<waitVc.size();i++)
             {
                Client client=waitVc.elementAt(i);
                if(id.equals(client.id))
                {
                   waitVc.removeElementAt(i);
                   break;
                }
             }
          }
       }
       // ��ü������ ���� 
       public synchronized void messageAll(String msg)
       {
          for(int i=0;i<waitVc.size();i++)
          {
             Client client=waitVc.elementAt(i);
             try
             {
                client.messageTo(msg);
             }catch(Exception ex)
             {
                waitVc.removeElementAt(i);
             }
          }
       }
    }
}


