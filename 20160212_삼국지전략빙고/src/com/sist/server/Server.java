package com.sist.server;
import java.util.*;
import java.io.*;
import java.net.*;

import com.sist.client.ChoiceNation;
import com.sist.common.*;
import com.sist.server.Server.Client;
public class Server implements Runnable{
	ChoiceNation cn=new ChoiceNation();
    // 서버 가동 
   ServerSocket ss;
   // port
   final int PORT=33333;
   // 접속자 정보를 저장 
   Vector<Client> waitVc=new Vector<Client>();
   // 방정보를 저장 
   Vector<Room> roomVc=new Vector<Room>();
   // 종료된 방번호 정보를 저장 및 반환
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
       // 통신 
       public void run()
       {
          try
          {
             while(true)
             {
                int x=1;
                System.out.println(x+"번쨰 WHILE문");
                x++;
                // 클라이언트 => 요청값을 받는다
                String msg=in.readLine();
                StringTokenizer st=
                   new StringTokenizer(msg, "|");
                // 100|id|sex|name|avata
                int protocol=Integer.parseInt(st.nextToken());
                System.out.println(x+"번쨰 WHILE문 프로토콜:"+protocol);
                
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
                   win=Integer.parseInt(st.nextToken());
                   lose=Integer.parseInt(st.nextToken());
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
                      messageTo(Function.MYLOG+"|"
                              +id+"|"
                              +win+"|"
                              +lose+"|"
                              +avata); //메세지를 다시 본인에게 날려준다.
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
                      System.out.println("서버:"+color);
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
                  Room room=new Room(tempNum, //방번호
                                 st.nextToken(),//방이름
                                 st.nextToken(),//공개_비공개
                                 st.nextToken() //방비번
                                 );
                  System.out.println("test========3");
                  System.out.println("서버 MAKEROOM 케이스문 방번호:"+room.roomNum+"\n"+
                                 "서버 MAKEROOM 케이스문 방이름:"+room.roomName+"\n"+
                                 "서버 MAKEROOM 케이스문 방상태:"+room.roomState+"\n"+
                                 "서버 MAKEROOM 케이스문 인원:"+room.current+"/"+room.inwon);
                  messageAll(Function.MAKEROOM+"|"
                           +room.roomNum+"|"
                           +room.roomName+"|"
                           +room.roomState+"|"
                           +room.current+"/"+room.inwon);
                  room.roomBang=id;
                  pos=room.roomNum+"번방";
                  System.out.println("test1111");
                  roomVc.addElement(room);
                  System.out.println("test2222");
                  room.userVc.addElement(this);
                  System.out.println("test3333");
                  System.out.println("서버 MAKEROOM 케이스문 방장아이디:"+room.roomBang+"\n"+
                        "서버 MAKEROOM 케이스문 방번호:"+pos+"\n"+
                           "방정보(유저정보포함) 추가 완료");
               messageTo(Function.MYROOMIN+"|"
                          +id+"|"+name+"|"
                          +sex+"|"+avata+"|"
                          +room.roomName+"|"
                          +room.roomBang+"|"
                          +win+"|"
                          +lose+"|"
                          +room.roomNum);
                  System.out.println("서버 MAKEROOM 케이스문 message to(MYROOMIN) 방장아이디:"+id+"\n"+
                        "서버 MAKEROOM 케이스문 message to(MYROOMIN) 대화명:"+name+"\n"+
                        "서버 MAKEROOM 케이스문 message to(MYROOMIN) 대화명:"+sex+"\n"+
                        "서버 MAKEROOM 케이스문 message to(MYROOMIN) 대화명:"+avata+"\n"+
                        "messageTo(MYROOMIN) 완료");
               messageAll(Function.POSCHANGE+"|"+id+"|"+pos);

                  }
                  break;
                  case Function.MYROOMIN: //방만들기시에 호출(방장에게), 테이블클릭 입장시 호출
                  {
                     /*
                      *   1. 방을 찾기
                      *   2. 위치변경
                      *   3. 현재인원 증가 
                      *   4. 들어가 있는 사람
                      *      = 들어가는 사람의 정보
                      *      = 입장메세지 
                      *   5. 들어가는 사람
                      *      = 대기실=>채팅방으로 변경
                      *      = 들어가 있는 사람들의 정보 
                      *   6. 대기실
                      */
                     int rNum=Integer.parseInt(st.nextToken());
                     for(Room room:roomVc)
                     {
                        if(rNum==room.roomNum)
                        {
                           room.current++;
                           pos=rNum+"번방";
                           for(Client client:room.userVc)
                           {
                              client.messageTo(Function.ROOMCHAT
                                    +"|[알림 ☞"+name+"님이 입장하셨습니다]| ");
                              client.messageTo(Function.ROOMIN+"|"
                                         +id+"|"+name+"|"
                                         +sex+"|"+avata+"|"
                                         +room.roomName+"|"
                                         +room.roomBang+"|"
                                        +win+"|"
                                        +lose+"|");
                                    
                           }
                           // 들어가는 사람 처리
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
                  System.out.println("서버 ROOMOUT케이스문의 나가는 사람 있는 방번호:"+rNum);
                  String myId=st.nextToken();
                  System.out.println("서버 ROOMOUT케이스문의 나가는놈 아이디:"+myId);
                  int roomIdx=0;
                  String str=" ";
                  pos="대기실";
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
                  System.out.println("서버 roomout 케이스 현재인원:"+newCurrent);
                  if(newCurrent<1) //방이 사라질 경우
                  {   
                     messageAll(Function.WAITUPDATE+"|"
                              +rNum+"|"
                              +roomVc.elementAt(roomIdx).current+"|"//1
                              +roomVc.elementAt(roomIdx).inwon+"|"  //2
                              +myId+"|"       //나가는놈
                              +pos);          //대기실
                     rnumVc.add(rNum);
                     roomVc.removeElementAt(roomIdx);
                  }
                  else //방이 존재하게 될 경우
                  {
                     messageAll(Function.WAITUPDATE+"|"
                           +rNum+"|"
                           +roomVc.elementAt(roomIdx).current+"|"//1
                           +roomVc.elementAt(roomIdx).inwon+"|"  //2
                           +myId+"|"       //나가는놈
                           +pos);          //대기실
                     
                     if(roomVc.elementAt(roomIdx).roomBang.equals(myId))//방장이 나갈경우
                     {
                        //다음 배열번호의 사람아이디를 방장아이디로 갱신
                        System.out.println("다음방장ID:"+roomVc.elementAt(roomIdx).userVc.elementAt(1).id);
                        roomVc.elementAt(roomIdx).roomBang=roomVc.elementAt(roomIdx).userVc.elementAt(1).id;
                        //다음 배열번호의 사람대화명을 받아두고
                        str=roomVc.elementAt(roomIdx).userVc.elementAt(1).name;
                     }
                     else//방장이 아닌 사람인 경우
                     {
                        str=name;
                     }
                     for(Client client:roomVc.elementAt(roomIdx).userVc) //방장이 나가는 방의 유저정보 업뎃을 위해 반복문 실행
                     {
                        if(!myId.equals(client.id))//남아있는 사람들 처리
                        {
                              client.messageTo(Function.BANGCHANGE //방변경 프로토콜 전송을 통해 남은 사람들에게 메세지 전송
                                              +"|"+roomVc.elementAt(roomIdx).roomBang //방장정보 변경
                                              +"|"+str //방장대화명 전송통해 퇴장메세지 처리
                                              +"|"+"2"); //방인원 전송
                              //퇴장 알림
                                    client.messageTo(Function.ROOMCHAT
                                             +"|[알림 ☞"+name+"님이 퇴장하셨습니다]| ");
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
                       System.out.println("서버 GAMESTART 방받았어:"+rNum);
                       for(Room room:roomVc)
                       {
                          if(room.roomNum==rNum)
                          {   System.out.println("서버 GAMESTART 방번호비교:클라이언트에서 받은방번호"+rNum);
                             System.out.println("서버 GAMESTART 방번호비교:서버에 저장된 방번호"+room.roomNum);
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


