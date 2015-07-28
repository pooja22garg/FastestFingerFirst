import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Queue {

	public BlockingQueue<String> MsgQueue ;
	
	public Queue(){;
		MsgQueue = new ArrayBlockingQueue<String>(50);
		
	}
	
}
