package com.sist.server;
import java.util.*;
public class Room {
	String roomName, roomState, roomPwd, roomBang;
	final int inwon=2;;
	int current;
	Vector<Server.Client> userVc=new Vector<Server.Client>();//userVc�� 0���� ����� ����
	public Room(String rn, String rs, String rp)
	{
		roomName=rn;
		roomState=rs;
		roomPwd=rp;
		current=1;
	}
}
