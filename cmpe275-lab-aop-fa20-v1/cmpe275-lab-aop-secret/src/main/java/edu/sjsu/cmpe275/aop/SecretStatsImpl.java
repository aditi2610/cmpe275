package edu.sjsu.cmpe275.aop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SecretStatsImpl implements SecretStats {

	Map<UUID, String> secrets = new HashMap<UUID, String>();
	Map<UUID, String> creatorMap = new HashMap<UUID, String>();
	Map<UUID, Map<String, Set<String>>> shareMap = new HashMap<UUID, Map<String, Set<String>>>();
	
	Map<String, int[]> statsMap =new HashMap<String, int[]>();
	Map<UUID, Set<String>> readCountMap = new HashMap<UUID, Set<String>>();
	

	StringBuilder longestSecret = new StringBuilder();
	String mostTrustedUser = new String();
	String worstSecretKeeper= new String();
	UUID mostKnowUUID ;

	
	public Set<String> getReadCountMap(UUID uuid) {
		return readCountMap.getOrDefault(uuid, new HashSet<String>());
	}

	public void setReadCountMap(UUID uuid, Set<String> readers) {
		readCountMap.put(uuid, readers);
	}

	public UUID getMostKnownUUID() {
		return this.mostKnowUUID;
	}

	public void setMostKnownUUID(UUID mostKnownUUID) {
		this.mostKnowUUID = mostKnownUUID;
	}

	public int[] getShareCountMap(String userId){
		return statsMap.getOrDefault(userId, new int[2]);
	}
	
	public void setSharCountMapForUser(String userId, int[] arr) {
		statsMap.put(userId, arr);
	}
	
	public StringBuilder getLongestSecret() {
		return longestSecret;
	}


	public void setLongestSecret(StringBuilder longestSecret) {
		this.longestSecret = longestSecret;
	}


	public String getSecretCreator(UUID uuid) {
		return creatorMap.get(uuid);
	}


	public void setSecretCreator(UUID uuid, String creator) {
		this.creatorMap.put(uuid, creator);
	}


	public  Map<String, Set<String>> getShareMapForAUUID(UUID uuid) {
		return shareMap.getOrDefault(uuid, new HashMap<String, Set<String>>());
	}


	public void setShareMapForAUUID(UUID uuid, Map<String, Set<String>> shareMap) {
		this.shareMap.put(uuid, shareMap);
	}
	
	
	public String getSecrets(UUID uuid) {
		return secrets.get(uuid);
	}


	public void setSecret(UUID uuid, String secret) {
		secrets.put(uuid, secret);
	}
	//@Override
	public void resetStatsAndSystem() {
		secrets = new HashMap<UUID, String>();
		creatorMap = new HashMap<UUID, String>();
		shareMap = new HashMap<UUID, Map<String,Set<String>>>();
		statsMap = new HashMap<String, int[]>();
		readCountMap = new HashMap<UUID, Set<String>>();
		longestSecret = new StringBuilder();
		mostKnowUUID =null;
		mostTrustedUser= "";
		worstSecretKeeper = "";
		
		
	}

	//@Override
	public int getLengthOfLongestSecret() {
		return longestSecret.length();
	}

	//@Override
	public String getMostTrustedUser() {
		return this.mostTrustedUser;
	}
	public void setMostTrustedUser(String user) {
		this.mostTrustedUser = user;
	}
	//@Override
	public String getWorstSecretKeeper() {
		return this.worstSecretKeeper;
	}

	public void setWorstSecretKeeper(String worstSecretKeeper) {
		this.worstSecretKeeper = worstSecretKeeper;
	}
	//@Override
	public String getBestKnownSecret() {
		return secrets.getOrDefault(this.mostKnowUUID, null);
	}
	
}


