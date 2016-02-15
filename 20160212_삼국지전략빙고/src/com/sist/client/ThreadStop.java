package com.sist.client; 
 
public class ThreadStop implements Runnable{ 
	@Override 
	public void run() { 
		// TODO Auto-generated method stub 
		try { 
			System.out.println("Thread is sleeping...");
			while(!Thread.currentThread().isInterrupted()) 
			Thread.sleep(10000); 
			System.out.println("Thread is live!");
		} catch (Exception e) { 
			// TODO: handle exception 
		}finally{ 
			System.out.println("Thread is dead!");
		} 
	} 
} 