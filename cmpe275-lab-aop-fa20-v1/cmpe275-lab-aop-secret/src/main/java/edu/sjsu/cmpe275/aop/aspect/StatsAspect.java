package edu.sjsu.cmpe275.aop.aspect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.SecretStatsImpl;

@Aspect
@Order(1)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

	@Autowired SecretStatsImpl stats;
	
	@AfterReturning(pointcut = "execution(public * edu.sjsu.cmpe275.aop.SecretService.createSecret(..))", returning = "returnVal")
	public void getUserID(JoinPoint joinPoint, Object returnVal) {
		String userId = (String) joinPoint.getArgs()[0];
		String secret = (String)joinPoint.getArgs()[1];	
		if(secret.length() > stats.getLengthOfLongestSecret())
			stats.setLongestSecret(new StringBuilder(secret));
		
//		System.out.println("created UUID is: " + returnVal);
		stats.setSecret((UUID) returnVal, (String) joinPoint.getArgs()[1]);
	
		stats.setSecretCreator((UUID) returnVal ,userId);
		Map<String , Set<String>> map = new HashMap<String, Set<String>>();
		map.put(userId, new HashSet<String>());
		stats.setShareMapForAUUID((UUID) returnVal, map);
	}
	
	@AfterReturning("execution(public * edu.sjsu.cmpe275.aop.SecretService.unshareSecret(..))")
	public void deleteSecretFromMap(JoinPoint joinPoint) {
		String userId = (String)joinPoint.getArgs()[0];
		UUID uuid = (UUID)joinPoint.getArgs()[1];
		String targetId =  (String)joinPoint.getArgs()[2];
		//Map<UUID, Map<String, Set<String>>> shareMap = stats.getShareMap();
		Map<String, Set<String>> map = stats.getShareMapForAUUID(uuid);
		
		Set<String> set =map.getOrDefault(userId,new HashSet<String>());
		
		set.remove(joinPoint.getArgs()[2]);
		if(map.containsKey(targetId)) {
			map.remove(targetId);
		}
		map.put(userId, set);
		stats.setShareMapForAUUID(uuid, map);
		//shareMap.put(uuid, map);
		//stats.setShareMap(shareMap);	
	}
	
	@AfterReturning("execution(public * edu.sjsu.cmpe275.aop.SecretService.shareSecret(..))")
	public void addSecretToMap(JoinPoint joinPoint) {
//		System.out.println();
//		System.out.println();
		//Map<UUID, Map<String, Set<String>>> shareMap = stats.getShareMap();

		String userId = (String) joinPoint.getArgs()[0];
		String targetId = (String) joinPoint.getArgs()[2];
		UUID uuid = (UUID) joinPoint.getArgs()[1];
		// checkIfAnyArgumentIsNull(userId, targetId, uuid);
		Map<String, Set<String>> map = stats.getShareMapForAUUID(uuid);
		
		Set<String> set = map.getOrDefault(userId, new HashSet<String>());
		if(!set.contains(targetId)) {
			//System.out.println("Most trusted user is: " + stats.getMostTrustedUser());
			int maxTrustedCount = stats.getShareCountMap(stats.getMostTrustedUser())[0];
			
			int[] sharedBy = stats.getShareCountMap(userId);
			//System.out.println("Share count for "+ userId+ "  is:  shareBy[0] " +sharedBy[0]+ " sharedBY [1]:  "+ sharedBy[1]  );
			sharedBy[1]+=1;
			stats.setSharCountMapForUser(userId, sharedBy);
			
			int[] sharedTo = stats.getShareCountMap(targetId);
			sharedTo[0] +=1;
			
			stats.setSharCountMapForUser(targetId, sharedTo);
			
			getMostTrustedUser(targetId, maxTrustedCount, sharedTo);
			getWorstSecretKeeper(userId, sharedBy);
			
			set.add(targetId);
			map.put(userId, set);
			stats.setShareMapForAUUID(uuid, map);

		}
	}
	
	@AfterReturning(pointcut = "execution(public * edu.sjsu.cmpe275.aop.SecretService.readSecret(..))", returning = "returnVal")
	public void readSecret(JoinPoint joinPoint, Object returnVal) {
		String userId = (String) joinPoint.getArgs()[0];
		UUID uuid = (UUID)joinPoint.getArgs()[1];
		// if creator is the same as the user ID. do nothing
		if (!userId.equals(stats.getSecretCreator(uuid))) {
			Set set = stats.getReadCountMap(uuid);
			if (!set.contains(userId)) {
				set.add(userId);
				//System.out.println("For this UUID " + uuid + " known count is: " + set.size());
				stats.setReadCountMap(uuid, set);
				// if the mostKnownUUID set size < set.size
				// update the mostKnownUUID ;
				// else if(Set size for both the UUID is equal)
				// compare the Strings by fetching mostKnownSecret and returnVal for current
				// UUID
				// and then update the mostKnownUUID;
				UUID mostKnownUUID = null;
				// stats.getBestKnownSecret();
				//System.out.println("Most Known UUID is: "+ stats.getMostKnownUUID()+  " size: "+ stats.getReadCountMap(stats.getMostKnownUUID()));
				int mostKnownUUIDSize = stats.getReadCountMap(stats.getMostKnownUUID()).size();
				if (mostKnownUUIDSize < set.size()) {
					mostKnownUUID = uuid;
					stats.setMostKnownUUID(mostKnownUUID);
					//System.out.println("Setting new most Known UUID: "+mostKnownUUID + "  size : " +mostKnownUUIDSize);
				} else if (mostKnownUUIDSize == set.size()) {
					mostKnownUUID = stats.getBestKnownSecret().compareTo((String) returnVal) < 0
							? stats.getMostKnownUUID()
							: uuid;
					stats.setMostKnownUUID(mostKnownUUID);
				}
				
				
				// update the bestKnownSecret
			}
		}
		
		
	}

	private void getWorstSecretKeeper(String userId, int[] sharedBy) {
		int worstSecretCount = stats.getShareCountMap(stats.getWorstSecretKeeper())[0]-stats.getShareCountMap(stats.getWorstSecretKeeper())[1] ;
		//System.out.println("Worst secret Count for "+ userId+ " is: "+worstSecretCount + " sharedBY[0] "+ sharedBy[0] + " sharedBY[1] " +sharedBy[1]);
		if(worstSecretCount == sharedBy[0]- sharedBy[1]) {
			String s = stats.getWorstSecretKeeper().compareTo(userId) < 0 ?stats.getWorstSecretKeeper(): userId;
			stats.setWorstSecretKeeper(s);
		}
		else if(sharedBy[0]- sharedBy[1] < worstSecretCount) {
			//System.out.println("Worst Secret keeper is getting updated to "+ userId);
			stats.setWorstSecretKeeper(userId);
		}
			
	}

	private void getMostTrustedUser(String targetId, int maxTrustedCount, int[] sharedTo) {
		//System.out.println(maxTrustedCount + " max TrustedCount " +  "  targetID:  " + targetId);
		if(maxTrustedCount == sharedTo[0]) {
			//System.out.println("Max trust count for 2 people are same");
			String s = stats.getMostTrustedUser().compareTo(targetId) < 0 ?stats.getMostTrustedUser(): targetId;
			stats.setMostTrustedUser(s);
		}
		
		else if(maxTrustedCount < sharedTo[0]) {
			stats.setMostTrustedUser(targetId);
		}
	}
	
}
