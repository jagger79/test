package cz.rkr.movies.be.facade.party;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public interface PartyTestProxy {

	@Component
	public class te {
		@Value("${hello.world:Hello World proxy!}")
		String mess;

		public void setMess(String mess) {
			this.mess = mess;
		}
	}

	ResponseEntity<Object> mail() throws MessagingException;

	void getBeanFactoryOfThisClazz();

}
