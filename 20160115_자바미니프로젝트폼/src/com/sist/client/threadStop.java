package com.sist.client;

public class threadStop implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.currentThread().isInterrupted())
				System.out.println("Thread is sleeping...");
				Thread.sleep(10000);
				System.out.println("Thread is live!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			System.out.println("Thread is dead!");
		}
	}

}
