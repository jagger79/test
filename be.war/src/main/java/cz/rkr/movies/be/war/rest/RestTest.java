package cz.rkr.movies.be.war.rest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.rkr.movies.be.facade.party.PartyTestProxy;
import cz.rkr.movies.be.war.async.AsyncTest;

@RestController
@RequestMapping(path = { "/" })
public class RestTest {

	@Autowired
	PartyTestProxy pt;
	@Autowired
	private AsyncTest asyncTest;

	@RequestMapping()
	public ResponseEntity<Object> getRoot() throws ExecutionException {
		Future<Object> ret = asyncTest.callWsAsynch();
		Future<Object> ret2 = asyncTest.callWsAsynch();
		Future<Object> ret3 = asyncTest.callWsAsynch();
		Future<Object> ret4 = asyncTest.callWsAsynch();

		ExecutionException exc = null;
		Object obj;
		try {
			obj = ret.get();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exc = e;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj = ret2.get();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exc = e;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj = ret3.get();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exc = e;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj = ret4.get();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exc = e;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (exc != null) {
			throw exc;
		}
		return ResponseEntity.accepted().body("<html><body>WORKS</body></html>");
	}

	@RequestMapping(path = { "/sub" })
	public ResponseEntity<Object> getSubroot() {
		try {
			pt.mail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.accepted().body("WORKS");
	}

	@EventListener
	@Cacheable
	public void test(String param) {
	}

}
