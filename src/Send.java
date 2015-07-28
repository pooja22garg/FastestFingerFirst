import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Send implements Runnable{
	
	 private BlockingQueue<String> MsgQueue;
//	private Socket so;
	
	public Send(BlockingQueue<String> mq)
	{
	
		this.MsgQueue = mq;
		Thread t;	
		t =new Thread(this);
		
		
		
		t.start();
		
		/*
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public void run()
	{
		
		 int k=0;
			String[] message = new String [2];
			String finalmsg = new String();
			Socket so[] = new Socket[10];
			so =ServerFront.rec.server; 
			while(true){
		
			try {
				finalmsg =MsgQueue.take();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			message=finalmsg.split("}");
			
			
			k=0;
			while( so[k].getRemoteSocketAddress().toString().equalsIgnoreCase(message[0])==false){
				//System.out.println(server[j].getRemoteSocketAddress().toString());
				k++;}
			 
		 
			try {/*
				ia = InetAddress.getByName(s[0]);
				so = new Socket(ia,portno);
*/
				while(so[k].isConnected()==false){}
				DataOutputStream dos = new DataOutputStream(so[k].getOutputStream());
				//File f = new File("./src/IPList");
				
					/* message format is  1.procid/msgtype:msg */
					
					//System.out.println("ClIENT : "+so[k].getRemoteSocketAddress()+ " sending msg:  " + message[1]);	
					dos.writeUTF(message[1]);
					System.out.println("ClIENT : "+so[k].getRemoteSocketAddress()+ " sending msg:  " + message[1]);
			
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	}
}
