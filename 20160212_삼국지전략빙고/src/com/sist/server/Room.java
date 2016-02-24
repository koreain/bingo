package com.sist.server;
import java.util.*;
public class Room {
   int roomNum; //방번호
   String roomName, //방이름
         roomState, //공개/비공개
         roomPwd,  //비번
         roomBang; //방장
   final int inwon=2; //전체인원 2고정
   int current; //현재인원
   boolean bChosenNation1; //나라 선택을 하면 순서 쓰레드가 돌아감
   boolean bChosenNation2;
   Vector<Server.Client> userVc=
         new Vector<Server.Client>();
   public Room(int rNo, String rn, String rs, String rp)
   {
      roomNum=rNo;
      roomName=rn;
      roomState=rs;
      roomPwd=rp;
      current=1;
   }
}