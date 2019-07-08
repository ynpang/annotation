package com.enjoy.aspect;

import com.enjoy.annotation.DoSomething;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class DoSomethingAspect {

    private String requestPath = null ; // 请求地址
    private String args = null; //方法里的参数
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果

    //定义@Around增强，pointcut连接点使用@annotation(xxx)进行定义
    @Around("@annotation(dst)")
    public Object doAround(ProceedingJoinPoint pjp, DoSomething dst) throws Throwable{
        String methodName = pjp.getSignature().getName();
        boolean neeedLog = dst.needLog();
        Object result = null;

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        // 获取输入参数
        inputParamMap = request.getParameterMap();

        // 获取请求地址
        requestPath = request.getRequestURI();
        String ip = request.getRemoteAddr();

        args = Arrays.toString(pjp.getArgs());

        //执行目标方法
        outputParamMap = new HashMap<String, Object>();

        try{
            result = pjp.proceed();
            outputParamMap.put("result", result);
            if(neeedLog){
                log.info("requestPath{},methodName{},methodParams{}," +
                        "inputParams{},returnMsg,ip,status",requestPath,pjp.getSignature().getName(),args,inputParamMap,outputParamMap,ip,1);
            }
        }catch (Exception e){
            e.printStackTrace();
            if(neeedLog){
            log.info("requestPath{},methodName{},methodParams{}," +
                    "inputParams{},returnMsg,ip,status",requestPath,pjp.getSignature().getName(),args,inputParamMap,outputParamMap,ip,2);
            }
            return e.getMessage().toString();
        }
        return result;
    }
}
