package cz.rkr.movies.be.war.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cz.rkr.movies.be.facade.party.FacadePartyManager;

//@WebService(serviceName = "TestService")
@Component
public class TestWebServiceImpl extends SpringBeanAutowiringSupport implements TestWebService {

	@Autowired
	private FacadePartyManager partyManager;

	@PostConstruct
	private void precons() {
		System.out.format("%s", "sem tu");
	}

	@WebMethod
	public void insertAccount() {
		partyManager.test();
		// biz.insertAccount(acc);
	}

}
