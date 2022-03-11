package chat;

import java.io.IOException;    //
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;  //
import java.io.InputStream;
import java.io.ObjectInputStream;  //?
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//import client.client;

public class chat_client {

	public static class MessageReceptor extends Thread {    //其他类要加上static才能被main调用
		private Socket client;
		private Boolean flag=true;   //是否还要继续从serveur读取
		
		public MessageReceptor(Socket client) {
			this.client=client;   
			
		}
		
		@Override
		public void run() {
			try {
				
				int compteur=0,nbMax;
		        byte b[]=new byte[35];
				
				InputStream ins=client.getInputStream();
				//while(true) {
				if(compteur==0) {
		
			        try {
			             //Socket client = new Socket ("localhost", 10082);   //用端口号10081连接到本机   可能发生io异常？
			        	
			        	//System.out.print("1");
			             
			             
			             InputStream in=client.getInputStream();      ////获得输入流  将自己的要输出的信息放入线路  eg:电话话筒     将指向serveur输出流
			             
			             
			             while(true){
			            	 if(flag==false) break;
			            	 
			                 in.read(b);    //读取   没读到时辅助线程就卡住
			                 System.out.println("#: "+new String(b));   //类型转换成string 
			                 
			                 
			                 b=new byte[35];    //清空b
			             }
			             
			             
			            try {
			                Thread.sleep(20);   //线程挂起(阻塞)，暂停执行，让给其他线程
			            } catch (InterruptedException ex) {  //阻塞中的线程如果收到中断信号 (线程在睡眠状态被中断) 抛出异常
			                Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
			            }
			            
			             in.close();
			             //client.close();
			            
			         } catch (IOException ex) {   //输入输出流异常？
			             Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
			         }
			        
			}//while结束
		            
		         } catch (IOException ex) {   
		             Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, ex);
		         }

	}//run结束
		
}//MessageReceptor extends Thread结束
	
	
public static void main(String[] args) {
        
	 try {
             Socket client = new Socket ("localhost", 10080);   //用端口号10081连接到本机   可能发生io异常？
             
             MessageReceptor msgReceptor=new MessageReceptor(client);
             
             
             
             msgReceptor.start();  //辅助线程启动(读取)
             
             
             OutputStream out = client.getOutputStream();  
             
             //out.write("bonjour4".getBytes());    //字符串转化成字节数组bytes

             //System.out.println("Qu'est-ce que vous vouelez dire? ");
             System.out.println("Entrez votre pseudo: ");
             Scanner sc = new Scanner(System.in);   //扫描器 读取用户输入
             String str_test = sc.nextLine();       //读取用户输入-> nbMax
             
             out.write((str_test).getBytes());
             
             
             while(!str_test.equals("quit") ){
            	 

            	 ////System.out.println("Qu'est-ce que vous vouelez dire? ");
            	 str_test = sc.nextLine();
            	 
            	 out.write((str_test).getBytes());    //写入
            	 //System.out.println("Successfully wrote: "+str_test);
            	 
            	 //System.out.println(str_test.equals("quit"));
            	 if(str_test.equals("quit")){
            		 msgReceptor.flag=false;
            	 }
            	 
             }
             
             out.write("quit".getBytes());
             out.close();
             
             
             client.close();
             
             System.out.println("FIN");
             
             //System.out.print("1");
             
	 } catch (IOException ex) {   //输入输出流异常？
         Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
     }
             
	
    }

}
