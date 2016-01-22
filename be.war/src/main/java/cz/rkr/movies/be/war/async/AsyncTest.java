package cz.rkr.movies.be.war.async;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cz.rkr.movies.be.war.ws.TestWebService;

@Component
public class AsyncTest {

	@Autowired
	@Qualifier("testRemoteWebService")
	private TestWebService ws;

	@Async
	public Future<Object> callWsAsynch() {

		ws.insertAccount();
		return null;
	}

}
