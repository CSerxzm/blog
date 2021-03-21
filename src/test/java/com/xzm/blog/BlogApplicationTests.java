package com.xzm.blog;

import com.xzm.blog.util.MD5Utils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void test02() {
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
