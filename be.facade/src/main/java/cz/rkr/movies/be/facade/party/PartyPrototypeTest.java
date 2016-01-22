package cz.rkr.movies.be.facade.party;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.MessagingException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PartyPrototypeTest implements InitializingBean, DisposableBean, ApplicationEventPublisherAware {
	// ,ApplicationListener<ApplicationEvent>{

	List<String> autowired;// in xml
	ApplicationEventPublisher applicationEventPublisher;

	String test1;
	String test2;

	public PartyPrototypeTest() {
		System.out.format("@CREATE %s\n", this);
	}

	public PartyPrototypeTest(String test) {
		test1 = test;
		System.out.format("@CREATE %s-%s\n", this, test);
	}

	public PartyPrototypeTest(String test, String test2) {
		test1 = test;
		this.test2 = test2;
		System.out.format("@CREATE %s-%s,%s\n", this, test, test2);
	}

	@PostConstruct
	public void predem() {
		System.out.format("@POST_CONSTRUCT %s\n", this);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.format("AFTER_PROPERTIES_SET %s\n", this);
	}

	@PreDestroy
	public void zniceni() {
		System.out.format("@PRE_DESTROY %s-%s,%s\n", this, test1, test2);
	}

	@Override
	public void destroy() throws Exception {
		System.out.format("DESTROY %s-%s,%s\n", this, test1, test2);
	}

	@RequestMapping()
	public void mail() throws MessagingException {
		System.out.format("SENDING MAIL %s\n", this);
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		try {
			sender.testConnection();
		} catch (Exception e) {
			System.out.format("PUBLISH EVENT %s\n", this);
			applicationEventPublisher.publishEvent("TEST");
		}
	}

	public void close() {
		System.out.format("Shutting donw %s", this);
	}

	public void shutdown() {
		System.out.format("Shutting donw %s", this);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
