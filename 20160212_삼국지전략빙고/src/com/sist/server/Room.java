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
//	boolean[] readyOrNot=new boolean[2];//게임준비여부
	Vector<Server.Client> userVc=
			new Vector<Server.Client>();
	public Room(int rNo, String rn, String rs, String rp)
	{
		roomNum=rNo;
		roomName=rn;
		roomState=rs;
		roomPwd=rp;
		current=1;
//		readyOrNot[0]=true;//방장의 준비상태는 항상 true
//		readyOrNot[1]=false;
	}
}
