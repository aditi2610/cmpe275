import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.ShapeService;

public class AopMain{
	
	public static void main(String[] args) {
		ApplicationContext appContext =  new ClassPathXmlApplicationContext("beans.xml");
		ShapeService service = appContext.getBean("shapeService", ShapeService.class);
		System.out.println( " value : "+ service.readShape("a"));
		//System.out.println(service.getCircle().getName());
	}
}