package com.ebpm.common.util;

public class ThreadUtil {
	boolean is =true;
	int num=0;
	public ThreadUtil(int num) {
		this.num= num;
	}
	public ThreadUtil() {
	}
	public void start()  {
		new Thread(new Runnable() {
			public void run() {
				try {
					listen();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	public void stop() {
		is=false;
	}
	
	public void listen() throws Exception{
		int count = 0;
		while(is) {
			Thread.sleep(3000);
			count+=1;
			System.out.println("线程:"+num+",第"+count+"执行");
		}
	}
}
