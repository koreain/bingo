package com.sist.server;
import java.util.*;
import java.io.*;
import java.net.*;
import com.sist.common.*;
import com.sist.server.Server.Client;
public class Server implements Runnable{
    // 서버 가동 
   ServerSocket ss;
   // port
   final int PORT=3333;
   // 접속자 정보를 저장 
   Vector<Client> waitVc=new Vector<Client>();
   // 방정보를 저장 
   Vector<Room> roomVc=new Vector<Room>();
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
   					}
   					else
   					{
   						tempNum=roomVc.size()+1;
   					}
   					Room room=new Room(tempNum, //방번호
   										st.nextToken(),//방이름
   										st.nextToken(),//공개_비공개
   										st.nextToken() //방비번
   										);
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
   					roomVc.addElement(room);
   					room.userVc.addElement(this);
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
	   				case Function.MYROOMIN:
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
                    {/*

	   					int rNum=Integer.parseInt(st.nextToken());
	   					System.out.println("서버 ROOMOUT케이스문의 나가는 사람 있는 방번호:"+rNum);
	   					String myId=st.nextToken();
	   					System.out.println("서버 ROOMOUT케이스문의 나가는놈 아이디:"+myId);
	   					int i=0;
	   					int z=0;
	   					int removeUserVc=0;
	   					int removeRoomVc=0;
	   					for(Room room:roomVc)
	   					{		
	   						if(rNum==(room.roomNum))//인원이 나가는 방 처리
	   						{
	   							int roomSize=2;
	   							room.current--;
	   							pos="대기실";
	   							if(myId.equals(room.roomBang))//방장이 나갈경우
	   							{
	   								if(room.current!=0)//남아있는 사람이 있다면
	   								{	
	   									room.roomBang=room.userVc.elementAt(1).id; //다음 배열번호의 사람아이디를 방장아이디로 갱신
	   									String str=room.userVc.elementAt(1).name; //다음 배열번호의 사람대화명을 받아두고
	   									for(Client client:room.userVc) //방장이 나가는 방의 유저정보 업뎃을 위해 반복문 실행
	   									{
	   										if(!myId.equals(client.id))//방장이 아닌 남아있는 사람들 처리
												{
	   												client.messageTo(Function.BANGCHANGE //방변경 프로토콜 전송을 통해 남은 사람들에게 메세지 전송
			   															 +"|"+room.roomBang //방장정보 변경
			   															 +"|"+str //방장대화명 전송통해 퇴장메세지 처리
			   															 +"|"+roomSize); //방인원 전송
												}
	   									}
	   								}
	   								else //????남아있는 사람이 없는경우
	   								{
	   									//모든 각자의 wr.table1의 해당roomNum 정보 삭제 messageAll

	   									//서버의 roomVc에서 방정보 삭제 roomVc.
	   									//서버의 rnumVc에 삭제되는 방번호 추가 rnumVc.
	   									rnumVc.addElement(rNum);
	   								}
	   							}
	   							//방장이든 아니든 공통적용
	   							int y=1;
	   							for(Client client:room.userVc)//인원이 나가는 방은 어느누가 나가든 //추가해야할 부분!!!!!!!!!!!! 
	   							{
	   								System.out.println(y+"번째");
	   								client.messageTo(Function.ROOMCHAT
	   										+"|[알림 ☞"+name+"님이 퇴장하셨습니다]| "); //나간다는 메세지를 보낸 사람의 아이디를 넣어서 퇴장메세지를 보낸다
	   								System.out.println("퇴장알림");
	   								client.messageTo(Function.ROOMOUT+"|"+id+"|"+name+"|"+roomSize); //cr의 아바타창과 유저정보 삭제
	   								System.out.println("아바타,유저정보삭제");
   									y++;
	   							}
	   							// 들어가는 사람 처리
	   							int k=0;
	   							System.out.println("int k=0");
	   							for(Client client:room.userVc)//나가는 인원이 있는 방에 있는 모든 유저목록에 대하여?
	   							{
	   								System.out.println("????????????????");
	   								if(myId.equals(client.id))//나가고자 하는 사람의 아이디가 존재하면
	   								{
	   									
	   									messageTo(Function.MYROOMOUT+"|"+roomSize);//나가는 사람쓰레드에서 cr초기화 및 cr창 숨김처리

	   									removeUserVc=k;
	   									//room.userVc.removeElementAt(k);//나가는 사람 유저정보를 유저정보 벡터에서 지워줌

	   								}
	   								k++;
	   								System.out.println(k);
	   							}
	   							System.out.println("그럼너냐");
	   							System.out.println("현재인원"+room.current);
	   							if(room.current<1)//만약 인원이 나가서 방에 아무도 존재하지 않을 경우
	   							{	System.out.println("현재인원2:"+room.current);
	   								System.out.println("i값은?:"+i);
	   								removeRoomVc=i;
	   								//roomVc.removeElementAt(i); //방자체 정보에서 빼준다.
	   								System.out.println("roomVc.elementAt(i):"+roomVc.elementAt(i));
	   								System.out.println(i+"번째 삭제조건문 돈다");
	   								break; //나감
	   							}
	   							System.out.println("서버 ROOMOUT케이스문 나가는 인원 있는 방  if절 종료");
	   						}
	   						System.out.println("서버 ROOMOUT케이스문 i++라인 바로위");
	   						i++;
	   					}
	   					
							System.out.println("for문 밖 roomVc.elementAt(i):");
							

	   					for(int l=0;l<roomVc.size();l++)
	   					{
	   						if(roomVc.elementAt(l).roomNum==rNum)
	   							z=l;
	   					}
	   					
	   					roomVc.elementAt(z).userVc.removeElementAt(removeUserVc);//나가는 사람 유저정보를 유저정보 벡터에서 지워줌
	   					roomVc.removeElementAt(removeRoomVc);
	   					
	   					messageAll(Function.WAITUPDATE+"|"
									+rNum+"|"
								+roomVc.elementAt(z).current+"|"//1
								+roomVc.elementAt(z).inwon+"|"  //2
								+myId+"|"		 //나가는놈
								+pos);			 //대기실
*/				
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
								+myId+"|"		 //나가는놈
								+pos);			 //대기실
					rnumVc.add(rNum);
					roomVc.removeElementAt(roomIdx);
				}
				else //방이 존재하게 될 경우
				{
					messageAll(Function.WAITUPDATE+"|"
							+rNum+"|"
							+roomVc.elementAt(roomIdx).current+"|"//1
							+roomVc.elementAt(roomIdx).inwon+"|"  //2
							+myId+"|"		 //나가는놈
							+pos);			 //대기실
					if(roomVc.elementAt(roomIdx).roomBang.equals(myId))
					{
						roomVc.elementAt(roomIdx).roomBang=roomVc.elementAt(roomIdx).userVc.elementAt(1).id; //다음 배열번호의 사람아이디를 방장아이디로 갱신
						str=roomVc.elementAt(roomIdx).userVc.elementAt(1).name; //다음 배열번호의 사람대화명을 받아두고	
					}
					else
					{
						str=myId;
					}
					for(Client client:roomVc.elementAt(roomIdx).userVc) //방장이 나가는 방의 유저정보 업뎃을 위해 반복문 실행
					{
						if(!myId.equals(client.id))//방장이 아닌 남아있는 사람들 처리
						{
								client.messageTo(Function.BANGCHANGE //방변경 프로토콜 전송을 통해 남은 사람들에게 메세지 전송
													 +"|"+roomVc.elementAt(roomIdx).roomBang //방장정보 변경
													 +"|"+str //방장대화명 전송통해 퇴장메세지 처리
													 +"|"+"2"); //방인원 전송
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



