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
//	boolean[] readyOrNot=new boolean[2];//�����غ񿩺�
	Vector<Server.Client> userVc=
			new Vector<Server.Client>();
	int[] numArr1=new int[75]; //�÷��̾�1  
	int[] numArr2=new int[75]; //�÷��̾�2
	public Room(int rNo, String rn, String rs, String rp)
	{
		roomNum=rNo;
		roomName=rn;
		roomState=rs;
		roomPwd=rp;
		current=1;
     
   		int su=0; //���� �߻��� ������ ����
   		boolean bDash=false; //�ߺ����� Ȯ��
   		for(int i=0; i<75; i++)
   		{
   			bDash=true;
   			while(bDash) // �����߻�, �ߺ� ����
   			{
   				su=(int)(Math.random()*75)+1;
   				bDash=false;
   				for(int j=0; j<i; j++)
   				{
   					if(numArr1[j]==su)
   					{
   						bDash=true; //�ߺ��� ������ ���߰� while���� �ٽ� ����(�������� �ٽ� ��)
   						break;
   					}
   				}
   			}
   			numArr1[i]=su;
            
   		}
   		for(int i=0; i<75; i++)
   		{
   			bDash=true;
   			while(bDash) // �����߻�, �ߺ� ����
   			{
   				su=(int)(Math.random()*75)+1; //�� ũ��+10��(��밡 ������ ���� �ְ�)�� ���� �Է�
   				bDash=false;
   	            for(int j=0; j<i; j++)
   	            {
   	            	if(numArr2[j]==su)
   	            	{
   	            		bDash=true; //�ߺ��� ������ ���߰� while���� �ٽ� ����(�������� �ٽ� ��)
   	            		break;
   	            	}
   	            }
   			}
   			numArr2[i]=su;
   			
   		}

	}
}
