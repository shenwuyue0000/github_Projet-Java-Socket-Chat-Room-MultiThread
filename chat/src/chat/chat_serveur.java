package chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import java.net.*;


public class chat_serveur {
	
	public static ArrayList<Socket> socketList=new ArrayList<Socket>();  
	public static ArrayList<String> noms = new ArrayList<>();
	//public static int x=0;
	
	
	public static class ThreadedEchoHandler implements Runnable {
		private Socket socket=null;
		public  int premier=0;
		public  int x=0;
		
		public ThreadedEchoHandler(Socket s){
			this.socket=s;
		}
		
		@Override
		public void run(){
			InputStream in;
			try {
				in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				//Scanner scanner = new Scanner(inputStream);
				//PrintWriter printWriter = new PrintWriter(outputStream, true);
				//printWriter.println("welcome to testServer");      //打印输出流
				out.write(("Entrez votre pseudo: ").getBytes());
				
				//int i=0;
				
				  byte b[]=new byte[35];
		            String chaine;
		         
		            do{
		            	
		                in.read(b);            //读取client信息
		                chaine=new String(b);     ////类型转换成string
		                chaine.trim();        //去除所有空格，包括首尾、中间       （这样写没用    chaine本身没改变
		                //System.out.println("reçu : "+chaine);
		                

		                
		                //if(x==0||x==1){
		                if(this.x==0){
		            		//noms.set(i, chaine);
		                	noms.add(chaine.trim());
		            		this.x++;
		            		System.out.println("noms: "+noms);
		            	}
		                
		                //for(Socket socket:socketList){ //遍历socket集合 
		                for(int i = 0 ;i<socketList.size();i++) {  
		                	Socket socket= socketList.get(i);
		                	

                            //把读取到的消息发送给各个客户端
		                	//if(socket!=this.socket){   //不发给自己
	                            if(!socket.isClosed()){
	                                //out = new DataOutputStream(socket.getOutputStream());
	                                //out.write(noms.get(i-1).getBytes()+"a dit: "+chaine.getBytes());
	                            	out=socket.getOutputStream();
	                            	
	                            	System.out.println(this);  /////
	                            	System.out.println(this.premier);     /////
	                            	
	                            	if(this.premier==0 ){  //   || premier==1       两个管道里都输出该新用户加入信息     仅此一次
	    			                	//System.out.println(chaine.trim()+" a rejoint la conversation");
	    			                	out.write( (chaine.trim()+" a rejoint la conversation").getBytes()) ;
	    			                	out.write( "-----------------------------------".getBytes()) ;
	    			                	
	    			                	System.out.println(chaine.trim()+" a rejoint la conversation");
	    			                	System.out.println("-----------------------------------");
	    			                	
	    			                	socket= socketList.get(i+1);
	    			                	out=socket.getOutputStream();
	    			                	
	    			                	out.write( (chaine.trim()+" a rejoint la conversation").getBytes()) ;
	    			                	out.write( "-----------------------------------".getBytes()) ;
	    			                	
	    			                	System.out.println(chaine.trim()+" a rejoint la conversation");
	    			                	System.out.println("-----------------------------------");
	    			                	
	    			                	
	    			                	this.premier++;
	    			                	break;
	    			                }
	                            	
	                            	
	                            	out.write((noms.get(noms.size()-1).trim()+" a dit: "+chaine.trim()).getBytes());
	                                //out.write("a dit: ".getBytes()+chaine.getBytes());
	                                
	                                //bo.encryptWrite(send,output);
	                            }
		                	//}    
                        //}
		                }	
		                
		                //System.out.println(chaine.equals("quit"));
		                if(chaine.equalsIgnoreCase("quit")) break;
		                	
		                //out.write(("suivant ?").getBytes());
		                b=new byte[35];  //清空
		                
		            }while(!chaine.startsWith("quit"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
		}
		
	}
	
	
	public static void main(String[] args) {
		//private List<ThreadServer> clients=new ArrayList<ThreadServer>();
		
		/*
		try{
			ServerSocket sc=new ServerSocket(10030);  //建立socket
			Socket soc=sc.accept();   //等待client连接
		}catch(Exception e) {

		}
	}
	*/
		try {
			
			ServerSocket serverSocket = new ServerSocket(10080);
			while (true) {
				Socket socket = serverSocket.accept();
				socketList.add(socket); //把sock对象加入sock集合
				System.out.println(socketList);
				
				ThreadedEchoHandler handler = new ThreadedEchoHandler(socket);
				Thread thread = new Thread(handler);  //
				
				thread.start();   //负责分别处理2个client
			}
            
        } catch (IOException ex) {   //输入输出流异常？
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
