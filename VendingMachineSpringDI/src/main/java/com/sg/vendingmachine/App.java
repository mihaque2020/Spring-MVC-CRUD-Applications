/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Minul
 */
public class App {
    
    public static void main(String[] args) {
      
      ApplicationContext ctx = // type ApplicationContext
           new ClassPathXmlApplicationContext("applicationContext.xml"); // Java object that holds the application context we defined in xml (Implementation)
                                                                         // Pass name of our Spring application  
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class); // retrieve beans instantiated by Spring using getBean(id of beaan, type of bean)
        controller.run();
    }
}
