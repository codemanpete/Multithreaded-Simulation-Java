package program1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lydia McGovern
 */
public class Main {
    public static void main(String[] args) {

        MinHeap processHeap = new MinHeap(ProcessNode.class, 100);
	ReentrantLock lock = new ReentrantLock();
	Boolean flag = true;
        
        Consumer c1 = new Consumer(processHeap, "Consumer Thread 1", lock, flag);
        Consumer c2 = new Consumer(processHeap, "Consumer Thread 2", lock, flag);
        Thread consThread1 = new Thread(c1); 
        Thread consThread2 = new Thread(c2);
        
        Producer pt = new Producer(processHeap, lock);
        Thread prodThread1 = new Thread(pt);
        
        consThread1.start();
        consThread2.start();
        prodThread1.start();

        // Waits for producer thread to be active before proceeding in main.
        try {
            prodThread1.join();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        } finally {
	    flag = false;
	}
        
        // Stall main thread while heap has items to process 
        while (!processHeap.isEmpty()) {
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (Exception e) {
		System.out.println(e);
	    }
	}
        
        consThread1.interrupt();
        consThread2.interrupt();
    }   
}