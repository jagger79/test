package cz.rkr.movies.be.war;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;

import cz.rkr.movies.be.facade.party.FacadePartyManager;
import cz.rkr.movies.be.facade.party.PartyPrototypeTest;

@Configuration
@ComponentScan(basePackages = { "cz.rkr.movies.be.facade", "cz.rkr.movies.be.war.async", "cz.rkr.movies.be.war.ws" })
// @EnableCaching(mode = AdviceMode.PROXY)
@EnableAsync
// @EnableScheduling
@ImportResource({ "classpath*:spring/*.xml" })
@Profile({ "default" })
// @PropertySource("classpath:/spring//app.properties")
@PropertySource("classpath:${my.placeholder:spring}/jdbc.properties")
public class AppConfigBe {

	@Bean
	public static ScriptFactoryPostProcessor getScriptFactory() {
		ScriptFactoryPostProcessor sf = new ScriptFactoryPostProcessor();
		return sf;
	}

	// @Bean
	// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	// public PartyPrototypeTest getPartyPrototypeTest() {
	// return new PartyPrototypeTest();
	// }
	//
	// @Bean
	// public PartyPrototypeTest getPartyPrototypeTest(String test) {
	// return new PartyPrototypeTest(test);
	// }

	@Bean
	@Lazy
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public static FacadePartyManager getFactoryMethod() {
		return new FacadePartyManager();
	}

}
