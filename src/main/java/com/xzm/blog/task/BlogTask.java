package com.xzm.blog.task;

import com.xzm.blog.service.BlogService;
import com.xzm.blog.service.BlogServiceImpl;
import com.xzm.blog.util.SpringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.thymeleaf.spring4.context.SpringContextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiangzhimin
 * @Description
 * @create 2021-03-21 16:22
 */
@Component
public class BlogTask  extends QuartzJobBean {

    private BlogService blogService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        if(blogService==null){
            blogService= SpringUtils.getBean(BlogService .class);
        }
        blogService.saveViews();
    }

}
