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
            for(int i = 0; i < 4; ++i) {
                for (int j = 0; j < 10; ++j) {
                    Random priorityVal = new Random();
                    pq.add(priorityVal.nextInt(10));
                }
                Thread.sleep(5000);
            }
            System.exit(0);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
