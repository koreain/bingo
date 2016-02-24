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
	int[] numArr1=new int[75]; //플레이어1  
	int[] numArr2=new int[75]; //플레이어2
	public Room(int rNo, String rn, String rs, String rp)
	{
		roomNum=rNo;
		roomName=rn;
		roomState=rs;
		roomPwd=rp;
		current=1;
     
   		int su=0; //난수 발생시 저장할 변수
   		boolean bDash=false; //중복여부 확인
   		for(int i=0; i<75; i++)
   		{
   			bDash=true;
   			while(bDash) // 난수발생, 중복 학인
   			{
   				su=(int)(Math.random()*75)+1;
   				bDash=false;
   				for(int j=0; j<i; j++)
   				{
   					if(numArr1[j]==su)
   					{
   						bDash=true; //중복이 있으면 멈추고 while문을 다시 수행(랜덤값을 다시 줌)
   						break;
   					}
   				}
   			}
   			numArr1[i]=su;
            
   		}
   		for(int i=0; i<75; i++)
   		{
   			bDash=true;
   			while(bDash) // 난수발생, 중복 학인
   			{
   				su=(int)(Math.random()*75)+1; //판 크기+10개(상대가 못맞출 수도 있게)의 난수 입력
   				bDash=false;
   	            for(int j=0; j<i; j++)
   	            {
   	            	if(numArr2[j]==su)
   	            	{
   	            		bDash=true; //중복이 있으면 멈추고 while문을 다시 수행(랜덤값을 다시 줌)
   	            		break;
   	            	}
   	            }
   			}
   			numArr2[i]=su;
   			
   		}

	}
}
