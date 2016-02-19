package com.sist.server;
import java.util.*;
import java.io.*;
import java.net.*;
import com.sist.common.*;
public class Server implements Runnable{
    // ���� ���� 
   ServerSocket ss;
   // port
   final int PORT=33333;
   // ������ ������ ���� 
   Vector<Client> waitVc=new Vector<Client>();
   // �������� ���� 
   //
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
                // Ŭ���̾�Ʈ => ��û���� �޴´�
                String msg=in.readLine();
                StringTokenizer st=
                   new StringTokenizer(msg, "|");
                // 100|id|sex|name|avata
                int protocol=Integer.parseInt(st.nextToken());
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
                         messageTo(Function.MYLOG+"|"+id); //�޼����� �ٽ� ���ο��� �����ش�.
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
                   }
                   break;
                   case Function.WAITCHAT:
                   {
                      String data=st.nextToken();
                      messageAll(Function.WAITCHAT+"|["+name+"]"+data);
                   }
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



