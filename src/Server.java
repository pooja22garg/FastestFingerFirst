import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Server extends Thread{
	 public ServerSocket serverSocket;
	 public Socket[] server= new Socket[10];
	 public BlockingQueue<String> MsgQueue = new ArrayBlockingQueue<String>(50);
	
	 int count=0;
	 public Server(Socket so[]){

		 this.server = so;
		 
	 }
	 public Server(){}
	public void run()
	{	
	int i=0;
		String addr = new String();
		String data =new String();
		
		try {
			serverSocket = new ServerSocket(1181);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
	      {
			
	         try
	         {
	            server[i] = serverSocket.accept();
	        			 System.out.println("lcoalposry"+server[i].getLocalPort() +"\t get port" +server[i].getPort());
	        			 
	            DataInputStream in =
	                  new DataInputStream(server[i].getInputStream());
	            addr = server[i].getRemoteSocketAddress().toString();
	            data = in.readUTF();
	            addr = addr +"," +data;
	            
	           
	           // addr.concat(data);
	            
	            try {
	            	
					MsgQueue.put(addr);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
					
	           
	           /* 
	            DataOutputStream out =
	                 new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to "
	              + server.getLocalSocketAddress() + "\nGoodbye!");
	            server.close();
	            */
	            System.out.println("ser");
	            i++;
	            ServerFront.count=i;
	         }catch(SocketTimeoutException s)
	         {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	            break;
	         }
	      }
	}
	

	public void handleSend(String[] mt,String addr,String[] msg)
	{


	}
	
}
