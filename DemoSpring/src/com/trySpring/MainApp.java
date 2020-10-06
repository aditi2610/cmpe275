package com.trySpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      //AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      
    /*
     * Greeter is the parent class of Impl Greeter class
     * context.getBean() would return the object of class ImplGreeter,
     *  which has been cast to Greeter class. 
     */
      Greeter greet = (Greeter)context.getBean("greeter");
      // getGreeting method of ImplGreeter class is calleds
      System.out.println(greet.getGreeting());
      //context.registerShutdownHook();

   }
   
   
}