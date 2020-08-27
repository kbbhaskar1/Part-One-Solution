package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InMemoryStorageService {
	
	
	private Map<Long, Integer []> localStore = new HashMap<Long, Integer []>();

	public Map<Long, Integer[]> getLocalStore() {
		return localStore;
	}

	
	

}
