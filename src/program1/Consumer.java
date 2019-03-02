package program1;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author petercheng
 */
public class Consumer implements Runnable {
    private MinHeap queue;
    private String name;
    private ReentrantLock lock;
    Boolean flag;
    
    public Consumer(MinHeap queue, String name, ReentrantLock lock, Boolean flag) {
        this.queue = queue;
        this.name = name;
	this.lock = lock;
	this.flag = flag;
    }
    
    public void run() {
        try {
	//    lock.lock();
            while(flag || !queue.isEmpty()) { // I think this might be wrong
                
                //waits for 1 second if queue is empty.
                //TODO: replace with .isEmpty() function
                if (queue.isEmpty()) {
                    Thread.sleep(500);
                } else {
		    lock.lock();
		    ProcessNode node = (ProcessNode) queue.remove();

		    lock.unlock();
                    System.out.println("\t" + this.name + " executed process " + node.getId() + " with priority " + node.getPriority());		    
                    //TODO: implement sleep time from process node.
                    Thread.sleep(node.getTimeSlice());;
                }
            }
        } catch (Exception e) {
            System.out.println(e + " in Consumer " + name);
        } finally {
	    // TODO: this part CANNOT be omitted
	    // Clean up
	    if (lock.isLocked())
		lock.unlock();
	}
    }
}