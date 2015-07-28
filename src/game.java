import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;


public class game extends Thread {
	private SharedDataStructures ds;
	private Queue q;
	private Queue reqQueue ;
	public static boolean flag=false;
public game(SharedDataStructures ds,Queue q,Queue rq){
	this.reqQueue = rq;
	this.ds = ds;
	this.q = q;
	Thread t= new Thread(this);
	t.start();
}	
	
	public void run()
	{
		
		int no,i=0,j1=0;
		boolean flag=true;
		int [] opt = new int[4];
		Random randomGenerator = new Random();
		while(i<4){
			
			no=randomGenerator.nextInt(5);
			j1=0;
			while(j1<opt.length){ if(opt[j1]==no){flag=false;break;}j1++;}
			if(flag && no!=0)
			{
				opt[i]=no;
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			flag=true;
		}
		ClientFront.updateOptions(opt[0]+","+opt[1]+","+opt[2]+","+opt[3]);
		int procid = ds.getprocid();
		System.out.println("inside game");
		System.out.println("for id "+ds.getprocid()+"  sv value is : "+ds.getsvValue());
		if(ds.getsvValue().charAt(procid)!='H')
		{	System.out.println("inside H wala if");
			char sv[] = new char[11];
			String c = ds.getsvValue();
			sv = c.toCharArray();  // sv value got
			
			int a[] = new int [10];
			int k=0,j=0;
			//System.out.println("inside initialise funciton ");
			while(k<11)
			{
				if(sv[k]=='R')
				{
					a[j]=k;
					j++;
					
				}
				k++;
			}
						String s2,port;
						boolean flag2=false;
						int p=0;
						int [] sn= new int[11];
						sn = ds.getsnValue();
						sn[procid]=sn[procid]+1;
						int f= 1181;
						ClientRecieve cr ;
						Scanner sc = new Scanner(System.in);
						while(k>0)
						{
							p=0;
							while(p<j){if(a[p]==k)flag2=true; p++;}
							if(flag2)
							{
								/*
								System.out.print("enter" );
								
								f = sc.nextInt();*/
								System.out.print("dfsfsf port"+f );
							
								System.out.print("addrss to which req is to be sent"+ClientFront.mh.addr[k] + "ggg" +f);
								//s2 =  procid + "'" + "co;" +"mm"+"}"+ClientFront.mh.addr[k]+":"+f;
								//q.MsgQueue.add(s2);
								
								s2 =  procid + "'" + "re;" + sn[procid]+"}"+ClientFront.mh.addr[k]+":"+f;
								ds.settsnValue(sn);
								System.out.println(s2);
								q.MsgQueue.add(s2);
								cr = new ClientRecieve(ClientFront.mr.q, ClientFront.stack,ClientFront.mh.addr[k]);
								
							}
							k--;
					}
					
			}
		
	while(!ds.checkstatusforcs())
			{}
/*	String s1,addr;
	String temp [] =new String[2];
	String temp1 [] =new String[2];*/
				ds.setsvValue(ds.getsvValue().replace('H', 'E'));
				{/*
					 try {
						BufferedReader br = new BufferedReader(new FileReader("IP"));
						try {
							while((s1= br.readLine())!=null){
								
								temp= s1.split("/");
								temp1 = temp[1].split(":");
								if(temp1[0].equals(ClientFront.serverIp)){
									addr = temp[1];
								}
							}
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					 
	String msg= procid + "'" + "cs;" + opt[0]+","+opt[1]+","+opt[2]+","+opt[3]+"}"+ClientFront.serverIp+":1181";
	System.out.println(msg);
					q.MsgQueue.add(msg);
					try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				ds.setsvValue(ds.getsvValue().replace('E', 'H'));
				
			csRelease();
			ds.changestatusforcs();
			/*
			String msg = new String();
			msg= "cs"+ "}" + "/"+ClientFront.serverIp+":"+ClientFront.serverPortNo;
			ClientFront.sendQueue.MsgQueue.add(msg);
			*/
			
		}	
		//System.out.println("here"+opt[0]+","+opt[1]+","+opt[2]);
	
	public void csRelease()
	{
		char [] sv = new char[11];
		char [] tsv = new char[11];
		int [] sn = new int[11];
		int [] tsn = new int[11];
		int myid,i=1;
		myid =ds.getprocid();
		System.out.println("CS release");
		
		
		
		sv= ds.getsvValue().toCharArray();	
		tsv= ds.gettsvValue().toCharArray();
		sn = ds.getsnValue();
		tsn = ds.gettsnValue();
		System.out.println("sn" +sn);
		System.out.println("tsn" +tsn);
		System.out.println("sv" +sv.toString());
		System.out.println("new tsv" +tsv.toString());
		sv[myid]='N';
		tsv[myid]='N';
		
		while(i<11)
		{
			if(sn[i]>tsn[i])
			{
				tsv[i] = sv[i];
				tsn[i]=sn[i];
				
			}else
			{
				sv[i] = tsv[i];
				sn[i]=tsn[i];
				
				
			}
			i++;
			
		}
		
		System.out.println("new sn" +sn);
		System.out.println("new tsn" +tsn);
		System.out.println("new sv" +sv.toString());
		System.out.println("new tsv" +tsv.toString());
		
		/*try {
			reqQueue.MsgQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	  }
	}

