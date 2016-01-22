package cz.rkr.movies.fe.war;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "cz.rkr.movies.fe.war.rest" })
@ImportResource({ "classpath*:spring/*.xml" })
@Profile({ "default" })
// @PropertySource("classpath:/spring//app.properties")
@PropertySource("classpath:${my.placeholder:spring}/jdbc.properties")
public class AppConfigFe {
}
