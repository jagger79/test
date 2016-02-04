package cz.rkr.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class TestCacheRest {

	@Autowired
	private TestCache testCache;

	@RequestMapping(path = "/get")
	public Object testMethod(@RequestParam(defaultValue = "test") String key) {
		String ret = testCache.testMethod(key).toString();
		return ResponseEntity.ok("<body>" + ret + "</body>");
	}

	@RequestMapping(path = "/put")
	public ResponseEntity<Object> testMethod1(@RequestParam(defaultValue = "test") String key) {
		String keys = testCache.testMethod1(key).toString();
		return ResponseEntity.ok("<body>" + keys + "</body>");
	}

}
