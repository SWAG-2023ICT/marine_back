package swag.marine.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
public class ControllerLoggingAop {

    @Pointcut("within(swag.marine.controller..*)")
    public void onRequest(){}
    @Before("onRequest()")
    public void beforeLogging(JoinPoint joinPoint){
        Method method = getMethod(joinPoint);
        log.info("[Method Name] : {}",method.getName());

        Object[] args = joinPoint.getArgs();
        if(args.length == 0) log.info("[Parameter] : null");

        for(Object arg : args){
            log.info("[Parameter Type] : {}",arg.getClass().getSimpleName());
            log.info("[Parameter Value] : {}", arg);
        }
    }

    @AfterReturning(value = "onRequest()", returning = "returnObj")
    public void afterLogging(JoinPoint joinPoint, Object returnObj){
        Method method = getMethod(joinPoint);
        log.info("[Method Name] : {}",method.getName());

        log.info("[Return Type] : {}", returnObj.getClass().getSimpleName());
        log.info("[Return Value] : {}", returnObj);
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
