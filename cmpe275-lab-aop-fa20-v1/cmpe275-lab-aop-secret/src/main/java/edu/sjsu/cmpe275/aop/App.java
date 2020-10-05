package edu.sjsu.cmpe275.aop;

import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		/***
		 * Following is a dummy implementation of App to demonstrate bean creation with
		 * Application context. You may make changes to suit your need, but this file is
		 * NOT part of your submission.
		 */

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		SecretService secretService = (SecretService) ctx.getBean("secretService");
		SecretStats stats = (SecretStats) ctx.getBean("secretStats");

		try {
			StringBuilder st = new StringBuilder();
			for (int i = 0; i < 102; i++) {
				st.append("a");
			}

//			UUID secret = secretService.createSecret("A", "my little secret");
//        	secretService.shareSecret("A",secret,"B");
//        	secretService.shareSecret("A",secret, "C");
//        	secretService.shareSecret("C",secret, "A");
//        	secretService.shareSecret("C",secret, "B");
//        	secretService.shareSecret("C",secret, "D");
//        	secretService.shareSecret("D",secret, "A");
//        	secretService.shareSecret("C",secret, "E");
//        	secretService.shareSecret("E",secret, "A");            
//        	secretService.readSecret("A", secret);
//        	secretService.readSecret("B", secret);
//        	secretService.readSecret("C", secret);
//        	secretService.readSecret("A", secret);
//        	secretService.readSecret("B", secret);
//          	secretService.unshareSecret("A",secret, "C");
//        	secretService.unshareSecret("C",secret, "B");
//            UUID secret2 = secretService.createSecret("A", "My little secret 2");
//            secretService.shareSecret("A",secret2,"C");//c =1
//        	secretService.shareSecret("A", secret2, "B");//b=1
//        	secretService.shareSecret("B", secret2, "C");//c=2
//        	secretService.shareSecret("A", secret2, "D");//d=1
//        	secretService.shareSecret("D", secret2, "C");//c=3
//        	secretService.readSecret("C", secret2);
//            secretService.unshareSecret("B", secret2, "C");
        	UUID secret3 = secretService.createSecret("A", "My little secret 3");
        	secretService.shareSecret("A",secret3,"B");
        	secretService.shareSecret("B",secret3,"C");
        	secretService.shareSecret("C",secret3,"D");
        	secretService.shareSecret("B",secret3,"D");
        	UUID secret4 = secretService.createSecret("A", "My little secret 4");
        	secretService.shareSecret("A",secret4,"D");
        	secretService.shareSecret("A",secret4,"E");
        	
        	UUID secret5 = secretService.createSecret("C", "My little secret 5");
        	secretService.shareSecret("C",secret5,"D");
        	secretService.shareSecret("D",secret5,"B");
        	secretService.shareSecret("B",secret5,"A");
          	secretService.shareSecret("B",secret5,"E");
          	secretService.shareSecret("B",secret5,"F");
          	secretService.shareSecret("B",secret5,"G");
          	secretService.shareSecret("B",secret5,"G");
          	secretService.shareSecret("B",secret5,"H");
          	secretService.shareSecret("B",secret5,"I");
          	secretService.shareSecret("B",secret5,"K");
          	secretService.shareSecret("B",secret5,"J");
          	secretService.shareSecret("G",secret5,"A");
          	secretService.shareSecret("H",secret5,"A");
          	secretService.shareSecret("I",secret5,"A");
          	secretService.shareSecret("J",secret5,"A");
          	secretService.shareSecret("K",secret5,"A");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("The best known secret: " + stats.getBestKnownSecret());
		System.out.println("The worst secret keeper: " + stats.getWorstSecretKeeper());
		System.out.println("The most trusted user: " + stats.getMostTrustedUser());
		ctx.close();
	}
}
