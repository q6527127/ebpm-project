package com.ebpm.common.util;
import java.util.Date;
import java.util.TimerTask;

public class TimerJavaUtil {
	//schedule(TimerTask task, long delay) 延迟 delay 毫秒 执行
//		public static void main(String[] args) {
//	        for (int i = 0; i < 10; ++i) {
//	            new java.util.Timer("timer - " + i).schedule(new TimerTask() {
//	                @Override
//	                public void run() {
//	                	System.out.println(Thread.currentThread().getName() + " run ");
//	                }
//	            }, 3000);
//	        }
//	    }
	//2. schedule(TimerTask task, Date time) 特定時間執行
//	public static void main(String[] args) {
//	        for (int i = 0; i < 10; ++i) {
//	            new java.util.Timer("timer - " + i).schedule(new TimerTask() {
//	                @Override
//	                public void run() {
//	                	System.out.println(Thread.currentThread().getName() + " run ");
//	                }
//	            }, new Date(System.currentTimeMillis() + 2000));
//	        }
//	    }
	
	//3. schedule(TimerTask task, long delay, long period) 延迟 delay 执行并每隔period 执行一次
//	public static void main(String[] args) {
//	        for (int i = 0; i < 10; ++i) {
//	            new java.util.Timer("timer - " + i).schedule(new TimerTask() {
//	                @Override
//	                public void run() {
//	                	System.out.println(Thread.currentThread().getName() + " run ");
//	                }
//	            }, 2000 , 3000);
//	        }
//	    }
	//默认实现为ScheduledThreadPoolExecutor 继承了ThreadPoolExecutor 的线程池特性，配合future特性，比Timer更强大。 具体用法可以阅读JDK文档；spring Task内部也是依靠它实现的。
//	public static void main(String[] args) throws SchedulerException {
//        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(10);
//        for (int i = 0; i < 10; ++i) {
//            executor.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " run ");
//                }
//            } , 2 , TimeUnit.SECONDS);
//        }
//        executor.shutdown();
//    }
}
