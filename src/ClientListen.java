import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ClientListen extends Thread 
{

	public ClientListen()
	{
		Thread t = new Thread(this,"client server");
		t.start();
		t.setPriority(9);
		
	}
	public void run(){
		String[] s= new String[2];
		String a;
		int count=0,portno,socketCount; 
		Socket sr[]=new Socket[10];
	try {
		System.out.println("enter port no for server socket"+ this.getName());	
		
		portno = 1181;
	/*	Scanner sc = new Scanner(System.in);
		portno=sc.nextInt();
		*/
		
			ServerSocket ss = new ServerSocket(portno);	
			while(true)
			{
				System.out.println("entered no is :" + portno);
				
				sr[count] = ss.accept();
				a =sr[count].getRemoteSocketAddress().toString();
				DataInputStream in = new DataInputStream(sr[count].getInputStream());
				
				System.out.println("coonection aACCEPTED : " + sr[count].getRemoteSocketAddress());
				s=a.split(":");
				
			
				
				s=s[0].split("/");
				ClientRecieve cr = new ClientRecieve(ClientFront.recieveQueue,ClientFront.stack,s[1]);
				
				
				
				while(!ClientFront.checkSocketStatus()){
					
				}
				socketCount = ClientFront.getCount();
					ClientFront.so[socketCount] = sr[count];
					
			count++;
				ClientFront.resetSocketStatus(1);
				System.out.println("one iteration of client listen complete ******************************");
				
			}
			
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
		
	}
	
	
}
