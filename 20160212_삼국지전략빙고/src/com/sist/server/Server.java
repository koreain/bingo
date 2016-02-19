package com.sist.server;
import java.util.*;
import java.io.*;
import java.net.*;
import com.sist.common.*;
public class Server implements Runnable{
    // 서버 가동 
   ServerSocket ss;
   // port
   final int PORT=33333;
   // 접속자 정보를 저장 
   Vector<Client> waitVc=new Vector<Client>();
   // 방정보를 저장 
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
   // 접속시 ==> 쓰레드 생성 (통신연결)
   public void run()
   {
      try
      {
         while(true)
         {
            Socket s=ss.accept();
            // s=> client의 정보(ip,port)
            // Thread로 전송 => 통신 
            Client client=new Client(s);
            client.start();
            // 통신시작 
         }
      }catch(Exception ex){}
   }
   public static void main(String[] args) {
      // TODO Auto-generated method stub
        Server server=new Server();
        new Thread(server).start();
   }
   // 통신 => Server의 모든내용을 공유 : 내부클래스 
    class Client extends Thread
    {
       String id,name,sex,avata,pos;
       // 통신 
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
       // 통신 
       public void run()
       {
          try
          {
             while(true)
             {
                // 클라이언트 => 요청값을 받는다
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
                      // 정보를 저장한다
                      id=st.nextToken();
                      name=st.nextToken();
                      sex=st.nextToken();
                      avata=st.nextToken();
                      pos="대기실";
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
                         // 대기실에 있는 사람에게 정보 전송
                         messageAll(Function.LOGIN+"|"+id+"|"
                               +name+"|"+sex+"|"+pos);//기존에 로그인해있던 유저들에게 접속자 정보를 보낸다
                         // 저장
                         waitVc.addElement(this); //접속한 1인 정보를 유저들정보 waitVc벡터에 저장 후,
                         messageTo(Function.MYLOG+"|"+id); //메세지를 다시 본인에게 날려준다.
                         for(Client client:waitVc) 
                         {
                            messageTo(Function.LOGIN+"|"
                                     +client.id+"|"
                                  +client.name+"|"
                                     +client.sex+"|"
                                  +client.pos);
                         }
                      }
                      // 개설된 방 정보
                   }
                   break;
                   case Function.WAITCHAT:
                   {
                      String data=st.nextToken();
                      messageAll(Function.WAITCHAT+"|["+name+"]"+data);
                   }
                   case Function.EXIT:
                   {
                      messageAll(Function.EXIT+"|"+id);//남아있는 사람 처리
                      messageTo(Function.MYEXIT+"|");//나가는 사람 처리
                       for(int i=0;i<waitVc.size();i++)
                       {
                          Client client=waitVc.elementAt(i);
                          if(id.equals(client.id))
                          {
                             waitVc.removeElementAt(i);
                             in.close(); //통신닫기 중요!!!
                             out.close();//통신닫기 중요!!!
                          }
                       }
                   }
                   break;
                }
                
             }
          }catch(Exception ex){}
       }
       // 결과값을 개인적으로 전송 
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
       // 전체적으로 전송 
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



