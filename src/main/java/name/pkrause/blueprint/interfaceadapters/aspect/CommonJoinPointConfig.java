package name.pkrause.blueprint.interfaceadapters.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonJoinPointConfig {
    @Pointcut("execution(* name.pkrause.blueprint.usecases.*.*.*(..))")
    public void useCasesExecution() {
    }
}
