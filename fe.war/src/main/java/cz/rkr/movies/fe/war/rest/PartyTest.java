package cz.rkr.movies.fe.war.rest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.rkr.movies.be.facade.party.PartyTestProxy;

@RestController
@RequestMapping(path = { "/party" })
public class PartyTest implements BeanPostProcessor {

	@Autowired
	Map<String, PartyTestProxy> pt;
	@Autowired
	List<PartyTestProxy> ptSet;
	@Autowired
	ApplicationContext ctx;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.format("POST PROCESS AFTER %s-%s\n", beanName, bean);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.format("POST PROCESS BEFORE %s-%s\n", beanName, bean);
		return bean;
	}

	@RequestMapping()
	public ResponseEntity<Object> mail() throws MessagingException {

		pt.values().iterator().next().mail();
		pt.values().iterator().next().getBeanFactoryOfThisClazz();

		return ResponseEntity.accepted().body("<html><body>MAIL sent</body></html>");
	}

}
