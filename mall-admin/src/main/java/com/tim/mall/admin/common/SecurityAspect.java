package com.tim.mall.admin.common;


import com.tim.mall.common.Context;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class SecurityAspect {

    private static final String LOGINADDRE="/admin/login";

    @Pointcut("execution(public * com.tim.mall.admin.Controller.*.*(..))")
    public void configuration(){
    }

    @Around("configuration()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        //首先应该排除登录页面,否则会造成无限循环
        String requestURI = request.getRequestURI();
        if(requestURI.contains("/admin/login")){
            return pjp.proceed();
        }else{
            //判断该用户是否已经登录
            if(session.getAttribute(Context.CURRENT_USER)==null||session.getAttribute(Context.PERMISSION_ATTRIBUTES)==null){
                return "login";
            }else{
                //判断该用户是否有该页面的权限
                List url=(List<String>)session.getAttribute(Context.PERMISSION_ATTRIBUTES);
                if(url.contains(requestURI)){
                    return pjp.proceed();
                }else{
                    return "noPermission";
                }
            }
        }

    }

}
