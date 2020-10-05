package edu.sjsu.cmpe275.aop.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class RetryAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Around("execution(public void edu.sjsu.cmpe275.aop.SecretService.*(..))")
	public Object networkFailure(ProceedingJoinPoint joinPoint) throws Throwable {
		//System.out.printf(" +    Retry aspect prior to the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		Object result = null;
		int i =0;
		boolean flag = false;
		while(i< 3) {
			try {
				result = joinPoint.proceed();
				i=3;
				//System.out.printf("   Finished the executuion of the metohd %s with result %s\n", joinPoint.getSignature().getName(), result);
			}catch(IOException e1){
				System.out.println("  Network failure occured "+  i + " times.");
				flag = true;
				i++;
			}
			finally {
				if( i ==3 && flag)
					System.out.println("System has tried 3 times , now exiting.");
			}
		}
		return result;
		
	}

}
