package program1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A consumer object that executes ProcessNode objects from a shared heap
 * @author petercheng
 */
public class Consumer implements Runnable {
    private MinHeap queue;
    private String name;
    private ReentrantLock lock;
    
/**
 * The constructor for the consumer
 * @param queue the shared queue/heap for ProcessNode objects
 * @param name the name of this consumer object
 * @param lock the shared lock for mutual exclusion
 */
    public Consumer(MinHeap queue, String name, ReentrantLock lock) {
        this.queue = queue;
        this.name = name;
	this.lock = lock;
    }
    
/**
 * run the consumer, simulating process execution by sleeping for the process's time slice
 */
    public void run() {
        try {
            while(true) { // this infinite loop will be interrupted
                if (queue.isEmpty()) { // wait a half second if the queue is empty
                    Thread.sleep(500);
                } else {
		    lock.lock(); // lock for mutual exclusion
		    ProcessNode node = (ProcessNode) queue.remove(); // remove the first process
                    System.out.println("\t" + this.name + " executed process " + node.getId() + " with priority " + node.getPriority());
		    lock.unlock();
                    Thread.sleep(node.getTimeSlice()); // sleep to simulate process execution
                }
            }
        } catch (Exception e) {
            System.out.println(e + " in " + name);
        } finally {
	    if (lock.isLocked()) // check if still locked
		lock.unlock();
	}
    }
}