package name.pkrause.blueprint.interfaceadapters.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BaseAspect {

    public BaseAspect() {
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseAspect.class);

    @Around("name.pkrause.blueprint.interfaceadapters.aspect.CommonJoinPointConfig.useCasesExecution()")
    public Object checkExecutingTimeOfUseCasesMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object value = null;

        try {
            value = joinPoint.proceed();
        } catch (Exception e) {
            LOGGER.error("Error during execute " + joinPoint.getSignature().getName() + " method", e);
        }

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000000;
        LOGGER.info("Method " + joinPoint.getSignature().getName() + " executed in: " + executionTime + "ms");

        return value;
    }

    @After("name.pkrause.blueprint.interfaceadapters.aspect.CommonJoinPointConfig.useCasesExecution()")
    public void afterUseCaseExecution(JoinPoint joinPoint) {
        LOGGER.info("Executed " + joinPoint.getSignature() + " method");
    }
}
