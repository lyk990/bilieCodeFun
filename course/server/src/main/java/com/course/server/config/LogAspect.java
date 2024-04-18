package com.course.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.course.server.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * define a point cut
     */
    @Pointcut("execution(public * com.course.*.controller..*Controller.*(..))")
    public void controllerPointcut() {
    }


    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // log uuid
        MDC.put("UUID", UuidUtil.getShortUuid());

        //request log
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        //operation
        String opName = "";
        if (name.contains("list") || name.contains("query")) {
            opName = "query";
        } else if (name.contains("save")) {
            opName = "save";
        } else if (name.contains("delete")) {
            opName = "delete";
        } else {
            opName = "operation";
        }

        //use reflection to get business name
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("no such business name");
        } catch (SecurityException e) {
            LOG.error("failed to get business name", e);
        }

        //print request info
        LOG.info("------ [{}] {} start ------", businessName, opName);
        LOG.info("request address: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("method name: {} {}", signature.getDeclaringTypeName(), name);
        LOG.info("remote address: {}", request.getRemoteAddr());

        //print request parameters
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        //do not log args which are too long or sensitive
        String[] excludeProperties = {};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("request parameters: {}", JSONObject.toJSONString(arguments, excludeFilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJointPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJointPoint.proceed();

        //exclude too long and sensitive args
        String[] excludeProperties = {"password"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);

        LOG.info("result: {}", JSONObject.toJSONString(result, excludeFilter));
        LOG.info("------ finish, spent: {} ms ------", System.currentTimeMillis() - startTime);
        return result;
    }
}
