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
public class Producer extends Thread{
    @Override
    public void run(){
        while (true) {
            System.out.println("Prod: awake");
            prodSleeps = false;
            
            if (currentItems == maxAmount) {
                System.out.println("Prod: sleeps");
                prodSleeps = true;
                if(conSleeps = true){
                    dead();
                }
                try {
                    synchronized(this){ wait();}
                } catch (InterruptedException e) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            insertItem();
            if (currentItems > 0) {
               synchronized(con){ con.notify();}
            }
        }
    }
}
