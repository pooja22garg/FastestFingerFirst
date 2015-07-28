import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.Scanner;
public class ClientMessageHandle extends Thread {

	Queue q;	
	
	SharedDataStructures ds;
	static public String[] addr  =new String[10];
	

	public  ClientMessageHandle(Queue q,SharedDataStructures ds){
		this.ds=ds;
		this.q = q;
		Thread t = new Thread(this);
		t.start();
		
		//System.out.println("thread ............."+"\n");
	}
	
	
	public void run()
	{
		String procid,msg = null,msgtype;
		String[] s = new String[3];
		System.out.println("inside message hadle ..................."+"\n");
		while(true){
		try {
			   msg=q.MsgQueue.take();
			   
		
       		} catch (InterruptedException e)
		      {
		     	// TODO Auto-generated catch block
			     e.printStackTrace();
		      }
		
		
				s=msg.split("'");
				procid= s[0];
				s= s[1].split(";");
				msg = s[1];
				
				msgtype= s[0];
				if(procid.equals("0"))
				{
					if(msgtype.equals("id"))
					{
						
						ds.setprocid(Integer.parseInt(msg));
						ClientFront.updateID(msg);
						
						
					}else if(msgtype.equals("in"))
					{
						ds.setsvValue(msg);
						
					}else if(msgtype.equals("fi"))
					{
					//System.out.println("inside mssg handle :  ...... ");
						
					 	String s1[] = new String[10];
						String temp [] = new String[2];
						int i=0,t;
						File f = new File("./IP");
						
						try {
							/*FileWriter writer = new FileWriter(f);
							System.out.println(msg);
							writer.write(msg);
							
							writer.close();
							*/
							String hh [] =new String[3];
							hh=msg.split(">");
							
							
							FileWriter fstream = new FileWriter("IP");
							  BufferedWriter out = new BufferedWriter(fstream);
							  while(i<3){
							  out.write(hh[i]);
							  out.newLine();
							  i++;
							  }
							  out.close();
							ClientListen cl = new ClientListen();
						i=0;
							s1 = msg.split(">");
							System.out.println("inside msg  handle");
							
							while(i<s1.length)
							{
								temp =s1[i].split(",");
								t = Integer.parseInt(temp[0]);
								temp = temp[1].split("/");
								temp=temp[1].split(":");
								addr[t] =temp[0];
								System.out.println("addr"+ addr[t]);
								i++;
							}
							
							//System.out.println("between cl and initialise.......");
							//ClientFront.initialiseSocket();
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else if(msgtype.equals("qu"))
					{
						
						
					}else if(msgtype.equals("to"))
					{
						String s1[] = new String[2];
						int i=0;
						int [] tv= new int[11];
						s1=msg.split(",");
						ds.settsvValue(s1[0]);
						
						while(i<s1[1].length())
						{
							tv[i]=s1[1].charAt(i);
							i++;
						}
						
						ds.settsnValue(tv);
						
					}
					else if(msgtype.equals("op"))
					{
						System.out.print("opt switch case");
						ClientFront.updateOptions(msg);
						
						game g = new game(ds,ClientFront.sendQueue,ClientFront.reqQueue);
					
						
					}
					
					}else{
						
						if(msgtype.equals("re"))
						{
							System.out.println("inside req if in client msg handle");
						char c[] = new char [11];
						ClientFront.updateRequestQueue(procid);
						while(!ds.checkstatusforcs()){}
						c= ds.getsvValue().toCharArray(); 
						int myid = ds.getprocid();
					if(c[myid]=='E' || c[myid]=='N')
					{
						
						System.out.println("my id is E or N");
						c[Integer.parseInt(procid)] = 'R';
						ds.setsvValue(c.toString());
						
						
						ClientFront.reqQueue.MsgQueue.add(procid);
						
						
					}else if(c[myid]=='R')
					{
						
						
						 if(c[Integer.parseInt(procid)]!='R')
						 {
							 System.out.println("m also req thats y m sending my req for cs to him");
							 String d;
							 int [] t = new int[11];
							 t = ds.getsnValue();
							 c[Integer.parseInt(procid)] = 'R';
							 ClientFront.reqQueue.MsgQueue.add(procid);
							 d=myid+ "'"+"re;" + t[myid]+"}"+addr[Integer.parseInt(procid)];
							 ClientFront.sendQueue.MsgQueue.add(d);
						 }
						
						
					}
					else if(c[Integer.parseInt(procid)]!='H')
						{
						System.out.println("m holding token dats y pasing it to iother");
							String d;
							int [] arr = new int[11];
							arr = ds.gettsnValue();
							String g[] =new String[2];
							g = addr[Integer.parseInt(procid)].split(":");
							msg = myid+"'" + "to;" +arr  + ","+ds.getsvValue()+"}"+g[0];
							
							System.out.println("((**********THIS ID WHAT U WNANR *******************)))insid token option"  +msg);
							ClientFront.sendQueue.MsgQueue.add(msg);
							d=ds.getsvValue().replace('H', 'N');
							ds.setsvValue(d);
							
							System.out.println("token msg pushed and token value"+ msg);
						}else
						{
							
							c[Integer.parseInt(procid)] = 'R';
							ds.setsvValue(c.toString());
							ClientFront.reqQueue.MsgQueue.add(procid);
							
						}
							
					
					}else
					{
						
						
					}
						ds.changestatusforcs();
				}					
					
			}
				
				
	}
}
