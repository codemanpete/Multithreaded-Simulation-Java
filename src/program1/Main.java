package program1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * main program to run three threads
 * @authors Lydia McGovern and Peter Cheng
 */
public class Main {
    public static void main(String[] args) {
        MinHeap processHeap = new MinHeap(ProcessNode.class, 100); // a heap for the processes
	ReentrantLock lock = new ReentrantLock(); // the lock will be shared between the threads
        
// Create two consumers and one producer objects
        Consumer c1 = new Consumer(processHeap, "Consumer Thread 1", lock);
        Consumer c2 = new Consumer(processHeap, "Consumer Thread 2", lock);
        Producer pt = new Producer(processHeap, lock);
// Place the consumers and producer in threads
        Thread consThread1 = new Thread(c1); 
        Thread consThread2 = new Thread(c2);
        Thread prodThread1 = new Thread(pt);
// Start the threads
        consThread1.start();
        consThread2.start();
        prodThread1.start();

// Waits for producer thread to finish before proceeding
        try {
            prodThread1.join();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        
// Stall main thread while heap has items to process to allow consumers to finish
        while (!processHeap.isEmpty()) {
	    try {
		TimeUnit.SECONDS.sleep(1);
	    } catch (Exception e) {
		System.out.println(e);
	    }
	}
// The heap is empty, so interrupt the consumers       
        consThread1.interrupt();
        consThread2.interrupt();
	System.out.println("Program 1 FINI");
    }   
}