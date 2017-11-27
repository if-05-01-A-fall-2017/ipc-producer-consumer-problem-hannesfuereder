/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package withsemaphores;

/**
 *
 * @author Hannes
 */

import static withsemaphores.WithSemaphores.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread{
       boolean h = false;
       @Override
        public void run(){
        while (true) {
            try {
                System.out.println("Cons:awake");
                semaphore.acquire();
                h = true;
                conSleeps = false;
                if (currentItems <= 0) {
                    System.out.println("Cons:sleeps");
                    conSleeps = true;
                    
                    check(conSleeps,prodSleeps, "Producer");
                    try {
                        h = false;
                        semaphore.release();
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(h){
                    semaphore.release();
                    h = false;
                }
                semaphore.acquire();
                removeItem();
                semaphore.release();
                semaphore.acquire();
                h = true;
                if (currentItems >= maxAmount-1) {
                    h = false;
                    semaphore.release();
                    synchronized(prod){ prod.notify();}
                }  
                if(h)
                {
                    h = false;
                    semaphore.release();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}