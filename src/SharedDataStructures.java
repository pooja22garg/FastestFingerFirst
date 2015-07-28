import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;


public class SharedDataStructures {

	private String sv = new String();
	private int[] sn= {0,0,0,0,0,0,0,0,0,0,0};
	private int procid;
	private boolean status=true;
	private int[] tsn={0,0,0,0,0,0,0,0,0,0,0};
	private String tsv ="TNNNNNNNNNN";
	
	public SharedDataStructures()
	{
				
	}
	public int[] gettsnValue(){return(tsn);}
	public String gettsvValue(){return(tsv);}
	public synchronized void settsvValue(String s){this.tsv =s; }
	public synchronized void settsnValue(int[] t){this.tsn =t; }
	public synchronized  boolean checkstatusforcs()
	{
		
		if(sv.charAt(procid)=='H' && status==true)
		{
			status=false;
			
		}
		return(!status);
	} 
	public synchronized  void changestatusforcs()
	{
		    status=true;
	}
	public  String getsvValue()
	{ 
		
		return(sv);
	}
	public  int[] getsnValue()
	{
		
		return(sn);
	}
	public int getprocid()
	{
		
		return(procid);
	}
	
	
	public synchronized void setsvValue(String s)
	{
		 sv=s;
		
	}
	public synchronized void setprocid(int t)
	{
		this.procid =t;
		
	}
	public synchronized void setsnValue(int[] t)
	{
		this.sn = t;
		
	}
	
	
	
}
