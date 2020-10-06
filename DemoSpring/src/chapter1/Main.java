package chapter1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String args[]) {
		ApplicationContext appContext  = new ClassPathXmlApplicationContext("chapter1.xml");
		SequenceGenerator  sequence = (SequenceGenerator)appContext.getBean("sequenceGenerator");
		System.out.println(sequence.getSequence());
		System.out.println(sequence.getSequence());
	}

}
