package pl.com.bottega.documentmanagement.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Piotr on 09.07.2016.
 */
@Component
public class ProfilingAspect {
    public Object profile(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        Date startTime = new Date();
        Object returnValue = proceedingJoinPoint.proceed();
        //proceedingJoinPoint.proceed();
        Date endTime = new Date();
        long time = endTime.getTime() - startTime.getTime();
        String msg  = "Class: "
                + proceedingJoinPoint.getTarget().getClass().getName()
                + " Procesing time " + time + " ms" +" Method: "
                + proceedingJoinPoint.getSignature().getName();
        org.apache.log4j.Logger.getLogger(ProfilingAspect.class).info(msg);
        return returnValue;

    }
}
