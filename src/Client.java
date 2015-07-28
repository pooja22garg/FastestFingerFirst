import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends Thread{
	public void run(){
	   {
	     
	      
	      try
	      {
	    	 InetAddress i = InetAddress.getByName("127.0.0.1");
	         Socket client = new Socket(i, 1181);
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF("R");
	        DataInputStream in = new DataInputStream(client.getInputStream());
	         
	         
	          in.readUTF();
	         
	         System.out.println("client received id "+  in.readUTF() + "at port "+client.getLocalPort());

	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
  }
}