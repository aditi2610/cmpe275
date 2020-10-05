package edu.sjsu.cmpe275.aop.aspect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.NotAuthorizedException;
import edu.sjsu.cmpe275.aop.SecretStatsImpl;

@Aspect
@Order(1)
public class AccessControlAspect {

	@Autowired SecretStatsImpl stats;

	@Pointcut("execution(public * edu.sjsu.cmpe275.aop.SecretService.*(String,java.util.UUID, ..))")
	private void checkIfUserhasAccess() {}
	

	@Before("checkIfUserhasAccess()")
	public void checkAccess(JoinPoint joinPoint) {
		String userId = (String)joinPoint.getArgs()[0];
		UUID uuid = (UUID)joinPoint.getArgs()[1];

		String creator = stats.getSecretCreator(uuid);
		
		Map<String, Set<String>> map = stats.getShareMapForAUUID(uuid);
		if(!map.containsKey(userId)) {
			List<String> list = new ArrayList<String>();
			list.addAll(map.getOrDefault(creator, new HashSet<String>()));
			int i = 0;
			while (i < list.size()) {
				String user = list.get(i);
				if (user == userId) {
					//System.out.println("User has been found!!" + user);
					break;
				}
				list.addAll(map.getOrDefault(user, new HashSet<String>()));
				i++;
				
			}
			if (i == list.size()) {
				throw new NotAuthorizedException("user " +userId+ " doesnot have access to the secret");
			}
		}
	}
	
	
	@Before("execution(public * edu.sjsu.cmpe275.aop.SecretService.unshare*(String,java.util.UUID, *))")
	public void unshareAccess(JoinPoint joinPoint) {
		String userId = (String)joinPoint.getArgs()[0];
		UUID uuid = (UUID)joinPoint.getArgs()[1];
		String targetId = (String)joinPoint.getArgs()[2];
		//get the share map for uuid and get the list of people userId has shared with
		// if the targetId is not within the list of people userId has shared it with
		// throw exception
		Map<String, Set<String>> map =stats.getShareMapForAUUID(uuid);
		System.out.println("trying to unshare: " + userId);
		String creator = stats.getSecretCreator(uuid);
		if(map.containsKey(userId)) {
			Set<String> sharedWithList = map.getOrDefault(userId, new HashSet<String>());
			if(!sharedWithList.contains(targetId)) {
				throw new NotAuthorizedException("user " +userId+ " has not shared the secret with "+ targetId +" . Hance cannot unshare it with him");
			}
		}else {
			throw new NotAuthorizedException("user " +userId+ " has not shared the secret with "+ targetId +" . Hance cannot unshare it with him");
		}
		
	}
	

	
	
	






}
