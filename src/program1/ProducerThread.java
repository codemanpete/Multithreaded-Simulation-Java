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
public class ProducerThread implements Runnable {
    private PriorityQueue<Integer> pq;
    public ProducerThread(PriorityQueue<Integer> pq) {
        this.pq = pq;
    }
    
    public void run(){
        try {
            // producer will spawn processes in 4 batches
            for(int i = 0; i < 4; ++i) {
                // producer will spawn 10 processes per batch
                for (int j = 0; j < 10; ++j) {
                    // TODO: implement new process node.
                    Random priorityVal = new Random();
                    pq.add(priorityVal.nextInt(10));
                }
                Thread.sleep(5000);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
