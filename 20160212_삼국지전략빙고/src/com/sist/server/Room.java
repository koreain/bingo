package com.sist.server;
import java.util.*;
public class Room {
	int roomNum; //���ȣ
	String roomName, //���̸�
		   roomState, //����/�����
		   roomPwd,  //���
		   roomBang; //����
	final int inwon=2; //��ü�ο� 2����
	int current; //�����ο�
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
