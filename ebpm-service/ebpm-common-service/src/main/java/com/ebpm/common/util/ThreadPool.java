package com.ebpm.common.util;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author xiaodi
 *
 */
public class ThreadPool {
	public static void main(String[] args) {
		
		ThreadPool.newScheduledThreadPool();
	}
	/**
	 * newCachedThreadPool	
	 　创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
	 对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
	 调用 execute 将重用以前构造的线程（如果线程可用）。
	 如果现有线程没有可用的，则创建一个新线程并添加到池中。
	 终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
	 因此，长时间保持空闲的线程池不会使用任何资源。
	 自结：如果有线程闲置，直接使用，不新建线程，无闲置线程则新建线程
	 */
	private static void newCachedThreadPool() {
		 ExecutorService cacheThreadPool =Executors.newCachedThreadPool();
         for(int i =1;i<=5;i++){
             final int index=i ;
             try{
                Thread.sleep(0);
            }catch(InterruptedException  e ) {
                 e.printStackTrace();
            }
             cacheThreadPool.execute(new Runnable(){
                 public void run() {
                     System.out.println("第" +index +"个线程" +Thread.currentThread().getName());    

                	 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }    
            });
        }
	}
	/**
	 * newFixedThreadPool
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
		因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
		定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
		自结：线程池定长线程， 设定最大4线程同时跑，其他线程等待，跑完一个等待线程接上
	 */
	private  static void newFixedThreadPool() {
		ExecutorService  fixedThreadPool =Executors. newFixedThreadPool(4);
        for (int i =1; i<=10;i++){
            final int index=i ;
            fixedThreadPool.execute(new Runnable(){
                public void run() {
                    try {
                       System.out.println("第" +index + "个线程" +Thread.currentThread().getName());
//                       Random ran = new Random();
//               			int result = ran.nextInt(2);
//               		if (result==0) {
//                        Thread.sleep(5000);
//
//					}else {
//	                    Thread.sleep(1000);
//
//					}
                    Thread.sleep(1000);

                   }  catch(InterruptedException  e ) {
                        e .printStackTrace();
                   }
               }

           });
       }

	}
	/**
	 * newScheduledThreadPool
	 * 创建一个定长线程池，支持定时及周期性任务执行。
	 * 表示延迟3秒执行
	 */
	private static void newScheduledThreadPool() {
//		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//		scheduledThreadPool.schedule(new Runnable() {
//
//		    public void run() {
//		        System.out.println("delay 3 seconds");
//		    }
//		}, 3, TimeUnit.SECONDS);
		//表示延迟1秒后每3秒执行一次。
		//ScheduledExecutorService比Timer更安全，功能更强大，后面会有一篇单独进行对比
		  ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3); 
		  scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("延迟1秒后每三秒执行一次");				
			}
		},1,3,TimeUnit.SECONDS);
	}
	/**
	 * newSingleThreadExecutor
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 * 结果依次输出，相当于顺序执行各个任务。
现行大多数GUI程序都是单线程的。Android中单线程可用于数据库操作，文件操作，应用批量安装，应用批量删除等不适合并发但可能IO阻塞性及影响UI线程响应的操作
	 */
	private static void newSingleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    singleThreadExecutor.execute(new Runnable() {

		        public void run() {
		            try {
	                       System.out.println("第" +index + "个线程" +Thread.currentThread().getName());
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    });
		}
	}
}
