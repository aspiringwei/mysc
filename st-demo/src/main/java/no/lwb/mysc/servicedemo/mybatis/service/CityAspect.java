package no.lwb.mysc.servicedemo.mybatis.service;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 面向方面
 * @author WeiBin Lin
 */
@Component
@Aspect
@Log4j2
public class CityAspect {

    @Pointcut(value = "within(no.lwb.mysc.servicedemo.mybatis.service.*)")
    public void CityService() {

    }

    @Around("execution(* no.lwb.mysc.servicedemo.mybatis.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch(getClass().getName());
        try {
            Object[]  args = joinPoint.getArgs();
            stopWatch.start(joinPoint.toShortString());
            return joinPoint.proceed(args);
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }
}
