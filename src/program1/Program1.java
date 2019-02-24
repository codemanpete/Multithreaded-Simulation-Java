/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program1;
import java.util.*;

/**
 *
 * @author petercheng
 */
public class Program1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PriorityQueue<Integer> processHeap = new PriorityQueue<Integer>();
        
        ConsumerThread ct1 = new ConsumerThread(processHeap, "Thread 1");
        ConsumerThread ct2 = new ConsumerThread(processHeap, "Thread 2");
        Thread consThread1 = new Thread(ct1); 
        Thread consThread2 = new Thread(ct2);
        
        ProducerThread pt = new ProducerThread(processHeap);
        Thread pt1 = new Thread(pt);
        
        consThread1.start();
        consThread2.start();
        pt1.start();

        // Waits for producer thread to be active before proceeding in main.
        try {
            pt1.join();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        
        // Stall main thread while heap has items to process 
        while (processHeap.size() > 0) {
            
        }
        
        consThread1.interrupt();
        consThread2.interrupt();
    }
    
}
