import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ClientSend extends Thread {

	 BlockingQueue<String> MsgQueue;
	 Thread t;
	 String msg;
	public ClientSend(BlockingQueue<String> mq)
	{
		// addr is of the format: ipaddress:portno

		
		MsgQueue=new ArrayBlockingQueue<String>(50);
		this.MsgQueue = mq;
		 t = new Thread(this);
	
		t.start();
	}
	
	public void run()
	{
		int i=0;
		String[] temp = new String[2]; 
		String[] s=new String[2];
		String [] s1= new String[2];
		boolean flag=true;
		Socket senderSocket = null;
		int count ;
		String ipaddr,portno = null;
		
		//MessageRecieve mr= new MessageRecieve();
		
		/*** Message inside queue if of the form: (String) msg } ipaddress:portno **/
		while(true){
		try {
		
				msg = MsgQueue.take();
				flag=true;
				s= msg.split("}");
				if(s[1].contains(":"))
				{
					temp=s[1].split(":");
					ipaddr = temp[0];
					portno = temp[1];
					
				}else
				{
					
					ipaddr = s[1];
				}
				
				
				
					
				i=0;
		
					while(!ClientFront.checkSocketStatus()){
						
					}
					count =ClientFront.getCount();
					while(i<count){
						if(ClientFront.so[i].getRemoteSocketAddress().toString().equalsIgnoreCase("/"+ipaddr)==true)
					{
						
						flag = false;
						senderSocket = new Socket();
						senderSocket = ClientFront.so[i] ;
						break;
						
					}
					i++;
					
		}		
				if(flag)
				{
					
						InetAddress a = InetAddress.getByName(ipaddr);
						
						senderSocket = new Socket(a,Integer.parseInt(portno));
						//ClientFront.so[i] =new Socket();
						ClientFront.so[count] = senderSocket;
						//System.out.println("from client "+ClientFront.so[i].getRemoteSocketAddress()+"  "+ClientFront.so[i].getLocalPort() + "i " + i);
						count++;
					
						ClientFront.resetSocketStatus(1);						
				}else
					ClientFront.resetSocketStatus(0);
				System.out.println("^^^^");
				DataOutputStream dos = new DataOutputStream(senderSocket.getOutputStream());
				//System.out.println("from client send" + ClientFront.so[0].getRemoteSocketAddress());
				ClientFront.flag =true;
				dos.writeUTF(s[0]);
				System.out.println("^^^^MESSAGESENT BY CLENT SEND **********"+s[0] +"  "+senderSocket.getRemoteSocketAddress());
				
		//dos.close();
			
		
		} catch (InterruptedException e) {
			MsgQueue.add(msg);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
}
