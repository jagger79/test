package cz.rkr.movies.be.war;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import cz.rkr.movies.be.facade.party.FacadePartyManager;
import cz.rkr.movies.be.facade.party.PartyPrototypeTest;
import cz.rkr.movies.be.war.rest.TestDomain;

@Configuration
@ComponentScan(basePackages = { "cz.rkr.movies.be.war.rest" })
@ImportResource({ "classpath*:spring/*.xml" })
@Profile({ "default" })
// @PropertySource("classpath:/spring//app.properties")
@PropertySource("classpath:${my.placeholder:spring}/jdbc.properties")
@Import({ AppAspectConfigBe.class })
@EnableTransactionManagement
public class AppConfigFe extends WebMvcConfigurationSupport {

	@Bean
	public static ScriptFactoryPostProcessor getScriptFactory() {
		ScriptFactoryPostProcessor sf = new ScriptFactoryPostProcessor();
		return sf;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PartyPrototypeTest getPartyPrototypeTest() {
		return new PartyPrototypeTest();
	}

	@Bean
	@Lazy
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public static FacadePartyManager getFactoryMethod() {
		return new FacadePartyManager();
	}

	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, TestDomain>() {
			public TestDomain convert(String source) {
				TestDomain ret = new TestDomain();
				ret.setNow(new Date());
				ret.setText("mam to");
				return ret;
			}
		});
	}

}
