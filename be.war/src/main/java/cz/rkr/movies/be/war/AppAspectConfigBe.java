package cz.rkr.movies.be.war;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;

@Configuration
@Profile({ "default" })
@EnableAspectJAutoProxy
@Aspect
// @Aspect("perthis(com.xyz.myapp.SystemArchitecture.businessService())")
public class AppAspectConfigBe {

	@Around(// "cz.rkr.movies.be.war.AppAspectConfigBe.serviceBean()"//
	// + " &&"//
	// " cz.rkr.movies.be.war.AppAspectConfigBe.serviceBeanExecution()"//
	"execution(* *..ServiceAopTest.*(..))"//
	// + " && @target(org.springframework.stereotype.Service)"//
	)
	public Object serviceBeanMethod(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch watch = new StopWatch("test");
		watch.start("jedem");
		try {
			return pjp.proceed();
		} finally {
			watch.stop();
			System.out.format("%s", watch.prettyPrint());
		}
	}

//	@Around("execution(* cz.rkr.movies.be.war.rest.*.*(..))")
//	public Object allExecution(ProceedingJoinPoint pjp) throws Throwable {
//		return pjp.proceed();
//	}

//	@Pointcut("@target(org.springframework.stereotype.Service)")
//	private void serviceBean() {
//	}
//
//	@Pointcut("execution(* cz.rkr.movies.be.facade.party.*.*(..))")
//	private void serviceBeanExecution() {
//	}

}
