import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


public class ClientRecieve extends Thread{

	Queue q;
	
	 Deque<String> stack ;
	public ClientRecieve(Queue q, Deque<String> stack,String addr){
		Thread t= new Thread();
		 this.q=q;
	
		 this.stack = stack;
		 
		// while(i<4){
			 stack.add(addr);
			 
			
			 t= new Thread(this);
			
			
			 t.start();
			// i++;
		 //}
			
		// 
		 //}
	}
	public void run()
	{
		
		/*here address comparison doesnt include port no */
		String msg,a;
		String addr;
		String [] s = new String[2];
	boolean flag=false;
	int i=0,count=0;
	
		addr=stack.pop();
		
		
		while(!ClientFront.checkSocketStatus()){
			
		}
		count = ClientFront.getCount();
		System.out.println("***********COUNT VALUE In recieve  " + count + "addr to recieve from  ********" + addr);
		
		try{
			i=0;
			while(i<count)
				
			{
				System.out.println("^^^^^^^^^^^^^^i and count value ^^^ " + i + ".........."+ count + "   "+ ClientFront.so[i].getRemoteSocketAddress().toString());
				a =ClientFront.so[i].getRemoteSocketAddress().toString();
				s=a.split(":");
			
				if(s[0].toString().equals("/"+addr)==true)
				{
					System.out.println("flag executed");
					flag=true;
					break;
				}	
				i++;
				}
			ClientFront.resetSocketStatus(0);
		if(flag){
			
			System.out.println(ClientFront.so[i].getPort());
			DataInputStream in = new DataInputStream(ClientFront.so[i].getInputStream());	
			while(true)
			{
				msg = in.readUTF();
			// System.out.println("client received id "+  msg + "at port "+ ClientFront.so[i].getLocalPort());
			   q.MsgQueue.add(msg);
			   System.out.println("&&&&&&&&&&&& msg pushed " +msg + " &&&&&&&&&&& ");
			 
			}
			
		}
		
	
	   }catch(IOException e)
		{
			e.printStackTrace();
		}
	
			}
			}
	

	

