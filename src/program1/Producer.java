package program1;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author petercheng
 */
public class Producer implements Runnable {
    private MinHeap queue;
    private ReentrantLock lock;
    final int SLEEP = 2000;
    
    public Producer(MinHeap<ProcessNode> queue, ReentrantLock lock) {
        this.queue = queue;
	this.lock = lock;
    }
    
    public void run(){
        try {
            for(int i = 0; i < 3; ++i) { // Spawn processes in 3 batches
		for (int j = 0; j < 5; ++j) {// Spawn 5 processes per batch
		    lock.lock();
		    ProcessNode node = new ProcessNode((int)(Math.random() * 10), (int)(Math.random() * SLEEP)); // a process of random priority and time slice
		    queue.add(node);
		    System.out.println("Producer Thread created process " + node.getId() + " with priority " + node.getPriority());
		    lock.unlock();
		}
		Thread.sleep(SLEEP);
            } 
        } catch (Exception e) {
            System.out.println(e);
        } finally {
	    if (lock.isLocked())
		lock.unlock();
	}
    }
}