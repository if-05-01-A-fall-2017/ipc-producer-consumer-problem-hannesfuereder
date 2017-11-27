/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumerproblem;
import static producerconsumerproblem.ProducerConsumerProblem.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hannes
 */
public class Consumer extends Thread{
       @Override
        public void run(){
        while (true) {
            System.out.println("Cons: awake");
            conSleeps = false;
            if (currentItems <= 0) {
                System.out.println("Cons:sleeps");
                conSleeps = true;
                if(prodSleeps = true){
                    dead();
                }
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            removeItem();
            if (currentItems >= maxAmount-1) {
                synchronized(prod){ prod.notify();}  
            }
        } 
    }
}
