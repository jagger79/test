package cz.rkr.movies.fe.war;

import javax.naming.NamingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextBeanFactoryReference;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiLocatorSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyContextJndiBeanFactoryLocator extends JndiLocatorSupport {

	public static final String BEAN_FACTORY_PATH_DELIMITERS = ",; \t\n";

	public ApplicationContext useBeanFactory(String factoryKey) throws BeansException {
		try {
			ApplicationContext appFactory = lookup(factoryKey, ApplicationContext.class);
			return appFactory;
		} catch (NamingException ex) {
			return null;
		}
	}

	protected BeanFactoryReference createBeanFactory(ApplicationContext appFactory) throws BeansException {
		return new ContextBeanFactoryReference(appFactory);
	}

	public void bindParentContext(String factoryKey, String beanFactoryPath) {
		ApplicationContext ctx = createApplicationContext(beanFactoryPath);
		try {
			getJndiTemplate().bind(factoryKey, ctx);
		} catch (NamingException e) {
			throw new BootstrapException("Define an environment variable [" + factoryKey + "] containing "
					+ "the class path locations of XML bean definition files", e);
		}
	}

	/**
	 * Create the ApplicationContext instance, given an array of class path resource Strings which should be combined
	 * 
	 * @param resources
	 *            an array of Strings representing classpath resource names
	 * @return the created ApplicationContext
	 * @throws BeansException
	 *             if context creation failed
	 */
	protected ApplicationContext createApplicationContext(String beanFactoryPath) throws BeansException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfigBe.class);
		rootContext.refresh();
		return rootContext;
	}

}
