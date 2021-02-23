package com.newyearblog.blog.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.AllArgsConstructor;
import lombok.ToString;

/* 
    使用springboot Aop来记录日志
*/

@Aspect
@Component // 加入spring容器
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.newyearblog.blog.controller.*.*(..))") // 切入点（切面）描述
    public void userControllerCut() {
    } // 切入点（切面）的名称

    @Pointcut("execution(* com.newyearblog.blog.controller.admin.*.*(..))") // 切入点（切面）描述
    public void adminControllerCut() {
    } // 切入点（切面）的名称

    @Before("userControllerCut() || adminControllerCut()") // 在cut切面之前执行
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); // 获得请求参数
        HttpServletRequest request = attributes.getRequest(); // 获取请求
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request: {}", requestLog);
    }

    @AfterReturning(returning = "result", pointcut = "userControllerCut() || adminControllerCut()") // 记录切入点函数的返回值
    public void doAfterRuturn(Object result) {
        logger.info("Result: {}", result);
    }

    // 请求日志类，定义日志记录的请求信息
    @ToString
    @AllArgsConstructor
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
