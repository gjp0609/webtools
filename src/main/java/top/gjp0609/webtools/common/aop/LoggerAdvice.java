package top.gjp0609.webtools.common.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Aspect
@Service
@SuppressWarnings("unused")
public class LoggerAdvice {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("within(top.gjp0609..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        log.info("-------------------------------->>>>>>>>--------------------------------");
        log.info("执行 " + loggerManage.value() + " 开始");
        threadLocal.set(new Date().getTime());
        log.info(joinPoint.getSignature().toString());
        log.info(parseParams(joinPoint.getArgs()));
    }

    @AfterReturning("within(top.gjp0609..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        long l = new Date().getTime() - threadLocal.get();
        log.info("执行 " + loggerManage.value() + " 结束，耗时" + (l / 1000) + "秒");
        log.info("--------------------------------<<<<<<<<--------------------------------\n");
    }

    @AfterThrowing(pointcut = "within(top.gjp0609..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        long l = new Date().getTime() - threadLocal.get();
        log.error("执行 " + loggerManage.value() + " 异常，耗时" + (l / 1000) + "秒");
        log.info("--------------------------------<<<<<<<<--------------------------------\n");
    }

    private String parseParams(Object[] params) {
        if (null == params || params.length <= 0 || params.length > 10240) {
            return "无参数";
        }
        StringBuilder param = new StringBuilder("传入参数[ ");
        for (Object obj : params) {
            param.append(ToStringBuilder.reflectionToString(obj)).append(", ");
        }
        param.append(" ]");
        return param.toString();
    }


}
