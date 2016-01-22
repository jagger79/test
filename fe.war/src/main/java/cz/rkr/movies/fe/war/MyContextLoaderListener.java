package cz.rkr.movies.fe.war;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class MyContextLoaderListener extends ContextLoaderListener {

	public MyContextLoaderListener() {
	}

	public MyContextLoaderListener(WebApplicationContext context) {
		super(context);
	}

	@Override
	protected ApplicationContext loadParentContext(ServletContext servletContext) {
		ApplicationContext parentContext = null;
		String beanFactoryPath = servletContext.getInitParameter(LOCATOR_FACTORY_SELECTOR_PARAM);
		String parentContextKey = servletContext.getInitParameter(LOCATOR_FACTORY_KEY_PARAM);

		if (parentContextKey != null) {
			// locatorFactorySelector may be null, indicating the default "classpath*:beanRefContext.xml"
			MyContextJndiBeanFactoryLocator locator = new MyContextJndiBeanFactoryLocator();
			locator.setResourceRef(true);

			parentContext = (ApplicationContext) locator.useBeanFactory(parentContextKey);
			if (parentContext == null) {
				locator.bindParentContext(parentContextKey, beanFactoryPath);
				parentContext = (ApplicationContext) locator.useBeanFactory(parentContextKey);
			}
		}

		return parentContext;
	}

}
