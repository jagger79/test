package cz.rkr.movies.be.war;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestParam;

import cz.rkr.movies.be.war.rest.TestDomain;

@Configuration
@Profile({ "default" })
// @EnableAspectJAutoProxy
@Aspect
// @Aspect("perthis(com.xyz.myapp.SystemArchitecture.businessService())")
public class AppAspectConfigBe2 {

	// tady bude ted implementovat instance dany interface ci dedit z teto tridy, nededi,ale pres aop to da,ze implementuje tuto tridu
	@DeclareParents(value = "cz.rkr.movies.be..*+", defaultImpl = DefaultUsageTracked.class)
	public static UsageTracked mixin;

	@Before("cz.rkr.moview.SystemArchitecture.businessService()")
	public Object convertParty() {
		return null;
	}

	@Around("execution(org.springframework.http.ResponseEntity<Object> mail*(..)) && " //
			// + "com.xyz.myapp.SystemArchitecture.inDataAccessLayer() && "//
			+ "args(in)")
	public Object preProcessQueryPattern(ProceedingJoinPoint pjp, TestDomain in) throws Throwable {
		StopWatch watch = new StopWatch("test");
		watch.start("jedem");
		String newPattern = "";// preProcess(accountHolderNamePattern);
		try {
			return pjp.proceed(new Object[] { in });
		} finally {
			watch.stop();
			System.out.format("%s", watch);
		}
	}

	@Pointcut("execution(* cz.rkr.movies.dao.*.*(..))")
	public void dataAccessOperation() {
	}

	@Pointcut("cz.rkr.movies.be.war.SystemArchitecture.dataAccessOperation() && args(account,..)")
	private void accountDataAccessOperation(TestDomain in) {
	}

	@Pointcut("bean(partyTest)")
	private void beanOperation() {
	}

	// @target - limits matching to join points (the execution of methods when using Spring AOP) where the class of the executing object has
	// an annotation of the given type
	@Pointcut("@target(partyTest)")
	private void restOperation() {
	}

	@Pointcut("@target(org.springframework.stereotype.Service)")
	private void serviceBean() {
	}

	@Pointcut("execution(public * cz.rkr.movies..*.*(..))")
	private void serviceBeanExecution() {
	}

	@Around("cz.rkr.movies.be.war.AppAspectConfigBe.serviceBean() &&"//
			+ " cz.rkr.movies.be.war.AppAspectConfigBe.serviceBeanExecution()")
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

	@Around("execution(* cz.rkr.movies..*.*(..))")
	public Object allExecution(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}

	@Pointcut("@args(in)")
	private void argTypeAnnotation(RequestParam in) {
	}

	@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional) ||"//
			+ " @target(org.springframework.transaction.annotation.Transactional)")
	private void transactions(RequestParam in) {
	}

	@AfterThrowing(pointcut = "cz.rkr.movies.be.war.SystemArchitecture.dataAccessOperation()", throwing = "ex")
	public void doRecoveryActions(DataAccessException ex) {
		// ...
	}

	@AfterReturning(pointcut = "cz.rkr.movies.be.war.SystemArchitecture.dataAccessOperation()", returning = "retVal")
	public void doAccessCheck(Object retVal) {
		// ...
	}

}
