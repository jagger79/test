package cz.rkr.movies.fe.war;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		container.setInitParameter(ContextLoader.LOCATOR_FACTORY_KEY_PARAM, "sharedSpring");
		container.setInitParameter(ContextLoader.LOCATOR_FACTORY_SELECTOR_PARAM, "classpath*:spring/*.xml");

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfigFe.class);

		// Manage the lifecycle of the root application context
		container.addListener(new MyContextLoaderListener(rootContext));

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}
