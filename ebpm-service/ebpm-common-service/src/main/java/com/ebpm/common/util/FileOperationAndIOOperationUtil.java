package com.ebpm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

/**
 * IO及fileIO流操作，
 * @author xiaodi
 *
 */
public class FileOperationAndIOOperationUtil {
	/**
	 * 字节流写
	 * @throws IOException
	 */
	public static void writeByteToFile() throws IOException{
        String hello= new String( "hello word!11111");
         byte[] byteArray= hello.getBytes();
        File file= new File( "/test.txt");
         //因为是用字节流来写媒介，所以对应的是OutputStream 
         //又因为媒介对象是文件，所以用到子类是FileOutputStream
        OutputStream os= new FileOutputStream( file);
         os.write( byteArray);
         os.close();
  }
	/**
	 * 字节流读
	 * @throws IOException
	 */
	public static void readByteFromFile() throws IOException{
        File file= new File( "/test.txt");
         byte[] byteArray= new byte[( int) file.length()];
         //因为是用字节流来读媒介，所以对应的是InputStream
         //又因为媒介对象是文件，所以用到子类是FileInputStream
        InputStream is= new FileInputStream( file);
         int size= is.read( byteArray);
        System. out.println( "大小:"+size +";内容:" +new String(byteArray));
         is.close();
  }
	/**
	 * 字符流写
	 * @throws IOException
	 */
	public static void writeCharToFile() throws IOException{
        String hello= new String( "hello word!");
        File file= new File( "/test.txt");
         //因为是用字符流来读媒介，所以对应的是Writer，又因为媒介对象是文件，所以用到子类是FileWriter
        Writer os= new FileWriter( file);
         os.write( hello);
         os.close();
  }
	/**
	 * 字符流读
	 * @throws IOException
	 */
	public static void readCharFromFile() throws IOException{
        File file= new File( "/test.txt");
         //因为是用字符流来读媒介，所以对应的是Reader
         //又因为媒介对象是文件，所以用到子类是FileReader
        Reader reader= new FileReader( file);
         char [] byteArray= new char[( int) file.length()];
         int size= reader.read( byteArray);
        System. out.println( "大小:"+size +";内容:" +new String(byteArray));
         reader.close();
  }
	/**
	 * 字节流转换为字符流
	 * @throws IOException
	 */
	public static void convertByteToChar() throws IOException{
        File file= new File( "/test.txt");
         //获得一个字节流
        InputStream is= new FileInputStream( file);
         //把字节流转换为字符流，其实就是把字符流和字节流组合的结果。
        Reader reader= new InputStreamReader( is);
         char [] byteArray= new char[( int) file.length()];
         int size= reader.read( byteArray);
        System. out.println( "大小:"+size +";内容:" +new String(byteArray));
         is.close();
         reader.close();
  }
	/**
	 * 一些对文件的操作
	 * @throws IOException
	 */
	public static void fileOperations() throws IOException{
         //检查文件是否存在
        File file = new File( "/test.txt");
         boolean fileExists = file.exists();
        System. out.println( fileExists);
         //创建文件目录,若父目录不存在则返回false
        File file2 = new File( "/fatherDir/subDir");
         boolean dirCreated = file2.mkdir();
        System. out.println( dirCreated);
         //创建文件目录,若父目录不存则连同父目录一起创建
        File file3 = new File( "/fatherDir/subDir2");
         boolean dirCreated2 = file3.mkdirs();
        System. out.println( dirCreated2);
        File file4= new File( "/test.txt");
         //判断长度
         long length = file4.length();
         //重命名文件
         boolean isRenamed = file4.renameTo( new File("/test2.txt"));
         //删除文件
         boolean isDeleted = file4.delete();
        File file5= new File( "/fatherDir/subDir");
         //是否是目录
         boolean isDirectory = file5.isDirectory();
         //列出文件名
        String[] fileNames = file5.list();
         //列出目录
        File[]   files = file4.listFiles();
  }
	/**
	 * 随机读取文件
	 * @throws IOException
	 */
	public static void randomAccessFileRead() throws IOException {
        // 创建一个RandomAccessFile对象
       RandomAccessFile file = new RandomAccessFile( "/test.txt", "rw");
        // 通过seek方法来移动读写位置的指针
        file.seek(10);
        // 获取当前指针
        long pointerBegin = file.getFilePointer();
        // 从当前指针开始读
        byte[] contents = new byte[1024];
        file.read( contents);
        long pointerEnd = file.getFilePointer();
       System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" + new String(contents));
        file.close();
	}
	/**
	 * 随机写入文件
	 * @throws IOException
	 */
	public static void randomAccessFileWrite() throws IOException {
        // 创建一个RandomAccessFile对象
       RandomAccessFile file = new RandomAccessFile( "/test.txt", "rw");
        // 通过seek方法来移动读写位置的指针
        file.seek(10);
        // 获取当前指针
        long pointerBegin = file.getFilePointer();
        // 从当前指针位置开始写
        file.write( "HELLO WORD".getBytes());
        long pointerEnd = file.getFilePointer();
       System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" );
        file.close();
 }
	/**
	 * 3.6 Java IO：管道媒介
	 * 管道主要用来实现同一个虚拟机中的两个线程进行交流。因此，一个管道既可以作为数据源媒介也可作为目标媒介。
	 * 需要注意的是java中的管道和Unix/Linux中的管道含义并不一样，在Unix/Linux中管道可以作为两个位于不同空间进程通信的媒介，
	 * 而在java中，管道只能为同一个JVM进程中的不同线程进行通信。和管道相关的IO类为：PipedInputStream和PipedOutputStream，下面我们来看一个例子：
	 * 例9，读写管道
	 * @throws IOException
	 */
	public static void PipedInputStream() throws IOException {
		          final PipedOutputStream output = new PipedOutputStream();
		          final PipedInputStream  input  = new PipedInputStream(output);
		          Thread thread1 = new Thread( new Runnable() {
		              public void run() {
		                  try {
		                      output.write( "Hello world, pipe!".getBytes());
		                  } catch (IOException e) {
		                  }
		              }
		          });
		          Thread thread2 = new Thread( new Runnable() {
		              public void run() {
		                  try {
		                      int data = input.read();
		                      while( data != -1){
		                          System. out.print(( char) data);
		                          data = input.read();
		                      }
		                  } catch (IOException e) {
		                  } finally{
		                     try {
		                                       input.close();
		                                } catch (IOException e) {
		                                       e.printStackTrace();
		                                }
		                  }
		              }
		          });
		          thread1.start();
		          thread2.start();
		      }
	
	
	
	/**
	 * 用缓冲流读文件
	 * 3.8 Java IO：BufferedInputStream和BufferedOutputStream
	 * 
		BufferedInputStream顾名思义，就是在对流进行写入时提供一个buffer来提高IO效率。在进行磁盘或网络IO时，原始的InputStream对数据读取的过程都是一个字节一个字节操作的，而BufferedInputStream在其内部提供了一个buffer，在读数据时，会一次读取一大块数据到buffer中，这样比单字节的操作效率要高的多，特别是进程磁盘IO和对大量数据进行读写的时候。
	
		使用BufferedInputStream十分简单，只要把普通的输入流和BufferedInputStream组合到一起即可。我们把上面的例2改造成用BufferedInputStream进行读文件，请看下面例子：
		
		关于如何设置buffer的大小，我们应根据我们的硬件状况来确定。对于磁盘IO来说，如果硬盘每次读取4KB大小的文件块，那么我们最好设置成这个大小的整数倍。因为磁盘对于顺序读的效率是特别高的，所以如果buffer再设置的大写可能会带来更好的效率，比如设置成4*4KB或8*4KB。
	
		还需要注意一点的就是磁盘本身就会有缓存，在这种情况下，BufferedInputStream会一次读取磁盘缓存大小的数据，而不是分多次的去读。所以要想得到一个最优的buffer值，我们必须得知道磁盘每次读的块大小和其缓存大小，然后根据多次试验的结果来得到最佳的buffer大小。
	
		BufferedOutputStream的情况和BufferedInputStream一致，在这里就不多做描述了。
	 * @throws IOException
	 */
	  public static void readByBufferedInputStream() throws IOException {
	        File file = new File( "/test.txt");
	         byte[] byteArray = new byte[( int) file.length()];
	         //可以在构造参数中传入buffer大小
	        InputStream is = new BufferedInputStream( new FileInputStream(file),2*1024);
	         int size = is.read( byteArray);
	        System. out.println( "大小:" + size + ";内容:" + new String(byteArray));
	         is.close();
	  }
	
	
	  
	  /**
	   *
	   * 3.9 Java IO：BufferedReader和BufferedWriter
	   * BufferedReader、BufferedWriter 的作用基本和BufferedInputStream、BufferedOutputStream一致，
	   * 具体用法和原理都差不多 ，只不过一个是面向字符流一个是面向字节流。同样，我们将改造字符流中的例4，给其加上buffer功能，看例子：
	   * @throws IOException
	   */
	   public static void readByBufferedReader() throws IOException {
	          File file = new File( "/test.txt");
	           // 在字符流基础上用buffer流包装，也可以指定buffer的大小
	          Reader reader = new BufferedReader( new FileReader(file),2*1024);
	           char[] byteArray = new char[( int) file.length()];
	           int size = reader.read( byteArray);
	          System. out.println( "大小:" + size + ";内容:" + new String(byteArray));
	           reader.close();
	    }
	   
	public static void main(String[] args) {
		try {
			FileOperationAndIOOperationUtil.readByBufferedInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
