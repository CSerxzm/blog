package com.xzm;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.tracing.dtrace.ArgsAttributes;
import com.xzm.bean.Blog;
import com.xzm.dao.BlogMapper;
import com.xzm.service.BlogService;
import com.xzm.util.MD5Utils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.cache.Cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	private StringEncryptor encryptor;

	@Test
	public  void test02(){
		String password = MD5Utils.code("123456");
		System.out.println(password);
		/*
		5f4dcc3b5aa765d61d8327deb882cf99
		 */
	}

	@Test
	public void test() {
		System.out.println("username:" + encryptor.encrypt("123456"));
		System.out.println("password:" + encryptor.encrypt("123456"));
		System.out.println("emailpassword:" + encryptor.encrypt("123456"));

	}

}
