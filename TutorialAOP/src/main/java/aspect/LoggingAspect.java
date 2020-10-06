package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
	
	@Before("execution(public void setName())")
	public void logging() {
		System.out.println("Logging Aspect is running before the method");
	}

}
