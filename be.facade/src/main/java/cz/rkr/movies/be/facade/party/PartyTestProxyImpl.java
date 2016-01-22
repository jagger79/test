package cz.rkr.movies.be.facade.party;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service // ("testProxy")
// @RequestMapping(path = { "/party1" })
public class PartyTestProxyImpl implements PartyTestProxy, FactoryBean<PartyTestProxy> {// , BeanFactoryPostProcessor {

	List<String> autowired;// in xml

	@Autowired
	ApplicationContext ctx;
	@Autowired
	ConfigurableListableBeanFactory beanFactory;
	@Autowired
	FacadePartyManager facadePartyManager;
	// public String message;
	@Autowired
	te message = new te();
	@Autowired
	PrototypeServiceLocator locator;

	@Override
	public PartyTestProxy getObject() throws Exception {
		return this;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return PartyTestProxy.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	@RequestMapping()
	public ResponseEntity<Object> mail() throws MessagingException {
		PartyPrototypeTest tr = createPrototypeParty();

		tr.mail();

		PartyPrototypeTest tr2 = locator.create("hola");
		PartyPrototypeTest tr3 = locator.create("test", "2");
		PartyPrototypeTest tr4 = locator.create(PartyPrototypeTest.class, "test", "2");

		locator.close(tr);
		locator.close(tr2);
		locator.close(tr3);
		locator.close(tr4);

		return ResponseEntity.accepted().body("<html><body>MAIL sent</body></html>");
	}

	public void getBeanFactoryOfThisClazz() {
		// Object bean = beanFactory.getBean("&partyTestProxyImpl");
		// bean = beanFactory.getBean("partyTestProxyImpl");
	}

	@Lookup
	public PartyPrototypeTest createPrototypeParty() {
		// will be overriden
		return null;
	}

	@Bean
	// @Lazy
	// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public FacadePartyManager getFactoryMethod() {
		return new FacadePartyManager();
	}

}
