package edu.sjsu.cmpe275.aop;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;

public class SecretServiceTest {

    /***
     * These are dummy test cases. You may add test cases based on your own need.
     */
	SecretStats stats = new SecretStatsImpl();
	SecretService secretService  = new SecretServiceImpl();
    @Test
    public void testOne() { 
    	UUID secret;
//		try {
//			secret = secretService.createSecret("Alice", "My little secret");
//			secretService.shareSecret("Alice", secret, "Bob");
//			secretService.readSecret("Bob", secret);
//			secretService.shareSecret("Bob", secret, "Charlie");	
//			
//
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String userId= stats.getMostTrustedUser();	
//	
//    		assertEquals(userId, "Bob");
    			
    }

    @Test
    public void testCaseN() { }
}