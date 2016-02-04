package cz.rkr.test;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
@ImportResource({ "classpath*:spring/*.xml" })
@ComponentScan(basePackages = { "cz.rkr" })
@EnableTransactionManagement
public class Config {

	@Bean(name = "ehcache")
	public net.sf.ehcache.CacheManager getCacheManager() {
		// TODO Auto-generated method stub
		net.sf.ehcache.CacheManager cache = EhCacheManagerUtils.buildCacheManager();
		cache.addCache("test");
		return cache;
	}

}
