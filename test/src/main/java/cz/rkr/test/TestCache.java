package cz.rkr.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TestCache {

	@Autowired
	private CacheManager cacheManager;

	@Cacheable(cacheNames = { "test" })
	// @Cacheable(cacheNames="books", key="#isbn")
	public Object testMethod(String key) {
		new Exception().printStackTrace();
		return "testovani cache";
	}

	@CachePut(cacheNames = { "test" })
	public Object testMethod1(String key) {
		return cacheManager.getCacheNames();
	}

}
