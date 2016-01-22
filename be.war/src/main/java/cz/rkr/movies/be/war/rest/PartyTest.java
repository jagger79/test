package cz.rkr.movies.be.war.rest;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.rkr.movies.be.facade.party.PartyTestProxy;

@RestController
@RequestMapping(path = { "/party" })
public class PartyTest {

	@Autowired
	Map<String, PartyTestProxy> pt;
	@Autowired
	List<PartyTestProxy> ptSet;
	@Autowired
	ApplicationContext ctx;
	@Autowired
	Validator validator;
	@Autowired
	private javax.validation.Validator javaxValidator;

	@Autowired
	ServiceAopTest serviceAopTest;

	@RequestMapping()
	public ResponseEntity<Object> mail(@RequestParam(name = "test") TestDomain in) throws MessagingException {

		pt.values().iterator().next().mail();
		pt.values().iterator().next().getBeanFactoryOfThisClazz();

		serviceAopTest.test();

		boolean b = validator.supports(Object.class);
		BeanDescriptor constraints = javaxValidator.getConstraintsForClass(Object.class);

		return ResponseEntity.accepted().body("<html><body>MAIL sent</body></html>");
	}

}
