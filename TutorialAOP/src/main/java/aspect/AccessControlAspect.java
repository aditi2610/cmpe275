package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AccessControlAspect {

	
//	@AfterReturning(pointcut =" args(name)", returning = "returnString")
//	public void myAfterReturningAdvice(String name,String returnString ) {
//			System.out.println("After Returning is executed");
//		
//	}
//	
	@Around("execution( * read*(..))")
	public Object myAroundAdvice(ProceedingJoinPoint procJoinPoint) {
		
		Object returnValue = null;
		System.out.println("before Proceeding");
		String args[] = new String[procJoinPoint.getArgs().length];
		for(int i =0;i< procJoinPoint.getArgs().length; i++) {
			args[i] = (String)procJoinPoint.getArgs()[i];
		}
		if(args[0].equals("a")) {
			try {
			returnValue =	procJoinPoint.proceed();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Not Authorized");
		}
		
		System.out.println("After Proceeding ");
		
		return returnValue;
	}
}

