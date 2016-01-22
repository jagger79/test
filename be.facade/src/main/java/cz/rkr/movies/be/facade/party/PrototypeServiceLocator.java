package cz.rkr.movies.be.facade.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PrototypeServiceLocator {

	@Autowired
	ApplicationContext ctx;
	@Autowired
	ConfigurableListableBeanFactory beanFactory;

	@Lookup
	// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PartyPrototypeTest getInstance2232() {
		return null;
	}

	public <T> T create(Class<T> clazz, Object... constructorParams) {
		return beanFactory.getBean(clazz, constructorParams);
	}

	@Lookup
	public PartyPrototypeTest create(String test) {
		return null;
	}

	@Lookup
	public PartyPrototypeTest create(String test, String t) {
		return null;
	}

	public void close(Object instance) {
		beanFactory.destroyBean(instance);
	}

}
