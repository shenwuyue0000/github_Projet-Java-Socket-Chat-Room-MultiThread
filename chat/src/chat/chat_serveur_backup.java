/*
package chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import chat.chat_client.MessageReceptor;

public class chat_serveur {
	
	public static class DetecterClients extends Thread {    //其他类要加上static才能被main调用
		private Socket client;
		
		public DetecterClients(Socket client) {
			this.client=client;   
			
		}
		
		@Override
		public void run() {
			try {
				
				int compteur=0,nbMax;
		        byte b[]=new byte[20];
				
				InputStream ins=client.getInputStream();
				//while(true) {
				if(compteur==0) {
		
			        try {
			    		
			    		try {
			    			
			                ServerSocket conn = new ServerSocket(10081);    //使用10081端口建立连接  (创建socket对象)   这里可能发生io异常？
			              
			                while(compteur!=2){
			                    Socket comm=conn.accept();
			                    System.out.println("发现新用户");
			                    
			                }

			                OutputStream out = comm.getOutputStream();      //获得输出流  可以读对方放入此线路的信息 不能读自己的信息   eg:电话听筒   将指向client输入流
			                InputStream in = comm.getInputStream();     //获得输入流  将自己的要输出的信息放入线路  eg:电话话筒   将指向client输出流
			                
			                byte b[]=new byte[20];
			                String chaine;
			             
			                do{
			      
			                    in.read(b);            //读取client信息
			                    chaine=new String(b);     ////类型转换成string
			                    chaine.trim();        //去除所有空格，包括首尾、中间 
			                    System.out.println("reçu : "+chaine);
			                    
			                    System.out.println(chaine.equals("quit"));
			                    if(chaine.equalsIgnoreCase("quit")) break;
			                    	
			                    out.write(("suivant ?").getBytes());
			                    b=new byte[20];  //清空
			                    
			                }while(!chaine.startsWith("quit"));
			                
			                System.out.println("Fin");
			                in.close();
			                out.close();
			                comm.close();
			                
			            } catch (IOException ex) {   //输入输出流异常？
			                Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
			            }
			            }
			            
			              } catch (IOException ex) {   //输入输出流异常？
			             Logger.getLogger(chat_client.class.getName()).log(Level.SEVERE, null, ex);
			         }
			        
			}//if结束
		            
		         } catch (IOException ex) {   
		             Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, ex);
		         }

	}//run结束
		
}
	
	
	public static void main(String[] args) {
		
		
        

    } //main结束
	
}
*/

