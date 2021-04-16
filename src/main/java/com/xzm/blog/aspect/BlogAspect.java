package com.xzm.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.xzm.blog.NotFoundException;
import com.xzm.blog.bean.Blog;
import com.xzm.blog.constant.BlogConstant;
import com.xzm.blog.util.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiangzhimin
 * @Description 使用ip控制浏览量
 * @create 2021-04-16 10:41
 */

@Aspect
@Component
public class BlogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.xzm.blog.service.BlogService.selectAndConvert(..))")
    public void log() {
    }

    @Around(value="log()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws NotFoundException,Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        Integer id = ((Integer) joinPoint.getArgs()[0]);
        Blog blog = null;
        if(RedisUtils.isEmpty(BlogConstant.ONEBLOG + id)){
            blog =  (Blog) joinPoint.proceed();
            if(blog!=null){
                RedisUtils.hPut(BlogConstant.ONEBLOG + id,"blog", JSON.toJSONString(blog));
                RedisUtils.hPut(BlogConstant.ONEBLOG + id,"views",blog.getViews().toString());
            }
        }else{
            //存在该blog
            blog = JSON.parseObject(RedisUtils.hGet(BlogConstant.ONEBLOG + id,"blog").toString(),Blog.class);
            String views = (String)RedisUtils.hGet(BlogConstant.ONEBLOG + id,"views").toString();
            blog.setViews(Integer.valueOf(views));
        }
        //校验IP
        String key = BlogConstant.BLOGANDIMAP + id +":"+ ip;
        if(blog!=null && RedisUtils.isEmpty(key)){
            //不存在,则放入redis，一个xia时过期
            RedisUtils.set(key,"1");
            blog.setViews(blog.getViews()+1);
            RedisUtils.hIncrement(BlogConstant.ONEBLOG + id,"views");
        }
        logger.info("ip:{} blog:{}",ip,blog.toString());
        return blog;
    }

}
