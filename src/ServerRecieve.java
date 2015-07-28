import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;


public class ServerRecieve extends Thread{

	private Socket[] so = new Socket[10];
	 private BlockingQueue MsgQueue;
	 Deque<Integer> stack =new ArrayDeque<Integer>();
	 Thread[] t =new Thread[10];
	public ServerRecieve(BlockingQueue MQ,Socket s[])
	{
		this.so = s;
		this.MsgQueue = MQ;
		int i=0;
		
		
		while(i<3)
		{
			stack.add(i);
			 t[i]= new Thread(this);
			 t[i].start();
			 i++;
		}
		
		
	}
	public void run(){
		
		
		String msg;
		int i;
		i=stack.pop();
		///System.out.println("value of i : " + i);
		while(true){	
		try{
			
			
		if(so[i]!=null){
			
		System.out.println(so[i].getPort());
			DataInputStream in = new DataInputStream(so[i].getInputStream());	
			while(true)
			{
				System.out.println("server listening...");
				msg = in.readUTF();
			// System.out.println("client received id "+  msg + "at port "+ ClientFront.so[i].getLocalPort());
			   MsgQueue.add(msg);
			   System.out.println("server side: msg recieved "+ msg);
			   
			 
			}
			

		}
	
	   }catch(IOException e)
		{
			e.printStackTrace();
		}
	
			
	}
	
 
	}
	
	public void destroy(){
		int i=0;
		while(i<3)
		{
			 t[i].destroy();
			 t[i].stop();
			 i++;
		}
	}
}