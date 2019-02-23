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
public class ConsumerThread implements Runnable {
    
    private PriorityQueue<Integer> pq;
    private String name;
    
    public ConsumerThread(PriorityQueue<Integer> pq, String name) {
        this.pq = pq;
        this.name = name;
    }
    
    public void run() {
        try {
            while(true) {
                
                //waits for 1 second if pq is empty.
                //TODO: replace with .isEmpty() function
                if (pq.size() == 0) {
                    Thread.sleep(1000);
                } else {
                    System.out.println("name: " + this.name + " executed process with priority: " + pq.poll());
                
                    //TODO: implement sleep time from process node.
                    Thread.sleep(100);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
