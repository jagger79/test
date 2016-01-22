package cz.rkr.movies.be.facade.party;

import org.springframework.stereotype.Component;

//@Component
public class FacadePartyManager {

	public void test() {
	}

	public void close() {
		System.out.format("Shutting donw %s", this);
	}

}
