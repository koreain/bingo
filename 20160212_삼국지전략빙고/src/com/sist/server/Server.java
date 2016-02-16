package com.sist.server;
import java.util.*;
import java.net.*;
import java.io.*;
public class Server implements Runnable{
    ServerSocket ss;
    Vector<Client> waitVc=
    		new Vector<Client>();
    // Vector ==> <> 제네릭 
    public Server()
    {
    	try
    	{
    		ss=new ServerSocket(33333);
    		// bind (전화기 => 개통 (전화기,전화번호(ip),전화선(port))
    		// listen (대기)
    		// port => 0~65535 (0~1023) 1521,4000,8080
    	    System.out.println("Server Start...");
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
	public void run()
	{
		try
		{
		   while(true)
		   {
			   Socket s=ss.accept();
			   //System.out.println("Client IP:"+s.getInetAddress().getHostAddress());
			   //System.out.println("Client Port:"+s.getPort());
		       Client client=new Client(s);
		       client.start();
		   }
		}catch(Exception ex){}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Server server=new Server();
        new Thread(server).start();//run()
	}
    class Client extends Thread
    {
    	Socket s;
    	BufferedReader in;
    	OutputStream out;
    	String id,name,sex,pos;
    	public Client(Socket s)
    	{
    		try
    		{
    			this.s=s;
    			in=new BufferedReader(
    				new InputStreamReader(s.getInputStream()));
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
    				String msg=in.readLine();
    				StringTokenizer st=
    					new StringTokenizer(msg, "|");
    				id=st.nextToken();
    				name=st.nextToken();
    				sex=st.nextToken();
    				pos="대기실";
    				for(Client user:waitVc)
    				{
    					user.out.write((id+"|"+name+"|"+sex+"|"+pos+"\n").getBytes());
    				}
    				waitVc.addElement(this);
    				for(Client user:waitVc)
    				{
    					out.write((user.id+"|"+user.name+"|"
    				        +user.sex+"|"+user.pos+"\n").getBytes());
    				}
    			}
    		}catch(Exception ex){}
    	}
    }
}




