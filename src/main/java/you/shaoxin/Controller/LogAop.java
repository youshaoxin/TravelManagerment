package you.shaoxin.Controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import you.shaoxin.Services.SysLogService;
import you.shaoxin.domin.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 功能:  AOP日志切面类
 * 创建时间: 2019-08-23 22:14 --游菜花
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    /*
     * 功能:前置通知,主要获取开始时间，执行的类是哪一个，执行了哪个方法
    **/
    @Before("execution(* you.shaoxin.Controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//得到具体访问的类对象
        String methodName = jp.getSignature().getName();//获取访问的方法名
        Object[] args = jp.getArgs();//获取访问的方法参数

        //获取具体执行的方法的method对象
        if(args == null || args.length==0) {
            method = clazz.getMethod(methodName);//只能获取无参的方法
        }else{
            Class[] classArgs = new Class[args.length];//参数的class数组
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();//拿到每一参数的class对象，装到class数组中
            }
            method = clazz.getMethod(methodName,classArgs);
        }
    }

    /*
     * 功能:后置通知
     **/
    @After("execution(* you.shaoxin.Controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        //1、获取访问的时长
        Long time = new Date().getTime()-visitTime.getTime();
        String url = "";


        //2、获取执行的url
        if(clazz!=null && method!=null && clazz!= LogAop.class){
            /*
             * 功能:获取类上注解的路径值
            **/
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);//获取注解为RequestMapping的注解
            if (classAnnotation!=null){
                //获取类上的注解@RequestMapping（”xxx“）
                String[] classvalue = classAnnotation.value();//得到注解名称为Value的注解数组

                //获取方法上的注解@RequestMapping("xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();


                    //url = 类名+方法名
                    url = classvalue[0]+methodValue[0];


                    //3、获取ip地址
                    String ip = request.getRemoteAddr();

                    //4、获得当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登录的用户
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);//执行时长
                    sysLog.setIp(ip);//ip地址
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());//访问的方法
                    sysLog.setUrl(url);//访问路径
                    sysLog.setUsername(username);//访问用户
                    sysLog.setVisitTime(visitTime);//开始访问时间

                    //调用service完成操作
                    sysLogService.save(sysLog);

                }
            }
        }
    }

}
