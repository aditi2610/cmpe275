package com.trySpring;

public class HelloWorld {
   private String message;
   int num;
   private String suffix;

  
   
   
   HelloWorld(String mes, int suf){
	   this.message = mes;
	   this.num = suf;
   }
   
   
   HelloWorld(String mes, String suf){
	   this.message = mes;
	   this.suffix = suf;
   }
//   
//   public void setMessage(String message){
//      this.message  = message;
//   }
   
   public void getNum() {
	   System.out.println("Number : "+ this.num);
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
   
   public void getSuffix() {
	   System.out.println("Suffix :" + this.suffix);
   }
   
   public void init() {
	   System.out.println("bean is initializing");
   }
   
   public void destroy() {
	   System.out.println("Destorying bean!!!");
   }
}