package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class ValidationAspect {

	@Before("execution(public * edu.sjsu.cmpe275.aop.SecretService.*(..))")
	public void validate(JoinPoint joinPoint) {
		//System.out.println("validation the arguments of " + joinPoint.getSignature().toString());
		Object[] args = new String[joinPoint.getArgs().length];
		// fetch all the arguments and store in an array
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			args[i] = joinPoint.getArgs()[i].toString();
			//System.out.println(args[i]);
			if (args[i] == null || args[i].toString().trim().length() == 0) {
				throw new IllegalArgumentException("Argument passed cannot be null or empty ");
			}
		}

		if (args.length == 3) {
			//System.out.println("Args .legnth is 3");
			if (((String) args[0]).equals((String) args[2]))
				throw new IllegalArgumentException("User and targetId cannot be same.");
		}
		if (args.length == 2) {
			if (String.class == args[1].getClass() && args[1].toString().length() > 100 ) {
				throw new IllegalArgumentException("Secret Content length cannot be > 100");
			}
		}
	}

}
