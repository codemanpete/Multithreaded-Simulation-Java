package program1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A producer object that creates ProcessNode objects and places them in a shared heap
 * @author petercheng
 */
public class Producer implements Runnable {
    private MinHeap queue;
    private ReentrantLock lock;
    final int SLEEP = 2000;
    
/**
 * The constructor for the producer
 * @param queue the shared queue/heap for ProcessNode objects
 * @param lock the shared lock for mutual exclusion
 */
    public Producer(MinHeap<ProcessNode> queue, ReentrantLock lock) {
        this.queue = queue;
	this.lock = lock;
    }
    
/**
 * Run three times, creating five processes in each batch (15 processes total)
 */
    public void run(){
        try {
            for(int i = 0; i < 3; ++i) { // Spawn processes in 3 batches
		for (int j = 0; j < 5; ++j) {// Spawn 5 processes per batch
		    lock.lock(); // lock to guarantee mutual exclusion
		    ProcessNode node = new ProcessNode((int)(Math.random() * 10), (int)(Math.random() * SLEEP)); // a process of random priority and time slice
		    queue.add(node);
		    System.out.println("Producer Thread created process " + node.getId() + " with priority " + node.getPriority());
		    lock.unlock();
		}
		Thread.sleep(SLEEP); // Sleep to allow other threads to work (i.e. consumers can execute the new processes)
            } 
        } catch (Exception e) {
            System.out.println(e);
        } finally {
	    if (lock.isLocked()) // Check if still locked
		lock.unlock();
	}
    }
}