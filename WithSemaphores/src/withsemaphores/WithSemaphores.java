/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package withsemaphores;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Hannes
 */
public class WithSemaphores {

    public static Semaphore semaphore = new Semaphore(1, true);
    public static final int maxAmount = 200;
    public static int currentItems = 1;
    public static Consumer con = new Consumer();
    public static Producer prod = new Producer();
    public static boolean prodSleeps = false;
    public static boolean conSleeps = false;
    
    public static void main(String[] args) {
        prod.start();
        con.start();
    }   
    
    public static void insertItem(){
        currentItems++;
    }
    public static void removeItem(){
        currentItems--;
    }
    public static synchronized  void check(boolean a, boolean b,String x){
      if(b && a)//THIS SHOULD NEVER BE TRUE!
      {
      System.out.println("DEAD LOCK " + x + " sleeped first");
      }
    }
}