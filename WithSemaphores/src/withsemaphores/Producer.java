/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package withsemaphores;

import java.util.logging.Level;
import java.util.logging.Logger;
import static withsemaphores.WithSemaphores.*;

/**
 *
 * @author Hannes
 */
public class Producer extends Thread{
    boolean h = false;
    @Override
    public void run(){
        while (true) {
            try {
                semaphore.acquire();
                h = true;
                System.out.println("Prod:awake");
                prodSleeps = false;
                if (currentItems == maxAmount) {
                    System.out.println("Prod:sleeps");
                    prodSleeps = true;
                    check(conSleeps, prodSleeps, "Consumer");
                    try {
                        h = false;
                        semaphore.release();
                        synchronized(this){ wait();}
                    } catch (InterruptedException e) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if(h == true)
                {
                    semaphore.release();
                    h = false;
                }
                semaphore.acquire();
                insertItem();
                semaphore.release();
                
                semaphore.acquire();
                h = true;
                
                if (currentItems > 0) {
                    semaphore.release();
                    h = false;
                    synchronized(con){ con.notify();}
                }
                if(h){
                    semaphore.release();
                    h = false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
