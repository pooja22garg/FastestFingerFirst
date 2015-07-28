import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MessageConsume extends Thread{

	
	 public BlockingQueue MsgQueue;
	 public MessageConsume(BlockingQueue MQ){
		 
		 this.MsgQueue = MQ;
	 }
		public void run()
		{	String c;
			try {
				c=EmptyQueue();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		public String EmptyQueue() throws InterruptedException{
			String c;
			//while((c=MsgQueue.take().toString())==null){}
			
				return MsgQueue.take().toString();
			}
		

}
